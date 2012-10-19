/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50524
Source Host           : localhost:3306
Source Database       : l1jdb

Target Server Type    : MYSQL
Target Server Version : 50524
File Encoding         : 65001

Date: 2012-10-19 07:43:47
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `william_item_summon`
-- ----------------------------
DROP TABLE IF EXISTS `william_item_summon`;
CREATE TABLE `william_item_summon` (
  `note` varchar(45) DEFAULT NULL,
  `item_id` int(10) unsigned NOT NULL DEFAULT '0',
  `checkLevel` int(3) unsigned NOT NULL DEFAULT '0',
  `checkClass` int(2) unsigned NOT NULL DEFAULT '0',
  `checkItem` int(10) unsigned NOT NULL DEFAULT '0',
  `hpConsume` int(10) unsigned NOT NULL DEFAULT '0',
  `mpConsume` int(10) unsigned NOT NULL DEFAULT '0',
  `material` int(10) unsigned NOT NULL DEFAULT '0',
  `material_count` int(10) unsigned NOT NULL DEFAULT '0',
  `summon_id` int(10) unsigned NOT NULL DEFAULT '0',
  `summonCost` int(10) unsigned NOT NULL DEFAULT '0',
  `onlyOne` int(1) unsigned NOT NULL DEFAULT '0',
  `removeItem` int(1) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of william_item_summon
-- ----------------------------
INSERT INTO `william_item_summon` VALUES ('风之书(黑长者)', '70335', '0', '0', '70334', '100', '100', '40318', '50', '45545', '30', '1', '1');
INSERT INTO `william_item_summon` VALUES ('地之书(巴风特)', '70336', '0', '0', '70334', '100', '100', '40318', '50', '45573', '30', '1', '1');
INSERT INTO `william_item_summon` VALUES ('水之书(冰魔)', '70337', '0', '0', '70334', '100', '100', '40318', '50', '46142', '30', '1', '1');
INSERT INTO `william_item_summon` VALUES ('火之书(巴列斯)', '70338', '0', '0', '70334', '100', '100', '40318', '50', '45583', '30', '1', '1');
