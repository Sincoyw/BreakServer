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
  `birthday` DATE DEFAULT NULL COMMENT '生日',
  `gender` TINYINT(5) UNSIGNED NOT NULL DEFAULT '1' COMMENT '角色性别',
  PRIMARY KEY (`user_id`), UNIQUE (`email`), UNIQUE (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=20000 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `tbl_role`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_role`;
CREATE TABLE `tbl_role` (
  `role_id` INT(10) UNSIGNED NOT NULL UNIQUE AUTO_INCREMENT COMMENT '角色id',
  `name` VARCHAR(64) NOT NULL COMMENT '角色名',
  PRIMARY KEY (`role_id`), UNIQUE (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `tbl_privilege`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_privilege`;
CREATE TABLE `tbl_privilege` (
  `privilege_id` INT(10) UNSIGNED NOT NULL UNIQUE AUTO_INCREMENT COMMENT '权限id',
  `name` VARCHAR(64) NOT NULL COMMENT '权限名',
  PRIMARY KEY (`privilege_id`), UNIQUE (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `users_roles`
-- ----------------------------
DROP TABLE IF EXISTS `users_roles`;
CREATE TABLE `users_roles` (
  `user_id` INT(10) UNSIGNED NOT NULL COMMENT '用户id',
  `role_id` INT(10) UNSIGNED NOT NULL COMMENT '角色id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `roles_privileges`
-- ----------------------------
DROP TABLE IF EXISTS `roles_privileges`;
CREATE TABLE `roles_privileges` (
  `role_id` INT(10) UNSIGNED NOT NULL COMMENT '角色id',
  `privilege_id` INT(10) UNSIGNED NOT NULL COMMENT '权限id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
