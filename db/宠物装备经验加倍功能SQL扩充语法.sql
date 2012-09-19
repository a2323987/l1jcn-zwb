#于 petitem资料表 m_def栏位 之后新增 exp_rate栏位
ALTER TABLE `petitem` ADD  `exp_rate` int(10) NOT NULL default '100' AFTER `m_def`;
