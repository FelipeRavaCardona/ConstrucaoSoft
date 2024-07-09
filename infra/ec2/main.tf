resource "aws_key_pair" "this" {
  key_name   = "github-key"
  public_key = file("ssh-file.pub")
}

resource "aws_security_group" "this" {
  name_prefix = "${var.name_prefix}-ec2-api"
  vpc_id      = var.vpc_id

  ingress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }

  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }
}

resource "aws_instance" "this" {
  ami           = var.ami_id
  instance_type = var.instance_type
  subnet_id     = var.subnet_id
  vpc_security_group_ids = [aws_security_group.this.id]
  associate_public_ip_address = true
  key_name = aws_key_pair.this.key_name

  tags = {
    Name = "${var.name_prefix}-ec2-api"
  }

  user_data = <<-EOF
    sudo yum update -y
    sudo yum install docker -y
    sudo service docker start
    sudo groupadd docker
    sudo usermod -a -G docker ec2-user
    newgrp docker
  EOF
  
}

resource "aws_eip" "this" {
  instance = aws_instance.this.id
}
