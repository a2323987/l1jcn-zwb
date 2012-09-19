/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50051
Source Host           : localhost:3306
Source Database       : l1jdb_taiwan

Target Server Type    : MYSQL
Target Server Version : 50051
File Encoding         : 65001

Date: 2012-06-30 19:33:14
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `spawnlist_furniture`
-- ----------------------------
DROP TABLE IF EXISTS `spawnlist_furniture`;
CREATE TABLE `spawnlist_furniture` (
  `item_obj_id` int(10) unsigned NOT NULL default '0',
  `npcid` int(10) unsigned NOT NULL default '0',
  `locx` int(10) NOT NULL default '0',
  `locy` int(10) NOT NULL default '0',
  `mapid` int(10) NOT NULL default '0',
  PRIMARY KEY  (`item_obj_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of spawnlist_furniture
-- ----------------------------
