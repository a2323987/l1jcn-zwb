#于 beginner资料表 bless栏位 之后新增 DeleteDay栏位
ALTER TABLE `beginner` Add `DeleteDay` int(10) NOT NULL default '0' AFTER `bless`;

#于 character_items资料表 m_def栏位 之后新增 DeleteDate栏位
ALTER TABLE `character_items` Add `DeleteDate` datetime default NULL AFTER `m_def`;
