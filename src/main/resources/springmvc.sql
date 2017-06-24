/*
Navicat MySQL Data Transfer

Source Server         : MySQL
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : springmvc

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2015-10-23 19:58:36
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `sys_menu`
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `menu_id` varchar(10) NOT NULL,
  `menu_name` varchar(30) NOT NULL,
  `menu_url` varchar(100) DEFAULT NULL,
  `menu_parent_id` varchar(10) NOT NULL,
  `menu_weight` int(11) DEFAULT '0' COMMENT '菜单排序使用',
  `menu_desc` varchar(50) DEFAULT NULL,
  `menu_flag` int(11) DEFAULT '1' COMMENT '1：启用  2：停用',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('0', 'root', '/', '-1', '0', null, '1');
INSERT INTO `sys_menu` VALUES ('1', 'JS', '/js', '0', '0', null, '1');
INSERT INTO `sys_menu` VALUES ('100', '权限管理', '/manage', '0', '0', null, '1');
INSERT INTO `sys_menu` VALUES ('10001', '用户管理', '/sys/user/userindex', '100', '0', null, '1');
INSERT INTO `sys_menu` VALUES ('10002', '角色管理', '/sys/role/roleindex', '100', '0', null, '1');
INSERT INTO `sys_menu` VALUES ('10003', '日志管理', '/log_manage', '100', '0', null, '1');
INSERT INTO `sys_menu` VALUES ('101', 'jquery', '/js/jquery', '1', '0', null, '1');
INSERT INTO `sys_menu` VALUES ('2', 'learn', '/learn', '0', '0', null, '1');
INSERT INTO `sys_menu` VALUES ('201', 'learn01', '/learn01', '2', '0', null, '1');
INSERT INTO `sys_menu` VALUES ('202', 'learn02', null, '2', '0', null, '1');
INSERT INTO `sys_menu` VALUES ('20201', 'learn0201', '/learn0201', '202', '0', null, '1');

-- ----------------------------
-- Table structure for `sys_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` varchar(50) NOT NULL,
  `role_name` varchar(20) NOT NULL,
  `role_desc` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('0', '游客', null);
INSERT INTO `sys_role` VALUES ('1', '管理员', null);
INSERT INTO `sys_role` VALUES ('298539e1-eef5-47d8-b2fd-483c4f0ed2e6', 'kjjkgfj', 'jk');
INSERT INTO `sys_role` VALUES ('a41f306a-7a5f-4e1e-a92d-652ddb474dea', '66', '66');
INSERT INTO `sys_role` VALUES ('a4bf2428-e13c-49f4-ae7c-90509ee6386b', 'tmp', 'kj');
INSERT INTO `sys_role` VALUES ('dd291571-73f1-4037-8ff0-687e0e75a416', '999', '9999');

-- ----------------------------
-- Table structure for `sys_role_menu`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `role_id` varchar(50) NOT NULL,
  `menu_id` varchar(50) NOT NULL,
  PRIMARY KEY (`role_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('1', '1');
INSERT INTO `sys_role_menu` VALUES ('1', '10001');
INSERT INTO `sys_role_menu` VALUES ('1', '10002');
INSERT INTO `sys_role_menu` VALUES ('1', '101');
INSERT INTO `sys_role_menu` VALUES ('1', '2');
INSERT INTO `sys_role_menu` VALUES ('1', '202');
INSERT INTO `sys_role_menu` VALUES ('1', '20201');

-- ----------------------------
-- Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` varchar(50) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(50) NOT NULL,
  `phone` varchar(15) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `creatDate` date DEFAULT NULL,
  `flag` int(2) DEFAULT '1' COMMENT '1：正常状态\r\n2：停用状态',
  `descmsg` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('-1', 'admin', '21232F297A57A5A743894A0E4A801FC3', null, null, '2015-01-10', '1', null);
INSERT INTO `sys_user` VALUES ('046b25a7-174e-47e2-b51f-32a267a580ca', '1234', '81DC9BDB52D04DC20036DBD8313ED055', '', '', '2015-10-23', '0', '');
INSERT INTO `sys_user` VALUES ('0a6ffc7c-6eab-45ad-9b28-8f7b7225307e', 'oo', 'E47CA7A09CF6781E29634502345930A7', '', '', '2015-10-23', '0', '');
INSERT INTO `sys_user` VALUES ('0b93c2a8-93a6-4db7-a9b2-5b454a9f10f8', '11', '6512BD43D9CAA6E02C990B0A82652DCA', '11', '11', '2015-10-23', '0', '11');
INSERT INTO `sys_user` VALUES ('0be21ec0-6d32-4cfa-9cb5-78571aa58d32', '123', '202CB962AC59075B964B07152D234B70', '123', '123', '2015-10-22', '0', '123');
INSERT INTO `sys_user` VALUES ('1', 'xuqiang', '900BC885D7553375AEC470198A9514F3', '15210688766', '123@163.com', '2015-10-10', '1', null);
INSERT INTO `sys_user` VALUES ('17168873-3b43-4286-864b-fda23db123b8', '88', '2A38A4A9316C49E5A833517C45D31070', '88', '88', '2015-10-23', '0', '88');
INSERT INTO `sys_user` VALUES ('1dc0954d-716d-4175-be14-681273e7d564', '888', '0A113EF6B61820DAA5611C870ED8D5EE', '', '', '2015-10-23', '0', '');
INSERT INTO `sys_user` VALUES ('2', 'test1', '900BC885D7553375AEC470198A9514F3', '1234567', null, '2015-10-01', '1', null);
INSERT INTO `sys_user` VALUES ('254d114c-b3b9-4f5c-b54d-36454a88bb64', 'qq', '099B3B060154898840F0EBDFB46EC78F', '', '', '2015-10-23', '0', '');
INSERT INTO `sys_user` VALUES ('3', 'test2', '123435', null, null, '2015-10-01', '1', null);
INSERT INTO `sys_user` VALUES ('4', 'sgdfg', 'testset', null, null, '2015-10-01', '1', null);
INSERT INTO `sys_user` VALUES ('5f5d6e43-d3a1-4921-8b99-087527da960c', 'yy', '2FB1C5CF58867B5BBC9A1B145A86F3A0', '', '', '2015-10-23', '0', '');
INSERT INTO `sys_user` VALUES ('68b5d0ee-1262-410d-acc0-3f54bcdfde2e', 'kk', 'DC468C70FB574EBD07287B38D0D0676D', '', 'kk', '2015-10-23', '0', '');
INSERT INTO `sys_user` VALUES ('7129c433-118c-452e-97c6-589a7b98009d', 'rrrr', '514F1B439F404F86F77090FA9EDC96CE', '', '', '2015-10-23', '0', '');
INSERT INTO `sys_user` VALUES ('8', 'dfgfgg', 'gh6sha', null, null, '2015-10-01', '1', null);
INSERT INTO `sys_user` VALUES ('8c06bbcd-22ff-403e-ba6a-757d972a76c8', 'www', '4EAE35F1B35977A00EBD8086C259D4C9', '', '', '2015-10-23', '0', '');
INSERT INTO `sys_user` VALUES ('8f5194be-3d3f-4c4f-8757-a8aa01c87530', '56', '9F61408E3AFB633E50CDF1B20DE6F466', '', '', '2015-10-23', '0', '');
INSERT INTO `sys_user` VALUES ('cd6f6d8d-cc0a-4f16-8128-352e3f17d504', 'zzz', 'F3ABB86BD34CF4D52698F14C0DA1DC60', '', '', '2015-10-23', '0', '');
INSERT INTO `sys_user` VALUES ('fb99a7d6-1183-438e-aacc-5e46f11fdce4', 'pp', 'C483F6CE851C9ECD9FB835FF7551737C', '', '', '2015-10-23', '0', '');

-- ----------------------------
-- Table structure for `sys_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `user_id` varchar(40) NOT NULL,
  `role_id` varchar(40) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('-1', '1');
INSERT INTO `sys_user_role` VALUES ('0b93c2a8-93a6-4db7-a9b2-5b454a9f10f8', '1');
INSERT INTO `sys_user_role` VALUES ('0b93c2a8-93a6-4db7-a9b2-5b454a9f10f8', 'a4bf2428-e13c-49f4-ae7c-90509ee6386b');
INSERT INTO `sys_user_role` VALUES ('0be21ec0-6d32-4cfa-9cb5-78571aa58d32', 'a41f306a-7a5f-4e1e-a92d-652ddb474dea');
INSERT INTO `sys_user_role` VALUES ('0be21ec0-6d32-4cfa-9cb5-78571aa58d32', 'a4bf2428-e13c-49f4-ae7c-90509ee6386b');
