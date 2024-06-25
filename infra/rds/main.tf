resource "aws_db_subnet_group" "this" {
    name       = "${var.rds_instance_name}-db-subnet"
    subnet_ids = var.subnet_ids
}

resource "aws_db_instance" "this" {
    instance_class         = "db.t4g.micro"
    allocated_storage      = 20
    engine                 = "postgres"
    engine_version         = "16.1"
    skip_final_snapshot    = true
    vpc_security_group_ids = var.security_group_ids
    db_subnet_group_name   = aws_db_subnet_group.this.name
    storage_encrypted      = var.is_storage_encrypted
    publicly_accessible    = var.is_publicly_acessible
    identifier             = var.rds_instance_name
    db_name                = var.database_name
    username               = var.master_database_username
    password               = var.database_password

    lifecycle {
        ignore_changes = [
            instance_class,
            engine,
            engine_version, 
            identifier,
            db_name,
            username,
            password
        ]
    }
}
