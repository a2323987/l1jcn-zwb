/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50051
Source Host           : localhost:3306
Source Database       : l1jdb_taiwan

Target Server Type    : MYSQL
Target Server Version : 50051
File Encoding         : 65001

Date: 2012-06-30 19:33:42
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `ub_settings`
-- ----------------------------
DROP TABLE IF EXISTS `ub_settings`;
CREATE TABLE `ub_settings` (
  `ub_id` int(10) unsigned NOT NULL default '0',
  `ub_name` varchar(45) NOT NULL default '',
  `ub_mapid` int(10) unsigned NOT NULL default '0',
  `ub_area_x1` int(10) unsigned NOT NULL default '0',
  `ub_area_y1` int(10) unsigned NOT NULL default '0',
  `ub_area_x2` int(10) unsigned NOT NULL default '0',
  `ub_area_y2` int(10) unsigned NOT NULL default '0',
  `min_lvl` int(10) unsigned NOT NULL default '0',
  `max_lvl` int(10) unsigned NOT NULL default '0',
  `max_player` int(10) unsigned NOT NULL default '0',
  `enter_royal` tinyint(3) unsigned NOT NULL default '0',
  `enter_knight` tinyint(3) unsigned NOT NULL default '0',
  `enter_mage` tinyint(3) unsigned NOT NULL default '0',
  `enter_elf` tinyint(3) unsigned NOT NULL default '0',
  `enter_darkelf` tinyint(3) unsigned NOT NULL default '0',
  `enter_dragonknight` tinyint(3) unsigned NOT NULL default '0',
  `enter_illusionist` tinyint(3) unsigned NOT NULL default '0',
  `enter_male` tinyint(3) unsigned NOT NULL default '0',
  `enter_female` tinyint(3) unsigned NOT NULL default '0',
  `use_pot` tinyint(3) unsigned NOT NULL default '0',
  `hpr_bonus` int(10) NOT NULL default '0',
  `mpr_bonus` int(10) NOT NULL default '0',
  PRIMARY KEY  (`ub_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ub_settings
-- ----------------------------
INSERT INTO `ub_settings` VALUES ('1', '奇岩竞技场', '88', '33494', '32724', '33516', '32746', '52', '99', '20', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '0');
INSERT INTO `ub_settings` VALUES ('2', '威顿竞技场', '98', '32682', '32878', '32717', '32913', '45', '60', '20', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '0');
INSERT INTO `ub_settings` VALUES ('3', '古鲁丁竞技场', '92', '32682', '32878', '32717', '32913', '31', '51', '20', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '0');
INSERT INTO `ub_settings` VALUES ('4', '说话之岛竞技场', '91', '32682', '32878', '32717', '32913', '25', '44', '20', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '0');
INSERT INTO `ub_settings` VALUES ('5', '银骑士竞技场', '95', '32682', '32878', '32717', '32913', '1', '30', '20', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '0');
