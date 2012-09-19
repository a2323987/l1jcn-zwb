/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50051
Source Host           : localhost:3306
Source Database       : l1jdb_taiwan

Target Server Type    : MYSQL
Target Server Version : 50051
File Encoding         : 65001

Date: 2012-06-30 19:32:24
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `npcchat`
-- ----------------------------
DROP TABLE IF EXISTS `npcchat`;
CREATE TABLE `npcchat` (
  `npc_id` int(10) unsigned NOT NULL default '0',
  `chat_timing` tinyint(1) unsigned NOT NULL default '0',
  `note` varchar(45) NOT NULL default '',
  `start_delay_time` int(10) NOT NULL default '0',
  `chat_id1` varchar(45) NOT NULL default '',
  `chat_id2` varchar(45) NOT NULL default '',
  `chat_id3` varchar(45) NOT NULL default '',
  `chat_id4` varchar(45) NOT NULL default '',
  `chat_id5` varchar(45) NOT NULL default '',
  `chat_interval` int(10) unsigned NOT NULL default '0',
  `is_shout` tinyint(1) unsigned NOT NULL default '0',
  `is_world_chat` tinyint(1) unsigned NOT NULL default '0',
  `is_repeat` tinyint(1) unsigned NOT NULL default '0',
  `repeat_interval` int(10) unsigned NOT NULL default '0',
  `game_time` int(10) unsigned NOT NULL default '0',
  PRIMARY KEY  (`npc_id`,`chat_timing`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of npcchat
-- ----------------------------
INSERT INTO `npcchat` VALUES ('45264', '2', '哈维(空降时)', '0', '$994', '', '', '', '', '0', '0', '0', '0', '0', '0');
INSERT INTO `npcchat` VALUES ('45458', '1', '德雷克(死亡时)', '0', '$3603', '', '', '', '', '0', '0', '0', '0', '0', '0');
INSERT INTO `npcchat` VALUES ('45464', '0', '西玛(出现时)', '15000', '$342', '', '', '', '', '0', '1', '0', '1', '20000', '0');
INSERT INTO `npcchat` VALUES ('45473', '0', '巴土瑟(出现时)', '0', '$339', '', '', '', '', '0', '1', '0', '1', '20000', '0');
INSERT INTO `npcchat` VALUES ('45488', '0', '卡士伯(出现时)', '5000', '$340', '', '', '', '', '0', '1', '0', '1', '20000', '0');
INSERT INTO `npcchat` VALUES ('45492', '1', '库曼(死亡时)', '0', '$3943', '', '', '', '', '0', '0', '0', '0', '0', '0');
INSERT INTO `npcchat` VALUES ('45497', '0', '马库尔(出现时)', '10000', '$341', '', '', '', '', '0', '1', '0', '1', '20000', '0');
INSERT INTO `npcchat` VALUES ('45545', '0', '黑长者(出现时)', '0', '$993', '', '', '', '', '0', '1', '0', '1', '10000', '0');
INSERT INTO `npcchat` VALUES ('45545', '1', '黑长者(死亡时)', '0', '$995', '', '', '', '', '0', '0', '0', '0', '0', '0');
INSERT INTO `npcchat` VALUES ('45548', '1', '豪势(死亡时)', '0', '$4030', '', '', '', '', '0', '0', '0', '0', '0', '0');
INSERT INTO `npcchat` VALUES ('45573', '0', '巴风特(出现时)', '0', '$825', '', '', '', '', '0', '1', '0', '0', '0', '0');
INSERT INTO `npcchat` VALUES ('45574', '1', '亲卫队长．凯特(死亡时)', '0', '$3938', '', '', '', '', '0', '0', '0', '0', '0', '0');
INSERT INTO `npcchat` VALUES ('45577', '1', '突击旅长．闇黑剑士(死亡时)', '0', '$3892', '', '', '', '', '0', '0', '0', '0', '0', '0');
INSERT INTO `npcchat` VALUES ('45585', '1', '暗杀团长．布雷哲(死亡时)', '0', '$3939', '', '', '', '', '0', '0', '0', '0', '0', '0');
INSERT INTO `npcchat` VALUES ('45588', '1', '魔兽师长．辛克莱(死亡时)', '0', '$3901', '', '', '', '', '0', '0', '0', '0', '0', '0');
INSERT INTO `npcchat` VALUES ('45600', '0', '克特(出现时)', '0', '$275', '$279', '$281', '$285', '$287', '5000', '0', '0', '0', '0', '0');
INSERT INTO `npcchat` VALUES ('45600', '1', '克特(死亡时)', '0', '$302', '', '', '', '', '0', '0', '0', '0', '0', '0');
INSERT INTO `npcchat` VALUES ('45602', '1', '魔法团长．卡勒米尔(死亡时)', '0', '$3903', '', '', '', '', '0', '0', '0', '0', '0', '0');
INSERT INTO `npcchat` VALUES ('45607', '1', '魔兽团长．凯巴勒(死亡时)', '0', '$3900', '', '', '', '', '0', '0', '0', '0', '0', '0');
INSERT INTO `npcchat` VALUES ('45608', '1', '佣兵队长．麦帕斯托(死亡时)', '0', '$3930', '', '', '', '', '0', '0', '0', '0', '0', '0');
INSERT INTO `npcchat` VALUES ('45612', '1', '神官长．邦妮(死亡时)', '0', '$3912', '', '', '', '', '0', '0', '0', '0', '0', '0');
INSERT INTO `npcchat` VALUES ('45615', '1', '冥法团长．可利波斯(死亡时)', '0', '$3917', '', '', '', '', '0', '0', '0', '0', '0', '0');
INSERT INTO `npcchat` VALUES ('45648', '1', '暗杀军王．史雷佛(死亡时)', '0', '$3940', '', '', '', '', '0', '0', '0', '0', '0', '0');
INSERT INTO `npcchat` VALUES ('45676', '1', '冥法军王．海露拜(死亡时)', '0', '$3923', '', '', '', '', '0', '0', '0', '0', '0', '0');
INSERT INTO `npcchat` VALUES ('45844', '1', '魔兽军王．巴兰卡(死亡时)', '0', '$3895', '', '', '', '', '0', '0', '0', '0', '0', '0');
INSERT INTO `npcchat` VALUES ('45863', '1', '法令军王．蕾雅(死亡时)', '0', '$3908', '', '', '', '', '0', '0', '0', '0', '0', '0');
INSERT INTO `npcchat` VALUES ('45931', '1', '水之精灵(死亡时)', '0', '$5167', '', '', '', '', '0', '0', '0', '0', '0', '0');
INSERT INTO `npcchat` VALUES ('45935', '0', '受诅咒的 梅杜莎(出现时)', '0', '$5169', '', '', '', '', '0', '0', '0', '0', '10000', '0');
INSERT INTO `npcchat` VALUES ('45941', '1', '受诅咒的巫女莎尔(死亡时)', '0', '$5166', '', '', '', '', '0', '1', '0', '0', '0', '0');
INSERT INTO `npcchat` VALUES ('45942', '0', '受诅咒的 水精灵王(出现时)', '0', '$5170', '', '', '', '', '0', '1', '0', '0', '10000', '0');
INSERT INTO `npcchat` VALUES ('45943', '1', '卡普(死亡时)', '0', '$5165', '', '', '', '', '0', '0', '0', '0', '0', '0');
INSERT INTO `npcchat` VALUES ('45955', '1', '长老．琪娜(死亡时)', '0', '$4625', '', '', '', '', '0', '0', '0', '0', '0', '0');
INSERT INTO `npcchat` VALUES ('45959', '1', '长老．艾迪尔(死亡时)', '0', '$4626', '', '', '', '', '0', '0', '0', '0', '0', '0');
INSERT INTO `npcchat` VALUES ('45963', '1', '副神官．卡山德拉(死亡时)', '0', '$3888', '', '', '', '', '0', '0', '0', '0', '0', '0');
INSERT INTO `npcchat` VALUES ('46083', '0', '巡逻兵 2(出现时)', '0', '$5016', '', '', '', '', '0', '1', '0', '0', '0', '0');
INSERT INTO `npcchat` VALUES ('46098', '0', '巡逻兵 2(出现时)', '0', '$5014', '', '', '', '', '0', '1', '0', '0', '0', '0');
INSERT INTO `npcchat` VALUES ('46157', '1', '妖魔密使(死亡时)', '0', '$6127', '', '', '', '', '0', '0', '0', '0', '0', '0');
INSERT INTO `npcchat` VALUES ('46158', '1', '妖魔密使(死亡时)', '0', '$6127', '', '', '', '', '0', '0', '0', '0', '0', '0');
INSERT INTO `npcchat` VALUES ('46159', '1', '妖魔密使(死亡时)', '0', '$6127', '', '', '', '', '0', '0', '0', '0', '0', '0');
INSERT INTO `npcchat` VALUES ('81175', '0', '通缉犯库杰(出现时)', '0', '$5325', '', '', '', '', '0', '1', '0', '1', '15000', '0');
INSERT INTO `npcchat` VALUES ('81175', '1', '通缉犯库杰(死亡时)', '0', '$5327', '', '', '', '', '0', '0', '0', '0', '0', '0');
INSERT INTO `npcchat` VALUES ('81307', '0', '派报少年(出现时)', '10000', '$8917', '$8918', '', '', '', '3000', '0', '0', '1', '10000', '0');
INSERT INTO `npcchat` VALUES ('81331', '0', '奇诺之监视者(出现时)', '0', '$5765', '', '', '', '', '0', '1', '0', '1', '15000', '0');
INSERT INTO `npcchat` VALUES ('81332', '0', '被遗弃的肉身(出现时)', '0', '$5766', '', '', '', '', '0', '1', '0', '1', '15000', '0');
INSERT INTO `npcchat` VALUES ('81333', '0', '被遗弃的肉身(出现时)', '0', '$5768', '', '', '', '', '0', '1', '0', '1', '15000', '0');
