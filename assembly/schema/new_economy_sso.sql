/*
Navicat MySQL Data Transfer

Source Server         : 192.168.81.143
Source Server Version : 50636
Source Host           : 192.168.81.143:3306
Source Database       : new_economy_sso

Target Server Type    : MYSQL
Target Server Version : 50636
File Encoding         : 65001

Date: 2018-07-11 10:27:51
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sso_access
-- ----------------------------
DROP TABLE IF EXISTS `sso_access`;
CREATE TABLE `sso_access` (
  `id` int(16) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `user_id` int(16) NOT NULL COMMENT '用户id',
  `token` varchar(32) NOT NULL COMMENT '生成的access token',
  `valid_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'token的有效期，时间格式：YYYY-MM-DD HH:MM:SS',
  `description` varchar(64) NOT NULL COMMENT '描述',
  `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `sso_access_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `sso_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 COMMENT='用户访问表';

-- ----------------------------
-- Records of sso_access
-- ----------------------------
INSERT INTO `sso_access` VALUES ('22', '1', '7a5f8fe3fc5470ae3b1646ba8309ddc8', '2018-07-11 11:15:04', '初始化', '2018-07-11 10:08:04', '2018-07-11 10:08:31');

-- ----------------------------
-- Table structure for sso_account_valid
-- ----------------------------
DROP TABLE IF EXISTS `sso_account_valid`;
CREATE TABLE `sso_account_valid` (
  `id` int(16) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `email` varchar(32) NOT NULL COMMENT '邮箱',
  `verify_code` varchar(32) NOT NULL COMMENT '验证码',
  `is_verified` int(1) NOT NULL COMMENT '枚举类型，是否被验证过：0或1（0：未被验证，1：被验证）',
  `type` int(1) NOT NULL COMMENT '枚举类型，1:注册，2:找回密码',
  `valid_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '验证码有效期',
  `description` varchar(64) NOT NULL COMMENT '描述',
  `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户账户验证表';

-- ----------------------------
-- Records of sso_account_valid
-- ----------------------------

-- ----------------------------
-- Table structure for sso_app
-- ----------------------------
DROP TABLE IF EXISTS `sso_app`;
CREATE TABLE `sso_app` (
  `id` int(16) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `app_name` varchar(32) NOT NULL COMMENT '应用名',
  `app_logo` varchar(128) NOT NULL COMMENT '应用定制logo地址',
  `app_pic` varchar(128) NOT NULL COMMENT '应用定制背景图地址',
  `is_enable` int(1) NOT NULL COMMENT '是否生效，可选1和0（1：生效，0：失效）',
  `description` varchar(64) NOT NULL COMMENT '描述',
  `last_modifier` varchar(32) NOT NULL COMMENT '最近一次修改者',
  `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `app_name` (`app_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='应用管理表';

-- ----------------------------
-- Records of sso_app
-- ----------------------------
INSERT INTO `sso_app` VALUES ('1', 'bdsso', 'http://ssofile.bbdops.com/f/1511765143011.png', 'http://ssofile.bbdops.com/f/1511765146410.png', '0', '系统初始化', 'admin', '2017-11-23 17:56:56', '2017-11-27 14:45:47');
INSERT INTO `sso_app` VALUES ('2', 'new-economy', 'http://10.28.200.252:8099/bdsso/file/1531703424536.png', 'http://10.28.200.252:8099/bdsso/file/1532315845579.jpg', '0', '成都新经济', 'admin', '2018-07-07 10:29:42', '2018-07-07 10:29:42');

-- ----------------------------
-- Table structure for sso_auth
-- ----------------------------
DROP TABLE IF EXISTS `sso_auth`;
CREATE TABLE `sso_auth` (
  `id` int(16) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `auth_code` varchar(32) NOT NULL COMMENT '权限码',
  `auth_name` varchar(32) NOT NULL COMMENT '权限名称',
  `app_id` int(16) NOT NULL COMMENT '应用id',
  `is_enable` int(1) NOT NULL COMMENT '是否生效 1:生效，0:失效',
  `description` varchar(64) NOT NULL COMMENT '描述',
  `last_modifier` varchar(32) NOT NULL COMMENT '最近一次修改者',
  `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `auth_code` (`auth_code`) USING BTREE,
  UNIQUE KEY `auth_name` (`auth_name`) USING BTREE,
  KEY `app_id` (`app_id`),
  CONSTRAINT `sso_auth_ibfk_1` FOREIGN KEY (`app_id`) REFERENCES `sso_app` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='用户权限表';

-- ----------------------------
-- Records of sso_auth
-- ----------------------------
INSERT INTO `sso_auth` VALUES ('1', 'BDSSO_ADMIN_A', 'BDSSO管理员', '1', '1', '系统初始化', 'admin', '2017-11-23 17:57:00', '2018-07-07 09:34:27');
INSERT INTO `sso_auth` VALUES ('2', 'BDSSO_COMMON_USER_A', 'BDSSO普通用户', '1', '1', '系统初始化', 'admin', '2017-11-23 17:57:00', '2017-11-23 17:57:00');
INSERT INTO `sso_auth` VALUES ('3', 'ECONOMY_ADMIN_A', '新经济管理员权限', '2', '1', '新经济管理员权限', 'admin', '2018-07-07 10:32:40', '2018-07-07 10:32:40');
INSERT INTO `sso_auth` VALUES ('4', 'GOVERNMENT_COMMON_USER_A', '政府普通用户权限', '2', '1', '政府普通用户权限', 'admin', '2018-07-07 10:33:33', '2018-07-10 16:01:41');
INSERT INTO `sso_auth` VALUES ('5', 'GOVERNMENT_LEADER_A', '政府领导用户权限', '2', '1', '政府领导用户权限', 'admin', '2018-07-07 10:37:37', '2018-07-07 10:37:37');
INSERT INTO `sso_auth` VALUES ('6', 'ENTERPRISR_USER_A', '企业用户权限', '2', '1', '企业用户权限', 'admin', '2018-07-07 10:38:31', '2018-07-07 10:38:31');
INSERT INTO `sso_auth` VALUES ('7', 'GOVERNMENT_USER_A', '政府用户权限', '2', '1', '政府用户权限', 'admin', '2018-07-10 16:02:15', '2018-07-10 16:02:15');

-- ----------------------------
-- Table structure for sso_auth_resource
-- ----------------------------
DROP TABLE IF EXISTS `sso_auth_resource`;
CREATE TABLE `sso_auth_resource` (
  `id` int(16) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `auth_id` int(16) NOT NULL COMMENT '权限id',
  `resource_id` int(16) NOT NULL COMMENT '资源id',
  `description` varchar(64) NOT NULL COMMENT '描述',
  `last_modifier` varchar(32) NOT NULL COMMENT '最近一次修改者',
  `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `resource_id` (`resource_id`),
  KEY `auth_id` (`auth_id`),
  CONSTRAINT `sso_auth_resource_ibfk_1` FOREIGN KEY (`resource_id`) REFERENCES `sso_resource` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `sso_auth_resource_ibfk_2` FOREIGN KEY (`auth_id`) REFERENCES `sso_auth` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=154 DEFAULT CHARSET=utf8 COMMENT='权限、资源 关联表';

-- ----------------------------
-- Records of sso_auth_resource
-- ----------------------------
INSERT INTO `sso_auth_resource` VALUES ('1', '1', '1', '系统初始化', 'admin', '2017-11-23 17:57:12', '2017-11-23 17:57:12');
INSERT INTO `sso_auth_resource` VALUES ('3', '1', '3', '系统初始化', 'admin', '2017-11-23 17:57:12', '2017-11-23 17:57:12');
INSERT INTO `sso_auth_resource` VALUES ('4', '1', '4', '系统初始化', 'admin', '2017-11-23 17:57:12', '2017-11-23 17:57:12');
INSERT INTO `sso_auth_resource` VALUES ('5', '1', '5', '系统初始化', 'admin', '2017-11-23 17:57:12', '2017-11-23 17:57:12');
INSERT INTO `sso_auth_resource` VALUES ('6', '1', '6', '系统初始化', 'admin', '2017-11-23 17:57:12', '2017-11-23 17:57:12');
INSERT INTO `sso_auth_resource` VALUES ('7', '1', '7', '系统初始化', 'admin', '2017-11-23 17:57:12', '2017-11-23 17:57:12');
INSERT INTO `sso_auth_resource` VALUES ('8', '1', '8', '系统初始化', 'admin', '2017-11-23 17:57:12', '2017-11-23 17:57:12');
INSERT INTO `sso_auth_resource` VALUES ('9', '1', '9', '系统初始化', 'admin', '2017-11-23 17:57:12', '2017-11-23 17:57:12');
INSERT INTO `sso_auth_resource` VALUES ('10', '1', '10', '系统初始化', 'admin', '2017-11-23 17:57:12', '2017-11-23 17:57:12');
INSERT INTO `sso_auth_resource` VALUES ('11', '2', '1', '系统初始化', 'admin', '2017-11-23 17:57:12', '2017-11-23 17:57:12');
INSERT INTO `sso_auth_resource` VALUES ('13', '2', '3', '系统初始化', 'admin', '2017-11-23 17:57:12', '2017-11-23 17:57:12');
INSERT INTO `sso_auth_resource` VALUES ('14', '2', '4', '系统初始化', 'admin', '2017-11-23 17:57:12', '2017-11-23 17:57:12');
INSERT INTO `sso_auth_resource` VALUES ('15', '2', '5', '系统初始化', 'admin', '2017-11-23 17:57:12', '2017-11-23 17:57:12');
INSERT INTO `sso_auth_resource` VALUES ('16', '2', '6', '系统初始化', 'admin', '2017-11-23 17:57:12', '2017-11-23 17:57:12');
INSERT INTO `sso_auth_resource` VALUES ('17', '2', '7', '系统初始化', 'admin', '2017-11-23 17:57:12', '2017-11-23 17:57:12');
INSERT INTO `sso_auth_resource` VALUES ('18', '2', '8', '系统初始化', 'admin', '2017-11-23 17:57:12', '2017-11-23 17:57:12');
INSERT INTO `sso_auth_resource` VALUES ('19', '2', '9', '系统初始化', 'admin', '2017-11-23 17:57:12', '2017-11-23 17:57:12');
INSERT INTO `sso_auth_resource` VALUES ('20', '2', '10', '系统初始化', 'admin', '2017-11-23 17:57:12', '2017-11-23 17:57:12');
INSERT INTO `sso_auth_resource` VALUES ('21', '1', '2', '应用', 'admin', '2017-11-29 14:02:43', '2017-11-29 14:02:43');
INSERT INTO `sso_auth_resource` VALUES ('28', '7', '19', '初始化', 'admin', '2018-07-07 11:46:24', '2018-07-07 11:46:24');
INSERT INTO `sso_auth_resource` VALUES ('29', '3', '18', '初始化', 'admin', '2018-07-10 18:55:11', '2018-07-10 18:55:11');
INSERT INTO `sso_auth_resource` VALUES ('30', '4', '26', '初始化', 'admin', '2018-07-10 18:55:31', '2018-07-10 18:55:31');
INSERT INTO `sso_auth_resource` VALUES ('31', '4', '17', '初始化', 'admin', '2018-07-10 18:57:22', '2018-07-10 18:57:22');
INSERT INTO `sso_auth_resource` VALUES ('32', '4', '22', '初始化', 'admin', '2018-07-10 18:57:35', '2018-07-10 18:57:35');
INSERT INTO `sso_auth_resource` VALUES ('33', '4', '21', '初始化', 'admin', '2018-07-10 19:16:26', '2018-07-10 19:16:26');
INSERT INTO `sso_auth_resource` VALUES ('34', '4', '15', '初始化', 'admin', '2018-07-10 19:16:41', '2018-07-10 19:16:41');
INSERT INTO `sso_auth_resource` VALUES ('35', '5', '15', '初始化', 'admin', '2018-07-10 19:15:41', '2018-07-10 19:15:41');
INSERT INTO `sso_auth_resource` VALUES ('36', '5', '21', '初始化', 'admin', '2018-07-10 19:16:10', '2018-07-10 19:16:10');
INSERT INTO `sso_auth_resource` VALUES ('37', '6', '12', '初始化', 'admin', '2018-07-10 19:01:33', '2018-07-10 19:01:33');
INSERT INTO `sso_auth_resource` VALUES ('38', '6', '11', '初始化', 'admin', '2018-07-10 18:04:41', '2018-07-10 18:04:41');
INSERT INTO `sso_auth_resource` VALUES ('39', '7', '13', '初始化', 'admin', '2018-07-10 18:41:51', '2018-07-10 18:41:51');
INSERT INTO `sso_auth_resource` VALUES ('40', '7', '14', '初始化', 'admin', '2018-07-10 18:42:09', '2018-07-10 18:42:09');
INSERT INTO `sso_auth_resource` VALUES ('41', '7', '16', '初始化', 'admin', '2018-07-10 18:46:05', '2018-07-10 18:46:05');
INSERT INTO `sso_auth_resource` VALUES ('42', '7', '25', '初始化', 'admin', '2018-07-10 18:46:53', '2018-07-10 18:46:53');
INSERT INTO `sso_auth_resource` VALUES ('43', '7', '23', '初始化', 'admin', '2018-07-10 18:47:07', '2018-07-10 18:47:07');
INSERT INTO `sso_auth_resource` VALUES ('44', '7', '20', '初始化', 'admin', '2018-07-07 11:46:24', '2018-07-07 11:46:24');
INSERT INTO `sso_auth_resource` VALUES ('45', '7', '24', '初始化', 'admin', '2018-07-07 11:46:24', '2018-07-07 11:46:24');

-- ----------------------------
-- Table structure for sso_cache_manager
-- ----------------------------
DROP TABLE IF EXISTS `sso_cache_manager`;
CREATE TABLE `sso_cache_manager` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cache_name` varchar(32) NOT NULL COMMENT '缓存名',
  `server_host` varchar(32) NOT NULL COMMENT '服务器名',
  `server_ip` varchar(15) NOT NULL COMMENT '服务器ip',
  `status` varchar(16) NOT NULL COMMENT '当前缓存的状态',
  `description` varchar(64) NOT NULL COMMENT '描述',
  `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `cache_name` (`cache_name`,`server_host`,`server_ip`,`status`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='token缓存管理表';

-- ----------------------------
-- Records of sso_cache_manager
-- ----------------------------

-- ----------------------------
-- Table structure for sso_resource
-- ----------------------------
DROP TABLE IF EXISTS `sso_resource`;
CREATE TABLE `sso_resource` (
  `id` int(16) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `type` int(1) NOT NULL COMMENT '类型',
  `resource_name` varchar(64) NOT NULL,
  `is_enable` int(1) NOT NULL COMMENT '是否生效，可选1和0（1：生效，0：失效）',
  `description` varchar(64) NOT NULL COMMENT '描述',
  `last_modifier` varchar(32) NOT NULL COMMENT '最近一次修改者',
  `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `resource_name` (`resource_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8 COMMENT='资源表';

-- ----------------------------
-- Records of sso_resource
-- ----------------------------
INSERT INTO `sso_resource` VALUES ('1', '0', 'BDSSO-USER-M', '1', '一级菜单-用户管理', 'admin', '2017-11-23 17:57:06', '2017-11-23 17:57:06');
INSERT INTO `sso_resource` VALUES ('2', '0', 'BDSSO-APPS-M', '1', '一级菜单-应用管理', 'admin', '2017-11-23 17:57:06', '2017-11-23 17:57:06');
INSERT INTO `sso_resource` VALUES ('3', '0', 'BDSSO-ROLE-M', '1', '一级菜单-角色管理', 'admin', '2017-11-23 17:57:06', '2017-11-23 17:57:06');
INSERT INTO `sso_resource` VALUES ('4', '0', 'BDSSO-AUTH-M', '1', '一级菜单-权限管理', 'admin', '2017-11-23 17:57:06', '2017-11-23 17:57:06');
INSERT INTO `sso_resource` VALUES ('5', '0', 'BDSSO-HISTORY-M', '1', '一级菜单-访问记录', 'admin', '2017-11-23 17:57:06', '2017-11-23 17:57:06');
INSERT INTO `sso_resource` VALUES ('6', '0', 'BDSSO-RESOURE-M', '1', '一级菜单-资源管理', 'admin', '2017-11-23 17:57:06', '2017-11-23 17:57:06');
INSERT INTO `sso_resource` VALUES ('7', '0', 'BDSSO-ROLEAUTH-M', '1', '一级菜单-角色权限管理', 'admin', '2017-11-23 17:57:06', '2017-11-23 17:57:06');
INSERT INTO `sso_resource` VALUES ('8', '0', 'BDSSO-USERROLE-M', '1', '一级菜单-用户角色管理', 'admin', '2017-11-23 17:57:06', '2017-11-23 17:57:06');
INSERT INTO `sso_resource` VALUES ('9', '0', 'BDSSO-RESOURCEAUTH-M', '1', '一级菜单-资源权限管理', 'admin', '2017-11-23 17:57:06', '2017-11-23 17:57:06');
INSERT INTO `sso_resource` VALUES ('10', '0', 'BDSSO-GENERAL-M', '1', '一级菜单-用户管理', 'admin', '2017-11-23 17:57:06', '2017-11-23 17:57:06');
INSERT INTO `sso_resource` VALUES ('11', '0', 'ECONOMY_HOME_M', '1', '新经济首页菜单', 'admin', '2018-07-07 10:56:25', '2018-07-07 10:56:25');
INSERT INTO `sso_resource` VALUES ('12', '0', 'ECONOMY_UNICORN_M', '1', '新经济独角兽菜单', 'admin', '2018-07-07 10:58:54', '2018-07-07 10:58:54');
INSERT INTO `sso_resource` VALUES ('13', '0', 'ECONOMY_FACTOR_M', '1', '新经济发展要素分析菜单', 'admin', '2018-07-07 11:00:43', '2018-07-07 11:00:43');
INSERT INTO `sso_resource` VALUES ('14', '0', 'ECONOMY_POLICY_M', '1', '新经济热点政策查询菜单', 'admin', '2018-07-07 11:01:56', '2018-07-07 11:01:56');
INSERT INTO `sso_resource` VALUES ('15', '0', 'ECONOMY_EMIGRATION_M', '1', '新经济重点企业外迁预警菜单', 'admin', '2018-07-07 11:19:02', '2018-07-07 11:19:02');
INSERT INTO `sso_resource` VALUES ('16', '0', 'ECONOMY_REGION_M', '1', '型经济区域发展分析菜单', 'admin', '2018-07-07 11:21:18', '2018-07-07 11:23:18');
INSERT INTO `sso_resource` VALUES ('17', '0', 'ECONOMY_ENTERPRISE_M', '1', '新经济企业查询菜单', 'admin', '2018-07-07 11:22:59', '2018-07-07 11:22:59');
INSERT INTO `sso_resource` VALUES ('18', '1', 'ECONOMY_EMIGRATION_DROPDOWN_B', '1', '新经济重点外迁下拉详情按钮', 'admin', '2018-07-07 11:24:57', '2018-07-07 11:24:57');
INSERT INTO `sso_resource` VALUES ('19', '1', 'ECONOMY_HOME_EXPORT_B', '1', '新经济首页导出按钮', 'admin', '2018-07-10 16:30:54', '2018-07-10 16:32:37');
INSERT INTO `sso_resource` VALUES ('20', '1', 'ECONOMY_UNICORN_EXPORT_B', '1', '新经济独角兽页面导出按钮', 'admin', '2018-07-10 16:32:25', '2018-07-10 16:32:25');
INSERT INTO `sso_resource` VALUES ('21', '1', 'ECONOMY_EMIGRATION_EXPORT_B', '1', '新经济重点外迁页面导出按钮', 'admin', '2018-07-10 16:33:50', '2018-07-10 16:33:50');
INSERT INTO `sso_resource` VALUES ('22', '1', 'ECONOMY_ENTERPRISE_EXPORT_B', '1', '新经济企业查询页面导出按钮', 'admin', '2018-07-10 16:36:00', '2018-07-10 16:36:00');
INSERT INTO `sso_resource` VALUES ('23', '1', 'ECONOMY_POLICY_EXPORT_B', '1', '新经济热点政策查询页面导出按钮', 'admin', '2018-07-10 16:44:53', '2018-07-10 16:49:09');
INSERT INTO `sso_resource` VALUES ('24', '1', 'ECONOMY_FACTOR_EXPORT_B', '1', '新经济发展要素分析页面导出按钮', 'admin', '2018-07-10 16:48:30', '2018-07-10 16:48:30');
INSERT INTO `sso_resource` VALUES ('25', '1', 'ECONOMY_REGION_EXPORT_B', '1', '新经济区域发展分析页面导出按钮', 'admin', '2018-07-10 16:51:18', '2018-07-10 16:51:18');
INSERT INTO `sso_resource` VALUES ('26', '1', 'ECONOMY_SKIP_DETAILS_B', '1', '新经济跳转至企业画像页面按钮', 'admin', '2018-07-10 17:33:26', '2018-07-10 17:33:26');

-- ----------------------------
-- Table structure for sso_role
-- ----------------------------
DROP TABLE IF EXISTS `sso_role`;
CREATE TABLE `sso_role` (
  `id` int(16) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `role_name` varchar(32) NOT NULL COMMENT '角色名',
  `is_enable` int(1) NOT NULL COMMENT '是否生效，可选1和0（1：生效，0：失效）',
  `description` varchar(64) NOT NULL COMMENT '描述',
  `last_modifier` varchar(32) NOT NULL COMMENT '最近一次修改者',
  `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`,`role_name`),
  UNIQUE KEY `role_name` (`role_name`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of sso_role
-- ----------------------------
INSERT INTO `sso_role` VALUES ('1', 'BDSSO_ADMIN_R', '1', '系统初始化', 'admin', '2017-11-23 17:56:52', '2018-07-07 09:33:37');
INSERT INTO `sso_role` VALUES ('2', 'BDSSO_COMMON_USER_R', '1', '系统初始化', 'admin', '2017-11-23 17:56:52', '2017-11-23 19:14:17');
INSERT INTO `sso_role` VALUES ('3', 'ECONOMY_ADMIN_R', '1', '新经济管理员角色', 'admin', '2018-07-07 10:30:36', '2018-07-07 10:32:03');
INSERT INTO `sso_role` VALUES ('4', 'GOVERNMENT_USER_COMMON_R', '1', '政府普通用户角色', 'admin', '2018-07-07 10:31:42', '2018-07-10 16:08:19');
INSERT INTO `sso_role` VALUES ('5', 'GOVERNMENT_LEADER_R', '1', '政府领导用户角色', 'admin', '2018-07-07 10:35:33', '2018-07-07 10:37:53');
INSERT INTO `sso_role` VALUES ('6', 'ENTERPRISR_USER_R', '1', '企业用户角色', 'admin', '2018-07-07 10:36:07', '2018-07-07 10:36:07');

-- ----------------------------
-- Table structure for sso_role_auth
-- ----------------------------
DROP TABLE IF EXISTS `sso_role_auth`;
CREATE TABLE `sso_role_auth` (
  `id` int(16) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `role_id` int(16) NOT NULL COMMENT '角色id',
  `auth_id` int(16) NOT NULL COMMENT '权限id',
  `description` varchar(64) NOT NULL COMMENT '描述',
  `last_modifier` varchar(32) NOT NULL COMMENT '最近一次修改者',
  `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `auth_id` (`auth_id`),
  KEY `role_id` (`role_id`),
  CONSTRAINT `sso_role_auth_ibfk_1` FOREIGN KEY (`auth_id`) REFERENCES `sso_auth` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `sso_role_auth_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `sso_role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=246 DEFAULT CHARSET=utf8 COMMENT='角色、权限 关联表';

-- ----------------------------
-- Records of sso_role_auth
-- ----------------------------
INSERT INTO `sso_role_auth` VALUES ('1', '1', '1', '系统初始化', 'admin', '2017-11-23 17:57:40', '2017-11-23 17:57:40');
INSERT INTO `sso_role_auth` VALUES ('2', '2', '2', '系统初始化', 'admin', '2017-11-23 17:57:40', '2017-11-23 17:57:40');
INSERT INTO `sso_role_auth` VALUES ('3', '3', '3', '系统初始化', 'admin', '2018-07-07 11:33:00', '2018-07-07 11:33:00');
INSERT INTO `sso_role_auth` VALUES ('4', '4', '4', '系统初始化', 'admin', '2018-07-07 11:33:36', '2018-07-07 11:33:36');
INSERT INTO `sso_role_auth` VALUES ('5', '4', '7', '初始化', 'admin', '2018-07-10 16:03:42', '2018-07-10 16:03:42');
INSERT INTO `sso_role_auth` VALUES ('6', '3', '4', '初始化', 'admin', '2018-07-10 19:03:27', '2018-07-10 19:03:27');
INSERT INTO `sso_role_auth` VALUES ('7', '6', '6', '初始化', 'admin', '2018-07-10 19:04:16', '2018-07-10 19:04:16');
INSERT INTO `sso_role_auth` VALUES ('8', '5', '7', '初始化', 'admin', '2018-07-10 19:04:42', '2018-07-10 19:04:42');
INSERT INTO `sso_role_auth` VALUES ('9', '3', '6', '初始化', 'admin', '2018-07-10 19:08:14', '2018-07-10 19:08:14');
INSERT INTO `sso_role_auth` VALUES ('10', '4', '6', '初始化', 'admin', '2018-07-10 19:08:32', '2018-07-10 19:08:32');
INSERT INTO `sso_role_auth` VALUES ('11', '5', '6', '初始化', 'admin', '2018-07-10 19:08:51', '2018-07-10 19:08:51');
INSERT INTO `sso_role_auth` VALUES ('12', '3', '7', '初始化', 'admin', '2018-07-10 19:34:08', '2018-07-10 19:34:08');
INSERT INTO `sso_role_auth` VALUES ('13', '3', '5', '初始化', 'admin', '2018-07-10 19:34:19', '2018-07-10 19:34:19');
INSERT INTO `sso_role_auth` VALUES ('14', '5', '5', '初始化', 'admin', '2018-07-10 19:34:45', '2018-07-10 19:34:45');

-- ----------------------------
-- Table structure for sso_user
-- ----------------------------
DROP TABLE IF EXISTS `sso_user`;
CREATE TABLE `sso_user` (
  `id` int(16) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `user_name` varchar(32) NOT NULL COMMENT '用户名',
  `user_password` varchar(64) NOT NULL COMMENT '用户密码，需要加密后存储',
  `email` varchar(32) NOT NULL COMMENT '用户邮箱',
  `is_enable` int(1) NOT NULL COMMENT '是否生效 1:生效，0:失效',
  `description` varchar(64) NOT NULL COMMENT '描述',
  `last_modifier` varchar(32) NOT NULL COMMENT '最近一次修改者',
  `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of sso_user
-- ----------------------------
INSERT INTO `sso_user` VALUES ('1', 'admin', '547a0681b629a77b479dcda0485d140c', 'middleware@bbdservice.com', '1', '系统初始化', 'admin', '2017-11-23 17:56:49', '2018-07-09 14:39:07');
INSERT INTO `sso_user` VALUES ('2', 'new-economy', '8089e1d2810eaf2d2b1b4c826c91c3e5', 'new-economy@bbdservice.com', '1', '手动添加用户', 'admin', '2018-07-07 10:57:41', '2018-07-07 10:57:41');

-- ----------------------------
-- Table structure for sso_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sso_user_role`;
CREATE TABLE `sso_user_role` (
  `id` int(16) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `user_id` int(16) NOT NULL COMMENT '用户id',
  `role_id` int(16) NOT NULL COMMENT '角色id',
  `description` varchar(64) NOT NULL COMMENT '描述',
  `last_modifier` varchar(32) NOT NULL COMMENT '最近一次修改者',
  `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `un_user_role` (`role_id`,`user_id`) USING BTREE,
  KEY `user_id` (`user_id`) USING BTREE,
  KEY `user_id_2` (`user_id`) USING BTREE,
  CONSTRAINT `sso_user_role_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `sso_role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `sso_user_role_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `sso_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=278 DEFAULT CHARSET=utf8 COMMENT='用户、角色 关联表';

-- ----------------------------
-- Records of sso_user_role
-- ----------------------------
INSERT INTO `sso_user_role` VALUES ('1', '1', '1', '系统初始化', 'admin', '2017-11-24 16:58:20', '2017-11-24 16:58:20');
INSERT INTO `sso_user_role` VALUES ('2', '1', '2', '系统初始化', 'admin', '2017-11-23 20:42:40', '2017-11-23 20:42:40');
INSERT INTO `sso_user_role` VALUES ('5', '2', '2', '账户注册', 'new-economy', '2018-07-07 10:57:41', '2018-07-07 10:57:41');
INSERT INTO `sso_user_role` VALUES ('4', '2', '3', '新经济管理员角色', 'admin', '2018-07-07 11:27:35', '2018-07-07 11:27:35');
INSERT INTO `sso_user_role` VALUES ('3', '2', '1', '初始化', 'admin', '2018-07-11 10:08:30', '2018-07-11 10:08:30');
INSERT INTO `sso_user_role` VALUES ('6', '1', '3', '系统初始化', 'admin', '2017-11-24 16:58:20', '2017-11-24 16:58:20');

-- ----------------------------
-- Table structure for sso_visit_history
-- ----------------------------
DROP TABLE IF EXISTS `sso_visit_history`;
CREATE TABLE `sso_visit_history` (
  `id` int(16) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `user_id` int(16) NOT NULL COMMENT '用户id',
  `last_login_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最近一次登陆日期，格式：YYYY-MM-DD HH:MM:SS',
  `last_login_ip` varchar(15) NOT NULL COMMENT '最近一次登陆的ip地址',
  `description` varchar(64) NOT NULL COMMENT '描述',
  `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='访问历史记录表';

-- ----------------------------
-- Records of sso_visit_history
-- ----------------------------
