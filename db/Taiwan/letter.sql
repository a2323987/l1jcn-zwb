/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50051
Source Host           : localhost:3306
Source Database       : l1jdb_taiwan

Target Server Type    : MYSQL
Target Server Version : 50051
File Encoding         : 65001

Date: 2012-06-30 19:31:55
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `letter`
-- ----------------------------
DROP TABLE IF EXISTS `letter`;
CREATE TABLE `letter` (
  `item_object_id` int(10) unsigned NOT NULL default '0',
  `code` int(10) unsigned NOT NULL default '0',
  `sender` varchar(16) default NULL,
  `receiver` varchar(16) default NULL,
  `date` varchar(16) default NULL,
  `template_id` int(5) unsigned NOT NULL default '0',
  `subject` blob,
  `content` blob,
  PRIMARY KEY  (`item_object_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of letter
-- ----------------------------
