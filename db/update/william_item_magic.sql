/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50524
Source Host           : localhost:3306
Source Database       : l1j2021

Target Server Type    : MYSQL
Target Server Version : 50524
File Encoding         : 65001

Date: 2012-10-19 08:17:34
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `william_item_magic`
-- ----------------------------
DROP TABLE IF EXISTS `william_item_magic`;
CREATE TABLE `william_item_magic` (
  `item_id` int(10) unsigned NOT NULL DEFAULT '0',
  `note` varchar(45) DEFAULT NULL,
  `checkClass` int(1) unsigned NOT NULL DEFAULT '0',
  `checkItem` int(10) unsigned NOT NULL DEFAULT '0',
  `skill_id` int(10) unsigned NOT NULL DEFAULT '0',
  `removeItem` int(1) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of william_item_magic
-- ----------------------------
INSERT INTO `william_item_magic` VALUES ('73601', '魔法卷轴 (加速术)', '0', '0', '43', '1');
INSERT INTO `william_item_magic` VALUES ('73602', '魔法卷轴 (地裂术)', '0', '0', '45', '1');
INSERT INTO `william_item_magic` VALUES ('73603', '魔法卷轴 (弱化术)', '0', '0', '47', '1');
INSERT INTO `william_item_magic` VALUES ('73604', '魔法卷轴 (烈炎术)', '0', '0', '46', '1');
INSERT INTO `william_item_magic` VALUES ('73605', '魔法卷轴 (祝福魔法武器)', '0', '0', '48', '1');
INSERT INTO `william_item_magic` VALUES ('73606', '魔法卷轴 (造尸术)', '0', '0', '41', '1');
INSERT INTO `william_item_magic` VALUES ('73607', '魔法卷轴 (通畅气脉术)', '0', '0', '26', '1');
INSERT INTO `william_item_magic` VALUES ('73608', '魔法卷轴 (体魄强健术)', '0', '0', '42', '1');
INSERT INTO `william_item_magic` VALUES ('73609', '魔法卷轴 (魔法相消术)', '0', '0', '44', '1');
INSERT INTO `william_item_magic` VALUES ('73610', '魔法卷轴 (冰矛围篱)', '0', '0', '50', '1');
INSERT INTO `william_item_magic` VALUES ('73611', '魔法卷轴 (召唤术)', '0', '0', '51', '1');
INSERT INTO `william_item_magic` VALUES ('73612', '魔法卷轴 (强力加速术)', '0', '0', '54', '1');
INSERT INTO `william_item_magic` VALUES ('73613', '魔法卷轴 (狂暴术)', '0', '0', '55', '1');
INSERT INTO `william_item_magic` VALUES ('73614', '魔法卷轴 (疾病术)', '0', '0', '56', '1');
INSERT INTO `william_item_magic` VALUES ('73615', '魔法卷轴 (神圣疾走)', '0', '0', '52', '1');
INSERT INTO `william_item_magic` VALUES ('73616', '魔法卷轴 (体力回复术)', '0', '0', '49', '1');
INSERT INTO `william_item_magic` VALUES ('73617', '魔法卷轴 (龙卷风)', '0', '0', '53', '1');
INSERT INTO `william_item_magic` VALUES ('73618', '魔法卷轴 (全部治愈术)', '0', '0', '57', '1');
INSERT INTO `william_item_magic` VALUES ('73619', '魔法卷轴 (冰雪暴)', '0', '0', '59', '1');
INSERT INTO `william_item_magic` VALUES ('73620', '魔法卷轴 (治愈能量风暴)', '0', '0', '63', '1');
INSERT INTO `william_item_magic` VALUES ('73621', '魔法卷轴 (火牢)', '0', '0', '58', '1');
INSERT INTO `william_item_magic` VALUES ('73622', '魔法卷轴 (返生术)', '0', '0', '61', '1');
INSERT INTO `william_item_magic` VALUES ('73623', '魔法卷轴 (隐身术)', '0', '0', '60', '1');
INSERT INTO `william_item_magic` VALUES ('73624', '魔法卷轴 (震裂术)', '0', '0', '62', '1');
INSERT INTO `william_item_magic` VALUES ('73625', '魔法卷轴 (魔法封印)', '0', '0', '64', '1');
INSERT INTO `william_item_magic` VALUES ('73626', '魔法卷轴 (强力无所遁形术)', '0', '0', '72', '1');
INSERT INTO `william_item_magic` VALUES ('73627', '魔法卷轴 (沉睡之雾)', '0', '0', '66', '1');
INSERT INTO `william_item_magic` VALUES ('73628', '魔法卷轴 (火风暴)', '0', '0', '70', '1');
INSERT INTO `william_item_magic` VALUES ('73629', '魔法卷轴 (圣结界)', '0', '0', '68', '1');
INSERT INTO `william_item_magic` VALUES ('73630', '魔法卷轴 (药水霜化术)', '0', '0', '71', '1');
INSERT INTO `william_item_magic` VALUES ('73631', '魔法卷轴 (变形术)', '0', '0', '67', '1');
INSERT INTO `william_item_magic` VALUES ('73632', '魔法卷轴 (雷霆风暴)', '0', '0', '65', '1');
INSERT INTO `william_item_magic` VALUES ('73633', '魔法卷轴 (冰雪飓风)', '0', '0', '80', '1');
INSERT INTO `william_item_magic` VALUES ('73634', '魔法卷轴 (创造魔法武器)', '0', '0', '73', '1');
INSERT INTO `william_item_magic` VALUES ('73635', '魔法卷轴 (流星雨)', '0', '0', '74', '1');
INSERT INTO `william_item_magic` VALUES ('73636', '魔法卷轴 (究极光裂术)', '0', '0', '77', '1');
INSERT INTO `william_item_magic` VALUES ('73637', '魔法卷轴 (终极返生术)', '0', '0', '75', '1');
INSERT INTO `william_item_magic` VALUES ('73638', '魔法卷轴 (绝对屏障)', '0', '0', '78', '1');
INSERT INTO `william_item_magic` VALUES ('73639', '魔法卷轴 (集体缓术速)', '0', '0', '76', '1');
INSERT INTO `william_item_magic` VALUES ('73640', '魔法卷轴 (灵魂升华)', '0', '0', '79', '1');
INSERT INTO `william_item_magic` VALUES ('73641', '生日快乐烟火', '0', '0', '121', '1');
INSERT INTO `william_item_magic` VALUES ('73642', '魔法卷轴(三重矢)', '0', '0', '132', '1');
INSERT INTO `william_item_magic` VALUES ('73643', '通畅气脉术(不消耗)', '0', '0', '26', '0');
INSERT INTO `william_item_magic` VALUES ('73644', '体魄强健术(不消耗)', '0', '0', '42', '0');
INSERT INTO `william_item_magic` VALUES ('73801', '究极起死回生术', '3', '0', '81', '0');
