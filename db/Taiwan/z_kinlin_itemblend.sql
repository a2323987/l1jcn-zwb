/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50051
Source Host           : localhost:3306
Source Database       : l1jdb_taiwan

Target Server Type    : MYSQL
Target Server Version : 50051
File Encoding         : 65001

Date: 2012-06-30 20:18:13
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `z_kinlin_itemblend`
-- ----------------------------
DROP TABLE IF EXISTS `z_kinlin_itemblend`;
CREATE TABLE `z_kinlin_itemblend` (
  `item_id` int(10) NOT NULL,
  `name` varchar(45) NOT NULL,
  `checkClass` int(1) NOT NULL default '0',
  `checkLevel` int(2) NOT NULL default '0',
  `rnd` int(10) NOT NULL default '100',
  `checkItem` int(10) NOT NULL,
  `hpConsume` int(10) unsigned NOT NULL default '0',
  `mpConsume` int(10) unsigned NOT NULL default '0',
  `material` int(10) NOT NULL,
  `material_count` int(10) NOT NULL,
  `material_2` int(10) NOT NULL,
  `material_2_count` int(10) NOT NULL,
  `material_3` int(10) NOT NULL,
  `material_3_count` int(10) NOT NULL,
  `new_item` int(10) NOT NULL,
  `new_item_counts` int(10) NOT NULL,
  `new_Enchantlvl_SW` int(1) NOT NULL,
  `new_item_Enchantlvl` int(10) NOT NULL default '0',
  `removeItem` int(1) NOT NULL default '1',
  `message` varchar(1000) default NULL,
  `item_Html` int(1) NOT NULL,
  PRIMARY KEY  (`item_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of z_kinlin_itemblend
-- ----------------------------
