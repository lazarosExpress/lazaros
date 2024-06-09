-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema lazarosDB
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema lazarosDB
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS lazarosDB DEFAULT CHARACTER SET utf8 ;
USE lazarosDB ;

-- -----------------------------------------------------
-- Table lazarosDB.customer
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS lazarosDB.customer (
  customer_id INT NOT NULL AUTO_INCREMENT,
  customer_firstName VARCHAR(45) NOT NULL,
  customer_eMail VARCHAR(45) NOT NULL,
  customer_lastName VARCHAR(45) NOT NULL,
  customer_password VARCHAR(45) NOT NULL,
  customer_phoneNumber VARCHAR(45) NOT NULL,
  PRIMARY KEY (customer_id))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table lazarosDB.Address
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS lazarosDB.Address (
  address_id INT NOT NULL AUTO_INCREMENT,
  address_title VARCHAR(45) NOT NULL,
  address_province VARCHAR(45) NOT NULL,
  address_district VARCHAR(45) NOT NULL,
  address_neighbourhood VARCHAR(45) NOT NULL,
  address_firstName VARCHAR(45) NOT NULL,
  address_lastName VARCHAR(45) NOT NULL,
  address_phoneNumber VARCHAR(45) NOT NULL,
  address_fullAddress VARCHAR(120) NOT NULL,
  customer_id INT NOT NULL,
  PRIMARY KEY (address_id),
  INDEX fk_Address_customer1_idx (customer_id ASC) VISIBLE,
  CONSTRAINT fk_Address_customer1
    FOREIGN KEY (customer_id)
    REFERENCES lazarosDB.customer (customer_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table lazarosDB.Supplier
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS lazarosDB.Supplier (
  supplier_id INT NOT NULL AUTO_INCREMENT,
  supplier_firstName VARCHAR(45) NOT NULL,
  supplier_lastName VARCHAR(45) NOT NULL,
  supplier_shopName VARCHAR(45) NOT NULL,
  supplier_iban VARCHAR(45) NOT NULL,
  supplier_email VARCHAR(45) NOT NULL,
  supplier_password VARCHAR(45) NOT NULL,
  supplier_phoneNumber VARCHAR(45) NOT NULL,
  PRIMARY KEY (supplier_id))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table lazarosDB.Unit
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS lazarosDB.Unit (
  unit_code VARCHAR(5) NOT NULL,
  unit_name VARCHAR(45) NOT NULL,
  unit_id INT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (unit_id))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table lazarosDB.Catagory
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS lazarosDB.Category (
  category_code VARCHAR(30) NOT NULL,
  category_name VARCHAR(45) NOT NULL,
  category_id INT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (category_id))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table lazarosDB.Product
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS lazarosDB.Product (
  product_id INT NOT NULL AUTO_INCREMENT,
  product_name VARCHAR(45) NOT NULL,
  product_price DOUBLE NOT NULL,
  product_stockQty INT NOT NULL,
  product_discountedPrice DOUBLE NOT NULL,
  product_explanation VARCHAR(499) NOT NULL,
  product_properties VARCHAR(45) NOT NULL,
  unit_id INT NOT NULL,
  supplier_id INT NOT NULL,
  category_id INT NOT NULL,
  PRIMARY KEY (product_id),
  INDEX fk_Product_Unit1_idx (unit_id ASC) VISIBLE,
  INDEX fk_Product_Supplier1_idx (supplier_id ASC) VISIBLE,
  INDEX fk_Product_Category1_idx (category_id ASC) VISIBLE,
  CONSTRAINT fk_Product_Unit1
    FOREIGN KEY (unit_id)
    REFERENCES lazarosDB.Unit (unit_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_Product_Supplier1
    FOREIGN KEY (supplier_id)
    REFERENCES lazarosDB.Supplier (supplier_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_Product_Category1
    FOREIGN KEY (category_id)
    REFERENCES lazarosDB.Category (category_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table lazarosDB.Payment
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS lazarosDB.Payment (
  payment_id INT NOT NULL AUTO_INCREMENT,
  payment_code VARCHAR(45) NOT NULL,
  payment_name VARCHAR(45) NOT NULL,
  PRIMARY KEY (payment_id))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table lazarosDB.Order
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS lazarosDB.Order (
  order_id INT NOT NULL AUTO_INCREMENT,
  order_date DATE NOT NULL,
  order_totalPrice DOUBLE NOT NULL,
  payment_id INT NOT NULL,
  customer_id INT NOT NULL,
  PRIMARY KEY (order_id),
  INDEX fk_Order_Payment1_idx (payment_id ASC) VISIBLE,
  INDEX fk_Order_customer1_idx (customer_id ASC) VISIBLE,
  CONSTRAINT fk_Order_Payment1
    FOREIGN KEY (payment_id)
    REFERENCES lazarosDB.Payment (payment_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_Order_customer1
    FOREIGN KEY (customer_id)
    REFERENCES lazarosDB.customer (customer_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table lazarosDB.OrderDetail
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS lazarosDB.OrderDetail (
  orderDetail_id INT NOT NULL AUTO_INCREMENT,
  order_qty INT NOT NULL,
  product_price DOUBLE NOT NULL,
  line_total DOUBLE NOT NULL,
  product_id INT NOT NULL,
  order_id INT NOT NULL,
  unit_id INT NOT NULL,
  PRIMARY KEY (orderDetail_id),
  INDEX fk_OrderDetail_Product1_idx (product_id ASC) VISIBLE,
  INDEX fk_OrderDetail_Order1_idx (order_id ASC) VISIBLE,
  INDEX fk_OrderDetail_Unit1_idx (unit_id ASC) VISIBLE,
  CONSTRAINT fk_OrderDetail_Product1
    FOREIGN KEY (product_id)
    REFERENCES lazarosDB.Product (product_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_OrderDetail_Order1
    FOREIGN KEY (order_id)
    REFERENCES lazarosDB.Order (order_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_OrderDetail_Unit1
    FOREIGN KEY (unit_id)
    REFERENCES lazarosDB.Unit (unit_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table lazarosDB.Basket
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS lazarosDB.Basket (
  basket_id INT NOT NULL AUTO_INCREMENT,
  basket_qty INT NOT NULL,
  customer_id INT NOT NULL,
  product_id INT NOT NULL,
  PRIMARY KEY (basket_id),
  INDEX fk_Basket_customer1_idx (customer_id ASC) VISIBLE,
  INDEX fk_Basket_Product1_idx (product_id ASC) VISIBLE,
  CONSTRAINT fk_Basket_customer1
    FOREIGN KEY (customer_id)
    REFERENCES lazarosDB.customer (customer_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_Basket_Product1
    FOREIGN KEY (product_id)
    REFERENCES lazarosDB.Product (product_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table lazarosDB.Comments
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS lazarosDB.Comments (
  comments_id INT NOT NULL AUTO_INCREMENT,
  comments_text VARCHAR(100) NOT NULL,
  PRIMARY KEY (comments_id))
ENGINE = InnoDB;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
