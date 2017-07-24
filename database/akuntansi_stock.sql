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
-- Table structure for table `stock`
--

DROP TABLE IF EXISTS `stock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stock` (
  `kode_produk` int(4) NOT NULL AUTO_INCREMENT,
  `nama_produk` varchar(100) DEFAULT NULL,
  `kategori` varchar(100) DEFAULT NULL,
  `jumlahpembelian` int(11) DEFAULT NULL,
  `harga_beli` double DEFAULT NULL,
  `harga_jual` double DEFAULT NULL,
  `jumlahpenjualan` int(11) DEFAULT NULL,
  `stock` int(11) DEFAULT NULL,
  `stock_awal` int(11) DEFAULT NULL,
  PRIMARY KEY (`kode_produk`)
) ENGINE=InnoDB AUTO_INCREMENT=1031 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stock`
--

LOCK TABLES `stock` WRITE;
/*!40000 ALTER TABLE `stock` DISABLE KEYS */;
INSERT INTO `stock` VALUES (1001,'Buku Tulis Kiky 38','Alat Tulis',0,45000,47600,0,0,0),(1002,'Buku Tulis Kiky 58','Alat Tulis',0,65000,67200,0,0,0),(1003,'Buku Tulis Kiky 40','Alat Tulis',0,90000,91600,0,0,0),(1004,'Buku Tulis Kiky 50','Alat Tulis',0,102000,105000,0,0,0),(1005,'Buku Tulis VOS Moopi Kuarto 38','Alat Tulis',0,40000,43800,0,0,0),(1006,'Pentel Pel 105','Alat Tulis',0,20000,21100,0,0,0),(1007,'Pentel Pen 105s','Alat Tulis',0,20000,21000,0,0,0),(1008,'S Sharpie Pen UPC Twin Black','Alat Tulis',0,20000,23000,0,0,0),(1009,'Pilot Pen Hi-Tec 0.3','Alat Tulis',0,25000,27300,0,0,0),(1010,'Snowman Pen 0.2MM','Alat Tulis',0,9500,10600,0,0,0),(1011,'00:00 (Ardelia Karisa)','Buku',0,32500,42500,0,0,0),(1012,'Love in Sydney(Arumi E)','Buku',0,49300,58000,0,0,0),(1013,'Breakfast at Tiffanys (Truman Capote)','Buku',0,29700,35000,0,0,0),(1014,'The Lost Boy (Pelzer)','Buku',0,46700,55000,0,0,0),(1015,'Artemis Fowl (Colfer)','Buku',0,34000,40000,0,0,0);
/*!40000 ALTER TABLE `stock` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-07-24 17:04:21
