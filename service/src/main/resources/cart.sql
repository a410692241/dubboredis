/*
 Navicat Premium Data Transfer

 Source Server         : test
 Source Server Type    : MySQL
 Source Server Version : 50724
 Source Host           : localhost:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 50724
 File Encoding         : 65001

 Date: 30/11/2018 20:41:26
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for cart
-- ----------------------------
DROP TABLE IF EXISTS `cart`;
CREATE TABLE `cart`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NULL DEFAULT NULL,
  `goodId` int(11) NULL DEFAULT NULL,
  `count` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 54 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for couse_score
-- ----------------------------
DROP TABLE IF EXISTS `couse_score`;
CREATE TABLE `couse_score`  (
  `couse_score_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '[批次科目分数]',
  `score` double NULL DEFAULT NULL COMMENT '[科目分值]',
  `batch` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '[招考批次]',
  `required` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '[科目要求]',
  `rule` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '[创建时间]',
  `course_id` int(11) NULL DEFAULT NULL COMMENT '[专业科目ID]',
  `show_video` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '[演示视频]演示视频]地址',
  `level` smallint(6) NULL DEFAULT NULL COMMENT '[分辨率]0:180x320;1:270x480;2:360x640;3:540x960;4:720x1280;5:1080x1920',
  `type` smallint(6) NULL DEFAULT NULL COMMENT '[试题类型]10：指定稿件 20：语音 30：形象 40:手型',
  `code_rate` smallint(6) NULL DEFAULT NULL COMMENT '[音频码率] 1:128kbps 2:192kbps ',
  `voice_url` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '[音频地址]多个用逗号隔开',
  `examTitle` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '[考题]',
  `second` int(11) NULL DEFAULT NULL COMMENT '[视频时长]',
  `imagePicture` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '[形象图片]多张用逗号分隔',
  `contents` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '[音频对应文字]，多个用逗号隔开',
  `times` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '[音频播放时间节点]，多个用逗号隔开',
  `teaPicture` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '[茶艺手型图片]，多个用逗号隔开',
  `batch_id` int(8) NULL DEFAULT NULL COMMENT '[批次id]',
  `scoreType` smallint(6) NULL DEFAULT NULL COMMENT '[评分类型]1:取平均分，2去掉最大最小取平均分',
  PRIMARY KEY (`couse_score_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 109 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee`  (
  `employee_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '[监管员id]监管员id',
  `area_id` int(11) NULL DEFAULT NULL COMMENT '[网格化Id]负责的区域',
  `department_id` int(11) NULL DEFAULT NULL COMMENT '[部门Id]部门id',
  `employee_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '[监管员姓名]监管员姓名',
  `sex` smallint(6) NULL DEFAULT NULL COMMENT '[性别]1：男 2：女',
  `position_code` smallint(6) NULL DEFAULT NULL COMMENT '[职位编码]职位编码',
  `mobile` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '[手机号]手机号',
  `password` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '[密码]密码',
  `entry_time` date NULL DEFAULT NULL COMMENT '[入职时间]入职时间',
  `create_time` date NULL DEFAULT NULL COMMENT '[创建时间]创建时间',
  `prison_code` smallint(6) NULL DEFAULT NULL COMMENT '[监管局code]',
  `mobile_token` varchar(72) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '[手机token]手机tokeny',
  `head_image` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '[头像]',
  `is_leader` smallint(6) NULL DEFAULT NULL COMMENT '[是否是领导]',
  `job_number` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '[执法人员编号]',
  `statu` smallint(6) NULL DEFAULT 1 COMMENT '[账号状态]1：启用 2：禁用',
  `main_body_forms_id` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '[主体业态id,多个,逗号隔开]',
  PRIMARY KEY (`employee_id`) USING BTREE,
  UNIQUE INDEX `uk_mobile`(`mobile`) USING BTREE,
  UNIQUE INDEX `UK_job_number`(`job_number`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '监管员' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for good
-- ----------------------------
DROP TABLE IF EXISTS `good`;
CREATE TABLE `good`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `goodId` int(11) NULL DEFAULT NULL,
  `userId` int(11) NULL DEFAULT NULL,
  `count` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `age` int(11) NULL DEFAULT NULL,
  `username` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
