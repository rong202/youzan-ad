/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : youzan-ad

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2019-04-26 15:36:00
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for ad_creative
-- ----------------------------
DROP TABLE IF EXISTS `ad_creative`;
CREATE TABLE `ad_creative` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `name` varchar(48) NOT NULL COMMENT '创意名称1',
  `type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '物料类型(图片, 视频)1111',
  `material_type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '物料子类型(图片: bmp, jpg 等等)',
  `height` int(10) NOT NULL DEFAULT '0' COMMENT '高度',
  `width` int(10) NOT NULL DEFAULT '0' COMMENT '宽度',
  `size` bigint(20) NOT NULL DEFAULT '0' COMMENT '物料大小, 单位是 KB',
  `duration` int(10) NOT NULL DEFAULT '0' COMMENT '持续时长, 只有视频才不为 0',
  `audit_status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '审核状态',
  `user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '标记当前记录所属用户',
  `url` varchar(256) NOT NULL COMMENT '物料地址',
  `create_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='创意表';

-- ----------------------------
-- Records of ad_creative
-- ----------------------------
INSERT INTO `ad_creative` VALUES ('10', '第一个创意', '1', '1', '720', '1080', '1024', '0', '1', '15', 'http://images.17.com/tgou/model/20180409/a8l_1523265640.jpg', '2018-11-19 21:31:31', '2018-11-19 21:31:31');

-- ----------------------------
-- Table structure for ad_plan
-- ----------------------------
DROP TABLE IF EXISTS `ad_plan`;
CREATE TABLE `ad_plan` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '标记当前记录所属用户',
  `plan_name` varchar(48) NOT NULL COMMENT '推广计划名称',
  `plan_status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '推广计划状态',
  `start_date` datetime NOT NULL COMMENT '推广计划开始时间；',
  `end_date` datetime NOT NULL COMMENT '推广计划结束时间；',
  `create_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='推广计划表';

-- ----------------------------
-- Records of ad_plan
-- ----------------------------
INSERT INTO `ad_plan` VALUES ('2', '234', '5G时代', '1', '2019-03-11 13:40:53', '2019-07-11 13:41:02', '1970-01-01 00:00:00', '1970-01-01 00:00:00');
INSERT INTO `ad_plan` VALUES ('3', '345', '五一劳动节', '0', '2019-04-16 13:42:32', '2019-04-11 13:42:36', '1970-01-01 00:00:00', '1970-01-01 00:00:00');
INSERT INTO `ad_plan` VALUES ('4', '350', '众筹修复巴黎圣母院', '1', '2019-04-15 00:00:00', '2019-04-20 00:00:00', '2019-04-16 01:00:00', '1970-01-01 00:00:00');
INSERT INTO `ad_plan` VALUES ('10', '15', '推广计划名称', '1', '2018-11-28 00:00:00', '2019-11-20 00:00:00', '2018-11-19 20:42:27', '2018-11-19 20:57:12');
INSERT INTO `ad_plan` VALUES ('11', '0', '22哇塞大大得而', '0', '2019-04-17 12:02:51', '2019-04-17 12:02:55', '1970-01-01 00:00:00', '1970-01-01 00:00:00');
INSERT INTO `ad_plan` VALUES ('12', '17', '五一去哪儿玩', '0', '2019-04-26 00:00:00', '2019-05-10 00:00:00', '2019-04-26 09:46:08', '2019-04-26 10:47:31');
INSERT INTO `ad_plan` VALUES ('13', '17', '五一dao到 zhe这 玩', '1', '2019-04-26 00:00:00', '2019-05-10 00:00:00', '2019-04-26 10:32:08', '2019-04-26 10:37:30');

-- ----------------------------
-- Table structure for ad_unit
-- ----------------------------
DROP TABLE IF EXISTS `ad_unit`;
CREATE TABLE `ad_unit` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `plan_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '关联推广计划 id',
  `unit_name` varchar(48) NOT NULL COMMENT '推广单元名称',
  `unit_status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '推广单元状态',
  `position_type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '广告位类型(开屏, 贴片, 中贴, 暂停帖, 后贴)',
  `budget` bigint(20) NOT NULL COMMENT '预算',
  `create_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='推广单元表';

-- ----------------------------
-- Records of ad_unit
-- ----------------------------
INSERT INTO `ad_unit` VALUES ('10', '10', '第一个推广单元', '1', '2', '10000000', '2018-11-20 11:43:26', '2018-11-20 11:43:26');
INSERT INTO `ad_unit` VALUES ('12', '10', '第二个推广单元', '1', '2', '15000000', '2018-01-01 00:00:00', '2018-01-01 00:00:00');
INSERT INTO `ad_unit` VALUES ('13', '13', 'ba\'wei八维 yi\'ri\'you一日游 ', '1', '2', '150000000000', '2019-04-26 10:54:07', '2019-04-26 10:54:07');

-- ----------------------------
-- Table structure for ad_unit_district
-- ----------------------------
DROP TABLE IF EXISTS `ad_unit_district`;
CREATE TABLE `ad_unit_district` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `unit_id` int(11) NOT NULL COMMENT '推广单元 id',
  `province` varchar(30) NOT NULL COMMENT '省',
  `city` varchar(30) NOT NULL COMMENT '市',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COMMENT='推广单元地域 Feature';

-- ----------------------------
-- Records of ad_unit_district
-- ----------------------------
INSERT INTO `ad_unit_district` VALUES ('10', '10', '安徽省', '淮北市');
INSERT INTO `ad_unit_district` VALUES ('11', '10', '安徽省', '宿州市');
INSERT INTO `ad_unit_district` VALUES ('12', '10', '安徽省', '合肥市');
INSERT INTO `ad_unit_district` VALUES ('14', '10', '辽宁省', '大连市');
INSERT INTO `ad_unit_district` VALUES ('15', '10', '河北省', '邯郸市');

-- ----------------------------
-- Table structure for ad_unit_it
-- ----------------------------
DROP TABLE IF EXISTS `ad_unit_it`;
CREATE TABLE `ad_unit_it` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `unit_id` int(11) NOT NULL COMMENT '推广单元 id',
  `it_tag` varchar(30) NOT NULL COMMENT '兴趣标签',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='推广单元兴趣 Feature';

-- ----------------------------
-- Records of ad_unit_it
-- ----------------------------
INSERT INTO `ad_unit_it` VALUES ('10', '10', '台球');
INSERT INTO `ad_unit_it` VALUES ('11', '10', '游泳');
INSERT INTO `ad_unit_it` VALUES ('12', '10', '乒乓球');

-- ----------------------------
-- Table structure for ad_unit_keyword
-- ----------------------------
DROP TABLE IF EXISTS `ad_unit_keyword`;
CREATE TABLE `ad_unit_keyword` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `unit_id` int(11) NOT NULL COMMENT '推广单元 id',
  `keyword` varchar(30) NOT NULL COMMENT '关键词',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COMMENT='推广单元关键词 Feature';

-- ----------------------------
-- Records of ad_unit_keyword
-- ----------------------------
INSERT INTO `ad_unit_keyword` VALUES ('10', '10', '宝马');
INSERT INTO `ad_unit_keyword` VALUES ('11', '10', '奥迪');
INSERT INTO `ad_unit_keyword` VALUES ('12', '10', '大众');
INSERT INTO `ad_unit_keyword` VALUES ('13', '12', 'jie\'jia\'ri节假日 ');
INSERT INTO `ad_unit_keyword` VALUES ('14', '12', 'du\'che堵车  ');
INSERT INTO `ad_unit_keyword` VALUES ('15', '12', '耐克');

-- ----------------------------
-- Table structure for ad_user
-- ----------------------------
DROP TABLE IF EXISTS `ad_user`;
CREATE TABLE `ad_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `username` varchar(128) NOT NULL DEFAULT '' COMMENT '用户名',
  `token` varchar(256) NOT NULL DEFAULT '' COMMENT '给用户生成的 token',
  `user_status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '用户状态',
  `create_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COMMENT='用户信息表';

-- ----------------------------
-- Records of ad_user
-- ----------------------------
INSERT INTO `ad_user` VALUES ('10', 'bawei', '031B9AD27EE2373400921BFAF49827F8', '1', '2019-03-27 16:50:25', '2019-03-27 16:50:25');
INSERT INTO `ad_user` VALUES ('11', 'yujisuan', 'D7532FF0CDE0BC333393DC25C66A352D', '1', '2019-03-27 16:53:14', '2019-03-27 16:53:14');
INSERT INTO `ad_user` VALUES ('12', 'yunjisuan', '14A402069C507FBE48CD1988E9179F45', '1', '2019-03-29 10:30:39', '2019-03-29 10:30:39');
INSERT INTO `ad_user` VALUES ('13', 'yunjisuan11', '1B6FA187F029795ADF0954D4B1C47F78', '1', '2019-03-29 11:40:38', '2019-03-29 11:40:38');
INSERT INTO `ad_user` VALUES ('14', 'chuanmei', '229FFFF766DF286F1B06968E88C13920', '1', '2019-03-29 16:57:08', '2019-03-29 16:57:08');
INSERT INTO `ad_user` VALUES ('15', '2dwsdsd', 'dsdsds323232', '0', '1970-01-01 00:00:00', '1970-01-01 00:00:00');
INSERT INTO `ad_user` VALUES ('16', '3232', '32323232dfgfggfgf', '0', '1970-01-01 00:00:00', '1970-01-01 00:00:00');
INSERT INTO `ad_user` VALUES ('17', 'alibaba', 'FFCCC52B393D6A9B366EB7FE3DFF9AB5', '1', '2019-04-26 09:24:13', '2019-04-26 09:24:13');
INSERT INTO `ad_user` VALUES ('18', 'meituan', 'F0A1DA7F43E936102090E92630933A1B', '1', '2019-04-26 10:28:51', '2019-04-26 10:28:51');

-- ----------------------------
-- Table structure for creative_unit
-- ----------------------------
DROP TABLE IF EXISTS `creative_unit`;
CREATE TABLE `creative_unit` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `creative_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '创意 id',
  `unit_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '推广单元 id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='创意和推广单元关联表';

-- ----------------------------
-- Records of creative_unit
-- ----------------------------
INSERT INTO `creative_unit` VALUES ('10', '10', '10');
