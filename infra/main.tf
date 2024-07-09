terraform {
    backend "s3" {
        profile        = "consoft"
        bucket         = "consoft-tf-state"
        key            = "prod/terraform.tfstate"
        region         = "us-east-1"
        dynamodb_table = "consoft-tf-state-lock"
        encrypt        = true
            }
}

locals {
  environment = "prod"
}

provider "aws" {
    region                   = "us-east-1"
    # shared_credentials_files = [ "/Users/felip/.aws/credentials" ]
    # profile                  = "consoft"
    
    default_tags {
        tags = {
            project = "Cons-Soft"
        }
    }
}

data "aws_caller_identity" "this" { }

locals {
    account_id =data.aws_caller_identity.this.account_id
}

module "network" {
    source  = "./network"

    name_prefix        = "${local.environment}-ConSoft"
    availability_zones = [
        "us-east-1a",
        "us-east-1b"
    ]
}

module "security_groups" {
    source = "./security_groups"

    vpc_id      = module.network.vpc_id
    name_prefix = local.environment
}

module "rds" {
    source = "./rds"

    rds_instance_name        = "${local.environment}-consoft"
    database_name            = "${local.environment}"
    master_database_username = "${local.environment}"
    security_group_ids       = [module.security_groups.database_dev_security_group_id]
    subnet_ids               = module.network.public_subnet_ids
}

module "ec2" {
  source = "./ec2"

  name_prefix    = "${local.environment}-consoft"
  ami_id         = "ami-0bb84b8ffd87024d8"
  instance_type  = "t2.micro"
  vpc_id         = module.network.vpc_id
  subnet_id      = module.network.public_subnet_ids[0]
}

module "api_gateway" {
    source = "./api_gateway"

    name_prefix = "${local.environment}-consoft"
    public_dns  = module.ec2.public_dns
}

output "ec2_ip" {
  value = module.ec2.ec2_eip
}

output "database_url" {
  value = module.rds.database_url
}