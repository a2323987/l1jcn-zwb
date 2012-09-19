/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50051
Source Host           : localhost:3306
Source Database       : l1jdb_taiwan

Target Server Type    : MYSQL
Target Server Version : 50051
File Encoding         : 65001

Date: 2012-06-30 19:33:34
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `town`
-- ----------------------------
DROP TABLE IF EXISTS `town`;
CREATE TABLE `town` (
  `town_id` int(10) unsigned NOT NULL default '0',
  `name` varchar(45) NOT NULL default '',
  `leader_id` int(10) unsigned NOT NULL default '0',
  `leader_name` varchar(45) default NULL,
  `tax_rate` int(10) unsigned NOT NULL default '0',
  `tax_rate_reserved` int(10) unsigned NOT NULL default '0',
  `sales_money` int(10) unsigned NOT NULL default '0',
  `sales_money_yesterday` int(10) unsigned NOT NULL default '0',
  `town_tax` int(10) unsigned NOT NULL default '0',
  `town_fix_tax` int(10) unsigned NOT NULL default '0',
  PRIMARY KEY  (`town_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of town
-- ----------------------------
INSERT INTO `town` VALUES ('1', '说话之岛', '0', null, '0', '0', '0', '0', '0', '14112');
INSERT INTO `town` VALUES ('2', '银骑士村庄', '0', null, '0', '0', '0', '1500', '0', '1658532');
INSERT INTO `town` VALUES ('3', '古鲁丁村庄', '0', null, '0', '0', '0', '0', '0', '1104804');
INSERT INTO `town` VALUES ('4', '燃柳村庄', '0', null, '0', '0', '0', '0', '0', '266');
INSERT INTO `town` VALUES ('5', '风木村庄', '0', null, '0', '0', '0', '0', '0', '59400');
INSERT INTO `town` VALUES ('6', '肯特村庄', '0', null, '0', '0', '0', '0', '0', '296');
INSERT INTO `town` VALUES ('7', '奇岩村庄', '0', null, '0', '0', '0', '0', '0', '538414');
INSERT INTO `town` VALUES ('8', '海音村庄', '0', null, '0', '0', '0', '0', '0', '0');
INSERT INTO `town` VALUES ('9', '威顿村庄', '0', null, '0', '0', '0', '0', '0', '19620');
INSERT INTO `town` VALUES ('10', '欧瑞村庄', '0', null, '0', '0', '0', '0', '0', '38062');
