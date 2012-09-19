/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50051
Source Host           : localhost:3306
Source Database       : l1jdb_taiwan

Target Server Type    : MYSQL
Target Server Version : 50051
File Encoding         : 65001

Date: 2012-06-30 19:32:53
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `race_ticket`
-- ----------------------------
DROP TABLE IF EXISTS `race_ticket`;
CREATE TABLE `race_ticket` (
  `item_obj_id` int(11) NOT NULL,
  `round` int(7) NOT NULL,
  `allotment_percentage` double NOT NULL,
  `victory` int(1) NOT NULL,
  `runner_num` int(2) NOT NULL,
  PRIMARY KEY  (`item_obj_id`,`round`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of race_ticket
-- ----------------------------
