DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` VARCHAR (45) NOT  NULL ,
  `user_name` VARCHAR (100) ,
  `nick_name` VARCHAR (100),
  `password` CHAR (32),
  `email` VARCHAR (50),
  `phone` VARCHAR (50),
  `sex` ENUM('S_MALE','S_FEMALE','S_BM'),
  `status` ENUM('S_OFF','S_NORMAL'),
  `avatar` VARCHAR (100),
  `remarks` VARCHAR (200),
  `add_at` BIGINT,
  `update_at` BIGINT,
  PRIMARY KEY (`id`)
) DEFAULT CHARSET=utf8;