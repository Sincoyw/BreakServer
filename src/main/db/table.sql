USE `breakserver`;

-- ----------------------------
-- Table structure for `tbl_user`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_user`;
CREATE TABLE `tbl_user` (
  `user_id` INT(10) UNSIGNED NOT NULL UNIQUE AUTO_INCREMENT COMMENT '用户id',
  `email` VARCHAR(320) NOT NULL COMMENT '邮箱',
  `password` CHAR(64) NOT NULL COMMENT '密码',
  `user_name` CHAR(64) NOT NULL COMMENT '用户名',
  `country_code` VARCHAR(20) NOT NULL COMMENT '国家代码',
  `phone` VARCHAR(20) NOT NULL COMMENT '手机号',
  `first_name` CHAR(64) NOT NULL COMMENT '名字第一部分',
  `last_name` CHAR(64) NOT NULL COMMENT '名字第二部分',
  `birthday` DATE NOT NULL COMMENT '生日',
  `gender` TINYINT(5) UNSIGNED NOT NULL DEFAULT '1' COMMENT '角色性别',
  PRIMARY KEY (`user_id`), UNIQUE (`email`), UNIQUE (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=20000 DEFAULT CHARSET=utf8;