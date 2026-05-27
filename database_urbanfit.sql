CREATE DATABASE  IF NOT EXISTS `urbanfitdb` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `urbanfitdb`;
-- MySQL dump 10.13  Distrib 8.0.43, for macos15 (arm64)
--
-- Host: localhost    Database: urbanfitdb
-- ------------------------------------------------------
-- Server version	9.4.0

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
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `address` (
  `address_id` int NOT NULL AUTO_INCREMENT,
  `customer_id` int NOT NULL,
  `address_type` varchar(20) NOT NULL,
  `street` varchar(100) NOT NULL,
  `city` varchar(50) NOT NULL,
  `state` varchar(50) DEFAULT NULL,
  `postal_code` varchar(20) DEFAULT NULL,
  `country` varchar(50) NOT NULL,
  PRIMARY KEY (`address_id`),
  KEY `fk_address_customer` (`customer_id`),
  CONSTRAINT `fk_address_customer` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (1,1,'billing','1200 University Dr','Fort Worth','TX','76109','USA'),(2,1,'shipping','2200 Stadium Dr Apt 3B','Fort Worth','TX','76109','USA'),(3,2,'billing','450 Maple St','Dallas','TX','75201','USA'),(4,2,'shipping','880 Elm St','Dallas','TX','75204','USA'),(5,3,'shipping','12 Oak Ridge Ln','Plano','TX','75024','USA'),(6,4,'shipping','930 Sunset Blvd','Los Angeles','CA','90026','USA'),(7,5,'billing','31 Hillcrest Rd','Austin','TX','78701','USA'),(8,5,'shipping','2500 Runway Way','Austin','TX','78702','USA'),(9,6,'shipping','775 Lakeside Dr','Irving','TX','75039','USA'),(10,7,'shipping','602 Pine St','Arlington','TX','76010','USA'),(11,8,'billing','14 Garden Ct','Houston','TX','77002','USA'),(12,8,'shipping','590 Bayou Ln','Houston','TX','77009','USA'),(13,9,'shipping','91 Riverside Way','Denver','CO','80202','USA'),(14,10,'billing','510 Broadway Ave','New York','NY','10012','USA'),(15,10,'shipping','815 West 45th St','New York','NY','10019','USA'),(16,11,'shipping','300 Greenway Dr','Chicago','IL','60601','USA'),(17,12,'shipping','72 Harbor St','San Diego','CA','92101','USA'),(18,13,'billing','808 Cedar Ave','Seattle','WA','98101','USA'),(19,13,'shipping','190 Trail Run Rd','Seattle','WA','98109','USA'),(20,14,'shipping','66 Sunrise Ct','Phoenix','AZ','85004','USA'),(21,15,'billing','120 Lake Point Dr','Miami','FL','33131','USA'),(22,15,'shipping','460 Marathon Blvd','Miami','FL','33132','USA');
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `category_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Running Shoes','Performance running footwear'),(2,'Training Apparel','Tops, shorts and tights for training'),(3,'Accessories','Socks, hats, bottles and other add-ons'),(4,'Electronics','Watches, headphones, trackers'),(5,'Recovery','Foam rollers, massage tools'),(6,'Lifestyle','Casual wear and everyday sneakers');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `customer_id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `email` varchar(100) NOT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`customer_id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'Andres','Pina','andres.pina@example.com','817-555-0101','2024-08-01 00:00:00'),(2,'Maria','Lopez','maria.lopez@example.com','214-555-0102','2024-08-10 00:00:00'),(3,'James','Carter','james.carter@example.com','469-555-0103','2024-09-02 00:00:00'),(4,'Elena','Garcia','elena.garcia@example.com','682-555-0104','2024-09-19 00:00:00'),(5,'David','Nguyen','david.nguyen@example.com','972-555-0105','2024-10-03 00:00:00'),(6,'Sophia','Martinez','sophia.martinez@example.com','817-555-0106','2024-10-12 00:00:00'),(7,'Liam','Johnson','liam.johnson@example.com','214-555-0107','2024-10-25 00:00:00'),(8,'Carla','Santos','carla.santos@example.com','469-555-0108','2024-11-02 00:00:00'),(9,'Omar','Hassan','omar.hassan@example.com','972-555-0109','2024-11-05 00:00:00'),(10,'Isabella','Romero','isabella.romero@example.com','682-555-0110','2024-11-09 00:00:00'),(11,'Henry','Miller','henry.miller@example.com','817-555-0111','2024-11-12 00:00:00'),(12,'Julia','Kim','julia.kim@example.com','214-555-0112','2024-11-15 00:00:00'),(13,'Mark','Wilson','mark.wilson@example.com','469-555-0113','2024-11-18 00:00:00'),(14,'Rosa','Diaz','rosa.diaz@example.com','972-555-0114','2024-11-20 00:00:00'),(15,'Victor','Alvarez','victor.alvarez@example.com','682-555-0115','2024-11-22 00:00:00');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customerorder`
--

DROP TABLE IF EXISTS `customerorder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customerorder` (
  `order_id` int NOT NULL AUTO_INCREMENT,
  `customer_id` int NOT NULL,
  `shipping_address_id` int DEFAULT NULL,
  `order_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `status` varchar(20) NOT NULL,
  `total_amount` decimal(10,2) NOT NULL,
  PRIMARY KEY (`order_id`),
  KEY `fk_order_customer` (`customer_id`),
  KEY `fk_order_shipping_address` (`shipping_address_id`),
  CONSTRAINT `fk_order_customer` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_order_shipping_address` FOREIGN KEY (`shipping_address_id`) REFERENCES `address` (`address_id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customerorder`
--

LOCK TABLES `customerorder` WRITE;
/*!40000 ALTER TABLE `customerorder` DISABLE KEYS */;
INSERT INTO `customerorder` VALUES (1,1,2,'2024-11-10 00:00:00','SHIPPED',214.97),(2,2,4,'2024-11-11 00:00:00','SHIPPED',189.98),(3,3,5,'2024-11-11 00:00:00','PENDING',139.99),(4,4,6,'2024-11-12 00:00:00','SHIPPED',274.98),(5,5,8,'2024-11-12 00:00:00','DELIVERED',356.47),(6,6,9,'2024-11-13 00:00:00','DELIVERED',94.98),(7,7,10,'2024-11-13 00:00:00','PENDING',229.99),(8,8,12,'2024-11-14 00:00:00','SHIPPED',153.48),(9,9,13,'2024-11-14 00:00:00','DELIVERED',309.98),(10,10,15,'2024-11-15 00:00:00','DELIVERED',129.98),(11,11,16,'2024-11-15 00:00:00','PENDING',179.98),(12,12,17,'2024-11-16 00:00:00','CANCELLED',69.99);
/*!40000 ALTER TABLE `customerorder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inventory`
--

DROP TABLE IF EXISTS `inventory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `inventory` (
  `inventory_id` int NOT NULL AUTO_INCREMENT,
  `product_id` int NOT NULL,
  `warehouse_id` int NOT NULL,
  `quantity_on_hand` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`inventory_id`),
  KEY `fk_inventory_product` (`product_id`),
  KEY `fk_inventory_warehouse` (`warehouse_id`),
  CONSTRAINT `fk_inventory_product` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_inventory_warehouse` FOREIGN KEY (`warehouse_id`) REFERENCES `warehouse` (`warehouse_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inventory`
--

LOCK TABLES `inventory` WRITE;
/*!40000 ALTER TABLE `inventory` DISABLE KEYS */;
INSERT INTO `inventory` VALUES (1,1,1,120),(2,1,2,80),(3,2,1,60),(4,2,3,40),(5,3,1,150),(6,3,2,100),(7,4,2,70),(8,4,3,50),(9,5,1,200),(10,5,3,140),(11,6,1,160),(12,6,2,110),(13,7,1,90),(14,8,2,75),(15,9,1,260),(16,9,2,180),(17,10,2,190),(18,11,1,120),(19,12,3,95),(20,13,1,40),(21,13,3,30),(22,14,2,55),(23,15,1,65),(24,16,2,80),(25,16,3,60),(26,17,1,140),(27,18,2,70),(28,19,1,85),(29,20,3,90),(30,20,1,60);
/*!40000 ALTER TABLE `inventory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orderitem`
--

DROP TABLE IF EXISTS `orderitem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orderitem` (
  `order_item_id` int NOT NULL AUTO_INCREMENT,
  `order_id` int NOT NULL,
  `product_id` int NOT NULL,
  `quantity` int NOT NULL,
  `unit_price` decimal(10,2) NOT NULL,
  PRIMARY KEY (`order_item_id`),
  KEY `fk_orderitem_order` (`order_id`),
  KEY `fk_orderitem_product` (`product_id`),
  CONSTRAINT `fk_orderitem_order` FOREIGN KEY (`order_id`) REFERENCES `customerorder` (`order_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_orderitem_product` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderitem`
--

LOCK TABLES `orderitem` WRITE;
/*!40000 ALTER TABLE `orderitem` DISABLE KEYS */;
INSERT INTO `orderitem` VALUES (1,1,1,1,139.99),(2,1,9,2,37.49),(3,2,2,1,179.99),(4,2,10,1,9.99),(5,3,1,1,139.99),(6,4,4,1,149.00),(7,4,13,1,125.98),(8,5,3,1,119.50),(9,5,5,2,34.99),(10,5,6,1,44.99),(11,5,9,1,19.99),(12,5,16,1,39.99),(13,5,17,1,29.99),(14,6,6,1,44.99),(15,6,9,2,24.99),(16,7,13,1,229.99),(17,8,14,1,129.99),(18,8,11,1,23.49),(19,9,2,2,154.99),(20,10,19,1,89.99),(21,10,18,1,39.99),(22,11,13,1,229.99),(23,11,15,1,49.99),(24,12,1,1,69.99);
/*!40000 ALTER TABLE `orderitem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment` (
  `payment_id` int NOT NULL AUTO_INCREMENT,
  `order_id` int NOT NULL,
  `payment_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `amount` decimal(10,2) NOT NULL,
  `payment_method` varchar(50) NOT NULL,
  `status` varchar(20) NOT NULL,
  PRIMARY KEY (`payment_id`),
  KEY `fk_payment_order` (`order_id`),
  CONSTRAINT `fk_payment_order` FOREIGN KEY (`order_id`) REFERENCES `customerorder` (`order_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment`
--

LOCK TABLES `payment` WRITE;
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
INSERT INTO `payment` VALUES (1,1,'2024-11-10 00:00:00',214.97,'credit_card','COMPLETED'),(2,2,'2024-11-11 00:00:00',189.98,'paypal','COMPLETED'),(3,3,'2024-11-11 00:00:00',139.99,'credit_card','PENDING'),(4,4,'2024-11-12 00:00:00',274.98,'credit_card','COMPLETED'),(5,5,'2024-11-12 00:00:00',356.47,'credit_card','COMPLETED'),(6,6,'2024-11-13 00:00:00',94.98,'gift_card','COMPLETED'),(7,7,'2024-11-13 00:00:00',229.99,'credit_card','PENDING'),(8,8,'2024-11-14 00:00:00',153.48,'paypal','COMPLETED'),(9,9,'2024-11-14 00:00:00',309.98,'credit_card','COMPLETED'),(10,10,'2024-11-15 00:00:00',129.98,'credit_card','COMPLETED'),(11,11,'2024-11-15 00:00:00',179.98,'paypal','PENDING'),(12,12,'2024-11-16 00:00:00',69.99,'credit_card','REFUNDED');
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `product_id` int NOT NULL AUTO_INCREMENT,
  `category_id` int NOT NULL,
  `name` varchar(100) NOT NULL,
  `brand` varchar(50) DEFAULT NULL,
  `price` decimal(10,2) NOT NULL,
  `active` tinyint(1) NOT NULL DEFAULT '1',
  `sku` varchar(50) NOT NULL,
  PRIMARY KEY (`product_id`),
  UNIQUE KEY `sku` (`sku`),
  KEY `fk_product_category` (`category_id`),
  CONSTRAINT `fk_product_category` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,1,'SkyRide 10K','FleetFeet',139.99,1,'RSKY-10K-001'),(2,1,'TempoFly Elite','Strider',179.99,1,'RFLY-ELT-002'),(3,1,'CloudStreet Daily','Nimbus',119.50,1,'RCLD-DLY-003'),(4,1,'TrailStorm Pro','Summit',149.00,1,'RTRAIL-PRO-004'),(5,2,'AeroDry Race Tee','FleetFeet',34.99,1,'TADR-TEE-005'),(6,2,'UltraFlex 7in Short','Strider',44.99,1,'SHUF-7IN-006'),(7,2,'ThermaWarm Tight','Nimbus',59.99,1,'THTH-WRM-007'),(8,2,'LightShell Running Vest','Summit',79.99,1,'VST-LSHL-008'),(9,3,'CushionRun Socks 3-pack','FleetFeet',19.99,1,'SOCK-3PK-009'),(10,3,'HydroFlow Bottle 600ml','Strider',16.50,1,'BOTT-600-010'),(11,3,'ChillCap Lightweight','Nimbus',24.00,1,'CAP-CHILL-011'),(12,3,'RaceNumber Belt','Summit',18.99,1,'BELT-RACE-012'),(13,4,'PaceTrack GPS Watch','FleetFeet',229.99,1,'GPS-PT-013'),(14,4,'BeatRush Earbuds','Strider',129.99,1,'EAR-BR-014'),(15,4,'HeartSense HR Strap','Nimbus',89.00,1,'HR-STRP-015'),(16,5,'FlexRoll Foam Roller','Summit',39.99,1,'ROLL-FLEX-016'),(17,5,'RecoveryBall Set','FleetFeet',29.99,1,'RCV-BALL-017'),(18,6,'StreetEase Hoodie','Strider',69.99,1,'HD-STRT-018'),(19,6,'Everyday Sneaker','Nimbus',89.99,1,'CAS-EVDY-019'),(20,6,'Campus Jogger Pant','Summit',59.50,1,'JG-CAMP-020');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shipment`
--

DROP TABLE IF EXISTS `shipment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shipment` (
  `shipment_id` int NOT NULL AUTO_INCREMENT,
  `order_id` int NOT NULL,
  `ship_date` datetime DEFAULT NULL,
  `carrier` varchar(50) NOT NULL,
  `tracking_number` varchar(100) DEFAULT NULL,
  `shipping_cost` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`shipment_id`),
  KEY `fk_shipment_order` (`order_id`),
  CONSTRAINT `fk_shipment_order` FOREIGN KEY (`order_id`) REFERENCES `customerorder` (`order_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shipment`
--

LOCK TABLES `shipment` WRITE;
/*!40000 ALTER TABLE `shipment` DISABLE KEYS */;
INSERT INTO `shipment` VALUES (1,1,'2024-11-11 00:00:00','UPS','1ZUP000000000001',9.99),(2,2,'2024-11-12 00:00:00','FedEx','FDX0000000000002',11.99),(3,3,NULL,'UPS',NULL,0.00),(4,4,'2024-11-13 00:00:00','USPS','USPS000000000004',7.50),(5,5,'2024-11-13 00:00:00','UPS','1ZUP000000000005',0.00),(6,6,'2024-11-14 00:00:00','USPS','USPS000000000006',5.99),(7,7,NULL,'FedEx',NULL,0.00),(8,8,'2024-11-15 00:00:00','UPS','1ZUP000000000008',8.49),(9,9,'2024-11-15 00:00:00','UPS','1ZUP000000000009',10.99),(10,10,'2024-11-16 00:00:00','USPS','USPS000000000010',6.50);
/*!40000 ALTER TABLE `shipment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `warehouse`
--

DROP TABLE IF EXISTS `warehouse`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `warehouse` (
  `warehouse_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `city` varchar(50) DEFAULT NULL,
  `state` varchar(50) DEFAULT NULL,
  `country` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`warehouse_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `warehouse`
--

LOCK TABLES `warehouse` WRITE;
/*!40000 ALTER TABLE `warehouse` DISABLE KEYS */;
INSERT INTO `warehouse` VALUES (1,'Central DC','Dallas','TX','USA'),(2,'West Coast DC','Los Angeles','CA','USA'),(3,'East Hub','Atlanta','GA','USA');
/*!40000 ALTER TABLE `warehouse` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-12-03 17:49:04
