/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50524
Source Host           : localhost:3306
Source Database       : l1jcn

Target Server Type    : MYSQL
Target Server Version : 50524
File Encoding         : 65001

Date: 2012-09-22 19:59:56
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `william_auto_add_skill`
-- ----------------------------
DROP TABLE IF EXISTS `william_auto_add_skill`;
CREATE TABLE `william_auto_add_skill` (
  `Id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `注解` varchar(1000) DEFAULT NULL,
  `Level` int(2) NOT NULL DEFAULT '1',
  `SkillId` varchar(1000) DEFAULT NULL,
  `Crown` int(1) unsigned NOT NULL DEFAULT '0',
  `Knight` int(1) unsigned NOT NULL DEFAULT '0',
  `Wizard` int(1) unsigned NOT NULL DEFAULT '0',
  `Elf` int(1) unsigned NOT NULL DEFAULT '0',
  `Darkelf` int(1) unsigned NOT NULL DEFAULT '0',
  `DragonKnight` int(1) unsigned NOT NULL DEFAULT '0',
  `Illusionist` int(1) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`Id`)
) ENGINE=MyISAM AUTO_INCREMENT=201 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of william_auto_add_skill
-- ----------------------------
INSERT INTO `william_auto_add_skill` VALUES ('1', '法师 - 第一级魔法', '4', '1,2,3,4,5,6,7,8', '0', '0', '1', '0', '0', '0', '0');
INSERT INTO `william_auto_add_skill` VALUES ('2', '法师 - 第二级魔法', '8', '9,10,11,12,13,14,15,16', '0', '0', '1', '0', '0', '0', '0');
INSERT INTO `william_auto_add_skill` VALUES ('3', '法师 - 第三级魔法', '12', '17,18,19,20,21,22,23', '0', '0', '1', '0', '0', '0', '0');
INSERT INTO `william_auto_add_skill` VALUES ('4', '法师 - 第四级魔法', '16', '25,26,27,28,29,30,31,32', '0', '0', '1', '0', '0', '0', '0');
INSERT INTO `william_auto_add_skill` VALUES ('5', '法师 - 第五级魔法', '20', '33,34,35,36,37,38,39,40', '0', '0', '1', '0', '0', '0', '0');
INSERT INTO `william_auto_add_skill` VALUES ('6', '法师 - 第六级魔法', '24', '41,42,43,44,45,46,47,48', '0', '0', '1', '0', '0', '0', '0');
INSERT INTO `william_auto_add_skill` VALUES ('7', '法师 - 第七级魔法', '28', '49,50,51,52,53,54,55,56', '0', '0', '1', '0', '0', '0', '0');
INSERT INTO `william_auto_add_skill` VALUES ('8', '法师 - 第八级魔法', '32', '57,59,61,62,,63,65,66', '0', '0', '1', '0', '0', '0', '0');
INSERT INTO `william_auto_add_skill` VALUES ('9', '王族 - 第一级魔法', '10', '1,2,3,4,5,6,7,8', '1', '0', '0', '0', '0', '0', '0');
INSERT INTO `william_auto_add_skill` VALUES ('10', '王族 - 第二级魔法', '20', '9,10,11,12,13,14,15,16', '1', '0', '0', '0', '0', '0', '0');
INSERT INTO `william_auto_add_skill` VALUES ('11', '王族 - 精准目标', '15', '113', '1', '0', '0', '0', '0', '0', '0');
INSERT INTO `william_auto_add_skill` VALUES ('12', '王族 - 激励士气', '40', '114', '1', '0', '0', '0', '0', '0', '0');
INSERT INTO `william_auto_add_skill` VALUES ('13', '王族 - 钢铁士气', '55', '115', '1', '0', '0', '0', '0', '0', '0');
INSERT INTO `william_auto_add_skill` VALUES ('14', '王族 - 呼唤盟友', '30', '116', '1', '0', '0', '0', '0', '0', '0');
INSERT INTO `william_auto_add_skill` VALUES ('15', '王族 - 冲击士气', '50', '117', '1', '0', '0', '0', '0', '0', '0');
INSERT INTO `william_auto_add_skill` VALUES ('16', '王族 - 援护盟友', '45', '118', '1', '0', '0', '0', '0', '0', '0');
INSERT INTO `william_auto_add_skill` VALUES ('17', '骑士 - 第一级魔法', '50', '1,2,3,4,5,6,7,8', '0', '1', '0', '0', '0', '0', '0');
INSERT INTO `william_auto_add_skill` VALUES ('18', '骑士 - 特殊级技能', '50', '87,88,90', '0', '1', '0', '0', '0', '0', '0');
INSERT INTO `william_auto_add_skill` VALUES ('19', '骑士 - 特殊级技能 - 尖刺盔甲', '60', '89', '0', '1', '0', '0', '0', '0', '0');
INSERT INTO `william_auto_add_skill` VALUES ('20', '妖精 - 第一级魔法', '8', '1,2,3,4,5,6,7,8', '0', '0', '0', '1', '0', '0', '0');
INSERT INTO `william_auto_add_skill` VALUES ('21', '妖精 - 第二级魔法', '16', '9,10,11,12,13,14,15,16', '0', '0', '0', '1', '0', '0', '0');
INSERT INTO `william_auto_add_skill` VALUES ('22', '妖精 - 第三级魔法', '24', '17,18,19,20,21,22,23', '0', '0', '0', '1', '0', '0', '0');
INSERT INTO `william_auto_add_skill` VALUES ('23', '妖精 - 第四级魔法', '32', '25,26,27,28,29,30,31,32', '0', '0', '0', '1', '0', '0', '0');
INSERT INTO `william_auto_add_skill` VALUES ('24', '妖精 - 第五级魔法', '40', '33,34,35,36,37,38,39,40', '0', '0', '0', '1', '0', '0', '0');
INSERT INTO `william_auto_add_skill` VALUES ('25', '妖精 - 第六级魔法', '48', '41,42,43,44,45,46,47,48', '0', '0', '0', '1', '0', '0', '0');
INSERT INTO `william_auto_add_skill` VALUES ('26', '妖精 - 第一级技能', '10', '129,130,131', '0', '0', '0', '1', '0', '0', '0');
INSERT INTO `william_auto_add_skill` VALUES ('27', '妖精 - 第二级技能', '20', '137,138', '0', '0', '0', '1', '0', '0', '0');
INSERT INTO `william_auto_add_skill` VALUES ('28', '妖精 - 第三级技能', '30', '145', '0', '0', '0', '1', '0', '0', '0');
INSERT INTO `william_auto_add_skill` VALUES ('29', '妖精 - 第四级技能', '40', '133,154', '0', '0', '0', '1', '0', '0', '0');
INSERT INTO `william_auto_add_skill` VALUES ('30', '妖精 - 第五级技能', '50', '134,162', '0', '0', '0', '1', '0', '0', '0');
INSERT INTO `william_auto_add_skill` VALUES ('31', '黑暗妖精 - 第一级魔法', '12', '1,2,3,4,5,6,7,8', '0', '0', '0', '0', '1', '0', '0');
INSERT INTO `william_auto_add_skill` VALUES ('32', '黑暗妖精 - 第二级魔法', '24', '9,10,11,12,13,14,15,16', '0', '0', '0', '0', '1', '0', '0');
INSERT INTO `william_auto_add_skill` VALUES ('33', '黑暗妖精 - 第一级技能', '15', '97,98,99,100', '0', '0', '0', '0', '1', '0', '0');
INSERT INTO `william_auto_add_skill` VALUES ('34', '黑暗妖精 - 第二级技能', '30', '101,102', '0', '0', '0', '0', '1', '0', '0');
INSERT INTO `william_auto_add_skill` VALUES ('35', '龙骑士 - 第一级技能', '15', '181,182,183,184,185', '0', '0', '0', '0', '0', '1', '0');
INSERT INTO `william_auto_add_skill` VALUES ('36', '龙骑士 - 第二级技能', '30', '186,190', '0', '0', '0', '0', '0', '1', '0');
INSERT INTO `william_auto_add_skill` VALUES ('37', '龙骑士 - 第三级技能', '45', '195', '0', '0', '0', '0', '0', '1', '0');
INSERT INTO `william_auto_add_skill` VALUES ('38', '幻术士 - 第一级技能', '10', '202,204', '0', '0', '0', '0', '0', '0', '1');
INSERT INTO `william_auto_add_skill` VALUES ('39', '幻术士 - 第二级技能', '20', '206,207,208,209', '0', '0', '0', '0', '0', '0', '1');
INSERT INTO `william_auto_add_skill` VALUES ('40', '幻术士 - 第三级技能', '30', '211,214', '0', '0', '0', '0', '0', '0', '1');
INSERT INTO `william_auto_add_skill` VALUES ('41', '幻术士 - 第四级技能', '40', '216,217,219', '0', '0', '0', '0', '0', '0', '1');

-- ----------------------------
-- Table structure for `william_item_magic`
-- ----------------------------
DROP TABLE IF EXISTS `william_item_magic`;
CREATE TABLE `william_item_magic` (
  `item_id` int(10) unsigned NOT NULL,
  `checkClass` int(1) unsigned NOT NULL DEFAULT '0',
  `checkItem` int(10) unsigned NOT NULL DEFAULT '0',
  `skill_id` int(10) unsigned NOT NULL,
  `removeItem` int(1) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of william_item_magic
-- ----------------------------

-- ----------------------------
-- Table structure for `william_item_price`
-- ----------------------------
DROP TABLE IF EXISTS `william_item_price`;
CREATE TABLE `william_item_price` (
  `item_id` int(10) unsigned NOT NULL DEFAULT '0',
  `name` varchar(45) DEFAULT NULL,
  `price` int(10) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of william_item_price
-- ----------------------------
INSERT INTO `william_item_price` VALUES ('100', '覆上奥里哈鲁根的角', '960');
INSERT INTO `william_item_price` VALUES ('101', '拉斯塔巴德矛', '1');
INSERT INTO `william_item_price` VALUES ('102', '露西锤', '110');
INSERT INTO `william_item_price` VALUES ('103', '戟', '2200');
INSERT INTO `william_item_price` VALUES ('104', '法丘', '8800');
INSERT INTO `william_item_price` VALUES ('105', '象牙塔长矛', '1');
INSERT INTO `william_item_price` VALUES ('106', '贝卡合金', '26600');
INSERT INTO `william_item_price` VALUES ('107', '深红长矛', '250');
INSERT INTO `william_item_price` VALUES ('108', '失去魔力的恶魔镰刀', '10');
INSERT INTO `william_item_price` VALUES ('109', '失去魔力的巴风特魔杖', '10');
INSERT INTO `william_item_price` VALUES ('110', '失去魔力的巴列斯魔杖', '10');
INSERT INTO `william_item_price` VALUES ('111', '失去魔力的冰之女王魔杖', '10');
INSERT INTO `william_item_price` VALUES ('115', '水晶魔杖', '200');
INSERT INTO `william_item_price` VALUES ('116', '黑法师之杖', '200');
INSERT INTO `william_item_price` VALUES ('117', '蕾雅魔杖', '4950');
INSERT INTO `william_item_price` VALUES ('118', '漆黑水晶球', '110');
INSERT INTO `william_item_price` VALUES ('119', '恶魔镰刀', '100');
INSERT INTO `william_item_price` VALUES ('120', '象牙塔魔杖', '10');
INSERT INTO `william_item_price` VALUES ('121', '冰之女王魔杖', '100');
INSERT INTO `william_item_price` VALUES ('122', '拉斯塔巴德魔杖', '14351');
INSERT INTO `william_item_price` VALUES ('123', '巴列斯魔杖', '1');
INSERT INTO `william_item_price` VALUES ('124', '巴风特魔杖', '10');
INSERT INTO `william_item_price` VALUES ('125', '巫术魔法杖', '2500');
INSERT INTO `william_item_price` VALUES ('126', '玛那魔杖', '10');
INSERT INTO `william_item_price` VALUES ('127', '钢铁玛那魔杖', '10');
INSERT INTO `william_item_price` VALUES ('128', '橡木魔法杖', '119');
INSERT INTO `william_item_price` VALUES ('129', '美基魔法杖', '30');
INSERT INTO `william_item_price` VALUES ('130', '红水晶魔杖', '2500');
INSERT INTO `william_item_price` VALUES ('131', '力量魔法杖', '330');
INSERT INTO `william_item_price` VALUES ('132', '神官魔杖', '2750');
INSERT INTO `william_item_price` VALUES ('133', '古代人的智慧', '150');
INSERT INTO `william_item_price` VALUES ('134', '圣晶魔杖', '150');
INSERT INTO `william_item_price` VALUES ('135', '不为人知的斧', '30');
INSERT INTO `william_item_price` VALUES ('136', '斧', '1');
INSERT INTO `william_item_price` VALUES ('137', '亚连', '1');
INSERT INTO `william_item_price` VALUES ('138', '木棒', '1');
INSERT INTO `william_item_price` VALUES ('139', '弗莱尔', '22');
INSERT INTO `william_item_price` VALUES ('140', '钉锤', '17');
INSERT INTO `william_item_price` VALUES ('141', '战锤', '33');
INSERT INTO `william_item_price` VALUES ('142', '银斧', '44');
INSERT INTO `william_item_price` VALUES ('143', '战斧', '99');
INSERT INTO `william_item_price` VALUES ('144', '侏儒铁斧', '500');
INSERT INTO `william_item_price` VALUES ('145', '狂战士斧', '500');
INSERT INTO `william_item_price` VALUES ('146', '流星锤', '770');
INSERT INTO `william_item_price` VALUES ('147', '象牙塔斧头', '500');
INSERT INTO `william_item_price` VALUES ('148', '巨斧', '50');
INSERT INTO `william_item_price` VALUES ('149', '牛人斧头', '3850');
INSERT INTO `william_item_price` VALUES ('150', '天父之怒', '1');
INSERT INTO `william_item_price` VALUES ('151', '恶魔斧头', '14300');
INSERT INTO `william_item_price` VALUES ('152', '青铜钢爪', '4800');
INSERT INTO `william_item_price` VALUES ('153', '钢铁钢爪', '1470');
INSERT INTO `william_item_price` VALUES ('154', '暗影钢爪', '100');
INSERT INTO `william_item_price` VALUES ('155', '魔兽军王之爪', '10');
INSERT INTO `william_item_price` VALUES ('156', '象牙塔钢爪', '2');
INSERT INTO `william_item_price` VALUES ('157', '银光钢爪', '2500');
INSERT INTO `william_item_price` VALUES ('158', '黑暗钢爪', '150');
INSERT INTO `william_item_price` VALUES ('159', '短钢爪', '1');
INSERT INTO `william_item_price` VALUES ('160', '兽王钢爪', '30');
INSERT INTO `william_item_price` VALUES ('161', '大马士革钢爪', '10');
INSERT INTO `william_item_price` VALUES ('162', '幽暗钢爪', '1500');
INSERT INTO `william_item_price` VALUES ('163', '巴兰卡钢爪', '1500');
INSERT INTO `william_item_price` VALUES ('164', '暗黑钢爪', '1500');
INSERT INTO `william_item_price` VALUES ('165', '恶魔钢爪', '1500');
INSERT INTO `william_item_price` VALUES ('166', '恨之钢爪', '2500');
INSERT INTO `william_item_price` VALUES ('167', '受封印 被遗忘的弩枪', '1200');
INSERT INTO `william_item_price` VALUES ('168', '黑暗十字弓', '20');
INSERT INTO `william_item_price` VALUES ('169', '猎人之弓', '2500');
INSERT INTO `william_item_price` VALUES ('170', '精灵弓', '1');
INSERT INTO `william_item_price` VALUES ('171', '欧西斯弓', '1');
INSERT INTO `william_item_price` VALUES ('172', '弓', '1');
INSERT INTO `william_item_price` VALUES ('173', '短弓', '1');
INSERT INTO `william_item_price` VALUES ('174', '象牙塔石弓', '1');
INSERT INTO `william_item_price` VALUES ('175', '象牙塔长弓', '1');
INSERT INTO `william_item_price` VALUES ('176', '拉斯塔巴德弓', '1');
INSERT INTO `william_item_price` VALUES ('177', '幽暗十字弓', '1');
INSERT INTO `william_item_price` VALUES ('178', '寂静十字弓', '1');
INSERT INTO `william_item_price` VALUES ('179', '古代妖精弩枪', '9500');
INSERT INTO `william_item_price` VALUES ('180', '十字弓', '2500');
INSERT INTO `william_item_price` VALUES ('181', '尤米弓', '30');
INSERT INTO `william_item_price` VALUES ('182', '古老的弩枪', '100');
INSERT INTO `william_item_price` VALUES ('183', '幻象之弓', '1');
INSERT INTO `william_item_price` VALUES ('184', '赤焰之弓', '30');
INSERT INTO `william_item_price` VALUES ('185', '恶魔十字弓', '3500');
INSERT INTO `william_item_price` VALUES ('186', '狄亚得十字弓', '2500');
INSERT INTO `william_item_price` VALUES ('187', '拉斯塔巴德十字弓', '100');
INSERT INTO `william_item_price` VALUES ('188', '拉斯塔巴德重十字弓', '100');
INSERT INTO `william_item_price` VALUES ('189', '暗黑十字弓', '100');
INSERT INTO `william_item_price` VALUES ('190', '沙哈之弓', '1');
INSERT INTO `william_item_price` VALUES ('191', '水之竖琴', '1');
INSERT INTO `william_item_price` VALUES ('192', '水精灵之弓', '1');
INSERT INTO `william_item_price` VALUES ('193', '铁手甲', '1');
INSERT INTO `william_item_price` VALUES ('194', '真铁手甲', '1');
INSERT INTO `william_item_price` VALUES ('206', '黑暗精灵之剑', '1');
INSERT INTO `william_item_price` VALUES ('208', '魔法精灵短剑', '1');
INSERT INTO `william_item_price` VALUES ('209', '体质精灵短剑', '10');
INSERT INTO `william_item_price` VALUES ('211', '独角兽之角', '2');
INSERT INTO `william_item_price` VALUES ('214', 'ID．妖精弓', '2');
INSERT INTO `william_item_price` VALUES ('215', '敏捷精灵弓', '2');
INSERT INTO `william_item_price` VALUES ('216', '妖精之弓', '2');
INSERT INTO `william_item_price` VALUES ('219', '马普勒的惩罚', '2');
INSERT INTO `william_item_price` VALUES ('220', '法师之杖', '2');
INSERT INTO `william_item_price` VALUES ('223', '神秘魔杖', '2');
INSERT INTO `william_item_price` VALUES ('224', '象牙塔魔杖', '2');
INSERT INTO `william_item_price` VALUES ('225', '王族之剑', '2');
INSERT INTO `william_item_price` VALUES ('226', '骑士之剑', '2');
INSERT INTO `william_item_price` VALUES ('228', '圣战士之剑', '0');
INSERT INTO `william_item_price` VALUES ('230', '屠杀的血色巨剑', '0');
INSERT INTO `william_item_price` VALUES ('20003', '钢铁头盔', '2500');
INSERT INTO `william_item_price` VALUES ('20006', '骑士面甲', '2500');
INSERT INTO `william_item_price` VALUES ('20011', '抗魔法头盔', '1500');
INSERT INTO `william_item_price` VALUES ('20013', '敏捷魔法头盔', '110');
INSERT INTO `william_item_price` VALUES ('20014', '治愈魔法头盔', '7500');
INSERT INTO `william_item_price` VALUES ('20015', '力量魔法头盔', '90');
INSERT INTO `william_item_price` VALUES ('20018', '马库尔之帽', '50');
INSERT INTO `william_item_price` VALUES ('20019', '王冠', '10');
INSERT INTO `william_item_price` VALUES ('20020', '武官头盔', '150');
INSERT INTO `william_item_price` VALUES ('20021', '精灵敏捷头盔', '1');
INSERT INTO `william_item_price` VALUES ('20022', '巴兰卡头盔', '2500');
INSERT INTO `william_item_price` VALUES ('20023', '风之头盔', '2500');
INSERT INTO `william_item_price` VALUES ('20024', '反王头盔', '2500');
INSERT INTO `william_item_price` VALUES ('20025', '巴土瑟之帽', '2500');
INSERT INTO `william_item_price` VALUES ('20026', '夜之视野', '110');
INSERT INTO `william_item_price` VALUES ('20027', '红骑士头巾', '2500');
INSERT INTO `william_item_price` VALUES ('20028', '象牙塔皮头盔', '0');
INSERT INTO `william_item_price` VALUES ('20029', '西玛之帽', '2500');
INSERT INTO `william_item_price` VALUES ('20030', '神官头饰', '30');
INSERT INTO `william_item_price` VALUES ('20031', '火焰之影头盔', '2500');
INSERT INTO `william_item_price` VALUES ('20032', '黑暗头饰', '2500');
INSERT INTO `william_item_price` VALUES ('20033', '艾尔穆的祝福', '50');
INSERT INTO `william_item_price` VALUES ('20034', '欧西斯头盔', '10');
INSERT INTO `william_item_price` VALUES ('20035', '精灵皮盔', '30');
INSERT INTO `william_item_price` VALUES ('20036', '夜视头盔', '6700');
INSERT INTO `william_item_price` VALUES ('20037', '真实的面具', '30');
INSERT INTO `william_item_price` VALUES ('20038', '银钉皮帽', '0');
INSERT INTO `william_item_price` VALUES ('20039', '精灵体质头盔', '30');
INSERT INTO `william_item_price` VALUES ('20040', '卡士伯之帽', '2500');
INSERT INTO `william_item_price` VALUES ('20041', '克特头盔', '150');
INSERT INTO `william_item_price` VALUES ('20042', '赛尼斯头箍', '150');
INSERT INTO `william_item_price` VALUES ('20043', '钢盔', '100');
INSERT INTO `william_item_price` VALUES ('20044', '蓝海贼头巾', '150');
INSERT INTO `william_item_price` VALUES ('20045', '骷髅头盔', '0');
INSERT INTO `william_item_price` VALUES ('20046', '南瓜帽', '0');
INSERT INTO `william_item_price` VALUES ('20047', '南瓜头套', '0');
INSERT INTO `william_item_price` VALUES ('20048', '混沌头盔', '10');
INSERT INTO `william_item_price` VALUES ('20049', '巨蚁女皇的金翅膀', '10');
INSERT INTO `william_item_price` VALUES ('20050', '巨蚁女皇的银翅膀', '10');
INSERT INTO `william_item_price` VALUES ('20051', '君主的威严', '2500');
INSERT INTO `william_item_price` VALUES ('20052', '侏儒斗篷', '0');
INSERT INTO `william_item_price` VALUES ('20053', '狼皮斗篷', '0');
INSERT INTO `william_item_price` VALUES ('20054', '地属性斗篷', '250');
INSERT INTO `william_item_price` VALUES ('20055', '玛那斗篷', '2500');
INSERT INTO `william_item_price` VALUES ('20056', '抗魔法斗篷', '200');
INSERT INTO `william_item_price` VALUES ('20057', '冥法军王斗篷', '250');
INSERT INTO `william_item_price` VALUES ('20058', '武官斗篷', '250');
INSERT INTO `william_item_price` VALUES ('20059', '水属性斗篷', '250');
INSERT INTO `william_item_price` VALUES ('20060', '蓝海贼斗篷', '150');
INSERT INTO `william_item_price` VALUES ('20061', '风属性斗篷', '150');
INSERT INTO `william_item_price` VALUES ('20062', '炎魔的血光斗篷', '350');
INSERT INTO `william_item_price` VALUES ('20063', '保护者斗篷', '150');
INSERT INTO `william_item_price` VALUES ('20064', '红骑士之斗篷', '20');
INSERT INTO `william_item_price` VALUES ('20065', '红色斗篷', '10');
INSERT INTO `william_item_price` VALUES ('20066', '黑虎皮斗篷', '10');
INSERT INTO `william_item_price` VALUES ('20067', '神官斗篷', '1500');
INSERT INTO `william_item_price` VALUES ('20068', '亚丁骑士团披肩', '150');
INSERT INTO `william_item_price` VALUES ('20069', '火焰之影斗篷', '100');
INSERT INTO `william_item_price` VALUES ('20070', '黑暗斗篷', '200');
INSERT INTO `william_item_price` VALUES ('20071', '火属性斗篷', '150');
INSERT INTO `william_item_price` VALUES ('20072', '欧西斯斗篷', '10');
INSERT INTO `william_item_price` VALUES ('20073', '精灵斗篷', '500');
INSERT INTO `william_item_price` VALUES ('20074', '银光斗篷', '0');
INSERT INTO `william_item_price` VALUES ('20075', '死亡斗篷', '0');
INSERT INTO `william_item_price` VALUES ('20076', '堕落斗篷', '250');
INSERT INTO `william_item_price` VALUES ('20077', '隐身斗篷', '150');
INSERT INTO `william_item_price` VALUES ('20078', '混沌斗篷', '250');
INSERT INTO `william_item_price` VALUES ('20079', '吸血鬼斗篷', '250');
INSERT INTO `william_item_price` VALUES ('20080', '位移斗篷', '50');
INSERT INTO `william_item_price` VALUES ('20081', '油布斗篷', '400');
INSERT INTO `william_item_price` VALUES ('20082', '象牙塔T恤', '0');
INSERT INTO `william_item_price` VALUES ('20083', '火焰之影衬衫', '10');
INSERT INTO `william_item_price` VALUES ('20084', '精灵T恤', '1300');
INSERT INTO `william_item_price` VALUES ('20085', 'T恤', '500');
INSERT INTO `william_item_price` VALUES ('20086', '智力T恤', '500');
INSERT INTO `william_item_price` VALUES ('20087', '敏捷T恤', '500');
INSERT INTO `william_item_price` VALUES ('20088', '力量T恤', '500');
INSERT INTO `william_item_price` VALUES ('20089', '小藤甲', '30');
INSERT INTO `william_item_price` VALUES ('20090', '皮背心', '342');
INSERT INTO `william_item_price` VALUES ('20091', '钢铁金属盔甲', '350');
INSERT INTO `william_item_price` VALUES ('20092', '古老的皮盔甲', '150');
INSERT INTO `william_item_price` VALUES ('20093', '古老的长袍', '150');
INSERT INTO `william_item_price` VALUES ('20094', '古老的鳞甲', '150');
INSERT INTO `william_item_price` VALUES ('20095', '古老的金属盔甲', '150');
INSERT INTO `william_item_price` VALUES ('20096', '环甲', '2500');
INSERT INTO `william_item_price` VALUES ('20097', '木甲', '600');
INSERT INTO `william_item_price` VALUES ('20098', '黑暗栖林者盔甲', '10');
INSERT INTO `william_item_price` VALUES ('20099', '恶魔盔甲', '2500');
INSERT INTO `william_item_price` VALUES ('20100', '死亡骑士盔甲', '50');
INSERT INTO `william_item_price` VALUES ('20101', '皮甲', '6250');
INSERT INTO `william_item_price` VALUES ('20102', '拉斯塔巴德皮盔甲', '750');
INSERT INTO `william_item_price` VALUES ('20103', '拉斯塔巴德长袍', '750');
INSERT INTO `william_item_price` VALUES ('20104', '拉斯塔巴德银钉皮盔甲', '500');
INSERT INTO `william_item_price` VALUES ('20105', '拉斯塔巴德链甲', '2500');
INSERT INTO `william_item_price` VALUES ('20106', '蕾雅长袍', '1');
INSERT INTO `william_item_price` VALUES ('20107', '巫妖斗篷', '60');
INSERT INTO `william_item_price` VALUES ('20108', '古代风龙鳞盔甲', '100');
INSERT INTO `william_item_price` VALUES ('20109', '法令军王长袍', '100');
INSERT INTO `william_item_price` VALUES ('20110', '抗魔法链甲', '40');
INSERT INTO `william_item_price` VALUES ('20111', '法师长袍', '2500');
INSERT INTO `william_item_price` VALUES ('20112', '曼波外套', '150');
INSERT INTO `william_item_price` VALUES ('20113', '武官护铠', '150');
INSERT INTO `william_item_price` VALUES ('20114', '绵质长袍', '500');
INSERT INTO `william_item_price` VALUES ('20115', '藤甲', '100');
INSERT INTO `william_item_price` VALUES ('20116', '巴兰卡盔甲', '250');
INSERT INTO `william_item_price` VALUES ('20117', '巴风特盔甲', '250');
INSERT INTO `william_item_price` VALUES ('20118', '反王盔甲', '250');
INSERT INTO `william_item_price` VALUES ('20119', '古代火龙鳞盔甲', '250');
INSERT INTO `william_item_price` VALUES ('20120', '皮盔甲', '10');
INSERT INTO `william_item_price` VALUES ('20121', '黑法师长袍', '0');
INSERT INTO `william_item_price` VALUES ('20122', '鳞甲', '100');
INSERT INTO `william_item_price` VALUES ('20123', '唤兽师长袍', '10');
INSERT INTO `william_item_price` VALUES ('20124', '骷髅盔甲', '0');
INSERT INTO `william_item_price` VALUES ('20125', '链甲', '30');
INSERT INTO `william_item_price` VALUES ('20126', '象牙塔皮盔甲', '0');
INSERT INTO `william_item_price` VALUES ('20127', '水龙鳞盔甲', '150');
INSERT INTO `william_item_price` VALUES ('20128', '水晶盔甲', '3500');
INSERT INTO `william_item_price` VALUES ('20129', '神官法袍', '150');
INSERT INTO `william_item_price` VALUES ('20130', '古代地龙鳞盔甲', '150');
INSERT INTO `william_item_price` VALUES ('20131', '火焰之影盔甲', '150');
INSERT INTO `william_item_price` VALUES ('20132', '黑暗披肩', '450');
INSERT INTO `william_item_price` VALUES ('20133', '黑暗执行者金属盔甲', '50');
INSERT INTO `william_item_price` VALUES ('20134', '冰之女王魅力礼服', '50');
INSERT INTO `william_item_price` VALUES ('20135', '欧西斯环甲', '100');
INSERT INTO `william_item_price` VALUES ('20136', '欧西斯链甲', '400');
INSERT INTO `william_item_price` VALUES ('20137', '精灵链甲', '30');
INSERT INTO `william_item_price` VALUES ('20138', '精灵金属盔甲', '90');
INSERT INTO `william_item_price` VALUES ('20139', '精灵护胸金属板', '1040');
INSERT INTO `william_item_price` VALUES ('20140', '被遗忘的皮盔甲', '0');
INSERT INTO `william_item_price` VALUES ('20141', '被遗忘的长袍', '0');
INSERT INTO `william_item_price` VALUES ('20142', '被遗忘的鳞甲', '0');
INSERT INTO `william_item_price` VALUES ('20143', '被遗忘的金属盔甲', '0');
INSERT INTO `william_item_price` VALUES ('20144', '死亡盔甲', '50');
INSERT INTO `william_item_price` VALUES ('20145', '硬皮背心', '1422');
INSERT INTO `william_item_price` VALUES ('20146', '地龙鳞盔甲', '150');
INSERT INTO `william_item_price` VALUES ('20147', '银钉皮甲', '150');
INSERT INTO `william_item_price` VALUES ('20148', '银钉皮背心', '1020');
INSERT INTO `william_item_price` VALUES ('20149', '青铜盔甲', '80');
INSERT INTO `william_item_price` VALUES ('20150', '克特盔甲', '150');
INSERT INTO `william_item_price` VALUES ('20151', '赛尼斯斗篷', '150');
INSERT INTO `william_item_price` VALUES ('20152', '堕落长袍', '150');
INSERT INTO `william_item_price` VALUES ('20153', '古代水龙鳞盔甲', '150');
INSERT INTO `william_item_price` VALUES ('20154', '金属盔甲', '18500');
INSERT INTO `william_item_price` VALUES ('20155', '蓝海贼皮盔甲', '1500');
INSERT INTO `william_item_price` VALUES ('20156', '风龙鳞盔甲', '150');
INSERT INTO `william_item_price` VALUES ('20157', '金属蜈蚣皮盔甲', '50');
INSERT INTO `william_item_price` VALUES ('20158', '混沌法袍', '150');
INSERT INTO `william_item_price` VALUES ('20159', '火龙鳞盔甲', '150');
INSERT INTO `william_item_price` VALUES ('20160', '黑长者长袍', '150');
INSERT INTO `william_item_price` VALUES ('20161', '幻象盔甲', '150');
INSERT INTO `william_item_price` VALUES ('20162', '皮手套', '200');
INSERT INTO `william_item_price` VALUES ('20163', '钢铁手套', '250');
INSERT INTO `william_item_price` VALUES ('20164', '影子手套', '2500');
INSERT INTO `william_item_price` VALUES ('20165', '恶魔手套', '2555');
INSERT INTO `william_item_price` VALUES ('20166', '死亡骑士手套', '50');
INSERT INTO `william_item_price` VALUES ('20167', '蜥蜴王手套', '2500');
INSERT INTO `william_item_price` VALUES ('20168', '武官手套', '150');
INSERT INTO `william_item_price` VALUES ('20169', '巴兰卡手套', '50');
INSERT INTO `william_item_price` VALUES ('20170', '反王手套', '150');
INSERT INTO `william_item_price` VALUES ('20171', '保护者手套', '4500');
INSERT INTO `william_item_price` VALUES ('20172', '水灵手套', '2500');
INSERT INTO `william_item_price` VALUES ('20173', '象牙塔皮手套', '0');
INSERT INTO `william_item_price` VALUES ('20174', '雪人手套', '50');
INSERT INTO `william_item_price` VALUES ('20175', '水晶手套', '2500');
INSERT INTO `william_item_price` VALUES ('20176', '神官手套', '150');
INSERT INTO `william_item_price` VALUES ('20177', '地灵手套', '150');
INSERT INTO `william_item_price` VALUES ('20178', '暗杀军王手套', '100');
INSERT INTO `william_item_price` VALUES ('20179', '火焰之影手套', '100');
INSERT INTO `william_item_price` VALUES ('20180', '黑暗手套', '100');
INSERT INTO `william_item_price` VALUES ('20181', '火灵手套', '100');
INSERT INTO `william_item_price` VALUES ('20182', '手套', '1500');
INSERT INTO `william_item_price` VALUES ('20183', '死亡手套', '100');
INSERT INTO `william_item_price` VALUES ('20184', '克特手套', '150');
INSERT INTO `william_item_price` VALUES ('20185', '赛尼斯手套', '150');
INSERT INTO `william_item_price` VALUES ('20186', '堕落手套', '150');
INSERT INTO `william_item_price` VALUES ('20187', '权力的长手套', '150');
INSERT INTO `william_item_price` VALUES ('20188', '蓝海贼手套', '1500');
INSERT INTO `william_item_price` VALUES ('20189', '风灵手套', '2500');
INSERT INTO `william_item_price` VALUES ('20190', '混沌手套', '2351');
INSERT INTO `william_item_price` VALUES ('20191', '腕甲', '3500');
INSERT INTO `william_item_price` VALUES ('20192', '皮长靴', '10');
INSERT INTO `william_item_price` VALUES ('20193', '皮凉鞋', '200');
INSERT INTO `william_item_price` VALUES ('20194', '钢铁长靴', '6800');
INSERT INTO `william_item_price` VALUES ('20195', '影子长靴', '2500');
INSERT INTO `william_item_price` VALUES ('20196', '黑暗栖林者长靴', '100');
INSERT INTO `william_item_price` VALUES ('20197', '恶魔长靴', '100');
INSERT INTO `william_item_price` VALUES ('20198', '死亡骑士长靴', '100');
INSERT INTO `william_item_price` VALUES ('20199', '拉斯塔巴德长靴', '100');
INSERT INTO `william_item_price` VALUES ('20200', '魔兽军王长靴', '100');
INSERT INTO `william_item_price` VALUES ('20201', '武官长靴', '100');
INSERT INTO `william_item_price` VALUES ('20202', '巴兰卡长靴', '100');
INSERT INTO `william_item_price` VALUES ('20203', '反王长靴', '100');
INSERT INTO `william_item_price` VALUES ('20204', '巴列斯长靴', '100');
INSERT INTO `william_item_price` VALUES ('20205', '长靴', '1250');
INSERT INTO `william_item_price` VALUES ('20206', '象牙塔皮凉鞋', '0');
INSERT INTO `william_item_price` VALUES ('20207', '深水长靴', '2500');
INSERT INTO `william_item_price` VALUES ('20208', '神官长靴', '100');
INSERT INTO `william_item_price` VALUES ('20209', '火焰之影长靴', '100');
INSERT INTO `william_item_price` VALUES ('20210', '黑暗长靴', '2500');
INSERT INTO `william_item_price` VALUES ('20211', '冰之女王魅力凉鞋', '100');
INSERT INTO `william_item_price` VALUES ('20212', '银钉皮凉鞋', '452');
INSERT INTO `william_item_price` VALUES ('20213', '短统靴', '150');
INSERT INTO `william_item_price` VALUES ('20214', '克特长靴', '150');
INSERT INTO `william_item_price` VALUES ('20215', '赛尼斯长靴', '150');
INSERT INTO `william_item_price` VALUES ('20216', '堕落长靴', '150');
INSERT INTO `william_item_price` VALUES ('20217', '蓝海贼长靴', '2500');
INSERT INTO `william_item_price` VALUES ('20218', '黑长者凉鞋', '3500');
INSERT INTO `william_item_price` VALUES ('20219', '皮盾牌', '10');
INSERT INTO `william_item_price` VALUES ('20220', '钢铁盾牌', '250');
INSERT INTO `william_item_price` VALUES ('20221', '骷髅盾牌', '10');
INSERT INTO `william_item_price` VALUES ('20222', '木盾', '30');
INSERT INTO `william_item_price` VALUES ('20223', '侏儒圆盾', '100');
INSERT INTO `william_item_price` VALUES ('20224', '拉斯塔巴德圆盾', '500');
INSERT INTO `william_item_price` VALUES ('20225', '玛那水晶球', '5647');
INSERT INTO `william_item_price` VALUES ('20226', '魔法能量之书', '5647');
INSERT INTO `william_item_price` VALUES ('20227', '梅杜莎盾牌', '100');
INSERT INTO `william_item_price` VALUES ('20228', '武官之盾', '100');
INSERT INTO `william_item_price` VALUES ('20229', '反射之盾', '50');
INSERT INTO `william_item_price` VALUES ('20230', '红骑士盾牌', '50');
INSERT INTO `william_item_price` VALUES ('20231', '塔盾', '3500');
INSERT INTO `william_item_price` VALUES ('20232', '象牙塔皮盾牌', '0');
INSERT INTO `william_item_price` VALUES ('20233', '神官魔法书', '100');
INSERT INTO `william_item_price` VALUES ('20234', '信念之盾', '2500');
INSERT INTO `william_item_price` VALUES ('20235', '伊娃之盾', '2500');
INSERT INTO `william_item_price` VALUES ('20236', '精灵盾牌', '2500');
INSERT INTO `william_item_price` VALUES ('20237', '阿克海盾牌', '60');
INSERT INTO `william_item_price` VALUES ('20238', '银骑士之盾', '150');
INSERT INTO `william_item_price` VALUES ('20239', '小盾牌', '30');
INSERT INTO `william_item_price` VALUES ('20240', '死亡之盾', '100');
INSERT INTO `william_item_price` VALUES ('20241', '银钉皮盾', '10');
INSERT INTO `william_item_price` VALUES ('20242', '大盾牌', '600');
INSERT INTO `william_item_price` VALUES ('20243', '隐藏之谷项链', '0');
INSERT INTO `william_item_price` VALUES ('20244', '小型魅力项链', '100');
INSERT INTO `william_item_price` VALUES ('20245', '小型敏捷项链', '100');
INSERT INTO `william_item_price` VALUES ('20246', '小型力量项链', '100');
INSERT INTO `william_item_price` VALUES ('20247', '小型智力项链', '100');
INSERT INTO `william_item_price` VALUES ('20248', '小型精神项链', '100');
INSERT INTO `william_item_price` VALUES ('20249', '小型体质项链', '100');
INSERT INTO `william_item_price` VALUES ('20250', '变形怪首领项链', '100');
INSERT INTO `william_item_price` VALUES ('20251', '都佩杰诺的项链', '100');
INSERT INTO `william_item_price` VALUES ('20252', '蕾雅项链', '100');
INSERT INTO `william_item_price` VALUES ('20253', '法令军王之炼', '100');
INSERT INTO `william_item_price` VALUES ('20254', '魅力项链', '100');
INSERT INTO `william_item_price` VALUES ('20255', '冥法军王之戒', '100');
INSERT INTO `william_item_price` VALUES ('20256', '敏捷项链', '100');
INSERT INTO `william_item_price` VALUES ('20257', '黑法师项链', '100');
INSERT INTO `william_item_price` VALUES ('20258', '唤兽师项链', '100');
INSERT INTO `william_item_price` VALUES ('20259', '歌唱之岛项链', '50');
INSERT INTO `william_item_price` VALUES ('20260', '艾莉丝项链', '100');
INSERT INTO `william_item_price` VALUES ('20261', '火焰之影项链', '100');
INSERT INTO `william_item_price` VALUES ('20262', '营养满分金项链', '100');
INSERT INTO `william_item_price` VALUES ('20263', '妖魔战士护身符', '2500');
INSERT INTO `william_item_price` VALUES ('20264', '力量项链', '100');
INSERT INTO `william_item_price` VALUES ('20265', '灵魂的印记', '2500');
INSERT INTO `william_item_price` VALUES ('20266', '智力项链', '100');
INSERT INTO `william_item_price` VALUES ('20267', '精神项链', '100');
INSERT INTO `william_item_price` VALUES ('20268', '体质项链', '100');
INSERT INTO `william_item_price` VALUES ('20269', '骷髅项链', '100');
INSERT INTO `william_item_price` VALUES ('20270', '都佩杰诺的项链1', '100');
INSERT INTO `william_item_price` VALUES ('20277', '变形怪首领之戒(右)', '100');
INSERT INTO `william_item_price` VALUES ('20278', '变形怪首领之戒(左)', '100');
INSERT INTO `william_item_price` VALUES ('20279', '蕾雅戒指', '100');
INSERT INTO `william_item_price` VALUES ('20280', '灭魔戒指', '50');
INSERT INTO `william_item_price` VALUES ('20281', '变形控制戒指', '100');
INSERT INTO `william_item_price` VALUES ('20282', '象牙塔戒指', '1');
INSERT INTO `william_item_price` VALUES ('20284', '召唤控制戒指', '100');
INSERT INTO `william_item_price` VALUES ('20285', '水灵戒指', '100');
INSERT INTO `william_item_price` VALUES ('20286', '守护团戒指', '100');
INSERT INTO `william_item_price` VALUES ('20287', '守护者的戒指', '100');
INSERT INTO `william_item_price` VALUES ('20288', '传送控制戒指', '100');
INSERT INTO `william_item_price` VALUES ('20289', '深渊戒指', '100');
INSERT INTO `william_item_price` VALUES ('20290', '火焰之影戒指', '100');
INSERT INTO `william_item_price` VALUES ('20291', '营养满分金戒指', '100');
INSERT INTO `william_item_price` VALUES ('20293', '受诅咒的钻石戒指', '2500');
INSERT INTO `william_item_price` VALUES ('20294', '受诅咒的红宝石戒指', '2500');
INSERT INTO `william_item_price` VALUES ('20295', '受诅咒的蓝宝石戒指', '2500');
INSERT INTO `william_item_price` VALUES ('20296', '受诅咒的绿宝石戒指', '2500');
INSERT INTO `william_item_price` VALUES ('20297', '精神的印记', '2500');
INSERT INTO `william_item_price` VALUES ('20298', '洁尼斯戒指', '100');
INSERT INTO `william_item_price` VALUES ('20299', '死亡的誓约', '2500');
INSERT INTO `william_item_price` VALUES ('20300', '地灵戒指', '100');
INSERT INTO `william_item_price` VALUES ('20301', '身体的印记', '2500');
INSERT INTO `william_item_price` VALUES ('20302', '风灵戒指', '100');
INSERT INTO `william_item_price` VALUES ('20303', '抗魔戒指', '2500');
INSERT INTO `william_item_price` VALUES ('20304', '火灵戒指', '2500');
INSERT INTO `william_item_price` VALUES ('20305', '情人戒指', '2500');
INSERT INTO `william_item_price` VALUES ('20306', '小型身体腰带', '50');
INSERT INTO `william_item_price` VALUES ('20307', '小型灵魂腰带', '50');
INSERT INTO `william_item_price` VALUES ('20308', '小型精神腰带', '50');
INSERT INTO `william_item_price` VALUES ('20309', '光明身体腰带', '100');
INSERT INTO `william_item_price` VALUES ('20310', '光明灵魂腰带', '100');
INSERT INTO `william_item_price` VALUES ('20311', '光明精神腰带', '100');
INSERT INTO `william_item_price` VALUES ('20312', '身体腰带', '100');
INSERT INTO `william_item_price` VALUES ('20313', '黑暗腰带', '10');
INSERT INTO `william_item_price` VALUES ('20314', '古代巨人戒指', '100');
INSERT INTO `william_item_price` VALUES ('20315', '营养满分金腰带', '100');
INSERT INTO `william_item_price` VALUES ('20316', '灵魂腰带', '100');
INSERT INTO `william_item_price` VALUES ('20317', '欧吉皮带', '100');
INSERT INTO `william_item_price` VALUES ('20318', '勇敢皮带', '100');
INSERT INTO `william_item_price` VALUES ('20319', '精神腰带', '100');
INSERT INTO `william_item_price` VALUES ('20320', '泰坦皮带', '100');
INSERT INTO `william_item_price` VALUES ('20321', '多罗皮带', '100');
INSERT INTO `william_item_price` VALUES ('20322', '皮夹克', '10');
INSERT INTO `william_item_price` VALUES ('20342', '死神披肩', '0');
INSERT INTO `william_item_price` VALUES ('20343', '曼波兔帽', '0');
INSERT INTO `william_item_price` VALUES ('20344', '曼波兔帽', '0');
INSERT INTO `william_item_price` VALUES ('20345', '柯利的项链', '0');
INSERT INTO `william_item_price` VALUES ('20346', '浣熊的项链', '0');
INSERT INTO `william_item_price` VALUES ('20347', '韩服', '0');
INSERT INTO `william_item_price` VALUES ('20348', '和服', '0');
INSERT INTO `william_item_price` VALUES ('20349', '猎犬项链', '0');
INSERT INTO `william_item_price` VALUES ('20350', '雪人的项链', '0');
INSERT INTO `william_item_price` VALUES ('20351', '雪人的胡萝卜', '0');
INSERT INTO `william_item_price` VALUES ('20352', '雪人的靴子', '0');
INSERT INTO `william_item_price` VALUES ('20353', '圣战士盔甲', '0');
INSERT INTO `william_item_price` VALUES ('20354', '圣战士长靴', '0');
INSERT INTO `william_item_price` VALUES ('20355', '圣战士手套', '0');
INSERT INTO `william_item_price` VALUES ('20356', '圣战士头盔', '0');
INSERT INTO `william_item_price` VALUES ('20357', '圣战士盾牌', '0');
INSERT INTO `william_item_price` VALUES ('30001', '诅咒的死亡骑士头盔', '100');
INSERT INTO `william_item_price` VALUES ('30002', '诅咒的死亡骑士盔甲', '100');
INSERT INTO `william_item_price` VALUES ('30003', '诅咒的死亡骑士手套', '100');
INSERT INTO `william_item_price` VALUES ('30004', '诅咒的死亡骑士长靴', '100');
INSERT INTO `william_item_price` VALUES ('30005', '荒神骑士头盔', '0');
INSERT INTO `william_item_price` VALUES ('30006', '荒神骑士盔甲', '0');
INSERT INTO `william_item_price` VALUES ('30007', '荒神骑士手套', '0');
INSERT INTO `william_item_price` VALUES ('30008', '荒神骑士长靴', '0');
INSERT INTO `william_item_price` VALUES ('40001', '灯', '5');
INSERT INTO `william_item_price` VALUES ('40002', '灯笼', '50');
INSERT INTO `william_item_price` VALUES ('40003', '灯油', '15');
INSERT INTO `william_item_price` VALUES ('40006', '创造怪物魔杖', '10');
INSERT INTO `william_item_price` VALUES ('40007', '闪电魔杖', '10');
INSERT INTO `william_item_price` VALUES ('40008', '变形魔杖', '10');
INSERT INTO `william_item_price` VALUES ('40009', '驱逐魔杖', '150');
INSERT INTO `william_item_price` VALUES ('40010', '治愈药水', '18');
INSERT INTO `william_item_price` VALUES ('40011', '强力治愈药水', '100');
INSERT INTO `william_item_price` VALUES ('40012', '终极治愈药水', '300');
INSERT INTO `william_item_price` VALUES ('40013', '自我加速药水', '100');
INSERT INTO `william_item_price` VALUES ('40014', '勇敢药水', '400');
INSERT INTO `william_item_price` VALUES ('40015', '加速魔力回复药水', '700');
INSERT INTO `william_item_price` VALUES ('40016', '慎重药水', '300');
INSERT INTO `william_item_price` VALUES ('40017', '解毒药水', '35');
INSERT INTO `william_item_price` VALUES ('40018', '强化 绿色药水', '750');
INSERT INTO `william_item_price` VALUES ('40019', '浓缩体力恢复剂', '27');
INSERT INTO `william_item_price` VALUES ('40020', '浓缩强力体力恢复剂', '150');
INSERT INTO `william_item_price` VALUES ('40021', '浓缩终极体力恢复剂', '450');
INSERT INTO `william_item_price` VALUES ('40022', '古代体力恢复剂', '31');
INSERT INTO `william_item_price` VALUES ('40023', '古代强力体力恢复剂', '187');
INSERT INTO `william_item_price` VALUES ('40024', '古代终极体力恢复剂', '0');
INSERT INTO `william_item_price` VALUES ('40025', '失明药水', '1');
INSERT INTO `william_item_price` VALUES ('40032', '伊娃的祝福', '150');
INSERT INTO `william_item_price` VALUES ('40044', '钻石', '500');
INSERT INTO `william_item_price` VALUES ('40045', '红宝石', '500');
INSERT INTO `william_item_price` VALUES ('40046', '蓝宝石', '500');
INSERT INTO `william_item_price` VALUES ('40047', '绿宝石', '500');
INSERT INTO `william_item_price` VALUES ('40048', '品质钻石', '10');
INSERT INTO `william_item_price` VALUES ('40049', '品质红宝石', '10');
INSERT INTO `william_item_price` VALUES ('40050', '品质蓝宝石', '10');
INSERT INTO `william_item_price` VALUES ('40051', '品质绿宝石', '10');
INSERT INTO `william_item_price` VALUES ('40052', '高品质钻石', '50');
INSERT INTO `william_item_price` VALUES ('40053', '高品质红\r\n\r\n\r\n\r\n', '50');
INSERT INTO `william_item_price` VALUES ('40054', '高品质蓝宝石', '50');
INSERT INTO `william_item_price` VALUES ('40055', '高品质绿宝石', '50');
INSERT INTO `william_item_price` VALUES ('40057', '漂浮之眼肉', '100');
INSERT INTO `william_item_price` VALUES ('40059', null, '2');
INSERT INTO `william_item_price` VALUES ('40060', null, '3');
INSERT INTO `william_item_price` VALUES ('40061', null, '3');
INSERT INTO `william_item_price` VALUES ('40062', null, '3');
INSERT INTO `william_item_price` VALUES ('40064', null, '3');
INSERT INTO `william_item_price` VALUES ('40065', null, '2');
INSERT INTO `william_item_price` VALUES ('40069', null, '3');
INSERT INTO `william_item_price` VALUES ('40072', null, '10');
INSERT INTO `william_item_price` VALUES ('40074', null, '15500');
INSERT INTO `william_item_price` VALUES ('40079', null, '60');
INSERT INTO `william_item_price` VALUES ('40087', null, '37500');
INSERT INTO `william_item_price` VALUES ('40088', null, '650');
INSERT INTO `william_item_price` VALUES ('40089', null, '500');
INSERT INTO `william_item_price` VALUES ('40100', '瞬间移动卷轴', '35');
INSERT INTO `william_item_price` VALUES ('40101', '指定传送卷轴(隐藏之谷)', '500');
INSERT INTO `william_item_price` VALUES ('40102', '亚丁村庄指定传送卷轴', '500');
INSERT INTO `william_item_price` VALUES ('40103', '欧瑞村庄指定传送卷轴', '500');
INSERT INTO `william_item_price` VALUES ('40104', '傲慢之塔移动卷轴(11F)', '500');
INSERT INTO `william_item_price` VALUES ('40105', '傲慢之塔移动卷轴(21F)', '500');
INSERT INTO `william_item_price` VALUES ('40106', '傲慢之塔移动卷轴(31F)', '500');
INSERT INTO `william_item_price` VALUES ('40107', '傲慢之塔移动卷轴(41F)', '500');
INSERT INTO `william_item_price` VALUES ('40108', '傲慢之塔移动卷轴(51F)', '500');
INSERT INTO `william_item_price` VALUES ('40109', '傲慢之塔移动卷轴(61F)', '500');
INSERT INTO `william_item_price` VALUES ('40110', '傲慢之塔移动卷轴(71F)', '500');
INSERT INTO `william_item_price` VALUES ('40111', '傲慢之塔移动卷轴(81F)', '500');
INSERT INTO `william_item_price` VALUES ('40112', '傲慢之塔移动卷轴(91F)', '500');
INSERT INTO `william_item_price` VALUES ('40113', '傲慢之塔移动卷轴(100F)', '500');
INSERT INTO `william_item_price` VALUES ('40114', '妖森指定传送卷轴', '500');
INSERT INTO `william_item_price` VALUES ('40115', '风木村庄指定传送卷轴', '500');
INSERT INTO `william_item_price` VALUES ('40116', '威顿村庄指定传送卷轴', '500');
INSERT INTO `william_item_price` VALUES ('40117', '银骑士村庄指定传送卷轴', '500');
INSERT INTO `william_item_price` VALUES ('40118', '隐遁者村庄指定传送卷轴', '500');
INSERT INTO `william_item_price` VALUES ('40119', '解除咀咒的卷轴', '50');
INSERT INTO `william_item_price` VALUES ('40120', '抵抗军村庄指定传送卷轴', '500');
INSERT INTO `william_item_price` VALUES ('40121', '指定传送(矿物洞穴)', '500');
INSERT INTO `william_item_price` VALUES ('40122', '肯特村庄指定传送卷轴', '500');
INSERT INTO `william_item_price` VALUES ('40123', '海音村庄指定传送卷轴', '500');
INSERT INTO `william_item_price` VALUES ('40124', '血盟传送卷轴', '60');
INSERT INTO `william_item_price` VALUES ('40125', '燃柳村庄指定传送卷轴', '500');
INSERT INTO `william_item_price` VALUES ('40126', '鉴定卷轴', '25');
INSERT INTO `william_item_price` VALUES ('40127', '对盔甲施法的幻象卷轴', '0');
INSERT INTO `william_item_price` VALUES ('40128', '对武器施法的幻象卷轴', '0');
INSERT INTO `william_item_price` VALUES ('40129', '奇安的卷轴', '0');
INSERT INTO `william_item_price` VALUES ('40130', '金侃的卷轴', '0');
INSERT INTO `william_item_price` VALUES ('40131', '甘地图腾', '0');
INSERT INTO `william_item_price` VALUES ('40132', '那鲁加图腾', '0');
INSERT INTO `william_item_price` VALUES ('40133', '都达玛拉图腾', '0');
INSERT INTO `william_item_price` VALUES ('40134', '罗孚图腾', '0');
INSERT INTO `william_item_price` VALUES ('40135', '阿吐巴图腾', '0');
INSERT INTO `william_item_price` VALUES ('40136', '3连发烟火', '460');
INSERT INTO `william_item_price` VALUES ('40137', '6连发烟火', '2180');
INSERT INTO `william_item_price` VALUES ('40138', '高级6连发烟火', '1050');
INSERT INTO `william_item_price` VALUES ('40139', '蓝色2段烟火', '350');
INSERT INTO `william_item_price` VALUES ('40140', '蓝色仙女棒', '40');
INSERT INTO `william_item_price` VALUES ('40141', '蓝色烟火', '87');
INSERT INTO `william_item_price` VALUES ('40142', '蓝色心型烟火', '175');
INSERT INTO `william_item_price` VALUES ('40143', '红色2段烟火', '350');
INSERT INTO `william_item_price` VALUES ('40144', '红色仙女棒', '42');
INSERT INTO `william_item_price` VALUES ('40145', '红色烟火', '87');
INSERT INTO `william_item_price` VALUES ('40146', '红色心型烟火', '145');
INSERT INTO `william_item_price` VALUES ('40147', '绿色2段圆形烟火', '350');
INSERT INTO `william_item_price` VALUES ('40148', '绿色2段烟火', '350');
INSERT INTO `william_item_price` VALUES ('40149', '蓝色圆形烟火', '175');
INSERT INTO `william_item_price` VALUES ('40150', '绿色仙女棒', '43');
INSERT INTO `william_item_price` VALUES ('40151', '淡绿色烟火', '82');
INSERT INTO `william_item_price` VALUES ('40152', '绿色烟火', '82');
INSERT INTO `william_item_price` VALUES ('40153', '绿色心型烟火', '175');
INSERT INTO `william_item_price` VALUES ('40154', '圣诞烟火', '500');
INSERT INTO `william_item_price` VALUES ('40155', '黄色2段圆形烟火', '350');
INSERT INTO `william_item_price` VALUES ('40156', '黄色2段烟火', '350');
INSERT INTO `william_item_price` VALUES ('40157', '黄色圆形烟火', '350');
INSERT INTO `william_item_price` VALUES ('40158', '黄色仙女棒', '43');
INSERT INTO `william_item_price` VALUES ('40159', '黄色烟火', '87');
INSERT INTO `william_item_price` VALUES ('40160', '淡黄色烟火', '87');
INSERT INTO `william_item_price` VALUES ('40161', '黄色心型烟火', '175');
INSERT INTO `william_item_price` VALUES ('40162', '高仑之心', '0');
INSERT INTO `william_item_price` VALUES ('40163', '黄金钥匙', '0');
INSERT INTO `william_item_price` VALUES ('40164', '技术书(冲击之晕)', '200');
INSERT INTO `william_item_price` VALUES ('40165', '技术书(增幅防御)', '200');
INSERT INTO `william_item_price` VALUES ('40166', '技术书(尖刺盔甲)', '200');
INSERT INTO `william_item_price` VALUES ('40167', '古老皮袋', '0');
INSERT INTO `william_item_price` VALUES ('40168', '古老丝袋', '0');
INSERT INTO `william_item_price` VALUES ('40169', '飞龙之心', '0');
INSERT INTO `william_item_price` VALUES ('40170', '魔法书(燃烧的火球)', '1650');
INSERT INTO `william_item_price` VALUES ('40171', '魔法书(通畅气脉术)', '1650');
INSERT INTO `william_item_price` VALUES ('40172', '魔法书(坏物术)', '1650');
INSERT INTO `william_item_price` VALUES ('40173', '魔法书(吸血鬼之吻)', '1650');
INSERT INTO `william_item_price` VALUES ('40174', '魔法书(缓速术)', '1650');
INSERT INTO `william_item_price` VALUES ('40175', '魔法书(魔法屏障)', '1650');
INSERT INTO `william_item_price` VALUES ('40176', '魔法书(冥想术)', '1650');
INSERT INTO `william_item_price` VALUES ('40177', '魔法书(岩牢)', '1650');
INSERT INTO `william_item_price` VALUES ('40178', '魔法书(木乃伊的诅咒)', '1650');
INSERT INTO `william_item_price` VALUES ('40179', '魔法书(极道落雷)', '4125');
INSERT INTO `william_item_price` VALUES ('40180', '魔法书(高级治愈术)', '4125');
INSERT INTO `william_item_price` VALUES ('40181', '魔法书(迷魅术)', '4125');
INSERT INTO `william_item_price` VALUES ('40182', '魔法书(圣洁之光)', '4125');
INSERT INTO `william_item_price` VALUES ('40183', '魔法书(冰锥)', '4125');
INSERT INTO `william_item_price` VALUES ('40184', '魔法书(魔力夺取)', '4125');
INSERT INTO `william_item_price` VALUES ('40185', '魔法书(黑闇之影)', '4125');
INSERT INTO `william_item_price` VALUES ('40186', '魔法书(造尸术)', '4125');
INSERT INTO `william_item_price` VALUES ('40187', '魔法书(体魄强健术)', '8250');
INSERT INTO `william_item_price` VALUES ('40188', '魔法书(加速术)', '8250');
INSERT INTO `william_item_price` VALUES ('40189', '魔法书(魔法相消术)', '4125');
INSERT INTO `william_item_price` VALUES ('40190', '魔法书(地裂术)', '8250');
INSERT INTO `william_item_price` VALUES ('40191', '魔法书(烈炎术)', '8250');
INSERT INTO `william_item_price` VALUES ('40192', '魔法书(弱化术)', '8250');
INSERT INTO `william_item_price` VALUES ('40193', '魔法书(祝福魔法武器)', '8250');
INSERT INTO `william_item_price` VALUES ('40194', '魔法书(体力回复术)', '50');
INSERT INTO `william_item_price` VALUES ('40195', '魔法书(冰矛围篱)', '50');
INSERT INTO `william_item_price` VALUES ('40196', '魔法书(召唤术)', '500');
INSERT INTO `william_item_price` VALUES ('40197', '魔法书(神圣疾走)', '100');
INSERT INTO `william_item_price` VALUES ('40198', '魔法书(龙卷风)', '50');
INSERT INTO `william_item_price` VALUES ('40199', '魔法书(强力加速术)', '50');
INSERT INTO `william_item_price` VALUES ('40200', '魔法书(狂暴术)', '500');
INSERT INTO `william_item_price` VALUES ('40201', '魔法书(疾病术)', '100');
INSERT INTO `william_item_price` VALUES ('40202', '魔法书(全部治愈术)', '50');
INSERT INTO `william_item_price` VALUES ('40203', '魔法书(火牢)', '50');
INSERT INTO `william_item_price` VALUES ('40204', '魔法书(冰雪暴)', '500');
INSERT INTO `william_item_price` VALUES ('40205', '魔法书(隐身术)', '500');
INSERT INTO `william_item_price` VALUES ('40206', '魔法书(返生术)', '50');
INSERT INTO `william_item_price` VALUES ('40207', '魔法书(震裂术)', '100');
INSERT INTO `william_item_price` VALUES ('40208', '魔法书(治愈能量风暴)', '50');
INSERT INTO `william_item_price` VALUES ('40209', '魔法书(魔法封印)', '500');
INSERT INTO `william_item_price` VALUES ('40210', '魔法书(雷霆风暴)', '100');
INSERT INTO `william_item_price` VALUES ('40211', '魔法书(沉睡之雾)', '100');
INSERT INTO `william_item_price` VALUES ('40212', '魔法书(变形术)', '500');
INSERT INTO `william_item_price` VALUES ('40213', '魔法书(圣结界)', '20');
INSERT INTO `william_item_price` VALUES ('40214', '魔法书(集体传送术)', '50');
INSERT INTO `william_item_price` VALUES ('40215', '魔法书(火风暴)', '30');
INSERT INTO `william_item_price` VALUES ('40216', '魔法书(药水霜化术)', '500');
INSERT INTO `william_item_price` VALUES ('40217', '魔法书(强力无所遁形术)', '50');
INSERT INTO `william_item_price` VALUES ('40218', '魔法书(创造魔法武器)', '50');
INSERT INTO `william_item_price` VALUES ('40219', '魔法书(流星雨)', '10');
INSERT INTO `william_item_price` VALUES ('40220', '魔法书(终极返生术)', '50');
INSERT INTO `william_item_price` VALUES ('40221', '魔法书(集体缓速术)', '100');
INSERT INTO `william_item_price` VALUES ('40222', '魔法书(究极光裂术)', '500');
INSERT INTO `william_item_price` VALUES ('40223', '魔法书(绝对屏障)', '500');
INSERT INTO `william_item_price` VALUES ('40224', '魔法书(灵魂升华)', '50');
INSERT INTO `william_item_price` VALUES ('40225', '魔法书(冰雪飓风)', '50');
INSERT INTO `william_item_price` VALUES ('40226', '魔法书(精准目标)', '14850');
INSERT INTO `william_item_price` VALUES ('40227', '魔法书(激励士气)', '14850');
INSERT INTO `william_item_price` VALUES ('40228', '魔法书(呼唤盟友)', '14850');
INSERT INTO `william_item_price` VALUES ('40229', '魔法书(钢铁士气)', '14850');
INSERT INTO `william_item_price` VALUES ('40230', '魔法书(冲击士气)', '14850');
INSERT INTO `william_item_price` VALUES ('40231', '魔法书(援护盟友)', '14850');
INSERT INTO `william_item_price` VALUES ('40232', '精灵水晶(魔法防御)', '1650');
INSERT INTO `william_item_price` VALUES ('40233', '精灵水晶(心灵转换)', '1650');
INSERT INTO `william_item_price` VALUES ('40234', '精灵水晶(世界树的呼唤)', '1650');
INSERT INTO `william_item_price` VALUES ('40235', '精灵水晶(净化精神)', '4125');
INSERT INTO `william_item_price` VALUES ('40236', '精灵水晶(属性防御)', '4125');
INSERT INTO `william_item_price` VALUES ('40237', '精灵水晶(释放元素)', '500');
INSERT INTO `william_item_price` VALUES ('40238', '精灵水晶(魂体转换)', '500');
INSERT INTO `william_item_price` VALUES ('40239', '精灵水晶(单属性防御)', '0');
INSERT INTO `william_item_price` VALUES ('40240', '精灵水晶(三重矢)', '500');
INSERT INTO `william_item_price` VALUES ('40241', '精灵水晶(弱化属性)', '8250');
INSERT INTO `william_item_price` VALUES ('40242', '精灵水晶(魔法消除)', '500');
INSERT INTO `william_item_price` VALUES ('40243', '精灵水晶(召唤属性精灵)', '8250');
INSERT INTO `william_item_price` VALUES ('40244', '精灵水晶(封印禁地)', '500');
INSERT INTO `william_item_price` VALUES ('40245', '精灵水晶(召唤强力属性精灵)', '8250');
INSERT INTO `william_item_price` VALUES ('40246', '精灵水晶(镜反射)', '500');
INSERT INTO `william_item_price` VALUES ('40247', '精灵水晶(大地防护)', '8250');
INSERT INTO `william_item_price` VALUES ('40248', '精灵水晶(地面障碍)', '500');
INSERT INTO `william_item_price` VALUES ('40249', '精灵水晶(大地屏障)', '500');
INSERT INTO `william_item_price` VALUES ('40250', '精灵水晶(大地的祝福)', '50');
INSERT INTO `william_item_price` VALUES ('40251', '精灵水晶(钢铁防护)', '50');
INSERT INTO `william_item_price` VALUES ('40252', '精灵水晶(体能激发)', '50');
INSERT INTO `william_item_price` VALUES ('40253', '精灵水晶(水之元气)', '10');
INSERT INTO `william_item_price` VALUES ('40254', '精灵水晶(生命之泉)', '500');
INSERT INTO `william_item_price` VALUES ('40255', '精灵水晶(生命的祝福)', '500');
INSERT INTO `william_item_price` VALUES ('40256', '精灵水晶(火焰武器)', '8250');
INSERT INTO `william_item_price` VALUES ('40257', '精灵水晶(烈炎气息)', '0');
INSERT INTO `william_item_price` VALUES ('40258', '精灵水晶(烈炎武器)', '0');
INSERT INTO `william_item_price` VALUES ('40259', '精灵水晶(属性之火)', '500');
INSERT INTO `william_item_price` VALUES ('40260', '精灵水晶(风之神射)', '8250');
INSERT INTO `william_item_price` VALUES ('40261', '精灵水晶(风之疾走)', '8250');
INSERT INTO `william_item_price` VALUES ('40262', '精灵水晶(暴风之眼)', '8250');
INSERT INTO `william_item_price` VALUES ('40263', '精灵水晶(暴风神射)', '8250');
INSERT INTO `william_item_price` VALUES ('40264', '精灵水晶(风之枷锁)', '50');
INSERT INTO `william_item_price` VALUES ('40265', '黑暗精灵水晶(暗隐术)', '50');
INSERT INTO `william_item_price` VALUES ('40266', '黑暗精灵水晶(附加剧毒)', '50');
INSERT INTO `william_item_price` VALUES ('40267', '黑暗精灵水晶(影之防护)', '50');
INSERT INTO `william_item_price` VALUES ('40268', '黑暗精灵水晶(提炼魔石)', '50');
INSERT INTO `william_item_price` VALUES ('40269', '黑暗精灵水晶(力量提升)', '50');
INSERT INTO `william_item_price` VALUES ('40270', '黑暗精灵水晶(行走加速)', '50');
INSERT INTO `william_item_price` VALUES ('40271', '黑暗精灵水晶(燃烧斗志)', '50');
INSERT INTO `william_item_price` VALUES ('40272', '黑暗精灵水晶(暗黑盲咒)', '50');
INSERT INTO `william_item_price` VALUES ('40273', '黑暗精灵水晶(毒性抵抗)', '50');
INSERT INTO `william_item_price` VALUES ('40274', '黑暗精灵水晶(敏捷提升)', '50');
INSERT INTO `william_item_price` VALUES ('40275', '黑暗精灵水晶(双重破坏)', '500');
INSERT INTO `william_item_price` VALUES ('40276', '黑暗精灵水晶(暗影闪避)', '500');
INSERT INTO `william_item_price` VALUES ('40277', '黑暗精灵水晶(暗影之牙)', '50');
INSERT INTO `william_item_price` VALUES ('40278', '黑暗精灵水晶(会心一击)', '50');
INSERT INTO `william_item_price` VALUES ('40279', '黑暗精灵水晶(闪避提升)', '50');
INSERT INTO `william_item_price` VALUES ('40280', '封印的傲慢之塔传送符(11F)', '500');
INSERT INTO `william_item_price` VALUES ('40281', '封印的傲慢之塔传送符(21F)', '500');
INSERT INTO `william_item_price` VALUES ('40282', '封印的傲慢之塔传送符(31F)', '500');
INSERT INTO `william_item_price` VALUES ('40283', '封印的傲慢之塔传送符(41F)', '500');
INSERT INTO `william_item_price` VALUES ('40284', '封印的傲慢之塔传送符(51F)', '500');
INSERT INTO `william_item_price` VALUES ('40285', '封印的傲慢之塔传送符(61F)', '500');
INSERT INTO `william_item_price` VALUES ('40286', '封印的傲慢之塔传送符(71F)', '500');
INSERT INTO `william_item_price` VALUES ('40287', '封印的傲慢之塔传送符(81F)', '500');
INSERT INTO `william_item_price` VALUES ('40288', '封印的傲慢之塔传送符(91F)', '500');
INSERT INTO `william_item_price` VALUES ('40289', '傲慢之塔传送符(11F)', '500');
INSERT INTO `william_item_price` VALUES ('40290', '傲慢之塔传送符(21F)', '500');
INSERT INTO `william_item_price` VALUES ('40291', '傲慢之塔传送符(31F)', '500');
INSERT INTO `william_item_price` VALUES ('40292', '傲慢之塔传送符(41F)', '500');
INSERT INTO `william_item_price` VALUES ('40293', '傲慢之塔传送符(51F)', '500');
INSERT INTO `william_item_price` VALUES ('40294', '傲慢之塔传送符(61F)', '500');
INSERT INTO `william_item_price` VALUES ('40295', '傲慢之塔传送符(71F)', '500');
INSERT INTO `william_item_price` VALUES ('40296', '傲慢之塔传送符(81F)', '500');
INSERT INTO `william_item_price` VALUES ('40297', '傲慢之塔传送符(91F)', '500');
INSERT INTO `william_item_price` VALUES ('40298', '往说话之岛的船票', '150');
INSERT INTO `william_item_price` VALUES ('40299', '往古鲁丁的船票', '150');
INSERT INTO `william_item_price` VALUES ('40300', '遗忘之岛船票', '150');
INSERT INTO `william_item_price` VALUES ('40301', '海音港口船票', '150');
INSERT INTO `william_item_price` VALUES ('40302', '海贼岛船票', '345');
INSERT INTO `william_item_price` VALUES ('40303', '隐藏港口船票', '345');
INSERT INTO `william_item_price` VALUES ('40304', '马普勒之石', '525');
INSERT INTO `william_item_price` VALUES ('40305', '帕格里奥之石', '525');
INSERT INTO `william_item_price` VALUES ('40306', '伊娃之石', '525');
INSERT INTO `william_item_price` VALUES ('40307', '沙哈之石', '525');
INSERT INTO `william_item_price` VALUES ('40308', '金币', '0');
INSERT INTO `william_item_price` VALUES ('40309', '食人妖精竞赛票', '0');
INSERT INTO `william_item_price` VALUES ('40310', '信纸', '17');
INSERT INTO `william_item_price` VALUES ('40311', '血盟的信纸', '40');
INSERT INTO `william_item_price` VALUES ('40312', '旅馆钥匙', '0');
INSERT INTO `william_item_price` VALUES ('40313', '银钥匙', '0');
INSERT INTO `william_item_price` VALUES ('40314', '项圈', '0');
INSERT INTO `william_item_price` VALUES ('40315', '哨子', '250');
INSERT INTO `william_item_price` VALUES ('40316', '高等宠物项圈', '0');
INSERT INTO `william_item_price` VALUES ('40317', '磨刀石', '75');
INSERT INTO `william_item_price` VALUES ('40318', '魔法宝石', '100');
INSERT INTO `william_item_price` VALUES ('40319', '精灵玉', '100');
INSERT INTO `william_item_price` VALUES ('40320', '一级黑魔石', '100');
INSERT INTO `william_item_price` VALUES ('40321', '二级黑魔石', '200');
INSERT INTO `william_item_price` VALUES ('40322', '三级黑魔石', '400');
INSERT INTO `william_item_price` VALUES ('40323', '四级黑魔石', '800');
INSERT INTO `william_item_price` VALUES ('40324', '五级黑魔石', '1600');
INSERT INTO `william_item_price` VALUES ('40325', '二段式魔法骰子', '50');
INSERT INTO `william_item_price` VALUES ('40326', '三段式魔法骰子', '50');
INSERT INTO `william_item_price` VALUES ('40327', '四段式魔法骰子', '50');
INSERT INTO `william_item_price` VALUES ('40328', '六段式魔法骰子', '50');
INSERT INTO `william_item_price` VALUES ('40329', '原住民图腾', '0');
INSERT INTO `william_item_price` VALUES ('40330', '无限箭筒', '50');
INSERT INTO `william_item_price` VALUES ('40331', '肯特勇士之剑', '0');
INSERT INTO `william_item_price` VALUES ('40332', '肯特射手之弓', '0');
INSERT INTO `william_item_price` VALUES ('40333', '肯特法师魔杖', '0');
INSERT INTO `william_item_price` VALUES ('40334', '肯特刺客双刀', '0');
INSERT INTO `william_item_price` VALUES ('40335', '肯特战士斧头', '0');
INSERT INTO `william_item_price` VALUES ('40336', '肯特徽章长靴', '0');
INSERT INTO `william_item_price` VALUES ('40337', '肯特徽章盔甲', '0');
INSERT INTO `william_item_price` VALUES ('40338', '肯特徽章手套', '0');
INSERT INTO `william_item_price` VALUES ('40339', '肯特徽章盾牌', '0');
INSERT INTO `william_item_price` VALUES ('40340', '肯特徽章头盔', '0');
INSERT INTO `william_item_price` VALUES ('40341', '安塔瑞斯之鳞', '0');
INSERT INTO `william_item_price` VALUES ('40342', '安塔瑞斯之爪', '0');
INSERT INTO `william_item_price` VALUES ('40343', '安塔瑞斯之眼', '0');
INSERT INTO `william_item_price` VALUES ('40344', '安塔瑞斯之血', '0');
INSERT INTO `william_item_price` VALUES ('40345', '安塔瑞斯之肉', '0');
INSERT INTO `william_item_price` VALUES ('40346', '安塔瑞斯之心', '0');
INSERT INTO `william_item_price` VALUES ('40347', '安塔瑞斯之骨', '0');
INSERT INTO `william_item_price` VALUES ('40348', '安塔瑞斯之牙', '0');
INSERT INTO `william_item_price` VALUES ('40349', '巴拉卡斯之鳞', '0');
INSERT INTO `william_item_price` VALUES ('40350', '巴拉卡斯之爪', '0');
INSERT INTO `william_item_price` VALUES ('40351', '巴拉卡斯之眼', '0');
INSERT INTO `william_item_price` VALUES ('40352', '巴拉卡斯之血', '0');
INSERT INTO `william_item_price` VALUES ('40353', '巴拉卡斯之肉', '0');
INSERT INTO `william_item_price` VALUES ('40354', '巴拉卡斯之心', '0');
INSERT INTO `william_item_price` VALUES ('40355', '巴拉卡斯之骨', '0');
INSERT INTO `william_item_price` VALUES ('40356', '巴拉卡斯之牙', '0');
INSERT INTO `william_item_price` VALUES ('40357', '法利昂之鳞', '0');
INSERT INTO `william_item_price` VALUES ('40358', '法利昂之爪', '0');
INSERT INTO `william_item_price` VALUES ('40359', '法利昂之眼', '0');
INSERT INTO `william_item_price` VALUES ('40360', '法利昂之血', '0');
INSERT INTO `william_item_price` VALUES ('40361', '法利昂之肉', '0');
INSERT INTO `william_item_price` VALUES ('40362', '法利昂之心', '0');
INSERT INTO `william_item_price` VALUES ('40363', '法利昂之骨', '0');
INSERT INTO `william_item_price` VALUES ('40364', '法利昂之牙', '0');
INSERT INTO `william_item_price` VALUES ('40365', '林德拜尔之鳞', '0');
INSERT INTO `william_item_price` VALUES ('40366', '林德拜尔之爪', '0');
INSERT INTO `william_item_price` VALUES ('40367', '林德拜尔之眼', '0');
INSERT INTO `william_item_price` VALUES ('40368', '林德拜尔之血', '0');
INSERT INTO `william_item_price` VALUES ('40369', '林德拜尔之肉', '0');
INSERT INTO `william_item_price` VALUES ('40370', '林德拜尔之心', '0');
INSERT INTO `william_item_price` VALUES ('40371', '林德拜尔之骨', '0');
INSERT INTO `william_item_price` VALUES ('40372', '林德拜尔之牙', '0');
INSERT INTO `william_item_price` VALUES ('40373', '地图:大陆全图', '500');
INSERT INTO `william_item_price` VALUES ('40374', '地图:说话之岛', '30');
INSERT INTO `william_item_price` VALUES ('40375', '地图:古鲁丁', '43');
INSERT INTO `william_item_price` VALUES ('40376', '地图:肯特城', '43');
INSERT INTO `william_item_price` VALUES ('40377', '地图:妖魔城堡', '33');
INSERT INTO `william_item_price` VALUES ('40378', '地图:妖精森林', '33');
INSERT INTO `william_item_price` VALUES ('40379', '地图:风木之城', '33');
INSERT INTO `william_item_price` VALUES ('40380', '地图:银骑士村庄', '31');
INSERT INTO `william_item_price` VALUES ('40381', '地图:龙之谷', '43');
INSERT INTO `william_item_price` VALUES ('40382', '地图:奇岩', '41');
INSERT INTO `william_item_price` VALUES ('40383', '地图:歌唱之岛', '38');
INSERT INTO `william_item_price` VALUES ('40384', '地图:隐藏之谷', '36');
INSERT INTO `william_item_price` VALUES ('40385', '地图:海音', '33');
INSERT INTO `william_item_price` VALUES ('40386', '地图:火龙窟', '31');
INSERT INTO `william_item_price` VALUES ('40387', '地图:欧瑞', '43');
INSERT INTO `william_item_price` VALUES ('40388', '地图:亚丁', '41');
INSERT INTO `william_item_price` VALUES ('40389', '地图:沉默洞穴', '39');
INSERT INTO `william_item_price` VALUES ('40390', '地图:海贼岛', '25');
INSERT INTO `william_item_price` VALUES ('40391', '计算器', '35');
INSERT INTO `william_item_price` VALUES ('40392', '耶诞树', '33');
INSERT INTO `william_item_price` VALUES ('40393', '火龙鳞', '50');
INSERT INTO `william_item_price` VALUES ('40394', '风龙鳞', '100');
INSERT INTO `william_item_price` VALUES ('40395', '水龙鳞', '100');
INSERT INTO `william_item_price` VALUES ('40396', '地龙鳞', '100');
INSERT INTO `william_item_price` VALUES ('40397', '奇美拉之皮(龙)', '100');
INSERT INTO `william_item_price` VALUES ('40398', '奇美拉之皮(山羊)', '100');
INSERT INTO `william_item_price` VALUES ('40399', '奇美拉之皮(狮子)', '100');
INSERT INTO `william_item_price` VALUES ('40400', '奇美拉之皮(蛇)', '100');
INSERT INTO `william_item_price` VALUES ('40401', '诅咒的皮革(火)', '100');
INSERT INTO `william_item_price` VALUES ('40402', '诅咒的皮革(水)', '100');
INSERT INTO `william_item_price` VALUES ('40403', '诅咒的皮革(风)', '100');
INSERT INTO `william_item_price` VALUES ('40404', '诅咒的皮革(地)', '100');
INSERT INTO `william_item_price` VALUES ('40405', '皮革', '2');
INSERT INTO `william_item_price` VALUES ('40406', '高级皮革', '20');
INSERT INTO `william_item_price` VALUES ('40407', '骨头碎片', '10');
INSERT INTO `william_item_price` VALUES ('40408', '金属块', '20');
INSERT INTO `william_item_price` VALUES ('40409', '不死鸟之心', '980');
INSERT INTO `william_item_price` VALUES ('40410', '黑暗安特的树皮', '45');
INSERT INTO `william_item_price` VALUES ('40411', '黑暗安特的水果', '123');
INSERT INTO `william_item_price` VALUES ('40412', '黑暗安特的树枝', '2');
INSERT INTO `william_item_price` VALUES ('40413', '冰之女王之心', '341');
INSERT INTO `william_item_price` VALUES ('40414', '炼金术之石', '231');
INSERT INTO `william_item_price` VALUES ('40415', '遗物袋子', '546');
INSERT INTO `william_item_price` VALUES ('40416', '诅咒之血', '552');
INSERT INTO `william_item_price` VALUES ('40417', '精灵结晶', '465');
INSERT INTO `william_item_price` VALUES ('40418', '堕落的财物', '451');
INSERT INTO `william_item_price` VALUES ('40419', '巨大莫妮亚蜘蛛丝', '451');
INSERT INTO `william_item_price` VALUES ('40420', '古代人的咒术书1册', '50');
INSERT INTO `william_item_price` VALUES ('40421', '古代人的咒术书2册', '50');
INSERT INTO `william_item_price` VALUES ('40422', '古代人的咒术书3册', '50');
INSERT INTO `william_item_price` VALUES ('40423', '古代人的咒术书4册', '50');
INSERT INTO `william_item_price` VALUES ('40424', '狼皮', '341');
INSERT INTO `william_item_price` VALUES ('40425', '黑暗栖林者药水', '345');
INSERT INTO `william_item_price` VALUES ('40426', '黑暗栖林者戒指', '1410');
INSERT INTO `william_item_price` VALUES ('40427', '黑暗妖精袋子', '576');
INSERT INTO `william_item_price` VALUES ('40428', '月光之泪', '341');
INSERT INTO `william_item_price` VALUES ('40429', '大洞穴卷轴碎片', '50');
INSERT INTO `william_item_price` VALUES ('40430', '大洞穴水晶', '45');
INSERT INTO `william_item_price` VALUES ('40431', '鼹鼠的皮', '98');
INSERT INTO `william_item_price` VALUES ('40432', '大洞穴卷轴碎片', '50');
INSERT INTO `william_item_price` VALUES ('40433', '犰狳之爪', '98');
INSERT INTO `william_item_price` VALUES ('40434', '犰狳的尾巴', '45');
INSERT INTO `william_item_price` VALUES ('40435', '深渊之花的花苞', '213');
INSERT INTO `william_item_price` VALUES ('40436', '深渊之花的根', '241');
INSERT INTO `william_item_price` VALUES ('40437', '深渊花枝条', '23');
INSERT INTO `william_item_price` VALUES ('40438', '蝙蝠之牙', '212');
INSERT INTO `william_item_price` VALUES ('40439', '白金金属板', '231');
INSERT INTO `william_item_price` VALUES ('40440', '白金', '456');
INSERT INTO `william_item_price` VALUES ('40441', '白金原石', '25');
INSERT INTO `william_item_price` VALUES ('40442', '布拉伯的胃液', '235');
INSERT INTO `william_item_price` VALUES ('40443', '黑色米索莉', '451');
INSERT INTO `william_item_price` VALUES ('40444', '黑色米索莉原石', '546');
INSERT INTO `william_item_price` VALUES ('40445', '黑色米索莉金属板', '235');
INSERT INTO `william_item_price` VALUES ('40446', '黑法师戒指', '1456');
INSERT INTO `william_item_price` VALUES ('40447', '黑虎的皮', '351');
INSERT INTO `william_item_price` VALUES ('40448', '黑虎的爪', '124');
INSERT INTO `william_item_price` VALUES ('40449', '黑虎的牙', '341');
INSERT INTO `william_item_price` VALUES ('40450', '黑暗安特的树枝', '341');
INSERT INTO `william_item_price` VALUES ('40451', '黑虎之心', '560');
INSERT INTO `william_item_price` VALUES ('40452', '唤兽师戒指', '1657');
INSERT INTO `william_item_price` VALUES ('40453', '唤兽师长鞭', '1345');
INSERT INTO `william_item_price` VALUES ('40454', '驯兽师戒指', '1653');
INSERT INTO `william_item_price` VALUES ('40455', '蓝色布料', '100');
INSERT INTO `william_item_price` VALUES ('40456', '红色布料', '100');
INSERT INTO `william_item_price` VALUES ('40457', '白色布料', '100');
INSERT INTO `william_item_price` VALUES ('40458', '光明的鳞片', '54');
INSERT INTO `william_item_price` VALUES ('40459', '毒蝎之皮', '87');
INSERT INTO `william_item_price` VALUES ('40460', '阿西塔基奥的灰烬', '165');
INSERT INTO `william_item_price` VALUES ('40461', '恶魔的黑色脚镣', '12');
INSERT INTO `william_item_price` VALUES ('40462', '恶魔的红色脚镣', '34');
INSERT INTO `william_item_price` VALUES ('40463', '恶魔的蓝色脚镣', '54');
INSERT INTO `william_item_price` VALUES ('40464', '恶魔的白色脚镣', '43');
INSERT INTO `william_item_price` VALUES ('40465', '精灵使戒指', '451');
INSERT INTO `william_item_price` VALUES ('40466', '龙之心', '4526');
INSERT INTO `william_item_price` VALUES ('40467', '银', '341');
INSERT INTO `william_item_price` VALUES ('40468', '银原石', '23');
INSERT INTO `william_item_price` VALUES ('40469', '银金属板', '65');
INSERT INTO `william_item_price` VALUES ('40470', '原石碎片', '32');
INSERT INTO `william_item_price` VALUES ('40471', '精灵碎片', '54');
INSERT INTO `william_item_price` VALUES ('40472', '地狱犬之皮', '65');
INSERT INTO `william_item_price` VALUES ('40473', '堕落镰刀', '774');
INSERT INTO `william_item_price` VALUES ('40474', '堕落之毒', '768');
INSERT INTO `william_item_price` VALUES ('40475', '堕落首级', '0');
INSERT INTO `william_item_price` VALUES ('40476', '堕落之手', '546');
INSERT INTO `william_item_price` VALUES ('40477', '堕落的恶魔书1册', '50');
INSERT INTO `william_item_price` VALUES ('40478', '堕落的恶魔书2册', '50');
INSERT INTO `william_item_price` VALUES ('40479', '堕落的恶魔书3册', '50');
INSERT INTO `william_item_price` VALUES ('40480', '堕落的恶魔书4册', '50');
INSERT INTO `william_item_price` VALUES ('40481', '堕落之牙', '546');
INSERT INTO `william_item_price` VALUES ('40482', '堕落之舌', '541');
INSERT INTO `william_item_price` VALUES ('40483', '金属蜈蚣的皮', '234');
INSERT INTO `william_item_price` VALUES ('40484', '金属蜈蚣的毒液', '464');
INSERT INTO `william_item_price` VALUES ('40485', '金属蜈蚣的牙', '341');
INSERT INTO `william_item_price` VALUES ('40486', '火山灰', '351');
INSERT INTO `william_item_price` VALUES ('40487', '黄金金属板', '682');
INSERT INTO `william_item_price` VALUES ('40488', '黄金', '264');
INSERT INTO `william_item_price` VALUES ('40489', '黄金原石', '543');
INSERT INTO `william_item_price` VALUES ('40490', '黑暗元素石', '874');
INSERT INTO `william_item_price` VALUES ('40491', '格利芬羽毛', '674');
INSERT INTO `william_item_price` VALUES ('40492', '绿水晶', '24');
INSERT INTO `william_item_price` VALUES ('40493', '魔法笛子', '657');
INSERT INTO `william_item_price` VALUES ('40494', '纯粹的米索莉块', '231');
INSERT INTO `william_item_price` VALUES ('40495', '米索莉线', '541');
INSERT INTO `william_item_price` VALUES ('40496', '粗糙的米索莉块', '879');
INSERT INTO `william_item_price` VALUES ('40497', '米索莉金属板', '546');
INSERT INTO `william_item_price` VALUES ('40498', '风之泪', '431');
INSERT INTO `william_item_price` VALUES ('40499', '蘑菇汁', '76');
INSERT INTO `william_item_price` VALUES ('40500', '紫水晶', '0');
INSERT INTO `william_item_price` VALUES ('40501', '红水晶', '0');
INSERT INTO `william_item_price` VALUES ('40502', '线', '25');
INSERT INTO `william_item_price` VALUES ('40503', '芮克妮的网', '314');
INSERT INTO `william_item_price` VALUES ('40504', '芮克妮的蜕皮', '652');
INSERT INTO `william_item_price` VALUES ('40505', '安特之树皮', '431');
INSERT INTO `william_item_price` VALUES ('40506', '安特的水果', '765');
INSERT INTO `william_item_price` VALUES ('40507', '安特的树枝', '65');
INSERT INTO `william_item_price` VALUES ('40508', '奥里哈鲁根', '454');
INSERT INTO `william_item_price` VALUES ('40509', '奥里哈鲁根金属板', '985');
INSERT INTO `william_item_price` VALUES ('40510', '污浊安特的树皮', '0');
INSERT INTO `william_item_price` VALUES ('40511', '污浊安特的水果', '0');
INSERT INTO `william_item_price` VALUES ('40512', '污浊安特的树枝', '0');
INSERT INTO `william_item_price` VALUES ('40513', '食人巨魔的血', '643');
INSERT INTO `william_item_price` VALUES ('40514', '精灵之泪', '10');
INSERT INTO `william_item_price` VALUES ('40515', '元素石', '431');
INSERT INTO `william_item_price` VALUES ('40516', '质量绿水晶', '0');
INSERT INTO `william_item_price` VALUES ('40517', '质量红水晶', '0');
INSERT INTO `william_item_price` VALUES ('40518', '质量蓝水晶', '0');
INSERT INTO `william_item_price` VALUES ('40519', '潘的鬃毛', '0');
INSERT INTO `william_item_price` VALUES ('40520', '精灵粉末', '0');
INSERT INTO `william_item_price` VALUES ('40521', '精灵羽翼', '0');
INSERT INTO `william_item_price` VALUES ('40522', '蓝水晶', '0');
INSERT INTO `william_item_price` VALUES ('40523', '白水晶', '0');
INSERT INTO `william_item_price` VALUES ('40524', '黑色血痕', '786');
INSERT INTO `william_item_price` VALUES ('40525', '格兰肯之泪', '0');
INSERT INTO `william_item_price` VALUES ('40526', '薄金属板', '0');
INSERT INTO `william_item_price` VALUES ('40527', '锄头', '0');
INSERT INTO `william_item_price` VALUES ('40528', '守护神之袋', '0');
INSERT INTO `william_item_price` VALUES ('40529', '感谢信', '50');
INSERT INTO `william_item_price` VALUES ('40530', '古代王族的钥匙', '0');
INSERT INTO `william_item_price` VALUES ('40531', '古代骑士的钥匙', '0');
INSERT INTO `william_item_price` VALUES ('40532', '古代法师的钥匙', '0');
INSERT INTO `william_item_price` VALUES ('40533', '古代钥匙(下半部)', '0');
INSERT INTO `william_item_price` VALUES ('40534', '古代钥匙(上半部)', '0');
INSERT INTO `william_item_price` VALUES ('40535', '古代妖精的钥匙', '0');
INSERT INTO `william_item_price` VALUES ('40536', '古代恶魔的记载', '50');
INSERT INTO `william_item_price` VALUES ('40537', '古代的遗物', '0');
INSERT INTO `william_item_price` VALUES ('40538', '食尸鬼的指甲', '341');
INSERT INTO `william_item_price` VALUES ('40539', '食尸鬼的牙齿', '214');
INSERT INTO `william_item_price` VALUES ('40540', '古老的交易文件', '50');
INSERT INTO `william_item_price` VALUES ('40541', '黑暗之星', '0');
INSERT INTO `william_item_price` VALUES ('40542', '变形怪的血', '0');
INSERT INTO `william_item_price` VALUES ('40543', '蛇女房间钥匙', '0');
INSERT INTO `william_item_price` VALUES ('40544', '蛇女之鳞', '452');
INSERT INTO `william_item_price` VALUES ('40545', '伦得之袋', '50');
INSERT INTO `william_item_price` VALUES ('40546', '马沙之袋', '0');
INSERT INTO `william_item_price` VALUES ('40547', '村民的遗物', '0');
INSERT INTO `william_item_price` VALUES ('40548', '古代亡灵之袋', '0');
INSERT INTO `william_item_price` VALUES ('40549', '炎魔之剑', '0');
INSERT INTO `william_item_price` VALUES ('40550', '炎魔之眼', '0');
INSERT INTO `william_item_price` VALUES ('40551', '炎魔之爪', '0');
INSERT INTO `william_item_price` VALUES ('40552', '炎魔之心', '0');
INSERT INTO `william_item_price` VALUES ('40553', '布鲁迪卡之袋', '50');
INSERT INTO `william_item_price` VALUES ('40554', '秘密名单', '50');
INSERT INTO `william_item_price` VALUES ('40555', '密室钥匙', '0');
INSERT INTO `william_item_price` VALUES ('40556', '暗杀名单之袋', '50');
INSERT INTO `william_item_price` VALUES ('40557', '暗杀名单(古鲁丁村)', '50');
INSERT INTO `william_item_price` VALUES ('40558', '暗杀名单(奇岩村)', '50');
INSERT INTO `william_item_price` VALUES ('40559', '暗杀名单(亚丁城镇)', '50');
INSERT INTO `william_item_price` VALUES ('40560', '暗杀名单(风木村)', '50');
INSERT INTO `william_item_price` VALUES ('40561', '暗杀名单(肯特村)', '50');
INSERT INTO `william_item_price` VALUES ('40562', '暗杀名单(海音村)', '50');
INSERT INTO `william_item_price` VALUES ('40563', '暗杀名单(燃柳村)', '50');
INSERT INTO `william_item_price` VALUES ('40564', '生命的卷轴', '50');
INSERT INTO `william_item_price` VALUES ('40565', '搜索状', '50');
INSERT INTO `william_item_price` VALUES ('40566', '神秘贝壳', '0');
INSERT INTO `william_item_price` VALUES ('40567', '神秘水晶球', '0');
INSERT INTO `william_item_price` VALUES ('40568', '神秘的袋子', '0');
INSERT INTO `william_item_price` VALUES ('40569', '神秘魔杖', '0');
INSERT INTO `william_item_price` VALUES ('40570', '艾莉亚的回报', '0');
INSERT INTO `william_item_price` VALUES ('40571', '刺客首领的箱子', '0');
INSERT INTO `william_item_price` VALUES ('40572', '刺客之证', '0');
INSERT INTO `william_item_price` VALUES ('40573', '灵魂之证', '50');
INSERT INTO `william_item_price` VALUES ('40574', '灵魂之证', '50');
INSERT INTO `william_item_price` VALUES ('40575', '灵魂之证', '50');
INSERT INTO `william_item_price` VALUES ('40576', '灵魂水晶', '0');
INSERT INTO `william_item_price` VALUES ('40577', '灵魂水晶', '0');
INSERT INTO `william_item_price` VALUES ('40578', '灵魂水晶', '0');
INSERT INTO `william_item_price` VALUES ('40579', '不死族的骨头', '0');
INSERT INTO `william_item_price` VALUES ('40580', '不死族的骨头碎片', '0');
INSERT INTO `william_item_price` VALUES ('40581', '不死族的钥匙', '0');
INSERT INTO `william_item_price` VALUES ('40582', '安迪亚之袋', '0');
INSERT INTO `william_item_price` VALUES ('40583', '安迪亚之信', '50');
INSERT INTO `william_item_price` VALUES ('40584', '雪怪首级', '0');
INSERT INTO `william_item_price` VALUES ('40585', '妖魔长老首级', '0');
INSERT INTO `william_item_price` VALUES ('40586', '王族徽章的碎片', '0');
INSERT INTO `william_item_price` VALUES ('40587', '王族徽章的碎片', '0');
INSERT INTO `william_item_price` VALUES ('40588', '妖精族宝物', '0');
INSERT INTO `william_item_price` VALUES ('40589', '文明的钥匙', '0');
INSERT INTO `william_item_price` VALUES ('40590', '杨果里恩之爪', '760');
INSERT INTO `william_item_price` VALUES ('40591', '受诅咒的魔法书', '50');
INSERT INTO `william_item_price` VALUES ('40592', '受诅咒的精灵书', '50');
INSERT INTO `william_item_price` VALUES ('40593', '调查簿的缺页', '50');
INSERT INTO `william_item_price` VALUES ('40594', '僵尸钥匙', '0');
INSERT INTO `william_item_price` VALUES ('40595', '死亡之证', '0');
INSERT INTO `william_item_price` VALUES ('40596', '死亡誓约', '50');
INSERT INTO `william_item_price` VALUES ('40597', '破损的调查簿', '50');
INSERT INTO `william_item_price` VALUES ('40598', '康之袋', '50');
INSERT INTO `william_item_price` VALUES ('40599', '塔拉斯的魔法袋', '0');
INSERT INTO `william_item_price` VALUES ('40600', '堕落钥匙', '0');
INSERT INTO `william_item_price` VALUES ('40601', '龙龟甲', '451');
INSERT INTO `william_item_price` VALUES ('40602', '蓝色长笛', '0');
INSERT INTO `william_item_price` VALUES ('40603', '蘑菇毒液', '50');
INSERT INTO `william_item_price` VALUES ('40604', '骷髅钥匙', '210');
INSERT INTO `william_item_price` VALUES ('40605', '骷髅头', '760');
INSERT INTO `william_item_price` VALUES ('40606', '混沌钥匙', '0');
INSERT INTO `william_item_price` VALUES ('40607', '返生药水', '0');
INSERT INTO `william_item_price` VALUES ('40608', '黑骑士的誓约', '100');
INSERT INTO `william_item_price` VALUES ('40609', '甘地妖魔魔法书', '100');
INSERT INTO `william_item_price` VALUES ('40610', '那鲁加妖魔魔法书', '100');
INSERT INTO `william_item_price` VALUES ('40611', '都达玛拉妖魔魔法书', '100');
INSERT INTO `william_item_price` VALUES ('40612', '阿吐巴妖魔魔法书', '100');
INSERT INTO `william_item_price` VALUES ('40613', '黑钥匙', '0');
INSERT INTO `william_item_price` VALUES ('40614', '矿物收集文件', '50');
INSERT INTO `william_item_price` VALUES ('40615', '暗影神殿2楼钥匙', '50');
INSERT INTO `william_item_price` VALUES ('40616', '暗影神殿3楼钥匙', '50');
INSERT INTO `william_item_price` VALUES ('40617', '水晶球', '0');
INSERT INTO `william_item_price` VALUES ('40618', '土之气息', '0');
INSERT INTO `william_item_price` VALUES ('40619', '东方监狱钥匙', '0');
INSERT INTO `william_item_price` VALUES ('40620', '第二迷宫钥匙', '0');
INSERT INTO `william_item_price` VALUES ('40621', '德雷克钥匙', '0');
INSERT INTO `william_item_price` VALUES ('40622', '飞龙的爪子', '0');
INSERT INTO `william_item_price` VALUES ('40623', '多鲁嘉1世传家之宝', '0');
INSERT INTO `william_item_price` VALUES ('40624', '多鲁嘉2世传家之宝', '0');
INSERT INTO `william_item_price` VALUES ('40625', '多鲁嘉3世传家之宝', '0');
INSERT INTO `william_item_price` VALUES ('40626', '多鲁嘉4世传家之宝', '0');
INSERT INTO `william_item_price` VALUES ('40627', '多鲁嘉5世传家之宝', '0');
INSERT INTO `william_item_price` VALUES ('40628', '多鲁嘉6世传家之宝', '0');
INSERT INTO `william_item_price` VALUES ('40629', '多鲁嘉7世传家之宝', '0');
INSERT INTO `william_item_price` VALUES ('40630', '迪哥的旧日记', '50');
INSERT INTO `william_item_price` VALUES ('40631', '莱斯塔的戒指', '0');
INSERT INTO `william_item_price` VALUES ('40632', '雷奥纳的袋子', '0');
INSERT INTO `william_item_price` VALUES ('40633', '蜥蜴人的报告', '50');
INSERT INTO `william_item_price` VALUES ('40634', '蜥蜴人的宝物', '0');
INSERT INTO `william_item_price` VALUES ('40635', '法令军团印记', '0');
INSERT INTO `william_item_price` VALUES ('40636', '法令军王印记盒', '0');
INSERT INTO `william_item_price` VALUES ('40637', '玛勒巴的信', '50');
INSERT INTO `william_item_price` VALUES ('40638', '魔兽军团印记', '0');
INSERT INTO `william_item_price` VALUES ('40639', '魔兽军王印记盒', '0');
INSERT INTO `william_item_price` VALUES ('40640', '冥法军王印记盒', '0');
INSERT INTO `william_item_price` VALUES ('40641', '说话卷轴', '50');
INSERT INTO `william_item_price` VALUES ('40642', '冥法军团印记', '0');
INSERT INTO `william_item_price` VALUES ('40643', '水之气息', '0');
INSERT INTO `william_item_price` VALUES ('40644', '迷宫构造图', '50');
INSERT INTO `william_item_price` VALUES ('40645', '风之气息', '0');
INSERT INTO `william_item_price` VALUES ('40646', '蜥蜴的角', '0');
INSERT INTO `william_item_price` VALUES ('40647', '藏宝图碎片', '50');
INSERT INTO `william_item_price` VALUES ('40648', '生锈的刺客之剑', '0');
INSERT INTO `william_item_price` VALUES ('40649', '东北方监狱钥匙', '0');
INSERT INTO `william_item_price` VALUES ('40650', '北方监狱钥匙', '0');
INSERT INTO `william_item_price` VALUES ('40651', '火之气息', '0');
INSERT INTO `william_item_price` VALUES ('40652', '燃烧的皮', '0');
INSERT INTO `william_item_price` VALUES ('40653', '红钥匙', '0');
INSERT INTO `william_item_price` VALUES ('40654', '第三迷宫钥匙', '0');
INSERT INTO `william_item_price` VALUES ('40655', '水晶之牙', '0');
INSERT INTO `william_item_price` VALUES ('40656', '试炼之剑A', '0');
INSERT INTO `william_item_price` VALUES ('40657', '试炼之剑B', '0');
INSERT INTO `william_item_price` VALUES ('40658', '试炼之剑C', '0');
INSERT INTO `william_item_price` VALUES ('40659', '试炼之剑D', '0');
INSERT INTO `william_item_price` VALUES ('40660', '试炼卷轴', '50');
INSERT INTO `william_item_price` VALUES ('40661', '儿子的遗骸', '0');
INSERT INTO `william_item_price` VALUES ('40662', '儿子的肖像画', '50');
INSERT INTO `william_item_price` VALUES ('40663', '儿子的信', '50');
INSERT INTO `william_item_price` VALUES ('40664', '阿拉斯的护身符', '50');
INSERT INTO `william_item_price` VALUES ('40665', '阿拉斯的信', '50');
INSERT INTO `william_item_price` VALUES ('40666', '无法得知的传家之宝', '0');
INSERT INTO `william_item_price` VALUES ('40667', '暗杀军团印记', '0');
INSERT INTO `william_item_price` VALUES ('40668', '暗杀军王印记盒', '0');
INSERT INTO `william_item_price` VALUES ('40669', '火焰之影肋骨', '0');
INSERT INTO `william_item_price` VALUES ('40670', '火焰之影尾巴', '0');
INSERT INTO `william_item_price` VALUES ('40671', '火焰之影骨翼', '0');
INSERT INTO `william_item_price` VALUES ('40672', '火焰之影脊椎', '0');
INSERT INTO `william_item_price` VALUES ('40673', '火焰之影首级', '0');
INSERT INTO `william_item_price` VALUES ('40674', '火焰之影指甲', '0');
INSERT INTO `william_item_price` VALUES ('40675', '黑暗矿石', '0');
INSERT INTO `william_item_price` VALUES ('40676', '闇之气息', '0');
INSERT INTO `william_item_price` VALUES ('40677', '黑暗矿石铸块', '0');
INSERT INTO `william_item_price` VALUES ('40678', '灵魂石碎片', '0');
INSERT INTO `william_item_price` VALUES ('40679', '污浊的金甲', '0');
INSERT INTO `william_item_price` VALUES ('40680', '污浊斗篷', '0');
INSERT INTO `william_item_price` VALUES ('40681', '污浊的钢靴', '0');
INSERT INTO `william_item_price` VALUES ('40682', '污浊的腕甲', '0');
INSERT INTO `william_item_price` VALUES ('40683', '污浊的头盔', '0');
INSERT INTO `william_item_price` VALUES ('40684', '污浊的弓', '0');
INSERT INTO `william_item_price` VALUES ('40685', '未磨光的雕像', '0');
INSERT INTO `william_item_price` VALUES ('40686', '完成品的雕像', '0');
INSERT INTO `william_item_price` VALUES ('40687', '奥姆的袋子', '0');
INSERT INTO `william_item_price` VALUES ('40688', '未上漆的雕像', '0');
INSERT INTO `william_item_price` VALUES ('40689', '未精雕的雕像', '0');
INSERT INTO `william_item_price` VALUES ('40690', '未修补的雕像', '0');
INSERT INTO `william_item_price` VALUES ('40691', '半成品的雕像', '0');
INSERT INTO `william_item_price` VALUES ('40692', '完成的藏宝图', '50');
INSERT INTO `william_item_price` VALUES ('40693', '远征队金甲', '0');
INSERT INTO `william_item_price` VALUES ('40694', '远征队斗篷', '0');
INSERT INTO `william_item_price` VALUES ('40695', '远征队钢靴', '0');
INSERT INTO `william_item_price` VALUES ('40696', '远征队的遗物', '0');
INSERT INTO `william_item_price` VALUES ('40697', '远征队腕甲', '0');
INSERT INTO `william_item_price` VALUES ('40698', '远征队头盔', '0');
INSERT INTO `william_item_price` VALUES ('40699', '远征队弓', '0');
INSERT INTO `william_item_price` VALUES ('40700', '银笛', '0');
INSERT INTO `william_item_price` VALUES ('40701', '小藏宝图', '50');
INSERT INTO `william_item_price` VALUES ('40702', '小袋子', '0');
INSERT INTO `william_item_price` VALUES ('40703', '心灵支配石', '0');
INSERT INTO `william_item_price` VALUES ('40704', '死亡尾骨', '0');
INSERT INTO `william_item_price` VALUES ('40705', '死亡巨斧', '0');
INSERT INTO `william_item_price` VALUES ('40706', '死亡战锤', '0');
INSERT INTO `william_item_price` VALUES ('40707', '死亡首级', '0');
INSERT INTO `william_item_price` VALUES ('40708', '死亡长矛', '0');
INSERT INTO `william_item_price` VALUES ('40709', '死亡之剑', '0');
INSERT INTO `william_item_price` VALUES ('40710', '朋友的袋子', '0');
INSERT INTO `william_item_price` VALUES ('40711', '卡得穆斯项链', '0');
INSERT INTO `william_item_price` VALUES ('40712', '卡立普的高级袋子', '0');
INSERT INTO `william_item_price` VALUES ('40713', '卡立普的袋子', '0');
INSERT INTO `william_item_price` VALUES ('40714', '蓝尾蜥蜴之皮', '0');
INSERT INTO `william_item_price` VALUES ('40715', '皮尔斯的礼物', '0');
INSERT INTO `william_item_price` VALUES ('40716', '爷爷的宝物', '1240');
INSERT INTO `william_item_price` VALUES ('40717', '弄绉的情书', '50');
INSERT INTO `william_item_price` VALUES ('40718', '血石碎片', '0');
INSERT INTO `william_item_price` VALUES ('40719', '混沌首级', '0');
INSERT INTO `william_item_price` VALUES ('40720', '黑暗之翼', '0');
INSERT INTO `william_item_price` VALUES ('40721', '巨大南瓜种子', '0');
INSERT INTO `william_item_price` VALUES ('40722', '金南瓜', '990');
INSERT INTO `william_item_price` VALUES ('40723', '银南瓜', '10');
INSERT INTO `william_item_price` VALUES ('40724', '铜南瓜', '1010');
INSERT INTO `william_item_price` VALUES ('40725', '南瓜糖果', '1020');
INSERT INTO `william_item_price` VALUES ('40726', '南瓜种子', '1030');
INSERT INTO `william_item_price` VALUES ('40727', '绿短袜', '1040');
INSERT INTO `william_item_price` VALUES ('40728', '红短袜', '1050');
INSERT INTO `william_item_price` VALUES ('40729', '金袜子', '1060');
INSERT INTO `william_item_price` VALUES ('40730', '圣诞卡片', '1070');
INSERT INTO `william_item_price` VALUES ('40731', '情人节卡片', '1080');
INSERT INTO `william_item_price` VALUES ('40732', '白色情人节卡片', '1090');
INSERT INTO `william_item_price` VALUES ('40733', '名誉货币', '1100');
INSERT INTO `william_item_price` VALUES ('40734', '信赖货币', '1110');
INSERT INTO `william_item_price` VALUES ('40735', '勇气货币', '1120');
INSERT INTO `william_item_price` VALUES ('40736', '智慧货币', '1130');
INSERT INTO `william_item_price` VALUES ('40737', '藏宝箱', '50');
INSERT INTO `william_item_price` VALUES ('40738', '银飞刀', '0');
INSERT INTO `william_item_price` VALUES ('40739', '飞刀', '0');
INSERT INTO `william_item_price` VALUES ('40740', '重飞刀', '0');
INSERT INTO `william_item_price` VALUES ('40741', '奥里哈鲁根镀金骨箭', '0');
INSERT INTO `william_item_price` VALUES ('40742', '古代之箭', '0');
INSERT INTO `william_item_price` VALUES ('40743', '箭', '0');
INSERT INTO `william_item_price` VALUES ('40744', '银箭', '0');
INSERT INTO `william_item_price` VALUES ('40745', '黄金箭', '0');
INSERT INTO `william_item_price` VALUES ('40746', '米索莉箭', '0');
INSERT INTO `william_item_price` VALUES ('40747', '黑色米索莉箭', '0');
INSERT INTO `william_item_price` VALUES ('40748', '奥里哈鲁根箭', '0');
INSERT INTO `william_item_price` VALUES ('40749', '猎犬之牙', '250');
INSERT INTO `william_item_price` VALUES ('40750', '破灭之牙', '250');
INSERT INTO `william_item_price` VALUES ('40751', '斗犬之牙', '250');
INSERT INTO `william_item_price` VALUES ('40752', '黄金之牙', '250');
INSERT INTO `william_item_price` VALUES ('40753', '猎犬之牙', '250');
INSERT INTO `william_item_price` VALUES ('40754', '猎犬之牙', '250');
INSERT INTO `william_item_price` VALUES ('40755', '猎犬之牙', '250');
INSERT INTO `william_item_price` VALUES ('40756', '神之牙', '250');
INSERT INTO `william_item_price` VALUES ('40757', '钢铁之牙', '450');
INSERT INTO `william_item_price` VALUES ('40758', '胜利之牙', '450');
INSERT INTO `william_item_price` VALUES ('40759', '猎犬之牙', '450');
INSERT INTO `william_item_price` VALUES ('40760', '猎犬之牙', '450');
INSERT INTO `william_item_price` VALUES ('40761', '宠物皮盔甲', '7500');
INSERT INTO `william_item_price` VALUES ('40762', '宠物骷髅盔甲', '7500');
INSERT INTO `william_item_price` VALUES ('40763', '宠物钢铁盔甲', '250');
INSERT INTO `william_item_price` VALUES ('40764', '宠物米索莉盔甲', '250');
INSERT INTO `william_item_price` VALUES ('40765', '宠物十字盔甲', '250');
INSERT INTO `william_item_price` VALUES ('40766', '宠物链甲', '250');
INSERT INTO `william_item_price` VALUES ('40778', '皮带', '1');
INSERT INTO `william_item_price` VALUES ('40779', '钢铁块', '1');
INSERT INTO `william_item_price` VALUES ('40801', '指定传送(黄昏山脉)', '500');
INSERT INTO `william_item_price` VALUES ('40802', '指定传送(奇岩竞技场)', '500');
INSERT INTO `william_item_price` VALUES ('40803', '指定传送(镜子森林)', '500');
INSERT INTO `william_item_price` VALUES ('40804', '指定传送(巴拉卡斯栖息地)', '500');
INSERT INTO `william_item_price` VALUES ('40805', '指定传送(法利昂栖息地)', '500');
INSERT INTO `william_item_price` VALUES ('40806', '指定传送(林德拜尔栖息地)', '500');
INSERT INTO `william_item_price` VALUES ('40807', '指定传送(海音洞穴3F)', '200');
INSERT INTO `william_item_price` VALUES ('40808', '指定传送(海音洞穴4F)', '200');
INSERT INTO `william_item_price` VALUES ('40809', '指定传送(火窟)', '200');
INSERT INTO `william_item_price` VALUES ('40810', '指定传送(龙之谷入口)', '500');
INSERT INTO `william_item_price` VALUES ('40811', '指定传送(沙漠)', '200');
INSERT INTO `william_item_price` VALUES ('40812', '指定传送(欧瑞)', '200');
INSERT INTO `william_item_price` VALUES ('40813', '指定传送(远古战场)', '200');
INSERT INTO `william_item_price` VALUES ('40814', '指定传送(食尸地)', '200');
INSERT INTO `william_item_price` VALUES ('40815', '指定传送(风木地监1F)', '200');
INSERT INTO `william_item_price` VALUES ('40816', '指定传送(风木地监2F)', '200');
INSERT INTO `william_item_price` VALUES ('40817', '指定传送(巨蚁洞穴)', '200');
INSERT INTO `william_item_price` VALUES ('40818', '指定传送(巨蚁洞穴)', '200');
INSERT INTO `william_item_price` VALUES ('40819', '指定传送(巨蚁女皇栖息地)', '200');
INSERT INTO `william_item_price` VALUES ('40820', '指定传送(象牙塔5F)', '200');
INSERT INTO `william_item_price` VALUES ('40821', '指定传送(象牙塔6F)', '200');
INSERT INTO `william_item_price` VALUES ('40822', '指定传送(象牙塔7F)', '200');
INSERT INTO `william_item_price` VALUES ('40823', '指定传送(象牙塔8F)', '200');
INSERT INTO `william_item_price` VALUES ('40824', '指定传送(骑士洞穴2F)', '200');
INSERT INTO `william_item_price` VALUES ('40825', '指定传送(骑士洞穴3F)', '200');
INSERT INTO `william_item_price` VALUES ('40826', '指定传送(骑士洞穴4F)', '200');
INSERT INTO `william_item_price` VALUES ('40827', '指定传送(奇岩地监2F)', '200');
INSERT INTO `william_item_price` VALUES ('40828', '指定传送(奇岩地监3F)', '200');
INSERT INTO `william_item_price` VALUES ('40829', '指定传送(奇岩地监4F)', '200');
INSERT INTO `william_item_price` VALUES ('40830', '指定传送(古鲁丁地监3F)', '200');
INSERT INTO `william_item_price` VALUES ('40831', '指定传送(古鲁丁地监4F)', '200');
INSERT INTO `william_item_price` VALUES ('40832', '指定传送(古鲁丁地监5F)', '200');
INSERT INTO `william_item_price` VALUES ('40833', '指定传送(古鲁丁地监6F)', '200');
INSERT INTO `william_item_price` VALUES ('40834', '指定传送(古鲁丁地监7F)', '200');
INSERT INTO `william_item_price` VALUES ('40835', '指定传送(龙之谷地监1F)', '200');
INSERT INTO `william_item_price` VALUES ('40836', '指定传送(龙之谷地监2F)', '200');
INSERT INTO `william_item_price` VALUES ('40837', '指定传送(龙之谷地监3F)', '200');
INSERT INTO `william_item_price` VALUES ('40838', '指定传送(龙之谷地监4F)', '200');
INSERT INTO `william_item_price` VALUES ('40839', '指定传送(龙之谷地监5F)', '200');
INSERT INTO `william_item_price` VALUES ('40840', '指定传送(龙之谷地监6F)', '200');
INSERT INTO `william_item_price` VALUES ('40841', '指定传送(安塔瑞斯栖息地)', '200');
INSERT INTO `william_item_price` VALUES ('40842', '指定传送(风木城)', '200');
INSERT INTO `william_item_price` VALUES ('40843', '指定传送(风木沙漠)', '200');
INSERT INTO `william_item_price` VALUES ('40844', '指定传送(布鲁迪卡洞)', '200');
INSERT INTO `william_item_price` VALUES ('40845', '指定传送(沉默洞穴)', '200');
INSERT INTO `william_item_price` VALUES ('40846', '指定传送(拉斯塔巴德地下洞穴1F)', '200');
INSERT INTO `william_item_price` VALUES ('40847', '指定传送(拉斯塔巴德地下洞穴2F)', '200');
INSERT INTO `william_item_price` VALUES ('40848', '指定传送(拉斯塔巴德地下洞穴3F)', '200');
INSERT INTO `william_item_price` VALUES ('40849', '指定传送(古代人空间1F)', '200');
INSERT INTO `william_item_price` VALUES ('40850', '指定传送(古代人空间2F)', '200');
INSERT INTO `william_item_price` VALUES ('40851', '指定传送(古代人空间4F)', '200');
INSERT INTO `william_item_price` VALUES ('40852', '指定传送(奥姆地监)', '200');
INSERT INTO `william_item_price` VALUES ('40853', '指定传送(大洞穴抵抗军地区)', '200');
INSERT INTO `william_item_price` VALUES ('40854', '指定传送(魔族神殿)', '200');
INSERT INTO `william_item_price` VALUES ('40855', '指定传送(精灵墓穴)', '200');
INSERT INTO `william_item_price` VALUES ('40856', '指定传送(海贼岛)', '200');
INSERT INTO `william_item_price` VALUES ('40857', '指定传送(拉斯塔巴德正门)', '200');
INSERT INTO `william_item_price` VALUES ('40858', '人头马白兰地XO', '200');
INSERT INTO `william_item_price` VALUES ('40859', '魔法卷轴(初级治愈术)', '20');
INSERT INTO `william_item_price` VALUES ('40860', '魔法卷轴(日光术)', '20');
INSERT INTO `william_item_price` VALUES ('40861', '魔法卷轴(保护罩)', '20');
INSERT INTO `william_item_price` VALUES ('40862', '魔法卷轴(光箭)', '20');
INSERT INTO `william_item_price` VALUES ('40863', '魔法卷轴(指定传送)', '20');
INSERT INTO `william_item_price` VALUES ('40864', '魔法卷轴(冰箭)', '20');
INSERT INTO `william_item_price` VALUES ('40865', '魔法卷轴(风刃)', '20');
INSERT INTO `william_item_price` VALUES ('40866', '魔法卷轴(神圣武器)', '20');
INSERT INTO `william_item_price` VALUES ('40867', '魔法卷轴(解毒术)', '40');
INSERT INTO `william_item_price` VALUES ('40868', '魔法卷轴(寒冷战栗)', '40');
INSERT INTO `william_item_price` VALUES ('40869', '魔法卷轴(毒咒)', '40');
INSERT INTO `william_item_price` VALUES ('40870', '魔法卷轴(拟似魔法武器)', '40');
INSERT INTO `william_item_price` VALUES ('40871', '魔法卷轴(无所遁形术)', '40');
INSERT INTO `william_item_price` VALUES ('40872', '魔法卷轴(负重强化)', '40');
INSERT INTO `william_item_price` VALUES ('40873', '魔法卷轴(火箭)', '40');
INSERT INTO `william_item_price` VALUES ('40874', '魔法卷轴(地狱之牙)', '40');
INSERT INTO `william_item_price` VALUES ('40875', '魔法卷轴(极光雷电)', '80');
INSERT INTO `william_item_price` VALUES ('40876', '魔法卷轴(起死回生术)', '80');
INSERT INTO `william_item_price` VALUES ('40877', '魔法卷轴(中级治愈术)', '80');
INSERT INTO `william_item_price` VALUES ('40878', '魔法卷轴(闇盲咒术)', '80');
INSERT INTO `william_item_price` VALUES ('40879', '魔法卷轴(铠甲护持)', '80');
INSERT INTO `william_item_price` VALUES ('40880', '魔法卷轴(寒冰气息)', '80');
INSERT INTO `william_item_price` VALUES ('40881', '魔法卷轴(能量感测)', '80');
INSERT INTO `william_item_price` VALUES ('40883', '魔法卷轴(燃烧的火球)', '160');
INSERT INTO `william_item_price` VALUES ('40884', '魔法卷轴(通畅气脉术)', '160');
INSERT INTO `william_item_price` VALUES ('40885', '魔法卷轴(坏物术)', '160');
INSERT INTO `william_item_price` VALUES ('40886', '魔法卷轴(吸血鬼之吻)', '160');
INSERT INTO `william_item_price` VALUES ('40887', '魔法卷轴(缓速术)', '160');
INSERT INTO `william_item_price` VALUES ('40888', '魔法卷轴(岩牢)', '160');
INSERT INTO `william_item_price` VALUES ('40889', '魔法卷轴(魔法屏障)', '160');
INSERT INTO `william_item_price` VALUES ('40890', '魔法卷轴(冥想术)', '160');
INSERT INTO `william_item_price` VALUES ('40891', '魔法卷轴(木乃伊的诅咒)', '320');
INSERT INTO `william_item_price` VALUES ('40892', '魔法卷轴(极道落雷)', '320');
INSERT INTO `william_item_price` VALUES ('40893', '魔法卷轴(高级治愈术)', '320');
INSERT INTO `william_item_price` VALUES ('40894', '魔法卷轴(迷魅术)', '320');
INSERT INTO `william_item_price` VALUES ('40895', '魔法卷轴(圣洁之光)', '30');
INSERT INTO `william_item_price` VALUES ('40896', '魔法卷轴(冰锥)', '30');
INSERT INTO `william_item_price` VALUES ('40897', '魔法卷轴(魔力夺取)', '320');
INSERT INTO `william_item_price` VALUES ('40898', '魔法卷轴(黑闇之影)', '320');
INSERT INTO `william_item_price` VALUES ('40899', '钢铁原石', '0');
INSERT INTO `william_item_price` VALUES ('40901', '结婚戒指(银)', '2500');
INSERT INTO `william_item_price` VALUES ('40902', '结婚戒指(金)', '50');
INSERT INTO `william_item_price` VALUES ('40903', '结婚戒指(蓝宝石)', '2500');
INSERT INTO `william_item_price` VALUES ('40904', '结婚戒指(绿宝石)', '2500');
INSERT INTO `william_item_price` VALUES ('40905', '结婚戒指(红宝石)', '2500');
INSERT INTO `william_item_price` VALUES ('40906', '结婚戒指(钻石)', '2500');
INSERT INTO `william_item_price` VALUES ('40907', '西玛戒指', '0');
INSERT INTO `william_item_price` VALUES ('40908', '欧林戒指', '0');
INSERT INTO `william_item_price` VALUES ('40929', '四阶神秘药水', '0');
INSERT INTO `william_item_price` VALUES ('40930', '烤肉', '0');
INSERT INTO `william_item_price` VALUES ('40931', '精工的蓝宝石', '0');
INSERT INTO `william_item_price` VALUES ('40932', '精工的品质蓝宝石', '0');
INSERT INTO `william_item_price` VALUES ('40933', '精工的高品质蓝宝石', '0');
INSERT INTO `william_item_price` VALUES ('40934', '精工的极品蓝宝石', '0');
INSERT INTO `william_item_price` VALUES ('40935', '精工的绿宝石', '0');
INSERT INTO `william_item_price` VALUES ('40936', '精工的品质绿宝石', '0');
INSERT INTO `william_item_price` VALUES ('40937', '精工的高品质绿宝石', '0');
INSERT INTO `william_item_price` VALUES ('40938', '精工的极品绿宝石', '0');
INSERT INTO `william_item_price` VALUES ('40939', '精工的红宝石', '0');
INSERT INTO `william_item_price` VALUES ('40940', '精工的品质红宝石', '0');
INSERT INTO `william_item_price` VALUES ('40941', '精工的高品质红宝石', '0');
INSERT INTO `william_item_price` VALUES ('40942', '精工的极品红宝石', '0');
INSERT INTO `william_item_price` VALUES ('40943', '精工的土之钻', '0');
INSERT INTO `william_item_price` VALUES ('40944', '精工的品质土之钻', '0');
INSERT INTO `william_item_price` VALUES ('40945', '精工的高品质土之钻', '0');
INSERT INTO `william_item_price` VALUES ('40946', '精工的极品土之钻', '0');
INSERT INTO `william_item_price` VALUES ('40947', '精工的水之钻', '0');
INSERT INTO `william_item_price` VALUES ('40948', '精工的品质水之钻', '0');
INSERT INTO `william_item_price` VALUES ('40949', '精工的高品质水之钻', '0');
INSERT INTO `william_item_price` VALUES ('40950', '精工的极品水之钻', '0');
INSERT INTO `william_item_price` VALUES ('40951', '精工的火之钻', '0');
INSERT INTO `william_item_price` VALUES ('40952', '精工的品质火之钻', '0');
INSERT INTO `william_item_price` VALUES ('40953', '精工的高品质火之钻', '0');
INSERT INTO `william_item_price` VALUES ('40954', '精工的极品火之钻', '0');
INSERT INTO `william_item_price` VALUES ('40955', '精工的风之钻', '0');
INSERT INTO `william_item_price` VALUES ('40956', '精工的品质风之钻', '0');
INSERT INTO `william_item_price` VALUES ('40957', '精工的高品质风之钻', '0');
INSERT INTO `william_item_price` VALUES ('40958', '精工的极品风之钻', '0');
INSERT INTO `william_item_price` VALUES ('40959', '冥法军王徽印', '0');
INSERT INTO `william_item_price` VALUES ('40960', '法令军王徽印', '0');
INSERT INTO `william_item_price` VALUES ('40961', '魔兽军王徽印', '0');
INSERT INTO `william_item_price` VALUES ('40962', '暗杀军王徽印', '0');
INSERT INTO `william_item_price` VALUES ('40964', '黑魔法粉', '0');
INSERT INTO `william_item_price` VALUES ('40965', '拉斯塔巴德制作武器秘笈', '0');
INSERT INTO `william_item_price` VALUES ('40966', '真．冥皇制作防具秘笈', '0');
INSERT INTO `william_item_price` VALUES ('40967', '圣地遗物', '0');
INSERT INTO `william_item_price` VALUES ('40968', '修行者经典', '0');
INSERT INTO `william_item_price` VALUES ('40969', '黑暗妖精的灵魂水晶', '0');
INSERT INTO `william_item_price` VALUES ('40970', '安加斯的尾巴', '0');
INSERT INTO `william_item_price` VALUES ('40971', '安加斯之牙', '0');
INSERT INTO `william_item_price` VALUES ('40972', '巴萨斯的气息', '0');
INSERT INTO `william_item_price` VALUES ('40973', '巴萨斯的翅膀', '0');
INSERT INTO `william_item_price` VALUES ('40974', '狄高的血', '0');
INSERT INTO `william_item_price` VALUES ('40975', '狄高的鳍', '0');
INSERT INTO `william_item_price` VALUES ('40976', '沙', '0');
INSERT INTO `william_item_price` VALUES ('40977', '染血的沙', '0');
INSERT INTO `william_item_price` VALUES ('40978', '地之守护者的尾巴', '0');
INSERT INTO `william_item_price` VALUES ('40979', '水之守护者的尾巴', '0');
INSERT INTO `william_item_price` VALUES ('40980', '火之守护者的尾巴', '0');
INSERT INTO `william_item_price` VALUES ('40981', '风之守护者的尾巴', '0');
INSERT INTO `william_item_price` VALUES ('40982', '地之守护者的皮', '0');
INSERT INTO `william_item_price` VALUES ('40983', '水之守护者的皮', '0');
INSERT INTO `william_item_price` VALUES ('40984', '火之守护者的皮', '0');
INSERT INTO `william_item_price` VALUES ('40985', '风之守护者的皮', '0');
INSERT INTO `william_item_price` VALUES ('40986', '守护者之牙', '0');
INSERT INTO `william_item_price` VALUES ('40987', '受诅咒的黑色耳环', '0');
INSERT INTO `william_item_price` VALUES ('40988', '受诅咒的黑色耳环', '0');
INSERT INTO `william_item_price` VALUES ('40989', '受诅咒的黑色耳环', '0');
INSERT INTO `william_item_price` VALUES ('40990', '炎魔的翅膀', '0');
INSERT INTO `william_item_price` VALUES ('40991', '炎魔双手剑', '0');
INSERT INTO `william_item_price` VALUES ('40992', '炎魔的头', '0');
INSERT INTO `william_item_price` VALUES ('40993', '炎魔的角', '0');
INSERT INTO `william_item_price` VALUES ('40994', '炎魔之肉', '0');
INSERT INTO `william_item_price` VALUES ('40995', '炎魔之指', '0');
INSERT INTO `william_item_price` VALUES ('40996', '炎魔的心脏', '0');
INSERT INTO `william_item_price` VALUES ('40997', '炎魔之牙', '0');
INSERT INTO `william_item_price` VALUES ('40998', '炎魔之肺', '0');
INSERT INTO `william_item_price` VALUES ('40999', '黑暗妖精士兵徽章', '0');
INSERT INTO `william_item_price` VALUES ('41000', '黑暗妖精将军徽章', '0');
INSERT INTO `william_item_price` VALUES ('41001', '报偿金', '0');
INSERT INTO `william_item_price` VALUES ('41002', '矿物袋子', '0');
INSERT INTO `william_item_price` VALUES ('41003', '罗伊的袋子', '0');
INSERT INTO `william_item_price` VALUES ('41004', '拉布罗的袋子', '0');
INSERT INTO `william_item_price` VALUES ('41005', '复活与永生之誓约书', '0');
INSERT INTO `william_item_price` VALUES ('41006', '拉伯勒的袋子', '0');
INSERT INTO `william_item_price` VALUES ('41007', '伊莉丝的命令书：灵魂之安息', '0');
INSERT INTO `william_item_price` VALUES ('41008', '伊莉丝的袋子', '0');
INSERT INTO `william_item_price` VALUES ('41009', '伊莉丝的命令书：同盟之意志', '0');
INSERT INTO `william_item_price` VALUES ('41010', '伊莉丝的推荐函', '0');
INSERT INTO `william_item_price` VALUES ('41011', '封印的历史书第1页', '0');
INSERT INTO `william_item_price` VALUES ('41012', '封印的历史书第2页', '0');
INSERT INTO `william_item_price` VALUES ('41013', '封印的历史书第3页', '0');
INSERT INTO `william_item_price` VALUES ('41014', '封印的历史书第4页', '0');
INSERT INTO `william_item_price` VALUES ('41015', '封印的历史书第5页', '0');
INSERT INTO `william_item_price` VALUES ('41016', '封印的历史书第6页', '0');
INSERT INTO `william_item_price` VALUES ('41017', '封印的历史书第7页', '0');
INSERT INTO `william_item_price` VALUES ('41018', '封印的历史书第8页', '0');
INSERT INTO `william_item_price` VALUES ('41019', '拉斯塔巴德历史书第1页', '0');
INSERT INTO `william_item_price` VALUES ('41020', '拉斯塔巴德历史书第2页', '0');
INSERT INTO `william_item_price` VALUES ('41021', '拉斯塔巴德历史书第3页', '0');
INSERT INTO `william_item_price` VALUES ('41022', '拉斯塔巴德历史书第4页', '0');
INSERT INTO `william_item_price` VALUES ('41023', '拉斯塔巴德历史书第5页', '0');
INSERT INTO `william_item_price` VALUES ('41024', '拉斯塔巴德历史书第6页', '0');
INSERT INTO `william_item_price` VALUES ('41025', '拉斯塔巴德历史书第7页', '0');
INSERT INTO `william_item_price` VALUES ('41026', '拉斯塔巴德历史书第8页', '0');
INSERT INTO `william_item_price` VALUES ('41027', '完整的拉斯塔巴德历史书', '0');
INSERT INTO `william_item_price` VALUES ('41028', '死亡骑士之书', '0');
INSERT INTO `william_item_price` VALUES ('41029', '召唤球之核', '0');
INSERT INTO `william_item_price` VALUES ('41030', '召唤球碎片', '0');
INSERT INTO `william_item_price` VALUES ('41031', '一阶段召唤球', '0');
INSERT INTO `william_item_price` VALUES ('41032', '二阶段召唤球', '0');
INSERT INTO `william_item_price` VALUES ('41033', '三阶段召唤球', '0');
INSERT INTO `william_item_price` VALUES ('41034', '四阶段召唤球', '0');
INSERT INTO `william_item_price` VALUES ('41035', '完整的召唤球', '0');
INSERT INTO `william_item_price` VALUES ('41036', '胶水', '0');
INSERT INTO `william_item_price` VALUES ('41037', '不完整的航海日志', '0');
INSERT INTO `william_item_price` VALUES ('41038', '航海日志第1页', '0');
INSERT INTO `william_item_price` VALUES ('41039', '航海日志第2页', '0');
INSERT INTO `william_item_price` VALUES ('41040', '航海日志第3页', '0');
INSERT INTO `william_item_price` VALUES ('41041', '航海日志第4页', '0');
INSERT INTO `william_item_price` VALUES ('41042', '航海日志第5页', '0');
INSERT INTO `william_item_price` VALUES ('41043', '航海日志第6页', '0');
INSERT INTO `william_item_price` VALUES ('41044', '航海日志第7页', '0');
INSERT INTO `william_item_price` VALUES ('41045', '航海日志第8页', '0');
INSERT INTO `william_item_price` VALUES ('41046', '航海日志第9页', '0');
INSERT INTO `william_item_price` VALUES ('41047', '航海日志第10页', '0');
INSERT INTO `william_item_price` VALUES ('41048', '涂着胶水的航海日志第1页', '0');
INSERT INTO `william_item_price` VALUES ('41049', '涂着胶水的航海日志第2页', '0');
INSERT INTO `william_item_price` VALUES ('41050', '涂着胶水的航海日志第3页', '0');
INSERT INTO `william_item_price` VALUES ('41051', '涂着胶水的航海日志第4页', '0');
INSERT INTO `william_item_price` VALUES ('41052', '涂着胶水的航海日志第5页', '0');
INSERT INTO `william_item_price` VALUES ('41053', '涂着胶水的航海日志第6页', '0');
INSERT INTO `william_item_price` VALUES ('41054', '涂着胶水的航海日志第7页', '0');
INSERT INTO `william_item_price` VALUES ('41055', '涂着胶水的航海日志第8页', '0');
INSERT INTO `william_item_price` VALUES ('41056', '涂着胶水的航海日志第9页', '0');
INSERT INTO `william_item_price` VALUES ('41057', '涂着胶水的航海日志第10页', '0');
INSERT INTO `william_item_price` VALUES ('41058', '完整的航海日志', '0');
INSERT INTO `william_item_price` VALUES ('41059', '航海士的袋子', '0');
INSERT INTO `william_item_price` VALUES ('41060', '诺曼阿吐巴的信', '0');
INSERT INTO `william_item_price` VALUES ('41061', '妖精调查书：卡麦都达玛拉', '0');
INSERT INTO `william_item_price` VALUES ('41062', '人类调查书：巴库摩那鲁加', '0');
INSERT INTO `william_item_price` VALUES ('41063', '精灵调查书：可普都达玛拉', '0');
INSERT INTO `william_item_price` VALUES ('41064', '妖魔调查书：弧邬牟那鲁加', '0');
INSERT INTO `william_item_price` VALUES ('41065', '死亡之树调查书：诺亚阿吐巴', '0');
INSERT INTO `william_item_price` VALUES ('41066', '污浊的根', '0');
INSERT INTO `william_item_price` VALUES ('41067', '污浊的树枝', '0');
INSERT INTO `william_item_price` VALUES ('41068', '污浊的皮', '0');
INSERT INTO `william_item_price` VALUES ('41069', '污浊的鬃毛', '0');
INSERT INTO `william_item_price` VALUES ('41070', '污浊的精灵羽翼', '0');
INSERT INTO `william_item_price` VALUES ('41071', '银盘', '0');
INSERT INTO `william_item_price` VALUES ('41072', '银烛台', '0');
INSERT INTO `william_item_price` VALUES ('41073', '强盗钥匙', '0');
INSERT INTO `william_item_price` VALUES ('41074', '强盗的袋子', '0');
INSERT INTO `william_item_price` VALUES ('41075', '污浊的头发', '0');
INSERT INTO `william_item_price` VALUES ('41076', '土核晶', '0');
INSERT INTO `william_item_price` VALUES ('41077', '水核晶', '0');
INSERT INTO `william_item_price` VALUES ('41078', '火核晶', '0');
INSERT INTO `william_item_price` VALUES ('41079', '风核晶', '0');
INSERT INTO `william_item_price` VALUES ('41080', '精灵核晶', '0');
INSERT INTO `william_item_price` VALUES ('41081', '妖魔尖牙', '0');
INSERT INTO `william_item_price` VALUES ('41082', '妖魔尖牙项链', '0');
INSERT INTO `william_item_price` VALUES ('41083', '咒术粉', '0');
INSERT INTO `william_item_price` VALUES ('41084', '幻觉之粉', '0');
INSERT INTO `william_item_price` VALUES ('41085', '预言家珍珠', '0');
INSERT INTO `william_item_price` VALUES ('41086', '树精的根', '0');
INSERT INTO `william_item_price` VALUES ('41087', '树精的树皮', '0');
INSERT INTO `william_item_price` VALUES ('41088', '树精的叶子', '0');
INSERT INTO `william_item_price` VALUES ('41089', '树精的树枝', '0');
INSERT INTO `william_item_price` VALUES ('41090', '那鲁加图腾', '0');
INSERT INTO `william_item_price` VALUES ('41091', '都达玛拉图腾', '0');
INSERT INTO `william_item_price` VALUES ('41092', '阿吐巴图腾', '0');
INSERT INTO `william_item_price` VALUES ('41093', '梦幻的熊娃娃', '0');
INSERT INTO `william_item_price` VALUES ('41094', '诱惑的香水', '0');
INSERT INTO `william_item_price` VALUES ('41095', '漂亮的洋装', '0');
INSERT INTO `william_item_price` VALUES ('41096', '华丽的戒指', '0');
INSERT INTO `william_item_price` VALUES ('41097', '爱玛伊的心', '0');
INSERT INTO `william_item_price` VALUES ('41098', '英雄传记', '0');
INSERT INTO `william_item_price` VALUES ('41099', '时髦的帽子', '0');
INSERT INTO `william_item_price` VALUES ('41100', '高级红酒', '0');
INSERT INTO `william_item_price` VALUES ('41101', '神秘的钥匙', '0');
INSERT INTO `william_item_price` VALUES ('41102', '伊森之心', '0');
INSERT INTO `william_item_price` VALUES ('41103', '石头块', '250');
INSERT INTO `william_item_price` VALUES ('41104', '铁矿石', '500');
INSERT INTO `william_item_price` VALUES ('41105', '火山岩', '500');
INSERT INTO `william_item_price` VALUES ('41106', '玛依奴的尾巴毛', '250');
INSERT INTO `william_item_price` VALUES ('41107', '盔甲片', '250');
INSERT INTO `william_item_price` VALUES ('41108', '钻石原石', '250');
INSERT INTO `william_item_price` VALUES ('41109', '玛依奴夏门的尾巴毛', '250');
INSERT INTO `william_item_price` VALUES ('41110', '遗物袋', '0');
INSERT INTO `william_item_price` VALUES ('41111', '破旧的遗物袋', '0');
INSERT INTO `william_item_price` VALUES ('41112', '旧遗物袋', '0');
INSERT INTO `william_item_price` VALUES ('41113', '褪色戒指', '0');
INSERT INTO `william_item_price` VALUES ('41114', '染血的手帕', '0');
INSERT INTO `william_item_price` VALUES ('41115', '染血的文件', '0');
INSERT INTO `william_item_price` VALUES ('41116', '褪色项链', '0');
INSERT INTO `william_item_price` VALUES ('41117', '破旧的钱包', '0');
INSERT INTO `william_item_price` VALUES ('41118', '染血的匕首', '0');
INSERT INTO `william_item_price` VALUES ('41119', '遗失的钥匙', '0');
INSERT INTO `william_item_price` VALUES ('41120', '玛雅的魔杖', '0');
INSERT INTO `william_item_price` VALUES ('41121', '火焰之影的契约书', '0');
INSERT INTO `william_item_price` VALUES ('41122', '火焰之影的契约', '0');
INSERT INTO `william_item_price` VALUES ('41123', '火焰之影的堕落粉', '0');
INSERT INTO `william_item_price` VALUES ('41124', '火焰之影的无力粉', '0');
INSERT INTO `william_item_price` VALUES ('41125', '火焰之影的执着粉', '0');
INSERT INTO `william_item_price` VALUES ('41126', '炎魔的堕落井水', '0');
INSERT INTO `william_item_price` VALUES ('41127', '炎魔的无力井水', '0');
INSERT INTO `william_item_price` VALUES ('41128', '炎魔的执着井水', '0');
INSERT INTO `william_item_price` VALUES ('41129', '炎魔的井水', '0');
INSERT INTO `william_item_price` VALUES ('41130', '炎魔的契约书', '0');
INSERT INTO `william_item_price` VALUES ('41131', '炎魔契的契约', '0');
INSERT INTO `william_item_price` VALUES ('41132', '炎魔的堕落粉', '0');
INSERT INTO `william_item_price` VALUES ('41133', '炎魔的无力粉', '0');
INSERT INTO `william_item_price` VALUES ('41134', '炎魔的执着粉', '0');
INSERT INTO `william_item_price` VALUES ('41135', '火焰之影的堕落井水', '0');
INSERT INTO `william_item_price` VALUES ('41136', '火焰之影的无力井水', '0');
INSERT INTO `william_item_price` VALUES ('41137', '火焰之影的执着井水', '0');
INSERT INTO `william_item_price` VALUES ('41138', '火焰之影的井水', '0');
INSERT INTO `william_item_price` VALUES ('41139', '不起眼的古老项链', '0');
INSERT INTO `william_item_price` VALUES ('41140', '复原的古老项链', '0');
INSERT INTO `william_item_price` VALUES ('41141', '神秘的体力药水', '0');
INSERT INTO `william_item_price` VALUES ('41142', '神秘的魔力药水', '0');
INSERT INTO `william_item_price` VALUES ('41143', '海贼骷髅首领变身药水', '0');
INSERT INTO `william_item_price` VALUES ('41144', '海贼骷髅士兵变身药水', '0');
INSERT INTO `william_item_price` VALUES ('41145', '海贼骷髅刀手变身药水', '0');
INSERT INTO `william_item_price` VALUES ('41146', '$10001', '0');
INSERT INTO `william_item_price` VALUES ('41147', '技术书(坚固防护)', '0');
INSERT INTO `william_item_price` VALUES ('41148', '技术书(反击屏障)', '0');
INSERT INTO `william_item_price` VALUES ('41149', '精灵水晶(烈焰之魂)', '500');
INSERT INTO `william_item_price` VALUES ('41150', '精灵水晶(能量激发)', '500');
INSERT INTO `william_item_price` VALUES ('41151', '精灵水晶(水之防护)', '500');
INSERT INTO `william_item_price` VALUES ('41152', '精灵水晶(污浊之水)', '500');
INSERT INTO `william_item_price` VALUES ('41153', '精灵水晶(精准射击)', '500');
INSERT INTO `william_item_price` VALUES ('41154', '暗之鳞', '0');
INSERT INTO `william_item_price` VALUES ('41155', '火之鳞', '0');
INSERT INTO `william_item_price` VALUES ('41156', '叛之鳞', '0');
INSERT INTO `william_item_price` VALUES ('41157', '恨之鳞', '0');
INSERT INTO `william_item_price` VALUES ('41158', '玛雅的水晶球', '0');
INSERT INTO `william_item_price` VALUES ('41159', '神秘的羽毛', '0');
INSERT INTO `william_item_price` VALUES ('41160', '宠物召唤笛', '250');
INSERT INTO `william_item_price` VALUES ('41161', '黑色耳环', '0');
INSERT INTO `william_item_price` VALUES ('41162', '黑色耳环', '0');
INSERT INTO `william_item_price` VALUES ('41163', '黑色耳环', '0');
INSERT INTO `william_item_price` VALUES ('41164', '神秘的黑色耳环', '0');
INSERT INTO `william_item_price` VALUES ('41165', '神秘的黑色耳环', '0');
INSERT INTO `william_item_price` VALUES ('41166', '神秘的黑色耳环', '0');
INSERT INTO `william_item_price` VALUES ('41167', '斗士耳环', '0');
INSERT INTO `william_item_price` VALUES ('41168', '神秘的斗士耳环', '0');
INSERT INTO `william_item_price` VALUES ('41169', '灰色斗士耳环', '0');
INSERT INTO `william_item_price` VALUES ('41170', '神秘的灰色斗士耳环', '0');
INSERT INTO `william_item_price` VALUES ('41171', '白色斗士耳环', '0');
INSERT INTO `william_item_price` VALUES ('41172', '神秘的白色斗士耳环', '0');
INSERT INTO `william_item_price` VALUES ('41173', '骑士耳环', '0');
INSERT INTO `william_item_price` VALUES ('41174', '神秘的骑士耳环', '0');
INSERT INTO `william_item_price` VALUES ('41175', '灰色骑士耳环', '0');
INSERT INTO `william_item_price` VALUES ('41176', '神秘的灰色骑士耳环', '0');
INSERT INTO `william_item_price` VALUES ('41177', '白色骑士耳环', '0');
INSERT INTO `william_item_price` VALUES ('41178', '神秘的白色骑士耳环', '0');
INSERT INTO `william_item_price` VALUES ('41179', '法师耳环', '0');
INSERT INTO `william_item_price` VALUES ('41180', '神秘的法师耳环', '0');
INSERT INTO `william_item_price` VALUES ('41181', '灰色法师耳环', '0');
INSERT INTO `william_item_price` VALUES ('41182', '神秘的灰色法师耳环', '0');
INSERT INTO `william_item_price` VALUES ('41183', '白色法师耳环', '0');
INSERT INTO `william_item_price` VALUES ('41184', '神秘的白色法师耳环', '0');
INSERT INTO `william_item_price` VALUES ('41185', '精致的风灵戒指(男爵)', '0');
INSERT INTO `william_item_price` VALUES ('41186', '精致的风灵戒指(公爵)', '0');
INSERT INTO `william_item_price` VALUES ('41187', '精致的风灵戒指(伯爵)', '0');
INSERT INTO `william_item_price` VALUES ('41188', '精致的风灵戒指(英雄)', '0');
INSERT INTO `william_item_price` VALUES ('41189', '精致的地灵戒指(男爵)', '0');
INSERT INTO `william_item_price` VALUES ('41190', '精致的地灵戒指(公爵)', '0');
INSERT INTO `william_item_price` VALUES ('41191', '精致的地灵戒指(伯爵)', '0');
INSERT INTO `william_item_price` VALUES ('41192', '精致的地灵戒指(英雄)', '0');
INSERT INTO `william_item_price` VALUES ('41193', '精致的火灵戒指(男爵)', '0');
INSERT INTO `william_item_price` VALUES ('41194', '精致的火灵戒指(公爵)', '0');
INSERT INTO `william_item_price` VALUES ('41195', '精致的火灵戒指(伯爵)', '0');
INSERT INTO `william_item_price` VALUES ('41196', '精致的火灵戒指(英雄)', '0');
INSERT INTO `william_item_price` VALUES ('41197', '精致的水灵戒指(男爵)', '0');
INSERT INTO `william_item_price` VALUES ('41198', '精致的水灵戒指(公爵)', '0');
INSERT INTO `william_item_price` VALUES ('41199', '精致的水灵戒指(伯爵)', '0');
INSERT INTO `william_item_price` VALUES ('41200', '精致的水灵戒指(英雄)', '0');
INSERT INTO `william_item_price` VALUES ('41201', '骑士之魂', '0');
INSERT INTO `william_item_price` VALUES ('41202', '妖精之魂', '0');
INSERT INTO `william_item_price` VALUES ('41203', '王族之魂', '0');
INSERT INTO `william_item_price` VALUES ('41204', '黑妖之魂', '0');
INSERT INTO `william_item_price` VALUES ('41205', '法师之魂', '0');
INSERT INTO `william_item_price` VALUES ('41206', '少了刀刃的武器', '250');
INSERT INTO `william_item_price` VALUES ('41207', '船员遗体', '0');
INSERT INTO `william_item_price` VALUES ('41208', '微弱的灵魂', '0');
INSERT INTO `william_item_price` VALUES ('41209', '$10002', '0');
INSERT INTO `william_item_price` VALUES ('41210', '$10003', '0');
INSERT INTO `william_item_price` VALUES ('41211', '香菜', '0');
INSERT INTO `william_item_price` VALUES ('41212', '$10005', '0');
INSERT INTO `william_item_price` VALUES ('41213', '$10006', '0');
INSERT INTO `william_item_price` VALUES ('41214', '$10012', '0');
INSERT INTO `william_item_price` VALUES ('41215', '$10010', '0');
INSERT INTO `william_item_price` VALUES ('41216', '$10011', '0');
INSERT INTO `william_item_price` VALUES ('41217', '$10028', '0');
INSERT INTO `william_item_price` VALUES ('41218', '$10029', '0');
INSERT INTO `william_item_price` VALUES ('41219', '$10030', '0');
INSERT INTO `william_item_price` VALUES ('41220', '$10031', '0');
INSERT INTO `william_item_price` VALUES ('41221', '黑暗妖精袋子', '0');
INSERT INTO `william_item_price` VALUES ('41222', '$10008', '0');
INSERT INTO `william_item_price` VALUES ('41223', '$10007', '0');
INSERT INTO `william_item_price` VALUES ('41224', '$10009', '0');
INSERT INTO `william_item_price` VALUES ('41225', '$10013', '0');
INSERT INTO `william_item_price` VALUES ('41226', '$10014', '0');
INSERT INTO `william_item_price` VALUES ('41227', '$10033', '0');
INSERT INTO `william_item_price` VALUES ('41228', '$10034', '0');
INSERT INTO `william_item_price` VALUES ('41229', '$10025', '0');
INSERT INTO `william_item_price` VALUES ('41230', '$10020', '0');
INSERT INTO `william_item_price` VALUES ('41231', '$10021', '0');
INSERT INTO `william_item_price` VALUES ('41232', '$10016', '0');
INSERT INTO `william_item_price` VALUES ('41233', '$10017', '0');
INSERT INTO `william_item_price` VALUES ('41234', '$10023', '0');
INSERT INTO `william_item_price` VALUES ('41235', '$10024', '0');
INSERT INTO `william_item_price` VALUES ('41236', '$10026', '0');
INSERT INTO `william_item_price` VALUES ('41237', '$10027', '0');
INSERT INTO `william_item_price` VALUES ('41238', '$10017', '0');
INSERT INTO `william_item_price` VALUES ('41239', '$10018', '0');
INSERT INTO `william_item_price` VALUES ('41240', '$10022', '0');
INSERT INTO `william_item_price` VALUES ('41241', '$10015', '0');
INSERT INTO `william_item_price` VALUES ('41242', '妖魔宝物袋', '0');
INSERT INTO `william_item_price` VALUES ('41243', '拉斯塔巴德补给袋', '0');
INSERT INTO `william_item_price` VALUES ('41244', '拉斯塔巴德补给箱', '0');
INSERT INTO `william_item_price` VALUES ('41245', '溶解剂', '25');
INSERT INTO `william_item_price` VALUES ('41246', '魔法结晶体', '0');
INSERT INTO `william_item_price` VALUES ('41247', '魔法娃娃的袋子', '0');
INSERT INTO `william_item_price` VALUES ('41248', '魔法娃娃：肥肥', '0');
INSERT INTO `william_item_price` VALUES ('41249', '魔法娃娃：小思克巴', '0');
INSERT INTO `william_item_price` VALUES ('41250', '魔法娃娃：野狼宝宝', '0');
INSERT INTO `william_item_price` VALUES ('41251', '骷髅圣杯', '0');
INSERT INTO `william_item_price` VALUES ('41252', '珍奇的乌龟', '0');
INSERT INTO `william_item_price` VALUES ('41253', '王宫料理师的调味料', '0');
INSERT INTO `william_item_price` VALUES ('41254', '胜利的徽章', '0');
INSERT INTO `william_item_price` VALUES ('41255', '料理书：1阶段', '0');
INSERT INTO `william_item_price` VALUES ('41256', '料理书：2阶段', '0');
INSERT INTO `william_item_price` VALUES ('41257', '料理书：3阶段', '0');
INSERT INTO `william_item_price` VALUES ('41258', '料理书：4阶段', '0');
INSERT INTO `william_item_price` VALUES ('41259', '料理书：5阶段', '0');
INSERT INTO `william_item_price` VALUES ('41260', '柴火', '150');
INSERT INTO `william_item_price` VALUES ('41261', '饭团', '0');
INSERT INTO `william_item_price` VALUES ('41262', '鸡肉串烧', '0');
INSERT INTO `william_item_price` VALUES ('41263', '太阳花籽', '0');
INSERT INTO `william_item_price` VALUES ('41264', '面粉', '0');
INSERT INTO `william_item_price` VALUES ('41265', '蜂蜜', '0');
INSERT INTO `william_item_price` VALUES ('41266', '蕃茄', '0');
INSERT INTO `william_item_price` VALUES ('41267', '起士', '0');
INSERT INTO `william_item_price` VALUES ('41268', '小比萨', '0');
INSERT INTO `william_item_price` VALUES ('41269', '烤玉米', '0');
INSERT INTO `william_item_price` VALUES ('41271', '爆米花', '0');
INSERT INTO `william_item_price` VALUES ('41272', '甜不辣', '0');
INSERT INTO `william_item_price` VALUES ('41273', '松饼', '0');
INSERT INTO `william_item_price` VALUES ('41274', '蚂蚁腿', '0');
INSERT INTO `william_item_price` VALUES ('41275', '熊肉', '0');
INSERT INTO `william_item_price` VALUES ('41276', '山猪肉', '0');
INSERT INTO `william_item_price` VALUES ('41277', '漂浮之眼肉排', '0');
INSERT INTO `william_item_price` VALUES ('41278', '烤熊肉', '0');
INSERT INTO `william_item_price` VALUES ('41279', '煎饼', '0');
INSERT INTO `william_item_price` VALUES ('41280', '烤蚂蚁腿起司', '0');
INSERT INTO `william_item_price` VALUES ('41281', '水果沙拉', '0');
INSERT INTO `william_item_price` VALUES ('41282', '水果糖醋肉', '0');
INSERT INTO `william_item_price` VALUES ('41283', '烤山猪肉串', '0');
INSERT INTO `william_item_price` VALUES ('41284', '蘑菇汤', '0');
INSERT INTO `william_item_price` VALUES ('41285', '特别的漂浮之眼肉排', '0');
INSERT INTO `william_item_price` VALUES ('41286', '特别的烤熊肉', '0');
INSERT INTO `william_item_price` VALUES ('41287', '特别的煎饼', '0');
INSERT INTO `william_item_price` VALUES ('41288', '特别的烤蚂蚁腿起司', '0');
INSERT INTO `william_item_price` VALUES ('41289', '特别的水果沙拉', '0');
INSERT INTO `william_item_price` VALUES ('41290', '特别的水果糖醋肉', '0');
INSERT INTO `william_item_price` VALUES ('41291', '特别的烤山猪肉串', '0');
INSERT INTO `william_item_price` VALUES ('41292', '特别的蘑菇汤', '0');
INSERT INTO `william_item_price` VALUES ('41293', '长钓竿', '0');
INSERT INTO `william_item_price` VALUES ('41294', '短钓竿', '0');
INSERT INTO `william_item_price` VALUES ('41295', '饵', '0');
INSERT INTO `william_item_price` VALUES ('41296', '鲷鱼', '0');
INSERT INTO `william_item_price` VALUES ('41297', '鲑鱼', '0');
INSERT INTO `william_item_price` VALUES ('41298', '鳕鱼', '0');
INSERT INTO `william_item_price` VALUES ('41299', '虎班带鱼', '0');
INSERT INTO `william_item_price` VALUES ('41300', '鲔鱼', '0');
INSERT INTO `william_item_price` VALUES ('41301', '发红光的鱼', '0');
INSERT INTO `william_item_price` VALUES ('41302', '发绿光的鱼', '0');
INSERT INTO `william_item_price` VALUES ('41303', '发蓝光的鱼', '0');
INSERT INTO `william_item_price` VALUES ('41304', '发白光的鱼', '0');
INSERT INTO `william_item_price` VALUES ('41305', '破碎的耳环', '0');
INSERT INTO `william_item_price` VALUES ('41306', '破碎的戒指', '0');
INSERT INTO `william_item_price` VALUES ('41307', '破碎的项链', '0');
INSERT INTO `william_item_price` VALUES ('41308', '勇者的南瓜袋子', '0');
INSERT INTO `william_item_price` VALUES ('41309', '宠物战金牌', '0');
INSERT INTO `william_item_price` VALUES ('41310', '胜利果实', '0');
INSERT INTO `william_item_price` VALUES ('41311', '惊喜箱', '0');
INSERT INTO `william_item_price` VALUES ('41312', '占星术师的瓮', '0');
INSERT INTO `william_item_price` VALUES ('41313', '占星术师的灵魂球', '0');
INSERT INTO `william_item_price` VALUES ('41314', '占星术师的符咒', '0');
INSERT INTO `william_item_price` VALUES ('41315', '圣水', '0');
INSERT INTO `william_item_price` VALUES ('41316', '神圣的米索莉粉', '0');
INSERT INTO `william_item_price` VALUES ('41317', '拉罗森的推荐书', '0');
INSERT INTO `william_item_price` VALUES ('41318', '可恩的便条纸', '0');
INSERT INTO `william_item_price` VALUES ('41319', '菊花花束', '0');
INSERT INTO `william_item_price` VALUES ('41320', '黛西花束', '0');
INSERT INTO `william_item_price` VALUES ('41321', '玫瑰花束', '0');
INSERT INTO `william_item_price` VALUES ('41322', '卡拉花束', '0');
INSERT INTO `william_item_price` VALUES ('41323', '太阳花花束', '0');
INSERT INTO `william_item_price` VALUES ('41324', '小苍兰花束', '0');
INSERT INTO `william_item_price` VALUES ('41325', '勇士之证', '0');
INSERT INTO `william_item_price` VALUES ('41326', '勇士之证', '0');
INSERT INTO `william_item_price` VALUES ('41327', '幽灵之气息', '0');
INSERT INTO `william_item_price` VALUES ('41328', '哈蒙的气息', '0');
INSERT INTO `william_item_price` VALUES ('41329', '标本制作委托书', '0');
INSERT INTO `william_item_price` VALUES ('41330', '狩猎蚂蚁之证', '0');
INSERT INTO `william_item_price` VALUES ('41331', '狩猎熊之证', '0');
INSERT INTO `william_item_price` VALUES ('41433', '\\f>┌\\f=每朝\\f>┐\\f2商店3', '200');
INSERT INTO `william_item_price` VALUES ('42001', '\\f>┌\\f=每朝\\f>┐\\f2商店1', '200');
INSERT INTO `william_item_price` VALUES ('42002', '指定传送(水晶洞穴1F)', '200');
INSERT INTO `william_item_price` VALUES ('42003', '指定传送(水晶洞穴2F)', '200');
INSERT INTO `william_item_price` VALUES ('42004', '指定传送(水晶洞穴3F)', '200');
INSERT INTO `william_item_price` VALUES ('42005', '指定传送(象牙塔1F)', '200');
INSERT INTO `william_item_price` VALUES ('42006', '指定传送(肯特地监1F)', '200');
INSERT INTO `william_item_price` VALUES ('42007', '指定传送(遗忘之岛)', '200');
INSERT INTO `william_item_price` VALUES ('42008', '指定传送(肯特地监2F)', '200');
INSERT INTO `william_item_price` VALUES ('42009', '指定传送(肯特地监3F)', '200');
INSERT INTO `william_item_price` VALUES ('42010', '指定传送(肯特地监4F)', '200');
INSERT INTO `william_item_price` VALUES ('42011', '指定传送(集会场1F)', '200');
INSERT INTO `william_item_price` VALUES ('42012', '指定传送(突击队训练场1F)', '200');
INSERT INTO `william_item_price` VALUES ('42013', '指定传送(魔兽军王办公室1F)', '200');
INSERT INTO `william_item_price` VALUES ('42014', '指定传送(野兽操练室1F)', '200');
INSERT INTO `william_item_price` VALUES ('42016', '指定传送(魔兽训练场1F)', '200');
INSERT INTO `william_item_price` VALUES ('42017', '指定传送(梦幻之岛)', '200');
INSERT INTO `william_item_price` VALUES ('42018', '指定传送(魔兽召唤室1F)', '200');
INSERT INTO `william_item_price` VALUES ('42019', '指定传送(黑暗的结界1F)', '200');
INSERT INTO `william_item_price` VALUES ('42020', '指定传送(黑魔法修炼场2F)', '200');
INSERT INTO `william_item_price` VALUES ('42021', '指定传送(古代巨人之墓)', '200');
INSERT INTO `william_item_price` VALUES ('42022', '指定传送(亚丁内城)', '200');
INSERT INTO `william_item_price` VALUES ('42023', '\\f>┌\\f=每朝\\f>┐\\f2商店2', '200');
INSERT INTO `william_item_price` VALUES ('42024', '指定传送(正义神殿)', '200');
INSERT INTO `william_item_price` VALUES ('42025', '指定传送(邪恶神殿)', '200');
INSERT INTO `william_item_price` VALUES ('42026', '指定传送(反王肯恩栖息地)', '200');
INSERT INTO `william_item_price` VALUES ('42027', '指定传送(银骑士村庄)', '60');
INSERT INTO `william_item_price` VALUES ('42028', '指定传送(古鲁丁地监7F)', '200');
INSERT INTO `william_item_price` VALUES ('42029', '傲慢之塔移动卷轴(100F)', '200');
INSERT INTO `william_item_price` VALUES ('42030', '指定传送(傲慢之塔90F)', '200');
INSERT INTO `william_item_price` VALUES ('42031', '指定传送(傲慢之塔80F)', '200');
INSERT INTO `william_item_price` VALUES ('42032', '指定传送(傲慢之塔70F)', '200');
INSERT INTO `william_item_price` VALUES ('42033', '指定传送(傲慢之塔60F)', '200');
INSERT INTO `william_item_price` VALUES ('42035', '指定传送(傲慢之塔50F)', '200');
INSERT INTO `william_item_price` VALUES ('42036', '指定传送(傲慢之塔40F)', '200');
INSERT INTO `william_item_price` VALUES ('42037', '指定传送(傲慢之塔30F)', '200');
INSERT INTO `william_item_price` VALUES ('42038', '指定传送(傲慢之塔20F)', '200');
INSERT INTO `william_item_price` VALUES ('42039', '指定传送(傲慢之塔10F)', '200');
INSERT INTO `william_item_price` VALUES ('42040', '指定传送(海贼岛地监1F)', '200');
INSERT INTO `william_item_price` VALUES ('42041', '指定传送(海贼岛地监2F)', '200');
INSERT INTO `william_item_price` VALUES ('42042', '指定传送(海贼岛地监3F)', '200');
INSERT INTO `william_item_price` VALUES ('42043', '指定传送(地底湖)', '200');
INSERT INTO `william_item_price` VALUES ('42044', '指定传送(说话岛练功区)', '200');
INSERT INTO `william_item_price` VALUES ('42045', '指定传送(血盟小屋)', '200');
INSERT INTO `william_item_price` VALUES ('42046', '指定传送(血盟小屋)', '200');
INSERT INTO `william_item_price` VALUES ('42047', '指定传送(古代人空间3F)', '200');
INSERT INTO `william_item_price` VALUES ('42048', '指定传送(奥姆地监)', '200');
INSERT INTO `william_item_price` VALUES ('42049', '指定传送(遗忘之岛)', '200');
INSERT INTO `william_item_price` VALUES ('42050', '地狱入场卷', '200');
INSERT INTO `william_item_price` VALUES ('42051', '指定传送(影之神殿外部)', '200');
INSERT INTO `william_item_price` VALUES ('42052', '指定传送(影之神殿1F)', '200');
INSERT INTO `william_item_price` VALUES ('43000', '返生药水', '0');
INSERT INTO `william_item_price` VALUES ('45000', '魔法书(初级治愈术)', '50');
INSERT INTO `william_item_price` VALUES ('45001', '魔法书(日光术)', '50');
INSERT INTO `william_item_price` VALUES ('45002', '魔法书(保护罩)', '50');
INSERT INTO `william_item_price` VALUES ('45003', '魔法书(光箭)', '50');
INSERT INTO `william_item_price` VALUES ('45004', '魔法书(指定传送)', '50');
INSERT INTO `william_item_price` VALUES ('45005', '魔法书(冰箭)', '50');
INSERT INTO `william_item_price` VALUES ('45006', '魔法书(风刃)', '50');
INSERT INTO `william_item_price` VALUES ('45007', '魔法书(神圣武器)', '50');
INSERT INTO `william_item_price` VALUES ('45008', '魔法书(解毒术)', '50');
INSERT INTO `william_item_price` VALUES ('45009', '魔法书(寒冷战栗)', '50');
INSERT INTO `william_item_price` VALUES ('45010', '魔法书(毒咒)', '50');
INSERT INTO `william_item_price` VALUES ('45011', '魔法书(拟似魔法武器)', '50');
INSERT INTO `william_item_price` VALUES ('45012', '魔法书(无所遁形术)', '50');
INSERT INTO `william_item_price` VALUES ('45013', '魔法书(负重强化)', '50');
INSERT INTO `william_item_price` VALUES ('45014', '魔法书(地狱之牙)', '50');
INSERT INTO `william_item_price` VALUES ('45015', '魔法书(火箭)', '50');
INSERT INTO `william_item_price` VALUES ('45016', '魔法书(极光雷电)', '50');
INSERT INTO `william_item_price` VALUES ('45017', '魔法书(寒冰气息)', '50');
INSERT INTO `william_item_price` VALUES ('45018', '魔法书(中级治愈术)', '50');
INSERT INTO `william_item_price` VALUES ('45019', '魔法书(闇盲咒术)', '50');
INSERT INTO `william_item_price` VALUES ('45020', '魔法书(铠甲护持)', '50');
INSERT INTO `william_item_price` VALUES ('45021', '魔法书(起死回生术)', '50');
INSERT INTO `william_item_price` VALUES ('45022', '魔法书(能量感测)', '50');
INSERT INTO `william_item_price` VALUES ('49005', '卡立普的袋子', '0');
INSERT INTO `william_item_price` VALUES ('49006', '卡立普的袋子', '0');
INSERT INTO `william_item_price` VALUES ('49007', '卡立普的袋子', '0');
INSERT INTO `william_item_price` VALUES ('49008', '卡立普的袋子', '0');
INSERT INTO `william_item_price` VALUES ('49009', '卡立普的高级袋子', '0');
INSERT INTO `william_item_price` VALUES ('49010', '卡立普的高级袋子', '0');
INSERT INTO `william_item_price` VALUES ('49011', '卡立普的高级袋子', '0');
INSERT INTO `william_item_price` VALUES ('49012', '卡立普的高级袋子', '0');
INSERT INTO `william_item_price` VALUES ('49013', '魔族的卷轴', '500');
INSERT INTO `william_item_price` VALUES ('49014', '灵魂之球', '0');
INSERT INTO `william_item_price` VALUES ('49015', '黑色米索莉溶液', '0');
INSERT INTO `william_item_price` VALUES ('49103', null, '10');
INSERT INTO `william_item_price` VALUES ('49104', null, '10');
INSERT INTO `william_item_price` VALUES ('49108', null, '4500');
INSERT INTO `william_item_price` VALUES ('49109', null, '4500');
INSERT INTO `william_item_price` VALUES ('49117', null, '750');
INSERT INTO `william_item_price` VALUES ('49118', null, '750');
INSERT INTO `william_item_price` VALUES ('49119', null, '750');
INSERT INTO `william_item_price` VALUES ('49120', null, '750');
INSERT INTO `william_item_price` VALUES ('49122', null, '20');
INSERT INTO `william_item_price` VALUES ('49124', null, '20');
INSERT INTO `william_item_price` VALUES ('49125', null, '20');
INSERT INTO `william_item_price` VALUES ('49127', null, '4500');
INSERT INTO `william_item_price` VALUES ('49129', null, '4500');
INSERT INTO `william_item_price` VALUES ('49156', null, '150');
INSERT INTO `william_item_price` VALUES ('49157', null, '250');
INSERT INTO `william_item_price` VALUES ('49158', null, '250');
INSERT INTO `william_item_price` VALUES ('60003', '高级祭司召唤球', '0');
INSERT INTO `william_item_price` VALUES ('60101', '饵 (10)', '0');
INSERT INTO `william_item_price` VALUES ('60102', '饵 (100)', '0');
INSERT INTO `william_item_price` VALUES ('60103', '溶解剂 (10)', '0');
INSERT INTO `william_item_price` VALUES ('60104', '溶解剂 (100)', '0');
INSERT INTO `william_item_price` VALUES ('60105', '项圈[Lv.10 牧羊犬]', '0');
INSERT INTO `william_item_price` VALUES ('60106', '项圈[Lv.10 猫]', '0');
INSERT INTO `william_item_price` VALUES ('60107', '项圈[Lv.10 熊]', '0');
INSERT INTO `william_item_price` VALUES ('60108', '项圈[Lv.10 杜宾狗]', '0');
INSERT INTO `william_item_price` VALUES ('60109', '项圈[Lv.10 狼]', '0');
INSERT INTO `william_item_price` VALUES ('60110', '项圈[Lv.10 浣熊]', '0');
INSERT INTO `william_item_price` VALUES ('60111', '项圈[Lv.10 小猎犬]', '0');
INSERT INTO `william_item_price` VALUES ('60112', '项圈[Lv.10 圣伯纳犬]', '0');
INSERT INTO `william_item_price` VALUES ('60113', '项圈[Lv.10 狐狸]', '0');
INSERT INTO `william_item_price` VALUES ('60114', '项圈[Lv.10 暴走兔]', '0');
INSERT INTO `william_item_price` VALUES ('60115', '项圈[Lv.10 哈士奇]', '0');
INSERT INTO `william_item_price` VALUES ('60116', '项圈[Lv.10 柯利]', '0');
INSERT INTO `william_item_price` VALUES ('60201', '瞬间移动戒指', '0');
INSERT INTO `william_item_price` VALUES ('60202', '宠物指环', '0');
INSERT INTO `william_item_price` VALUES ('60203', '魔法符袋', '0');
INSERT INTO `william_item_price` VALUES ('60204', '魔法娃娃：长者', '0');
INSERT INTO `william_item_price` VALUES ('60205', '魔法娃娃：奎斯坦修', '0');
INSERT INTO `william_item_price` VALUES ('60206', '魔法娃娃：石头高仑', '0');
INSERT INTO `william_item_price` VALUES ('60207', '装备鉴定卷轴', '0');
INSERT INTO `william_item_price` VALUES ('60208', '装备强化水晶', '0');
INSERT INTO `william_item_price` VALUES ('60209', '回忆蜡烛', '0');
INSERT INTO `william_item_price` VALUES ('60210', '偷窥卡', '0');
INSERT INTO `william_item_price` VALUES ('60211', '反偷窥卡', '0');
INSERT INTO `william_item_price` VALUES ('60212', '瞬间移动戒指', '0');
INSERT INTO `william_item_price` VALUES ('60213', '重生药水', '0');
INSERT INTO `william_item_price` VALUES ('60301', '宠物活力药水', '0');
INSERT INTO `william_item_price` VALUES ('60302', '宠物魔力药水', '0');
INSERT INTO `william_item_price` VALUES ('60401', '魔法徽章', '0');
INSERT INTO `william_item_price` VALUES ('62001', '精灵的祝福御守', '0');
INSERT INTO `william_item_price` VALUES ('62002', '精灵的祝福签诗', '0');
INSERT INTO `william_item_price` VALUES ('62003', '历史古书(上册)', '0');
INSERT INTO `william_item_price` VALUES ('62004', '历史古书(下册)', '0');
INSERT INTO `william_item_price` VALUES ('62005', '历史古书(全)', '0');
INSERT INTO `william_item_price` VALUES ('62006', '古代的羽毛笔', '0');
INSERT INTO `william_item_price` VALUES ('62007', '受祝福的泉水', '0');
INSERT INTO `william_item_price` VALUES ('62008', '古代格利芬的羽毛', '0');
INSERT INTO `william_item_price` VALUES ('70000', '解卡点滚动条(银骑士村)', '250');
INSERT INTO `william_item_price` VALUES ('75000', '钱包', '0');
INSERT INTO `william_item_price` VALUES ('100004', '匕首', '8');
INSERT INTO `william_item_price` VALUES ('100008', '米索莉短剑', '1');
INSERT INTO `william_item_price` VALUES ('100009', '奥里哈鲁根短剑', '1');
INSERT INTO `william_item_price` VALUES ('100025', '银剑', '750');
INSERT INTO `william_item_price` VALUES ('100027', '弯刀', '650');
INSERT INTO `william_item_price` VALUES ('100029', '银长剑', '950');
INSERT INTO `william_item_price` VALUES ('100037', '大马士革刀', '5500');
INSERT INTO `william_item_price` VALUES ('100041', '武士刀', '5500');
INSERT INTO `william_item_price` VALUES ('100042', '细剑', '1');
INSERT INTO `william_item_price` VALUES ('100049', '武官之刃', '1');
INSERT INTO `william_item_price` VALUES ('100052', '双手剑', '7800');
INSERT INTO `william_item_price` VALUES ('100057', '瑟鲁基之剑', '8100');
INSERT INTO `william_item_price` VALUES ('100062', '武官双手剑', '1');
INSERT INTO `william_item_price` VALUES ('100064', '巨剑', '1');
INSERT INTO `william_item_price` VALUES ('100074', '银光双刀', '1');
INSERT INTO `william_item_price` VALUES ('100084', '暗黑双刀', '1');
INSERT INTO `william_item_price` VALUES ('100095', '矛', '66');
INSERT INTO `william_item_price` VALUES ('100098', '阔矛', '385');
INSERT INTO `william_item_price` VALUES ('100099', '精灵之矛', '1');
INSERT INTO `william_item_price` VALUES ('100102', '露西锤', '650');
INSERT INTO `william_item_price` VALUES ('100103', '戟', '650');
INSERT INTO `william_item_price` VALUES ('100107', '深红长矛', '1');
INSERT INTO `william_item_price` VALUES ('100132', '神官魔杖', '1');
INSERT INTO `william_item_price` VALUES ('100143', '战斧', '770');
INSERT INTO `william_item_price` VALUES ('100151', '恶魔斧头', '1');
INSERT INTO `william_item_price` VALUES ('100164', '暗黑钢爪', '1');
INSERT INTO `william_item_price` VALUES ('100169', '猎人之弓', '1');
INSERT INTO `william_item_price` VALUES ('100172', '弓', '55');
INSERT INTO `william_item_price` VALUES ('100189', '暗黑十字弓', '1');
INSERT INTO `william_item_price` VALUES ('120011', '抗魔法头盔', '250');
INSERT INTO `william_item_price` VALUES ('120016', '曼波帽子', '0');
INSERT INTO `william_item_price` VALUES ('120043', '钢盔', '200');
INSERT INTO `william_item_price` VALUES ('120056', '抗魔法斗篷', '100');
INSERT INTO `william_item_price` VALUES ('120074', '银光斗篷', '0');
INSERT INTO `william_item_price` VALUES ('120077', '隐身斗篷', '0');
INSERT INTO `william_item_price` VALUES ('120085', 'T恤', '0');
INSERT INTO `william_item_price` VALUES ('120101', '皮甲', '5500');
INSERT INTO `william_item_price` VALUES ('120112', '曼波外套', '0');
INSERT INTO `william_item_price` VALUES ('120128', '水晶盔甲', '0');
INSERT INTO `william_item_price` VALUES ('120137', '精灵链甲', '0');
INSERT INTO `william_item_price` VALUES ('120149', '青铜盔甲', '5500');
INSERT INTO `william_item_price` VALUES ('120154', '金属盔甲', '5500');
INSERT INTO `william_item_price` VALUES ('120182', '手套', '0');
INSERT INTO `william_item_price` VALUES ('120242', '大盾牌', '1200');
INSERT INTO `william_item_price` VALUES ('120244', '小型魅力项链', '0');
INSERT INTO `william_item_price` VALUES ('120245', '小型敏捷项链', '0');
INSERT INTO `william_item_price` VALUES ('120246', '小型力量项链', '0');
INSERT INTO `william_item_price` VALUES ('120247', '小型智力项链', '0');
INSERT INTO `william_item_price` VALUES ('120248', '小型精神项链', '0');
INSERT INTO `william_item_price` VALUES ('120249', '小型体质项链', '0');
INSERT INTO `william_item_price` VALUES ('120254', '魅力项链', '0');
INSERT INTO `william_item_price` VALUES ('120256', '敏捷项链', '0');
INSERT INTO `william_item_price` VALUES ('120264', '力量项链', '0');
INSERT INTO `william_item_price` VALUES ('120266', '智力项链', '0');
INSERT INTO `william_item_price` VALUES ('120267', '精神项链', '0');
INSERT INTO `william_item_price` VALUES ('120268', '体质项链', '0');
INSERT INTO `william_item_price` VALUES ('120280', '灭魔戒指', '0');
INSERT INTO `william_item_price` VALUES ('120285', '水灵戒指', '0');
INSERT INTO `william_item_price` VALUES ('120289', '深渊戒指', '0');
INSERT INTO `william_item_price` VALUES ('120300', '地灵戒指', '0');
INSERT INTO `william_item_price` VALUES ('120302', '风灵戒指', '0');
INSERT INTO `william_item_price` VALUES ('120304', '火灵戒指', '0');
INSERT INTO `william_item_price` VALUES ('120306', '小型身体腰带', '0');
INSERT INTO `william_item_price` VALUES ('120307', '小型灵魂腰带', '0');
INSERT INTO `william_item_price` VALUES ('120308', '小型精神腰带', '0');
INSERT INTO `william_item_price` VALUES ('120309', '光明身体腰带', '0');
INSERT INTO `william_item_price` VALUES ('120310', '光明灵魂腰带', '0');
INSERT INTO `william_item_price` VALUES ('120311', '光明精神腰带', '0');
INSERT INTO `william_item_price` VALUES ('120312', '身体腰带', '0');
INSERT INTO `william_item_price` VALUES ('120316', '灵魂腰带', '0');
INSERT INTO `william_item_price` VALUES ('120317', '欧吉皮带', '0');
INSERT INTO `william_item_price` VALUES ('120319', '精神腰带', '0');
INSERT INTO `william_item_price` VALUES ('120320', '泰坦皮带', '0');
INSERT INTO `william_item_price` VALUES ('120321', '多罗皮带', '0');
INSERT INTO `william_item_price` VALUES ('140006', '创造怪物魔杖', '650');
INSERT INTO `william_item_price` VALUES ('140008', '变身魔杖', '650');
INSERT INTO `william_item_price` VALUES ('140010', '治愈药水', '64');
INSERT INTO `william_item_price` VALUES ('140011', '强力治愈药水', '212');
INSERT INTO `william_item_price` VALUES ('140012', '终极治愈药水', '990');
INSERT INTO `william_item_price` VALUES ('140013', '自我加速药水', '412');
INSERT INTO `william_item_price` VALUES ('140014', '勇敢药水', '920');
INSERT INTO `william_item_price` VALUES ('140015', '加速魔力恢复药水', '777');
INSERT INTO `william_item_price` VALUES ('140016', '慎重药水', '990');
INSERT INTO `william_item_price` VALUES ('140018', '强化自我加速药水', '2722');
INSERT INTO `william_item_price` VALUES ('140061', '柠檬', '9');
INSERT INTO `william_item_price` VALUES ('140062', '香蕉', '9');
INSERT INTO `william_item_price` VALUES ('140065', '糖果', '6');
INSERT INTO `william_item_price` VALUES ('140068', '精灵饼干', '1250');
INSERT INTO `william_item_price` VALUES ('140069', '橘子', '9');
INSERT INTO `william_item_price` VALUES ('140072', '烤薄饼', '33');
INSERT INTO `william_item_price` VALUES ('140074', '对盔甲施法的卷轴', '800');
INSERT INTO `william_item_price` VALUES ('140087', '对武器施法的卷轴', '400');
INSERT INTO `william_item_price` VALUES ('140088', '变形卷轴', '2422');
INSERT INTO `william_item_price` VALUES ('140089', '复活卷轴', '2134');
INSERT INTO `william_item_price` VALUES ('140100', '瞬间移动卷轴', '1020');
INSERT INTO `william_item_price` VALUES ('140119', '解除咀咒的卷轴', '100');
INSERT INTO `william_item_price` VALUES ('140506', '安特的水果', '0');
INSERT INTO `william_item_price` VALUES ('200001', '欧西斯匕首', '32');
INSERT INTO `william_item_price` VALUES ('200002', '骰子匕首', '240');
INSERT INTO `william_item_price` VALUES ('200027', '弯刀', '1320');
INSERT INTO `william_item_price` VALUES ('200032', '侵略者之剑', '2750');
INSERT INTO `william_item_price` VALUES ('200041', '武士刀', '220');
INSERT INTO `william_item_price` VALUES ('200052', '双手剑', '19800');
INSERT INTO `william_item_price` VALUES ('200171', '欧西斯弓', '100');
INSERT INTO `william_item_price` VALUES ('220034', '欧西斯头盔', '150');
INSERT INTO `william_item_price` VALUES ('220043', '钢盔', '200');
INSERT INTO `william_item_price` VALUES ('220056', '抗魔法斗篷', '100');
INSERT INTO `william_item_price` VALUES ('220101', '皮甲', '11500');
INSERT INTO `william_item_price` VALUES ('220115', '藤甲', '200');
INSERT INTO `william_item_price` VALUES ('220122', '鳞甲', '20');
INSERT INTO `william_item_price` VALUES ('220125', '链甲', '60');
INSERT INTO `william_item_price` VALUES ('220135', '欧西斯环甲', '200');
INSERT INTO `william_item_price` VALUES ('220136', '欧西斯链甲', '800');
INSERT INTO `william_item_price` VALUES ('220147', '银钉皮甲', '300');
INSERT INTO `william_item_price` VALUES ('220154', '金属盔甲', '370');
INSERT INTO `william_item_price` VALUES ('220213', '短统靴', '300');
INSERT INTO `william_item_price` VALUES ('220237', '阿克海盾牌', '90');
INSERT INTO `william_item_price` VALUES ('240010', '治愈药水', '64');
INSERT INTO `william_item_price` VALUES ('240074', '对盔甲施法的卷轴', '34510');
INSERT INTO `william_item_price` VALUES ('240087', '对武器施法的卷轴', '75220');

-- ----------------------------
-- Table structure for `william_item_summon`
-- ----------------------------
DROP TABLE IF EXISTS `william_item_summon`;
CREATE TABLE `william_item_summon` (
  `item_id` int(10) unsigned NOT NULL,
  `checkLevel` int(3) unsigned NOT NULL DEFAULT '0',
  `checkClass` int(2) unsigned NOT NULL DEFAULT '0',
  `checkItem` int(10) unsigned NOT NULL DEFAULT '0',
  `hpConsume` int(10) unsigned NOT NULL DEFAULT '0',
  `mpConsume` int(10) unsigned NOT NULL DEFAULT '0',
  `material` int(10) unsigned NOT NULL DEFAULT '0',
  `material_count` int(10) unsigned NOT NULL DEFAULT '0',
  `summon_id` int(10) unsigned NOT NULL,
  `summonCost` int(10) unsigned NOT NULL DEFAULT '0',
  `onlyOne` int(1) unsigned NOT NULL DEFAULT '0',
  `removeItem` int(1) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of william_item_summon
-- ----------------------------

-- ----------------------------
-- Table structure for `william_item_update`
-- ----------------------------
DROP TABLE IF EXISTS `william_item_update`;
CREATE TABLE `william_item_update` (
  `item_id` int(11) NOT NULL DEFAULT '0',
  `count` int(11) DEFAULT NULL,
  `add_dmg` int(11) DEFAULT NULL,
  `add_dmgmodifier` int(11) DEFAULT NULL,
  `add_hitmodifier` int(11) DEFAULT NULL,
  `add_str` int(11) DEFAULT NULL,
  `add_dex` int(11) DEFAULT NULL,
  `add_int` int(11) DEFAULT NULL,
  `add_con` int(11) DEFAULT '0',
  `add_cha` int(11) DEFAULT '0',
  `add_wis` int(11) DEFAULT '0',
  PRIMARY KEY (`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of william_item_update
-- ----------------------------
INSERT INTO `william_item_update` VALUES ('268911338', '-2', '0', '2', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `william_item_update` VALUES ('268911339', '-2', '0', '2', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `william_item_update` VALUES ('268911342', '0', '0', '2', '1', '0', '1', '0', '0', '0', '0');
INSERT INTO `william_item_update` VALUES ('268911343', '-5', '0', '4', '1', '0', '0', '0', '0', '0', '0');
INSERT INTO `william_item_update` VALUES ('268983048', '3', '0', '0', '0', '0', '3', '0', '0', '0', '0');

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
INSERT INTO `william_quests` VALUES ('2', 'request for request_exchange_2', null, null, '49101', '100', '70005', '1', '65', null, null, '0', '3', '0', '1', '70964');
INSERT INTO `william_quests` VALUES ('1', 'request for request_exchange_1', null, null, '40346,40354,40362,40370', '1,1,1,1', '70005', '1', '65', null, null, '0', '3', '0', '1', '70964');

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
-- Table structure for `william_reward`
-- ----------------------------
DROP TABLE IF EXISTS `william_reward`;
CREATE TABLE `william_reward` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `注解` varchar(45) DEFAULT NULL,
  `Metempsychosis` int(3) NOT NULL DEFAULT '0',
  `level` int(3) unsigned NOT NULL DEFAULT '0',
  `give_royal` int(1) unsigned NOT NULL DEFAULT '0',
  `give_knight` int(1) unsigned NOT NULL DEFAULT '0',
  `give_mage` int(1) unsigned NOT NULL DEFAULT '0',
  `give_elf` int(1) unsigned NOT NULL DEFAULT '0',
  `give_darkelf` int(1) unsigned NOT NULL DEFAULT '0',
  `give_DragonKnight` int(1) unsigned NOT NULL DEFAULT '0',
  `give_Illusionist` int(1) unsigned NOT NULL DEFAULT '0',
  `getItem` varchar(45) NOT NULL DEFAULT '',
  `count` varchar(45) NOT NULL DEFAULT '',
  `enchantlvl` varchar(45) NOT NULL DEFAULT '',
  `quest_id` int(10) unsigned NOT NULL DEFAULT '0',
  `quest_step` int(10) unsigned NOT NULL DEFAULT '0',
  `message` varchar(45) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of william_reward
-- ----------------------------
INSERT INTO `william_reward` VALUES ('1', '王族奖励：+7黄金权杖', '0', '52', '1', '0', '0', '0', '0', '0', '0', '51', '1', '7', '1', '255', '恭喜你升级到５２级，给予你奖励物品！');
INSERT INTO `william_reward` VALUES ('2', '法师奖励：+7钢铁玛那魔杖', '0', '52', '0', '0', '1', '0', '0', '0', '0', '127', '1', '7', '1', '255', '恭喜你升级到５２级，给予你奖励物品！');
INSERT INTO `william_reward` VALUES ('3', '妖精奖励：+7精灵弓', '0', '52', '0', '0', '0', '1', '0', '0', '0', '170', '1', '7', '1', '255', '恭喜你升级到５２级，给予你奖励物品！');
INSERT INTO `william_reward` VALUES ('4', '骑士奖励：+7武士刀', '0', '52', '0', '1', '0', '0', '0', '0', '0', '41', '1', '7', '1', '255', '恭喜你升级到５２级，给予你奖励物品！');
INSERT INTO `william_reward` VALUES ('5', '黑妖奖励：+7暗影双刀', '0', '52', '0', '0', '0', '0', '1', '0', '0', '72', '1', '7', '1', '255', '恭喜你升级到５２级，给予你奖励物品！');
INSERT INTO `william_reward` VALUES ('6', '龙骑士奖励：+7消灭者锁链剑', '0', '52', '0', '0', '0', '0', '0', '1', '0', '272', '1', '7', '1', '255', '恭喜你升级到５２级，给予你奖励物品！');
INSERT INTO `william_reward` VALUES ('7', '幻术师奖励：+7幻术士魔杖', '0', '52', '0', '0', '0', '0', '0', '0', '1', '269', '1', '7', '1', '255', '恭喜你升级到５２级，给予你奖励物品！');

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
INSERT INTO `william_spawn_npc` VALUES ('90001', 'L1Merchant', '1049', '任务兑换师', '1', '1', '1', '1', '1', '0', '0', '0', '0', '0', '0', '0', 'exchange', '', '', '', '', '卷轴兑换师:,这里可以为你兑换各种任务道具，请仔细检查身上的物品是否满足兑换的条件。,4龙之心兑换回忆蜡烛,100个时空裂痕碎片换自杀药水,', '', '32714', '32836', '6', '350', '1');

-- ----------------------------
-- Table structure for `william_system_message`
-- ----------------------------
DROP TABLE IF EXISTS `william_system_message`;
CREATE TABLE `william_system_message` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `message` varchar(1000) NOT NULL,
  `note` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=117 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of william_system_message
-- ----------------------------
INSERT INTO `william_system_message` VALUES ('100', '\\f:[声望LV1]', '声望LV1');
INSERT INTO `william_system_message` VALUES ('101', '\\fU[声望LV2]', '声望LV2');
INSERT INTO `william_system_message` VALUES ('102', '\\fA[声望LV3]', '声望LV3');
INSERT INTO `william_system_message` VALUES ('103', '\\fI[声望LV4]', '声望LV4');
INSERT INTO `william_system_message` VALUES ('104', '\\fN[声望LV5]', '声望LV5');
INSERT INTO `william_system_message` VALUES ('105', '\\fB[声望LV6]', '声望LV6');
INSERT INTO `william_system_message` VALUES ('106', '\\fY[声望LV7]', '声望LV7');
INSERT INTO `william_system_message` VALUES ('107', '\\f9[声望LV8]', '声望LV8');
INSERT INTO `william_system_message` VALUES ('108', '\\fW[声望LV9]', '声望LV9');
INSERT INTO `william_system_message` VALUES ('109', '\\f7[声望LV10]', '声望LV10');
INSERT INTO `william_system_message` VALUES ('110', '\\f;[声望LV11]', '声望LV11');
INSERT INTO `william_system_message` VALUES ('111', '\\fH[声望LV12]', '声望LV12');
INSERT INTO `william_system_message` VALUES ('112', '您的声望值增加1点', '声望道具');
INSERT INTO `william_system_message` VALUES ('113', '您的声望值增加5点', '声望道具');
INSERT INTO `william_system_message` VALUES ('114', '您的声望值增加10点', '声望道具');
INSERT INTO `william_system_message` VALUES ('115', '您的声望值已到了极限', '声望道具');
INSERT INTO `william_system_message` VALUES ('116', '您不适合使用这种声望道具', '声望道具');

-- ----------------------------
-- Table structure for `william_teleport_scroll`
-- ----------------------------
DROP TABLE IF EXISTS `william_teleport_scroll`;
CREATE TABLE `william_teleport_scroll` (
  `item_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `tpLocX` int(10) unsigned NOT NULL DEFAULT '0',
  `tpLocY` int(10) unsigned NOT NULL DEFAULT '0',
  `tpMapId` int(10) unsigned NOT NULL DEFAULT '0',
  `check_minLocX` int(10) unsigned NOT NULL DEFAULT '0',
  `check_minLocY` int(10) unsigned NOT NULL DEFAULT '0',
  `check_maxLocX` int(10) unsigned NOT NULL DEFAULT '0',
  `check_maxLocY` int(10) unsigned NOT NULL DEFAULT '0',
  `check_MapId` int(10) unsigned NOT NULL DEFAULT '0',
  `removeItem` int(1) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`item_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of william_teleport_scroll
-- ----------------------------
