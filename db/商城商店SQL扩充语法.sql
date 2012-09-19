#于 shop资料表 purchasing_price栏位 之后新增 gash_price栏位
ALTER TABLE `shop` ADD `gash_price` int(10) NOT NULL default '-1' AFTER `purchasing_price`;
