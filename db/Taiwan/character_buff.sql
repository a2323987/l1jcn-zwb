/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50051
Source Host           : localhost:3306
Source Database       : l1jdb_taiwan

Target Server Type    : MYSQL
Target Server Version : 50051
File Encoding         : 65001

Date: 2012-06-30 19:30:16
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `character_buff`
-- ----------------------------
DROP TABLE IF EXISTS `character_buff`;
CREATE TABLE `character_buff` (
  `char_obj_id` int(10) NOT NULL default '0',
  `skill_id` int(10) unsigned NOT NULL default '0',
  `remaining_time` int(10) NOT NULL default '0',
  `poly_id` int(10) default '0',
  PRIMARY KEY  (`char_obj_id`,`skill_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of character_buff
-- ----------------------------
