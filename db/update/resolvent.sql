/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50524
Source Host           : localhost:3306
Source Database       : l1jtw

Target Server Type    : MYSQL
Target Server Version : 50524
File Encoding         : 65001

Date: 2012-10-30 21:32:41
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `resolvent`
-- ----------------------------
DROP TABLE IF EXISTS `resolvent`;
CREATE TABLE `resolvent` (
  `item_id` int(10) NOT NULL DEFAULT '0',
  `note` varchar(45) NOT NULL,
  `crystal_item` int(10) NOT NULL DEFAULT '41246',
  `crystal_count` int(10) NOT NULL DEFAULT '0',
  `crystal_chance` int(10) NOT NULL DEFAULT '50',
  PRIMARY KEY (`item_id`)
) ENGINE=MyISAM DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of resolvent
-- ----------------------------
INSERT INTO `resolvent` VALUES ('20140', '被遗忘的皮盔甲', '41246', '10', '50');
INSERT INTO `resolvent` VALUES ('20141', '被遗忘的长袍', '41246', '10', '50');
INSERT INTO `resolvent` VALUES ('20142', '被遗忘的鳞甲', '41246', '10', '50');
INSERT INTO `resolvent` VALUES ('20143', '被遗忘的金属盔甲', '41246', '10', '50');
INSERT INTO `resolvent` VALUES ('20172', '水灵手套', '41246', '10', '50');
INSERT INTO `resolvent` VALUES ('20177', '地灵手套', '41246', '10', '50');
INSERT INTO `resolvent` VALUES ('20181', '火灵手套', '41246', '10', '50');
INSERT INTO `resolvent` VALUES ('20189', '风灵手套', '41246', '10', '50');
INSERT INTO `resolvent` VALUES ('20266', '智力项链', '41246', '10', '50');
INSERT INTO `resolvent` VALUES ('20284', '召唤控制戒指', '41246', '10', '50');
INSERT INTO `resolvent` VALUES ('20306', '小型身体腰带', '41246', '10', '50');
INSERT INTO `resolvent` VALUES ('20307', '小型灵魂腰带', '41246', '10', '50');
INSERT INTO `resolvent` VALUES ('20308', '小型精神腰带', '41246', '10', '50');
INSERT INTO `resolvent` VALUES ('20312', '身体腰带', '41246', '10', '50');
INSERT INTO `resolvent` VALUES ('20316', '灵魂腰带', '41246', '10', '50');
INSERT INTO `resolvent` VALUES ('20319', '精神腰带', '41246', '10', '50');
INSERT INTO `resolvent` VALUES ('20321', '多罗皮带', '41246', '10', '50');
INSERT INTO `resolvent` VALUES ('40014', '勇敢药水', '41246', '10', '50');
INSERT INTO `resolvent` VALUES ('40044', '钻石', '41246', '10', '50');
INSERT INTO `resolvent` VALUES ('40045', '红宝石', '41246', '10', '50');
INSERT INTO `resolvent` VALUES ('40046', '蓝宝石', '41246', '10', '50');
INSERT INTO `resolvent` VALUES ('40047', '绿宝石', '41246', '10', '50');
INSERT INTO `resolvent` VALUES ('40048', '品质钻石', '41246', '10', '50');
INSERT INTO `resolvent` VALUES ('40049', '品质红宝石', '41246', '10', '50');
INSERT INTO `resolvent` VALUES ('40050', '品质蓝宝石', '41246', '10', '50');
INSERT INTO `resolvent` VALUES ('40051', '品质绿宝石', '41246', '10', '50');
INSERT INTO `resolvent` VALUES ('40090', '空的魔法卷轴(等级1)', '41246', '10', '50');
INSERT INTO `resolvent` VALUES ('40091', '空的魔法卷轴(等级2)', '41246', '10', '50');
INSERT INTO `resolvent` VALUES ('40092', '空的魔法卷轴(等级3)', '41246', '10', '50');
INSERT INTO `resolvent` VALUES ('40093', '空的魔法卷轴(等级4)', '41246', '10', '50');
INSERT INTO `resolvent` VALUES ('40094', '空的魔法卷轴(等级5)', '41246', '10', '50');
INSERT INTO `resolvent` VALUES ('120266', '智力项链', '41246', '10', '50');
INSERT INTO `resolvent` VALUES ('120306', '小型身体腰带', '41246', '10', '50');
INSERT INTO `resolvent` VALUES ('120307', '小型灵魂腰带', '41246', '10', '50');
INSERT INTO `resolvent` VALUES ('120308', '小型精神腰带', '41246', '10', '50');
INSERT INTO `resolvent` VALUES ('120312', '身体腰带', '41246', '10', '50');
INSERT INTO `resolvent` VALUES ('120316', '灵魂腰带', '41246', '10', '50');
INSERT INTO `resolvent` VALUES ('120319', '精神腰带', '41246', '10', '50');
INSERT INTO `resolvent` VALUES ('120321', '多罗皮带', '41246', '10', '50');
INSERT INTO `resolvent` VALUES ('61', '真．冥皇执行剑', '60653', '50', '50');
INSERT INTO `resolvent` VALUES ('84', '暗黑双刀', '60653', '50', '50');
INSERT INTO `resolvent` VALUES ('134', '圣晶魔杖', '60653', '50', '50');
INSERT INTO `resolvent` VALUES ('189', '暗黑十字弓', '60653', '50', '50');
INSERT INTO `resolvent` VALUES ('20058', '武官斗篷', '60653', '10', '50');
INSERT INTO `resolvent` VALUES ('20233', '神官魔法书', '60653', '10', '50');
INSERT INTO `resolvent` VALUES ('20113', '武官护铠', '60653', '10', '50');
INSERT INTO `resolvent` VALUES ('20129', '神官法袍', '60653', '10', '50');
INSERT INTO `resolvent` VALUES ('20067', '神官斗篷', '60653', '10', '50');
INSERT INTO `resolvent` VALUES ('20070', '黑暗斗篷', '60653', '10', '50');
INSERT INTO `resolvent` VALUES ('20176', '神官手套', '60653', '10', '50');
INSERT INTO `resolvent` VALUES ('20168', '武官手套', '60653', '10', '50');
INSERT INTO `resolvent` VALUES ('20264', '力量项链', '60653', '10', '50');
INSERT INTO `resolvent` VALUES ('20280', '灭魔戒指', '60653', '10', '50');
INSERT INTO `resolvent` VALUES ('20201', '武官长靴', '60653', '10', '50');
INSERT INTO `resolvent` VALUES ('20210', '黑暗长靴', '60653', '10', '50');
INSERT INTO `resolvent` VALUES ('20032', '黑暗头饰', '60653', '10', '50');
INSERT INTO `resolvent` VALUES ('20208', '神官长靴', '60653', '10', '50');
INSERT INTO `resolvent` VALUES ('20098', '黑暗栖林者盔甲', '60653', '10', '50');
INSERT INTO `resolvent` VALUES ('20030', '神官头饰', '60653', '10', '50');
INSERT INTO `resolvent` VALUES ('20020', '武官头盔', '60653', '10', '50');
INSERT INTO `resolvent` VALUES ('20137', '精灵链甲', '60653', '10', '50');
INSERT INTO `resolvent` VALUES ('20132', '黑暗披肩', '60653', '10', '50');
INSERT INTO `resolvent` VALUES ('20228', '武官之盾', '60653', '10', '50');
INSERT INTO `resolvent` VALUES ('20267', '精神项链', '41246', '10', '50');
INSERT INTO `resolvent` VALUES ('120267', '精神项链', '41246', '10', '50');
