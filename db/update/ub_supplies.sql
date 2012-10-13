/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50524
Source Host           : localhost:3306
Source Database       : l1jcn

Target Server Type    : MYSQL
Target Server Version : 50524
File Encoding         : 65001

Date: 2012-10-13 13:51:36
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `ub_supplies`
-- ----------------------------
DROP TABLE IF EXISTS `ub_supplies`;
CREATE TABLE `ub_supplies` (
  `ub_id` int(10) unsigned NOT NULL DEFAULT '0',
  `ub_name` varchar(64) DEFAULT NULL,
  `ub_round` int(10) unsigned NOT NULL DEFAULT '0',
  `ub_item_id` int(11) unsigned NOT NULL DEFAULT '0',
  `ub_item_stackcont` int(10) unsigned NOT NULL DEFAULT '1',
  `ub_item_cont` int(10) unsigned NOT NULL DEFAULT '1',
  `note` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ub_id`,`ub_round`,`ub_item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ub_supplies
-- ----------------------------
INSERT INTO `ub_supplies` VALUES ('1', '奇岩竞技场', '1', '40011', '5', '20', '强力治愈药水');
INSERT INTO `ub_supplies` VALUES ('1', '奇岩竞技场', '1', '40012', '3', '20', '终极治愈药水');
INSERT INTO `ub_supplies` VALUES ('1', '奇岩竞技场', '1', '40017', '3', '20', '解毒药水');
INSERT INTO `ub_supplies` VALUES ('1', '奇岩竞技场', '1', '40308', '30000', '20', '金币');
INSERT INTO `ub_supplies` VALUES ('1', '奇岩竞技场', '2', '40011', '10', '20', '强力治愈药水');
INSERT INTO `ub_supplies` VALUES ('1', '奇岩竞技场', '2', '40012', '5', '20', '终极治愈药水');
INSERT INTO `ub_supplies` VALUES ('1', '奇岩竞技场', '2', '40017', '5', '20', '解毒药水');
INSERT INTO `ub_supplies` VALUES ('1', '奇岩竞技场', '2', '40093', '1', '10', '空的魔法卷轴(等级4)');
INSERT INTO `ub_supplies` VALUES ('1', '奇岩竞技场', '2', '40308', '60000', '30', '金币');
INSERT INTO `ub_supplies` VALUES ('1', '奇岩竞技场', '3', '40011', '20', '20', '强力治愈药水');
INSERT INTO `ub_supplies` VALUES ('1', '奇岩竞技场', '3', '40012', '10', '20', '终极治愈药水');
INSERT INTO `ub_supplies` VALUES ('1', '奇岩竞技场', '3', '40017', '7', '20', '解毒药水');
INSERT INTO `ub_supplies` VALUES ('1', '奇岩竞技场', '3', '40308', '100000', '10', '金币');
INSERT INTO `ub_supplies` VALUES ('1', '奇岩竞技场', '3', '42094', '1', '3', '炎魔栖息地');
INSERT INTO `ub_supplies` VALUES ('1', '奇岩竞技场', '4', '60000', '1', '10', '元宝');
