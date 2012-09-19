/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50051
Source Host           : localhost:3306
Source Database       : l1jdb_taiwan

Target Server Type    : MYSQL
Target Server Version : 50051
File Encoding         : 65001

Date: 2012-06-30 20:18:05
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `william_reward`
-- ----------------------------
DROP TABLE IF EXISTS `william_reward`;
CREATE TABLE `william_reward` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `注解` varchar(45) default NULL,
  `level` int(3) unsigned NOT NULL default '0',
  `give_royal` int(1) unsigned NOT NULL default '0',
  `give_knight` int(1) unsigned NOT NULL default '0',
  `give_mage` int(1) unsigned NOT NULL default '0',
  `give_elf` int(1) unsigned NOT NULL default '0',
  `give_darkelf` int(1) unsigned NOT NULL default '0',
  `give_dragonknight` int(1) unsigned NOT NULL default '0',
  `give_illusionist` int(1) unsigned NOT NULL default '0',
  `getItem` varchar(45) NOT NULL,
  `count` varchar(45) NOT NULL,
  `enchantlvl` varchar(45) NOT NULL,
  `quest_id` int(10) unsigned NOT NULL default '0',
  `quest_step` int(10) unsigned NOT NULL default '255',
  `message` varchar(45) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of william_reward
-- ----------------------------
