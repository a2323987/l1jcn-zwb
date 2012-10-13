/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50524
Source Host           : localhost:3306
Source Database       : l1jcn

Target Server Type    : MYSQL
Target Server Version : 50524
File Encoding         : 65001

Date: 2012-10-10 21:54:57
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `polymorphs`
-- ----------------------------
DROP TABLE IF EXISTS `polymorphs`;
CREATE TABLE `polymorphs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `解注` varchar(45) DEFAULT NULL,
  `polyid` int(11) DEFAULT NULL,
  `minlevel` int(11) DEFAULT NULL,
  `weaponequip` int(11) DEFAULT NULL,
  `armorequip` int(11) DEFAULT NULL,
  `isSkillUse` int(11) DEFAULT NULL,
  `cause` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=9227 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of polymorphs
-- ----------------------------
INSERT INTO `polymorphs` VALUES ('29', 'floating eye', '漂浮之眼\r\n', '29', '1', '0', '0', '1', '7');
INSERT INTO `polymorphs` VALUES ('95', 'shelob', '夏洛伯', '95', '10', '0', '0', '0', '7');
INSERT INTO `polymorphs` VALUES ('146', 're ungoliant', '杨果里恩', '146', '1', '0', '0', '0', '7');
INSERT INTO `polymorphs` VALUES ('938', 'beagle', '小猎犬', '938', '1', '0', '2', '1', '7');
INSERT INTO `polymorphs` VALUES ('945', 'milkcow', '牛\r\n', '945', '1', '0', '0', '0', '7');
INSERT INTO `polymorphs` VALUES ('947', 'deer', '鹿\r\n', '947', '1', '0', '0', '0', '7');
INSERT INTO `polymorphs` VALUES ('951', 'cerberus', '地狱犬\r\n', '951', '15', '0', '0', '0', '7');
INSERT INTO `polymorphs` VALUES ('979', 'wild boar', '野猪\r\n', '979', '1', '0', '0', '1', '7');
INSERT INTO `polymorphs` VALUES ('1037', 'giant ant', '巨蚁', '1037', '1', '0', '136', '0', '7');
INSERT INTO `polymorphs` VALUES ('1039', 'giant ant soldier', '巨大兵蚁', '1039', '1', '0', '136', '0', '7');
INSERT INTO `polymorphs` VALUES ('1047', 'scorpion', '毒蝎', '1047', '15', '0', '0', '0', '7');
INSERT INTO `polymorphs` VALUES ('2064', 'snowman', '雪人', '2064', '1', '0', '1027', '1', '7');
INSERT INTO `polymorphs` VALUES ('2284', 'dark elf polymorph', '黑暗精灵', '2284', '52', '256', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('2323', 'orc scout polymorph', '妖魔巡手', '2323', '15', '256', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('2374', 're skeleton', '骷髅', '2374', '1', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('2376', 'skeleton axeman polymorph', '骷髅斧手', '2376', '10', '1791', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('2377', 'skeleton pike polymorph', '骷髅枪兵', '2377', '10', '1791', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('2378', 'spartoi polymorph', '史巴托\r\n史巴托\r\n', '2378', '10', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('2384', 'succubus morph', '思克巴\r\n', '2384', '40', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('2385', 'yeti morph', '雪怪', '2385', '15', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('2386', 'minotaur i morph', '巨斧牛人\r\n', '2386', '15', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('2387', 'giant a morph', '巨人\r\n', '2387', '15', '1791', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('2388', 'death', '死神', '2388', '1', '0', '32', '0', '7');
INSERT INTO `polymorphs` VALUES ('2501', 'jack o lantern', '南瓜怪', '2501', '1', '751', '417', '0', '7');
INSERT INTO `polymorphs` VALUES ('3101', 'black knight chief morph', '克特', '3101', '51', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('3102', 'great minotaur morph', '巨大牛人', '3102', '50', '1791', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('3103', 'barlog morph', '炎魔\r\n', '3103', '52', '1791', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('3126', 'fire bowman morph', '火焰弓箭手\r\n', '3126', '51', '256', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('3630', 'cyclops', '独眼巨人\r\n', '3630', '40', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('3631', 'griffon', '格利芬\r\n', '3631', '40', '0', '32', '1', '7');
INSERT INTO `polymorphs` VALUES ('3632', 're cockatrice', '亚力安', '3632', '1', '0', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('3633', 'ettin', '阿鲁巴\r\n', '3633', '40', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('3634', 'assassin', '刺客', '3634', '45', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('3664', 'baranka', '巴兰卡', '3664', '1', '704', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('3750', 'haregi', '日本服装(女)', '3750', '1', '0', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('3784', 'death knight', '死亡骑士\r\n', '3784', '52', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('3860', 'bow orc', '妖魔弓箭手\r\n', '3860', '1', '256', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('3861', 'goblin', '哥布林\r\n', '3861', '1', '751', '913', '0', '7');
INSERT INTO `polymorphs` VALUES ('3862', 'kobolds', '地灵\r\n', '3862', '1', '751', '913', '1', '7');
INSERT INTO `polymorphs` VALUES ('3863', 're dwarf', '侏儒', '3863', '1', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('3864', 'orc fighter', '妖魔斗士\r\n', '3864', '1', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('3865', 're werewolf', '狼人', '3865', '1', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('3866', 'gandi orc', '甘地妖魔\r\n', '3866', '10', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('3867', 'rova orc', '罗孚妖魔\r\n', '3867', '10', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('3868', 'atuba orc', '阿土巴妖魔\r\n', '3868', '10', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('3869', 'dudamara orc', '都达玛拉妖魔\r\n', '3869', '10', '1791', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('3870', 'neruga orc', '那鲁加妖魔\r\n', '3870', '10', '1791', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('3871', 're skeleton archer', '骷髅弓箭手', '3871', '1', '256', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('3872', 'zombie', '人形僵尸\r\n', '3872', '10', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('3873', 're ghoul', '食尸鬼', '3873', '10', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('3874', 're lycanthrope', '莱肯', '3874', '1', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('3875', 'ghast', '卡司特\r\n', '3875', '10', '751', '945', '1', '7');
INSERT INTO `polymorphs` VALUES ('3876', 'bugbear', '食人妖精', '3876', '10', '751', '945', '1', '7');
INSERT INTO `polymorphs` VALUES ('3877', 'ogre', '殴吉\r\n', '3877', '15', '1791', '913', '1', '7');
INSERT INTO `polymorphs` VALUES ('3878', 'troll', '多罗\r\n', '3878', '15', '751', '545', '1', '7');
INSERT INTO `polymorphs` VALUES ('3879', 'elder', '长者', '3879', '15', '751', '0', '1', '7');
INSERT INTO `polymorphs` VALUES ('3880', 'king bugbear', '食人妖精王\r\n', '3880', '15', '751', '945', '1', '7');
INSERT INTO `polymorphs` VALUES ('3881', 'dark elder', '黑长者\r\n', '3881', '45', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('3882', 'necromancer1', '巴士瑟 (地)', '3882', '45', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('3883', 'necromancer2', '卡士伯 (火)', '3883', '45', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('3884', 'necromancer3', '马库尔 (水)', '3884', '45', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('3885', 'necromancer4', '西玛 (风)', '3885', '45', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('3886', 'lesser demon', '小恶魔', '3886', '45', '751', '4095', '0', '7');
INSERT INTO `polymorphs` VALUES ('3887', 'darkelf carrier', '黑暗妖精运送员\r\n', '3887', '45', '1791', '4095', '0', '7');
INSERT INTO `polymorphs` VALUES ('3888', 'baphomet', '巴风特\r\n', '3888', '50', '751', '954', '1', '7');
INSERT INTO `polymorphs` VALUES ('3889', 'demon', '恶魔', '3889', '51', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('3890', 'ancient black knight morph', '黑暗骑士', '3890', '55', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('3891', 'ancient black mage morph', '黑暗法师', '3891', '55', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('3892', 'ancient black scouter morph', '黑暗巡守\r\n', '3892', '55', '256', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('3893', 'ancient silver knight morph', '银光骑士\r\n', '3893', '60', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('3894', 'ancient silver mage morph', '银光法师', '3894', '60', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('3895', 'ancient silver scouter morph', '银光巡守\r\n', '3895', '60', '256', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('3896', 'ancient gold knight morph', '黄金骑士\r\n', '3896', '65', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('3897', 'ancient gold mage morph', '黄金法师\r\n', '3897', '65', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('3898', 'ancient gold scouter morph', '黄金巡守\r\n', '3898', '65', '256', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('3899', 'ancient platinum knight morph', '白金骑士\r\n', '3899', '70', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('3900', 'ancient platinum mage morph', '白金法师\r\n', '3900', '70', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('3901', 'ancient platinum scouter morph', '白金巡守\r\n', '3901', '70', '256', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('3902', 'Kelenis Morph', '赛尼斯\r\n', '3902', '1', '43', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('3903', 'Ken Lauhel Morph', '反王肯恩\r\n', '3903', '1', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('3904', 'stone golem', '高仑石头怪\r\n', '3904', '1', '751', '145', '1', '7');
INSERT INTO `polymorphs` VALUES ('3905', 'beleth', '巴列斯\r\n', '3905', '50', '751', '954', '1', '7');
INSERT INTO `polymorphs` VALUES ('3906', 're orc', '妖魔', '3906', '1', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('3945', 're gelatincube', '果冻怪', '3945', '1', '751', '0', '1', '7');
INSERT INTO `polymorphs` VALUES ('3950', 're middle oum', '欧姆民兵', '3950', '1', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('3952', 'vampire', '吸血鬼\r\n', '3952', '1', '0', '32', '0', '7');
INSERT INTO `polymorphs` VALUES ('4000', 'knight vald morph', '骑士范德\r\n', '4000', '1', '1791', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('4001', 'iris morph', '艾莉丝\r\n', '4001', '1', '1791', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('4002', 'paperman morph', '纸人\r\n', '4002', '1', '1791', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('4004', 'succubus queen morph', '思克巴女皇\r\n', '4004', '1', '1791', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('4186', 'robber bone', '海贼骷髅', '4186', '1', '751', '4095', '0', '7');
INSERT INTO `polymorphs` VALUES ('4188', 'robber bone head', '海贼骷髅首领\r\n', '4188', '1', '751', '4095', '0', '7');
INSERT INTO `polymorphs` VALUES ('4190', 'robber bone soldier', '海贼骷髅士兵', '4190', '1', '256', '4095', '0', '7');
INSERT INTO `polymorphs` VALUES ('4227', 'hakama', '韩国服装(男)\r\n', '4227', '1', '0', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('4767', 'rabbit', '曼波兔', '4767', '1', '0', '4095', '0', '7');
INSERT INTO `polymorphs` VALUES ('4769', 'carrot rabbit', '曼波兔(有罗卜)\r\n', '4769', '1', '0', '4095', '0', '7');
INSERT INTO `polymorphs` VALUES ('4917', 'darkelf ranger morph', '黑暗妖精巡守\r\n', '4917', '45', '256', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('4918', 'bandit bow morph', '强盗\r\n', '4918', '40', '256', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('4919', 'darkelf guard morph', '黑暗妖精警卫 (弓)\r\n', '4919', '50', '256', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('4920', 'elmor general morph', '艾尔摩将军\r\n', '4920', '45', '1791', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('4921', 'darkelf general morph', '黑暗妖精将军\r\n', '4921', '52', '1791', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('4922', 'guardian armor morph', '铠甲守护神\r\n', '4922', '50', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('4923', 'black knight morph', '黑骑士', '4923', '51', '1080', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('4924', 'darkelf spear morph', '黑暗妖精警卫 (枪)\r\n', '4924', '50', '1080', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('4925', 'elmor soldier morph', '艾尔摩士兵\r\n', '4925', '40', '1080', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('4926', 'darkelf wizard morph', '黑暗妖精法师\r\n', '4926', '50', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('4928', 'high collie', '高等柯利\r\n', '4928', '1', '0', '2', '1', '7');
INSERT INTO `polymorphs` VALUES ('4929', 'high raccoon', '高等浣熊\r\n', '4929', '1', '0', '2', '1', '7');
INSERT INTO `polymorphs` VALUES ('4932', 'assassin master morph', '刺客首领\r\n', '4932', '52', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('5184', 'red uniform', '红色足球员\r\n', '5184', '1', '0', '8', '1', '7');
INSERT INTO `polymorphs` VALUES ('5186', 'blue uniform', '蓝色足球员\r\n', '5186', '1', '0', '8', '1', '7');
INSERT INTO `polymorphs` VALUES ('5645', 'Halloween Pumpkin', '南瓜稻草人\r\n', '5645', '1', '2047', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('5719', 'manekineko', '招财猫\r\n', '5719', '1', '2047', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('5727', 'ancient black assassin morph', '黑暗刺客\r\n', '5727', '55', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('5730', 'ancient silver assassin morph', '银光刺客\r\n', '5730', '60', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('5733', 'ancient gold assassin morph', '黄金刺客\r\n', '5733', '65', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('5736', 'ancient platinum assassin morph', '白金刺客\r\n', '5736', '70', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('5976', 'high bear', '高等熊\r\n', '5976', '1', '751', '2', '0', '7');
INSERT INTO `polymorphs` VALUES ('6002', 'spirit of earth boss', '土精灵王(拿槌子)\r\n', '6002', '1', '8', '0', '1', '7');
INSERT INTO `polymorphs` VALUES ('6010', 'red orc', '红色的妖魔\r\n', '6010', '1', '0', '1', '0', '7');
INSERT INTO `polymorphs` VALUES ('6080', 'princess horse', '骑马的公主\r\n', '6080', '1', '16', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('6086', 'Rabor Born Head', '海贼骷髅\r\n', '6086', '1', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('6087', 'Rabor Born archer', '海贼骷髅士兵\r\n', '6087', '1', '256', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('6088', 'Rabor Born knife', '海贼骷髅刀手\r\n', '6088', '1', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('6089', 'drake morph', '德雷克\r\n', '6089', '1', '1791', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('6094', 'prince horse', '骑马的王子\r\n', '6094', '1', '16', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('6136', 'barlog 52', '炎魔 52\r\n', '6136', '52', '1791', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('6137', 'death 52', '死亡骑士', '6137', '52', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('6138', 'assassin 52', '刺客首领 52\r\n', '6138', '52', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('6139', 'general 52', '黑暗妖精将军 52\r\n', '6139', '52', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('6140', 'darkelf 52', '黑暗精灵 52\r\n', '6140', '52', '256', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('6141', 'barlog 55', '炎魔 55\r\n', '6141', '55', '1791', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('6142', 'death 55', '死亡骑士', '6142', '55', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('6143', 'assassin 55', '刺客首领 55\r\n', '6143', '55', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('6144', 'general 55', '黑暗妖精将军 55\r\n', '6144', '55', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('6145', 'darkelf 55', '黑暗妖精', '6145', '55', '256', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('6146', 'barlog 60', '炎魔 60\r\n', '6146', '60', '1791', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('6147', 'death 60', '死亡骑士', '6147', '60', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('6148', 'assassin 60', '刺客首领 60\r\n', '6148', '60', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('6149', 'general 60', '黑暗妖精将军 60\r\n', '6149', '60', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('6150', 'darkelf 60', '黑暗妖精', '6150', '60', '256', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('6151', 'barlog 65', '炎魔 65\r\n', '6151', '65', '1791', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('6152', 'death 65', '死亡骑士', '6152', '65', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('6153', 'assassin 65', '刺客首领 65\r\n', '6153', '65', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('6154', 'general 65', '黑暗妖精将军 65', '6154', '65', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('6155', 'darkelf 65', '黑暗妖精', '6155', '65', '256', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('6156', 'barlog 70', '炎魔 70', '6156', '70', '1791', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('6157', 'death 70', '死亡骑士', '6157', '70', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('6158', 'assassin 70', '刺客首领 70\r\n', '6158', '70', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('6159', 'general 70', '黑暗妖精将军 70\r\n', '6159', '70', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('6160', 'darkelf 70', '黑暗妖精', '6160', '70', '256', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('6180', 'unicorn A', '独角兽 A', '6180', '0', '751', '4095', '1', '10');
INSERT INTO `polymorphs` VALUES ('6181', 'unicorn B', '独角兽 B', '6181', '0', '751', '4095', '1', '10');
INSERT INTO `polymorphs` VALUES ('6182', 'unicorn C', '独角兽 C', '6182', '0', '751', '4095', '1', '10');
INSERT INTO `polymorphs` VALUES ('6183', 'unicorn D', '独角兽 D', '6183', '0', '0', '0', '0', '10');
INSERT INTO `polymorphs` VALUES ('6184', 'bear A', '熊 A', '6184', '0', '749', '4095', '1', '10');
INSERT INTO `polymorphs` VALUES ('6185', 'bear B', '熊 B', '6185', '0', '749', '4095', '1', '10');
INSERT INTO `polymorphs` VALUES ('6186', 'bear C', '熊 C', '6186', '0', '749', '4095', '1', '10');
INSERT INTO `polymorphs` VALUES ('6187', 'bear D', '熊 D', '6187', '0', '0', '0', '0', '10');
INSERT INTO `polymorphs` VALUES ('6188', 'mini white dog A', '高丽幼犬 A', '6188', '0', '751', '4095', '1', '10');
INSERT INTO `polymorphs` VALUES ('6189', 'mini white dog B', '高丽幼犬 B', '6189', '0', '751', '4095', '1', '10');
INSERT INTO `polymorphs` VALUES ('6190', 'mini white dog C', '高丽幼犬 C', '6190', '0', '751', '4095', '1', '10');
INSERT INTO `polymorphs` VALUES ('6191', 'mini white dog D', '高丽幼犬 D', '6191', '0', '0', '0', '0', '10');
INSERT INTO `polymorphs` VALUES ('6192', 'ratman A', '鼠人 A', '6192', '0', '751', '4095', '1', '10');
INSERT INTO `polymorphs` VALUES ('6193', 'ratman B', '鼠人 B', '6193', '0', '751', '4095', '1', '10');
INSERT INTO `polymorphs` VALUES ('6194', 'ratman C', '鼠人 C', '6194', '0', '751', '4095', '1', '10');
INSERT INTO `polymorphs` VALUES ('6195', 'ratman D', '鼠人 D', '6195', '0', '0', '0', '0', '10');
INSERT INTO `polymorphs` VALUES ('6196', 'pet tiger A', '老虎 A', '6196', '0', '751', '4095', '1', '10');
INSERT INTO `polymorphs` VALUES ('6197', 'pet tiger B', '老虎 B', '6197', '0', '751', '4095', '1', '10');
INSERT INTO `polymorphs` VALUES ('6198', 'pet tiger C', '老虎 C', '6198', '0', '751', '4095', '1', '10');
INSERT INTO `polymorphs` VALUES ('6199', 'pet tiger D', '老虎 D', '6199', '0', '0', '0', '0', '10');
INSERT INTO `polymorphs` VALUES ('6200', 'dillo A', '犰狳 A', '6200', '0', '751', '4095', '1', '10');
INSERT INTO `polymorphs` VALUES ('6201', 'dillo B', '犰狳 B', '6201', '0', '751', '4095', '1', '10');
INSERT INTO `polymorphs` VALUES ('6202', 'dillo C', '犰狳 C', '6202', '0', '751', '4095', '1', '10');
INSERT INTO `polymorphs` VALUES ('6203', 'dillo D', '犰狳 D', '6203', '0', '0', '0', '0', '10');
INSERT INTO `polymorphs` VALUES ('6204', 'mole A', '鼹鼠 A', '6204', '0', '256', '4095', '1', '10');
INSERT INTO `polymorphs` VALUES ('6205', 'mole B', '鼹鼠 B', '6205', '0', '256', '4095', '1', '10');
INSERT INTO `polymorphs` VALUES ('6206', 'mole C', '鼹鼠 C', '6206', '0', '256', '4095', '1', '10');
INSERT INTO `polymorphs` VALUES ('6207', 'mole D', '鼹鼠 D', '6207', '0', '0', '0', '0', '10');
INSERT INTO `polymorphs` VALUES ('6208', 'darkelf thief A', '黑暗妖精残兵 (盗贼) A', '6208', '0', '1007', '4095', '1', '10');
INSERT INTO `polymorphs` VALUES ('6209', 'darkelf thief B', '黑暗妖精残兵 (盗贼) B', '6209', '0', '1007', '4095', '1', '10');
INSERT INTO `polymorphs` VALUES ('6210', 'darkelf thief C', '黑暗妖精残兵 (盗贼) C', '6210', '0', '1007', '4095', '1', '10');
INSERT INTO `polymorphs` VALUES ('6211', 'darkelf thief D', '黑暗妖精残兵 (盗贼) D', '6211', '0', '0', '0', '0', '10');
INSERT INTO `polymorphs` VALUES ('6212', 'ken lauhel A', '反王肯恩 A', '6212', '0', '751', '4095', '1', '10');
INSERT INTO `polymorphs` VALUES ('6213', 'ken lauhel B', '反王肯恩 B', '6213', '0', '751', '4095', '1', '10');
INSERT INTO `polymorphs` VALUES ('6214', 'ken lauhel C', '反王肯恩 C', '6214', '0', '751', '4095', '1', '10');
INSERT INTO `polymorphs` VALUES ('6215', 'ken lauhel D', '反王肯恩 D', '6215', '0', '0', '0', '0', '10');
INSERT INTO `polymorphs` VALUES ('6216', 'kelenis A', '赛尼斯 A', '6216', '0', '751', '4095', '1', '10');
INSERT INTO `polymorphs` VALUES ('6217', 'kelenis B', '赛尼斯 B', '6217', '0', '751', '4095', '1', '10');
INSERT INTO `polymorphs` VALUES ('6218', 'kelenis C', '赛尼斯 C', '6218', '0', '751', '4095', '1', '10');
INSERT INTO `polymorphs` VALUES ('6219', 'kelenis D', '赛尼斯 D', '6219', '0', '0', '0', '0', '10');
INSERT INTO `polymorphs` VALUES ('6220', 'slave A', '豪势 A', '6220', '0', '751', '4095', '1', '10');
INSERT INTO `polymorphs` VALUES ('6221', 'slave B', '豪势 B', '6221', '0', '751', '4095', '1', '10');
INSERT INTO `polymorphs` VALUES ('6222', 'slave C', '豪势 C', '6222', '0', '751', '4095', '1', '10');
INSERT INTO `polymorphs` VALUES ('6223', 'slave D', '豪势 D', '6223', '0', '0', '0', '0', '10');
INSERT INTO `polymorphs` VALUES ('6224', 'dofleganger boss A', '变形怪首领 A', '6224', '0', '751', '4095', '1', '10');
INSERT INTO `polymorphs` VALUES ('6225', 'dofleganger boss B', '变形怪首领 B', '6225', '0', '751', '4095', '1', '10');
INSERT INTO `polymorphs` VALUES ('6226', 'dofleganger boss C', '变形怪首领 C', '6226', '0', '751', '4095', '1', '10');
INSERT INTO `polymorphs` VALUES ('6227', 'dofleganger boss D', '变形怪首领 D', '6227', '0', '0', '0', '0', '10');
INSERT INTO `polymorphs` VALUES ('6228', 'lich A', '巫妖 A', '6228', '0', '751', '4095', '1', '10');
INSERT INTO `polymorphs` VALUES ('6229', 'lich B', '巫妖 B', '6229', '0', '751', '4095', '1', '10');
INSERT INTO `polymorphs` VALUES ('6230', 'lich C', '巫妖 C', '6230', '0', '751', '4095', '1', '10');
INSERT INTO `polymorphs` VALUES ('6231', 'lich D', '巫妖 D', '6231', '0', '0', '0', '0', '10');
INSERT INTO `polymorphs` VALUES ('6232', 'woman1 A', 'woman1 A', '6232', '0', '0', '4095', '0', '10');
INSERT INTO `polymorphs` VALUES ('6233', 'woman1 B', 'woman1 B', '6233', '0', '0', '0', '0', '10');
INSERT INTO `polymorphs` VALUES ('6234', 'woman2 A', 'woman2 A', '6234', '0', '0', '4095', '0', '10');
INSERT INTO `polymorphs` VALUES ('6235', 'woman2 B', 'woman2 B', '6235', '0', '0', '0', '0', '10');
INSERT INTO `polymorphs` VALUES ('6236', 'woman3 A', 'woman3 A', '6236', '0', '0', '4095', '0', '10');
INSERT INTO `polymorphs` VALUES ('6237', 'woman3 B', 'woman3 B', '6237', '0', '0', '0', '0', '10');
INSERT INTO `polymorphs` VALUES ('6238', 'woman4 A', 'woman4 A', '6238', '0', '0', '4095', '0', '10');
INSERT INTO `polymorphs` VALUES ('6239', 'woman4 B', 'woman4 B', '6239', '0', '0', '0', '0', '10');
INSERT INTO `polymorphs` VALUES ('6240', 'woman5 A', 'woman5 A', '6240', '0', '0', '4095', '0', '10');
INSERT INTO `polymorphs` VALUES ('6241', 'woman5 B', 'woman5 B', '6241', '0', '0', '0', '0', '10');
INSERT INTO `polymorphs` VALUES ('6242', 'noblewoman A', 'noblewoman A', '6242', '0', '0', '4095', '0', '10');
INSERT INTO `polymorphs` VALUES ('6243', 'noblewoman B', 'noblewoman B', '6243', '0', '0', '0', '0', '10');
INSERT INTO `polymorphs` VALUES ('6267', 'neo black knight', '黑暗骑士', '6267', '55', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('6268', 'neo black mage', '黑暗法师', '6268', '55', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('6269', 'neo black scouter', '黑暗巡守', '6269', '55', '256', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('6270', 'neo silver knight', '银光骑士', '6270', '60', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('6271', 'neo silver mage', '银光法师', '6271', '60', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('6272', 'neo silver scouter', '银光巡守', '6272', '60', '256', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('6273', 'neo gold knight', '黄金骑士', '6273', '65', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('6274', 'neo gold mage', '黄金法师', '6274', '65', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('6275', 'neo gold scouter', '黄金巡守', '6275', '65', '256', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('6276', 'neo platinum knight', '白金骑士', '6276', '70', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('6277', 'neo platinum mage', '白金法师', '6277', '70', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('6278', 'neo platinum scouter', '白金巡守', '6278', '70', '256', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('6279', 'neo black assassin', '黑暗刺客', '6279', '55', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('6280', 'neo silver assassin', '银影', '6280', '60', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('6281', 'neo gold assassin', '黄金刺客', '6281', '65', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('6282', 'neo platinum assassin', '白影', '6282', '70', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('6284', 'Haunted House jack o lantern', '南瓜怪\r\n', '6284', '1', '0', '0', '0', '7');
INSERT INTO `polymorphs` VALUES ('6400', 'Halloween jack o lantern', '南瓜稻草人\r\n', '6400', '1', '2047', '4095', '1', '7');

INSERT INTO `polymorphs` VALUES ('7332', 'spearm 52', '狂暴将军', '7332', '52', '1080', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('7338', 'spearm 55', '狂暴将军', '7338', '55', '1080', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('7339', 'spearm 60', '狂暴将军', '7339', '60', '1080', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('7340', 'spearm 65', '狂暴将军', '7340', '65', '1080', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('7341', 'spearm 70', '狂暴将军', '7341', '70', '1080', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('8719', '柑橘', '柑橘', '8719', '1', '2047', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('8774', 'young kelenis', '赛尼斯', '8774', '75', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('8812', 'young gunter', '甘特', '8812', '80', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('8817', 'young ken lauel', '反王肯恩', '8817', '75', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('8851', 'young dantes', '丹特斯', '8851', '75', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('8900', 'young helvine', '海露拜', '8900', '75', '256', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('8913', 'young gilian', '丝莉安', '8913', '80', '256', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('8978', 'young bluedica', '布鲁迪卡', '8978', '80', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('9003', 'young joewoo', '宙斯', '9003', '80', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('9011', 'spearm 75', '狂暴将军', '9011', '75', '1080', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('9012', 'spearm 80', '狂暴将军', '9012', '80', '1080', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('9205', 'death 75', '死亡骑士', '9205', '75', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('9206', 'death 80', '死亡骑士', '9206', '80', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('9225', 'darkelf 75', '黑暗妖精', '9225', '75', '256', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('9226', 'darkelf 80', '黑暗妖精', '9226', '80', '256', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('8860', 're orc scout', '妖魔巡守', '8860', '1', '256', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('8859', 're bugbear', '食人妖精', '8859', '1', '751', '945', '1', '7');
INSERT INTO `polymorphs` VALUES ('2437', 're giant ant', '巨蚁', '2437', '1', '0', '136', '0', '7');
INSERT INTO `polymorphs` VALUES ('2438', 're giant ant soldier', '巨大兵蚁', '2438', '1', '0', '136', '0', '7');
INSERT INTO `polymorphs` VALUES ('8858', 're ghast', '卡司特', '8858', '1', '751', '945', '1', '7');
INSERT INTO `polymorphs` VALUES ('8865', 're lizardman', '蜥蜴人', '8865', '1', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('8866', 're kiwi parrot', '奇异鹦鹉', '8866', '1', '0', '954', '1', '7');
INSERT INTO `polymorphs` VALUES ('8868', 're wild fang', '狂野毒牙', '8868', '1', '0', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('8863', 're raccoon', '浣熊', '8863', '1', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('8864', 're owlbear', '欧熊', '8864', '1', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('8867', 're ratman', '鼠人', '8867', '1', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('8862', 're dragon fly', '龙蝇', '8862', '1', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('8861', 're succubus', '思克巴', '8861', '1', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('8781', 're guard sword', '贪欲的战士', '8781', '30', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('8782', 're darkelf carrier', '黑暗妖精运送员', '8782', '30', '1080', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('8783', 're darkelf assassin', '刺客', '8783', '30', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('8784', 're elder', '长者', '8784', '30', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('8785', 're orc axe', '妖魔 斧手', '8785', '30', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('8786', 're bandit bow', '强盗', '8786', '30', '256', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('8787', 're guard chief', '伯爵的亲卫队', '8787', '40', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('8788', 're guard spear', '贪欲的士兵', '8788', '40', '1080', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('8789', 're hose', '豪势', '8789', '40', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('8790', 're dark elder', '黑长者', '8790', '40', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('8791', 're bandit axe', '强盗', '8791', '40', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('8792', 're darkelf ranger', '黑暗妖精巡守', '8792', '40', '256', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('8793', 're cargo', '库曼', '8793', '45', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('8794', 're darkelf spear', '黑暗妖精警卫', '8794', '45', '1080', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('8795', 're blaze', '暗杀团长．布雷哲', '8795', '45', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('8796', 're black wizard', '黑法师', '8796', '45', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('8797', 're dwarf boss', '侏儒族将军', '8797', '45', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('8798', 're darkelf thief', '黑暗妖精盗贼', '8798', '45', '256', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('8799', 're black knight chief', '克特', '8799', '50', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('8800', 're black knight', '黑骑士', '8800', '50', '1080', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('8801', 're assassin master', '刺客首领', '8801', '50', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('8802', 're baphomet', '巴风特', '8802', '50', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('8803', 're minotaur', '巨斧牛人', '8803', '50', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('8804', 're darkelf guard', '黑暗妖精警卫', '8804', '50', '256', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('8805', 're dreik', '飞龙', '8805', '52', '192', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('8806', 're xinclair', '魔兽师长．辛克莱', '8806', '52', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('8807', 're great minotaur', '巨大牛人', '8807', '52', '8', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('8808', 're darkelf bow', '黑暗妖精', '8808', '52', '256', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('365', 'poly platinum knight morph', '光圈白金骑士', '365', '100', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('367', 'poly platinum mage morph', '光圈白金法师', '367', '100', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('369', 'poly platinum assassin morph', '光圈白金刺客', '369', '100', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('371', 'poly platinum scouter morph', '光圈白金巡守', '371', '100', '256', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('363', 'poly god knight morph', '光圈荒神死亡骑士', '363', '100', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('9362', '觉醒：安塔瑞斯', '9362', '1', '1054', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('9363', '觉醒：巴拉卡斯', '9363', '1', '1054', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('9364', '觉醒：法力昂', '9364', '1', '1054', '4095', '1', '7');
