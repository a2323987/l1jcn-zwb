/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50051
Source Host           : localhost:3306
Source Database       : l1jdb_taiwan

Target Server Type    : MYSQL
Target Server Version : 50051
File Encoding         : 65001

Date: 2012-06-30 19:31:52
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `inn_key`
-- ----------------------------
DROP TABLE IF EXISTS `inn_key`;
CREATE TABLE `inn_key` (
  `item_obj_id` int(11) NOT NULL,
  `key_id` int(11) NOT NULL,
  `npc_id` int(10) default NULL,
  `hall` tinyint(2) default NULL,
  `due_time` datetime default NULL,
  PRIMARY KEY  (`item_obj_id`,`key_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of inn_key
-- ----------------------------
