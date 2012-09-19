#于 armor资料表 itemdesc_id栏位 之后新增 itemdesc_extra栏位
ALTER TABLE `armor` Add `itemdesc_extra` varchar(1000) default NULL AFTER `itemdesc_id`;

#于 etcitem资料表 itemdesc_id栏位 之后新增 itemdesc_extra栏位
ALTER TABLE `etcitem` Add `itemdesc_extra` varchar(1000) default NULL AFTER `itemdesc_id`;

#于 weapon资料表 itemdesc_id栏位 之后新增 itemdesc_extra栏位
ALTER TABLE `weapon` Add `itemdesc_extra` varchar(1000) default NULL AFTER `itemdesc_id`;
