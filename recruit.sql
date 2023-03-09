/*
 Navicat Premium Data Transfer

 Source Server         : zuo-localhost
 Source Server Type    : MySQL
 Source Server Version : 50721
 Source Host           : localhost:3306
 Source Schema         : recruit

 Target Server Type    : MySQL
 Target Server Version : 50721
 File Encoding         : 65001

 Date: 09/03/2023 16:28:36
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '管理员ID',
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, 'Yuu', '81dc9bdb52d04dc20036dbd8313ed055');

-- ----------------------------
-- Table structure for bid
-- ----------------------------
DROP TABLE IF EXISTS `bid`;
CREATE TABLE `bid`  (
  `id` bigint(20) NOT NULL COMMENT '投标ID',
  `task_id` bigint(20) NULL DEFAULT NULL COMMENT '任务ID',
  `employee_id` bigint(20) NULL DEFAULT NULL COMMENT '职业者ID',
  `bid_price` double NULL DEFAULT NULL COMMENT '投标价格',
  `delivery_desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '交货时间描述，例如 1 天',
  `delivery_time` timestamp(0) NULL DEFAULT NULL COMMENT '交货时间',
  `bid_status` tinyint(4) NULL DEFAULT NULL COMMENT '竞标状态',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bid
-- ----------------------------
INSERT INTO `bid` VALUES (157124347413656, 157124009918832, 157124288026264, 1500, '1小时', '2019-10-17 01:31:14', 1, '2019-10-17 00:31:14');
INSERT INTO `bid` VALUES (167778795425216, 157124114265141, 157124288026264, 550, '1小时', '2023-03-03 05:12:34', 1, '2023-03-03 04:12:34');
INSERT INTO `bid` VALUES (167791907316761, 167791902309843, 167791852018862, 1500, '1小时', '2023-03-04 17:37:53', 1, '2023-03-04 16:37:53');
INSERT INTO `bid` VALUES (167834651713476, 167834647107390, 157124225706439, 1500, '1小时', '2023-03-09 16:21:57', 1, '2023-03-09 15:21:57');
INSERT INTO `bid` VALUES (167834723291952, 167834719064241, 157124225706439, 1500, '1小时', '2023-03-09 16:33:53', 0, '2023-03-09 15:33:53');

-- ----------------------------
-- Table structure for complaints
-- ----------------------------
DROP TABLE IF EXISTS `complaints`;
CREATE TABLE `complaints`  (
  `id` bigint(20) NOT NULL,
  `complainant` bigint(20) NULL DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` timestamp(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of complaints
-- ----------------------------
INSERT INTO `complaints` VALUES (167785585126454, 157124225706439, '有问题', '2023-03-03 23:04:11');
INSERT INTO `complaints` VALUES (167791889859357, 167791852018862, '网站太差了', '2023-03-04 16:34:59');

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee`  (
  `id` bigint(20) NOT NULL COMMENT '雇员ID',
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登录密码',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `head_img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'http://recruit1.oss-cn-shenzhen.aliyuncs.com/4aa3d423-b8cf-4520-91ba-3f30b578973f.png' COMMENT '头像地址',
  `tagline` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标语',
  `profile` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '个人简介',
  `browse_count` int(11) NULL DEFAULT 0 COMMENT '主页被浏览次数',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `appraise` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商户评价',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES (157124225706439, 'Yuu', '1234', '1225459207@qq.com', '13055206361', 'http://recruit1.oss-cn-shenzhen.aliyuncs.com/dc7fa65a-ae93-4938-8f91-01f6c6e7ee4c.jpg', '好人', '来跟妲己一起玩呀~~~', 3, '2019-10-17 00:10:57', NULL);
INSERT INTO `employee` VALUES (157124288026264, 'Yuu2', '1234', '123@qq.com', '13230180577', 'http://recruit1.oss-cn-shenzhen.aliyuncs.com/10f65b3a-e73d-4d8b-b95b-3841534ea0dc.png', '我要找工作', '该用户效率很高\r\n', 58, '2019-10-17 00:21:20', '\r\n商户评价：不错');
INSERT INTO `employee` VALUES (167791852018862, 'ZuoJiaCheng', '1234', '1225459206@qq.com', '13230180577', 'http://recruit1.oss-cn-shenzhen.aliyuncs.com/11ae52d9-3581-425b-a7aa-8a4ca7d5cc1f.png', '本科', '我超厉害\r\n', 11, '2023-03-04 16:28:40', '\r\n商户评价：不错');

-- ----------------------------
-- Table structure for employee_bookmarked
-- ----------------------------
DROP TABLE IF EXISTS `employee_bookmarked`;
CREATE TABLE `employee_bookmarked`  (
  `id` bigint(20) NOT NULL COMMENT '雇员收藏任务ID',
  `employee_id` bigint(20) NULL DEFAULT NULL COMMENT '雇员ID',
  `task_id` bigint(20) NULL DEFAULT NULL COMMENT '任务ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of employee_bookmarked
-- ----------------------------
INSERT INTO `employee_bookmarked` VALUES (157124280249465, 157124225706439, 157124114265141);
INSERT INTO `employee_bookmarked` VALUES (157124281570732, 157124225706439, 157124090699406);
INSERT INTO `employee_bookmarked` VALUES (157124282308079, 157124225706439, 157124009918832);
INSERT INTO `employee_bookmarked` VALUES (167778654100735, 157124288026264, 157124114265141);

-- ----------------------------
-- Table structure for employee_skill
-- ----------------------------
DROP TABLE IF EXISTS `employee_skill`;
CREATE TABLE `employee_skill`  (
  `id` bigint(20) NOT NULL COMMENT '雇员技能对应ID',
  `employee_id` bigint(20) NULL DEFAULT NULL COMMENT '雇员ID',
  `skill_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '技能名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of employee_skill
-- ----------------------------
INSERT INTO `employee_skill` VALUES (157124228454456, 157124225706439, 'Java');
INSERT INTO `employee_skill` VALUES (157124228840540, 157124225706439, 'MySQL');
INSERT INTO `employee_skill` VALUES (157124229314145, 157124225706439, 'SSM');
INSERT INTO `employee_skill` VALUES (157124229869194, 157124225706439, 'Spring Boot');
INSERT INTO `employee_skill` VALUES (167791885652104, 167791852018862, '计算机二级');

-- ----------------------------
-- Table structure for employer
-- ----------------------------
DROP TABLE IF EXISTS `employer`;
CREATE TABLE `employer`  (
  `id` bigint(20) NOT NULL COMMENT '雇主ID',
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登录密码',
  `head_img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of employer
-- ----------------------------
INSERT INTO `employer` VALUES (157123889925612, 'YDY', '1234', 'http://recruit1.oss-cn-shenzhen.aliyuncs.com/37872ab6-0460-4905-85b1-5350e09df6e4.jpg', '13055206362', '1225459206@qq.com', '2019-10-16 23:14:59');

-- ----------------------------
-- Table structure for home_bower
-- ----------------------------
DROP TABLE IF EXISTS `home_bower`;
CREATE TABLE `home_bower`  (
  `id` bigint(20) NOT NULL COMMENT '主页浏览表',
  `employee_id` bigint(20) NULL DEFAULT NULL COMMENT '雇员ID',
  `employer_id` bigint(20) NULL DEFAULT NULL COMMENT '雇主ID',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '浏览时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of home_bower
-- ----------------------------
INSERT INTO `home_bower` VALUES (167778925746941, 157124288026264, 157123889925612, '2023-03-03 04:34:17');
INSERT INTO `home_bower` VALUES (167778926899445, 157124288026264, 157123889925612, '2023-03-03 04:34:29');
INSERT INTO `home_bower` VALUES (167778940348153, 157124288026264, 157123889925612, '2023-03-03 04:36:43');
INSERT INTO `home_bower` VALUES (167778943981149, 157124288026264, 157123889925612, '2023-03-03 04:37:20');
INSERT INTO `home_bower` VALUES (167779004149467, 157124288026264, 157123889925612, '2023-03-03 04:47:21');
INSERT INTO `home_bower` VALUES (167779056098973, 157124288026264, 157123889925612, '2023-03-03 04:56:01');
INSERT INTO `home_bower` VALUES (167779061246824, 157124288026264, 157123889925612, '2023-03-03 04:56:52');
INSERT INTO `home_bower` VALUES (167779207172395, 167778134393411, 157123889925612, '2023-03-03 05:21:12');
INSERT INTO `home_bower` VALUES (167779588169350, 157124288026264, 157123889925612, '2023-03-03 06:24:42');
INSERT INTO `home_bower` VALUES (167782774333098, 157124288026264, 157123889925612, '2023-03-03 15:15:43');
INSERT INTO `home_bower` VALUES (167782776217108, 157124288026264, 157123889925612, '2023-03-03 15:16:02');
INSERT INTO `home_bower` VALUES (167782801663195, 157124288026264, 157123889925612, '2023-03-03 15:20:17');
INSERT INTO `home_bower` VALUES (167782822004219, 157124288026264, 157123889925612, '2023-03-03 15:23:40');
INSERT INTO `home_bower` VALUES (167782952262888, 157124288026264, 157123889925612, '2023-03-03 15:45:23');
INSERT INTO `home_bower` VALUES (167782966525682, 157124288026264, 157123889925612, '2023-03-03 15:47:45');
INSERT INTO `home_bower` VALUES (167782978168331, 157124288026264, 157123889925612, '2023-03-03 15:49:42');
INSERT INTO `home_bower` VALUES (167782980375349, 157124288026264, 157123889925612, '2023-03-03 15:50:04');
INSERT INTO `home_bower` VALUES (167782985560489, 157124288026264, 157123889925612, '2023-03-03 15:50:56');
INSERT INTO `home_bower` VALUES (167782986445504, 157124288026264, 157123889925612, '2023-03-03 15:51:04');
INSERT INTO `home_bower` VALUES (167782987596168, 157124288026264, 157123889925612, '2023-03-03 15:51:16');
INSERT INTO `home_bower` VALUES (167782997344009, 157124288026264, 157123889925612, '2023-03-03 15:52:53');
INSERT INTO `home_bower` VALUES (167783026394729, 157124288026264, 157123889925612, '2023-03-03 15:57:44');
INSERT INTO `home_bower` VALUES (167783056437349, 157124288026264, 157123889925612, '2023-03-03 16:02:44');
INSERT INTO `home_bower` VALUES (167783057280742, 157124288026264, 157123889925612, '2023-03-03 16:02:53');
INSERT INTO `home_bower` VALUES (167783063230873, 157124288026264, 157123889925612, '2023-03-03 16:03:52');
INSERT INTO `home_bower` VALUES (167783075788191, 157124288026264, 157123889925612, '2023-03-03 16:05:58');
INSERT INTO `home_bower` VALUES (167791926083243, 167791852018862, 157123889925612, '2023-03-04 16:41:01');
INSERT INTO `home_bower` VALUES (167791959898927, 167791852018862, 157123889925612, '2023-03-04 16:46:39');
INSERT INTO `home_bower` VALUES (167792002551626, 157124225706439, 157123889925612, '2023-03-04 16:53:46');
INSERT INTO `home_bower` VALUES (167792002888103, 157124225706439, 157123889925612, '2023-03-04 16:53:49');
INSERT INTO `home_bower` VALUES (167792008246732, 157124225706439, 157123889925612, '2023-03-04 16:54:42');
INSERT INTO `home_bower` VALUES (167828698438646, 157124288026264, 157123889925612, '2023-03-08 22:49:44');
INSERT INTO `home_bower` VALUES (167835022938574, 167791852018862, 157123889925612, '2023-03-09 16:23:49');
INSERT INTO `home_bower` VALUES (167835024086772, 157124288026264, 157123889925612, '2023-03-09 16:24:01');
INSERT INTO `home_bower` VALUES (167835024699127, 157124288026264, 157123889925612, '2023-03-09 16:24:07');
INSERT INTO `home_bower` VALUES (167835024977326, 157124288026264, 157123889925612, '2023-03-09 16:24:10');
INSERT INTO `home_bower` VALUES (167835025471721, 167791852018862, 157123889925612, '2023-03-09 16:24:15');
INSERT INTO `home_bower` VALUES (167835036734235, 167791852018862, 157123889925612, '2023-03-09 16:26:07');
INSERT INTO `home_bower` VALUES (167835037547313, 167791852018862, 157123889925612, '2023-03-09 16:26:15');
INSERT INTO `home_bower` VALUES (167835038345942, 167791852018862, 157123889925612, '2023-03-09 16:26:23');
INSERT INTO `home_bower` VALUES (167835042840192, 167791852018862, 157123889925612, '2023-03-09 16:27:08');
INSERT INTO `home_bower` VALUES (167835043816751, 167791852018862, 157123889925612, '2023-03-09 16:27:18');
INSERT INTO `home_bower` VALUES (167835044859809, 167791852018862, 157123889925612, '2023-03-09 16:27:29');
INSERT INTO `home_bower` VALUES (167835045901546, 167791852018862, 157123889925612, '2023-03-09 16:27:39');
INSERT INTO `home_bower` VALUES (167835047016572, 157124288026264, 157123889925612, '2023-03-09 16:27:50');
INSERT INTO `home_bower` VALUES (167835047745987, 157124288026264, 157123889925612, '2023-03-09 16:27:57');

-- ----------------------------
-- Table structure for job_want
-- ----------------------------
DROP TABLE IF EXISTS `job_want`;
CREATE TABLE `job_want`  (
  `id` bigint(20) NOT NULL,
  `job_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `job_desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `fees_low` double NULL DEFAULT NULL,
  `fees_high` double NULL DEFAULT NULL,
  `create_time` timestamp(0) NULL DEFAULT NULL,
  `job_status` int(4) NULL DEFAULT NULL,
  `employee_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of job_want
-- ----------------------------
INSERT INTO `job_want` VALUES (157124225706439, 'java工程师', 'java', 1000, 2000, '2023-03-01 16:38:58', 0, 157124225706439);
INSERT INTO `job_want` VALUES (167785193466865, 'PHP工程师', '后端开发', 1000, 2000, '2023-03-03 21:58:55', -1, 157124225706439);
INSERT INTO `job_want` VALUES (167785241488507, '前端工程师', '超级厉害的前端工程师', 2000, 3000, '2023-03-03 22:06:55', -1, 157124225706439);
INSERT INTO `job_want` VALUES (167791857400532, '讲师', '我要当讲师', 1234, 2000, '2023-03-04 16:29:34', 0, 167791852018862);

-- ----------------------------
-- Table structure for task
-- ----------------------------
DROP TABLE IF EXISTS `task`;
CREATE TABLE `task`  (
  `id` bigint(20) NOT NULL COMMENT '任务ID',
  `category_id` bigint(20) NULL DEFAULT NULL COMMENT '任务分类ID',
  `employer_id` bigint(20) NULL DEFAULT NULL COMMENT '任务发布雇主ID',
  `task_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务标题',
  `task_profile` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务简介',
  `task_desc` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '任务描述',
  `fees_low` double NULL DEFAULT NULL COMMENT '最低预算价格',
  `fees_high` double NULL DEFAULT NULL COMMENT '最高预算价格',
  `fees_file` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务附件地址',
  `filename` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '附件文件名称',
  `employee_id` bigint(20) NULL DEFAULT NULL COMMENT '完成任务雇员ID',
  `task_price` double NULL DEFAULT NULL COMMENT '任务成交价格',
  `task_status` tinyint(4) NULL DEFAULT NULL COMMENT '任务状态',
  `close_time` timestamp(0) NULL DEFAULT NULL COMMENT '成交时间',
  `bid_time` timestamp(0) NULL DEFAULT NULL COMMENT '中标时间',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of task
-- ----------------------------
INSERT INTO `task` VALUES (157124009918832, 157123941263519, 157123889925612, '我需要一个微信小程序', '一个商城系统', '微信小程序(这个项目不错我很喜欢，给的钱很多） 用户评价：商家超级棒！！！ \r\n用户评价：还行吧 ', 1000, 2000, 'http://recruit1.oss-cn-shenzhen.aliyuncs.com/d7077ec8-0ed5-4e7d-8e6f-d45138baf212.pdf', '常用类.pdf', 157124288026264, 1500, 3, '2023-03-03 05:01:10', '2023-03-03 04:55:58', '2019-10-16 23:34:59');
INSERT INTO `task` VALUES (157124090699406, 157123941263519, 157123889925612, '我需要找个人玩游戏', 'LOL、王者荣耀一起来', '快来玩耍呀！ 用户评价：很有趣的啊！！！ ', 10, 100, 'http://recruit1.oss-cn-shenzhen.aliyuncs.com/5084c0a5-71cb-495d-a929-fb6348c8211d.pdf', '面向对象.pdf', 157124288026264, 10, 3, '2019-10-17 00:39:57', '2019-10-17 00:24:40', '2019-10-16 23:48:27');
INSERT INTO `task` VALUES (167791902309843, 157123941263519, 157123889925612, '网站开发', '很简单的网站开发', '很简单的网站\r\n用户评价：雇主很好 ', 1000, 2000, 'http://recruit1.oss-cn-shenzhen.aliyuncs.com/980629a2-903c-45be-b5ef-423dd3a8a385.pdf', '董洋铭毕设要求.pdf', 167791852018862, 1500, 3, '2023-03-04 16:38:35', '2023-03-04 16:38:06', '2023-03-04 16:37:03');
INSERT INTO `task` VALUES (167828968477211, 157123941263519, 157123889925612, 'Springboot后端开发', '开发网站', '很简单的', 1000, 2000, 'http://recruit1.oss-cn-shenzhen.aliyuncs.com/6619a36c-8490-4c74-b08a-1512e80ecd52.pdf', '董洋铭毕设要求.pdf', NULL, NULL, 0, NULL, NULL, '2023-03-08 23:34:45');
INSERT INTO `task` VALUES (167834719064241, 157123941263519, 157123889925612, '网页', '很简单的网页', '很简单的网页', 1000, 2000, '', '', 167834874004565, 1500, 0, NULL, '2023-03-09 15:59:27', '2023-03-09 15:33:11');

-- ----------------------------
-- Table structure for task_category
-- ----------------------------
DROP TABLE IF EXISTS `task_category`;
CREATE TABLE `task_category`  (
  `id` bigint(20) NOT NULL COMMENT '任务分类ID',
  `category_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分类名称',
  `category_img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分类图片展示地址',
  `is_popular` tinyint(4) NULL DEFAULT 0 COMMENT '是否热门 0 否 1 热门',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of task_category
-- ----------------------------
INSERT INTO `task_category` VALUES (157123941263519, '网站/软件开发', 'http://recruit1.oss-cn-shenzhen.aliyuncs.com/f5c7a84b-89fb-4b04-8e15-4afe18068de6.jpg', 1);
INSERT INTO `task_category` VALUES (157123944799651, '数据科学/分析学', 'http://recruit1.oss-cn-shenzhen.aliyuncs.com/853bca3f-eb79-478f-85f0-aa03e9def32e.jpg', 1);
INSERT INTO `task_category` VALUES (157123947616803, '会计/咨询', 'http://recruit1.oss-cn-shenzhen.aliyuncs.com/ab9a164d-ea0c-4409-a214-901bea818b89.jpg', 1);
INSERT INTO `task_category` VALUES (157123949774553, '写作/翻译', 'http://recruit1.oss-cn-shenzhen.aliyuncs.com/c588a079-e60b-46bd-b274-c3a477a06a90.jpg', 1);
INSERT INTO `task_category` VALUES (157123952210737, '销售/市场营销', 'http://recruit1.oss-cn-shenzhen.aliyuncs.com/062b6bac-1127-4209-8987-b532180129e9.jpg', 1);
INSERT INTO `task_category` VALUES (157123953698793, '图形/设计', 'http://recruit1.oss-cn-shenzhen.aliyuncs.com/07d93a43-d92f-44a8-8c86-cf471933eff5.jpg', 1);
INSERT INTO `task_category` VALUES (157123955607669, '数字营销', 'http://recruit1.oss-cn-shenzhen.aliyuncs.com/875d9129-a0f8-48a8-857a-b5fc2886e215.jpg', 1);
INSERT INTO `task_category` VALUES (157123956834969, '教育培训', 'http://recruit1.oss-cn-shenzhen.aliyuncs.com/45f15358-b3ba-4dc3-822e-ae6a233617a3.jpg', 1);

-- ----------------------------
-- Table structure for task_skill
-- ----------------------------
DROP TABLE IF EXISTS `task_skill`;
CREATE TABLE `task_skill`  (
  `id` bigint(20) NOT NULL COMMENT '任务技能ID',
  `skill_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '技能名称',
  `task_id` bigint(20) NULL DEFAULT NULL COMMENT '任务ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of task_skill
-- ----------------------------
INSERT INTO `task_skill` VALUES (157123994236997, 'Java', 157123994233279);
INSERT INTO `task_skill` VALUES (157123994237672, 'SSM', 157123994233279);
INSERT INTO `task_skill` VALUES (157123994237921, 'Spring Boot', 157123994233279);
INSERT INTO `task_skill` VALUES (157124026569962, '微信小程序', 157124009918832);
INSERT INTO `task_skill` VALUES (157124090700978, 'LOL', 157124090699406);
INSERT INTO `task_skill` VALUES (157124090701279, '英雄联盟', 157124090699406);
INSERT INTO `task_skill` VALUES (167778879490066, '', 167778879489043);
INSERT INTO `task_skill` VALUES (167779313928837, '', 167779313928251);
INSERT INTO `task_skill` VALUES (167791902310156, '', 167791902309843);
INSERT INTO `task_skill` VALUES (167828968477709, '', 167828968477211);
INSERT INTO `task_skill` VALUES (167834647108053, '', 167834647107390);
INSERT INTO `task_skill` VALUES (167834719064921, '', 167834719064241);

SET FOREIGN_KEY_CHECKS = 1;
