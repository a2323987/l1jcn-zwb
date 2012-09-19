/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50051
Source Host           : localhost:3306
Source Database       : l1jdb_taiwan

Target Server Type    : MYSQL
Target Server Version : 50051
File Encoding         : 65001

Date: 2012-06-30 20:18:08
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `william_teleport_scroll`
-- ----------------------------
DROP TABLE IF EXISTS `william_teleport_scroll`;
CREATE TABLE `william_teleport_scroll` (
  `item_id` int(10) unsigned NOT NULL auto_increment,
  `tpLocX` int(10) unsigned NOT NULL default '0',
  `tpLocY` int(10) unsigned NOT NULL default '0',
  `tpMapId` int(10) unsigned NOT NULL default '0',
  `check_minLocX` int(10) unsigned NOT NULL default '0',
  `check_minLocY` int(10) unsigned NOT NULL default '0',
  `check_maxLocX` int(10) unsigned NOT NULL default '0',
  `check_maxLocY` int(10) unsigned NOT NULL default '0',
  `check_MapId` int(10) unsigned NOT NULL default '0',
  `removeItem` int(1) unsigned NOT NULL default '0',
  PRIMARY KEY  (`item_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of william_teleport_scroll
-- ----------------------------
