/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50051
Source Host           : localhost:3306
Source Database       : l1jdb_taiwan

Target Server Type    : MYSQL
Target Server Version : 50051
File Encoding         : 65001

Date: 2012-06-30 19:30:08
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `castle`
-- ----------------------------
DROP TABLE IF EXISTS `castle`;
CREATE TABLE `castle` (
  `castle_id` int(11) NOT NULL default '0',
  `name` varchar(45) NOT NULL default '',
  `war_time` datetime default NULL,
  `tax_rate` int(11) NOT NULL default '0',
  `public_money` int(11) NOT NULL default '0',
  PRIMARY KEY  (`castle_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of castle
-- ----------------------------
INSERT INTO `castle` VALUES ('1', '肯特城', '2009-10-06 19:41:01', '10', '4973002');
INSERT INTO `castle` VALUES ('2', '妖魔城', '2009-10-06 19:41:01', '10', '1205');
INSERT INTO `castle` VALUES ('3', '风木城', '2009-10-06 19:41:01', '10', '7790772');
INSERT INTO `castle` VALUES ('4', '奇岩城', '2009-10-06 19:41:01', '10', '2858838');
INSERT INTO `castle` VALUES ('5', '海音城', '2009-10-06 19:41:01', '10', '0');
INSERT INTO `castle` VALUES ('6', '侏儒城', '2009-10-06 19:41:01', '10', '259622');
INSERT INTO `castle` VALUES ('7', '亚丁城', '2009-10-06 19:41:01', '10', '1765500');
INSERT INTO `castle` VALUES ('8', '狄亚得要塞', '2009-10-06 19:41:01', '10', '53117156');
