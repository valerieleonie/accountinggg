-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: akuntansi
-- ------------------------------------------------------
-- Server version	5.7.17-log

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
-- Table structure for table `trialbalance`
--

DROP TABLE IF EXISTS `trialbalance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `trialbalance` (
  `chartno` int(11) DEFAULT NULL,
  `opening` int(11) DEFAULT NULL,
  `ending` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trialbalance`
--

LOCK TABLES `trialbalance` WRITE;
/*!40000 ALTER TABLE `trialbalance` DISABLE KEYS */;
INSERT INTO `trialbalance` VALUES (1000,0,0),(1010,0,0),(1020,0,0),(1030,0,0),(1040,0,0),(1050,0,0),(1060,0,0),(1061,0,0),(1070,0,0),(1080,0,0),(1100,0,0),(1110,0,0),(1120,0,0),(1130,0,0),(1140,0,0),(2000,0,0),(2010,0,0),(2020,0,0),(2030,0,0),(2100,0,0),(2110,0,0),(3000,0,0),(3010,0,0),(3020,0,0),(3030,0,0),(3040,0,0),(4000,0,0),(4010,0,0),(4020,0,0),(4030,0,0),(5000,0,0),(5110,0,0),(5120,0,0),(6000,0,0),(6010,0,0),(6011,0,0),(6012,0,0),(6013,0,0),(6020,0,0),(6030,0,0),(6031,0,0),(7000,0,0);
/*!40000 ALTER TABLE `trialbalance` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-07-24 17:04:19
