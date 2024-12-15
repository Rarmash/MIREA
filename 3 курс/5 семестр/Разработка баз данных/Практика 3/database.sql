-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema coffeehouse
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema coffeehouse
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `coffeehouse` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `coffeehouse` ;

-- -----------------------------------------------------
-- Table `coffeehouse`.`customers`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `coffeehouse`.`customers` (
  `customer_id` INT NOT NULL AUTO_INCREMENT,
  `fullName` VARCHAR(50) NOT NULL,
  `phoneNumber` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`customer_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `coffeehouse`.`positions`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `coffeehouse`.`positions` (
  `position_id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`position_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `coffeehouse`.`employees`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `coffeehouse`.`employees` (
  `employee_id` INT NOT NULL AUTO_INCREMENT,
  `fullName` VARCHAR(50) NOT NULL,
  `phoneNumber` VARCHAR(20) NOT NULL,
  `position_id` INT NOT NULL,
  PRIMARY KEY (`employee_id`),
  INDEX `position_id` (`position_id` ASC) VISIBLE,
  CONSTRAINT `employees_ibfk_1`
    FOREIGN KEY (`position_id`)
    REFERENCES `coffeehouse`.`positions` (`position_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

-- -----------------------------------------------------
-- Table `coffeehouse`.`orders`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `coffeehouse`.`orders` (
  `order_id` INT NOT NULL AUTO_INCREMENT,
  `customer_id` INT NOT NULL,
  `employee_id` INT NOT NULL,
  `date` DATE NOT NULL,
  `status` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`order_id`),
  INDEX `customer_id` (`customer_id` ASC) VISIBLE,
  INDEX `employee_id` (`employee_id` ASC) VISIBLE,
  CONSTRAINT `orders_ibfk_1`
    FOREIGN KEY (`customer_id`)
    REFERENCES `coffeehouse`.`customers` (`customer_id`),
  CONSTRAINT `orders_ibfk_2`
    FOREIGN KEY (`employee_id`)
    REFERENCES `coffeehouse`.`employees` (`employee_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `coffeehouse`.`products`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `coffeehouse`.`products` (
  `product_id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`product_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `coffeehouse`.`ordersproducts`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `coffeehouse`.`ordersproducts` (
  `order_id` INT NOT NULL,
  `product_id` INT NOT NULL,
  `quantity` INT NOT NULL,
  PRIMARY KEY (`order_id`, `product_id`),
  INDEX `product_id` (`product_id` ASC) VISIBLE,
  CONSTRAINT `ordersproducts_ibfk_1`
    FOREIGN KEY (`order_id`)
    REFERENCES `coffeehouse`.`orders` (`order_id`),
  CONSTRAINT `ordersproducts_ibfk_2`
    FOREIGN KEY (`product_id`)
    REFERENCES `coffeehouse`.`products` (`product_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `coffeehouse`.`productcount`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `coffeehouse`.`productcount` (
  `product_id` INT NOT NULL,
  `count` INT NOT NULL,
  PRIMARY KEY (`product_id`),
  CONSTRAINT `productcount_ibfk_1`
    FOREIGN KEY (`product_id`)
    REFERENCES `coffeehouse`.`products` (`product_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `coffeehouse`.`suppliers`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `coffeehouse`.`suppliers` (
  `supplier_id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(50) NOT NULL,
  `phoneNumber` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`supplier_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `coffeehouse`.`productssuppliers`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `coffeehouse`.`productssuppliers` (
  `product_id` INT NOT NULL,
  `supplier_id` INT NOT NULL,
  PRIMARY KEY (`product_id`, `supplier_id`),
  INDEX `supplier_id` (`supplier_id` ASC) VISIBLE,
  CONSTRAINT `productssuppliers_ibfk_1`
    FOREIGN KEY (`product_id`)
    REFERENCES `coffeehouse`.`products` (`product_id`),
  CONSTRAINT `productssuppliers_ibfk_2`
    FOREIGN KEY (`supplier_id`)
    REFERENCES `coffeehouse`.`suppliers` (`supplier_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `coffeehouse`.`reviews`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `coffeehouse`.`reviews` (
  `review_id` INT NOT NULL AUTO_INCREMENT,
  `customer_id` INT NOT NULL,
  `text` TEXT NOT NULL,
  `date` DATE NOT NULL,
  PRIMARY KEY (`review_id`),
  INDEX `customer_id` (`customer_id` ASC) VISIBLE,
  CONSTRAINT `reviews_ibfk_1`
    FOREIGN KEY (`customer_id`)
    REFERENCES `coffeehouse`.`customers` (`customer_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
