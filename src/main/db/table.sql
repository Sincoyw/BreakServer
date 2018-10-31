USE `breakserver`;

-- ----------------------------
-- Table structure for `tbl_user`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_user`;
CREATE TABLE `tbl_user` (
  `user_id` INT(10) UNSIGNED NOT NULL UNIQUE AUTO_INCREMENT COMMENT '用户id',
  `user_name` CHAR(64) NOT NULL COMMENT '用户名',
  `password` CHAR(64) NOT NULL COMMENT '密码',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20000 DEFAULT CHARSET=utf8;