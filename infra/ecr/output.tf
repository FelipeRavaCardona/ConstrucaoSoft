output "arn" {
  description = "ARN of the ecr used to push containers"
  value       = aws_ecr_repository.this.arn
}