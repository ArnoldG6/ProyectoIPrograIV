-- MySQL Workbench Synchronization
-- Generated: 2021-04-22 10:31
-- Model: New Model
-- Version: 1.0
-- Project: Name of the project
-- Author: arnol

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

CREATE SCHEMA IF NOT EXISTS `Universidad` DEFAULT CHARACTER SET utf8 COLLATE utf8_danish_ci ;

CREATE TABLE IF NOT EXISTS `Universidad`.`groups` (
  `gro_id` VARCHAR(6) NOT NULL,
  PRIMARY KEY (`gro_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_danish_ci;

CREATE TABLE IF NOT EXISTS `Universidad`.`administrators` (
  `admin_id` VARCHAR(9) NOT NULL,
  `username` VARCHAR(25) NULL DEFAULT NULL,
  `email` VARCHAR(45) NULL DEFAULT NULL,
  `pho_num` VARCHAR(45) NULL DEFAULT NULL,
  `password` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`admin_id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_danish_ci;

CREATE TABLE IF NOT EXISTS `Universidad`.`subjects` (
  `sub_id` VARCHAR(6) NOT NULL,
  `sub_name` VARCHAR(35) NULL DEFAULT NULL,
  `Groups_gro_id` VARCHAR(6) NOT NULL,
  PRIMARY KEY (`sub_id`, `Groups_gro_id`),
  INDEX `fk_Subjects_Groups1_idx` (`Groups_gro_id` ASC) VISIBLE,
  CONSTRAINT `fk_Subjects_Groups1`
    FOREIGN KEY (`Groups_gro_id`)
    REFERENCES `Universidad`.`groups` (`gro_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_danish_ci;

CREATE TABLE IF NOT EXISTS `Universidad`.`students` (
  `stu_id` VARCHAR(9) NOT NULL,
  `username` VARCHAR(25) NULL DEFAULT NULL,
  `email` VARCHAR(45) NULL DEFAULT NULL,
  `pho_num` VARCHAR(45) NULL DEFAULT NULL,
  `password` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`stu_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_danish_ci;

CREATE TABLE IF NOT EXISTS `Universidad`.`teachers` (
  `tea_id` VARCHAR(9) NOT NULL,
  `username` VARCHAR(25) NULL DEFAULT NULL,
  `email` VARCHAR(45) NULL DEFAULT NULL,
  `phone_num` VARCHAR(45) NULL DEFAULT NULL,
  `gro_id` VARCHAR(6) NOT NULL,
  PRIMARY KEY (`tea_id`, `gro_id`),
  INDEX `fk_Teachers_Groups_idx` (`gro_id` ASC) VISIBLE,
  CONSTRAINT `fk_Teachers_Groups`
    FOREIGN KEY (`gro_id`)
    REFERENCES `Universidad`.`groups` (`gro_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_danish_ci;

CREATE TABLE IF NOT EXISTS `Universidad`.`teachers_has_Students` (
  `Teachers_tea_id` VARCHAR(9) NOT NULL,
  `tea_grou_id` VARCHAR(6) NOT NULL,
  `stu_id` VARCHAR(9) NOT NULL,
  PRIMARY KEY (`Teachers_tea_id`, `tea_grou_id`, `stu_id`),
  INDEX `fk_Teachers_has_Students_Students1_idx` (`stu_id` ASC) VISIBLE,
  INDEX `fk_Teachers_has_Students_Teachers1_idx` (`Teachers_tea_id` ASC, `tea_grou_id` ASC) VISIBLE,
  CONSTRAINT `fk_Teachers_has_Students_Teachers1`
    FOREIGN KEY (`Teachers_tea_id` , `tea_grou_id`)
    REFERENCES `Universidad`.`teachers` (`tea_id` , `gro_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Teachers_has_Students_Students1`
    FOREIGN KEY (`stu_id`)
    REFERENCES `Universidad`.`students` (`stu_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_danish_ci;

CREATE TABLE IF NOT EXISTS `Universidad`.`groups_has_Students` (
  `Groups_gro_id` VARCHAR(6) NOT NULL,
  `Students_stu_id` VARCHAR(9) NOT NULL,
  PRIMARY KEY (`Groups_gro_id`, `Students_stu_id`),
  INDEX `fk_Groups_has_Students_Students1_idx` (`Students_stu_id` ASC) VISIBLE,
  INDEX `fk_Groups_has_Students_Groups1_idx` (`Groups_gro_id` ASC) VISIBLE,
  CONSTRAINT `fk_Groups_has_Students_Groups1`
    FOREIGN KEY (`Groups_gro_id`)
    REFERENCES `Universidad`.`groups` (`gro_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Groups_has_Students_Students1`
    FOREIGN KEY (`Students_stu_id`)
    REFERENCES `Universidad`.`students` (`stu_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_danish_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
