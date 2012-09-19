/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50051
Source Host           : localhost:3306
Source Database       : l1jdb_yorick

Target Server Type    : MYSQL
Target Server Version : 50051
File Encoding         : 65001

Date: 2012-07-01 21:32:33
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `william_system_message`
-- ----------------------------
DROP TABLE IF EXISTS `william_system_message`;
CREATE TABLE `william_system_message` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `message` varchar(1000) NOT NULL,
  `note` varchar(45) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=112 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of william_system_message
-- ----------------------------
INSERT INTO `william_system_message` VALUES ('100', '\\f:[声望LV1]', '声望LV1');
INSERT INTO `william_system_message` VALUES ('101', '\\fU[声望LV2]', '声望LV2');
INSERT INTO `william_system_message` VALUES ('102', '\\fA[声望LV3]', '声望LV3');
INSERT INTO `william_system_message` VALUES ('103', '\\fI[声望LV4]', '声望LV4');
INSERT INTO `william_system_message` VALUES ('104', '\\fN[声望LV5]', '声望LV5');
INSERT INTO `william_system_message` VALUES ('105', '\\fB[声望LV6]', '声望LV6');
INSERT INTO `william_system_message` VALUES ('106', '\\fY[声望LV7]', '声望LV7');
INSERT INTO `william_system_message` VALUES ('107', '\\f9[声望LV8]', '声望LV8');
INSERT INTO `william_system_message` VALUES ('108', '\\fW[声望LV9]', '声望LV9');
INSERT INTO `william_system_message` VALUES ('109', '\\f7[声望LV10]', '声望LV10');
INSERT INTO `william_system_message` VALUES ('110', '\\f;[声望LV11]', '声望LV11');
INSERT INTO `william_system_message` VALUES ('111', '\\fH[声望LV12]', '声望LV12');
INSERT INTO `william_system_message` VALUES ('112', '您的声望值增加1点', '声望道具');
INSERT INTO `william_system_message` VALUES ('113', '您的声望值增加5点', '声望道具');
INSERT INTO `william_system_message` VALUES ('114', '您的声望值增加10点', '声望道具');
INSERT INTO `william_system_message` VALUES ('115', '您的声望值已到了极限', '声望道具');
INSERT INTO `william_system_message` VALUES ('116', '您不适合使用这种声望道具', '声望道具');
