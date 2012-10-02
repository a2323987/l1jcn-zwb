/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50524
Source Host           : localhost:3306
Source Database       : l1jcn

Target Server Type    : MYSQL
Target Server Version : 50524
File Encoding         : 65001

Date: 2012-10-02 12:24:33
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `william_npc_action`
-- ----------------------------
DROP TABLE IF EXISTS `william_npc_action`;
CREATE TABLE `william_npc_action` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `note` varchar(45) DEFAULT NULL,
  `npcid` int(10) NOT NULL,
  `checkLevel` int(3) unsigned NOT NULL DEFAULT '0',
  `checkClass` int(3) unsigned NOT NULL DEFAULT '0',
  `checkPoly` int(10) unsigned NOT NULL DEFAULT '0',
  `checkQuestId` int(10) unsigned NOT NULL DEFAULT '0',
  `checkQuestOrder` int(10) unsigned NOT NULL DEFAULT '0',
  `checkItem` varchar(1000) DEFAULT NULL,
  `checkItemCount` varchar(1000) DEFAULT NULL,
  `notHaveItem` varchar(1000) DEFAULT NULL,
  `notHaveItemCount` varchar(1000) DEFAULT NULL,
  `ShowHtml` varchar(1000) DEFAULT NULL,
  `ShowHtmlData` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of william_npc_action
-- ----------------------------

-- ----------------------------
-- Table structure for `william_npc_quest`
-- ----------------------------
DROP TABLE IF EXISTS `william_npc_quest`;
CREATE TABLE `william_npc_quest` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `note` varchar(45) DEFAULT NULL,
  `npcid` int(10) NOT NULL,
  `action` varchar(45) NOT NULL,
  `checkLevel` int(3) unsigned NOT NULL DEFAULT '0',
  `checkClass` int(3) unsigned NOT NULL DEFAULT '0',
  `checkPoly` int(10) unsigned NOT NULL DEFAULT '0',
  `checkHaveQuestId` int(10) unsigned NOT NULL DEFAULT '0',
  `checkHaveQuestOrder` int(10) unsigned NOT NULL DEFAULT '0',
  `notHaveQuestId` int(10) unsigned NOT NULL DEFAULT '0',
  `notHaveQuestOrder` int(10) unsigned NOT NULL DEFAULT '0',
  `checkItem` varchar(1000) DEFAULT NULL,
  `checkItemCount` varchar(1000) DEFAULT NULL,
  `notHaveItem` varchar(1000) DEFAULT NULL,
  `notHaveItemCount` varchar(1000) DEFAULT NULL,
  `material` varchar(1000) DEFAULT NULL,
  `materialCount` varchar(1000) DEFAULT NULL,
  `justCheckMaterial` int(2) unsigned NOT NULL DEFAULT '0',
  `GiveItem` varchar(1000) DEFAULT NULL,
  `GiveItemCount` varchar(1000) DEFAULT NULL,
  `saveQuestId` int(10) unsigned NOT NULL DEFAULT '0',
  `saveQuestOrder` int(10) unsigned NOT NULL DEFAULT '0',
  `ShowHtml` varchar(1000) DEFAULT NULL,
  `ShowHtmlData` varchar(1000) DEFAULT NULL,
  `ShowNotHaveHtml` varchar(1000) DEFAULT NULL,
  `ShowNotHaveHtmlData` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of william_npc_quest
-- ----------------------------

-- ----------------------------
-- Table structure for `william_quests`
-- ----------------------------
DROP TABLE IF EXISTS `william_quests`;
CREATE TABLE `william_quests` (
  `oid` int(10) NOT NULL AUTO_INCREMENT,
  `command` varchar(100) NOT NULL,
  `htmlid` varchar(100) DEFAULT NULL,
  `htmldata` varchar(1000) DEFAULT NULL,
  `materials` varchar(1000) DEFAULT NULL,
  `counts` varchar(1000) DEFAULT NULL,
  `createitem` varchar(1000) DEFAULT NULL,
  `createcount` varchar(1000) DEFAULT NULL,
  `activated_level` int(3) NOT NULL,
  `activated_timestart` varchar(8) DEFAULT NULL,
  `activated_timeend` varchar(8) DEFAULT NULL,
  `activated_type` int(2) NOT NULL,
  `islimit` int(1) NOT NULL,
  `justcheck` int(1) NOT NULL,
  `enable` int(1) NOT NULL DEFAULT '1',
  `npcid` int(10) unsigned NOT NULL,
  PRIMARY KEY (`oid`)
) ENGINE=MyISAM AUTO_INCREMENT=18954 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of william_quests
-- ----------------------------
INSERT INTO `william_quests` VALUES ('1', 'request for request_exchange_1', null, null, '40346,40354,40362,40370', '1,1,1,1', '49142', '1', '65', null, null, '0', '3', '0', '1', '70964');

-- ----------------------------
-- Table structure for `william_quests_save`
-- ----------------------------
DROP TABLE IF EXISTS `william_quests_save`;
CREATE TABLE `william_quests_save` (
  `oid` int(10) NOT NULL AUTO_INCREMENT,
  `command` varchar(100) NOT NULL,
  `userid` varchar(100) NOT NULL,
  PRIMARY KEY (`oid`)
) ENGINE=MyISAM AUTO_INCREMENT=192 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of william_quests_save
-- ----------------------------

-- ----------------------------
-- Table structure for `william_spawn_npc`
-- ----------------------------
DROP TABLE IF EXISTS `william_spawn_npc`;
CREATE TABLE `william_spawn_npc` (
  `npcid` int(10) NOT NULL,
  `type` varchar(100) NOT NULL,
  `gfxid` int(10) NOT NULL,
  `name` varchar(100) NOT NULL,
  `UseRoyal` int(10) unsigned NOT NULL DEFAULT '1',
  `UseKnight` int(10) unsigned NOT NULL DEFAULT '1',
  `UseElf` int(10) unsigned NOT NULL DEFAULT '1',
  `UseMage` int(10) unsigned NOT NULL DEFAULT '1',
  `UseDarkelf` int(10) unsigned NOT NULL DEFAULT '1',
  `UseDragonKnight` int(10) NOT NULL DEFAULT '1',
  `UseIllusionist` int(10) NOT NULL DEFAULT '1',
  `CheckItem1` int(10) unsigned NOT NULL DEFAULT '0',
  `Count1` int(10) unsigned NOT NULL DEFAULT '0',
  `CheckItem2` int(10) unsigned NOT NULL DEFAULT '0',
  `Count2` int(10) unsigned NOT NULL DEFAULT '0',
  `CheckItem3` int(10) unsigned NOT NULL DEFAULT '0',
  `Count3` int(10) unsigned NOT NULL DEFAULT '0',
  `CheckCastleId` int(10) unsigned NOT NULL DEFAULT '0',
  `class_htmlid` varchar(100) NOT NULL,
  `other_htmlid` varchar(100) NOT NULL,
  `item1_htmlid` varchar(100) NOT NULL,
  `item2_htmlid` varchar(100) NOT NULL,
  `item3_htmlid` varchar(100) NOT NULL,
  `class_htmldata` varchar(1000) NOT NULL,
  `other_htmldata` varchar(1000) NOT NULL,
  `location_x` int(10) NOT NULL,
  `location_y` int(10) NOT NULL,
  `heading` int(10) NOT NULL,
  `map` int(10) NOT NULL,
  `count` int(1) unsigned NOT NULL DEFAULT '1',
  PRIMARY KEY (`npcid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of william_spawn_npc
-- ----------------------------
INSERT INTO `william_spawn_npc` VALUES ('90001', 'L1Merchant', '1049', '任务兑换师', '1', '1', '1', '1', '1', '1', '1', '0', '0', '0', '0', '0', '0', '0', 'exchange', '', '', '', '', '卷轴兑换师:,这里可以为你兑换各种任务道具，请仔细检查身上的物品是否满足兑换的条件。,4龙之心兑换回忆蜡烛,', '', '32714', '32836', '6', '350', '1');
