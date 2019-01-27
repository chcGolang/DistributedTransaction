/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.50.115
 Source Server Type    : MySQL
 Source Server Version : 50724
 Source Host           : 192.168.50.115:3306
 Source Schema         : dist_tran_course

 Target Server Type    : MySQL
 Target Server Version : 50724
 File Encoding         : 65001

 Date: 27/01/2019 12:56:47
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for T_USER
-- ----------------------------
DROP TABLE IF EXISTS `T_USER`;
CREATE TABLE `T_USER`  (
  `amount` int(255) NULL DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of T_USER
-- ----------------------------
INSERT INTO `T_USER` VALUES (100, 'BatMan');
INSERT INTO `T_USER` VALUES (200, 'SuperMan');

SET FOREIGN_KEY_CHECKS = 1;
