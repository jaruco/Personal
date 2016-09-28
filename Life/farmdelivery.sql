/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50525
Source Host           : localhost:3306
Source Database       : farmdelivery

Target Server Type    : MYSQL
Target Server Version : 50525
File Encoding         : 65001

Date: 2016-09-27 20:01:14
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `associate`
-- ----------------------------
DROP TABLE IF EXISTS `associate`;
CREATE TABLE `associate` (
  `idAssociate` int(11) NOT NULL,
  `idDocument` tinyint(4) NOT NULL,
  `document` varchar(20) NOT NULL,
  `companyName` varchar(30) NOT NULL,
  `addresss` varchar(30) NOT NULL,
  `telephone` varchar(20) NOT NULL,
  PRIMARY KEY (`idAssociate`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of associate
-- ----------------------------
INSERT INTO `associate` VALUES ('1', '1', '10727999855', 'Ruiz Farm', 'Lima 15', '949474503');

-- ----------------------------
-- Table structure for `item`
-- ----------------------------
DROP TABLE IF EXISTS `item`;
CREATE TABLE `item` (
  `idItem` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(20) NOT NULL,
  `name` varchar(30) NOT NULL,
  `price` decimal(8,2) NOT NULL,
  `description` varchar(30) DEFAULT NULL,
  `idAssociate` int(11) NOT NULL,
  PRIMARY KEY (`idItem`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of item
-- ----------------------------
INSERT INTO `item` VALUES ('1', 'shampoo', 'Shampoo H&S', '19.90', 'Anticaspa', '1');
INSERT INTO `item` VALUES ('2', '', '', '0.00', null, '0');

-- ----------------------------
-- Table structure for `list`
-- ----------------------------
DROP TABLE IF EXISTS `list`;
CREATE TABLE `list` (
  `idList` int(11) NOT NULL DEFAULT '0',
  `idItem` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  PRIMARY KEY (`idList`,`idItem`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of list
-- ----------------------------
INSERT INTO `list` VALUES ('1', '1', '0');
INSERT INTO `list` VALUES ('1', '2', '0');
INSERT INTO `list` VALUES ('1', '3', '0');
INSERT INTO `list` VALUES ('2', '5', '0');
INSERT INTO `list` VALUES ('3', '2', '0');

-- ----------------------------
-- Table structure for `subscription`
-- ----------------------------
DROP TABLE IF EXISTS `subscription`;
CREATE TABLE `subscription` (
  `internalId` int(11) NOT NULL,
  `code` char(20) NOT NULL,
  `name` varchar(20) NOT NULL,
  `idList` int(5) NOT NULL,
  `idAssociate` int(11) NOT NULL,
  `total` decimal(8,2) NOT NULL,
  PRIMARY KEY (`internalId`),
  KEY `list` (`idList`),
  KEY `associate` (`idAssociate`),
  CONSTRAINT `associate` FOREIGN KEY (`idAssociate`) REFERENCES `associate` (`idAssociate`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `list` FOREIGN KEY (`idList`) REFERENCES `list` (`idList`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of subscription
-- ----------------------------
INSERT INTO `subscription` VALUES ('1', 'test', 'Test Subscription', '1', '1', '0.00');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `internalId` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  `lastname` varchar(50) NOT NULL,
  `position` varchar(50) NOT NULL,
  `status` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`internalId`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'jruiz.jc@gmail.com', 'jarucor', 'Jorge', 'Ruiz', 'Android Developer', '1');
INSERT INTO `user` VALUES ('2', 'celiapafi@gmail.com', 'ceroca', 'Celia', 'Palacios', 'Senior Developer', '1');
INSERT INTO `user` VALUES ('3', 'arupa@gmail.com', 'qwerty', 'Alley Benjamin', 'Ruiz Palacios', 'Project Manager', '0');
INSERT INTO `user` VALUES ('4', 'cruizp@gmail.com', '12345', 'Camila', 'Ruiz', 'job', '0');
INSERT INTO `user` VALUES ('5', 'ppf@gmail.com', 'pa', 'pablo', 'Palacios', 'markeing', '0');
