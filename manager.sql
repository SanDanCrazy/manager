/*
Navicat MySQL Data Transfer

Source Server         : manager
Source Server Version : 50726
Source Host           : localhost:3306
Source Database       : manager

Target Server Type    : MYSQL
Target Server Version : 50726
File Encoding         : 65001

Date: 2020-01-17 13:41:35
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `password` varchar(30) NOT NULL,
  `realname` varchar(10) NOT NULL,
  `product_id` tinyint(11) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`),
  KEY `product_id` (`product_id`),
  CONSTRAINT `customer_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of customer
-- ----------------------------
INSERT INTO `customer` VALUES ('1', 'caiheng', '123456', '蔡恒', '1');
INSERT INTO `customer` VALUES ('2', 'ziling', '123456', '李子琳', '1');
INSERT INTO `customer` VALUES ('3', 'dashi', '123456', '菜大师', '1');
INSERT INTO `customer` VALUES ('4', 'dakang', '123456', '大康兄', '1');
INSERT INTO `customer` VALUES ('5', 'boss', '123', '管理员', '1');

-- ----------------------------
-- Table structure for customer_message
-- ----------------------------
DROP TABLE IF EXISTS `customer_message`;
CREATE TABLE `customer_message` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `message_id` int(11) NOT NULL,
  `customer_id` smallint(6) NOT NULL,
  `checked` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of customer_message
-- ----------------------------
INSERT INTO `customer_message` VALUES ('1', '4', '5', '1');
INSERT INTO `customer_message` VALUES ('2', '4', '1', '0');
INSERT INTO `customer_message` VALUES ('3', '4', '2', '0');
INSERT INTO `customer_message` VALUES ('4', '4', '2', '0');
INSERT INTO `customer_message` VALUES ('5', '7', '5', '0');
INSERT INTO `customer_message` VALUES ('6', '7', '1', '0');
INSERT INTO `customer_message` VALUES ('7', '7', '2', '0');
INSERT INTO `customer_message` VALUES ('8', '8', '5', '0');
INSERT INTO `customer_message` VALUES ('9', '8', '1', '0');
INSERT INTO `customer_message` VALUES ('10', '8', '2', '0');
INSERT INTO `customer_message` VALUES ('11', '9', '5', '0');
INSERT INTO `customer_message` VALUES ('12', '9', '1', '0');
INSERT INTO `customer_message` VALUES ('13', '9', '2', '0');
INSERT INTO `customer_message` VALUES ('14', '10', '1', '0');
INSERT INTO `customer_message` VALUES ('15', '10', '2', '0');
INSERT INTO `customer_message` VALUES ('16', '10', '3', '0');
INSERT INTO `customer_message` VALUES ('17', '11', '1', '0');
INSERT INTO `customer_message` VALUES ('18', '11', '2', '0');
INSERT INTO `customer_message` VALUES ('19', '11', '3', '0');
INSERT INTO `customer_message` VALUES ('20', '12', '5', '0');
INSERT INTO `customer_message` VALUES ('21', '12', '1', '0');
INSERT INTO `customer_message` VALUES ('22', '12', '2', '0');
INSERT INTO `customer_message` VALUES ('23', '13', '5', '0');
INSERT INTO `customer_message` VALUES ('24', '13', '1', '0');
INSERT INTO `customer_message` VALUES ('25', '13', '2', '0');

-- ----------------------------
-- Table structure for customer_role
-- ----------------------------
DROP TABLE IF EXISTS `customer_role`;
CREATE TABLE `customer_role` (
  `customer_id` int(5) unsigned NOT NULL,
  `role_id` smallint(5) unsigned NOT NULL,
  `product_id` smallint(5) unsigned NOT NULL,
  PRIMARY KEY (`customer_id`,`role_id`,`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of customer_role
-- ----------------------------
INSERT INTO `customer_role` VALUES ('1', '1', '1');
INSERT INTO `customer_role` VALUES ('1', '1', '2');
INSERT INTO `customer_role` VALUES ('1', '2', '1');
INSERT INTO `customer_role` VALUES ('2', '1', '2');
INSERT INTO `customer_role` VALUES ('5', '6', '0');
INSERT INTO `customer_role` VALUES ('5', '6', '1');
INSERT INTO `customer_role` VALUES ('5', '6', '2');

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `message_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `product_id` smallint(6) NOT NULL,
  `story_id` smallint(6) NOT NULL,
  `edition` smallint(6) NOT NULL,
  `content` varchar(255) NOT NULL,
  `created_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`message_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES ('4', '1', '11', '1', '管理员 新建 测试一下添加消息 需求', '2019-12-18 19:10:45');
INSERT INTO `message` VALUES ('5', '1', '12', '1', '管理员 新建 测试一下添加消息 需求', '2019-12-18 19:26:05');
INSERT INTO `message` VALUES ('6', '1', '13', '1', '管理员 新建 测试一下添加消息 需求', '2019-12-18 19:27:51');
INSERT INTO `message` VALUES ('7', '1', '14', '1', '管理员 新建 测试一下添加消息 需求', '2019-12-18 19:29:58');
INSERT INTO `message` VALUES ('8', '1', '15', '1', '管理员 新建 测试一下添加消息 需求', '2019-12-21 11:16:36');
INSERT INTO `message` VALUES ('9', '1', '16', '1', '管理员 新建 测试一下添加消息 需求', '2019-12-21 11:24:26');
INSERT INTO `message` VALUES ('10', '1', '16', '2', '蔡恒 修改了 less changes 需求', '2020-01-03 09:28:17');
INSERT INTO `message` VALUES ('11', '1', '16', '2', '蔡恒 修改了 less changes 需求', '2020-01-03 09:30:48');
INSERT INTO `message` VALUES ('12', '1', '17', '1', '管理员 新建了 测试jsonview 需求', '2020-01-13 11:51:07');
INSERT INTO `message` VALUES ('13', '1', '18', '1', '管理员 新建了 测试jsonview 需求', '2020-01-13 11:51:50');

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` tinyint(4) unsigned NOT NULL,
  `product_name` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES ('0', '未分配');
INSERT INTO `product` VALUES ('1', '设备云');
INSERT INTO `product` VALUES ('2', '健康云');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `role_name` varchar(255) NOT NULL,
  `role_code` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '设计负责人', 'partner');
INSERT INTO `role` VALUES ('2', '开发负责人', 'partner');
INSERT INTO `role` VALUES ('3', '测试负责人', 'partner');
INSERT INTO `role` VALUES ('4', '产品经理', 'partner');
INSERT INTO `role` VALUES ('5', '项目经理', 'manager');
INSERT INTO `role` VALUES ('6', '系统管理员', 'admin');

-- ----------------------------
-- Table structure for story
-- ----------------------------
DROP TABLE IF EXISTS `story`;
CREATE TABLE `story` (
  `story_id` smallint(5) unsigned NOT NULL,
  `product_id` tinyint(3) unsigned NOT NULL,
  `edition` tinyint(3) unsigned NOT NULL,
  `origin` varchar(10) DEFAULT NULL,
  `put_time` date NOT NULL,
  `story_name` char(100) NOT NULL,
  `story_status` tinyint(3) unsigned NOT NULL,
  `description` varchar(500) DEFAULT NULL,
  `conclusion` varchar(500) DEFAULT NULL,
  `design_id` int(11) DEFAULT NULL,
  `dev_id` int(11) DEFAULT NULL,
  `test_id` int(11) DEFAULT NULL,
  `test_time` date DEFAULT NULL,
  `edit_id` int(11) DEFAULT NULL,
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `description_str` varchar(500) DEFAULT NULL,
  `conclusion_str` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`story_id`,`product_id`,`edition`),
  KEY `product_id` (`product_id`),
  KEY `design_name` (`design_id`),
  KEY `dev_name` (`dev_id`),
  KEY `test_name` (`test_id`),
  KEY `edit_id` (`edit_id`),
  KEY `story_id` (`story_id`),
  CONSTRAINT `story_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `story_ibfk_2` FOREIGN KEY (`design_id`) REFERENCES `customer` (`id`) ON DELETE SET NULL ON UPDATE SET NULL,
  CONSTRAINT `story_ibfk_3` FOREIGN KEY (`dev_id`) REFERENCES `customer` (`id`) ON DELETE SET NULL ON UPDATE SET NULL,
  CONSTRAINT `story_ibfk_4` FOREIGN KEY (`test_id`) REFERENCES `customer` (`id`) ON DELETE SET NULL ON UPDATE SET NULL,
  CONSTRAINT `story_ibfk_5` FOREIGN KEY (`edit_id`) REFERENCES `customer` (`id`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of story
-- ----------------------------
INSERT INTO `story` VALUES ('1', '1', '1', null, '2019-03-03', '再来再来！', '1', 'wow', null, '2', '3', '4', '2019-10-10', '1', '2019-12-06 16:37:03', null, null);
INSERT INTO `story` VALUES ('1', '1', '2', null, '2019-03-03', '我傻了！！！', '3', 'wow', null, '2', '3', '4', '2019-10-10', '3', '2019-12-06 19:20:05', null, null);
INSERT INTO `story` VALUES ('1', '1', '3', null, '2019-03-03', '这次来点不一样的！', '3', 'wow', null, '2', '3', '4', '2019-10-10', '3', '2019-12-06 18:02:59', null, null);
INSERT INTO `story` VALUES ('1', '1', '4', null, '2019-03-03', '这次来点不一样的！', '3', 'wow', null, '2', '3', '4', '2019-10-10', '3', '2019-12-06 18:50:07', null, null);
INSERT INTO `story` VALUES ('2', '1', '1', null, '2019-03-03', '这次来点不一样的！', '1', 'wow', null, '2', '3', '4', '2019-10-10', '1', '2019-12-06 16:37:52', null, null);
INSERT INTO `story` VALUES ('4', '1', '1', null, '2019-03-03', '问题在哪儿！', '1', 'wow', null, '2', '3', '4', '2019-10-10', '4', '2019-12-06 19:10:05', null, null);
INSERT INTO `story` VALUES ('5', '1', '1', null, '2019-03-03', '试一下事务！', '1', 'wow', null, '2', '3', '4', '2019-10-10', '4', '2019-12-07 16:01:12', null, null);
INSERT INTO `story` VALUES ('6', '1', '1', null, '2019-03-03', '这次试一下Date类！', '1', 'wow', null, '2', '3', '4', '2019-10-10', '4', '2019-12-07 17:26:51', null, null);
INSERT INTO `story` VALUES ('7', '1', '1', null, '2019-03-03', '测试', '1', 'wow', null, '2', '3', '4', '2019-10-10', '4', '2019-12-07 17:59:10', null, null);
INSERT INTO `story` VALUES ('7', '1', '2', null, '2019-03-03', '被编辑', '0', 'wow', null, '2', '3', '4', '2019-10-10', '3', '2019-12-07 18:01:54', null, null);
INSERT INTO `story` VALUES ('7', '1', '3', null, '2019-03-03', '被编辑', '1', 'you can really dance', null, null, null, null, '2019-10-10', null, '2019-12-07 18:01:54', null, null);
INSERT INTO `story` VALUES ('7', '1', '4', null, '2019-03-03', 'nice', '1', 'you can really dance', null, null, null, null, '2019-10-10', null, '2019-12-07 18:01:54', null, null);
INSERT INTO `story` VALUES ('7', '1', '5', null, '2019-03-03', 'nice', '1', 'you can really dance', null, null, null, null, '2019-10-10', null, '2019-12-07 18:01:54', null, null);
INSERT INTO `story` VALUES ('7', '1', '6', null, '2019-03-03', 'wrapperrequest', '1', 'wrapper gogogo', null, null, null, null, '2019-10-10', null, '2019-12-07 18:01:54', null, null);
INSERT INTO `story` VALUES ('7', '1', '7', null, '2019-03-03', 'controller!', '1', 'wrapper nonono', null, '1', '2', '3', '2019-10-10', '1', '2019-12-07 18:01:54', null, null);
INSERT INTO `story` VALUES ('8', '1', '1', null, '2019-03-03', '加工产品优化', '0', '新建需求', null, '1', '2', '2', '2019-10-10', '1', '2019-12-17 15:19:10', null, null);
INSERT INTO `story` VALUES ('8', '1', '2', null, '2019-03-03', '看这里看这里!', '1', 'wrapper nonono', null, '1', '2', '3', null, '5', '2019-12-07 18:01:54', null, null);
INSERT INTO `story` VALUES ('8', '1', '3', null, '2019-04-04', 'here here', '1', 'wrapper nonono', null, '1', '2', '3', '2019-10-10', '5', '2019-12-30 18:03:28', null, null);
INSERT INTO `story` VALUES ('9', '1', '1', null, '2019-03-03', '测试一下添加消息', '0', '新建消息', null, '1', '2', '2', '2019-10-10', '5', '2019-12-18 18:14:29', null, null);
INSERT INTO `story` VALUES ('10', '1', '1', null, '2019-03-03', '测试一下添加消息', '0', '新建消息', null, '1', '2', '2', '2019-10-10', '5', '2019-12-18 18:15:06', null, null);
INSERT INTO `story` VALUES ('11', '1', '1', null, '2019-03-03', '测试一下添加消息', '0', '新建消息', null, '1', '2', '2', '2019-10-10', '5', '2019-12-18 19:10:45', null, null);
INSERT INTO `story` VALUES ('12', '1', '1', null, '2019-03-03', '测试一下添加消息', '0', '新建消息', null, '1', '2', '2', '2019-10-10', '5', '2019-12-18 19:26:04', null, null);
INSERT INTO `story` VALUES ('13', '1', '1', null, '2019-03-03', '测试一下添加消息', '0', '新建消息', null, '1', '2', '2', '2019-10-10', '5', '2019-12-18 19:27:51', null, null);
INSERT INTO `story` VALUES ('14', '1', '1', null, '2019-03-03', '测试一下添加消息', '0', '新建消息', null, '1', '2', '2', '2019-10-10', '5', '2019-12-18 19:29:58', null, null);
INSERT INTO `story` VALUES ('15', '1', '1', null, '2019-03-03', '测试一下添加消息', '0', '新建消息', null, '1', '2', '2', '2019-10-10', '5', '2019-12-21 11:16:36', null, null);
INSERT INTO `story` VALUES ('16', '1', '1', null, '2019-03-03', '测试一下添加消息', '0', '新建消息', null, null, null, null, '2019-10-10', '5', '2019-12-23 09:33:35', null, null);
INSERT INTO `story` VALUES ('16', '1', '2', null, '2019-04-04', 'less changes', '1', 'rapper nonono', null, '1', '2', '3', '2019-10-10', '1', '2020-01-03 09:30:48', null, null);
INSERT INTO `story` VALUES ('17', '1', '1', null, '2019-03-03', '测试jsonview', '0', '新建消息', 'helloafjhoasdfhoasf', '1', '2', '2', '2019-10-10', '5', '2020-01-13 11:51:07', '新建消息', 'helloafjhoasdfhoasf');
INSERT INTO `story` VALUES ('18', '1', '1', null, '2019-03-03', '测试jsonview', '0', '新建消息', 'helloafjhoasdfhoasf', '1', '2', '2', '2019-10-10', '5', '2020-01-13 11:51:50', '新建消息', 'helloafjhoasdfhoasf');

-- ----------------------------
-- Table structure for story_detail
-- ----------------------------
DROP TABLE IF EXISTS `story_detail`;
CREATE TABLE `story_detail` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `product_id` tinyint(4) NOT NULL,
  `story_id` smallint(6) NOT NULL,
  `edit_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `edit_name` varchar(255) NOT NULL,
  `attribute` varchar(255) NOT NULL,
  `previous` varchar(255) DEFAULT NULL,
  `modified` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of story_detail
-- ----------------------------
INSERT INTO `story_detail` VALUES ('1', '1', '8', '2019-12-07 18:01:54', '管理员', '客户描述', '新建需求', 'wrapper nonono');
INSERT INTO `story_detail` VALUES ('2', '1', '8', '2019-12-07 18:01:54', '管理员', '测试负责人', '李子琳', '菜大师');
INSERT INTO `story_detail` VALUES ('3', '1', '8', '2019-12-07 18:01:54', '管理员', '需求名称', '加工产品优化', '看这里看这里!');
INSERT INTO `story_detail` VALUES ('4', '1', '8', '2019-12-07 18:01:54', '管理员', '需求状态', '新需求', '新需求');
INSERT INTO `story_detail` VALUES ('5', '1', '8', '2019-12-30 18:03:28', '管理员', '提出时间', '2019-03-03', '2019-04-04');
INSERT INTO `story_detail` VALUES ('6', '1', '8', '2019-12-30 18:03:28', '管理员', '需求名称', '看这里看这里!', 'here here');
INSERT INTO `story_detail` VALUES ('8', '1', '16', '2020-01-02 09:59:32', '管理员', '客户描述', '新建消息', 'wrapper nonono');
INSERT INTO `story_detail` VALUES ('9', '1', '16', '2020-01-02 09:59:32', '管理员', '设计负责人', null, '蔡恒');
INSERT INTO `story_detail` VALUES ('10', '1', '16', '2020-01-02 09:59:32', '管理员', '开发负责人', null, '李子琳');
INSERT INTO `story_detail` VALUES ('11', '1', '16', '2020-01-02 09:59:32', '管理员', '测试负责人', null, '菜大师');
INSERT INTO `story_detail` VALUES ('12', '1', '16', '2020-01-02 09:59:32', '管理员', '提出时间', '2019-03-03', '2019-04-04');
INSERT INTO `story_detail` VALUES ('13', '1', '16', '2020-01-02 09:59:32', '管理员', '需求名称', '测试一下添加消息', 'a lot of changes');
INSERT INTO `story_detail` VALUES ('14', '1', '16', '2020-01-02 09:59:32', '管理员', '需求状态', '新需求', '新需求');
INSERT INTO `story_detail` VALUES ('15', '1', '16', '2020-01-03 09:28:17', '蔡恒', '客户描述', '新建消息', 'rapper nonono');
INSERT INTO `story_detail` VALUES ('16', '1', '16', '2020-01-03 09:28:17', '蔡恒', '设计负责人', null, '蔡恒');
INSERT INTO `story_detail` VALUES ('17', '1', '16', '2020-01-03 09:28:17', '蔡恒', '开发负责人', null, '李子琳');
INSERT INTO `story_detail` VALUES ('18', '1', '16', '2020-01-03 09:28:17', '蔡恒', '测试负责人', null, '菜大师');
INSERT INTO `story_detail` VALUES ('19', '1', '16', '2020-01-03 09:28:17', '蔡恒', '提出时间', '2019-03-03', '2019-04-04');
INSERT INTO `story_detail` VALUES ('20', '1', '16', '2020-01-03 09:28:17', '蔡恒', '需求名称', '测试一下添加消息', 'less changes');
INSERT INTO `story_detail` VALUES ('21', '1', '16', '2020-01-03 09:28:17', '蔡恒', '需求状态', '新需求', '处理中');
INSERT INTO `story_detail` VALUES ('22', '1', '16', '2020-01-03 09:30:48', '蔡恒', '客户描述', '新建消息', 'rapper nonono');
INSERT INTO `story_detail` VALUES ('23', '1', '16', '2020-01-03 09:30:48', '蔡恒', '设计负责人', null, '蔡恒');
INSERT INTO `story_detail` VALUES ('24', '1', '16', '2020-01-03 09:30:48', '蔡恒', '开发负责人', null, '李子琳');
INSERT INTO `story_detail` VALUES ('25', '1', '16', '2020-01-03 09:30:48', '蔡恒', '测试负责人', null, '菜大师');
INSERT INTO `story_detail` VALUES ('26', '1', '16', '2020-01-03 09:30:48', '蔡恒', '提出时间', '2019-03-03', '2019-04-04');
INSERT INTO `story_detail` VALUES ('27', '1', '16', '2020-01-03 09:30:48', '蔡恒', '需求名称', '测试一下添加消息', 'less changes');
INSERT INTO `story_detail` VALUES ('28', '1', '16', '2020-01-03 09:30:48', '蔡恒', '需求状态', '新需求', '处理中');

-- ----------------------------
-- Table structure for story_edition
-- ----------------------------
DROP TABLE IF EXISTS `story_edition`;
CREATE TABLE `story_edition` (
  `product_id` tinyint(3) unsigned NOT NULL,
  `edition` tinyint(3) unsigned NOT NULL,
  `story_id` smallint(5) unsigned NOT NULL,
  `edit_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`product_id`,`story_id`,`edition`),
  KEY `edit_id` (`edit_id`),
  CONSTRAINT `story_edition_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `story_edition_ibfk_2` FOREIGN KEY (`edit_id`) REFERENCES `customer` (`id`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of story_edition
-- ----------------------------
INSERT INTO `story_edition` VALUES ('1', '1', '2', '1');
INSERT INTO `story_edition` VALUES ('1', '3', '8', '1');
INSERT INTO `story_edition` VALUES ('1', '2', '1', '3');
INSERT INTO `story_edition` VALUES ('1', '1', '4', '4');
INSERT INTO `story_edition` VALUES ('1', '1', '5', '4');
INSERT INTO `story_edition` VALUES ('1', '1', '6', '4');
INSERT INTO `story_edition` VALUES ('1', '7', '7', '4');
INSERT INTO `story_edition` VALUES ('1', '1', '9', '5');
INSERT INTO `story_edition` VALUES ('1', '1', '10', '5');
INSERT INTO `story_edition` VALUES ('1', '1', '11', '5');
INSERT INTO `story_edition` VALUES ('1', '1', '12', '5');
INSERT INTO `story_edition` VALUES ('1', '1', '13', '5');
INSERT INTO `story_edition` VALUES ('1', '1', '14', '5');
INSERT INTO `story_edition` VALUES ('1', '1', '15', '5');
INSERT INTO `story_edition` VALUES ('1', '2', '16', '5');
INSERT INTO `story_edition` VALUES ('1', '1', '17', '5');
INSERT INTO `story_edition` VALUES ('1', '1', '18', '5');
