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
