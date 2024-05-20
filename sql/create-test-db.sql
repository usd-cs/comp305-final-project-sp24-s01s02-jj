/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Alumni`
--

DROP TABLE IF EXISTS `Alumni`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Alumni` (
                          `id` bigint(20) unsigned NOT NULL,
                          `graduation_date` date NOT NULL,
                          `degree_type` bigint(20) NOT NULL,
                          PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Alumni`
--

LOCK TABLES `Alumni` WRITE;
/*!40000 ALTER TABLE `Alumni` DISABLE KEYS */;
INSERT INTO `Alumni` VALUES
                         (1,'2023-07-31',1),
                         (371,'3999-06-25',2),
                         (372,'2101-05-27',3),
                         (373,'2101-05-27',3);
/*!40000 ALTER TABLE `Alumni` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Building`
--

DROP TABLE IF EXISTS `Building`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Building` (
                            `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
                            `name` mediumtext NOT NULL,
                            `address` mediumtext NOT NULL,
                            `floors` tinyint(4) NOT NULL,
                            `abbreviation` tinytext NOT NULL,
                            PRIMARY KEY (`id`),
                            UNIQUE KEY `Building_pk` (`abbreviation`) USING HASH
) ENGINE=InnoDB AUTO_INCREMENT=162 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Building`
--

LOCK TABLES `Building` WRITE;
/*!40000 ALTER TABLE `Building` DISABLE KEYS */;
INSERT INTO `Building` VALUES
                           (1,'Building1','1234 Building Ave',3,'BLD'),
                           (45,'Evil Building','123 Seasame St.',7,'EVB'),
                           (61,'Elmo\'s House','123 Seasame St.',40,'ELMO');
/*!40000 ALTER TABLE `Building` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Department`
--

DROP TABLE IF EXISTS `Department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Department` (
                              `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
                              `name` mediumtext NOT NULL,
                              PRIMARY KEY (`id`),
                              UNIQUE KEY `Department_pk` (`name`) USING HASH
) ENGINE=InnoDB AUTO_INCREMENT=215 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Department`
--

LOCK TABLES `Department` WRITE;
/*!40000 ALTER TABLE `Department` DISABLE KEYS */;
INSERT INTO `Department` VALUES
                             (1,'Fake Department 1'),
                             (2,'Fake Department 2'),
                             (3,'engineeringDept'),
                             (4,'Evil Department');
/*!40000 ALTER TABLE `Department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Employee`
--

DROP TABLE IF EXISTS `Employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Employee` (
                            `id` bigint(20) unsigned NOT NULL,
                            `start_date` date NOT NULL,
                            `hourly_wage` double NOT NULL,
                            `manager` bigint(20) DEFAULT NULL,
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Employee`
--

LOCK TABLES `Employee` WRITE;
/*!40000 ALTER TABLE `Employee` DISABLE KEYS */;
INSERT INTO `Employee` VALUES
                           (5,'2024-05-04',16.85,NULL),
                           (6,'2024-05-04',16.86,5),
                           (75,'2024-05-04',16.85,NULL),
                           (376,'2021-01-05',0.12,5),
                           (377,'2021-01-05',0.12,5),
                           (378,'2012-03-07',10.75,377),
                           (749,'1111-12-12',17.6,NULL),
                           (750,'1112-12-22',14,NULL),
                           (751,'1111-12-22',16.5,377);
/*!40000 ALTER TABLE `Employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Faculty`
--

DROP TABLE IF EXISTS `Faculty`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Faculty` (
                           `id` bigint(20) unsigned NOT NULL,
                           `office_location` bigint(20) NOT NULL,
                           `has_tenure` tinyint(1) NOT NULL,
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Faculty`
--

LOCK TABLES `Faculty` WRITE;
/*!40000 ALTER TABLE `Faculty` DISABLE KEYS */;
INSERT INTO `Faculty` VALUES
                          (75,1,1),
                          (376,45,0),
                          (377,45,0);
/*!40000 ALTER TABLE `Faculty` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Person`
--

DROP TABLE IF EXISTS `Person`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Person` (
                          `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
                          `first_name` tinytext NOT NULL,
                          `last_name` tinytext NOT NULL,
                          `birthdate` date NOT NULL,
                          `phone_number` tinytext NOT NULL,
                          `username` tinytext NOT NULL,
                          `organization_email` tinytext NOT NULL,
                          `secondary_email` tinytext NOT NULL,
                          `is_active` tinyint(1) NOT NULL,
                          `department` bigint(20) NOT NULL,
                          PRIMARY KEY (`id`),
                          UNIQUE KEY `person_username_unique` (`username`) USING HASH,
                          UNIQUE KEY `person_organization_email_unique` (`organization_email`) USING HASH,
                          UNIQUE KEY `person_secondary_email_unique` (`secondary_email`) USING HASH
) ENGINE=InnoDB AUTO_INCREMENT=1113 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Person`
--

LOCK TABLES `Person` WRITE;
/*!40000 ALTER TABLE `Person` DISABLE KEYS */;
INSERT INTO `Person` VALUES
                         (1,'Alumni1','Smith','2004-03-18','1234567891','alumni','alumni@sandiego.edu','alumni@gmail.com',1,1),
                         (5,'Fakename','Fakelast','2004-05-04','1235650099','employee','employee@sandiego.edu','employee@gmail.com',1,2),
                         (6,'Fakename','Fakelast','2004-05-04','1235650099','employee2','employee2@sandiego.edu','employee2@gmail.com',1,2),
                         (55,'Student1','Student1last','2005-06-06','1293409055','student','student@sandiego.edu','student@gmail.com',1,1),
                         (75,'Fac1','Fac1last','1982-09-10','1240059999','fac','fac@sandiego.edu','fac@gmail.com',1,1),
                         (142,'John','Bilips','3903-02-01','1','johnB','john@email.com','john@email2.com',1,3),
                         (143,'John','Evil','3903-02-01','2','johnE','john@evilmail.com','john@evilmail2.com',0,4),
                         (371,'FutureNoah','SpaceMan','2003-02-02','0001','NoahFuture','noah@futuremail.com','noah@futuremail2.com',1,3),
                         (372,'FutureJohn','SpaceGuy','2003-02-02','0002','JohnFuture','john@futuremail.com','john@futuremail2.com',1,3),
                         (373,'FutureAndrew','SpaceDude','2003-02-02','0003','AndrewFuture','andrew@futuremail.com','andrew@futuremail2.com',1,3),
                         (374,'Kevin','Homie','2005-06-06','2105557257','daHomieKevin','kevin@homiemail.com','kevin@homie2mail.com',1,1),
                         (375,'Ronnie','Homie','2005-06-06','6195554578','daHomieRonnie','ronnie@homiemail.com','ronnie@homie2mail.com',1,1),
                         (376,'Evil Professor','Professor','1900-05-07','018273','evilProfessor','uwillfail@sandiego.edu','stillgonnafailu@sandiego.edu',1,4),
                         (377,'Another','Prof','1900-05-07','243563','prof','sate@sandiego.edu','sat2@sandiego.edu',1,4),
                         (378,'Greg','Gregson','1900-05-07','4556677825','greg','greg@gregmail.com','greg@greg2mail.com',1,4),
                         (519,'John','Formtesting','1222-12-12','1231231234','jformtester','ftest@test.com','ftest@person.com',1,1),
                         (749,'big','worker','0222-12-12','1234567890','tester','test@test.com','test@mypassion.net',1,4),
                         (750,'test','Formtesting','1111-12-12','1234567890','wfargo','wlesser@sandiego.edu','wrt@rt.com',1,4),
                         (751,'lock','ness','1111-12-22','1231234444','Loops','bigtest@test.yey','peros@mops.com',1,4);
/*!40000 ALTER TABLE `Person` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Room`
--

DROP TABLE IF EXISTS `Room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Room` (
                        `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
                        `building` bigint(20) NOT NULL,
                        `room_number` mediumint(9) NOT NULL,
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=161 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Room`
--

LOCK TABLES `Room` WRITE;
/*!40000 ALTER TABLE `Room` DISABLE KEYS */;
INSERT INTO `Room` VALUES
                       (1,1,102),
                       (45,45,13),
                       (61,45,102);
/*!40000 ALTER TABLE `Room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Student`
--

DROP TABLE IF EXISTS `Student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Student` (
                           `id` bigint(20) unsigned NOT NULL,
                           `major` mediumtext NOT NULL,
                           `grade` bigint(20) NOT NULL,
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Student`
--

LOCK TABLES `Student` WRITE;
/*!40000 ALTER TABLE `Student` DISABLE KEYS */;
INSERT INTO `Student` VALUES
                          (55,'Computer Science',2),
                          (374,'Math',4),
                          (375,'Math',4);
/*!40000 ALTER TABLE `Student` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-20  9:49:32
