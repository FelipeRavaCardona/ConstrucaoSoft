output "ec2_eip" {
  value = aws_eip.this.public_ip
}

output "public_dns" {
  value = aws_instance.this.public_dns
}