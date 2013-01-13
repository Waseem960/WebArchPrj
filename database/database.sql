SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `unishopsh` DEFAULT CHARACTER SET utf8 ;
USE `unishopsh` ;

-- -----------------------------------------------------
-- Table `unishopsh`.`region`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `unishopsh`.`region` (
  `idregion` INT(11) NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(255) NOT NULL ,
  PRIMARY KEY (`idregion`) ,
  UNIQUE INDEX `idregion_UNIQUE` (`idregion` ASC) )
ENGINE = InnoDB
AUTO_INCREMENT = 21
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `unishopsh`.`district`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `unishopsh`.`district` (
  `iddistrict` INT(11) NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(255) NULL DEFAULT NULL ,
  `idregion` INT(11) NOT NULL ,
  PRIMARY KEY (`iddistrict`) ,
  UNIQUE INDEX `iddistrict_UNIQUE` (`iddistrict` ASC) ,
  INDEX `idregion` (`idregion` ASC) ,
  CONSTRAINT `idregion`
    FOREIGN KEY (`idregion` )
    REFERENCES `unishopsh`.`region` (`idregion` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 111
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `unishopsh`.`municipal`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `unishopsh`.`municipal` (
  `idmunicipal` INT(11) NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(255) NULL DEFAULT NULL ,
  `iddistrict` INT(11) NULL DEFAULT NULL ,
  PRIMARY KEY (`idmunicipal`) ,
  UNIQUE INDEX `idmunicipal_UNIQUE` (`idmunicipal` ASC) ,
  INDEX `iddistrict` (`iddistrict` ASC) ,
  CONSTRAINT `iddistrict`
    FOREIGN KEY (`iddistrict` )
    REFERENCES `unishopsh`.`district` (`iddistrict` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 8093
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `unishopsh`.`shop`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `unishopsh`.`shop` (
  `idshop` INT(11) NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(1000) NOT NULL ,
  `description` VARCHAR(5000) NULL DEFAULT NULL ,
  `address` VARCHAR(200) NULL DEFAULT NULL ,
  `idcity` INT(11) NULL DEFAULT NULL ,
  `author` VARCHAR(100) NULL DEFAULT NULL ,
  PRIMARY KEY (`idshop`) ,
  UNIQUE INDEX `idshop_UNIQUE` (`idshop` ASC) ,
  INDEX `idmunicipal` (`idcity` ASC) ,
  CONSTRAINT `idmunicipal`
    FOREIGN KEY (`idcity` )
    REFERENCES `unishopsh`.`municipal` (`idmunicipal` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 9
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `unishopsh`.`user`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `unishopsh`.`user` (
  `iduser` INT(11) NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) NULL DEFAULT NULL ,
  `surname` VARCHAR(45) NULL DEFAULT NULL ,
  `username` VARCHAR(45) NULL DEFAULT NULL ,
  `password` VARCHAR(45) NULL DEFAULT NULL ,
  `email` VARCHAR(45) NULL DEFAULT NULL ,
  `foto` BLOB NULL DEFAULT NULL ,
  PRIMARY KEY (`iduser`) ,
  UNIQUE INDEX `iduser_UNIQUE` (`iduser` ASC) )
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `unishopsh`.`comment`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `unishopsh`.`comment` (
  `idcomment` INT(11) NOT NULL AUTO_INCREMENT ,
  `usercomment` VARCHAR(1000) NULL DEFAULT NULL ,
  `idparent` INT(11) NULL DEFAULT NULL ,
  `date` TIMESTAMP NULL DEFAULT NULL ,
  `idshop` INT(11) NOT NULL ,
  `iduser` INT(11) NULL DEFAULT NULL ,
  PRIMARY KEY (`idcomment`) ,
  INDEX `idshop` (`idshop` ASC) ,
  INDEX `iduser` (`iduser` ASC) ,
  INDEX `idshop_idx` (`idshop` ASC) ,
  INDEX `iduser_idx` (`iduser` ASC) ,
  INDEX `iduser_idx1` (`iduser` ASC) ,
  CONSTRAINT `idshop`
    FOREIGN KEY (`idshop` )
    REFERENCES `unishopsh`.`shop` (`idshop` )
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `iduser`
    FOREIGN KEY (`iduser` )
    REFERENCES `unishopsh`.`user` (`iduser` )
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 12
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `unishopsh`.`shoppics`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `unishopsh`.`shoppics` (
  `idshoppics` INT(11) NOT NULL ,
  `name` VARCHAR(100) NULL DEFAULT NULL ,
  `pic` LONGBLOB NULL DEFAULT NULL ,
  `formatpic` VARCHAR(45) NULL DEFAULT NULL ,
  `idshop` INT(11) NULL DEFAULT NULL ,
  `shop_idshop` INT(11) NOT NULL ,
  PRIMARY KEY (`idshoppics`) ,
  INDEX `idshop` (`idshop` ASC) ,
  INDEX `fk_shoppics_shop1_idx` (`shop_idshop` ASC) ,
  CONSTRAINT `fk_shoppics_shop1`
    FOREIGN KEY (`shop_idshop` )
    REFERENCES `unishopsh`.`shop` (`idshop` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
