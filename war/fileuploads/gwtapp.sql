-- MySQL dump 10.13  Distrib 5.6.21, for Win64 (x86_64)
--
-- Host: localhost    Database: gwtapp
-- ------------------------------------------------------
-- Server version	5.1.31-community

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
-- Table structure for table `beanobjects`
--

DROP TABLE IF EXISTS `beanobjects`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `beanobjects` (
  `beanId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `beanName` varchar(45) NOT NULL DEFAULT '',
  `beanObject` blob NOT NULL,
  `beanType` char(1) NOT NULL DEFAULT '',
  PRIMARY KEY (`beanId`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `beanobjects`
--

LOCK TABLES `beanobjects` WRITE;
/*!40000 ALTER TABLE `beanobjects` DISABLE KEYS */;
INSERT INTO `beanobjects` VALUES (9,'com.halcyonpro.gwtapp.shared.dto.DummyBean','??\0sr\0(com.halcyonpro.gwtapp.shared.dto.DummyBean?pM??P?\0I\0idZ\0\rnotificationsL\0addresst\0Ljava/lang/String;L\0genderq\0~\0L\0nameq\0~\0xp\0\0\0t\0Daat\0Mt\0jjh','S');
/*!40000 ALTER TABLE `beanobjects` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dummybean`
--

DROP TABLE IF EXISTS `dummybean`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dummybean` (
  `beanId` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `beanType` char(1) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `id` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`beanId`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dummybean`
--

LOCK TABLES `dummybean` WRITE;
/*!40000 ALTER TABLE `dummybean` DISABLE KEYS */;
INSERT INTO `dummybean` VALUES (551,'daa','D','F',1,'jk');
/*!40000 ALTER TABLE `dummybean` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `globalprefrences`
--

DROP TABLE IF EXISTS `globalprefrences`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `globalprefrences` (
  `globalPrefrencesId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `myAccountPreferencesId` int(10) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`globalPrefrencesId`),
  KEY `FK_f2k6sbevlkuyylg7ijua4c4c5` (`myAccountPreferencesId`),
  CONSTRAINT `FK_f2k6sbevlkuyylg7ijua4c4c5` FOREIGN KEY (`myAccountPreferencesId`) REFERENCES `myaccountpreferences` (`myAccountPreferencesId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `globalprefrences`
--

LOCK TABLES `globalprefrences` WRITE;
/*!40000 ALTER TABLE `globalprefrences` DISABLE KEYS */;
INSERT INTO `globalprefrences` VALUES (1,1);
/*!40000 ALTER TABLE `globalprefrences` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `myaccount`
--

DROP TABLE IF EXISTS `myaccount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `myaccount` (
  `myAccountId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `monthlyPaymentPlan` int(1) unsigned NOT NULL DEFAULT '0',
  `quarterlyPaymentPlan` int(1) unsigned NOT NULL DEFAULT '0',
  `yearlyPaymentPlan` int(1) unsigned NOT NULL DEFAULT '0',
  `registeredTo` varchar(45) NOT NULL DEFAULT '',
  `lastEdited` datetime DEFAULT NULL,
  PRIMARY KEY (`myAccountId`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `myaccount`
--

LOCK TABLES `myaccount` WRITE;
/*!40000 ALTER TABLE `myaccount` DISABLE KEYS */;
INSERT INTO `myaccount` VALUES (1,0,1,0,'junaid','2015-10-30 10:59:29'),(2,0,0,0,'u','2015-10-27 17:22:27'),(3,0,1,0,'y','2015-10-27 17:22:27'),(8,0,0,0,'y','2015-10-27 17:22:27'),(17,0,0,1,'y','2015-10-30 11:00:14');
/*!40000 ALTER TABLE `myaccount` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `myaccountpreferences`
--

DROP TABLE IF EXISTS `myaccountpreferences`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `myaccountpreferences` (
  `myAccountPreferencesId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `showPlanType` int(1) unsigned NOT NULL DEFAULT '0',
  `showPaymentDetails` int(1) unsigned NOT NULL DEFAULT '0',
  `viewPlanPaymentTerms` int(1) unsigned NOT NULL DEFAULT '0',
  `viewPlanMonthlyPayments` int(1) unsigned NOT NULL DEFAULT '0',
  `viewPlanQuarterlyPayments` int(1) unsigned NOT NULL DEFAULT '0',
  `viewPlanYearlyPayments` int(1) unsigned NOT NULL DEFAULT '0',
  `viewRegUserName` int(1) unsigned NOT NULL DEFAULT '0',
  `viewRegName` int(1) unsigned NOT NULL DEFAULT '0',
  `viewRegShowPanel` int(1) unsigned NOT NULL DEFAULT '0',
  `viewRegRegisteredTo` int(1) unsigned NOT NULL DEFAULT '0',
  `viewRegLastEdited` int(1) unsigned NOT NULL DEFAULT '0',
  `viewRegEmail` int(1) unsigned NOT NULL DEFAULT '0',
  `editRegUserName` int(1) unsigned NOT NULL DEFAULT '0',
  `editRegName` int(1) unsigned NOT NULL DEFAULT '0',
  `editRegShowPanel` int(1) unsigned NOT NULL DEFAULT '0',
  `editRegRegisteredTo` int(1) unsigned NOT NULL DEFAULT '0',
  `editRegEmail` int(1) unsigned NOT NULL DEFAULT '0',
  `editRegPassword` int(1) unsigned NOT NULL DEFAULT '0',
  `viewPlanShowPanel` int(1) unsigned NOT NULL DEFAULT '0',
  `editRegCloseAccount` int(1) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`myAccountPreferencesId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `myaccountpreferences`
--

LOCK TABLES `myaccountpreferences` WRITE;
/*!40000 ALTER TABLE `myaccountpreferences` DISABLE KEYS */;
INSERT INTO `myaccountpreferences` VALUES (1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1);
/*!40000 ALTER TABLE `myaccountpreferences` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sequence`
--

DROP TABLE IF EXISTS `sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sequence` (
  `SEQ_NAME` varchar(50) NOT NULL,
  `SEQ_COUNT` decimal(38,0) DEFAULT NULL,
  PRIMARY KEY (`SEQ_NAME`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sequence`
--

LOCK TABLES `sequence` WRITE;
/*!40000 ALTER TABLE `sequence` DISABLE KEYS */;
INSERT INTO `sequence` VALUES ('SEQ_GEN',0),('SEQ_GEN_IDENTITY',600);
/*!40000 ALTER TABLE `sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `userId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL DEFAULT '0',
  `password` varchar(200) NOT NULL DEFAULT '0',
  `email` varchar(45) NOT NULL DEFAULT '0',
  `name` varchar(200) NOT NULL DEFAULT '0',
  `myAccountId` int(11) NOT NULL DEFAULT '0',
  `isAdmin` int(1) unsigned NOT NULL DEFAULT '0',
  `status` int(1) unsigned NOT NULL DEFAULT '0',
  `token` varchar(200) DEFAULT NULL,
  `tokenGeneratedDate` datetime DEFAULT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (8,'user1','$2a$12$yv2RmsaYmTExxoNJUqAp8.CYYLn1.29F/H/6ftf5fNPU.eX01oBM2','user1@email.com','user1',1,0,1,'$2a$12$yv2RmsaYmTExxoNJUqAp8.CYYLn1.29F/H/6ftf5fNPU.eX01oBM2','2015-10-30 09:16:18'),(9,'admin','$2a$12$7JqQKRDFotpPie21VE.VPeuZU1bYTrAFjiJMuTFzla4oBZHhLKMmi','admin@email.com','admin',2,1,1,'@@sss','2015-10-27 16:16:03'),(10,'user2','$2a$12$YkypXaBpO1.hvYMeeM6cDOxLEzw1t4gqjGxlfImoPXnaxIdqzNGsC','user2@email.com','user2',3,0,0,'@@aaa','2006-03-07 16:29:30'),(11,'bcry','editedpwd','junaidp@gmail.com','bcry',8,0,1,'editedpwd','2015-10-27 15:33:00'),(12,'user3','$2a$12$M2MYb/m.zoP72XhMvKV6pO.vatTMYyyc1e7gbnEHqi53sqqYsLd1m','junaidp@gmail.com','User 3 testings',17,0,1,'$2a$12$M2MYb/m.zoP72XhMvKV6pO.vatTMYyyc1e7gbnEHqi53sqqYsLd1m','2015-10-27 16:11:23');
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

-- Dump completed on 2016-02-19  8:58:53