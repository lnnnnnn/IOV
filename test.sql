/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50711
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50711
File Encoding         : 65001

Date: 2016-06-06 11:09:40
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for carnet
-- ----------------------------
DROP TABLE IF EXISTS `carnet`;
CREATE TABLE `carnet` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sid` varchar(255) DEFAULT NULL,
  `brand` varchar(255) DEFAULT NULL,
  `symbol` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `hphm` varchar(255) DEFAULT NULL,
  `classno` varchar(255) DEFAULT NULL,
  `engineno` varchar(255) DEFAULT NULL,
  `level` varchar(255) DEFAULT NULL,
  `mileage` double DEFAULT NULL,
  `gas_percent` int DEFAULT NULL,
  `engineP` varchar(255) DEFAULT NULL,
  `transmissionP` varchar(255) DEFAULT NULL,
  `lightS` varchar(255) DEFAULT NULL,
  `username` varchar(255)  DEFAULT NULL,
  PRIMARY KEY (`id`)
); 
/*ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;*/

-- ----------------------------
-- Records of carnet
-- ----------------------------
INSERT INTO `carnet` VALUES ('1', '001', '奥迪', '', 'A4L(中型车)', '浙CEV583', '140101596', '64090329', '4座', '12', 50, '好', '好', '好','zzz');
INSERT INTO `carnet` VALUES ('2', '002', '宝马', '', '3系(中型车)', '浙CEF155', '6072005235', '02033933', null, null, null, null, null, null,'zzj');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ;
/*ENGINE=MyISAM  AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;*/

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'zzz', '123456','757165407@qq.com');
INSERT INTO `user` VALUES ('2', 'zzj', '123456','757165407@qq.com');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `oilorder`;
CREATE TABLE `oilorder` (
  `finished` bool DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `carDesc` varchar(255) DEFAULT NULL,
  `date` datetime DEFAULT NULL,

   `gasStation` varchar(255) DEFAULT NULL,
`type` varchar(255) DEFAULT NULL,
				
`volume` varchar(255) DEFAULT NULL,
`money` double DEFAULT NULL,
`barcodeSrc` varchar(255) DEFAULT NULL,
`username` varchar(255) DEFAULT NULL,
  
  PRIMARY KEY (`id`)
) ;
/*ENGINE=MyISAM  AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;*/

-- ----------------------------
-- Records of oilorder
-- ----------------------------

