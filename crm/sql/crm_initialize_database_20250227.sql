CREATE DATABASE IF NOT EXISTS CRM;

CREATE TABLE IF NOT EXISTS `crm`.`customer` (
  `id` INT NOT NULL,
  `first_name` NVARCHAR(50) NOT NULL,
  `last_name` NVARCHAR(50) NOT NULL,
  `full_name` VARCHAR(100) NOT NULL,
  `phone` VARCHAR(50) NOT NULL,
  `email` VARCHAR(200) NOT NULL,
  `created_at` DATETIME NOT NULL,
  `updated_at` DATETIME NOT NULL,
  PRIMARY KEY (`id`));


CREATE TABLE IF NOT EXISTS `crm`.`order` (
  `id` INT NOT NULL,
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
  `id` INT NOT NULL,
  `customer_id` INT NOT NULL,
  `message` NVARCHAR(500) NOT NULL,
  `sent_at` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_message_customer_idx` (`customer_id` ASC) VISIBLE,
  CONSTRAINT `fk_message_customer`
    FOREIGN KEY (`customer_id`)
    REFERENCES `crm`.`customer` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


