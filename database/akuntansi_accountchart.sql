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
-- Table structure for table `accountchart`
--

DROP TABLE IF EXISTS `accountchart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `accountchart` (
  `chartno` int(4) NOT NULL,
  `chartname` varchar(100) NOT NULL,
  PRIMARY KEY (`chartno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accountchart`
--

LOCK TABLES `accountchart` WRITE;
/*!40000 ALTER TABLE `accountchart` DISABLE KEYS */;
INSERT INTO `accountchart` VALUES (1000,'Asset'),(1010,'Cash'),(1020,'Bank'),(1030,'Account Recieveable'),(1040,'Inventory'),(1050,'Tax Out'),(1060,'Cost in Advances'),(1061,'Insurance in Advances'),(1070,'Giro Recieveable'),(1080,'Tax In'),(1100,'Fixed Asset'),(1110,'Vehicle'),(1120,'Building and Land'),(1130,'Machine'),(1140,'Computer'),(2000,'Liability'),(2010,'Bank Credit'),(2020,'Account Payable'),(2030,'Giro Payable'),(2100,'Long Term Liability'),(2110,'Long Term Loan'),(3000,'Capital'),(3010,'Owner Investment'),(3020,'Devidend'),(3030,'Privet'),(3040,'Retained Earning'),(4000,'Sales'),(4010,'Sales Income'),(4020,'Sales Discount'),(4030,'Sales Return'),(5000,'Cost of Good Sold'),(5110,'Purchasing'),(5120,'Purchasing Disc'),(6000,'Operational Expenditure'),(6010,'General Expenditure'),(6011,'Insurance Expenses'),(6012,'Computer Expenses'),(6013,'Vehicle Expenses'),(6020,'Marketing Expenditure'),(6030,'Human Resource Expenditure'),(6031,'Salary Expenses'),(7000,'Others');
/*!40000 ALTER TABLE `accountchart` ENABLE KEYS */;
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
