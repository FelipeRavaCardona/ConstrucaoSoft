resource "aws_ecr_repository" "this" {
  name         = "${var.environment}-consoft"
  force_delete = true

  image_scanning_configuration {
    scan_on_push = true
  }
}
