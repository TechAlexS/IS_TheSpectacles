CREATE DATABASE  IF NOT EXISTS `ecommerce` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `ecommerce`;
-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: ecommerce
-- ------------------------------------------------------
-- Server version	8.0.29

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `indirizzi`
--

DROP TABLE IF EXISTS `indirizzi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `indirizzi` (
  `idIndirizzo` int NOT NULL AUTO_INCREMENT,
  `indirizzo` varchar(70) NOT NULL,
  `attivo` tinyint(1) NOT NULL,
  `citta` varchar(45) NOT NULL,
  `provincia` varchar(2) NOT NULL,
  `cap` int NOT NULL,
  `email` varchar(45) NOT NULL,
  `telefono` varchar(45) NOT NULL,
  `nome` varchar(45) NOT NULL,
  `cognome` varchar(45) NOT NULL,
  PRIMARY KEY (`idIndirizzo`),
  KEY `email` (`email`),
  CONSTRAINT `indirizzi_ibfk_1` FOREIGN KEY (`email`) REFERENCES `utente` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `indirizzi`
--

LOCK TABLES `indirizzi` WRITE;
/*!40000 ALTER TABLE `indirizzi` DISABLE KEYS */;
INSERT INTO `indirizzi` VALUES (1,'via roma n.53',1,'Ischia','NA',80100,'a.satta2@gmail.com','','',''),(2,'via mattei n.1',1,'Pompei','NA',80040,'m.rossi@gmail.com','','',''),(3,'via toledo n.25',1,'Caserta','CE',81100,'f.bianchi@gmail.com','','',''),(4,'via  marco polo n.10',0,'Milano','MI',83900,'a.satta2@gmail.com','','',''),(5,'Via Mazzini 4',1,'Caserta','CE',81100,'g@gmail.com','','',''),(6,'Via le mani dal Naso 2',1,'Pollenatrocchia','NA',82590,'g@gmail.com','','',''),(7,'Via Napoli 3',1,'Milano','MI',81100,'m@gmail.com','','',''),(8,'Via Savona 34',1,'Milazzo','MI',86890,'m@gmail.com','','',''),(9,'Via Roma',1,'NApoli','NA',81100,'g@gmail.com','','',''),(10,'via del carrello n.1',0,'Napoli','Na',45889,'pas@gmail.com','1256854756','',''),(11,'via italia n.10',0,'Roma','RM',81126,'pas@gmail.com','1256985632','',''),(12,'via napoli n.9',0,'Napoli','NA',81282,'alex@gmail.com','3698562145','',''),(13,'via italia',0,'Caserta','CE',81100,'alex@gmail.com','0123658962','',''),(14,'via ischia',0,'Ischia','NA',80072,'alex@gmail.com','1236586325','',''),(15,'via ischia',0,'','',81100,'alex@gmail.com','','',''),(16,'via vo',0,'fsa','CE',81100,'alex@gmail.com','4569856325','',''),(17,'Via afìlfredo',0,'Casera','CE',81100,'alex@gmail.com','1256983659','Mario','Rossi'),(18,'via angelo',0,'Caserta','CE',81100,'alex@gmail.com','125698563258','Alex','Satta');
/*!40000 ALTER TABLE `indirizzi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `occhiale`
--

DROP TABLE IF EXISTS `occhiale`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `occhiale` (
  `idOcchiale` varchar(45) NOT NULL,
  `nomeOcchiale` varchar(30) NOT NULL,
  `marca` varchar(30) NOT NULL,
  `prezzo` int NOT NULL,
  `disponibilita` int NOT NULL,
  `tipo` varchar(1) NOT NULL,
  `colore` varchar(30) NOT NULL,
  `categoria` varchar(30) NOT NULL,
  `img` varchar(45) NOT NULL,
  `img2` varchar(45) NOT NULL,
  `descrizione` varchar(500) NOT NULL,
  PRIMARY KEY (`idOcchiale`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `occhiale`
--

LOCK TABLES `occhiale` WRITE;
/*!40000 ALTER TABLE `occhiale` DISABLE KEYS */;
INSERT INTO `occhiale` VALUES ('Bvlgari_BV6158B 278_13','LYNX','Bvlgari',330,9,'D','marrone/oro','sole','GV_Library_IBLU07_BB(1).jpg','GV_Library_IBLU07_BB(2).jpg','Tutte le regole possono essere infrante, modificate o reinventate nel mondo delle montature, proprio come nella vita.'),('Emporio_Armani_0EA4152_56691W','RIM','Emporio Armani',130,9,'U','blu','vista','Emporio_Armani_0EA4152_56691W(1).jpg','Emporio_Armani_0EA4152_56691W(2).jpg','Montatura Emporio Armani. La democrazia della moda. Moderni, affascinanti e dallo spirito libero.'),('Gucci_GG0860S_2','VISION','Gucci',260,10,'D','nero','sole','Gucci_GG0860S_2(1).jpg','Gucci_GG0860S_2(2).jpg','Gucci GG0860S. Dagli yacht club alle strade eleganti, Gucci è in vetta allo stile del lusso e della moda da decenni. I suoi occhiali da sole sono una vera dichiarazione di stile grazie ai dettagli voluttuosi e alla qualità artigianale. Fino a dove potrai arrivare, con Gucci?'),('Gucci_GG0926S_1','CLASSIC STYLE','Gucci',230,10,'U','nero','sole','Gucci_GG0926S_1(1).jpg','Gucci_GG0926S_1(2).jpg','Dagli yacht club alle strade eleganti, Gucci è in vetta allo stile del lusso e della moda da decenni. I suoi occhiali da sole sono una vera dichiarazione di stile grazie ai dettagli voluttuosi e alla qualità artigianale. Fino a dove potrai arrivare, con Gucci?'),('GV_Library_ IBLU07_BB','RELAX EYE','GV Library',25,10,'U','nero','luce','GV_Library_IBLU07_BB(1).jpg','GV_Library_IBLU07_BB(2).jpg','Occhiali con protezione Luce Blu, senza gradazione. L’utilizzo di occhiali con lenti appositamente trattate è un prezioso accorgimento che possono adottare sia i portatori di occhiali da vista che coloro che non presentano difetti visivi. È consigliato ricorrere all’utilizzo di occhiali dotati di lenti appositamente trattate per respingere i raggi di Luce Blu in grado di proteggere l’occhio durante l’esposizione e da utilizzare durante la giornata lavorativa trascorsa al PC o guardando la TV.'),('Max_Mara_MM5003_28','FIANCÈ','Max Mara',180,8,'D','oro','vista','Max_Mara_MM5003_28(1).jpg','Max_Mara_MM5003_28(2).jpg','Max Mara incarna una femminilità espressiva e moderna caratterizzata da materiali di alta qualità con dettagli classici e sobri.'),('Montana_BLFBOX67_BB','SAFE EYE','Montana',20,7,'U','nero','luce','Montana_BLFBOX67_BB(1).jpg','Montana_BLFBOX67_BB(2).jpg','Occhiali con protezione Luce Blu, senza gradazione. L’utilizzo di occhiali con lenti appositamente trattate è un prezioso accorgimento che possono adottare sia i portatori di occhiali da vista che coloro che non presentano difetti visivi. È consigliato ricorrere all’utilizzo di occhiali dotati di lenti appositamente trattate per respingere i raggi di Luce Blu in grado di proteggere l’occhio durante l’esposizione e da utilizzare durante la giornata lavorativa trascorsa al PC o guardando la TV.'),('Montana_BLFBOX67F_RD','RED LIGHT','Montana',20,10,'D','rosso','luce','Montana_BLFBOX67F_RD(1).jpg','Montana_BLFBOX67F_RD(2).jpg','Occhiali con protezione Luce Blu, senza gradazione. L’utilizzo di occhiali con lenti appositamente trattate è un prezioso accorgimento che possono adottare sia i portatori di occhiali da vista che coloro che non presentano difetti visivi. È consigliato ricorrere all’utilizzo di occhiali dotati di lenti appositamente trattate per respingere i raggi di Luce Blu in grado di proteggere l’occhio durante l’esposizione e da utilizzare durante la giornata lavorativa trascorsa al PC o guardando la TV.'),('Montana_BLFBOX83D_VV','EYE PROTECTION','Montana',20,10,'D','rosa','luce','Montana_BLFBOX83D_VV(1).jpg','Montana_BLFBOX83D_VV(2).jpg','Occhiali con protezione Luce Blu, senza gradazione. L’utilizzo di occhiali con lenti appositamente trattate è un prezioso accorgimento che possono adottare sia i portatori di occhiali da vista che coloro che non presentano difetti visivi. È consigliato ricorrere all’utilizzo di occhiali dotati di lenti appositamente trattate per respingere i raggi di Luce Blu in grado di proteggere l’occhio durante l’esposizione e da utilizzare durante la giornata lavorativa trascorsa al PC o guardando la TV.'),('Ray-Ban_JUSTIN RB4165 622_T3','JUSTIN','Ray ban',165,10,'U','nero','sole','Ray-Ban_JUSTIN_RB4165_622_T3(1).jpg','Ray-Ban_JUSTIN_RB4165_622_T3(2).jpg','Autentici e senza tempo. Indossati dalle celebrità e dai personaggi pubblici, Ray-Ban rende disponibile in tutto il mondo il classico stile statunitense.'),('Ray-Ban_JUSTIN_RB4165 601_8G','JUSTIN','Ray ban',140,10,'U','nero','sole','Ray-Ban_JUSTIN_RB4165_601_8G(1).jpg','Ray-Ban_JUSTIN_RB4165_601_8G(2).jpg','Autentici e senza tempo. Indossati dalle celebrità e dai personaggi pubblici, Ray-Ban rende disponibile in tutto il mondo il classico stile statunitense.'),('Ray-Ban_JUSTIN_RB4165 622_55','JUSTIN','Ray ban',145,7,'U','verde','sole','Ray-Ban_JUSTIN_RB4165_622_55(1).jpg','Ray-Ban_JUSTIN_RB4165_622_55(2).jpg','Autentici e senza tempo. Indossati dalle celebrità e dai personaggi pubblici, Ray-Ban rende disponibile in tutto il mondo il classico stile statunitense.'),('Saint_Laurent_SL276_1','MICA','Saint Laurent',180,10,'D','nero','sole','Saint_Laurent_SL276_1(1).jpg','Saint_Laurent_SL276_1(2).jpg','Occhiali da sole Saint Laurent SL 276. Uno dei designer di moda più influenti del XX secolo, Saint Laurent è sinonimo di stile e libertà.'),('Tom_Ford_FT0764_01B','BOMB','Tom Ford',260,11,'U','nero','sole','Tom_Ford_FT0764_01B(1).jpg','Tom_Ford_FT0764_01B(2).jpg','Le collezioni Tom Ford di occhiali trasmettono lusso, glamour e attenzione ai particolari.'),('Tom_Ford_FT5737-B_52','AVIATOR','Tom Ford',240,5,'U','Tartarugato','vista','Tom_Ford_FT5737-B_52(1).jpg','Tom_Ford_FT5737-B_52(2).jpg','Le collezioni Tom Ford di occhiali trasmettono lusso, glamour e attenzione ai particolari.'),('Tom_Ford_FT5745-B_1','THICKNESS','Tom Ford',260,10,'D','nero','vista','Tom_Ford_FT5745-B_1(1).jpg','Tom_Ford_FT5745-B_1(2).jpg','Le collezioni Tom Ford di occhiali trasmettono lusso, glamour e attenzione ai particolari.'),('Versace_0VE4408_GB1_87','Prova','Versace',25,20,'D','nero','sole','Versace_0VE4408_GB1_87(1).jpg','Versace_0VE4408_GB1_87(2).jpg','ASD');
/*!40000 ALTER TABLE `occhiale` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `occhiale_ordine`
--

DROP TABLE IF EXISTS `occhiale_ordine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `occhiale_ordine` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_occhiale` varchar(45) NOT NULL,
  `id_ordine` varchar(45) NOT NULL,
  `prezzo_reale` int NOT NULL,
  `quantita` int NOT NULL,
  `iva` float NOT NULL,
  PRIMARY KEY (`id`),
  KEY `occhiale_ordine_fk2_idx` (`id_ordine`),
  CONSTRAINT `occhiale_ordine_fk1` FOREIGN KEY (`id_ordine`) REFERENCES `ordine` (`idOrdine`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `occhiale_ordine`
--

LOCK TABLES `occhiale_ordine` WRITE;
/*!40000 ALTER TABLE `occhiale_ordine` DISABLE KEYS */;
INSERT INTO `occhiale_ordine` VALUES (1,'Tom_Ford_FT0764_01B','d61fc843-a44a-49e4-909b-f190800e0e91',260,2,0),(2,'Tom_Ford_FT5745-B_1','d7dcc070-f1ac-4622-ad2a-83e9c80b436d',260,1,0),(3,'Ray-Ban_JUSTIN_RB4165 622_55','f1267d12-e857-4905-8e57-b8cd16c40cb5',145,1,0),(4,'Gucci_GG0926S_1','f1267d12-e857-4905-8e57-b8cd16c40cb5',230,1,0),(5,'Max_Mara_MM5003_28','e516fb70-2051-4403-a45a-685fb754698b',180,2,0),(6,'Ray-Ban_JUSTIN_RB4165 601_8G','31ed5d7e-d68c-416b-9146-a7f301184c56',140,1,0),(7,'Tom_Ford_FT5745-B_1','31ed5d7e-d68c-416b-9146-a7f301184c56',260,1,0),(8,'Tom_Ford_FT5745-B_1','12121cb7-8421-4a6e-9da6-8e7b53568c86',260,1,0),(9,'Tom_Ford_FT5745-B_1','3c6e43dd-ef5b-4dbc-8363-15a4b29ee6a8',260,1,0),(10,'Montana_BLFBOX67_BB','81ccc385-8ac7-4898-b9c6-ce4120108141',20,1,0),(11,'Tom_Ford_FT5745-B_1','a88874e4-80da-4170-b7e0-93f9514e4130',260,1,0),(12,'Ray-Ban_JUSTIN_RB4165 622_55','df7da2c5-93de-4a14-922b-9b502199bc36',145,1,0),(13,'Max_Mara_MM5003_28','df7da2c5-93de-4a14-922b-9b502199bc36',180,1,0),(14,'Montana_BLFBOX67_BB','ebde5717-3d95-4137-b432-95ab63b69ec4',20,1,0),(15,'Max_Mara_MM5003_28','ebde5717-3d95-4137-b432-95ab63b69ec4',180,1,0),(16,'Gucci_GG0926S_1','62329c99-cc66-46e1-a10b-2e77ac721a9f',230,1,0),(17,'Max_Mara_MM5003_28','62329c99-cc66-46e1-a10b-2e77ac721a9f',180,1,0),(18,'Ray-Ban_JUSTIN_RB4165 622_55','bb151c8d-9e86-44c5-9db3-12becdf25b89',145,1,0),(19,'Tom_Ford_FT5745-B_1','60ea6670-e813-4b88-99a3-6631ff072d4f',260,1,0),(20,'Gucci_GG0926S_1','60ea6670-e813-4b88-99a3-6631ff072d4f',230,1,0),(21,'Gucci_GG0926S_1','3dbb12fa-1735-4237-b853-f82fc4bb7fe6',230,1,0),(22,'Bvlgari_BV6158B 278_13','3dbb12fa-1735-4237-b853-f82fc4bb7fe6',330,1,0),(23,'GV_Library_ IBLU07_BB','6dbd9d5a-bc29-40cb-9cba-b21584b5cb8b',25,1,0),(24,'Gucci_GG0926S_1','db7b0422-05fb-4d7d-9d6f-9ccbff2e8e4d',230,1,0),(25,'Max_Mara_MM5003_28','db7b0422-05fb-4d7d-9d6f-9ccbff2e8e4d',180,1,0),(26,'Gucci_GG0926S_1','dfeefe5d-88a0-423a-a3de-3a44e59a0432',230,1,0),(27,'GV_Library_ IBLU07_BB','dfeefe5d-88a0-423a-a3de-3a44e59a0432',25,1,0),(28,'Ray-Ban_JUSTIN_RB4165 622_55','e5c64047-d5a7-4a14-bbd5-768aaf801e18',145,2,0),(29,'Tom_Ford_FT5745-B_1','1e28101d-9edb-4b51-9e3e-50ed0db3b375',260,1,0),(30,'GV_Library_ IBLU07_BB','1e28101d-9edb-4b51-9e3e-50ed0db3b375',25,1,0),(31,'Tom_Ford_FT0764_01B','1e28101d-9edb-4b51-9e3e-50ed0db3b375',260,1,0),(32,'Gucci_GG0926S_1','ff58c21f-a9c7-4b8b-a53f-a686df7f051d',230,1,0),(33,'Tom_Ford_FT5745-B_1','079a806b-293d-4c6b-8710-0d9f55a58442',260,1,0),(34,'GV_Library_ IBLU07_BB','079a806b-293d-4c6b-8710-0d9f55a58442',25,1,0),(35,'Bvlgari_BV6158B 278_13','2b8c7498-ce9b-49bf-86ef-2cac1770c7ef',330,3,0),(36,'Ray-Ban_JUSTIN RB4165 622_T3','2b8c7498-ce9b-49bf-86ef-2cac1770c7ef',165,2,0),(37,'Montana_BLFBOX67_BB','d9fe1801-e1ac-44d0-96a3-c5718025b18f',20,3,0),(38,'Max_Mara_MM5003_28','d9fe1801-e1ac-44d0-96a3-c5718025b18f',180,2,0),(39,'Ray-Ban_JUSTIN_RB4165 622_55','5406190c-cc07-4cad-89ad-e8a8bd62fbdf',145,3,0),(40,'Tom_Ford_FT5737-B_52','5406190c-cc07-4cad-89ad-e8a8bd62fbdf',240,5,0),(41,'Tom_Ford_FT0764_01B','95ed8121-b256-4e2d-85eb-c4daac49500a',25,1,0),(42,'Bvlgari_BV6158B 278_13','fae81ab5-2e83-49fe-b547-a9346725e55b',330,1,0),(43,'Emporio_Armani_0EA4152_56691W','fae81ab5-2e83-49fe-b547-a9346725e55b',130,1,0);
/*!40000 ALTER TABLE `occhiale_ordine` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ordine`
--

DROP TABLE IF EXISTS `ordine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ordine` (
  `idOrdine` varchar(36) NOT NULL,
  `data` timestamp NOT NULL,
  `email` varchar(45) NOT NULL,
  `stato` text NOT NULL,
  PRIMARY KEY (`idOrdine`),
  KEY `email` (`email`),
  CONSTRAINT `ordine_ibfk_1` FOREIGN KEY (`email`) REFERENCES `utente` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ordine`
--

LOCK TABLES `ordine` WRITE;
/*!40000 ALTER TABLE `ordine` DISABLE KEYS */;
INSERT INTO `ordine` VALUES ('046eb5c5-01d9-477d-b831-ee969df9b7d6','2022-07-06 09:57:06','a@gmail.com','preso in carico'),('079a806b-293d-4c6b-8710-0d9f55a58442','2022-07-17 17:23:13','pas@gmail.com','preso in carico'),('0a1969e6-30c8-43c7-b6f1-03f1fa44d8a7','2022-07-06 09:58:33','a@gmail.com','preso in carico'),('12121cb7-8421-4a6e-9da6-8e7b53568c86','2022-07-06 17:21:04','m.rossi@gmail.com','preso in carico'),('14902dbf-075e-46f8-ae89-d8a4c36ed84c','2022-07-06 09:51:01','a@gmail.com','preso in carico'),('1e28101d-9edb-4b51-9e3e-50ed0db3b375','2022-07-14 10:48:50','g@gmail.com','preso in carico'),('2b8c7498-ce9b-49bf-86ef-2cac1770c7ef','2022-07-17 17:44:17','pas@gmail.com','preso in carico'),('31ed5d7e-d68c-416b-9146-a7f301184c56','2022-07-06 10:51:14','ma@gmail.com','preso in carico'),('3c6e43dd-ef5b-4dbc-8363-15a4b29ee6a8','2022-07-06 17:28:54','m.rossi@gmail.com','preso in carico'),('3dbb12fa-1735-4237-b853-f82fc4bb7fe6','2022-07-11 18:03:18','g@gmail.com','preso in carico'),('5406190c-cc07-4cad-89ad-e8a8bd62fbdf','2022-07-18 13:31:34','alex@gmail.com','preso in carico'),('5bd38e01-b421-4311-985d-1838568a9159','2022-07-06 09:45:18','a@gmail.com','preso in carico'),('60ea6670-e813-4b88-99a3-6631ff072d4f','2022-07-11 16:42:13','g@gmail.com','preso in carico'),('62329c99-cc66-46e1-a10b-2e77ac721a9f','2022-07-08 09:55:53','m@gmail.com','preso in carico'),('6dbd9d5a-bc29-40cb-9cba-b21584b5cb8b','2022-07-12 17:07:51','m@gmail.com','preso in carico'),('8011f89a-f4ff-4f7c-bb2e-0d8550fb5a1a','2022-07-06 09:55:43','a@gmail.com','preso in carico'),('81ccc385-8ac7-4898-b9c6-ce4120108141','2022-07-06 17:33:01','m.rossi@gmail.com','preso in carico'),('95ed8121-b256-4e2d-85eb-c4daac49500a','2022-07-19 17:38:33','alex@gmail.com','preso in carico'),('a06dad75-9063-4941-9711-6198e6b8c646','2022-07-06 09:51:03','a@gmail.com','preso in carico'),('a88874e4-80da-4170-b7e0-93f9514e4130','2022-07-06 17:33:52','m.rossi@gmail.com','preso in carico'),('bb151c8d-9e86-44c5-9db3-12becdf25b89','2022-07-08 09:56:57','m@gmail.com','preso in carico'),('d3c59a20-86d1-453d-bca0-502d0157dd4d','2022-07-06 09:54:10','a@gmail.com','preso in carico'),('d61fc843-a44a-49e4-909b-f190800e0e91','2022-07-06 10:08:30','a@gmail.com','preso in carico'),('d7dcc070-f1ac-4622-ad2a-83e9c80b436d','2022-07-06 10:10:04','a@gmail.com','preso in carico'),('d9fe1801-e1ac-44d0-96a3-c5718025b18f','2022-07-17 17:50:37','pas@gmail.com','preso in carico'),('db7b0422-05fb-4d7d-9d6f-9ccbff2e8e4d','2022-07-13 08:04:25','m@gmail.com','preso in carico'),('df7da2c5-93de-4a14-922b-9b502199bc36','2022-07-06 17:47:24','a.satta2@gmail.com','preso in carico'),('dfeefe5d-88a0-423a-a3de-3a44e59a0432','2022-07-13 08:08:38','m@gmail.com','preso in carico'),('e156bc78-e7bf-4677-9fd1-ee01f87d2288','2022-07-06 17:34:34','m.rossi@gmail.com','preso in carico'),('e516fb70-2051-4403-a45a-685fb754698b','2022-07-06 10:50:31','ma@gmail.com','preso in carico'),('e5c64047-d5a7-4a14-bbd5-768aaf801e18','2022-07-13 09:32:08','g@gmail.com','preso in carico'),('ebde5717-3d95-4137-b432-95ab63b69ec4','2022-07-08 09:35:51','g@gmail.com','preso in carico'),('ef8977cd-e838-4292-b776-46d515b5da8d','2022-07-06 09:47:33','a@gmail.com','preso in carico'),('f1267d12-e857-4905-8e57-b8cd16c40cb5','2022-07-06 10:13:55','a@gmail.com','preso in carico'),('fae81ab5-2e83-49fe-b547-a9346725e55b','2022-10-14 09:38:34','alex@gmail.com','preso in carico'),('ff58c21f-a9c7-4b8b-a53f-a686df7f051d','2022-07-17 17:19:50','pas@gmail.com','preso in carico');
/*!40000 ALTER TABLE `ordine` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `utente`
--

DROP TABLE IF EXISTS `utente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `utente` (
  `firstName` varchar(20) NOT NULL,
  `lastName` varchar(20) NOT NULL,
  `birthday` date NOT NULL,
  `email` varchar(45) NOT NULL,
  `pass` varchar(50) NOT NULL,
  `role` int NOT NULL,
  PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `utente`
--

LOCK TABLES `utente` WRITE;
/*!40000 ALTER TABLE `utente` DISABLE KEYS */;
INSERT INTO `utente` VALUES ('Alessandro','Satta','2000-10-11','a.satta2@gmail.com','oPMoWwfCbA3NIZFEfzkRcNBgNejVfjGgSLqHB086mhU=',0),('Antonio','Martucci','1995-06-05','a@gmail.com','oPMoWwfCbA3NIZFEfzkRcNBgNejVfjGgSLqHB086mhU=',0),('Alessandro','Satta','2023-01-31','alessandrosatta@gmail.com','oPMoWwfCbA3NIZFEfzkRcNBgNejVfjGgSLqHB086mhU=',0),('alex','alex','2022-07-18','alex@gmail.com','oPMoWwfCbA3NIZFEfzkRcNBgNejVfjGgSLqHB086mhU=',0),('as','as','2022-07-18','as@gmail.com','oPMoWwfCbA3NIZFEfzkRcNBgNejVfjGgSLqHB086mhU=',0),('lo','lo','2022-11-16','asd@gmai.com','a4ayc/80/OGda4BO/1o/V0etpOqiLx1JwB5S3beHW0s=',0),('Francesco','Bianchi','2003-06-09','f.bianchi@gmail.com','oPMoWwfCbA3NIZFEfzkRcNBgNejVfjGgSLqHB086mhU=',0),('Matteo','Giordano','1995-06-05','g@gmail.com','oPMoWwfCbA3NIZFEfzkRcNBgNejVfjGgSLqHB086mhU=',0),('Ligia','l','2022-07-18','l@gmail.com','oPMoWwfCbA3NIZFEfzkRcNBgNejVfjGgSLqHB086mhU=',0),('Mario','Rossi','1995-03-08','m.rossi@gmail.com','oPMoWwfCbA3NIZFEfzkRcNBgNejVfjGgSLqHB086mhU=',0),('Michele','Micky','1985-06-05','m@gmail.com','oPMoWwfCbA3NIZFEfzkRcNBgNejVfjGgSLqHB086mhU=',0),('Mary','Rossi','1905-06-05','ma@gmail.com','oPMoWwfCbA3NIZFEfzkRcNBgNejVfjGgSLqHB086mhU=',0),('mar','asd','2022-11-16','mar@gmai.com','oPMoWwfCbA3NIZFEfzkRcNBgNejVfjGgSLqHB086mhU=',0),('mario','mario','2022-07-18','mario@gmail.com','oPMoWwfCbA3NIZFEfzkRcNBgNejVfjGgSLqHB086mhU=',0),('Pasquale','Esposito','1975-06-05','p@gmail.com','oPMoWwfCbA3NIZFEfzkRcNBgNejVfjGgSLqHB086mhU=',0),('Pasqualino','Pasqui','1905-06-05','pas@gmail.com','oPMoWwfCbA3NIZFEfzkRcNBgNejVfjGgSLqHB086mhU=',0),('Peppe','Peppino','1955-06-05','pep@gmail.com','oPMoWwfCbA3NIZFEfzkRcNBgNejVfjGgSLqHB086mhU=',0),('pippo','asd','2022-10-24','pippo@gmail.com','oPMoWwfCbA3NIZFEfzkRcNBgNejVfjGgSLqHB086mhU=',0),('pr','pr','2022-07-18','pr@gmail.com','oPMoWwfCbA3NIZFEfzkRcNBgNejVfjGgSLqHB086mhU=',0),('Rob','Pisc','1986-06-05','r@gmail.com','oPMoWwfCbA3NIZFEfzkRcNBgNejVfjGgSLqHB086mhU=',0),('Roberto','Piscopo','2001-08-07','ro@gmail.com','oPMoWwfCbA3NIZFEfzkRcNBgNejVfjGgSLqHB086mhU=',1),('silvy','silvy','1995-06-05','s@gmail.com','oPMoWwfCbA3NIZFEfzkRcNBgNejVfjGgSLqHB086mhU=',0),('Vincenzo','Rossi','2023-01-24','vinc@gmail.com','oPMoWwfCbA3NIZFEfzkRcNBgNejVfjGgSLqHB086mhU=',0);
/*!40000 ALTER TABLE `utente` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-02-16 13:56:58
