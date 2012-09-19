/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50051
Source Host           : localhost:3306
Source Database       : l1jdb_taiwan

Target Server Type    : MYSQL
Target Server Version : 50051
File Encoding         : 65001

Date: 2012-06-30 19:30:28
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `character_items`
-- ----------------------------
DROP TABLE IF EXISTS `character_items`;
CREATE TABLE `character_items` (
  `id` int(11) NOT NULL default '0',
  `item_id` int(11) default NULL,
  `char_id` int(11) default NULL,
  `item_name` varchar(255) default NULL,
  `count` int(11) default NULL,
  `is_equipped` int(11) default NULL,
  `enchantlvl` int(11) default NULL,
  `is_id` int(11) default NULL,
  `durability` int(11) default NULL,
  `charge_count` int(11) default NULL,
  `remaining_time` int(11) default NULL,
  `last_used` datetime default NULL,
  `bless` int(11) default NULL,
  `attr_enchant_kind` int(11) default NULL,
  `attr_enchant_level` int(11) default NULL,
  `firemr` int(11) default NULL,
  `watermr` int(11) default NULL,
  `earthmr` int(11) default NULL,
  `windmr` int(11) default NULL,
  `addsp` int(11) default NULL,
  `addhp` int(11) default NULL,
  `addmp` int(11) default NULL,
  `hpr` int(11) default NULL,
  `mpr` int(11) default NULL,
  `m_def` int(11) default NULL,
  `DeleteDate` datetime default NULL,
  PRIMARY KEY  (`id`),
  KEY `key_id` (`char_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of character_items
-- ----------------------------
