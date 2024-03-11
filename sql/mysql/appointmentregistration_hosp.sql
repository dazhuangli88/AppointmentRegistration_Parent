/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80030
 Source Host           : localhost:3306
 Source Schema         : appointmentregistration_hosp

 Target Server Type    : MySQL
 Target Server Version : 80030
 File Encoding         : 65001

 Date: 11/03/2024 20:20:00
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for hospital_set
-- ----------------------------
DROP TABLE IF EXISTS `hospital_set`;
CREATE TABLE `hospital_set`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
  `hosname` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '医院名称',
  `hoscode` varchar(30) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '医院编号',
  `api_url` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT 'api基础路径',
  `sign_key` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '签名秘钥',
  `contacts_name` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '联系人',
  `contacts_phone` varchar(12) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '联系人手机',
  `status` tinyint NOT NULL DEFAULT 0 COMMENT '状态',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint NOT NULL DEFAULT 0 COMMENT '逻辑删除(1:已删除，0:未删除)',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_hoscode`(`hoscode` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '医院设置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of hospital_set
-- ----------------------------
INSERT INTO `hospital_set` VALUES (1, '中山大学附属第五医院', '1001', 'http://localhost:8001', '', NULL, '0756-2528888', 0, '2024-01-12 19:35:59', '2024-03-07 23:16:11', 0);
INSERT INTO `hospital_set` VALUES (2, '遵义医学院第五附属珠海医院', '1002', 'http://localhost:8001', NULL, NULL, '0756-6275013', 0, '2024-01-14 15:08:25', '2024-03-09 03:08:09', 0);
INSERT INTO `hospital_set` VALUES (3, '广东省中医院珠海医院', '1003', 'http://localhost:8001', NULL, NULL, '0756-3325027', 0, '2024-01-16 22:37:00', '2024-03-09 03:08:35', 0);
INSERT INTO `hospital_set` VALUES (16, '珠海市人民医院', '1004', 'http://localhost:8001', 'bb1b9c318a9fc2f76e50eeeb8d881771', '周末', '13268218493', 1, '2024-03-09 03:11:25', '2024-03-10 19:08:37', 0);
INSERT INTO `hospital_set` VALUES (17, '高新区人民医院', '1007', 'http://localhost:8001', 'b2eff8c1f0a378e9df36e62d965f9afd', '周礼', '13798997045', 1, '2024-03-09 03:13:02', '2024-03-09 03:13:02', 0);

SET FOREIGN_KEY_CHECKS = 1;
