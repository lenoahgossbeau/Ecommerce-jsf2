-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: bd_ecommerce
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
-- Table structure for table `account_transaction`
--

DROP TABLE IF EXISTS `account_transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account_transaction` (
  `id` int(11) NOT NULL,
  `account_number` varchar(65) DEFAULT NULL,
  `account_name` varchar(45) DEFAULT NULL,
  `causal` varchar(105) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `amount` decimal(10,0) DEFAULT NULL,
  `idType_account_transaction` int(11) DEFAULT NULL,
  `idUser` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idType_account_transaction_idx` (`idType_account_transaction`),
  KEY `idUser_idx` (`idUser`),
  CONSTRAINT `idType_account_transaction` FOREIGN KEY (`idType_account_transaction`) REFERENCES `type_transaction` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account_transaction`
--

LOCK TABLES `account_transaction` WRITE;
/*!40000 ALTER TABLE `account_transaction` DISABLE KEYS */;
/*!40000 ALTER TABLE `account_transaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `article`
--

DROP TABLE IF EXISTS `article`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `article` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(45) DEFAULT NULL,
  `nom` varchar(45) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  `prix` decimal(10,0) DEFAULT NULL,
  `disponible` varchar(45) DEFAULT NULL,
  `idFournisseur` int(11) NOT NULL,
  `idType_Article` int(11) DEFAULT NULL,
  `idOrder` int(11) DEFAULT NULL,
  `idEntree` int(11) DEFAULT NULL,
  `idBuy` int(11) DEFAULT NULL,
  `idStock` int(11) DEFAULT NULL,
  `idShop` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idFournisseur_idx` (`idFournisseur`),
  KEY `idType_Article_idx` (`idType_Article`),
  KEY `idOrder_idx` (`idOrder`),
  KEY `idEntrée_idx` (`idEntree`),
  KEY `idShop_idx` (`idShop`),
  KEY `idStock_idx` (`idStock`),
  KEY `idBuy_idx` (`idBuy`),
  CONSTRAINT `idFournisseur` FOREIGN KEY (`idFournisseur`) REFERENCES `fournisseur` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article`
--

LOCK TABLES `article` WRITE;
/*!40000 ALTER TABLE `article` DISABLE KEYS */;
INSERT INTO `article` VALUES (1,'5343hnhj','Televiseur','Televiseur SMartt TV',300000,'Oui',4,1,1,51,28,1,1),(2,'324762jhjs','Climatisateur','Climatisateur 1200 btu',400000,'Oui',4,1,2,53,29,2,2),(3,'31eqeq','Onduleur','Onduleur avec Inverseur',44545,'Oui',5,2,2,53,29,2,2),(4,'2412iknk','Tracteur','Caterpilar Melangeur',565656,'Oui',7,3,3,55,30,3,3),(5,'23424knk','Climatiseur','Climatiseur 10000btu',620000,'Oui',8,4,3,55,30,3,3),(6,'458kkkk','Computeyr','Laptop ',210000,'Oui',8,2,4,55,30,3,3),(7,'fg9988','Hard Disk','HardDIsk 5 T',10000,'Oui',6,2,8,62,35,6,6),(8,'12123r','piece de  de motos','far',2058,'au magassin',5,3,6,57,33,4,4),(9,'12123r','piece de  de motos','pneu',12500,'fdsf',7,4,7,59,34,5,5),(13,'547y45rt689','yuoyu','tyutyu',20000,'ytutyu',7,4,9,64,35,8,7),(15,'dgxhfrtx','ruxcy','etet7er',68568585,'yfrhf',7,3,12,69,38,10,10),(19,'retr12','rtrwe','fdydf',30,'wer',8,4,15,78,41,14,15),(20,'rweresu','tttt','tyfytfy',800,'yrcxyxdr',5,3,16,80,42,15,16),(21,'ryree','ryeyfxcb','gwet',988,'2erqw',6,2,NULL,NULL,NULL,NULL,NULL),(22,'ret12','ewr','dsfgg',3000,'ewrte',7,4,17,82,43,16,17);
/*!40000 ALTER TABLE `article` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `buy`
--

DROP TABLE IF EXISTS `buy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `buy` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date2` datetime DEFAULT NULL,
  `designation` varchar(45) DEFAULT NULL,
  `quantite` varchar(45) DEFAULT NULL,
  `montant` decimal(10,0) DEFAULT NULL,
  `amount_deposit` decimal(10,0) DEFAULT NULL,
  `idStatus_buy` int(11) DEFAULT NULL,
  `idType_buy` int(11) DEFAULT NULL,
  `idDeposit_buy` int(11) DEFAULT NULL,
  `idUser` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idStatus_buy_idx` (`idStatus_buy`),
  KEY `idType_buy_idx` (`idType_buy`),
  KEY `idDeposit_buy_idx` (`idDeposit_buy`),
  KEY `idUser_idx` (`idUser`),
  CONSTRAINT `idDeposit_buy` FOREIGN KEY (`idDeposit_buy`) REFERENCES `deposit_buy` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `idStatus_buy` FOREIGN KEY (`idStatus_buy`) REFERENCES `status_buy` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `idType_buy` FOREIGN KEY (`idType_buy`) REFERENCES `type_buy` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `idUser` FOREIGN KEY (`idUser`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `buy`
--

LOCK TABLES `buy` WRITE;
/*!40000 ALTER TABLE `buy` DISABLE KEYS */;
INSERT INTO `buy` VALUES (28,'2018-08-17 21:11:00','Premier Achat','1',2590,NULL,NULL,NULL,NULL,NULL),(29,'2018-08-17 21:15:14','deuxiene achat','1',250000,NULL,NULL,NULL,NULL,NULL),(30,'2018-08-17 21:18:48','troisieme avhat','3',200000,NULL,NULL,NULL,NULL,NULL),(31,'2018-08-20 09:03:08','ydé','60',300000,NULL,NULL,NULL,NULL,NULL),(32,'2018-08-20 09:18:02','dla','50',300000,NULL,NULL,NULL,NULL,NULL),(33,'2018-08-20 09:26:00','dla','50',90000,NULL,NULL,NULL,NULL,NULL),(34,'2018-08-20 09:53:10','DLA','20t',46643432,NULL,NULL,NULL,NULL,NULL),(35,'2018-08-22 15:53:44','dsfsd',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(36,'2018-08-22 15:57:49','rftgd','1',NULL,NULL,NULL,NULL,NULL,NULL),(37,'2018-08-22 16:53:26','fdgdf','1',12500,NULL,NULL,NULL,NULL,NULL),(38,'2018-08-27 18:06:50','tfjtfty','1',68568585,NULL,NULL,NULL,NULL,NULL),(39,'2018-08-29 10:36:45','ghjkghm','1',20230001,NULL,NULL,NULL,NULL,NULL),(40,'2018-08-29 11:40:50','xvxcv','1',600001,NULL,NULL,NULL,NULL,NULL),(41,'2018-08-29 12:23:04','fg','1',30,NULL,NULL,NULL,NULL,NULL),(42,'2018-08-29 21:45:17','fzdf','1',800,NULL,NULL,NULL,NULL,NULL),(43,'2018-08-31 09:44:38','dfg','1',3000,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `buy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contact`
--

DROP TABLE IF EXISTS `contact`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contact` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(45) DEFAULT NULL,
  `prenom` varchar(45) DEFAULT NULL,
  `telephone` varchar(45) DEFAULT NULL,
  `profession` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `siege_sociale` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `id_customer` FOREIGN KEY (`id`) REFERENCES `customer` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `id_fournisseur` FOREIGN KEY (`id`) REFERENCES `fournisseur` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contact`
--

LOCK TABLES `contact` WRITE;
/*!40000 ALTER TABLE `contact` DISABLE KEYS */;
/*!40000 ALTER TABLE `contact` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(45) DEFAULT NULL,
  `prenom` varchar(45) DEFAULT NULL,
  `adresse` varchar(45) DEFAULT NULL,
  `telephone` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (5,'DONJIO','Donna','Rue des pavÃ©es SImbock','323131331','marie.donjio@gmail.com'),(6,'DONFACK','Evrad','Rue des pavÃ©es SImbock','546464646','evrad@gmail.com'),(7,'DONGMO','Martial','SImbock','989893434','martial.dongmo@gmail.com'),(8,'DONGMO TEFO','Marielle','SIMBOCK','487479242','marielle@gmail.com'),(9,'DONGMO FOUEGUE','Nathan','SIMBOCK','487978024','nathan@gmail.com'),(10,'rwetrytty','65r7turyi','yudyruolgpi','84987417','tduytdfuy'),(11,'dedred','gygfhjk','ydfydfyu','drytdrfty','rydrydrtuf@dfdtded');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `daily_fund`
--

DROP TABLE IF EXISTS `daily_fund`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `daily_fund` (
  `id` int(11) NOT NULL,
  `amount` decimal(10,0) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `idUser` int(11) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `daily_fund`
--

LOCK TABLES `daily_fund` WRITE;
/*!40000 ALTER TABLE `daily_fund` DISABLE KEYS */;
/*!40000 ALTER TABLE `daily_fund` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `deposit_buy`
--

DROP TABLE IF EXISTS `deposit_buy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `deposit_buy` (
  `id` int(11) NOT NULL,
  `amount` decimal(10,0) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `idBuy` int(11) DEFAULT NULL,
  `idUser` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idBuy_idx` (`idBuy`),
  KEY `idUser_idx` (`idUser`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `deposit_buy`
--

LOCK TABLES `deposit_buy` WRITE;
/*!40000 ALTER TABLE `deposit_buy` DISABLE KEYS */;
/*!40000 ALTER TABLE `deposit_buy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ec_order`
--

DROP TABLE IF EXISTS `ec_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ec_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `designation` varchar(45) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `mode_paiement` varchar(45) DEFAULT NULL,
  `statut_paiement` varchar(45) DEFAULT NULL,
  `mode_livraison` varchar(45) DEFAULT NULL,
  `statut_livraison` varchar(45) DEFAULT NULL,
  `quantité` varchar(45) DEFAULT NULL,
  `montant` decimal(10,0) DEFAULT NULL,
  `idCustomer` int(11) DEFAULT NULL,
  `idBuy` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idCustomer_idx` (`idCustomer`),
  KEY `idBuy_idx` (`idBuy`),
  CONSTRAINT `idBuy` FOREIGN KEY (`idBuy`) REFERENCES `buy` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `idCustomer` FOREIGN KEY (`idCustomer`) REFERENCES `customer` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ec_order`
--

LOCK TABLES `ec_order` WRITE;
/*!40000 ALTER TABLE `ec_order` DISABLE KEYS */;
INSERT INTO `ec_order` VALUES (1,NULL,'2018-08-17 21:09:53','Cash','paye','Bateau','livre',NULL,NULL,5,28),(2,NULL,'2018-08-17 21:14:06','Cheque','Impaye','Courier','En magasin',NULL,NULL,8,29),(3,NULL,'2018-08-17 21:17:20','Online payment','paye','Train','consigne',NULL,NULL,7,30),(4,NULL,'2018-08-17 21:18:02','quatrieme commande','paye','Avion','en l air',NULL,NULL,5,30),(5,NULL,'2018-08-20 09:08:32','chèque','payé','bateau','livré',NULL,NULL,6,32),(6,NULL,'2018-08-20 09:25:11','carte vert','payé','avion','livré',NULL,NULL,8,33),(7,NULL,'2018-08-20 09:52:59','jhgj','gfhgh','hgjgh','ghjh',NULL,NULL,8,34),(8,'ytity','2018-08-22 15:22:29','ytiyuyti','yuityi','ytiyi','ytiytiyu','1',1,5,35),(9,'hdfh','2018-08-22 15:34:26','hgdhdg','gfhfgh','gfhgfh','hfghfgh','1',1,9,35),(12,'hcutdf','2018-08-27 18:05:46','tfdu5rrf','6f68t','yigyuigf','6uut87t','1',68568585,10,38),(15,'fdgfd','2018-08-29 12:20:44','vzcvxc','zxcvxc','cvxzv','cvzxv','1',30,8,41),(16,'sfdf','2018-08-29 21:44:03','dxfdx','gfxgfxc','xdfgxdfg','xdfxd','1',800,7,42),(17,'fgf','2018-08-31 09:44:07','sdf','dsgf','fgf','fdghfd','1',3000,8,43);
/*!40000 ALTER TABLE `ec_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `entree`
--

DROP TABLE IF EXISTS `entree`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `entree` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` datetime DEFAULT NULL,
  `nom` varchar(45) DEFAULT NULL,
  `quantité` varchar(45) DEFAULT NULL,
  `idType_Entree` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idType_Entrée_idx` (`idType_Entree`)
) ENGINE=InnoDB AUTO_INCREMENT=83 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `entree`
--

LOCK TABLES `entree` WRITE;
/*!40000 ALTER TABLE `entree` DISABLE KEYS */;
INSERT INTO `entree` VALUES (50,'2018-08-17 21:05:02','Stock Premier','1',2),(51,'2018-08-17 21:06:25','Entree en Shop Premier','1',1),(52,'2018-08-17 21:12:31','Deuxieme stock','2',2),(53,'2018-08-17 21:13:05','deuxieme shop','2',1),(54,'2018-08-17 21:16:14','troixieme entree stock','3',2),(55,'2018-08-17 21:16:32','troisieme entree shop','3',1),(56,'2018-08-20 09:00:50','ordinateur','300',2),(57,'2018-08-20 09:20:26','pièce de motos','520',1),(58,'2018-08-20 09:46:31','hgjg','ghkjgh',2),(59,'2018-08-20 09:48:42','fgfgj','2200',1),(60,'2018-08-22 13:55:04','desktop','80',2),(61,'2018-08-22 15:21:19','yut','1',2),(62,'2018-08-22 15:21:53','iopiup','1',1),(63,'2018-08-22 15:33:28','ghfgh','1',2),(64,'2018-08-22 15:33:58','tftuf','2',1),(65,'2018-08-22 15:39:01','dsfgdsgdf','1',1),(66,'2018-08-22 16:49:15','hrgfh','1',2),(67,'2018-08-22 16:49:26','yrty','1',1),(68,'2018-08-27 18:03:15','utcftjf','2',2),(69,'2018-08-27 18:04:24','yufyj','1',1),(70,'2018-08-29 10:26:01','fsdf','1',2),(71,'2018-08-29 10:27:45','dwe','1',1),(72,'2018-08-29 10:34:59','bcvbcv','1',2),(73,'2018-08-29 10:35:40','hjgh','2',1),(74,'2018-08-29 11:38:42','cvbcx','1',2),(75,'2018-08-29 11:38:58','xcvxcv','1',1),(76,'2018-08-29 12:18:34','guuy','1',2),(77,'2018-08-29 12:19:32','yuty','0',1),(78,'2018-08-29 12:19:41','klj','1',1),(79,'2018-08-29 21:39:57','r6t7tt6','1',2),(80,'2018-08-29 21:42:22','fghcf','1',1),(81,'2018-08-31 09:43:35','gbh','1',2),(82,'2018-08-31 09:43:45','ret','1',1);
/*!40000 ALTER TABLE `entree` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fournisseur`
--

DROP TABLE IF EXISTS `fournisseur`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fournisseur` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom1` varchar(45) DEFAULT NULL,
  `prenom` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `siege_social` varchar(45) DEFAULT NULL,
  `telephone` varchar(45) DEFAULT NULL,
  `code1` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fournisseur`
--

LOCK TABLES `fournisseur` WRITE;
/*!40000 ALTER TABLE `fournisseur` DISABLE KEYS */;
INSERT INTO `fournisseur` VALUES (4,'SAMSUNG','SAMSUNG','samsung@mail.com','Coree','5665454646','ewqq113'),(5,'LG','LG','lg@gmail.com','USA','7868686','31232q1qw'),(6,'Packard Bell','Packard Bell','packaerd@gmail.com','FRANCE','889998435','dfgdfho34'),(7,'PANASONIC','PANASONIC','panasonic@li8bero.it','JAPAN','988535353','tyt453535'),(8,'FUJITSU','FUJITSU','fujitsu@gmail.com','JAPAN','4872474','wruiwhr23'),(9,'tset','estrset','tesrtrs@drtd','setrsdtd','5545654','y6srtd125');
/*!40000 ALTER TABLE `fournisseur` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `profile`
--

DROP TABLE IF EXISTS `profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `profile` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `profile`
--

LOCK TABLES `profile` WRITE;
/*!40000 ALTER TABLE `profile` DISABLE KEYS */;
INSERT INTO `profile` VALUES (1,'Admin','administrateur'),(2,'SystèmeAdmin','SystèmeAdministrateur'),(3,'User','utilisateur'),(4,'Guest','visualisation');
/*!40000 ALTER TABLE `profile` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shop`
--

DROP TABLE IF EXISTS `shop`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shop` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` datetime DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  `quantité` varchar(45) DEFAULT NULL,
  `idEntree` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idEntrée_idx` (`idEntree`),
  CONSTRAINT `idEntrée` FOREIGN KEY (`idEntree`) REFERENCES `entree` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shop`
--

LOCK TABLES `shop` WRITE;
/*!40000 ALTER TABLE `shop` DISABLE KEYS */;
INSERT INTO `shop` VALUES (1,'2018-08-17 21:06:25','Entree en Shop Premier','1',51),(2,'2018-08-17 21:13:05','deuxieme shop','2',53),(3,'2018-08-17 21:16:32','troisieme entree shop','3',55),(4,'2018-08-20 09:20:26','pièce de motos','520',57),(5,'2018-08-20 09:48:42','fgfgj','2200',59),(6,'2018-08-22 15:21:53','iopiup','1',62),(7,'2018-08-22 15:33:58','tftuf','2',64),(8,'2018-08-22 15:39:01','dsfgdsgdf','1',65),(10,'2018-08-27 18:04:24','yufyj','1',69),(11,'2018-08-29 10:27:45','dwe','1',71),(12,'2018-08-29 10:35:40','hjgh','2',73),(13,'2018-08-29 11:38:58','xcvxcv','1',75),(14,'2018-08-29 12:19:32','yuty','0',77),(15,'2018-08-29 12:19:41','klj','1',78),(16,'2018-08-29 21:42:22','fghcf','1',80),(17,'2018-08-31 09:43:45','ret','1',82);
/*!40000 ALTER TABLE `shop` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sortir`
--

DROP TABLE IF EXISTS `sortir`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sortir` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` datetime DEFAULT NULL,
  `nom` varchar(45) DEFAULT NULL,
  `quantité` decimal(10,0) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sortir`
--

LOCK TABLES `sortir` WRITE;
/*!40000 ALTER TABLE `sortir` DISABLE KEYS */;
/*!40000 ALTER TABLE `sortir` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `status_buy`
--

DROP TABLE IF EXISTS `status_buy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `status_buy` (
  `id` int(11) NOT NULL,
  `strCode` varchar(45) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `status_buy`
--

LOCK TABLES `status_buy` WRITE;
/*!40000 ALTER TABLE `status_buy` DISABLE KEYS */;
INSERT INTO `status_buy` VALUES (1,'PAID','Paid'),(2,'UNPAID','Unpaid'),(3,'DEPOSIT','Deposit');
/*!40000 ALTER TABLE `status_buy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stock`
--

DROP TABLE IF EXISTS `stock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stock` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` datetime DEFAULT NULL,
  `description` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  `quantité` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  `idEntree` int(11) DEFAULT NULL,
  `idSortir` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COLLATE=utf8_danish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stock`
--

LOCK TABLES `stock` WRITE;
/*!40000 ALTER TABLE `stock` DISABLE KEYS */;
INSERT INTO `stock` VALUES (1,'2018-08-17 21:05:02','Stock Premier','1',50,NULL),(2,'2018-08-17 21:12:31','Deuxieme stock','2',52,NULL),(3,'2018-08-17 21:16:14','troixieme entree stock','3',54,NULL),(4,'2018-08-20 09:00:50','ordinateur','300',56,NULL),(5,'2018-08-20 09:46:31','hgjg','ghkjgh',58,NULL),(6,'2018-08-22 13:55:04','desktop','80',60,NULL),(7,'2018-08-22 15:21:19','yut','1',61,NULL),(8,'2018-08-22 15:33:28','ghfgh','1',63,NULL),(9,'2018-08-22 16:49:15','hrgfh','1',66,NULL),(10,'2018-08-27 18:03:15','utcftjf','2',68,NULL),(11,'2018-08-29 10:26:01','fsdf','1',70,NULL),(12,'2018-08-29 10:34:59','bcvbcv','1',72,NULL),(13,'2018-08-29 11:38:42','cvbcx','1',74,NULL),(14,'2018-08-29 12:18:34','guuy','1',76,NULL),(15,'2018-08-29 21:39:57','r6t7tt6','1',79,NULL),(16,'2018-08-31 09:43:35','gbh','1',81,NULL);
/*!40000 ALTER TABLE `stock` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `type_account`
--

DROP TABLE IF EXISTS `type_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `type_account` (
  `id` int(11) NOT NULL,
  `type` varchar(45) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `type_account`
--

LOCK TABLES `type_account` WRITE;
/*!40000 ALTER TABLE `type_account` DISABLE KEYS */;
INSERT INTO `type_account` VALUES (1,'EXTERNAL','External Account'),(2,'INTERNAL','Internal Account');
/*!40000 ALTER TABLE `type_account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `type_article`
--

DROP TABLE IF EXISTS `type_article`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `type_article` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(45) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `type_article`
--

LOCK TABLES `type_article` WRITE;
/*!40000 ALTER TABLE `type_article` DISABLE KEYS */;
INSERT INTO `type_article` VALUES (1,'informatique','Informatique'),(2,'electronique','Electronique'),(3,'mecanicien','Mecanicien'),(4,'electrique','Electrique');
/*!40000 ALTER TABLE `type_article` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `type_buy`
--

DROP TABLE IF EXISTS `type_buy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `type_buy` (
  `id` int(11) NOT NULL,
  `strCode` varchar(45) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `type_buy`
--

LOCK TABLES `type_buy` WRITE;
/*!40000 ALTER TABLE `type_buy` DISABLE KEYS */;
INSERT INTO `type_buy` VALUES (1,'CASH','paid in cash'),(2,'CREDIT','paid in credit');
/*!40000 ALTER TABLE `type_buy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `type_entree`
--

DROP TABLE IF EXISTS `type_entree`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `type_entree` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(45) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `type_entree`
--

LOCK TABLES `type_entree` WRITE;
/*!40000 ALTER TABLE `type_entree` DISABLE KEYS */;
INSERT INTO `type_entree` VALUES (1,'Shop','Entée en shop'),(2,'Stock','Entrée en shop');
/*!40000 ALTER TABLE `type_entree` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `type_transaction`
--

DROP TABLE IF EXISTS `type_transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `type_transaction` (
  `id` int(11) NOT NULL,
  `type` varchar(45) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `type_transaction`
--

LOCK TABLES `type_transaction` WRITE;
/*!40000 ALTER TABLE `type_transaction` DISABLE KEYS */;
INSERT INTO `type_transaction` VALUES (1,'DEPOSIT','Deposit'),(2,'WITHDRAWALL','Withdrawall');
/*!40000 ALTER TABLE `type_transaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(45) NOT NULL,
  `mot_de_passe` varchar(56) NOT NULL,
  `nom` varchar(45) NOT NULL,
  `date_inscription` datetime NOT NULL,
  `idProfile` int(11) DEFAULT NULL,
  `idUserAction` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idProfile_idx` (`idProfile`),
  KEY `idUserAction_idx` (`idUserAction`),
  CONSTRAINT `idProfile` FOREIGN KEY (`idProfile`) REFERENCES `profile` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `idUserAction` FOREIGN KEY (`idUserAction`) REFERENCES `useraction` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'ericfeussing@gmail.com','JtY/TTSxyzVXEA303hdpjBzyltrGJlAWhAAwBNk8fxa8V1qxBtG/9A==','erico','2018-04-16 10:12:47',NULL,NULL),(2,'erico@gmail.com','UJRR1YKysjMwUs7ocfdvc96kyhgo71y0kMGN24CSL9/lpc1EW0Q6mg==','eric','2018-04-23 10:48:21',NULL,NULL),(3,'erico@gmail.com','3aOzXE0nmRhMcKwUbWzUzpdz8T1nrXXMu+7tazOMqBhiuZESTool4Q==','erico','2018-08-31 13:20:36',NULL,NULL),(4,'erico@gmail.com','UzkS2wvKmbz1XxPCUNzCEWKAJOmITV8W8iAG2kZ9ft4FdMLoCv+1Vw==','erico','2018-08-31 13:21:40',NULL,NULL),(5,'martial@gmail.com','/3Lw2wPdpvNl+Xze7yNXugQNnTZTgJx1hMJ8qo1P4Z7JWJ7c/UwPog==','Martial','2018-09-13 22:21:31',1,1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `useraction`
--

DROP TABLE IF EXISTS `useraction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `useraction` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `action` varchar(45) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `useraction`
--

LOCK TABLES `useraction` WRITE;
/*!40000 ALTER TABLE `useraction` DISABLE KEYS */;
INSERT INTO `useraction` VALUES (1,'Write','ecriture'),(2,'Reed','lecture'),(3,'All','ecriture / lecture');
/*!40000 ALTER TABLE `useraction` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-09-14 12:57:17
