terraform {
    backend "s3" {
        profile        = "consoft"
        bucket         = "consoft-tf-state"
        key            = "tf-backend/terraform.tfstate"
        region         = "us-east-1"
        dynamodb_table = "consoft-tf-state-lock"
        encrypt        = true
    }
}

provider "aws" {
    region = "us-east-1"
    shared_credentials_files = [ "/Users/felip/.aws/credentials" ]
    profile                  = "consoft"
}

resource "aws_s3_bucket" "this" {
    bucket        = "consoft-tf-state"
    force_destroy = true
}

resource "aws_s3_bucket_versioning" "this" {
    bucket = aws_s3_bucket.this.id

    versioning_configuration {
        status = "Enabled"
    }
}

resource "aws_s3_bucket_server_side_encryption_configuration" "this" {
    bucket = aws_s3_bucket.this.id

    rule {
        apply_server_side_encryption_by_default {
            sse_algorithm = "AES256"
        }
    }
}

resource "aws_dynamodb_table" "this" {
    name         = "consoft-tf-state-lock"
    billing_mode = "PAY_PER_REQUEST"
    hash_key     = "LockID"

    attribute {
        name = "LockID"
        type = "S"
    }
}