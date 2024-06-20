locals {
  subnet_bits = ceil(log(var.maximum_subnet_number, 2))
  host_bits   = ceil(log(var.maximum_host_per_subnet + 2, 2))
}

resource "aws_vpc" "this" {
  cidr_block           = "10.0.0.0/${32 - local.host_bits - local.subnet_bits}"
  enable_dns_support   = true
  enable_dns_hostnames = true
  tags = {
    Name = "${var.name_prefix}-vpc"
  }
}

resource "aws_internet_gateway" "this" {
  vpc_id = aws_vpc.this.id

  tags = {
    Name = "${var.name_prefix}-internet-gateway"
  }
}

resource "aws_eip" "this" {
  domain = "vpc"

  tags = {
    Name = "${var.name_prefix}-elastic-ip"
  }
}

resource "aws_route_table" "public" {
  vpc_id = aws_vpc.this.id

  route {
    cidr_block = "0.0.0.0/0"
    gateway_id = aws_internet_gateway.this.id
  }

  tags = {
    Name = "${var.name_prefix}-public-route-table"
  }
}

resource "aws_subnet" "public_subnet" {
  for_each = toset(var.availability_zones)

  vpc_id                  = aws_vpc.this.id
  cidr_block              = cidrsubnet(aws_vpc.this.cidr_block, local.host_bits, index(var.availability_zones, each.value))
  availability_zone       = each.value
  map_public_ip_on_launch = true

  tags = {
    Name = "${var.name_prefix}-${each.value}-public-subnet"
  }
}

resource "aws_route_table_association" "public" {
  for_each = { for az, subnet in aws_subnet.public_subnet : az => subnet.id }

  subnet_id      = each.value
  route_table_id = aws_route_table.public.id
}
