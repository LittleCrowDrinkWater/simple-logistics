/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50719
 Source Host           : localhost:3306
 Source Schema         : mogistic

 Target Server Type    : MySQL
 Target Server Version : 50719
 File Encoding         : 65001

 Date: 07/03/2022 19:00:28
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for car
-- ----------------------------
DROP TABLE IF EXISTS `car`;
CREATE TABLE `car`  (
  `id` bigint(20) NOT NULL,
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT 'user_id',
  `warehouse_id` int(11) NULL DEFAULT NULL COMMENT '车辆所属仓库id，在到下一个仓库前属于上一个仓库',
  `state` int(11) NULL DEFAULT NULL COMMENT '车辆状态',
  `drive_licence` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '车牌\r\n',
  `id_card` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '驾照',
  `insurance_card` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '保险',
  `run_licence` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '车辆行驶号',
  `allow_carry_volume` double NULL DEFAULT NULL,
  `allow_carry_weight` double NULL DEFAULT NULL,
  `car_length` double NULL DEFAULT NULL,
  `car_width` double NULL DEFAULT NULL,
  `car_height` double NULL DEFAULT NULL,
  `car_frame_no` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `car_no` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `car_type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `remark` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  INDEX `warehouse_id`(`warehouse_id`) USING BTREE,
  CONSTRAINT `car_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `car_ibfk_2` FOREIGN KEY (`warehouse_id`) REFERENCES `warehouse` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of car
-- ----------------------------

-- ----------------------------
-- Table structure for goods_info
-- ----------------------------
DROP TABLE IF EXISTS `goods_info`;
CREATE TABLE `goods_info`  (
  `id` bigint(20) NOT NULL,
  `car_id` bigint(20) NULL DEFAULT NULL COMMENT 'carId',
  `operate_user_id` bigint(20) NULL DEFAULT NULL COMMENT '操作员uid',
  `receive_user_id` bigint(20) NULL DEFAULT NULL COMMENT '收货人id',
  `send_goods_user_id` bigint(20) NULL DEFAULT NULL COMMENT '寄货人id',
  `goods_bill_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '货单',
  `accept_station` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '接收货物地址',
  `carriage` double NULL DEFAULT NULL COMMENT '重量',
  `fact_deal_date` bigint(20) NULL DEFAULT NULL COMMENT '实际交易日期',
  `predelivery_date` bigint(20) NULL DEFAULT NULL COMMENT '预计送达日期',
  `send_goods_date` bigint(20) NULL DEFAULT NULL COMMENT '实际收货时间',
  `fetch_goods_mode` int(50) NULL DEFAULT NULL COMMENT '取件方式',
  `insurance` double NULL DEFAULT NULL COMMENT '保险',
  `transfer_status` int(11) NULL DEFAULT NULL COMMENT '中转状态',
  `status` int(11) NULL DEFAULT NULL COMMENT '订单状态',
  `gmt_create` bigint(20) NULL DEFAULT NULL,
  `gmt_modified` bigint(20) NULL DEFAULT NULL,
  `remark` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `operate_user_id`(`operate_user_id`) USING BTREE,
  INDEX `receive_user_id`(`receive_user_id`) USING BTREE,
  INDEX `send_goods_user_id`(`send_goods_user_id`) USING BTREE,
  INDEX `car_id`(`car_id`) USING BTREE,
  CONSTRAINT `goods_info_ibfk_1` FOREIGN KEY (`operate_user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `goods_info_ibfk_2` FOREIGN KEY (`receive_user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `goods_info_ibfk_3` FOREIGN KEY (`send_goods_user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `goods_info_ibfk_4` FOREIGN KEY (`car_id`) REFERENCES `car` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of goods_info
-- ----------------------------

-- ----------------------------
-- Table structure for location
-- ----------------------------
DROP TABLE IF EXISTS `location`;
CREATE TABLE `location`  (
  `id` int(11) NOT NULL,
  `province` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `city` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `detail_address` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of location
-- ----------------------------

-- ----------------------------
-- Table structure for pay
-- ----------------------------
DROP TABLE IF EXISTS `pay`;
CREATE TABLE `pay`  (
  `id` bigint(20) NOT NULL,
  `goods_info_id` bigint(20) NULL DEFAULT NULL,
  `total_payment` int(11) NULL DEFAULT NULL,
  `pay_mode` int(11) NULL DEFAULT NULL,
  `transfer_fee` int(11) NULL DEFAULT NULL,
  `carry_goods_fee` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `goods_info_id`(`goods_info_id`) USING BTREE,
  CONSTRAINT `pay_ibfk_1` FOREIGN KEY (`goods_info_id`) REFERENCES `goods_info` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pay
-- ----------------------------

-- ----------------------------
-- Table structure for transfer_info
-- ----------------------------
DROP TABLE IF EXISTS `transfer_info`;
CREATE TABLE `transfer_info`  (
  `id` bigint(20) NOT NULL,
  `goods_info_id` bigint(20) NULL DEFAULT NULL COMMENT '货物运单id',
  `car_id` bigint(20) NULL DEFAULT NULL COMMENT 'carId',
  `goods_bill_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '货单',
  `accept_station` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '接收货物地址',
  `carriage` double NULL DEFAULT NULL COMMENT '重量',
  `operate_user_id` bigint(20) NULL DEFAULT NULL COMMENT '操作员uid',
  `fact_deal_date` bigint(20) NULL DEFAULT NULL COMMENT '实际交易日期',
  `predelivery_date` bigint(20) NULL DEFAULT NULL COMMENT '预计送达日期',
  `send_goods_date` bigint(20) NULL DEFAULT NULL COMMENT '实际收货时间',
  `fetch_goods_mode` int(50) NULL DEFAULT NULL COMMENT '取件方式',
  `insurance` double NULL DEFAULT NULL COMMENT '保险',
  `receive_user_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收货人id',
  `send_goods_user_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '寄货人id',
  `remark` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `status` int(11) NULL DEFAULT NULL COMMENT '订单状态',
  `gmt_create` bigint(20) NULL DEFAULT NULL,
  `gmt_modified` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `goods_info_id`(`goods_info_id`) USING BTREE,
  INDEX `car_id`(`car_id`) USING BTREE,
  CONSTRAINT `transfer_info_ibfk_1` FOREIGN KEY (`goods_info_id`) REFERENCES `goods_info` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `transfer_info_ibfk_2` FOREIGN KEY (`car_id`) REFERENCES `car` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of transfer_info
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type_id` int(11) NULL DEFAULT NULL COMMENT '用户类型',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `tel` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `email` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `gmt_modified` bigint(20) NULL DEFAULT NULL COMMENT '最新修改时间',
  `gmt_create` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `gmt_last_login` bigint(20) NULL DEFAULT NULL COMMENT '最后一次登录时间',
  `token` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '密码',
  `id_card` varchar(18) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '身份证',
  `birthday` bigint(50) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `type_id`(`type_id`) USING BTREE,
  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`type_id`) REFERENCES `user_group` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------

-- ----------------------------
-- Table structure for user_group
-- ----------------------------
DROP TABLE IF EXISTS `user_group`;
CREATE TABLE `user_group`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `group_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_group
-- ----------------------------
INSERT INTO `user_group` VALUES (1, '对职员用户的增删改查\n', '管理组');
INSERT INTO `user_group` VALUES (2, '司机组', '司机组');
INSERT INTO `user_group` VALUES (3, '填写货运单', '客户组');
INSERT INTO `user_group` VALUES (4, '接货管理：填写一份货运单合同', '票据组');
INSERT INTO `user_group` VALUES (5, '结算管理', '财务组');

-- ----------------------------
-- Table structure for warehouse
-- ----------------------------
DROP TABLE IF EXISTS `warehouse`;
CREATE TABLE `warehouse`  (
  `id` int(11) NOT NULL,
  `director_user_id` bigint(20) NULL DEFAULT NULL,
  `location_id` int(11) NULL DEFAULT NULL,
  `description` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `director_user_id`(`director_user_id`) USING BTREE,
  INDEX `location_id`(`location_id`) USING BTREE,
  CONSTRAINT `warehouse_ibfk_1` FOREIGN KEY (`director_user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `warehouse_ibfk_2` FOREIGN KEY (`location_id`) REFERENCES `location` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of warehouse
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
