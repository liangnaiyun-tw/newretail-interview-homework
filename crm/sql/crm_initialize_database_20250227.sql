CREATE DATABASE IF NOT EXISTS CRM;

CREATE TABLE IF NOT EXISTS `crm`.`customer` (
                                                `id` INT NOT NULL AUTO_INCREMENT,
                                                `first_name` NVARCHAR(50) NOT NULL,
    `last_name` NVARCHAR(50) NOT NULL,
    `full_name` VARCHAR(100) NOT NULL,
    `phone` VARCHAR(50) NOT NULL,
    `email` VARCHAR(200) NOT NULL,
    `created_at` DATETIME NOT NULL,
    `updated_at` DATETIME NOT NULL,
    PRIMARY KEY (`id`));


CREATE TABLE IF NOT EXISTS `crm`.`order` (
                                             `id` INT NOT NULL AUTO_INCREMENT,
                                             `customer_id` INT NOT NULL,
                                             `amount` DECIMAL(10,2) NOT NULL,
    `order_date` DATETIME NOT NULL,
    `created_at` DATETIME NOT NULL,
    `updated_at` DATETIME NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `customer_id_idx` (`customer_id` ASC) VISIBLE,
    CONSTRAINT `fk_order_customer`
    FOREIGN KEY (`customer_id`)
    REFERENCES `crm`.`customer` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


CREATE TABLE IF NOT EXISTS `crm`.`message` (
   `id` INT NOT NULL AUTO_INCREMENT,
   `customer_id` INT NOT NULL,
   `message` NVARCHAR(500) NOT NULL,
    `sent_at` DATETIME,
    `status` INT NOT NULL,
    `created_at` DATETIME NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_message_customer_idx` (`customer_id` ASC) VISIBLE,
    CONSTRAINT `fk_message_customer`
    FOREIGN KEY (`customer_id`)
    REFERENCES `crm`.`customer` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

-- 插入 customer 表的數據
INSERT INTO `crm`.`customer` (first_name, last_name, full_name, phone, email, created_at, updated_at)
VALUES
    ('John', 'Doe', 'John Doe', '0912345678', 'john.doe@example.com', NOW(), NOW()),
    ('Jane', 'Smith', 'Jane Smith', '0923456789', 'jane.smith@example.com', NOW(), NOW()),
    ('Alice', 'Johnson', 'Alice Johnson', '0934567890', 'alice.johnson@example.com', NOW(), NOW()),
    ('Bob', 'Brown', 'Bob Brown', '0945678901', 'bob.brown@example.com', NOW(), NOW());

-- 插入 order 表的數據
INSERT INTO `crm`.`order` (customer_id, amount, order_date, created_at, updated_at)
VALUES
    (1, 600.00, NOW() - INTERVAL 10 DAY, NOW(), NOW()),  -- John Doe
    (1, 400.00, NOW() - INTERVAL 20 DAY, NOW(), NOW()),  -- John Doe
    (2, 700.00, NOW() - INTERVAL 5 DAY, NOW(), NOW()),   -- Jane Smith
    (3, 800.00, NOW() - INTERVAL 15 DAY, NOW(), NOW()),  -- Alice Johnson
    (4, 300.00, NOW() - INTERVAL 25 DAY, NOW(), NOW()),  -- Bob Brown
    (2, 550.00, NOW() - INTERVAL 3 DAY, NOW(), NOW());    -- Jane Smith

