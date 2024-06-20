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

# resource "aws_iam_role" "this" {
#   name = "${var.name_prefix}-ec2-api"

#   assume_role_policy = jsonencode({
#     Version = "2012-10-17"
#     Statement = [
#       {
#         Effect = "Allow"
#         Principal = {
#           Service = "ec2.amazonaws.com"
#         }
#         Action = "sts:AssumeRole"
#       }
#     ]
#   })
# }

# resource "aws_iam_policy" "this" {
#   name        = "${var.name_prefix}-ec2-api"
#   policy      = jsonencode({
#     Version = "2012-10-17"
#     Statement = [
#       {
#         Effect = "Allow"
#         Action = [
#           "secretsmanager:GetSecretValue",
#           "ecr:GetAuthorizationToken"
#         ]
#         Resource = "*"
#       },
#       {
#         Effect = "Allow"
#         Action = [
#           "ecr:GetDownloadUrlForLayer",
#           "ecr:BatchGetImage",
#           "ecr:BatchCheckLayerAvailability",
#         ]
#         Resource = "${var.ecr_arn}"
#       }
#     ]
#   })
# }

# resource "aws_iam_role_policy_attachment" "this" {
#   role       = aws_iam_role.this.name
#   policy_arn = aws_iam_policy.this.arn
# }

# resource "aws_iam_instance_profile" "this" {
#   name = "${var.name_prefix}-ec2-api"
#   role = "LabRole"
# }

resource "aws_instance" "this" {
  ami           = var.ami_id
  instance_type = var.instance_type
  subnet_id     = var.subnet_id
  vpc_security_group_ids = [aws_security_group.this.id]
  associate_public_ip_address = true
  # iam_instance_profile = aws_iam_instance_profile.this.name

  tags = {
    Name = "${var.name_prefix}-ec2-api"
  }

  
}

resource "aws_eip" "this" {
  instance = aws_instance.this.id
}
