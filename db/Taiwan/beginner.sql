/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50051
Source Host           : localhost:3306
Source Database       : l1jdb_taiwan

Target Server Type    : MYSQL
Target Server Version : 50051
File Encoding         : 65001

Date: 2012-06-30 19:29:57
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `beginner`
-- ----------------------------
DROP TABLE IF EXISTS `beginner`;
CREATE TABLE `beginner` (
  `id` int(10) NOT NULL auto_increment,
  `item_id` int(6) NOT NULL default '0',
  `count` int(10) NOT NULL default '0',
  `charge_count` int(10) NOT NULL default '0',
  `enchantlvl` int(6) NOT NULL default '0',
  `item_name` varchar(50) NOT NULL default '',
  `activate` char(1) NOT NULL default 'A',
  `bless` int(11) unsigned NOT NULL default '1',
  `DeleteDay` int(10) NOT NULL default '0',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of beginner
-- ----------------------------
INSERT INTO `beginner` VALUES ('1', '40005', '1', '0', '0', '蜡烛', 'A', '1', '0');
INSERT INTO `beginner` VALUES ('2', '40005', '1', '0', '0', '蜡烛', 'A', '1', '0');
INSERT INTO `beginner` VALUES ('3', '40641', '1', '0', '0', '说话卷轴', 'A', '1', '0');
INSERT INTO `beginner` VALUES ('4', '35', '1', '0', '0', '象牙塔单手剑', 'K', '1', '0');
INSERT INTO `beginner` VALUES ('5', '48', '1', '0', '0', '象牙塔双手剑', 'K', '1', '0');
INSERT INTO `beginner` VALUES ('6', '147', '1', '0', '0', '象牙塔斧头', 'K', '1', '0');
INSERT INTO `beginner` VALUES ('7', '105', '1', '0', '0', '象牙塔长矛', 'K', '1', '0');
INSERT INTO `beginner` VALUES ('8', '174', '1', '0', '0', '象牙塔石弓', 'K', '1', '0');
INSERT INTO `beginner` VALUES ('9', '7', '1', '0', '0', '象牙塔短剑', 'K', '1', '0');
INSERT INTO `beginner` VALUES ('10', '49309', '1', '0', '0', '象牙塔箭筒', 'K', '1', '0');
INSERT INTO `beginner` VALUES ('11', '35', '1', '0', '0', '象牙塔单手剑', 'P', '1', '0');
INSERT INTO `beginner` VALUES ('12', '48', '1', '0', '0', '象牙塔双手剑', 'P', '1', '0');
INSERT INTO `beginner` VALUES ('13', '147', '1', '0', '0', '象牙塔斧头', 'P', '1', '0');
INSERT INTO `beginner` VALUES ('14', '7', '1', '0', '0', '象牙塔短剑', 'P', '1', '0');
INSERT INTO `beginner` VALUES ('15', '35', '1', '0', '0', '象牙塔单手剑', 'E', '1', '0');
INSERT INTO `beginner` VALUES ('16', '175', '1', '0', '0', '象牙塔长弓', 'E', '1', '0');
INSERT INTO `beginner` VALUES ('17', '174', '1', '0', '0', '象牙塔石弓', 'E', '1', '0');
INSERT INTO `beginner` VALUES ('18', '7', '1', '0', '0', '象牙塔短剑', 'E', '1', '0');
INSERT INTO `beginner` VALUES ('19', '49309', '1', '0', '0', '象牙塔箭筒', 'E', '1', '0');
INSERT INTO `beginner` VALUES ('20', '35', '1', '0', '0', '象牙塔单手剑', 'W', '1', '0');
INSERT INTO `beginner` VALUES ('21', '224', '1', '0', '0', '象牙塔魔杖', 'W', '1', '0');
INSERT INTO `beginner` VALUES ('22', '7', '1', '0', '0', '象牙塔短剑', 'W', '1', '0');
INSERT INTO `beginner` VALUES ('23', '35', '1', '0', '0', '象牙塔单手剑', 'D', '1', '0');
INSERT INTO `beginner` VALUES ('24', '174', '1', '0', '0', '象牙塔石弓', 'D', '1', '0');
INSERT INTO `beginner` VALUES ('25', '73', '1', '0', '0', '象牙塔双刀', 'D', '1', '0');
INSERT INTO `beginner` VALUES ('26', '156', '1', '0', '0', '象牙塔钢爪', 'D', '1', '0');
INSERT INTO `beginner` VALUES ('27', '7', '1', '0', '0', '象牙塔短剑', 'D', '1', '0');
INSERT INTO `beginner` VALUES ('28', '49309', '1', '0', '0', '象牙塔箭筒', 'D', '1', '0');
INSERT INTO `beginner` VALUES ('29', '35', '1', '0', '0', '象牙塔单手剑', 'R', '1', '0');
INSERT INTO `beginner` VALUES ('30', '48', '1', '0', '0', '象牙塔双手剑', 'R', '1', '0');
INSERT INTO `beginner` VALUES ('31', '147', '1', '0', '0', '象牙塔斧头', 'R', '1', '0');
INSERT INTO `beginner` VALUES ('32', '147', '1', '0', '0', '象牙塔斧头', 'I', '1', '0');
INSERT INTO `beginner` VALUES ('33', '174', '1', '0', '0', '象牙塔石弓', 'I', '1', '0');
INSERT INTO `beginner` VALUES ('34', '224', '1', '0', '0', '象牙塔魔杖', 'I', '1', '0');
INSERT INTO `beginner` VALUES ('35', '49309', '1', '0', '0', '象牙塔箭筒', 'I', '1', '0');
