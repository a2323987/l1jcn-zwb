#于 characters资料表 char_name栏位 之后新增 FamePoint栏位
ALTER TABLE `characters` ADD `FamePoint` int(10) default '0' AFTER `char_name`;
