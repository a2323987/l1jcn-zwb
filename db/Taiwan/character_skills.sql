/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50051
Source Host           : localhost:3306
Source Database       : l1jdb_taiwan

Target Server Type    : MYSQL
Target Server Version : 50051
File Encoding         : 65001

Date: 2012-06-30 19:30:37
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `character_skills`
-- ----------------------------
DROP TABLE IF EXISTS `character_skills`;
CREATE TABLE `character_skills` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `char_obj_id` int(10) NOT NULL default '0',
  `skill_id` int(10) unsigned NOT NULL default '0',
  `skill_name` varchar(45) NOT NULL default '',
  `is_active` int(10) default NULL,
  `activetimeleft` int(10) default NULL,
  PRIMARY KEY  (`char_obj_id`,`skill_id`),
  KEY `key_id` (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of character_skills
-- ----------------------------
