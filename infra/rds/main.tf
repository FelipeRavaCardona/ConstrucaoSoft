data "aws_secretsmanager_random_password" "this" {
    password_length     = 16
    exclude_punctuation = true
}

resource "aws_secretsmanager_secret" "this" {
    name                    = "${var.rds_instance_name}"
    recovery_window_in_days = 0
}

resource "aws_secretsmanager_secret_version" "password" {
    secret_id     = aws_secretsmanager_secret.this.id
    secret_string = jsonencode({
        "password": data.aws_secretsmanager_random_password.this.random_password
    })

    lifecycle {
        ignore_changes = [ 
            secret_string 
        ]
    }
}

data "aws_secretsmanager_secret_version" "this" {
    secret_id = aws_secretsmanager_secret.this.id

    depends_on = [ 
        aws_secretsmanager_secret.this,
        aws_secretsmanager_secret_version.password
    ]
}

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
    password               = jsondecode(data.aws_secretsmanager_secret_version.this.secret_string)["password"]

    depends_on = [ 
        aws_secretsmanager_secret_version.password
    ]

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

resource "aws_secretsmanager_secret_version" "updated" {
    secret_id     = aws_secretsmanager_secret.this.id
    secret_string = jsonencode({
        "username": "${aws_db_instance.this.username}",
        "password": "${jsondecode(data.aws_secretsmanager_secret_version.this.secret_string)["password"]}",
        "endpoint": "${aws_db_instance.this.endpoint}",
        "db_name": "${aws_db_instance.this.db_name}"
    })

    lifecycle {
        ignore_changes = [ 
            secret_string 
        ]
    }

    depends_on = [ 
        aws_db_instance.this 
    ]
}