/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50051
Source Host           : localhost:3306
Source Database       : l1jdb_taiwan

Target Server Type    : MYSQL
Target Server Version : 50051
File Encoding         : 65001

Date: 2012-06-30 19:31:02
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `commands`
-- ----------------------------
DROP TABLE IF EXISTS `commands`;
CREATE TABLE `commands` (
  `name` varchar(255) NOT NULL,
  `access_level` int(10) NOT NULL default '200',
  `class_name` varchar(255) NOT NULL,
  PRIMARY KEY  (`name`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of commands
-- ----------------------------
INSERT INTO `commands` VALUES ('echo', '200', 'L1Echo');
INSERT INTO `commands` VALUES ('setting', '200', 'L1Status');
INSERT INTO `commands` VALUES ('summon', '150', 'L1Summon');
INSERT INTO `commands` VALUES ('cleaning', '200', 'L1DeleteGroundItem');
INSERT INTO `commands` VALUES ('addskill', '200', 'L1AddSkill');
INSERT INTO `commands` VALUES ('level', '150', 'L1Level');
INSERT INTO `commands` VALUES ('loc', '50', 'L1Loc');
INSERT INTO `commands` VALUES ('desc', '200', 'L1Describe');
INSERT INTO `commands` VALUES ('who', '100', 'L1Who');
INSERT INTO `commands` VALUES ('allbuff', '200', 'L1AllBuff');
INSERT INTO `commands` VALUES ('speed', '50', 'L1Speed');
INSERT INTO `commands` VALUES ('adena', '150', 'L1Adena');
INSERT INTO `commands` VALUES ('hpbar', '50', 'L1HpBar');
INSERT INTO `commands` VALUES ('resettrap', '200', 'L1ResetTrap');
INSERT INTO `commands` VALUES ('reloadtrap', '200', 'L1ReloadTrap');
INSERT INTO `commands` VALUES ('showtrap', '200', 'L1ShowTrap');
INSERT INTO `commands` VALUES ('castgfx', '200', 'L1CastGfx');
INSERT INTO `commands` VALUES ('gfxid', '200', 'L1GfxId');
INSERT INTO `commands` VALUES ('invgfxid', '200', 'L1InvGfxId');
INSERT INTO `commands` VALUES ('hometown', '150', 'L1HomeTown');
INSERT INTO `commands` VALUES ('gm', '150', 'L1GM');
INSERT INTO `commands` VALUES ('present', '200', 'L1Present');
INSERT INTO `commands` VALUES ('lvpresent', '200', 'L1LevelPresent');
INSERT INTO `commands` VALUES ('shutdown', '200', 'L1Shutdown');
INSERT INTO `commands` VALUES ('item', '150', 'L1CreateItem');
INSERT INTO `commands` VALUES ('itemset', '150', 'L1CreateItemSet');
INSERT INTO `commands` VALUES ('buff', '100', 'L1Buff');
INSERT INTO `commands` VALUES ('patrol', '50', 'L1Patrol');
INSERT INTO `commands` VALUES ('banip', '150', 'L1BanIp');
INSERT INTO `commands` VALUES ('chat', '50', 'L1Chat');
INSERT INTO `commands` VALUES ('chatng', '50', 'L1ChatNG');
INSERT INTO `commands` VALUES ('skick', '150', 'L1SKick');
INSERT INTO `commands` VALUES ('kick', '50', 'L1Kick');
INSERT INTO `commands` VALUES ('powerkick', '150', 'L1PowerKick');
INSERT INTO `commands` VALUES ('accbankick', '150', 'L1AccountBanKick');
INSERT INTO `commands` VALUES ('poly', '150', 'L1Poly');
INSERT INTO `commands` VALUES ('ress', '100', 'L1Ress');
INSERT INTO `commands` VALUES ('death', '200', 'L1Kill');
INSERT INTO `commands` VALUES ('gmroom', '50', 'L1GMRoom');
INSERT INTO `commands` VALUES ('topc', '50', 'L1ToPC');
INSERT INTO `commands` VALUES ('move', '50', 'L1Move');
INSERT INTO `commands` VALUES ('weather', '150', 'L1ChangeWeather');
INSERT INTO `commands` VALUES ('tospawn', '200', 'L1ToSpawn');
INSERT INTO `commands` VALUES ('f', '50', 'L1Favorite');
INSERT INTO `commands` VALUES ('recall', '100', 'L1Recall');
INSERT INTO `commands` VALUES ('visible', '50', 'L1Visible');
INSERT INTO `commands` VALUES ('partyrecall', '100', 'L1PartyRecall');
INSERT INTO `commands` VALUES ('invisible', '50', 'L1Invisible');
INSERT INTO `commands` VALUES ('spawn', '100', 'L1SpawnCmd');
INSERT INTO `commands` VALUES ('insert', '200', 'L1InsertSpawn');
INSERT INTO `commands` VALUES ('help', '50', 'L1CommandHelp');
INSERT INTO `commands` VALUES ('action', '200', 'L1Action');
INSERT INTO `commands` VALUES ('tile', '200', 'L1Tile');
INSERT INTO `commands` VALUES ('findinvis', '200', 'L1FindInvis');
INSERT INTO `commands` VALUES ('crazy', '200', 'L1Crazy');
INSERT INTO `commands` VALUES ('itemdrops', '200', 'L1ItemDrops');
INSERT INTO `commands` VALUES ('search', '200', 'L1Search');
INSERT INTO `commands` VALUES ('公告', '200', 'L1Echo');
INSERT INTO `commands` VALUES ('设定', '200', 'L1Status');
INSERT INTO `commands` VALUES ('召宠', '150', 'L1Summon');
INSERT INTO `commands` VALUES ('清除', '200', 'L1DeleteGroundItem');
INSERT INTO `commands` VALUES ('技能', '200', 'L1AddSkill');
INSERT INTO `commands` VALUES ('等级', '150', 'L1Level');
INSERT INTO `commands` VALUES ('座标', '50', 'L1Loc');
INSERT INTO `commands` VALUES ('详情', '200', 'L1Describe');
INSERT INTO `commands` VALUES ('玩家', '100', 'L1Who');
INSERT INTO `commands` VALUES ('全辅助', '200', 'L1AllBuff');
INSERT INTO `commands` VALUES ('加速', '50', 'L1Speed');
INSERT INTO `commands` VALUES ('金币', '150', 'L1Adena');
INSERT INTO `commands` VALUES ('血条', '50', 'L1HpBar');
INSERT INTO `commands` VALUES ('重设陷阱', '200', 'L1ResetTrap');
INSERT INTO `commands` VALUES ('重读陷阱', '200', 'L1ReloadTrap');
INSERT INTO `commands` VALUES ('显示陷阱', '200', 'L1ShowTrap');
INSERT INTO `commands` VALUES ('施法动画', '200', 'L1CastGfx');
INSERT INTO `commands` VALUES ('影像动画', '200', 'L1GfxId');
INSERT INTO `commands` VALUES ('背包影像', '200', 'L1InvGfxId');
INSERT INTO `commands` VALUES ('村庄', '150', 'L1HomeTown');
INSERT INTO `commands` VALUES ('游戏管理员', '150', 'L1GM');
INSERT INTO `commands` VALUES ('赠送', '200', 'L1Present');
INSERT INTO `commands` VALUES ('限等赠送', '200', 'L1LevelPresent');
INSERT INTO `commands` VALUES ('关机', '200', 'L1Shutdown');
INSERT INTO `commands` VALUES ('物品', '150', 'L1CreateItem');
INSERT INTO `commands` VALUES ('套装', '150', 'L1CreateItemSet');
INSERT INTO `commands` VALUES ('辅助', '100', 'L1Buff');
INSERT INTO `commands` VALUES ('查询', '50', 'L1Patrol');
INSERT INTO `commands` VALUES ('封锁IP', '150', 'L1BanIp');
INSERT INTO `commands` VALUES ('广播频道', '50', 'L1Chat');
INSERT INTO `commands` VALUES ('禁言', '50', 'L1ChatNG');
INSERT INTO `commands` VALUES ('skick1', '150', 'L1SKick');
INSERT INTO `commands` VALUES ('kick1', '50', 'L1Kick');
INSERT INTO `commands` VALUES ('powerkick1', '150', 'L1PowerKick');
INSERT INTO `commands` VALUES ('封锁帐号', '150', 'L1AccountBanKick');
INSERT INTO `commands` VALUES ('变身', '150', 'L1Poly');
INSERT INTO `commands` VALUES ('恢复', '100', 'L1Ress');
INSERT INTO `commands` VALUES ('死亡', '200', 'L1Kill');
INSERT INTO `commands` VALUES ('房间', '50', 'L1GMRoom');
INSERT INTO `commands` VALUES ('前往玩家', '50', 'L1ToPC');
INSERT INTO `commands` VALUES ('移动', '50', 'L1Move');
INSERT INTO `commands` VALUES ('天气', '150', 'L1ChangeWeather');
INSERT INTO `commands` VALUES ('前往怪物', '200', 'L1ToSpawn');
INSERT INTO `commands` VALUES ('书签', '50', 'L1Favorite');
INSERT INTO `commands` VALUES ('召回', '100', 'L1Recall');
INSERT INTO `commands` VALUES ('解除隐身', '50', 'L1Visible');
INSERT INTO `commands` VALUES ('召团', '100', 'L1PartyRecall');
INSERT INTO `commands` VALUES ('隐身', '50', 'L1Invisible');
INSERT INTO `commands` VALUES ('召怪', '100', 'L1SpawnCmd');
INSERT INTO `commands` VALUES ('创怪', '200', 'L1InsertSpawn');
INSERT INTO `commands` VALUES ('说明', '50', 'L1CommandHelp');
INSERT INTO `commands` VALUES ('动作', '200', 'L1Action');
INSERT INTO `commands` VALUES ('tile1', '200', 'L1Tile');
INSERT INTO `commands` VALUES ('findinvis1', '200', 'L1FindInvis');
INSERT INTO `commands` VALUES ('狂暴', '200', 'L1Crazy');
INSERT INTO `commands` VALUES ('掉落资料', '200', 'L1ItemDrops');
INSERT INTO `commands` VALUES ('查资料', '200', 'L1Search');
