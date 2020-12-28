/*
Navicat MySQL Data Transfer

Source Server         : root
Source Server Version : 50520
Source Host           : localhost:3306
Source Database       : estore

Target Server Type    : MYSQL
Target Server Version : 50520
File Encoding         : 65001

Date: 2020-12-28 22:21:52
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `book`
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book` (
  `bid` char(32) NOT NULL,
  `bname` varchar(100) DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `author` varchar(50) DEFAULT NULL,
  `image` varchar(200) DEFAULT NULL,
  `cid` char(32) DEFAULT NULL,
  `isdel` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`bid`),
  KEY `cid` (`cid`),
  CONSTRAINT `book_ibfk_1` FOREIGN KEY (`cid`) REFERENCES `category` (`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES ('001', '封神榜', '50.00', '姜子牙', 'book_img/xh1.jpg', '1', '0');
INSERT INTO `book` VALUES ('002', '剑逆苍穹', '28.00', '甄嬛', 'book_img/xh2.jpg', '1', '0');
INSERT INTO `book` VALUES ('003', '薄环凉色', '50.00', '姜子牙', 'book_img/yq1.jpg', '2', '0');
INSERT INTO `book` VALUES ('004', '旋风侠盗', '61.00', '阿园', 'book_img/wx1.jpg', '5', '0');
INSERT INTO `book` VALUES ('005', '你懂我的爱', '73.00', '莫言', 'book_img/yq2.jpg', '2', '0');
INSERT INTO `book` VALUES ('006', '东归蝶血', '73.00', '张瑞峰', 'book_img/wx2.jpg', '5', '0');
INSERT INTO `book` VALUES ('007', '守夜', '73.00', '斯蒂芬金', 'book_img/kb1.jpg', '4', '0');
INSERT INTO `book` VALUES ('008', '日本恐怖小说选', '73.00', '村山槐多', 'book_img/kb2.jpg', '4', '0');
INSERT INTO `book` VALUES ('009', 'Java培训就业教程', '58.00', '张孝祥', 'book_img/kj1.jpg', '6', '0');
INSERT INTO `book` VALUES ('010', '精通Hibernate', '67.00', '孙鑫', 'book_img/kj2.jpg', '6', '0');
INSERT INTO `book` VALUES ('011', 'Java编程思想', '108.00', 'James', 'book_img/kj4.jpg', '6', '0');
INSERT INTO `book` VALUES ('012', 'Struts2详解', '42.00', '涛哥', 'book_img/kj8.jpg', '6', '0');
INSERT INTO `book` VALUES ('013', 'JavaWeb开发详解', '32.00', '涛哥', 'book_img/kj5.jpg', '6', '0');
INSERT INTO `book` VALUES ('014', '测试图书', '11.00', '测试作者', null, null, '1');
INSERT INTO `book` VALUES ('1423353802954cb2b2454f8970e637c0', '大漠苍狼', '11.00', '南派三叔', 'book_img/461cc3db4f8b4c4f89077544c57e39c0.jpg', '1', '1');
INSERT INTO `book` VALUES ('9a25af7e939c4af799a1c4f69e9933d5', '琅琊榜', '88.60', '唐僧', 'book_img/d3b24ceee24e4d81ad0a89819ba85b48.jpg', '5', '0');
INSERT INTO `book` VALUES ('c170dc85d8f84488acd2fc8ca4503863', '大漠苍狼', '51.00', '南派三叔', 'book_img/12b92f6638fc466384b2a3b36860c9e8.png', '1', '1');

-- ----------------------------
-- Table structure for `category`
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `cid` char(32) NOT NULL,
  `cname` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES ('1', '玄幻');
INSERT INTO `category` VALUES ('2', '言情');
INSERT INTO `category` VALUES ('4', '恐怖');
INSERT INTO `category` VALUES ('5', '武侠');
INSERT INTO `category` VALUES ('6', '科技');

-- ----------------------------
-- Table structure for `orderitem`
-- ----------------------------
DROP TABLE IF EXISTS `orderitem`;
CREATE TABLE `orderitem` (
  `itemid` char(32) NOT NULL,
  `count` int(11) DEFAULT NULL,
  `subtotal` decimal(10,2) DEFAULT NULL,
  `bid` char(32) DEFAULT NULL,
  `oid` char(32) DEFAULT NULL,
  PRIMARY KEY (`itemid`),
  KEY `bid` (`bid`),
  KEY `oid` (`oid`),
  CONSTRAINT `orderitem_ibfk_1` FOREIGN KEY (`bid`) REFERENCES `book` (`bid`),
  CONSTRAINT `orderitem_ibfk_2` FOREIGN KEY (`oid`) REFERENCES `orders` (`oid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orderitem
-- ----------------------------
INSERT INTO `orderitem` VALUES ('0150807843684dfcb6ab4ed4beec185a', '1', '28.00', '002', '7e44dd10594844c6b0e8ff837351de8e');
INSERT INTO `orderitem` VALUES ('2470327f1cc142119817de934ddef98f', '2', '146.00', '008', 'ebafb660c5e4475194e8c3675b08a680');
INSERT INTO `orderitem` VALUES ('2505a2899f834129bdef77053577d139', '1', '25.00', '001', '7517c66655e7445484b07e23ca652b37');
INSERT INTO `orderitem` VALUES ('311abd55ecbd4449aa14ecde4fabf6d3', '1', '25.00', '001', '28a708dd390b416483a70e1d412bb129');
INSERT INTO `orderitem` VALUES ('491dd1107225431fa4030230954f75cd', '1', '25.00', '001', 'faa0ae62eb754b5c8071c174ec54fbf5');
INSERT INTO `orderitem` VALUES ('64a5efa31f1f45b8a20edf8bc151e1cc', '1', '28.00', '002', '58e0704bcc4d43cfa8875d86b6d94907');
INSERT INTO `orderitem` VALUES ('7ac07fecfb094799a449a497b52a6ab4', '1', '73.00', '005', '67d5c2a30024412e8b10b041054e72e2');
INSERT INTO `orderitem` VALUES ('8af6c0e671c841879487f6142dabc4c3', '4', '128.00', '013', '60b1ba2cd17a4e2ba23ebbf4ae7da3c1');
INSERT INTO `orderitem` VALUES ('94d56482bfaa4e7b854fab99207c9b8f', '2', '146.00', '006', '74872d7e8fc5447d950fdcd9ec93cfde');
INSERT INTO `orderitem` VALUES ('9570704d85ff4f22bed66f1d64b5f859', '1', '73.00', '008', '73b975b2c8a247a8851ebe34618d1a5d');
INSERT INTO `orderitem` VALUES ('95fdada311634320b1c4dfad5bb64c8b', '4', '128.00', '013', 'ebafb660c5e4475194e8c3675b08a680');
INSERT INTO `orderitem` VALUES ('961d6a1dba8c4e40b2232be1f53ac678', '1', '28.00', '002', '344ba8eb70a443a88d62a778bae56ded');
INSERT INTO `orderitem` VALUES ('9802fb3dbf154cdaa18cb4553574c79e', '1', '73.00', '007', '1deb013c95494129ad0b0aafca6757f4');
INSERT INTO `orderitem` VALUES ('9ab5668467b246a6a4a02fbcb344c9b1', '2', '146.00', '006', '28a708dd390b416483a70e1d412bb129');
INSERT INTO `orderitem` VALUES ('9fc9425eb6714de2af01bb6edf25252b', '1', '25.00', '001', 'dc4312bb09154bb4941e7f5a3b71afea');
INSERT INTO `orderitem` VALUES ('a97adeb792ac4fa393814920c8fa42e9', '1', '73.00', '008', '60b1ba2cd17a4e2ba23ebbf4ae7da3c1');
INSERT INTO `orderitem` VALUES ('aa210c29a3104acb8b02a080d60f9e9a', '1', '28.00', '002', '0339e9781f754dc99d16590b9e33eef0');
INSERT INTO `orderitem` VALUES ('ad311bed711f4f17813db853070b0461', '4', '128.00', '013', '73b975b2c8a247a8851ebe34618d1a5d');
INSERT INTO `orderitem` VALUES ('ae7fa2117b634b57b5578faa1066fe24', '1', '25.00', '001', '13dfc8f1935c4c008c0674f565c1c2b3');
INSERT INTO `orderitem` VALUES ('dc80b586354746e8873cd448460b134f', '1', '50.00', '001', '74872d7e8fc5447d950fdcd9ec93cfde');
INSERT INTO `orderitem` VALUES ('deb39a35dda1465bbf921bf9f5f19132', '1', '50.00', '001', '93081ab30d3347609d58db688da3735a');
INSERT INTO `orderitem` VALUES ('e8c26d1f273a470a9a23cc145f49bbdb', '1', '67.00', '010', 'e306b2a40f1647368a5accf2e1f80f06');
INSERT INTO `orderitem` VALUES ('faed882b5c404a48bf49192c37ffd321', '2', '122.00', '004', '15f93d4d00954478929b2878152bb2d4');
INSERT INTO `orderitem` VALUES ('ffe974ac754142769904902a3cb38fa0', '1', '61.00', '004', 'd1cff4006dfb4bfda71e90ede995efed');

-- ----------------------------
-- Table structure for `orders`
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `oid` char(32) NOT NULL,
  `total` decimal(10,2) DEFAULT NULL,
  `ordertime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `state` int(11) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `uid` char(32) DEFAULT NULL,
  PRIMARY KEY (`oid`),
  KEY `uid` (`uid`),
  CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES ('0339e9781f754dc99d16590b9e33eef0', '28.00', '2020-03-15 17:43:09', '1', '北京市海淀区金燕龙大厦2楼216室无敌收', 'e22e7f3f5b2c44f4877b0e1e9af8ff3b');
INSERT INTO `orders` VALUES ('13dfc8f1935c4c008c0674f565c1c2b3', '25.00', '2020-03-15 17:27:09', '1', '北京市海淀区金燕龙大厦2楼216室无敌收', 'e22e7f3f5b2c44f4877b0e1e9af8ff3b');
INSERT INTO `orders` VALUES ('15f93d4d00954478929b2878152bb2d4', '122.00', '2020-03-15 17:41:13', '1', '北京市海淀区金燕龙大厦2楼216室无敌收', 'e22e7f3f5b2c44f4877b0e1e9af8ff3b');
INSERT INTO `orders` VALUES ('1deb013c95494129ad0b0aafca6757f4', '73.00', '2018-10-16 09:08:25', '1', '江西省南昌市红谷滩新区金燕龙大厦2楼216室无敌收', 'e22e7f3f5b2c44f4877b0e1e9af8ff3b');
INSERT INTO `orders` VALUES ('28a708dd390b416483a70e1d412bb129', '171.00', '2020-03-14 11:17:36', '1', null, 'e22e7f3f5b2c44f4877b0e1e9af8ff3b');
INSERT INTO `orders` VALUES ('344ba8eb70a443a88d62a778bae56ded', '28.00', '2020-03-15 22:17:57', '1', null, 'e22e7f3f5b2c44f4877b0e1e9af8ff3b');
INSERT INTO `orders` VALUES ('58e0704bcc4d43cfa8875d86b6d94907', '28.00', '2020-03-15 22:16:13', '1', null, 'e22e7f3f5b2c44f4877b0e1e9af8ff3b');
INSERT INTO `orders` VALUES ('60b1ba2cd17a4e2ba23ebbf4ae7da3c1', '201.00', '2020-03-14 15:34:29', '1', null, 'e22e7f3f5b2c44f4877b0e1e9af8ff3b');
INSERT INTO `orders` VALUES ('67d5c2a30024412e8b10b041054e72e2', '73.00', '2018-10-16 09:17:19', '1', '江西省南昌市红谷滩新区金燕龙大厦2楼216室无敌收', 'e22e7f3f5b2c44f4877b0e1e9af8ff3b');
INSERT INTO `orders` VALUES ('73b975b2c8a247a8851ebe34618d1a5d', '201.00', '2020-03-14 15:36:24', '1', null, 'e22e7f3f5b2c44f4877b0e1e9af8ff3b');
INSERT INTO `orders` VALUES ('74872d7e8fc5447d950fdcd9ec93cfde', '196.00', '2018-10-16 09:05:59', '1', '江西省南昌市红谷滩新区金燕龙大厦2楼216室无敌收', 'e22e7f3f5b2c44f4877b0e1e9af8ff3b');
INSERT INTO `orders` VALUES ('7517c66655e7445484b07e23ca652b37', '25.00', '2020-03-15 14:23:16', '1', null, 'e22e7f3f5b2c44f4877b0e1e9af8ff3b');
INSERT INTO `orders` VALUES ('7e44dd10594844c6b0e8ff837351de8e', '28.00', '2018-10-15 21:13:56', '1', null, 'e22e7f3f5b2c44f4877b0e1e9af8ff3b');
INSERT INTO `orders` VALUES ('93081ab30d3347609d58db688da3735a', '50.00', '2018-10-15 21:13:56', '1', null, 'e22e7f3f5b2c44f4877b0e1e9af8ff3b');
INSERT INTO `orders` VALUES ('d1cff4006dfb4bfda71e90ede995efed', '61.00', '2018-10-15 21:13:56', '1', null, 'e22e7f3f5b2c44f4877b0e1e9af8ff3b');
INSERT INTO `orders` VALUES ('dc4312bb09154bb4941e7f5a3b71afea', '25.00', '2020-03-15 14:54:28', '1', null, 'e22e7f3f5b2c44f4877b0e1e9af8ff3b');
INSERT INTO `orders` VALUES ('e306b2a40f1647368a5accf2e1f80f06', '67.00', '2018-10-15 21:14:40', '1', null, 'e22e7f3f5b2c44f4877b0e1e9af8ff3b');
INSERT INTO `orders` VALUES ('ebafb660c5e4475194e8c3675b08a680', '274.00', '2018-10-15 21:13:56', '1', null, 'e22e7f3f5b2c44f4877b0e1e9af8ff3b');
INSERT INTO `orders` VALUES ('faa0ae62eb754b5c8071c174ec54fbf5', '25.00', '2020-03-15 22:16:52', '1', null, 'e22e7f3f5b2c44f4877b0e1e9af8ff3b');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `uid` char(32) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `state` tinyint(1) DEFAULT NULL,
  `code` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`uid`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('80a0b76206da483a8be7720a59bf541b', 'ccc', '123', 'ccc@estore.com', '0', 'ca85783edc8a4b2fa12aee553cd5e095732156d08e824e969830c05902e6039e');
INSERT INTO `user` VALUES ('8590098a0db64d7d85fcf65f4d935f56', 'aaaa', '123', 'aaaa@estore.com', '1', 'dae63bc655d1423b81281a22c4e188b54ba0e774d1544bd1ac2fd442b3e931da');
INSERT INTO `user` VALUES ('95b8918336f94638b59a0736588ee3fc', 'bbb', '123', 'bbb@estore.com', '0', '53857fab542d415d972075358276e9a6941d2bb332ca47418eccc1a1b580e531');
INSERT INTO `user` VALUES ('e22e7f3f5b2c44f4877b0e1e9af8ff3b', 'aaa', '123', 'aaa@estore.com', '0', '4beb8da02f324b51972364d98ee8d1c56610a1186d374dc1bb6f87166fab6599');
