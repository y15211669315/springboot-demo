/*
Navicat MySQL Data Transfer

Source Server         : localhostDB
Source Server Version : 50716
Source Host           : localhost:3306
Source Database       : classroom_db

Target Server Type    : MYSQL
Target Server Version : 50716
File Encoding         : 65001

Date: 2018-09-12 15:18:36
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_evaluate
-- ----------------------------
DROP TABLE IF EXISTS `t_evaluate`;
CREATE TABLE `t_evaluate` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `user_id` int(11) DEFAULT NULL COMMENT '评价人',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '评价时间',
  `content` varchar(500) DEFAULT NULL COMMENT '评价内容',
  `status` int(1) DEFAULT '0' COMMENT '状态：0：正常 1：已删除',
  `cid` int(11) DEFAULT NULL COMMENT '课堂id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=145 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_evaluate
-- ----------------------------
INSERT INTO `t_evaluate` VALUES ('97', '1', '2018-08-16 14:12:56', '不错不错', '0', null);
INSERT INTO `t_evaluate` VALUES ('98', '2', '2018-08-24 17:32:37', '很好很好', '0', null);
INSERT INTO `t_evaluate` VALUES ('99', '3', '2018-08-16 14:12:56', 'good', '0', null);
INSERT INTO `t_evaluate` VALUES ('100', '4', '2018-08-11 14:12:56', '募款', '0', null);
INSERT INTO `t_evaluate` VALUES ('101', '5', '2018-08-11 14:12:56', '放大', '0', null);
INSERT INTO `t_evaluate` VALUES ('102', '6', '2018-08-24 17:32:37', '的的', '0', null);
INSERT INTO `t_evaluate` VALUES ('103', '7', '2018-08-24 14:12:56', '大发大水', '0', null);
INSERT INTO `t_evaluate` VALUES ('104', '8', '2018-08-24 14:12:56', '公司的', '0', null);
INSERT INTO `t_evaluate` VALUES ('105', '9', '2018-08-16 14:12:56', '削皮', '0', null);
INSERT INTO `t_evaluate` VALUES ('106', '10', '2018-08-24 17:32:37', '调皮', '0', null);
INSERT INTO `t_evaluate` VALUES ('107', '11', '2018-08-16 14:12:56', '搞笑', '0', null);
INSERT INTO `t_evaluate` VALUES ('108', '12', '2018-08-11 14:12:56', 'what', '0', null);
INSERT INTO `t_evaluate` VALUES ('109', '13', '2018-08-11 14:12:56', '嘿嘿嘿', '0', null);
INSERT INTO `t_evaluate` VALUES ('110', '14', '2018-08-11 14:12:56', '面膜么', '0', null);
INSERT INTO `t_evaluate` VALUES ('111', '15', '2018-08-11 14:12:56', '么么么', '0', null);
INSERT INTO `t_evaluate` VALUES ('112', '16', '2018-08-24 14:12:56', '笑傲', '0', null);
INSERT INTO `t_evaluate` VALUES ('113', '17', '2018-08-21 14:12:56', '骄傲', '0', null);
INSERT INTO `t_evaluate` VALUES ('114', '18', '2018-08-24 17:32:37', '苗疆客', '0', null);
INSERT INTO `t_evaluate` VALUES ('115', '19', '2018-08-24 14:12:56', '角度考虑的', '0', null);
INSERT INTO `t_evaluate` VALUES ('116', '20', '2018-08-24 14:12:56', '大的', '0', null);
INSERT INTO `t_evaluate` VALUES ('117', '21', '2018-08-24 14:12:56', '发大水f', '0', null);
INSERT INTO `t_evaluate` VALUES ('118', '22', '2018-08-24 17:32:37', '给收到 ', '0', null);
INSERT INTO `t_evaluate` VALUES ('119', '23', '2018-08-11 14:12:56', '的撒', '0', null);
INSERT INTO `t_evaluate` VALUES ('120', '24', '2018-08-24 14:12:56', '的', '0', null);
INSERT INTO `t_evaluate` VALUES ('121', '25', '2018-08-24 14:12:56', '阿三地方', '0', null);
INSERT INTO `t_evaluate` VALUES ('122', '26', '2018-08-24 17:32:37', ' 大幅', '0', null);
INSERT INTO `t_evaluate` VALUES ('123', '27', '2018-08-24 14:12:56', '是方式', '0', null);
INSERT INTO `t_evaluate` VALUES ('124', '28', '2018-08-24 14:12:56', ' 权威人物', '0', null);
INSERT INTO `t_evaluate` VALUES ('125', '29', '2018-08-24 14:12:56', '是啊', '0', null);
INSERT INTO `t_evaluate` VALUES ('126', '30', '2018-08-24 17:32:37', '阿瑟东大幅', '0', null);
INSERT INTO `t_evaluate` VALUES ('127', '31', '2018-08-24 14:12:56', '的f\'d', '0', null);
INSERT INTO `t_evaluate` VALUES ('128', '32', '2018-08-24 14:12:56', ' 啊发', '0', null);
INSERT INTO `t_evaluate` VALUES ('129', '33', '2018-08-24 14:12:56', ' 大幅 ', '0', null);
INSERT INTO `t_evaluate` VALUES ('130', '34', '2018-08-24 17:32:37', '朝内大街看到', '0', null);
INSERT INTO `t_evaluate` VALUES ('131', '35', '2018-08-08 14:12:56', ' 会发抖', '0', null);
INSERT INTO `t_evaluate` VALUES ('132', '36', '2018-08-24 14:12:56', '预热我', '0', null);
INSERT INTO `t_evaluate` VALUES ('133', '37', '2018-08-24 14:12:56', '发大的', '0', null);
INSERT INTO `t_evaluate` VALUES ('134', '38', '2018-08-24 17:32:37', '噶士大夫撒', '0', null);
INSERT INTO `t_evaluate` VALUES ('135', '39', '2018-08-16 14:12:56', '大幅 ', '0', null);
INSERT INTO `t_evaluate` VALUES ('136', '40', '2018-08-16 14:12:56', '想买啊', '0', null);
INSERT INTO `t_evaluate` VALUES ('137', '41', '2018-08-16 14:12:56', '大使馆的', '0', null);
INSERT INTO `t_evaluate` VALUES ('138', '42', '2018-08-16 14:12:56', '西奥已经扩大到', '0', null);
INSERT INTO `t_evaluate` VALUES ('139', '43', '2018-08-16 14:12:56', '发', '0', null);
INSERT INTO `t_evaluate` VALUES ('140', '44', '2018-08-24 14:12:56', '回头看咯', '0', null);
INSERT INTO `t_evaluate` VALUES ('141', '45', '2018-08-24 14:12:56', '给哈', '0', null);
INSERT INTO `t_evaluate` VALUES ('142', '46', '2018-08-24 17:32:37', '了解皮u', '0', null);
INSERT INTO `t_evaluate` VALUES ('143', '47', '2018-08-24 14:12:56', '看完杰克麦克卢', '0', null);
INSERT INTO `t_evaluate` VALUES ('144', '48', '2018-08-24 14:12:56', '爱上天涯', '0', null);
