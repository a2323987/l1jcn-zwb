alter table shop add EnchantLevel int(10) NOT NULL DEFAULT '0',;


update shop set EnchantLevel = 0 ;