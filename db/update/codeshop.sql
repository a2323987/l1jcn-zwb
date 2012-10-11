/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50524
Source Host           : localhost:3306
Source Database       : l1jcn

Target Server Type    : MYSQL
Target Server Version : 50524
File Encoding         : 65001

Date: 2012-10-11 20:02:59
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `codeshop`
-- ----------------------------
DROP TABLE IF EXISTS `codeshop`;
CREATE TABLE `codeshop` (
  `code` varchar(32) NOT NULL DEFAULT '0',
  `item_id` int(11) NOT NULL,
  `item_name` varchar(255) DEFAULT NULL,
  `count` int(11) NOT NULL DEFAULT '1',
  `enchantlvl` int(11) NOT NULL DEFAULT '0',
  `attr_enchant_kind` int(11) NOT NULL DEFAULT '0',
  `attr_enchant_level` int(11) NOT NULL DEFAULT '0',
  `is_used` int(2) NOT NULL DEFAULT '0',
  `use_character` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`code`),
  KEY `key_id` (`code`) USING BTREE
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of codeshop
-- ----------------------------
INSERT INTO `codeshop` VALUES ('a123', '306', '隐藏的魔族魔杖', '2', '9', '2', '5', '0', '');
