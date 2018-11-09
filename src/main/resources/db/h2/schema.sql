DROP TABLE IF EXISTS `coder`;
CREATE TABLE `coder` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(50)  NOT NULL COMMENT '姓名',
  `sex` char(2)  NOT NULL COMMENT '姓别',
  `age` int(10) unsigned DEFAULT NULL COMMENT '年龄',
  `address` varchar(100)  DEFAULT NULL COMMENT '地址',
  `phone` varchar(20)  DEFAULT NULL COMMENT '电话',
  PRIMARY KEY (`id`)
) COMMENT='码农';

