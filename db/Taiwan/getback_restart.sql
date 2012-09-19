/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50051
Source Host           : localhost:3306
Source Database       : l1jdb_taiwan

Target Server Type    : MYSQL
Target Server Version : 50051
File Encoding         : 65001

Date: 2012-06-30 19:31:42
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `getback_restart`
-- ----------------------------
DROP TABLE IF EXISTS `getback_restart`;
CREATE TABLE `getback_restart` (
  `area` int(10) NOT NULL default '0',
  `note` varchar(50) default NULL,
  `locx` int(10) NOT NULL default '0',
  `locy` int(10) NOT NULL default '0',
  `mapid` int(10) NOT NULL default '0',
  PRIMARY KEY  (`area`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of getback_restart
-- ----------------------------
INSERT INTO `getback_restart` VALUES ('5', 'Talking Island Ship to Aden Mainland', '32631', '32983', '0');
INSERT INTO `getback_restart` VALUES ('6', 'Aden Mainland Ship to Talking Island', '32543', '32728', '4');
INSERT INTO `getback_restart` VALUES ('70', '忘れられた岛', '32828', '32848', '70');
INSERT INTO `getback_restart` VALUES ('75', '象牙の塔:1阶', '34047', '32283', '4');
INSERT INTO `getback_restart` VALUES ('76', '象牙の塔:2阶', '34047', '32283', '4');
INSERT INTO `getback_restart` VALUES ('77', '象牙の塔:3阶', '34047', '32283', '4');
INSERT INTO `getback_restart` VALUES ('78', '象牙の塔:4阶', '34047', '32283', '4');
INSERT INTO `getback_restart` VALUES ('79', '象牙の塔:5阶', '34047', '32283', '4');
INSERT INTO `getback_restart` VALUES ('80', '象牙の塔:6阶', '34047', '32283', '4');
INSERT INTO `getback_restart` VALUES ('81', '象牙の塔:7阶', '34047', '32283', '4');
INSERT INTO `getback_restart` VALUES ('82', '象牙の塔:8阶', '34047', '32283', '4');
INSERT INTO `getback_restart` VALUES ('83', 'Aden Mainland Ship to Forgotten Island', '33426', '33499', '4');
INSERT INTO `getback_restart` VALUES ('84', 'Forgotten Island Ship to Aden Mainland', '32936', '33057', '70');
INSERT INTO `getback_restart` VALUES ('88', 'Giran Colosseum', '33442', '32797', '0');
INSERT INTO `getback_restart` VALUES ('91', 'Talking island Colosseum', '32580', '32931', '4');
INSERT INTO `getback_restart` VALUES ('92', 'Gludio Colosseum', '32612', '32734', '0');
INSERT INTO `getback_restart` VALUES ('95', 'Silver knight Colosseum', '33080', '33392', '4');
INSERT INTO `getback_restart` VALUES ('98', 'Welldone Colosseum', '33705', '32504', '4');
INSERT INTO `getback_restart` VALUES ('101', '傲慢の塔1F', '32781', '32816', '101');
INSERT INTO `getback_restart` VALUES ('102', '傲慢の塔2F', '32781', '32816', '101');
INSERT INTO `getback_restart` VALUES ('103', '傲慢の塔3F', '32781', '32816', '101');
INSERT INTO `getback_restart` VALUES ('104', '傲慢の塔4F', '32781', '32816', '101');
INSERT INTO `getback_restart` VALUES ('105', '傲慢の塔5F', '32781', '32816', '101');
INSERT INTO `getback_restart` VALUES ('106', '傲慢の塔6F', '32781', '32816', '101');
INSERT INTO `getback_restart` VALUES ('107', '傲慢の塔7F', '32781', '32816', '101');
INSERT INTO `getback_restart` VALUES ('108', '傲慢の塔8F', '32781', '32816', '101');
INSERT INTO `getback_restart` VALUES ('109', '傲慢の塔9F', '32781', '32816', '101');
INSERT INTO `getback_restart` VALUES ('110', '傲慢の塔10F', '32781', '32816', '101');
INSERT INTO `getback_restart` VALUES ('111', '傲慢の塔11F', '32781', '32816', '101');
INSERT INTO `getback_restart` VALUES ('112', '傲慢の塔12F', '32781', '32816', '101');
INSERT INTO `getback_restart` VALUES ('113', '傲慢の塔13F', '32781', '32816', '101');
INSERT INTO `getback_restart` VALUES ('114', '傲慢の塔14F', '32781', '32816', '101');
INSERT INTO `getback_restart` VALUES ('115', '傲慢の塔15F', '32781', '32816', '101');
INSERT INTO `getback_restart` VALUES ('116', '傲慢の塔16F', '32781', '32816', '101');
INSERT INTO `getback_restart` VALUES ('117', '傲慢の塔17F', '32781', '32816', '101');
INSERT INTO `getback_restart` VALUES ('118', '傲慢の塔18F', '32781', '32816', '101');
INSERT INTO `getback_restart` VALUES ('119', '傲慢の塔19F', '32781', '32816', '101');
INSERT INTO `getback_restart` VALUES ('120', '傲慢の塔20F', '32781', '32816', '101');
INSERT INTO `getback_restart` VALUES ('121', '傲慢の塔21F', '32781', '32816', '101');
INSERT INTO `getback_restart` VALUES ('122', '傲慢の塔22F', '32781', '32816', '101');
INSERT INTO `getback_restart` VALUES ('123', '傲慢の塔23F', '32781', '32816', '101');
INSERT INTO `getback_restart` VALUES ('124', '傲慢の塔24F', '32781', '32816', '101');
INSERT INTO `getback_restart` VALUES ('125', '傲慢の塔25F', '32781', '32816', '101');
INSERT INTO `getback_restart` VALUES ('126', '傲慢の塔26F', '32781', '32816', '101');
INSERT INTO `getback_restart` VALUES ('127', '傲慢の塔27F', '32781', '32816', '101');
INSERT INTO `getback_restart` VALUES ('128', '傲慢の塔28F', '32781', '32816', '101');
INSERT INTO `getback_restart` VALUES ('129', '傲慢の塔29F', '32781', '32816', '101');
INSERT INTO `getback_restart` VALUES ('130', '傲慢の塔30F', '32781', '32816', '101');
INSERT INTO `getback_restart` VALUES ('131', '傲慢の塔31F', '32781', '32816', '101');
INSERT INTO `getback_restart` VALUES ('132', '傲慢の塔32F', '32781', '32816', '101');
INSERT INTO `getback_restart` VALUES ('133', '傲慢の塔33F', '32781', '32816', '101');
INSERT INTO `getback_restart` VALUES ('134', '傲慢の塔34F', '32781', '32816', '101');
INSERT INTO `getback_restart` VALUES ('135', '傲慢の塔35F', '32781', '32816', '101');
INSERT INTO `getback_restart` VALUES ('136', '傲慢の塔36F', '32781', '32816', '101');
INSERT INTO `getback_restart` VALUES ('137', '傲慢の塔37F', '32781', '32816', '101');
INSERT INTO `getback_restart` VALUES ('138', '傲慢の塔38F', '32781', '32816', '101');
INSERT INTO `getback_restart` VALUES ('139', '傲慢の塔39F', '32781', '32816', '101');
INSERT INTO `getback_restart` VALUES ('140', '傲慢の塔40F', '32781', '32816', '101');
INSERT INTO `getback_restart` VALUES ('141', '傲慢の塔41F', '32781', '32816', '101');
INSERT INTO `getback_restart` VALUES ('142', '傲慢の塔42F', '32781', '32816', '101');
INSERT INTO `getback_restart` VALUES ('143', '傲慢の塔43F', '32781', '32816', '101');
INSERT INTO `getback_restart` VALUES ('144', '傲慢の塔44F', '32781', '32816', '101');
INSERT INTO `getback_restart` VALUES ('145', '傲慢の塔45F', '32781', '32816', '101');
INSERT INTO `getback_restart` VALUES ('146', '傲慢の塔46F', '32781', '32816', '101');
INSERT INTO `getback_restart` VALUES ('147', '傲慢の塔47F', '32781', '32816', '101');
INSERT INTO `getback_restart` VALUES ('148', '傲慢の塔48F', '32781', '32816', '101');
INSERT INTO `getback_restart` VALUES ('149', '傲慢の塔49F', '32781', '32816', '101');
INSERT INTO `getback_restart` VALUES ('150', '傲慢の塔50F', '32781', '32816', '101');
INSERT INTO `getback_restart` VALUES ('151', '傲慢の塔51F', '32781', '32816', '101');
INSERT INTO `getback_restart` VALUES ('152', '傲慢の塔52F', '32781', '32816', '101');
INSERT INTO `getback_restart` VALUES ('153', '傲慢の塔53F', '32781', '32816', '101');
INSERT INTO `getback_restart` VALUES ('154', '傲慢の塔54F', '32781', '32816', '101');
INSERT INTO `getback_restart` VALUES ('155', '傲慢の塔55F', '32781', '32816', '101');
INSERT INTO `getback_restart` VALUES ('156', '傲慢の塔56F', '32781', '32816', '101');
INSERT INTO `getback_restart` VALUES ('157', '傲慢の塔57F', '32781', '32816', '101');
INSERT INTO `getback_restart` VALUES ('158', '傲慢の塔58F', '32781', '32816', '101');
INSERT INTO `getback_restart` VALUES ('159', '傲慢の塔59F', '32781', '32816', '101');
INSERT INTO `getback_restart` VALUES ('160', '傲慢の塔60F', '32781', '32816', '101');
INSERT INTO `getback_restart` VALUES ('161', '傲慢の塔61F', '32781', '32816', '101');
INSERT INTO `getback_restart` VALUES ('162', '傲慢の塔62F', '32781', '32816', '101');
INSERT INTO `getback_restart` VALUES ('163', '傲慢の塔63F', '32781', '32816', '101');
INSERT INTO `getback_restart` VALUES ('164', '傲慢の塔64F', '32781', '32816', '101');
INSERT INTO `getback_restart` VALUES ('165', '傲慢の塔65F', '32781', '32816', '101');
INSERT INTO `getback_restart` VALUES ('166', '傲慢の塔66F', '32781', '32816', '101');
INSERT INTO `getback_restart` VALUES ('167', '傲慢の塔67F', '32781', '32816', '101');
INSERT INTO `getback_restart` VALUES ('168', '傲慢の塔68F', '32781', '32816', '101');
INSERT INTO `getback_restart` VALUES ('169', '傲慢の塔69F', '32781', '32816', '101');
INSERT INTO `getback_restart` VALUES ('170', '傲慢の塔70F', '32781', '32816', '101');
INSERT INTO `getback_restart` VALUES ('171', '傲慢の塔71F', '32781', '32816', '101');
INSERT INTO `getback_restart` VALUES ('172', '傲慢の塔72F', '32781', '32816', '101');
INSERT INTO `getback_restart` VALUES ('173', '傲慢の塔73F', '32781', '32816', '101');
INSERT INTO `getback_restart` VALUES ('174', '傲慢の塔74F', '32781', '32816', '101');
INSERT INTO `getback_restart` VALUES ('175', '傲慢の塔75F', '32781', '32816', '101');
INSERT INTO `getback_restart` VALUES ('176', '傲慢の塔76F', '32781', '32816', '101');
INSERT INTO `getback_restart` VALUES ('177', '傲慢の塔77F', '32781', '32816', '101');
INSERT INTO `getback_restart` VALUES ('178', '傲慢の塔78F', '32781', '32816', '101');
INSERT INTO `getback_restart` VALUES ('179', '傲慢の塔79F', '32781', '32816', '101');
INSERT INTO `getback_restart` VALUES ('180', '傲慢の塔80F', '32781', '32816', '101');
INSERT INTO `getback_restart` VALUES ('181', '傲慢の塔81F', '32781', '32816', '101');
INSERT INTO `getback_restart` VALUES ('182', '傲慢の塔82F', '32781', '32816', '101');
INSERT INTO `getback_restart` VALUES ('183', '傲慢の塔83F', '32781', '32816', '101');
INSERT INTO `getback_restart` VALUES ('184', '傲慢の塔84F', '32781', '32816', '101');
INSERT INTO `getback_restart` VALUES ('185', '傲慢の塔85F', '32781', '32816', '101');
INSERT INTO `getback_restart` VALUES ('186', '傲慢の塔86F', '32781', '32816', '101');
INSERT INTO `getback_restart` VALUES ('187', '傲慢の塔87F', '32781', '32816', '101');
INSERT INTO `getback_restart` VALUES ('188', '傲慢の塔88F', '32781', '32816', '101');
INSERT INTO `getback_restart` VALUES ('189', '傲慢の塔89F', '32781', '32816', '101');
INSERT INTO `getback_restart` VALUES ('190', '傲慢の塔90F', '32781', '32816', '101');
INSERT INTO `getback_restart` VALUES ('191', '傲慢の塔91F', '32781', '32816', '101');
INSERT INTO `getback_restart` VALUES ('192', '傲慢の塔92F', '32781', '32816', '101');
INSERT INTO `getback_restart` VALUES ('193', '傲慢の塔93F', '32781', '32816', '101');
INSERT INTO `getback_restart` VALUES ('194', '傲慢の塔94F', '32781', '32816', '101');
INSERT INTO `getback_restart` VALUES ('195', '傲慢の塔95F', '32781', '32816', '101');
INSERT INTO `getback_restart` VALUES ('196', '傲慢の塔96F', '32781', '32816', '101');
INSERT INTO `getback_restart` VALUES ('197', '傲慢の塔97F', '32781', '32816', '101');
INSERT INTO `getback_restart` VALUES ('198', '傲慢の塔98F', '32781', '32816', '101');
INSERT INTO `getback_restart` VALUES ('199', '傲慢の塔99F', '32781', '32816', '101');
INSERT INTO `getback_restart` VALUES ('200', '傲慢の塔100F', '32781', '32816', '101');
INSERT INTO `getback_restart` VALUES ('303', '梦幻の岛', '33976', '32936', '4');
INSERT INTO `getback_restart` VALUES ('446', 'Ship Pirate island to Hidden dock', '32297', '33087', '440');
INSERT INTO `getback_restart` VALUES ('447', 'Ship Hidden dock to Pirate island', '32750', '32874', '445');
INSERT INTO `getback_restart` VALUES ('451', 'ラスタバ城:集会场1F', '32744', '32818', '450');
INSERT INTO `getback_restart` VALUES ('452', 'ラスタバ城:突击队训练场1F', '32744', '32818', '450');
INSERT INTO `getback_restart` VALUES ('453', 'ラスタバ城:魔兽军王の执务室1F', '32744', '32818', '450');
INSERT INTO `getback_restart` VALUES ('454', 'ラスタバ城:野兽调教室1F', '32744', '32818', '450');
INSERT INTO `getback_restart` VALUES ('455', 'ラスタバ城:野兽训练室1F', '32744', '32818', '450');
INSERT INTO `getback_restart` VALUES ('456', 'ラスタバ城:魔兽召唤室1F', '32744', '32818', '450');
INSERT INTO `getback_restart` VALUES ('460', 'ラスタバ城:黑魔法训练场2F', '32667', '32863', '457');
INSERT INTO `getback_restart` VALUES ('461', 'ラスタバ城:黑魔法研究室2F', '32667', '32863', '457');
INSERT INTO `getback_restart` VALUES ('462', 'ラスタバ城:魔灵军王の执务室2F', '32667', '32863', '457');
INSERT INTO `getback_restart` VALUES ('463', 'ラスタバ城:魔灵军王の书斋2F', '32667', '32863', '457');
INSERT INTO `getback_restart` VALUES ('464', 'ラスタバ城:精灵召唤室2F', '32667', '32863', '457');
INSERT INTO `getback_restart` VALUES ('465', 'ラスタバ城:精灵の生息地2F', '32667', '32863', '457');
INSERT INTO `getback_restart` VALUES ('466', 'ラスタバ城:闇の精灵研究室2F', '32667', '32863', '457');
INSERT INTO `getback_restart` VALUES ('470', 'ラスタバ城:恶灵の祭坛3F', '32671', '32855', '467');
INSERT INTO `getback_restart` VALUES ('471', 'ラスタバ城:デビルロードの祭坛3F', '32671', '32855', '467');
INSERT INTO `getback_restart` VALUES ('472', 'ラスタバ城:佣兵训练场3F', '32671', '32855', '467');
INSERT INTO `getback_restart` VALUES ('473', 'ラスタバ城:冥法军の训练场3F', '32671', '32855', '467');
INSERT INTO `getback_restart` VALUES ('474', 'ラスタバ城:オーム实验室3F', '32671', '32855', '467');
INSERT INTO `getback_restart` VALUES ('475', 'ラスタバ城:冥法军王の执务室3F', '32671', '32855', '467');
INSERT INTO `getback_restart` VALUES ('476', 'ラスタバ城:中央コントロールルーム3F', '32671', '32855', '467');
INSERT INTO `getback_restart` VALUES ('477', 'ラスタバ城:デビルロードの佣兵室3F', '32671', '32855', '467');
INSERT INTO `getback_restart` VALUES ('478', 'ラスタバ城:立入禁止エリア3F', '32671', '32855', '467');
INSERT INTO `getback_restart` VALUES ('490', 'ラスタバ城:地下训练场B1F', '32744', '32818', '450');
INSERT INTO `getback_restart` VALUES ('491', 'ラスタバ城:地下通路B1F', '32744', '32818', '450');
INSERT INTO `getback_restart` VALUES ('492', 'ラスタバ城:暗杀军王の执务室B1F', '32744', '32818', '450');
INSERT INTO `getback_restart` VALUES ('493', 'ラスタバ城:地下コントロールルームB1F', '32744', '32818', '450');
INSERT INTO `getback_restart` VALUES ('494', 'ラスタバ城:地下处刑场B1F', '32744', '32818', '450');
INSERT INTO `getback_restart` VALUES ('495', 'ラスタバ城:地下决斗场B1F', '32744', '32818', '450');
INSERT INTO `getback_restart` VALUES ('496', 'ラスタバ城:地下牢B1F', '32744', '32818', '450');
INSERT INTO `getback_restart` VALUES ('530', 'ラスタバ城:グランカインの神殿/ケイナの部屋', '32744', '32792', '536');
INSERT INTO `getback_restart` VALUES ('531', 'ラスタバ城:ビアタス/バロメス/エンディアスの部屋', '32744', '32792', '536');
INSERT INTO `getback_restart` VALUES ('532', 'ラスタバ城:庭园/イデアの部屋', '32744', '32792', '536');
INSERT INTO `getback_restart` VALUES ('533', 'ラスタバ城:ティアメス/ラミアス/バロードの部屋', '32744', '32792', '536');
INSERT INTO `getback_restart` VALUES ('534', 'ラスタバ城:カサンドラ/ダンテスの部屋', '32744', '32792', '536');
INSERT INTO `getback_restart` VALUES ('535', 'ダークエルフの圣地', '32744', '32792', '536');
INSERT INTO `getback_restart` VALUES ('550', '船の墓场:地上层', '32844', '32694', '550');
INSERT INTO `getback_restart` VALUES ('551', '船の墓场:大型船内1F', '32844', '32694', '550');
INSERT INTO `getback_restart` VALUES ('552', '船の墓场:大型船内1F(水中)', '32844', '32694', '550');
INSERT INTO `getback_restart` VALUES ('554', '船の墓场:大型船内2F', '32844', '32694', '550');
INSERT INTO `getback_restart` VALUES ('555', '船の墓场:大型船内2F(水中)', '32844', '32694', '550');
INSERT INTO `getback_restart` VALUES ('557', '船の墓场:船内', '32844', '32694', '550');
INSERT INTO `getback_restart` VALUES ('558', '船の墓场:深海层', '32844', '32694', '550');
INSERT INTO `getback_restart` VALUES ('600', '欲望の洞窟外周部', '32608', '33178', '4');
INSERT INTO `getback_restart` VALUES ('601', '欲望の洞窟ロビー', '32608', '33178', '4');
INSERT INTO `getback_restart` VALUES ('608', 'ヤヒの实验室', '34053', '32284', '4');
INSERT INTO `getback_restart` VALUES ('777', '见弃てられた者たちの地(空间の歪)', '34043', '32184', '4');
INSERT INTO `getback_restart` VALUES ('778', '见弃てられた者たちの地(次元の门・地上)', '32608', '33178', '4');
INSERT INTO `getback_restart` VALUES ('779', '见弃てられた者たちの地(次元の门・海底)', '32608', '33178', '4');
INSERT INTO `getback_restart` VALUES ('780', 'テーベ砂漠', '33966', '33253', '4');
INSERT INTO `getback_restart` VALUES ('781', 'テーベ ピラミッド内部', '33966', '33253', '4');
INSERT INTO `getback_restart` VALUES ('782', 'テーベ オシリス祭坛', '33966', '33253', '4');
INSERT INTO `getback_restart` VALUES ('5124', 'Fishing place', '32815', '32809', '5124');
INSERT INTO `getback_restart` VALUES ('5125', 'petmatch place', '32628', '32781', '4');
INSERT INTO `getback_restart` VALUES ('5131', 'petmatch place', '32628', '32781', '4');
INSERT INTO `getback_restart` VALUES ('5132', 'petmatch place', '32628', '32781', '4');
INSERT INTO `getback_restart` VALUES ('5133', 'petmatch place', '32628', '32781', '4');
INSERT INTO `getback_restart` VALUES ('5134', 'petmatch place', '32628', '32781', '4');
INSERT INTO `getback_restart` VALUES ('5140', 'お化け屋敷', '32624', '32813', '4');
INSERT INTO `getback_restart` VALUES ('5143', 'race', '32628', '32772', '4');
INSERT INTO `getback_restart` VALUES ('16384', 'Talking Island Hotel', '32599', '32931', '0');
INSERT INTO `getback_restart` VALUES ('16896', 'Talking Island Hotel', '32599', '32931', '0');
INSERT INTO `getback_restart` VALUES ('17408', 'Gludio Hotel', '32631', '32761', '4');
INSERT INTO `getback_restart` VALUES ('17920', 'Gludio Hotel', '32631', '32761', '4');
INSERT INTO `getback_restart` VALUES ('18432', 'Giran Hotel', '33437', '32790', '4');
INSERT INTO `getback_restart` VALUES ('18944', 'Giran Hotel', '33437', '32790', '4');
INSERT INTO `getback_restart` VALUES ('19456', 'Oren Hotel', '34067', '32254', '4');
INSERT INTO `getback_restart` VALUES ('19968', 'Oren Hotel', '34067', '32254', '4');
INSERT INTO `getback_restart` VALUES ('20480', 'Windawood Hotel', '32627', '33167', '4');
INSERT INTO `getback_restart` VALUES ('20992', 'Windawood Hotel', '32627', '33167', '4');
INSERT INTO `getback_restart` VALUES ('21504', 'SKT Hotel', '33115', '33379', '4');
INSERT INTO `getback_restart` VALUES ('22016', 'SKT Hotel', '33115', '33379', '4');
INSERT INTO `getback_restart` VALUES ('22528', 'Heine Hotel', '33604', '33276', '4');
INSERT INTO `getback_restart` VALUES ('23040', 'Heine Hotel', '33604', '33276', '4');
INSERT INTO `getback_restart` VALUES ('2005', '新隐藏之谷', '32691', '32864', '2005');
INSERT INTO `getback_restart` VALUES ('86', '隐藏之谷地监', '32691', '32864', '2005');
INSERT INTO `getback_restart` VALUES ('1002', '侏儒部落', '33705', '32504', '4');
INSERT INTO `getback_restart` VALUES ('783', '提卡尔 废墟村落', '32795', '32751', '783');
INSERT INTO `getback_restart` VALUES ('784', '提卡尔 库库尔坎祭坛', '32795', '32751', '783');
INSERT INTO `getback_restart` VALUES ('1005', '安塔瑞斯栖息地', '33710', '32521', '4');
INSERT INTO `getback_restart` VALUES ('1006', '安塔瑞斯栖息地', '33710', '32521', '4');
INSERT INTO `getback_restart` VALUES ('1007', '安塔瑞斯栖息地', '33710', '32521', '4');
INSERT INTO `getback_restart` VALUES ('1008', '安塔瑞斯栖息地', '33710', '32521', '4');
INSERT INTO `getback_restart` VALUES ('1009', '安塔瑞斯栖息地', '33710', '32521', '4');
INSERT INTO `getback_restart` VALUES ('1010', '安塔瑞斯栖息地', '33710', '32521', '4');
INSERT INTO `getback_restart` VALUES ('1011', '法利昂栖息地', '33710', '32521', '4');
INSERT INTO `getback_restart` VALUES ('1012', '法利昂栖息地', '33710', '32521', '4');
INSERT INTO `getback_restart` VALUES ('1013', '法利昂栖息地', '33710', '32521', '4');
INSERT INTO `getback_restart` VALUES ('1014', '法利昂栖息地', '33710', '32521', '4');
INSERT INTO `getback_restart` VALUES ('1015', '法利昂栖息地', '33710', '32521', '4');
INSERT INTO `getback_restart` VALUES ('1016', '法利昂栖息地', '33710', '32521', '4');
INSERT INTO `getback_restart` VALUES ('5300', '钓鱼池', '32608', '32772', '4');
INSERT INTO `getback_restart` VALUES ('5301', '钓鱼池', '32608', '32772', '4');
INSERT INTO `getback_restart` VALUES ('5302', '钓鱼池', '32608', '32772', '4');
INSERT INTO `getback_restart` VALUES ('306', '黑暗妖精试炼地监', '32896', '32663', '4');
