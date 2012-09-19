#于 shop资料表 gash_price栏位 之后新增 delete_day栏位
ALTER TABLE `shop` Add `delete_day` int(10) unsigned NOT NULL default '0' AFTER `gash_price`;
#于 shop资料表 delete_day栏位 之后新增 delete_date栏位
ALTER TABLE `shop` Add `delete_date` datetime default NULL AFTER `delete_day`;
