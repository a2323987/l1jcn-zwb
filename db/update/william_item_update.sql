/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50524
Source Host           : localhost:3306
Source Database       : l1jtw

Target Server Type    : MYSQL
Target Server Version : 50524
File Encoding         : 65001

Date: 2012-10-30 21:18:02
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `william_item_update`
-- ----------------------------
DROP TABLE IF EXISTS `william_item_update`;
CREATE TABLE `william_item_update` (
  `item_id` int(11) NOT NULL DEFAULT '0',
  `count` int(11) DEFAULT NULL,
  `add_dmg` int(11) DEFAULT NULL,
  `add_dmgmodifier` int(11) DEFAULT NULL,
  `add_hitmodifier` int(11) DEFAULT NULL,
  `add_str` int(11) DEFAULT NULL,
  `add_dex` int(11) DEFAULT NULL,
  `add_int` int(11) DEFAULT NULL,
  `add_con` int(11) DEFAULT '0',
  `add_cha` int(11) DEFAULT '0',
  `add_wis` int(11) DEFAULT '0',
  `add_sp` int(11) DEFAULT '0',
  `add_freeze` int(11) DEFAULT '0',
  `add_stone` int(11) DEFAULT '0',
  `add_sleep` int(11) DEFAULT '0',
  `add_blind` int(11) DEFAULT '0',
  `add_stun` int(11) DEFAULT '0',
  `add_sustain` int(11) DEFAULT '0',
  `add_mr` int(11) DEFAULT '0',
  PRIMARY KEY (`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of william_item_update
-- ----------------------------
INSERT INTO `william_item_update` VALUES ('84785', '3', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0');
INSERT INTO `william_item_update` VALUES ('85045', '6', '-1', '-1', '0', '4', '5', '3', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0');
