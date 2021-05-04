-- MySQL Workbench Synchronization
-- Generated: 2021-05-04 09:49
-- Model: New Model
-- Version: 1.0
-- Project: Name of the project
-- Author: arnol

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

ALTER TABLE `university`.`groups` 
DROP FOREIGN KEY `fk_groups_subjects1`,
DROP FOREIGN KEY `fk_groups_teachers1`;

ALTER TABLE `university`.`teachers_has_Students` 
DROP FOREIGN KEY `fk_Teachers_has_Students_Students1`;

ALTER TABLE `university`.`groups_has_Students` 
DROP FOREIGN KEY `fk_Groups_has_Students_Groups1`,
DROP FOREIGN KEY `fk_Groups_has_Students_Students1`;

ALTER TABLE `university`.`groups` 
ADD INDEX `fk_groups_teachers1_idx` (`teachers_tea_id` ASC, `teachers_gro_id` ASC) VISIBLE,
DROP INDEX `fk_groups_teachers1_idx` ;
;

ALTER TABLE `university`.`groups` 
ADD CONSTRAINT `fk_groups_subjects1`
  FOREIGN KEY (`subject__id`)
  REFERENCES `university`.`subjects` (`sub_id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_groups_teachers1`
  FOREIGN KEY (`teachers_tea_id` , `teachers_gro_id`)
  REFERENCES `university`.`teachers` (`tea_id` , `gro_id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `university`.`teachers_has_Students` 
DROP FOREIGN KEY `fk_Teachers_has_Students_Teachers1`;

ALTER TABLE `university`.`teachers_has_Students` ADD CONSTRAINT `fk_Teachers_has_Students_Teachers1`
  FOREIGN KEY (`Teachers_tea_id` , `tea_grou_id`)
  REFERENCES `university`.`teachers` (`tea_id` , `gro_id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_Teachers_has_Students_Students1`
  FOREIGN KEY (`stu_id`)
  REFERENCES `university`.`students` (`stu_id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `university`.`groups_has_Students` 
ADD CONSTRAINT `fk_Groups_has_Students_Groups1`
  FOREIGN KEY (`Groups_gro_id`)
  REFERENCES `university`.`groups` (`gro_id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_Groups_has_Students_Students1`
  FOREIGN KEY (`Students_stu_id`)
  REFERENCES `university`.`students` (`stu_id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
