/*
 Navicat Premium Data Transfer

 Source Server         : loclahost
 Source Server Type    : MySQL
 Source Server Version : 80015
 Source Host           : localhost:3306
 Source Schema         : dx_cloud_cr

 Target Server Type    : MySQL
 Target Server Version : 80015
 File Encoding         : 65001

 Date: 16/07/2020 15:18:56
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for nkd_cas_depts
-- ----------------------------
DROP TABLE IF EXISTS `nkd_cas_depts`;
CREATE TABLE `nkd_cas_depts`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ZSJDWID` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '主数据单位ID',
  `ZSJMC` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '主数据名称',
  `CASDWID` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'CAS单位ID',
  `CASDWMC` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'CAS单位名称',
  `CASFJDW` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'CAS父级ID',
  `DISABLE` int(11) NULL DEFAULT NULL COMMENT '是否禁用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 122 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of nkd_cas_depts
-- ----------------------------
INSERT INTO `nkd_cas_depts` VALUES (1, '010', '理学院', '010', '理学院', '1', 0);
INSERT INTO `nkd_cas_depts` VALUES (2, '010010', '数学系', '010010', '数学系', '010', 0);
INSERT INTO `nkd_cas_depts` VALUES (3, '010020', '物理系', '010020', '物理系', '010', 0);
INSERT INTO `nkd_cas_depts` VALUES (4, '010030', '化学系', '010030', '化学系', '010', 0);
INSERT INTO `nkd_cas_depts` VALUES (5, '010080', '地球与空间科学系', '010080', '地球与空间科学系', '010', 0);
INSERT INTO `nkd_cas_depts` VALUES (6, '010090', '统计与数据科学系', '010090', '统计与数据科学系', '010', 0);
INSERT INTO `nkd_cas_depts` VALUES (7, '020', '工学院', '020', '工学院', '1', 0);
INSERT INTO `nkd_cas_depts` VALUES (8, '020010', '电子与电气工程系', '020010', '电子与电气工程系', '020', 0);
INSERT INTO `nkd_cas_depts` VALUES (9, '020020', '材料科学与工程系', '020020', '材料科学与工程系', '020', 0);
INSERT INTO `nkd_cas_depts` VALUES (10, '020030', '环境科学与工程学院', '020030', '环境科学与工程学院', '020', 0);
INSERT INTO `nkd_cas_depts` VALUES (11, '02003010', '南科大工程技术创新中心（北京）', '02003010', '南科大工程技术创新中心（北京）', '020030', 0);
INSERT INTO `nkd_cas_depts` VALUES (12, '020040', '海洋科学与工程系', '020040', '海洋科学与工程系', '020', 0);
INSERT INTO `nkd_cas_depts` VALUES (13, '020050', '力学与航空航天工程系', '020050', '力学与航空航天工程系', '020', 0);
INSERT INTO `nkd_cas_depts` VALUES (14, '020060', '机械与能源工程系', '020060', '机械与能源工程系', '020', 0);
INSERT INTO `nkd_cas_depts` VALUES (15, '020070', '计算机科学与工程系', '020070', '计算机科学与工程系', '020', 0);
INSERT INTO `nkd_cas_depts` VALUES (16, '020080', '地球与空间科学系', '020080', '地球与空间科学系', '020', 0);
INSERT INTO `nkd_cas_depts` VALUES (17, '020100', '生物医学工程系', '020100', '生物医学工程系', '020', 0);
INSERT INTO `nkd_cas_depts` VALUES (18, '020150', '系统设计与智能制造学院', '020150', '系统设计与智能制造学院', '020', 0);
INSERT INTO `nkd_cas_depts` VALUES (19, '030', '生命与健康学院', '030', '生命与健康学院', '1', 0);
INSERT INTO `nkd_cas_depts` VALUES (20, '030010', '生物系', '030010', '生物系', '030', 0);
INSERT INTO `nkd_cas_depts` VALUES (21, '040', '商学院', '040', '商学院', '1', 0);
INSERT INTO `nkd_cas_depts` VALUES (22, '040010', '金融系', '040010', '金融系', '040', 0);
INSERT INTO `nkd_cas_depts` VALUES (23, '040020', '管理科学与信息系统系', '040020', '管理科学与信息系统系', '040', 0);
INSERT INTO `nkd_cas_depts` VALUES (24, '050', '人文社科学部', '050', '人文社科学部', '1', 0);
INSERT INTO `nkd_cas_depts` VALUES (25, '050010', '人文科学中心', '050010', '人文科学中心', '050', 0);
INSERT INTO `nkd_cas_depts` VALUES (26, '050020', '社会科学中心', '050020', '社会科学中心', '050', 0);
INSERT INTO `nkd_cas_depts` VALUES (27, '050030', '思想政治教育与研究中心', '050030', '思想政治教育与研究中心', '050', 0);
INSERT INTO `nkd_cas_depts` VALUES (28, '055', '创新创业学院', '055', '创新创业学院', '1', 0);
INSERT INTO `nkd_cas_depts` VALUES (29, '058', '医学院', '058', '医学院', '1', 0);
INSERT INTO `nkd_cas_depts` VALUES (30, '060', '语言中心', '060', '语言中心', '1', 0);
INSERT INTO `nkd_cas_depts` VALUES (31, '065', '思想政治教育与研究中心', '065', '思想政治教育与研究中心', '1', 0);
INSERT INTO `nkd_cas_depts` VALUES (32, '070', '公共基础课部', '070', '公共基础课部', '1', 0);
INSERT INTO `nkd_cas_depts` VALUES (33, '070010', '艺术中心', '070010', '艺术中心', '070', 0);
INSERT INTO `nkd_cas_depts` VALUES (34, '070020', '体育中心', '070020', '体育中心', '070', 0);
INSERT INTO `nkd_cas_depts` VALUES (35, '080', '公共分析测试中心', '080', '公共分析测试中心', '1', 0);
INSERT INTO `nkd_cas_depts` VALUES (36, '085', '实验动物中心', '085', '实验动物中心', '1', 0);
INSERT INTO `nkd_cas_depts` VALUES (37, '090', '前沿与交叉科学研究院', '090', '前沿与交叉科学研究院', '1', 0);
INSERT INTO `nkd_cas_depts` VALUES (38, '095', '先进技术研究院', '095', '先进技术研究院', '1', 0);
INSERT INTO `nkd_cas_depts` VALUES (39, '098', '南方科技大学-北京大学植物与食品联合研究所', '098', '南方科技大学-北京大学植物与食品联合研究所', '1', 0);
INSERT INTO `nkd_cas_depts` VALUES (40, '1', '南方科技大学', '1', '南方科技大学', NULL, 0);
INSERT INTO `nkd_cas_depts` VALUES (41, '100', '校领导', '100', '校领导', '1', 0);
INSERT INTO `nkd_cas_depts` VALUES (42, '110', '党政办公室', '110', '党政办公室', '1', 0);
INSERT INTO `nkd_cas_depts` VALUES (43, '110010', '校车队', '110010', '校车队', '110', 0);
INSERT INTO `nkd_cas_depts` VALUES (44, '110020', '纪检监察室', '110020', '纪检监察室', '110', 0);
INSERT INTO `nkd_cas_depts` VALUES (45, '115', '校工会', '115', '校工会', '1', 0);
INSERT INTO `nkd_cas_depts` VALUES (46, '120', '宣传与公共关系部', '120', '宣传与公共关系部', '1', 0);
INSERT INTO `nkd_cas_depts` VALUES (47, '125', '纪检监察室（审计法务室）', '125', '纪检监察室（审计法务室）', '1', 0);
INSERT INTO `nkd_cas_depts` VALUES (48, '130', '校团委', '130', '校团委', '1', 0);
INSERT INTO `nkd_cas_depts` VALUES (49, '140', '教学工作部', '140', '教学工作部', '1', 0);
INSERT INTO `nkd_cas_depts` VALUES (50, '150', '学生工作部', '150', '学生工作部', '1', 0);
INSERT INTO `nkd_cas_depts` VALUES (51, '150010', '致仁书院', '150010', '致仁书院', '150', 0);
INSERT INTO `nkd_cas_depts` VALUES (52, '150020', '树仁书院', '150020', '树仁书院', '150', 0);
INSERT INTO `nkd_cas_depts` VALUES (53, '150030', '致诚书院', '150030', '致诚书院', '150', 0);
INSERT INTO `nkd_cas_depts` VALUES (54, '150040', '树德书院', '150040', '树德书院', '150', 0);
INSERT INTO `nkd_cas_depts` VALUES (55, '150050', '致新书院', '150050', '致新书院', '150', 0);
INSERT INTO `nkd_cas_depts` VALUES (56, '150060', '树礼书院', '150060', '树礼书院', '150', 0);
INSERT INTO `nkd_cas_depts` VALUES (57, '160', '招生办公室', '160', '招生办公室', '1', 0);
INSERT INTO `nkd_cas_depts` VALUES (58, '170', '研究生院', '170', '研究生院', '1', 0);
INSERT INTO `nkd_cas_depts` VALUES (59, '180', '科研部', '180', '科研部', '1', 0);
INSERT INTO `nkd_cas_depts` VALUES (60, '190', '规划发展部', '190', '规划发展部', '1', 0);
INSERT INTO `nkd_cas_depts` VALUES (61, '195', '重大项目办公室', '195', '重大项目办公室', '1', 0);
INSERT INTO `nkd_cas_depts` VALUES (62, '200', '实验室与设备管理部', '200', '实验室与设备管理部', '1', 0);
INSERT INTO `nkd_cas_depts` VALUES (63, '200010', '安全办公室', '200010', '安全办公室', '200', 0);
INSERT INTO `nkd_cas_depts` VALUES (64, '210', '人力资源部(组织统战部)', '210', '人力资源部(组织统战部)', '1', 0);
INSERT INTO `nkd_cas_depts` VALUES (65, '220', '财务部', '220', '财务部', '1', 0);
INSERT INTO `nkd_cas_depts` VALUES (66, '220010', '资产管理办公室', '220010', '资产管理办公室', '220', 0);
INSERT INTO `nkd_cas_depts` VALUES (67, '225', '招标办公室', '225', '招标办公室', '1', 0);
INSERT INTO `nkd_cas_depts` VALUES (68, '230', '国际合作部', '230', '国际合作部', '1', 0);
INSERT INTO `nkd_cas_depts` VALUES (69, '238', '总务与空间办公室', '238', '总务与空间办公室', '1', 0);
INSERT INTO `nkd_cas_depts` VALUES (70, '239', '校园服务办公室', '239', '校园服务办公室', '1', 0);
INSERT INTO `nkd_cas_depts` VALUES (71, '240', '后勤保障部', '240', '后勤保障部', '1', 0);
INSERT INTO `nkd_cas_depts` VALUES (72, '240010', '医疗中心', '240010', '医疗中心', '240', 0);
INSERT INTO `nkd_cas_depts` VALUES (73, '240020', '幼儿园', '240020', '幼儿园', '240', 0);
INSERT INTO `nkd_cas_depts` VALUES (74, '241', '设施设备维护办公室', '241', '设施设备维护办公室', '1', 0);
INSERT INTO `nkd_cas_depts` VALUES (75, '242', '安全、健康与环境办公室', '242', '安全、健康与环境办公室', '1', 0);
INSERT INTO `nkd_cas_depts` VALUES (76, '250', '基建办公室', '250', '基建办公室', '1', 0);
INSERT INTO `nkd_cas_depts` VALUES (77, '255', '校园建设规划委员会办公室', '255', '校园建设规划委员会办公室', '1', 0);
INSERT INTO `nkd_cas_depts` VALUES (78, '260', '网络信息中心', '260', '网络信息中心', '1', 0);
INSERT INTO `nkd_cas_depts` VALUES (79, '260test', '网络信息中心test', '260test', '网络信息中心test', '1', 1);
INSERT INTO `nkd_cas_depts` VALUES (80, '270', '图书馆', '270', '图书馆', '1', 0);
INSERT INTO `nkd_cas_depts` VALUES (81, '275', '技术转移中心', '275', '技术转移中心', '1', 0);
INSERT INTO `nkd_cas_depts` VALUES (82, '280', '资产经营管理有限公司', '280', '资产经营管理有限公司', '1', 0);
INSERT INTO `nkd_cas_depts` VALUES (83, '280100', '南科润杨产学研基地有限公司', '280100', '南科润杨产学研基地有限公司', '280', 0);
INSERT INTO `nkd_cas_depts` VALUES (84, '285', '物业管理公司', '285', '物业管理公司', '1', 0);
INSERT INTO `nkd_cas_depts` VALUES (85, '287', '孵化器管理有限公司', '287', '孵化器管理有限公司', '1', 0);
INSERT INTO `nkd_cas_depts` VALUES (86, '290', '餐饮服务中心', '290', '餐饮服务中心', '1', 0);
INSERT INTO `nkd_cas_depts` VALUES (87, '300', '教育基金会', '300', '教育基金会', '1', 0);
INSERT INTO `nkd_cas_depts` VALUES (88, '310', '校友会', '310', '校友会', '1', 0);
INSERT INTO `nkd_cas_depts` VALUES (89, '400', '医学院建设办公室', '400', '医学院建设办公室', '1', 0);
INSERT INTO `nkd_cas_depts` VALUES (90, '500', '高等教育研究中心', '500', '高等教育研究中心', '050', 0);
INSERT INTO `nkd_cas_depts` VALUES (91, '600', '南科大实验教育集团建设办公室', '600', '南科大实验教育集团建设办公室', '1', 0);
INSERT INTO `nkd_cas_depts` VALUES (92, '649', '附属教育集团', '649', '附属教育集团', '1', 0);
INSERT INTO `nkd_cas_depts` VALUES (93, '649010', '教育集团（南山）', '649010', '教育集团（南山）', '649', 0);
INSERT INTO `nkd_cas_depts` VALUES (94, '650', '南方科技大学附属医院建设办公室', '650', '南方科技大学附属医院建设办公室', '1', 0);
INSERT INTO `nkd_cas_depts` VALUES (95, '700', '未来网络研究院', '700', '未来网络研究院', '1', 0);
INSERT INTO `nkd_cas_depts` VALUES (96, '710', '量子科学与工程研究院', '710', '量子科学与工程研究院', '1', 0);
INSERT INTO `nkd_cas_depts` VALUES (97, '720', '空天动力研究院', '720', '空天动力研究院', '1', 0);
INSERT INTO `nkd_cas_depts` VALUES (98, '730', '南方科技大学-香港科技大学深港微电子学院筹建办公室', '730', '南方科技大学-香港科技大学深港微电子学院筹建办公室', '1', 0);
INSERT INTO `nkd_cas_depts` VALUES (99, '740', '科学与工程计算中心', '740', '科学与工程计算中心', '1', 0);
INSERT INTO `nkd_cas_depts` VALUES (100, '900', '深圳格拉布斯研究院', '900', '深圳格拉布斯研究院', '1', 0);
INSERT INTO `nkd_cas_depts` VALUES (101, '905', '深圳国际数学中心（杰曼诺夫数学中心）（筹）', '905', '深圳国际数学中心（杰曼诺夫数学中心）（筹）', '1', 0);
INSERT INTO `nkd_cas_depts` VALUES (102, 'del', '离退', 'del', '离退', '1', 0);
INSERT INTO `nkd_cas_depts` VALUES (103, 'pg', '研究生', 'pg', '研究生', '1', 0);
INSERT INTO `nkd_cas_depts` VALUES (104, 'pg2015', '研究生2015级', 'pg2015', '研究生2015级', 'pg', 0);
INSERT INTO `nkd_cas_depts` VALUES (105, 'pg2016', '研究生2016级', 'pg2016', '研究生2016级', 'pg', 0);
INSERT INTO `nkd_cas_depts` VALUES (106, 'pg2017', '研究生2017级', 'pg2017', '研究生2017级', 'pg', 0);
INSERT INTO `nkd_cas_depts` VALUES (107, 'pg2018', '研究生2018级', 'pg2018', '研究生2018级', 'pg', 0);
INSERT INTO `nkd_cas_depts` VALUES (108, 'pg2019', '研究生2019级', 'pg2019', '研究生2019级', 'pg', 0);
INSERT INTO `nkd_cas_depts` VALUES (109, 'ug', '本科生', 'ug', '本科生', '1', 0);
INSERT INTO `nkd_cas_depts` VALUES (110, 'ug2011', '教改实验班', 'ug2011', '教改实验班', 'ug', 0);
INSERT INTO `nkd_cas_depts` VALUES (111, 'ug2012', '本科2012级', 'ug2012', '本科2012级', 'ug', 0);
INSERT INTO `nkd_cas_depts` VALUES (112, 'ug2013', '本科2013级', 'ug2013', '本科2013级', 'ug', 0);
INSERT INTO `nkd_cas_depts` VALUES (113, 'ug2014', '本科2014级', 'ug2014', '本科2014级', 'ug', 0);
INSERT INTO `nkd_cas_depts` VALUES (114, 'ug2015', '本科2015级', 'ug2015', '本科2015级', 'ug', 0);
INSERT INTO `nkd_cas_depts` VALUES (115, 'ug2016', '本科2016级', 'ug2016', '本科2016级', 'ug', 0);
INSERT INTO `nkd_cas_depts` VALUES (116, 'ug2017', '本科2017级', 'ug2017', '本科2017级', 'ug', 0);
INSERT INTO `nkd_cas_depts` VALUES (117, 'ug2018', '本科2018级', 'ug2018', '本科2018级', 'ug', 0);
INSERT INTO `nkd_cas_depts` VALUES (118, 'ug2019', '本科2019级', 'ug2019', '本科2019级', 'ug', 0);
INSERT INTO `nkd_cas_depts` VALUES (119, '科学与工程计算中心', 'KXGC', '科学与工程计算中心', 'KXGC', '1', 1);

-- ----------------------------
-- Table structure for nkd_cas_employs
-- ----------------------------
DROP TABLE IF EXISTS `nkd_cas_employs`;
CREATE TABLE `nkd_cas_employs`  (
  `SID` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `CASID` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `DISABLE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `DEPTS` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Group` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phonenum` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `zw` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '职位'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of nkd_cas_employs
-- ----------------------------
INSERT INTO `nkd_cas_employs` VALUES ('70000281', '57951e74d10091a07f6e3ca9', '0', NULL, '[\"temp\"]', '刘思洋', NULL, NULL, NULL);
INSERT INTO `nkd_cas_employs` VALUES ('70000235', '57d64c30d10091a07f6e4307', '0', NULL, '[\"temp\"]', '邓阳', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `parent_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单URL',
  `perms` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
  `type` int(11) NULL DEFAULT NULL COMMENT ' 1：菜单   2：按钮',
  `icon` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单图标',
  `order_num` int(11) NULL DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜单管理' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `remark` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_user_id` int(11) NULL DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `dic_id` int(11) NULL DEFAULT NULL COMMENT '数据字典id',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 238 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  `menu_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11337 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色与菜单对应关系' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  `cas_user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'CAS用户ID',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `role_id`(`role_id`) USING BTREE,
  INDEX `cas_user_id`(`cas_user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 373 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户与角色对应关系' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
