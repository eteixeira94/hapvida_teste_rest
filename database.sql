-- MySQL Workbench Synchronization
-- Generated: 2021-05-26 18:29
-- Model: New Model
-- Version: 1.0
-- Project: Name of the project
-- Author: ednar

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

CREATE SCHEMA IF NOT EXISTS `teste_hapvida` DEFAULT CHARACTER SET utf8 ;

CREATE TABLE IF NOT EXISTS `teste_hapvida`.`veterinario` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(255) NOT NULL,
  `telefone` VARCHAR(11) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `teste_hapvida`.`animal` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(255) NOT NULL,
  `especie` VARCHAR(30) NOT NULL,
  `raca` VARCHAR(255) NOT NULL,
  `data_nascimento` DATE NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `teste_hapvida`.`tutor` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(255) NOT NULL,
  `telefone` VARCHAR(11) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `teste_hapvida`.`consulta` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `data_consulta` DATE NOT NULL,
  `status` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `teste_hapvida`.`animal_has_tutor` (
  `animal_id` INT(11) NOT NULL,
  `tutor_id` INT(11) NOT NULL,
  PRIMARY KEY (`animal_id`, `tutor_id`),
  INDEX `tutor_idx` (`tutor_id` ASC) INVISIBLE,
  INDEX `animal_idx` (`animal_id` ASC) VISIBLE,
  CONSTRAINT `fk_aanimal`
    FOREIGN KEY (`animal_id`)
    REFERENCES `teste_hapvida`.`animal` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tutor`
    FOREIGN KEY (`tutor_id`)
    REFERENCES `teste_hapvida`.`tutor` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `teste_hapvida`.`consulta_has_animal` (
  `consulta_id` BIGINT(20) NOT NULL,
  `animal_id` INT(11) NOT NULL,
  `veterinario_id` INT(11) NOT NULL,
  PRIMARY KEY (`consulta_id`, `animal_id`, `veterinario_id`),
  INDEX `animal_idx` (`animal_id` ASC) VISIBLE,
  INDEX `consulta_idx` (`consulta_id` ASC) VISIBLE,
  INDEX `veterinario_idx` (`veterinario_id` ASC) VISIBLE,
  CONSTRAINT `fk_consulta`
    FOREIGN KEY (`consulta_id`)
    REFERENCES `teste_hapvida`.`consulta` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_animal`
    FOREIGN KEY (`animal_id`)
    REFERENCES `teste_hapvida`.`animal` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_veterinario`
    FOREIGN KEY (`veterinario_id`)
    REFERENCES `teste_hapvida`.`veterinario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
