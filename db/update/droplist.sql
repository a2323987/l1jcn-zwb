/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50524
Source Host           : localhost:3306
Source Database       : l1jtw

Target Server Type    : MYSQL
Target Server Version : 50524
File Encoding         : 65001

Date: 2012-10-09 15:56:12
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `droplist`
-- ----------------------------
DROP TABLE IF EXISTS `droplist`;
CREATE TABLE `droplist` (
  `mobId` int(6) unsigned NOT NULL DEFAULT '0',
  `location` varchar(45) NOT NULL DEFAULT '',
  `itemId` int(6) unsigned NOT NULL DEFAULT '0',
  `it_name` varchar(45) NOT NULL DEFAULT '',
  `min` int(4) unsigned NOT NULL DEFAULT '0',
  `max` int(4) unsigned NOT NULL DEFAULT '0',
  `chance` int(8) unsigned NOT NULL DEFAULT '0',
  `mapid` int(10) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`mobId`,`itemId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of droplist
-- ----------------------------
INSERT INTO `droplist` VALUES ('40000', '通用掉落', '40308', '金币', '10', '100', '1000000', '0');
INSERT INTO `droplist` VALUES ('45068', '变异的 漂浮之眼', '40057', '漂浮之眼肉', '1', '1', '1000000', '0');
INSERT INTO `droplist` VALUES ('45223', '食人妖精', '60011', '声望[1]', '1', '1', '50000', '7');
INSERT INTO `droplist` VALUES ('45241', '地狱犬', '60011', '声望[1]', '1', '1', '50000', '7');
INSERT INTO `droplist` VALUES ('45298', '食人妖精王', '60011', '声望[1]', '1', '1', '50000', '7');
INSERT INTO `droplist` VALUES ('45196', '鳄鱼', '40093', '空的魔法卷轴(等级4)', '1', '1', '10000', '70');
INSERT INTO `droplist` VALUES ('45355', '狼人', '40093', '空的魔法卷轴(等级4)', '1', '1', '10000', '70');
INSERT INTO `droplist` VALUES ('45358', '夏洛伯', '40093', '空的魔法卷轴(等级4)', '1', '1', '10000', '70');
INSERT INTO `droplist` VALUES ('45362', '亚力安', '40093', '空的魔法卷轴(等级4)', '1', '1', '10000', '70');
INSERT INTO `droplist` VALUES ('45364', '黑暗精灵', '40093', '空的魔法卷轴(等级4)', '1', '1', '10000', '70');
INSERT INTO `droplist` VALUES ('45371', '欧熊', '40093', '空的魔法卷轴(等级4)', '1', '1', '10000', '70');
INSERT INTO `droplist` VALUES ('45377', '蜥蜴人', '40093', '空的魔法卷轴(等级4)', '1', '1', '10000', '70');
INSERT INTO `droplist` VALUES ('45378', '卡司特', '40093', '空的魔法卷轴(等级4)', '1', '1', '10000', '70');
INSERT INTO `droplist` VALUES ('45387', '蛇女', '40093', '空的魔法卷轴(等级4)', '1', '1', '10000', '70');
INSERT INTO `droplist` VALUES ('45389', '莱肯', '40093', '空的魔法卷轴(等级4)', '1', '1', '10000', '70');
INSERT INTO `droplist` VALUES ('45390', '巨斧牛人', '40093', '空的魔法卷轴(等级4)', '1', '1', '10000', '70');
INSERT INTO `droplist` VALUES ('45392', '食人妖精', '40093', '空的魔法卷轴(等级4)', '1', '1', '10000', '70');
INSERT INTO `droplist` VALUES ('45401', '杨果里恩', '40093', '空的魔法卷轴(等级4)', '1', '1', '10000', '70');
INSERT INTO `droplist` VALUES ('45445', '格利芬', '40093', '空的魔法卷轴(等级4)', '1', '1', '10000', '70');
INSERT INTO `droplist` VALUES ('45449', '链锤牛人', '40093', '空的魔法卷轴(等级4)', '1', '1', '10000', '70');
INSERT INTO `droplist` VALUES ('45452', '哈维', '40093', '空的魔法卷轴(等级4)', '1', '1', '10000', '70');
INSERT INTO `droplist` VALUES ('45457', '变形怪', '40093', '空的魔法卷轴(等级4)', '1', '1', '10000', '70');
INSERT INTO `droplist` VALUES ('45469', '巨大鳄鱼', '40093', '空的魔法卷轴(等级4)', '1', '1', '10000', '70');
INSERT INTO `droplist` VALUES ('45470', '卡司特王', '40093', '空的魔法卷轴(等级4)', '1', '1', '10000', '70');
INSERT INTO `droplist` VALUES ('45477', '多罗', '40093', '空的魔法卷轴(等级4)', '1', '1', '10000', '70');
INSERT INTO `droplist` VALUES ('45505', '阿鲁巴', '40093', '空的魔法卷轴(等级4)', '1', '1', '10000', '70');
INSERT INTO `droplist` VALUES ('45509', '食人妖精王', '40093', '空的魔法卷轴(等级4)', '1', '1', '10000', '70');
INSERT INTO `droplist` VALUES ('45531', '邪恶蜥蜴', '40093', '空的魔法卷轴(等级4)', '1', '1', '10000', '70');
INSERT INTO `droplist` VALUES ('45538', '独眼巨人', '40093', '空的魔法卷轴(等级4)', '1', '1', '10000', '70');
INSERT INTO `droplist` VALUES ('45578', '遗忘飞龙', '40093', '空的魔法卷轴(等级4)', '1', '1', '10000', '70');
INSERT INTO `droplist` VALUES ('45196', '鳄鱼', '40094', '空的魔法卷轴(等级5)', '1', '1', '10000', '70');
INSERT INTO `droplist` VALUES ('45355', '狼人', '40094', '空的魔法卷轴(等级5)', '1', '1', '10000', '70');
INSERT INTO `droplist` VALUES ('45358', '夏洛伯', '40094', '空的魔法卷轴(等级5)', '1', '1', '10000', '70');
INSERT INTO `droplist` VALUES ('45362', '亚力安', '40094', '空的魔法卷轴(等级5)', '1', '1', '10000', '70');
INSERT INTO `droplist` VALUES ('45364', '黑暗精灵', '40094', '空的魔法卷轴(等级5)', '1', '1', '10000', '70');
INSERT INTO `droplist` VALUES ('45371', '欧熊', '40094', '空的魔法卷轴(等级5)', '1', '1', '10000', '70');
INSERT INTO `droplist` VALUES ('45377', '蜥蜴人', '40094', '空的魔法卷轴(等级5)', '1', '1', '10000', '70');
INSERT INTO `droplist` VALUES ('45378', '卡司特', '40094', '空的魔法卷轴(等级5)', '1', '1', '10000', '70');
INSERT INTO `droplist` VALUES ('45387', '蛇女', '40094', '空的魔法卷轴(等级5)', '1', '1', '10000', '70');
INSERT INTO `droplist` VALUES ('45389', '莱肯', '40094', '空的魔法卷轴(等级5)', '1', '1', '10000', '70');
INSERT INTO `droplist` VALUES ('45390', '巨斧牛人', '40094', '空的魔法卷轴(等级5)', '1', '1', '10000', '70');
INSERT INTO `droplist` VALUES ('45392', '食人妖精', '40094', '空的魔法卷轴(等级5)', '1', '1', '10000', '70');
INSERT INTO `droplist` VALUES ('45401', '杨果里恩', '40094', '空的魔法卷轴(等级5)', '1', '1', '10000', '70');
INSERT INTO `droplist` VALUES ('45445', '格利芬', '40094', '空的魔法卷轴(等级5)', '1', '1', '10000', '70');
INSERT INTO `droplist` VALUES ('45449', '链锤牛人', '40094', '空的魔法卷轴(等级5)', '1', '1', '10000', '70');
INSERT INTO `droplist` VALUES ('45452', '哈维', '40094', '空的魔法卷轴(等级5)', '1', '1', '10000', '70');
INSERT INTO `droplist` VALUES ('45457', '变形怪', '40094', '空的魔法卷轴(等级5)', '1', '1', '10000', '70');
INSERT INTO `droplist` VALUES ('45469', '巨大鳄鱼', '40094', '空的魔法卷轴(等级5)', '1', '1', '10000', '70');
INSERT INTO `droplist` VALUES ('45470', '卡司特王', '40094', '空的魔法卷轴(等级5)', '1', '1', '10000', '70');
INSERT INTO `droplist` VALUES ('45477', '多罗', '40094', '空的魔法卷轴(等级5)', '1', '1', '10000', '70');
INSERT INTO `droplist` VALUES ('45505', '阿鲁巴', '40094', '空的魔法卷轴(等级5)', '1', '1', '10000', '70');
INSERT INTO `droplist` VALUES ('45509', '食人妖精王', '40094', '空的魔法卷轴(等级5)', '1', '1', '10000', '70');
INSERT INTO `droplist` VALUES ('45531', '邪恶蜥蜴', '40094', '空的魔法卷轴(等级5)', '1', '1', '10000', '70');
INSERT INTO `droplist` VALUES ('45538', '独眼巨人', '40094', '空的魔法卷轴(等级5)', '1', '1', '10000', '70');
INSERT INTO `droplist` VALUES ('45578', '遗忘飞龙', '40094', '空的魔法卷轴(等级5)', '1', '1', '10000', '70');
INSERT INTO `droplist` VALUES ('45362', '亚力安', '40076', '古代的卷轴', '1', '1', '1000', '70');
INSERT INTO `droplist` VALUES ('45390', '巨斧牛人', '40076', '古代的卷轴', '1', '1', '1000', '70');
INSERT INTO `droplist` VALUES ('45449', '链锤牛人', '40076', '古代的卷轴', '1', '1', '1000', '70');
INSERT INTO `droplist` VALUES ('45457', '变形怪', '40076', '古代的卷轴', '1', '1', '1000', '70');
INSERT INTO `droplist` VALUES ('45531', '邪恶蜥蜴', '40076', '古代的卷轴', '1', '1', '1000', '70');
INSERT INTO `droplist` VALUES ('45578', '遗忘飞龙', '40076', '古代的卷轴', '1', '1', '1000', '70');
INSERT INTO `droplist` VALUES ('45578', '遗忘飞龙', '20140', '被遗忘的皮盔甲', '1', '1', '10000', '70');
INSERT INTO `droplist` VALUES ('45401', '杨果里恩', '20140', '被遗忘的皮盔甲', '1', '1', '10000', '70');
INSERT INTO `droplist` VALUES ('45378', '卡司特', '20140', '被遗忘的皮盔甲', '1', '1', '10000', '70');
INSERT INTO `droplist` VALUES ('45389', '莱肯', '20140', '被遗忘的皮盔甲', '1', '1', '10000', '70');
INSERT INTO `droplist` VALUES ('45371', '欧熊', '20140', '被遗忘的皮盔甲', '1', '1', '10000', '70');
INSERT INTO `droplist` VALUES ('45364', '黑暗精灵', '20140', '被遗忘的皮盔甲', '1', '1', '10000', '70');
INSERT INTO `droplist` VALUES ('45470', '卡司特王', '20140', '被遗忘的皮盔甲', '1', '1', '10000', '70');
INSERT INTO `droplist` VALUES ('45531', '邪恶蜥蜴', '20141', '被遗忘的长袍', '1', '1', '10000', '70');
INSERT INTO `droplist` VALUES ('45392', '食人妖精', '20141', '被遗忘的长袍', '1', '1', '10000', '70');
INSERT INTO `droplist` VALUES ('45445', '格利芬', '20141', '被遗忘的长袍', '1', '1', '10000', '70');
INSERT INTO `droplist` VALUES ('45387', '蛇女', '20141', '被遗忘的长袍', '1', '1', '10000', '70');
INSERT INTO `droplist` VALUES ('45377', '蜥蜴人', '20141', '被遗忘的长袍', '1', '1', '10000', '70');
INSERT INTO `droplist` VALUES ('45390', '巨斧牛人', '20142', '被遗忘的鳞甲', '1', '1', '10000', '70');
INSERT INTO `droplist` VALUES ('45358', '夏洛伯', '20142', '被遗忘的鳞甲', '1', '1', '10000', '70');
INSERT INTO `droplist` VALUES ('45477', '蜥蜴人', '20142', '被遗忘的鳞甲', '1', '1', '10000', '70');
INSERT INTO `droplist` VALUES ('45355', '狼人', '20142', '被遗忘的鳞甲', '1', '1', '10000', '70');
INSERT INTO `droplist` VALUES ('45387', '蛇女', '20142', '被遗忘的鳞甲', '1', '1', '10000', '70');
INSERT INTO `droplist` VALUES ('45469', '巨大鳄鱼', '20143', '被遗忘的金属盔甲', '1', '1', '10000', '70');
INSERT INTO `droplist` VALUES ('45449', '链锤牛人', '20143', '被遗忘的金属盔甲', '1', '1', '10000', '70');
INSERT INTO `droplist` VALUES ('45505', '阿鲁巴', '20143', '被遗忘的金属盔甲', '1', '1', '10000', '70');
INSERT INTO `droplist` VALUES ('45509', '食人妖精王', '20143', '被遗忘的金属盔甲', '1', '1', '10000', '70');
INSERT INTO `droplist` VALUES ('45538', '独眼巨人', '20143', '被遗忘的金属盔甲', '1', '1', '10000', '70');
INSERT INTO `droplist` VALUES ('45362', '亚力安', '20143', '被遗忘的金属盔甲', '1', '1', '10000', '70');
INSERT INTO `droplist` VALUES ('45505', '阿鲁巴', '17', '受封印 被遗忘的巨剑', '1', '1', '10000', '70');
INSERT INTO `droplist` VALUES ('45538', '独眼巨人', '17', '受封印 被遗忘的巨剑', '1', '1', '10000', '70');
INSERT INTO `droplist` VALUES ('45362', '亚力安', '18', '受封印 被遗忘的剑', '1', '1', '10000', '70');
INSERT INTO `droplist` VALUES ('45531', '邪恶蜥蜴', '18', '受封印 被遗忘的剑', '1', '1', '10000', '70');
INSERT INTO `droplist` VALUES ('45445', '格利芬', '167', '受封印 被遗忘的弩枪', '1', '1', '10000', '70');
INSERT INTO `droplist` VALUES ('45578', '遗忘飞龙', '167', '受封印 被遗忘的弩枪', '1', '1', '10000', '70');
INSERT INTO `droplist` VALUES ('45578', '遗忘飞龙', '60011', '声望[1]', '1', '1', '10000', '70');
INSERT INTO `droplist` VALUES ('45401', '杨果里恩', '60011', '声望[1]', '1', '1', '10000', '70');
INSERT INTO `droplist` VALUES ('45378', '卡司特', '60011', '声望[1]', '1', '1', '10000', '70');
INSERT INTO `droplist` VALUES ('45389', '莱肯', '60011', '声望[1]', '1', '1', '10000', '70');
INSERT INTO `droplist` VALUES ('45371', '欧熊', '60011', '声望[1]', '1', '1', '10000', '70');
INSERT INTO `droplist` VALUES ('45364', '黑暗精灵', '60011', '声望[1]', '1', '1', '10000', '70');
INSERT INTO `droplist` VALUES ('45470', '卡司特王', '60011', '声望[1]', '1', '1', '10000', '70');
INSERT INTO `droplist` VALUES ('45531', '邪恶蜥蜴', '60011', '声望[1]', '1', '1', '10000', '70');
INSERT INTO `droplist` VALUES ('45392', '食人妖精', '60011', '声望[1]', '1', '1', '10000', '70');
INSERT INTO `droplist` VALUES ('45445', '格利芬', '60011', '声望[1]', '1', '1', '10000', '70');
INSERT INTO `droplist` VALUES ('45387', '蛇女', '60011', '声望[1]', '1', '1', '10000', '70');
INSERT INTO `droplist` VALUES ('45377', '蜥蜴人', '60011', '声望[1]', '1', '1', '10000', '70');
INSERT INTO `droplist` VALUES ('45390', '巨斧牛人', '60011', '声望[1]', '1', '1', '10000', '70');
INSERT INTO `droplist` VALUES ('45358', '夏洛伯', '60011', '声望[1]', '1', '1', '10000', '70');
INSERT INTO `droplist` VALUES ('45477', '蜥蜴人', '60011', '声望[1]', '1', '1', '10000', '70');
INSERT INTO `droplist` VALUES ('45355', '狼人', '60011', '声望[1]', '1', '1', '10000', '70');
INSERT INTO `droplist` VALUES ('45469', '巨大鳄鱼', '60011', '声望[1]', '1', '1', '10000', '70');
INSERT INTO `droplist` VALUES ('45449', '链锤牛人', '60011', '声望[1]', '1', '1', '10000', '70');
INSERT INTO `droplist` VALUES ('45505', '阿鲁巴', '60011', '声望[1]', '1', '1', '10000', '70');
INSERT INTO `droplist` VALUES ('45509', '食人妖精王', '60011', '声望[1]', '1', '1', '10000', '70');
INSERT INTO `droplist` VALUES ('45538', '独眼巨人', '60011', '声望[1]', '1', '1', '10000', '70');
INSERT INTO `droplist` VALUES ('45362', '亚力安', '60011', '声望[1]', '1', '1', '10000', '70');
INSERT INTO `droplist` VALUES ('45505', '阿鲁巴', '60012', '声望[5]', '1', '1', '5000', '70');
INSERT INTO `droplist` VALUES ('45538', '独眼巨人', '60012', '声望[5]', '1', '1', '5000', '70');
INSERT INTO `droplist` VALUES ('45362', '亚力安', '60012', '声望[5]', '1', '1', '5000', '70');
INSERT INTO `droplist` VALUES ('45531', '邪恶蜥蜴', '60012', '声望[5]', '1', '1', '5000', '70');
INSERT INTO `droplist` VALUES ('45445', '格利芬', '60012', '声望[5]', '1', '1', '5000', '70');
INSERT INTO `droplist` VALUES ('45578', '遗忘飞龙', '60012', '声望[5]', '1', '1', '5000', '70');
INSERT INTO `droplist` VALUES ('45578', '遗忘飞龙', '60013', '声望[10]', '1', '1', '2500', '70');
INSERT INTO `droplist` VALUES ('45573', '巴风特', '60011', '声望[1]', '1', '1', '300000', '100');
INSERT INTO `droplist` VALUES ('45600', '克特', '60011', '声望[1]', '1', '1', '300000', '100');
INSERT INTO `droplist` VALUES ('45488', '卡士伯', '60011', '声望[1]', '1', '1', '300000', '100');
INSERT INTO `droplist` VALUES ('45601', '死亡骑士', '60011', '声望[1]', '1', '1', '300000', '100');
INSERT INTO `droplist` VALUES ('45456', '魔法师', '60011', '声望[1]', '1', '1', '300000', '100');
INSERT INTO `droplist` VALUES ('45584', '巨大牛人', '60011', '声望[1]', '1', '1', '300000', '100');
INSERT INTO `droplist` VALUES ('45573', '巴风特', '60012', '声望[5]', '1', '1', '150000', '100');
INSERT INTO `droplist` VALUES ('45600', '克特', '60012', '声望[5]', '1', '1', '150000', '100');
INSERT INTO `droplist` VALUES ('45488', '卡士伯', '60012', '声望[5]', '1', '1', '150000', '100');
INSERT INTO `droplist` VALUES ('45601', '死亡骑士', '60012', '声望[5]', '1', '1', '150000', '100');
INSERT INTO `droplist` VALUES ('45456', '魔法师', '60012', '声望[5]', '1', '1', '150000', '100');
INSERT INTO `droplist` VALUES ('45584', '巨大牛人', '60012', '声望[5]', '1', '1', '150000', '100');
INSERT INTO `droplist` VALUES ('45573', '巴风特', '60013', '声望[10]', '1', '1', '75000', '100');
INSERT INTO `droplist` VALUES ('45600', '克特', '60013', '声望[10]', '1', '1', '75000', '100');
INSERT INTO `droplist` VALUES ('45488', '卡士伯', '60013', '声望[10]', '1', '1', '75000', '100');
INSERT INTO `droplist` VALUES ('45601', '死亡骑士', '60013', '声望[10]', '1', '1', '75000', '100');
INSERT INTO `droplist` VALUES ('45456', '魔法师', '60013', '声望[10]', '1', '1', '75000', '100');
INSERT INTO `droplist` VALUES ('45584', '巨大牛人', '60013', '声望[10]', '1', '1', '75000', '100');
INSERT INTO `droplist` VALUES ('45573', '巴风特', '109', '失去魔力的巴风特魔杖', '1', '1', '75000', '100');
INSERT INTO `droplist` VALUES ('45600', '克特', '15', '失去魔力的克特之剑', '1', '1', '75000', '100');
INSERT INTO `droplist` VALUES ('45601', '死亡骑士', '58', '死亡骑士的烈炎之剑', '1', '1', '75000', '100');
INSERT INTO `droplist` VALUES ('45573', '巴风特', '20117', '巴风特盔甲', '1', '1', '3500', '100');
INSERT INTO `droplist` VALUES ('45600', '克特', '20041', '克特头盔', '1', '1', '3500', '100');
INSERT INTO `droplist` VALUES ('45600', '克特', '20150', '克特盔甲', '1', '1', '3500', '100');
INSERT INTO `droplist` VALUES ('45600', '克特', '20184', '克特手套', '1', '1', '3500', '100');
INSERT INTO `droplist` VALUES ('45600', '克特', '20214', '克特长靴', '1', '1', '3500', '100');
INSERT INTO `droplist` VALUES ('45601', '死亡骑士', '20010', '死亡骑士头盔', '1', '1', '3500', '100');
INSERT INTO `droplist` VALUES ('45601', '死亡骑士', '20100', '死亡骑士盔甲', '1', '1', '3500', '100');
INSERT INTO `droplist` VALUES ('45601', '死亡骑士', '20166', '死亡骑士手套', '1', '1', '3500', '100');
INSERT INTO `droplist` VALUES ('45601', '死亡骑士', '20198', '死亡骑士长靴', '1', '1', '3500', '100');
INSERT INTO `droplist` VALUES ('45488', '卡士伯', '20040', '卡士伯之帽', '1', '1', '3500', '100');
INSERT INTO `droplist` VALUES ('45497', '马库尔', '20018', '马库尔之帽', '1', '1', '3500', '100');
INSERT INTO `droplist` VALUES ('45473', '巴土瑟', '20025', '巴土瑟之帽', '1', '1', '3500', '100');
INSERT INTO `droplist` VALUES ('45464', '西玛', '20029', '西玛之帽', '1', '1', '3500', '100');
INSERT INTO `droplist` VALUES ('45497', '马库尔', '60012', '声望[5]', '1', '1', '5000', '100');
INSERT INTO `droplist` VALUES ('45473', '巴土瑟', '60012', '声望[5]', '1', '1', '5000', '100');
INSERT INTO `droplist` VALUES ('45464', '西玛', '60012', '声望[5]', '1', '1', '5000', '100');
INSERT INTO `droplist` VALUES ('45497', '马库尔', '60011', '声望[1]', '1', '1', '10000', '100');
INSERT INTO `droplist` VALUES ('45473', '巴土瑟', '60011', '声望[1]', '1', '1', '10000', '100');
INSERT INTO `droplist` VALUES ('45464', '西玛', '60011', '声望[1]', '1', '1', '10000', '100');

//所有怪都会喝绿水和HP水

INSERT INTO `droplist` VALUES ('40000', '40021', '5', '10', '1000000', '通用掉落怪', '浓缩终极体力恢复剂', '0');
INSERT INTO `droplist` VALUES ('40000', '40018', '1', '1', '1000000', '通用掉落怪', '强化 绿色药水', '0');
