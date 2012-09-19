#更新 etcitem 小屋家具道具的 item_type
update `etcitem` set `item_type` = 'furniture' WHERE `item_id` >= '41383' and `item_id` <= '41400' ;
update `etcitem` set `item_type` = 'furniture' WHERE `item_id` = '41401' ;
update `etcitem` set `item_type` = 'furniture' WHERE `item_id` >= '49065' and `item_id` <= '49076' ;
#于 petitem资料表 m_def栏位 之后新增 exp_rate栏位
ALTER TABLE `petitem` ADD  `exp_rate` int(10) NOT NULL default '100' AFTER `m_def`;
#于 beginner资料表 bless栏位 之后新增 DeleteDay栏位
ALTER TABLE `beginner` Add `DeleteDay` int(10) NOT NULL default '0' AFTER `bless`;

#于 character_items资料表 m_def栏位 之后新增 DeleteDate栏位
ALTER TABLE `character_items` Add `DeleteDate` datetime default NULL AFTER `m_def`;
#于 armor资料表 grade栏位 之后新增 delete_day栏位
ALTER TABLE `armor` Add `delete_day` int(10) unsigned NOT NULL default '0' AFTER `grade`;
#于 armor资料表 delete_day栏位 之后新增 delete_date栏位
ALTER TABLE `armor` Add `delete_date` datetime default NULL AFTER `delete_day`;

#于 etcitem资料表 save_at_once栏位 之后新增 delete_day栏位
ALTER TABLE `etcitem` Add `delete_day` int(10) unsigned NOT NULL default '0' AFTER `save_at_once`;
#于 etcitem资料表 delete_day栏位 之后新增 delete_date栏位
ALTER TABLE `etcitem` Add `delete_date` datetime default NULL AFTER `delete_day`;

#于 weapon资料表 max_use_time栏位 之后新增 delete_day栏位
ALTER TABLE `weapon` Add `delete_day` int(10) unsigned NOT NULL default '0' AFTER `max_use_time`;
#于 weapon资料表 delete_day栏位 之后新增 delete_date栏位
ALTER TABLE `weapon` Add `delete_date` datetime default NULL AFTER `delete_day`;
#于 shop资料表 gash_price栏位 之后新增 delete_day栏位
ALTER TABLE `shop` Add `delete_day` int(10) unsigned NOT NULL default '0' AFTER `gash_price`;
#于 shop资料表 delete_day栏位 之后新增 delete_date栏位
ALTER TABLE `shop` Add `delete_date` datetime default NULL AFTER `delete_day`;
#于 beginner资料表 bless栏位 之后新增 DeleteDay栏位
ALTER TABLE `beginner` Add `DeleteDay` int(10) NOT NULL default '0' AFTER `bless`;

#于 character_items资料表 m_def栏位 之后新增 DeleteDate栏位
ALTER TABLE `character_items` Add `DeleteDate` datetime default NULL AFTER `m_def`;

#于 armor资料表 grade栏位 之后新增 delete_day栏位
ALTER TABLE `armor` Add `delete_day` int(10) unsigned NOT NULL default '0' AFTER `grade`;
#于 armor资料表 delete_day栏位 之后新增 delete_date栏位
ALTER TABLE `armor` Add `delete_date` datetime default NULL AFTER `delete_day`;

#于 etcitem资料表 save_at_once栏位 之后新增 delete_day栏位
ALTER TABLE `etcitem` Add `delete_day` int(10) unsigned NOT NULL default '0' AFTER `save_at_once`;
#于 etcitem资料表 delete_day栏位 之后新增 delete_date栏位
ALTER TABLE `etcitem` Add `delete_date` datetime default NULL AFTER `delete_day`;

#于 weapon资料表 max_use_time栏位 之后新增 delete_day栏位
ALTER TABLE `weapon` Add `delete_day` int(10) unsigned NOT NULL default '0' AFTER `max_use_time`;
#于 weapon资料表 delete_day栏位 之后新增 delete_date栏位
ALTER TABLE `weapon` Add `delete_date` datetime default NULL AFTER `delete_day`;

#于 shop资料表 gash_price栏位 之后新增 delete_day栏位
ALTER TABLE `shop` Add `delete_day` int(10) unsigned NOT NULL default '0' AFTER `gash_price`;
#于 shop资料表 delete_day栏位 之后新增 delete_date栏位
ALTER TABLE `shop` Add `delete_date` datetime default NULL AFTER `delete_day`;
#关闭信纸、血盟的信纸的贩售
update `shop` set `selling_price` = '-1', `pack_count` = '0' WHERE `item_id` = '40310';
update `shop` set `selling_price` = '-1', `pack_count` = '0' WHERE `item_id` = '40311';
update `shop` set `selling_price` = '-1', `pack_count` = '0' WHERE `item_id` = '49016';
update `shop` set `selling_price` = '-1', `pack_count` = '0' WHERE `item_id` = '49017';
update `shop` set `selling_price` = '-1', `pack_count` = '0' WHERE `item_id` = '49018';
update `shop` set `selling_price` = '-1', `pack_count` = '0' WHERE `item_id` = '49019';

#关闭地图:大陆全图、地图:XXXXX的贩售
update `shop` set `selling_price` = '-1', `pack_count` = '0' WHERE `item_id` = '40373';
update `shop` set `selling_price` = '-1', `pack_count` = '0' WHERE `item_id` = '40374';
update `shop` set `selling_price` = '-1', `pack_count` = '0' WHERE `item_id` = '40375';
update `shop` set `selling_price` = '-1', `pack_count` = '0' WHERE `item_id` = '40376';
update `shop` set `selling_price` = '-1', `pack_count` = '0' WHERE `item_id` = '40377';
update `shop` set `selling_price` = '-1', `pack_count` = '0' WHERE `item_id` = '40378';
update `shop` set `selling_price` = '-1', `pack_count` = '0' WHERE `item_id` = '40379';
update `shop` set `selling_price` = '-1', `pack_count` = '0' WHERE `item_id` = '40380';
update `shop` set `selling_price` = '-1', `pack_count` = '0' WHERE `item_id` = '40381';
update `shop` set `selling_price` = '-1', `pack_count` = '0' WHERE `item_id` = '40382';
update `shop` set `selling_price` = '-1', `pack_count` = '0' WHERE `item_id` = '40383';
update `shop` set `selling_price` = '-1', `pack_count` = '0' WHERE `item_id` = '40384';
update `shop` set `selling_price` = '-1', `pack_count` = '0' WHERE `item_id` = '40385';
update `shop` set `selling_price` = '-1', `pack_count` = '0' WHERE `item_id` = '40386';
update `shop` set `selling_price` = '-1', `pack_count` = '0' WHERE `item_id` = '40387';
update `shop` set `selling_price` = '-1', `pack_count` = '0' WHERE `item_id` = '40388';
update `shop` set `selling_price` = '-1', `pack_count` = '0' WHERE `item_id` = '40389';
update `shop` set `selling_price` = '-1', `pack_count` = '0' WHERE `item_id` = '40390';

#关闭指定传送(潘朵拉)的贩售
update `shop` set `selling_price` = '-1', `pack_count` = '0' WHERE `item_id` = '42027';
#于 armor资料表 itemdesc_id栏位 之后新增 itemdesc_extra栏位
ALTER TABLE `armor` Add `itemdesc_extra` varchar(1000) default NULL AFTER `itemdesc_id`;

#于 etcitem资料表 itemdesc_id栏位 之后新增 itemdesc_extra栏位
ALTER TABLE `etcitem` Add `itemdesc_extra` varchar(1000) default NULL AFTER `itemdesc_id`;

#于 weapon资料表 itemdesc_id栏位 之后新增 itemdesc_extra栏位
ALTER TABLE `weapon` Add `itemdesc_extra` varchar(1000) default NULL AFTER `itemdesc_id`;
#于 magic_doll资料表 effect_chance栏位 之后新增 exp_rate_chance栏位
ALTER TABLE `magic_doll` ADD `exp_rate_chance` tinyint(2) NOT NULL default '0' AFTER `effect_chance`;

#于 magic_doll资料表 exp_rate_chance栏位 之后新增 exp_rate栏位
ALTER TABLE `magic_doll` ADD `exp_rate` int(10) NOT NULL default '100' AFTER `exp_rate_chance`;

#于 magic_doll资料表 exp_rate栏位 之后新增 isHaste栏位
ALTER TABLE `magic_doll` ADD `isHaste` tinyint(2) NOT NULL default '0' AFTER `exp_rate`;

#于 magic_doll资料表 isHaste栏位 之后新增 hp栏位
ALTER TABLE `magic_doll` ADD `hp` int(10) NOT NULL default '0' AFTER `isHaste`;

#于 magic_doll资料表 isHaste栏位 之后新增 mp栏位
ALTER TABLE `magic_doll` ADD `mp` int(10) NOT NULL default '0' AFTER `hp`;

#于 magic_doll资料表 mp栏位 之后新增 hit_modifier栏位
ALTER TABLE `magic_doll` ADD `hit_modifier` int(10) NOT NULL default '0' AFTER `mp`;

#于 magic_doll资料表 hit_modifier栏位 之后新增 dmg_modifier栏位
ALTER TABLE `magic_doll` ADD `dmg_modifier` int(10) NOT NULL default '0' AFTER `hit_modifier`;#于 shop资料表 purchasing_price栏位 之后新增 gash_price栏位
ALTER TABLE `shop` ADD `gash_price` int(10) NOT NULL default '-1' AFTER `purchasing_price`;
#于 characters资料表 char_name栏位 之后新增 FamePoint栏位
ALTER TABLE `characters` ADD `FamePoint` int(10) default '0' AFTER `char_name`;
#于 characters资料表 DeleteTime栏位 之后新增 LastActive栏位
ALTER TABLE `characters` ADD `LastActive` datetime default NULL AFTER `DeleteTime`;

#于 characters资料表 LastActive栏位 之后新增 EinhasadPoint栏位
ALTER TABLE `characters` ADD `EinhasadPoint` int(10) default NULL AFTER `LastActive`;
