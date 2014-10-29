
SET FOREIGN_KEY_CHECKS=0;
SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `googlereaderreplacement` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `googlereaderreplacement` ;

-- -----------------------------------------------------
-- Table `googlereaderreplacement`.`users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `googlereaderreplacement`.`users` ;

CREATE  TABLE IF NOT EXISTS `googlereaderreplacement`.`users` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `created` DATETIME NOT NULL ,
  `first_name` VARCHAR(45) NOT NULL ,
  `last_name` VARCHAR(45) NOT NULL ,
  `email` VARCHAR(50) NOT NULL ,
  `password` VARCHAR(40) NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `googlereaderreplacement`.`feeds`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `googlereaderreplacement`.`feeds` ;

CREATE  TABLE IF NOT EXISTS `googlereaderreplacement`.`feeds` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `created` DATETIME NOT NULL ,
  `name` VARCHAR(45) NULL ,
  `url` VARCHAR(1024) NOT NULL ,
  `url_md5` VARCHAR(32) NOT NULL ,
  `refreshed` DATETIME NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `url_md5_UNIQUE` (`url_md5` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `googlereaderreplacement`.`items`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `googlereaderreplacement`.`items` ;

CREATE  TABLE IF NOT EXISTS `googlereaderreplacement`.`items` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `created` DATETIME NOT NULL ,
  `feed_id` INT NOT NULL ,
  `title` VARCHAR(255) NOT NULL ,
  `link` VARCHAR(1024) NOT NULL ,
  `link_md5` VARCHAR(32) NOT NULL ,
  `description` TEXT NULL ,
  `pubDate` DATETIME NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_items_feeds1_idx` (`feed_id` ASC) ,
  UNIQUE INDEX `index3` (`feed_id` ASC, `link_md5` ASC) ,
  CONSTRAINT `fk_items_feeds1`
    FOREIGN KEY (`feed_id` )
    REFERENCES `googlereaderreplacement`.`feeds` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `googlereaderreplacement`.`feeds_users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `googlereaderreplacement`.`feeds_users` ;

CREATE  TABLE IF NOT EXISTS `googlereaderreplacement`.`feeds_users` (
  `users_id` INT NOT NULL ,
  `feeds_id` INT NOT NULL ,
  `favorite` TINYINT(1) NOT NULL ,
  PRIMARY KEY (`users_id`, `feeds_id`) ,
  INDEX `fk_users_feeds_feeds1_idx` (`feeds_id` ASC) ,
  INDEX `fk_users_feeds_users1_idx` (`users_id` ASC) ,
  CONSTRAINT `fk_users_feeds_users1`
    FOREIGN KEY (`users_id` )
    REFERENCES `googlereaderreplacement`.`users` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_users_feeds_feeds1`
    FOREIGN KEY (`feeds_id` )
    REFERENCES `googlereaderreplacement`.`feeds` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `googlereaderreplacement`.`items_users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `googlereaderreplacement`.`items_users` ;

CREATE  TABLE IF NOT EXISTS `googlereaderreplacement`.`items_users` (
  `items_id` INT NOT NULL ,
  `users_id` INT NOT NULL ,
  `starred` TINYINT(1) NOT NULL ,
  PRIMARY KEY (`items_id`, `users_id`) ,
  INDEX `fk__users_users1_idx` (`users_id` ASC) ,
  INDEX `fk__users_1_idx` (`items_id` ASC) ,
  CONSTRAINT `fk__users_1`
    FOREIGN KEY (`items_id`)
    REFERENCES `googlereaderreplacement`.`items` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk__users_users1`
    FOREIGN KEY (`users_id`)
    REFERENCES `googlereaderreplacement`.`users` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

USE `googlereaderreplacement` ;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
SET FOREIGN_KEY_CHECKS=1;