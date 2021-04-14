-- MySQL Workbench Synchronization
-- Generated: 2021-04-14 16:46
-- Model: New Model
-- Version: 1.0
-- Project: Name of the project
-- Author: arnol

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

ALTER TABLE `universidad`.`Subjects` 
DROP FOREIGN KEY `fk_Subjects_Groups1`;

ALTER TABLE `universidad`.`Teachers` 
DROP FOREIGN KEY `fk_Teachers_Groups`;

ALTER TABLE `universidad`.`Teachers_has_Students` 
DROP FOREIGN KEY `fk_Teachers_has_Students_Students1`;

ALTER TABLE `universidad`.`Groups_has_Students` 
DROP FOREIGN KEY `fk_Groups_has_Students_Groups1`,
DROP FOREIGN KEY `fk_Groups_has_Students_Students1`;

ALTER TABLE `universidad`.`Subjects` 
ADD INDEX `fk_Subjects_Groups1_idx` (`Groups_gro_id` ASC) VISIBLE,
DROP INDEX `fk_Subjects_Groups1_idx` ;
;

ALTER TABLE `universidad`.`Teachers` 
DROP COLUMN `gro_id`,
ADD COLUMN `gro_id` VARCHAR(6) NOT NULL AFTER `phone_num`,
ADD INDEX `fk_Teachers_Groups_idx` (`gro_id` ASC) VISIBLE,
DROP INDEX `fk_Teachers_Groups_idx` ;
ALTER TABLE `universidad`.`Teachers` ALTER INDEX `PRIMARY` VISIBLE;

ALTER TABLE `universidad`.`Teachers_has_Students` 
DROP COLUMN `stu_id`,
DROP COLUMN `tea_grou_id`,
ADD COLUMN `tea_grou_id` VARCHAR(6) NOT NULL AFTER `Teachers_tea_id`,
ADD COLUMN `stu_id` VARCHAR(9) NOT NULL AFTER `tea_grou_id`,
ADD INDEX `fk_Teachers_has_Students_Students1_idx` (`stu_id` ASC) VISIBLE,
ADD INDEX `fk_Teachers_has_Students_Teachers1_idx` (`Teachers_tea_id` ASC, `tea_grou_id` ASC) VISIBLE,
DROP INDEX `fk_Teachers_has_Students_Teachers1_idx` ,
DROP INDEX `fk_Teachers_has_Students_Students1_idx` ;
ALTER TABLE `universidad`.`Teachers` ALTER INDEX `PRIMARY` VISIBLE;
ALTER TABLE `universidad`.`Teachers_has_Students` ALTER INDEX `PRIMARY` VISIBLE;

ALTER TABLE `universidad`.`Groups_has_Students` 
ADD INDEX `fk_Groups_has_Students_Students1_idx` (`Students_stu_id` ASC) VISIBLE,
ADD INDEX `fk_Groups_has_Students_Groups1_idx` (`Groups_gro_id` ASC) VISIBLE,
DROP INDEX `fk_Groups_has_Students_Groups1_idx` ,
DROP INDEX `fk_Groups_has_Students_Students1_idx` ;
ALTER TABLE `universidad`.`Teachers` ALTER INDEX `PRIMARY` VISIBLE;
ALTER TABLE `universidad`.`Teachers_has_Students` ALTER INDEX `PRIMARY` VISIBLE;

ALTER TABLE `universidad`.`Subjects` 
ADD CONSTRAINT `fk_Subjects_Groups1`
  FOREIGN KEY (`Groups_gro_id`)
  REFERENCES `universidad`.`Groups` (`gro_id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `universidad`.`Teachers` 
ADD CONSTRAINT `fk_Teachers_Groups`
  FOREIGN KEY (`gro_id`)
  REFERENCES `universidad`.`Groups` (`gro_id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `universidad`.`Teachers_has_Students` 
DROP FOREIGN KEY `fk_Teachers_has_Students_Teachers1`;

ALTER TABLE `universidad`.`Teachers_has_Students` ADD CONSTRAINT `fk_Teachers_has_Students_Teachers1`
  FOREIGN KEY (`Teachers_tea_id` , `tea_grou_id`)
  REFERENCES `universidad`.`Teachers` (`tea_id` , `gro_id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_Teachers_has_Students_Students1`
  FOREIGN KEY (`stu_id`)
  REFERENCES `universidad`.`Students` (`stu_id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `universidad`.`Groups_has_Students` 
ADD CONSTRAINT `fk_Groups_has_Students_Groups1`
  FOREIGN KEY (`Groups_gro_id`)
  REFERENCES `universidad`.`Groups` (`gro_id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_Groups_has_Students_Students1`
  FOREIGN KEY (`Students_stu_id`)
  REFERENCES `universidad`.`Students` (`stu_id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
