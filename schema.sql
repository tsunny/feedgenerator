-- MySQL dump 10.13  Distrib 5.6.26, for osx10.8 (x86_64)
--
-- Host: localhost    Database: fg
-- ------------------------------------------------------
-- Server version	5.6.26

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
-- Table structure for table `CommonBaseClass`
--

DROP TABLE IF EXISTS `CommonBaseClass`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CommonBaseClass` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdBy` varchar(255) DEFAULT NULL,
  `guid` varchar(255) DEFAULT NULL,
  `updatedBy` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_njjgk8qjompueqtvpl8u5hlnf` (`guid`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Topic`
--

DROP TABLE IF EXISTS `Topic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Topic` (
  `lowerCasedName` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_81868hw8bd2f5voe44ghl96kf` (`name`),
  CONSTRAINT `FK_lg56ocwsyi1wm2lehim6nlhva` FOREIGN KEY (`id`) REFERENCES `CommonBaseClass` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Topic_Topic`
--

DROP TABLE IF EXISTS `Topic_Topic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Topic_Topic` (
  `Topic_id` bigint(20) NOT NULL,
  `concepts_id` bigint(20) NOT NULL,
  UNIQUE KEY `UK_j2bnmmfe2mm2tkysjdalgpyts` (`concepts_id`),
  KEY `FK_qrqtksjg1dpj6qa8sormwtjqb` (`Topic_id`),
  CONSTRAINT `FK_j2bnmmfe2mm2tkysjdalgpyts` FOREIGN KEY (`concepts_id`) REFERENCES `Topic` (`id`),
  CONSTRAINT `FK_qrqtksjg1dpj6qa8sormwtjqb` FOREIGN KEY (`Topic_id`) REFERENCES `Topic` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `User`
--

DROP TABLE IF EXISTS `User`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `User` (
  `email` varchar(255) DEFAULT NULL,
  `firstName` varchar(255) DEFAULT NULL,
  `lastName` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_omjl8ijwi58yx57d9ejn4c0jt` FOREIGN KEY (`id`) REFERENCES `CommonBaseClass` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `UserInterestMap`
--

DROP TABLE IF EXISTS `UserInterestMap`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `UserInterestMap` (
  `weight` float NOT NULL,
  `id` bigint(20) NOT NULL,
  `topic_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_ee1030ppoxai5647e96v7dmhx` (`topic_id`),
  KEY `FK_9kck3pbgsns7djoyutle89mqq` (`user_id`),
  CONSTRAINT `FK_9kck3pbgsns7djoyutle89mqq` FOREIGN KEY (`user_id`) REFERENCES `User` (`id`),
  CONSTRAINT `FK_ee1030ppoxai5647e96v7dmhx` FOREIGN KEY (`topic_id`) REFERENCES `Topic` (`id`),
  CONSTRAINT `FK_ivd6l61qyek9ia6p1s6h5vu82` FOREIGN KEY (`id`) REFERENCES `CommonBaseClass` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `UserRole`
--

DROP TABLE IF EXISTS `UserRole`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `UserRole` (
  `name` varchar(255) DEFAULT NULL,
  `id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_tp99f11id2fupsot9ouugqysw` (`name`),
  CONSTRAINT `FK_ti955wqtrvorykystva5rabpj` FOREIGN KEY (`id`) REFERENCES `CommonBaseClass` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `User_UserRole`
--

DROP TABLE IF EXISTS `User_UserRole`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `User_UserRole` (
  `User_id` bigint(20) NOT NULL,
  `userRoles_id` bigint(20) NOT NULL,
  KEY `FK_fxws3ckb9g6tf2i8s3lp8oi0b` (`userRoles_id`),
  KEY `FK_ao87e542n1itog5ha09bn7syt` (`User_id`),
  CONSTRAINT `FK_ao87e542n1itog5ha09bn7syt` FOREIGN KEY (`User_id`) REFERENCES `User` (`id`),
  CONSTRAINT `FK_fxws3ckb9g6tf2i8s3lp8oi0b` FOREIGN KEY (`userRoles_id`) REFERENCES `UserRole` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-01-20  4:23:29
