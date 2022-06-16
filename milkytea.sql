/*
 Navicat Premium Data Transfer

 Source Server         : LocaMysql
 Source Server Type    : MySQL
 Source Server Version : 50650
 Source Host           : localhost:3306
 Source Schema         : milkytea

 Target Server Type    : MySQL
 Target Server Version : 50650
 File Encoding         : 65001

 Date: 24/05/2022 11:23:16
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for attributea
-- ----------------------------
DROP TABLE IF EXISTS `attributea`;
CREATE TABLE `attributea`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `goosId` int(10) NULL DEFAULT NULL,
  `add` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of attributea
-- ----------------------------

-- ----------------------------
-- Table structure for attributeb
-- ----------------------------
DROP TABLE IF EXISTS `attributeb`;
CREATE TABLE `attributeb`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of attributeb
-- ----------------------------
INSERT INTO `attributeb` VALUES (1, '冰');
INSERT INTO `attributeb` VALUES (2, '常温');
INSERT INTO `attributeb` VALUES (3, '热');

-- ----------------------------
-- Table structure for attributec
-- ----------------------------
DROP TABLE IF EXISTS `attributec`;
CREATE TABLE `attributec`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of attributec
-- ----------------------------
INSERT INTO `attributec` VALUES (1, '0糖');
INSERT INTO `attributec` VALUES (2, '半糖');
INSERT INTO `attributec` VALUES (3, '全糖');

-- ----------------------------
-- Table structure for goodsinfo
-- ----------------------------
DROP TABLE IF EXISTS `goodsinfo`;
CREATE TABLE `goodsinfo`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `goodsName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `goodsPrice` int(10) NULL DEFAULT NULL,
  `describe` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `attributebId` int(10) NULL DEFAULT NULL,
  `attributecId` int(10) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of goodsinfo
-- ----------------------------

-- ----------------------------
-- Table structure for shopinfo
-- ----------------------------
DROP TABLE IF EXISTS `shopinfo`;
CREATE TABLE `shopinfo`  (
  `id` int(11) NOT NULL,
  `shopName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of shopinfo
-- ----------------------------
INSERT INTO `shopinfo` VALUES (1, '蜜雪冰城', '合川区');

-- ----------------------------
-- Table structure for shopswipe
-- ----------------------------
DROP TABLE IF EXISTS `shopswipe`;
CREATE TABLE `shopswipe`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `shopId` int(11) NULL DEFAULT NULL,
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of shopswipe
-- ----------------------------
INSERT INTO `shopswipe` VALUES (1, 1, 'http://facetocode.xyz:9091/title?picName=pic3');
INSERT INTO `shopswipe` VALUES (2, 1, 'http://facetocode.xyz:9091/title?picName=pic2');
INSERT INTO `shopswipe` VALUES (3, 1, 'http://facetocode.xyz:9091/title?picName=pic1');

-- ----------------------------
-- Table structure for swipe
-- ----------------------------
DROP TABLE IF EXISTS `swipe`;
CREATE TABLE `swipe`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of swipe
-- ----------------------------
INSERT INTO `swipe` VALUES (1, 'http://facetocode.xyz:9090/swipe?picName=pic1');
INSERT INTO `swipe` VALUES (2, 'http://facetocode.xyz:9090/swipe?picName=pic2');
INSERT INTO `swipe` VALUES (3, 'http://facetocode.xyz:9090/swipe?picName=pic3');

-- ----------------------------
-- Table structure for userinfo
-- ----------------------------
DROP TABLE IF EXISTS `userinfo`;
CREATE TABLE `userinfo`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `phone` varbinary(11) NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `birthday` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `noun` int(10) NULL DEFAULT NULL,
  `createTime` datetime NULL DEFAULT NULL,
  `updateTime` datetime NULL DEFAULT NULL,
  `deleteTime` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of userinfo
-- ----------------------------
INSERT INTO `userinfo` VALUES (5, 0x3135323233363530323135, '123456', '女', '', 0, '2022-05-23 18:26:31', '2022-05-23 20:31:43', NULL);

SET FOREIGN_KEY_CHECKS = 1;
