
-- 属性表 
CREATE TABLE `bsp_attributes` (
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

-- 订单表 
CREATE TABLE `bsp_orders` (
  `oid` INT NOT NULL COMMENT '',
  `osn` VARCHAR(200) NULL COMMENT '',
    `uid` INT NULL COMMENT '',
     `orderstate` INT NULL COMMENT '',
      `productamount` DECIMAL NULL COMMENT '',
      `orderamount` DECIMAL NULL COMMENT '',
      `surplusmoney` DECIMAL NULL COMMENT '',
   
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


		<result column="parentid" jdbcType="INTEGER" property="parentid" />
		<result column="isreview" jdbcType="TINYINT" property="isreview" />
		<result column="addtime" jdbcType="TIMESTAMP" property="addtime" />
		<result column="shipsn" jdbcType="VARCHAR" property="shipsn" />
		<result column="shipsystemname" jdbcType="VARCHAR" property="shipsystemname" />
		<result column="shipfriendname" jdbcType="VARCHAR" property="shipfriendname" />
		<result column="shiptime" jdbcType="TIMESTAMP" property="shiptime" />
		<result column="paysn" jdbcType="VARCHAR" property="paysn" />
		<result column="paysystemname" jdbcType="VARCHAR" property="paysystemname" />
		<result column="payfriendname" jdbcType="VARCHAR" property="payfriendname" />
		<result column="paymode" jdbcType="TINYINT" property="paymode" />
		<result column="paytime" jdbcType="TIMESTAMP" property="paytime" />
		<result column="regionid" jdbcType="INTEGER" property="regionid" />
		<result column="consignee" jdbcType="VARCHAR" property="consignee" />
		<result column="mobile" jdbcType="VARCHAR" property="mobile" />
		<result column="phone" jdbcType="VARCHAR" property="phone" />
		<result column="email" jdbcType="VARCHAR" property="email" />
		<result column="zipcode" jdbcType="VARCHAR" property="zipcode" />
		<result column="address" jdbcType="VARCHAR" property="address" />
		<result column="besttime" jdbcType="DATE" property="besttime" />
		<result column="shipfee" jdbcType="DECIMAL" property="shipfee" />
		<result column="handlingCost" jdbcType="DECIMAL" property="handlingcost" />
		<result column="payfee" jdbcType="DECIMAL" property="payfee" />
		<result column="fullcut" jdbcType="INTEGER" property="fullcut" />
		<result column="discount" jdbcType="DECIMAL" property="discount" />
		<result column="paycreditcount" jdbcType="INTEGER" property="paycreditcount" />
		<result column="paycreditmoney" jdbcType="DECIMAL" property="paycreditmoney" />
		<result column="couponmoney" jdbcType="INTEGER" property="couponmoney" />
		<result column="weight" jdbcType="DECIMAL" property="weight" />
		<result column="buyerremark" jdbcType="VARCHAR" property="buyerremark" />
		<result column="ip" jdbcType="VARCHAR" property="ip" />
		<result column="beginDate" jdbcType="VARCHAR" property="begindate" />
		<result column="endDate" jdbcType="VARCHAR" property="enddate" />
		<result column="factoryAddress" jdbcType="VARCHAR" property="factoryaddress" />
		<result column="storeAddress" jdbcType="VARCHAR" property="storeaddress" />
		<result column="paystate" jdbcType="VARCHAR" property="paystate" />
		<result column="salerid" jdbcType="INTEGER" property="salerid" />
		<result column="needinv" jdbcType="CHAR" property="needinv" />
		<result column="userrole" jdbcType="INTEGER" property="userrole" />
		<result column="remark" jdbcType="VARCHAR" property="remark" />
		<result column="expireTime" jdbcType="DATE" property="expireTime" />
		<result column="ncordernum" jdbcType="VARCHAR" property="ncordernum" />
		<result column="ncstate" jdbcType="CHAR" property="ncstate" />		
