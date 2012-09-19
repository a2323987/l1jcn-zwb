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
