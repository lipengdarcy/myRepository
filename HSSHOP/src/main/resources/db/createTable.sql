
-- 属性表 
CREATE TABLE `hsshop_dev`.`bsp_attributes` (
  `attrid` INT NOT NULL COMMENT '',
  `name` VARCHAR(200) NULL COMMENT '',
  `cateid` INT NULL COMMENT '',
  `attrgroupid` INT NULL COMMENT '',
  `showtype` INT NULL COMMENT '',
  `isfilter` TINYINT(10) NULL COMMENT '',
  `displayorder` INT NULL COMMENT '',
  `skuorder` INT NULL COMMENT '',
  PRIMARY KEY (`attrid`)  COMMENT '')
ENGINE = MyISAM
DEFAULT CHARACTER SET = utf8;


    