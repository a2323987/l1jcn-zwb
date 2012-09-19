#于 characters资料表 DeleteTime栏位 之后新增 LastActive栏位
ALTER TABLE `characters` ADD `LastActive` datetime default NULL AFTER `DeleteTime`;

#于 characters资料表 LastActive栏位 之后新增 EinhasadPoint栏位
ALTER TABLE `characters` ADD `EinhasadPoint` int(10) default NULL AFTER `LastActive`;
