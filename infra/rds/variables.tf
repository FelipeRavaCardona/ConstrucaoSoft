variable "is_storage_encrypted" {
    description = "Wether the storage should be encrypted"
    type        = bool
    default     = false
}

variable "is_publicly_acessible" {
    description = "Wether the RDS Instance should have a public IP"
    type        = bool
    default     = true
}

variable "rds_instance_name" {
    description = "Name to be given to the RDS Instance"
    type        = string
}

variable "database_name" {
    description = "Name to be given to the database creating in the RDS Instance"
    type        = string
}

variable "master_database_username" {
    description = "Username to be used for the master user"
    type        = string
    default     = "postgres"
}

variable "security_group_ids" {
    description = "list with the ids of the security groups the RDS Instance should be assigned to"
    type        = list(string)
}

variable "subnet_ids" {
    description = "list with the ids of the subnets the RDS Instance should be assigned to"
    type        = list(string)
}