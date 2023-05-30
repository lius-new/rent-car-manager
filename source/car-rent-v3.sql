-- MySQL dump 10.13  Distrib 5.7.42, for Linux (x86_64)
--
-- Host: localhost    Database: car-rent
-- ------------------------------------------------------
-- Server version	5.7.42

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `car`
--

DROP TABLE IF EXISTS `car`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `car` (
  `id` int(32) NOT NULL AUTO_INCREMENT COMMENT '车辆id',
  `car_name` varchar(32) NOT NULL COMMENT '车辆名称',
  `car_user_id` int(32) NOT NULL COMMENT '车主id',
  `car_desc` varchar(32) DEFAULT NULL COMMENT '车辆概括',
  `car_content` varchar(32) DEFAULT NULL COMMENT '车辆详情',
  `car_status` int(1) DEFAULT '0' COMMENT '车辆状态(0: 可用, 1: 不可用,2: 审核状态)',
  `rent_id` int(11) DEFAULT NULL COMMENT '租借的id',
  PRIMARY KEY (`id`),
  KEY `fk_car_user` (`car_user_id`),
  CONSTRAINT `fk_car_user` FOREIGN KEY (`car_user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `car`
--

LOCK TABLES `car` WRITE;
/*!40000 ALTER TABLE `car` DISABLE KEYS */;
INSERT INTO `car` VALUES (2,'梅赛德斯-迈巴赫 ',4,'歪新人,飞船哈哈哈',NULL,0,NULL),(3,'宝马 M760i',4,'关于坐骑,日骑千里',NULL,0,NULL),(4,'劳斯莱斯',7,'劳动所得',NULL,2,NULL),(5,'宾利',7,'冰刀利车,雪地专用',NULL,0,NULL),(6,'奥迪R8',8,'校长的架坐',NULL,0,NULL),(7,'马自达',19,'我开马自达,你开什么车来的',NULL,0,17),(12,'hello',5,NULL,NULL,2,NULL);
/*!40000 ALTER TABLE `car` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rent`
--

DROP TABLE IF EXISTS `rent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rent` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '租借的id',
  `rent_user_id` int(11) NOT NULL COMMENT '租借的用户id',
  `rent_car_id` int(11) DEFAULT NULL COMMENT '租借的车辆id',
  `rent_start_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '租借开始时间',
  `rent_end_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '租借结束时间',
  PRIMARY KEY (`id`),
  KEY `rent_car_id_key` (`rent_car_id`),
  KEY `rent_user_id_key` (`rent_user_id`),
  CONSTRAINT `rent_car_id_key` FOREIGN KEY (`rent_car_id`) REFERENCES `car` (`id`),
  CONSTRAINT `rent_user_id_key` FOREIGN KEY (`rent_user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8 COMMENT='车辆租借表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rent`
--

LOCK TABLES `rent` WRITE;
/*!40000 ALTER TABLE `rent` DISABLE KEYS */;
INSERT INTO `rent` VALUES (13,4,4,'2023-05-29 13:46:49','2023-05-30 13:46:49'),(15,4,5,'2023-05-29 13:46:49','2023-05-30 13:46:49'),(16,4,6,'2023-06-29 13:46:49','2023-07-30 13:46:49'),(17,17,7,'2023-05-30 13:21:12','2023-05-31 13:21:12'),(20,7,6,'2023-05-30 17:06:54','2023-05-31 17:06:59');
/*!40000 ALTER TABLE `rent` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(32) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `user_name` varchar(32) NOT NULL COMMENT '用户名',
  `user_password` varchar(32) NOT NULL COMMENT '用户密码',
  `user_email` varchar(32) DEFAULT NULL COMMENT '用户邮箱',
  `user_role` varchar(32) NOT NULL DEFAULT 'USER' COMMENT '用于权限(ADMIN/USER/VIP)',
  `user_status` int(1) NOT NULL DEFAULT '0' COMMENT '用户状态(0:可以使用,1暂停使用)',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_name` (`user_name`),
  UNIQUE KEY `user_email` (`user_email`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (4,'admin','this',NULL,'ADMIN',0),(5,'admin1','admin1',NULL,'USER',0),(6,'admin2','admin2',NULL,'VIP',1),(7,'admin3','admin3',NULL,'VIP',0),(8,'admin4','admin4',NULL,'USER',0),(9,'admin5','admin5',NULL,'USER',0),(10,'admin6','admin6',NULL,'USER',0),(11,'admin7','admin7',NULL,'USER',0),(12,'admin8','admin8',NULL,'USER',0),(17,'hello5','hello5',NULL,'USER',0),(18,'hello6','hello6',NULL,'USER',0),(19,'徐博文','sb',NULL,'USER',0),(20,'张三','张三',NULL,'USER',0);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-05-30 22:46:53
