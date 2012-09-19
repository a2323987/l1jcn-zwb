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
ALTER TABLE `magic_doll` ADD `dmg_modifier` int(10) NOT NULL default '0' AFTER `hit_modifier`;