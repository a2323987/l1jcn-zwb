/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50524
Source Host           : localhost:3306
Source Database       : l1jcn

Target Server Type    : MYSQL
Target Server Version : 50524
File Encoding         : 65001

Date: 2012-10-03 20:27:28
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
  `Class` int(1) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`Id`)
) ENGINE=MyISAM AUTO_INCREMENT=201 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of william_auto_add_skill
-- ----------------------------
INSERT INTO `william_auto_add_skill` VALUES ('1', '法师 - 第一级魔法', '4', '1,2,3,4,5,6,7,8', '2');
INSERT INTO `william_auto_add_skill` VALUES ('2', '法师 - 第二级魔法', '8', '9,10,11,12,13,14,15,16', '2');
INSERT INTO `william_auto_add_skill` VALUES ('3', '法师 - 第三级魔法', '12', '17,18,19,20,21,22,23', '2');
INSERT INTO `william_auto_add_skill` VALUES ('4', '法师 - 第四级魔法', '16', '25,26,27,28,29,30,31,32', '2');
INSERT INTO `william_auto_add_skill` VALUES ('5', '法师 - 第五级魔法', '20', '33,34,35,36,37,38,39,40', '2');
INSERT INTO `william_auto_add_skill` VALUES ('6', '法师 - 第六级魔法', '24', '41,42,43,44,45,46,47,48', '2');
INSERT INTO `william_auto_add_skill` VALUES ('7', '法师 - 第七级魔法', '28', '49,50,51,52,53,54,55,56', '2');
INSERT INTO `william_auto_add_skill` VALUES ('8', '法师 - 第八级魔法', '32', '57,59,61,62,,63,65,66', '2');
INSERT INTO `william_auto_add_skill` VALUES ('9', '王族 - 第一级魔法', '10', '1,2,3,4,5,6,7,8', '0');
INSERT INTO `william_auto_add_skill` VALUES ('10', '王族 - 第二级魔法', '20', '9,10,11,12,13,14,15,16', '0');
INSERT INTO `william_auto_add_skill` VALUES ('11', '王族 - 精准目标', '15', '113', '0');
INSERT INTO `william_auto_add_skill` VALUES ('12', '王族 - 激励士气', '40', '114', '0');
INSERT INTO `william_auto_add_skill` VALUES ('13', '王族 - 钢铁士气', '55', '115', '0');
INSERT INTO `william_auto_add_skill` VALUES ('14', '王族 - 呼唤盟友', '30', '116', '0');
INSERT INTO `william_auto_add_skill` VALUES ('15', '王族 - 冲击士气', '50', '117', '0');
INSERT INTO `william_auto_add_skill` VALUES ('16', '王族 - 援护盟友', '45', '118', '0');
INSERT INTO `william_auto_add_skill` VALUES ('17', '骑士 - 第一级魔法', '50', '1,2,3,4,5,6,7,8', '1');
INSERT INTO `william_auto_add_skill` VALUES ('18', '骑士 - 特殊级技能', '50', '87,88,90', '1');
INSERT INTO `william_auto_add_skill` VALUES ('19', '骑士 - 特殊级技能 - 尖刺盔甲', '60', '89', '1');
INSERT INTO `william_auto_add_skill` VALUES ('20', '妖精 - 第一级魔法', '8', '1,2,3,4,5,6,7,8', '3');
INSERT INTO `william_auto_add_skill` VALUES ('21', '妖精 - 第二级魔法', '16', '9,10,11,12,13,14,15,16', '3');
INSERT INTO `william_auto_add_skill` VALUES ('22', '妖精 - 第三级魔法', '24', '17,18,19,20,21,22,23', '3');
INSERT INTO `william_auto_add_skill` VALUES ('23', '妖精 - 第四级魔法', '32', '25,26,27,28,29,30,31,32', '3');
INSERT INTO `william_auto_add_skill` VALUES ('24', '妖精 - 第五级魔法', '40', '33,34,35,36,37,38,39,40', '3');
INSERT INTO `william_auto_add_skill` VALUES ('25', '妖精 - 第六级魔法', '48', '41,42,43,44,45,46,47,48', '3');
INSERT INTO `william_auto_add_skill` VALUES ('26', '妖精 - 第一级技能', '10', '129,130,131', '3');
INSERT INTO `william_auto_add_skill` VALUES ('27', '妖精 - 第二级技能', '20', '137,138', '3');
INSERT INTO `william_auto_add_skill` VALUES ('28', '妖精 - 第三级技能', '30', '145', '3');
INSERT INTO `william_auto_add_skill` VALUES ('29', '妖精 - 第四级技能', '40', '133,154', '3');
INSERT INTO `william_auto_add_skill` VALUES ('30', '妖精 - 第五级技能', '50', '134,162', '3');
INSERT INTO `william_auto_add_skill` VALUES ('31', '黑暗妖精 - 第一级魔法', '12', '1,2,3,4,5,6,7,8', '4');
INSERT INTO `william_auto_add_skill` VALUES ('32', '黑暗妖精 - 第二级魔法', '24', '9,10,11,12,13,14,15,16', '4');
INSERT INTO `william_auto_add_skill` VALUES ('33', '黑暗妖精 - 第一级技能', '15', '97,98,99,100', '4');
INSERT INTO `william_auto_add_skill` VALUES ('34', '黑暗妖精 - 第二级技能', '30', '101,102', '4');
INSERT INTO `william_auto_add_skill` VALUES ('35', '龙骑士 - 第一级技能', '15', '181,182,183,184,185', '5');
INSERT INTO `william_auto_add_skill` VALUES ('36', '龙骑士 - 第二级技能', '30', '186,190', '5');
INSERT INTO `william_auto_add_skill` VALUES ('37', '龙骑士 - 第三级技能', '45', '195', '5');
INSERT INTO `william_auto_add_skill` VALUES ('38', '幻术士 - 第一级技能', '10', '202,204', '6');
INSERT INTO `william_auto_add_skill` VALUES ('39', '幻术士 - 第二级技能', '20', '206,207,208,209', '6');
INSERT INTO `william_auto_add_skill` VALUES ('40', '幻术士 - 第三级技能', '30', '211,214', '6');
INSERT INTO `william_auto_add_skill` VALUES ('41', '幻术士 - 第四级技能', '40', '216,217,219', '6');
