-- MySQL dump 10.13  Distrib 9.0.1, for macos14 (arm64)
--
-- Host: 103.216.119.207    Database: techplatform
-- ------------------------------------------------------
-- Server version	9.1.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `blog`
--

DROP TABLE IF EXISTS `blog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `blog` (
  `id` varchar(32) NOT NULL,
  `created_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `system_status` enum('ACTIVE','INACTIVE') DEFAULT NULL,
  `updated_date` datetime(6) DEFAULT NULL,
  `content` text,
  `liked` int DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  `viewed` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `blog`
--

LOCK TABLES `blog` WRITE;
/*!40000 ALTER TABLE `blog` DISABLE KEYS */;
/*!40000 ALTER TABLE `blog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `blog_bookmark`
--

DROP TABLE IF EXISTS `blog_bookmark`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `blog_bookmark` (
  `id` varchar(32) NOT NULL,
  `created_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `system_status` enum('ACTIVE','INACTIVE') DEFAULT NULL,
  `updated_date` datetime(6) DEFAULT NULL,
  `blog_id` varchar(255) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `blog_bookmark`
--

LOCK TABLES `blog_bookmark` WRITE;
/*!40000 ALTER TABLE `blog_bookmark` DISABLE KEYS */;
/*!40000 ALTER TABLE `blog_bookmark` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chapter`
--

DROP TABLE IF EXISTS `chapter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chapter` (
  `id` varchar(32) NOT NULL,
  `created_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `system_status` enum('ACTIVE','INACTIVE') DEFAULT NULL,
  `updated_date` datetime(6) DEFAULT NULL,
  `course_id` varchar(50) DEFAULT NULL,
  `numerical_order` int DEFAULT NULL,
  `previous_chapter_id` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chapter`
--

LOCK TABLES `chapter` WRITE;
/*!40000 ALTER TABLE `chapter` DISABLE KEYS */;
INSERT INTO `chapter` VALUES ('21063fd3116a4065b5f73b2b8a77177d','2024-12-13 10:34:25','ACTIVE',NULL,'fcfdec192e5e4a528ecde3befb7648fa',0,'e0138a1d35a54bf0a0afd5552eff1771','Ôn lại ES6+'),('3f638244197d490e9109d83ac0dcd795','2024-12-12 21:20:43','ACTIVE',NULL,'46d18733975d442db90f938346d4ac3d',0,'540108742fd549bfa4e08f40710107fc','Làm quen với CSS'),('540108742fd549bfa4e08f40710107fc','2024-12-12 21:20:32','ACTIVE',NULL,'46d18733975d442db90f938346d4ac3d',0,'776f476e222b47dfbdc38cf750c66cf8','Làm quen với HTML, CSS'),('5dceebc223ee45d493062e6ec2e67971','2024-12-13 16:35:51','ACTIVE',NULL,'d64dffcb181a4af8aae61a1812e822d1',0,NULL,'Bắt đầu'),('5fd8276b89f845e3963b547ffe6f0ac9','2024-12-12 22:00:52','ACTIVE',NULL,'d112f302089d4bdf9f5f4fea708399e9',0,'6d1c585f4b834f6798323663f6dcf35b','Biến, Comment, Built-in'),('6d1c585f4b834f6798323663f6dcf35b','2024-12-12 22:00:32','ACTIVE','2024-12-13 16:35:51.140000','d112f302089d4bdf9f5f4fea708399e9',0,'5dceebc223ee45d493062e6ec2e67971','Giới thiệu'),('776f476e222b47dfbdc38cf750c66cf8','2024-12-12 21:20:12','ACTIVE','2024-12-13 05:01:05.818000','46d18733975d442db90f938346d4ac3d',0,NULL,'Bắt đầu'),('a0dd2c94f29047c59bfc0b144d310aa9','2024-12-12 21:20:57','ACTIVE',NULL,'46d18733975d442db90f938346d4ac3d',0,'3f638244197d490e9109d83ac0dcd795','Đệm viền và khoảng lề'),('e0138a1d35a54bf0a0afd5552eff1771','2024-12-13 10:34:08','ACTIVE',NULL,'fcfdec192e5e4a528ecde3befb7648fa',0,NULL,'Giới thiệu'),('fc5a2c2ecb0d4da694542f1a99feeef3','2024-12-12 22:01:06','ACTIVE',NULL,'d112f302089d4bdf9f5f4fea708399e9',0,'5fd8276b89f845e3963b547ffe6f0ac9','Toán tử, kiểu dữ liệu');
/*!40000 ALTER TABLE `chapter` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment` (
  `id` varchar(32) NOT NULL,
  `created_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `system_status` enum('ACTIVE','INACTIVE') DEFAULT NULL,
  `updated_date` datetime(6) DEFAULT NULL,
  `comment_type` enum('NONE','LESSON') DEFAULT NULL,
  `content` text,
  `parent_id` varchar(255) DEFAULT NULL,
  `root_id` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  `viewed` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES ('614d26979b264eda8de62a4c5c17999a','2024-12-13 10:50:53','ACTIVE',NULL,'LESSON','a',NULL,'57386417e7ce4fc291741d6e4783295b',NULL,'d17665f8ed424a78a292e1266e876795',0),('62b5f86107ba4ec483713d2a5d6f3fde','2024-12-13 02:56:33','ACTIVE',NULL,'LESSON','Xin chao',NULL,'3a19d0fba11a4ef49789afcfa72f4f37',NULL,'d17665f8ed424a78a292e1266e876795',0),('bd181a8f6bf24cc3b10942411312cfc3','2024-12-13 02:56:21','ACTIVE',NULL,'LESSON','asd',NULL,'3a19d0fba11a4ef49789afcfa72f4f37',NULL,'d17665f8ed424a78a292e1266e876795',0);
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course` (
  `id` varchar(32) NOT NULL,
  `created_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `system_status` enum('ACTIVE','INACTIVE') DEFAULT NULL,
  `updated_date` datetime(6) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `content` text,
  `course_type` enum('NONE') DEFAULT NULL,
  `created_by_user_id` varchar(255) DEFAULT NULL,
  `description` text,
  `discount` float DEFAULT NULL,
  `img_public_id` varchar(255) DEFAULT NULL,
  `price` float DEFAULT NULL,
  `slug` text,
  `thumbnail_url` text,
  `title` varchar(255) DEFAULT NULL,
  `viewed` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` VALUES ('46d18733975d442db90f938346d4ac3d','2024-12-07 22:36:11','ACTIVE','2024-12-11 14:32:00.818000','TPYL5E5','[{\"type\":\"bulleted-list\",\"children\":[{\"type\":\"list-item\",\"children\":[{\"text\":\"Biết cách xây dựng giao diện web với HTML, CSS\"}]},{\"type\":\"list-item\",\"children\":[{\"text\":\"Biết cách phân tích giao diện website\"}]},{\"type\":\"list-item\",\"children\":[{\"text\":\"Biết cách đặt tên class CSS theo chuẩn BEM\"}]},{\"type\":\"list-item\",\"children\":[{\"text\":\"Biết cách làm giao diện web responsive\"}]},{\"type\":\"list-item\",\"children\":[{\"text\":\"Làm chủ Flexbox khi dựng bố cục website\"}]},{\"type\":\"list-item\",\"children\":[{\"text\":\"Sở hữu 2 giao diện web khi học xong khóa học\"}]},{\"type\":\"list-item\",\"children\":[{\"text\":\"Biết cách tự tạo động lực cho bản thân\"}]},{\"type\":\"list-item\",\"children\":[{\"text\":\"Biết cách tự học những kiến thức mới\"}]},{\"type\":\"list-item\",\"children\":[{\"text\":\"Học được cách làm UI chỉn chu, kỹ tính\"}]},{\"type\":\"list-item\",\"children\":[{\"text\":\"Nhận chứng chỉ khóa học do F8 cấp\"}]}]}]','NONE',NULL,'Trong khóa này chúng ta sẽ cùng nhau xây dựng giao diện 2 trang web là The Band & Shopee.\n',0,NULL,0,'html-css-tu-zero-den-hero','course/l81kzajnygtnuxnxvasz','HTML CSS từ Zero đến Hero',0),('d112f302089d4bdf9f5f4fea708399e9','2024-12-12 21:59:59','ACTIVE',NULL,'TPIUSTK','[{\"type\":\"numbered-list\",\"children\":[{\"type\":\"list-item\",\"children\":[{\"text\":\"Bạn sẽ học được gì?\"}]},{\"type\":\"list-item\",\"children\":[{\"text\":\"Hiểu chi tiết về các khái niệm cơ bản trong JS\"}]},{\"type\":\"list-item\",\"children\":[{\"text\":\"Xây dựng được website đầu tiên kết hợp với JS\"}]},{\"type\":\"list-item\",\"children\":[{\"text\":\"Tự tin khi phỏng vấn với kiến thức vững chắc\"}]},{\"type\":\"list-item\",\"children\":[{\"text\":\"Có nền tảng để học các thư viện và framework JS\"}]},{\"type\":\"list-item\",\"children\":[{\"text\":\"Nắm chắc các tính năng trong phiên bản ES6\"}]},{\"type\":\"list-item\",\"children\":[{\"text\":\"Thành thạo DOM APIs để tương tác với trang web\"}]},{\"type\":\"list-item\",\"children\":[{\"text\":\"Ghi nhớ các khái niệm nhờ bài tập trắc nghiệm\"}]},{\"type\":\"list-item\",\"children\":[{\"text\":\"Nâng cao tư duy với các bài kiểm tra với testcases\"}]},{\"type\":\"list-item\",\"children\":[{\"text\":\"Các bài thực hành như Tabs, Music Player\"}]},{\"type\":\"list-item\",\"children\":[{\"text\":\"Nhận chứng chỉ khóa học do F8 cấp\"}]},{\"type\":\"paragraph\",\"children\":[{\"text\":\"\"}]}]}]','NONE',NULL,'Học Javascript cơ bản phù hợp cho người chưa từng học lập trình. Với hơn 100 bài học và có bài tập thực hành sau mỗi bài học.\n',0,NULL,10000,'lap-trinh-javascript-co-ban','course/jjm7e5qlkaiddoylmkew','Lập Trình JavaScript Cơ Bản',0),('d64dffcb181a4af8aae61a1812e822d1','2024-12-13 16:31:21','ACTIVE',NULL,'TPDWZHA','[{\"type\":\"paragraph\",\"children\":[{\"text\":\"Bạn sẽ học được gì?\"}]},{\"type\":\"paragraph\",\"children\":[{\"text\":\"\"}]},{\"type\":\"bulleted-list\",\"children\":[{\"type\":\"list-item\",\"children\":[{\"text\":\"Được học kiến thức miễn phí với nội dung chất lượng hơn mất phí\"}]},{\"type\":\"list-item\",\"children\":[{\"text\":\"Các kiến thức nâng cao của Javascript giúp code trở nên tối ưu hơn\"}]},{\"type\":\"list-item\",\"children\":[{\"text\":\"Hiểu được cách tư duy nâng cao của các lập trình viên có kinh nghiệm\"}]},{\"type\":\"list-item\",\"children\":[{\"text\":\"Hiểu được các khái niệm khó như từ khóa this, phương thức bind, call, apply & xử lý bất đồng bộ\"}]},{\"type\":\"list-item\",\"children\":[{\"text\":\"Có nền tảng Javascript vững chắc để làm việc với mọi thư viện, framework viết bởi Javascript\"}]},{\"type\":\"list-item\",\"children\":[{\"text\":\"Nâng cao cơ hội thành công khi phỏng vấn xin việc nhờ kiến thức chuyên môn vững chắc\"}]}]}]','NONE',NULL,'Hiểu sâu hơn về cách Javascript hoạt động, tìm hiểu về IIFE, closure, reference types, this keyword, bind, call, apply, prototype, ...',0,NULL,100000,'lap-trinh-javascript-nang-cao','course/rte3uq8j79hgockeezx1','Lập Trình JavaScript Nâng Cao',0),('fcfdec192e5e4a528ecde3befb7648fa','2024-12-13 10:33:48','ACTIVE',NULL,'TP2F1L7','[{\"type\":\"numbered-list\",\"children\":[{\"type\":\"list-item\",\"children\":[{\"text\":\"Bạn sẽ học được gì?\"}]},{\"type\":\"list-item\",\"children\":[{\"text\":\"Hiểu về khái niệm SPA/MPA\"}]},{\"type\":\"list-item\",\"children\":[{\"text\":\"Hiểu về khái niệm hooks\"}]},{\"type\":\"list-item\",\"children\":[{\"text\":\"Hiểu cách ReactJS hoạt động\"}]},{\"type\":\"list-item\",\"children\":[{\"text\":\"Hiểu về function/class component\"}]},{\"type\":\"list-item\",\"children\":[{\"text\":\"Biết cách tối ưu hiệu năng ứng dụng\"}]},{\"type\":\"list-item\",\"children\":[{\"text\":\"Thành thạo làm việc với RESTful API\"}]},{\"type\":\"list-item\",\"children\":[{\"text\":\"Hiểu rõ ràng Redux workflow\"}]},{\"type\":\"list-item\",\"children\":[{\"text\":\"Thành thạo sử dụng Redux vào dự án\"}]},{\"type\":\"list-item\",\"children\":[{\"text\":\"Biết sử dụng redux-thunk middleware\"}]},{\"type\":\"list-item\",\"children\":[{\"text\":\"Xây dựng sản phẩm thực tế (clone Tiktok)\"}]},{\"type\":\"list-item\",\"children\":[{\"text\":\"Triển khai dự án React ra Internet\"}]},{\"type\":\"list-item\",\"children\":[{\"text\":\"Đủ hành trang tự tin apply đi xin việc\"}]},{\"type\":\"list-item\",\"children\":[{\"text\":\"Biết cách Deploy lên Github/Gitlab page\"}]},{\"type\":\"list-item\",\"children\":[{\"text\":\"Nhận chứng chỉ khóa học do F8 cấp\"}]}]}]','NONE',NULL,'Khóa học ReactJS từ cơ bản tới nâng cao, kết quả của khóa học này là bạn có thể làm hầu hết các dự án thường gặp với ReactJS. Cuối khóa học này bạn sẽ sở hữu một dự án giống Tiktok.com, bạn có thể tự tin đi xin việc khi nắm chắc các kiến thức được chia sẻ trong khóa học này.',0,NULL,10000,'xay-dung-website-voi-reactjs','course/ck7ulswometymw1rtafb','Xây Dựng Website với ReactJS',0);
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `device`
--

DROP TABLE IF EXISTS `device`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `device` (
  `id` varchar(32) NOT NULL,
  `created_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `system_status` enum('ACTIVE','INACTIVE') DEFAULT NULL,
  `updated_date` datetime(6) DEFAULT NULL,
  `browser` varchar(255) DEFAULT NULL,
  `fcm_token` varchar(255) DEFAULT NULL,
  `ip_address` varchar(255) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `device`
--

LOCK TABLES `device` WRITE;
/*!40000 ALTER TABLE `device` DISABLE KEYS */;
INSERT INTO `device` VALUES ('1374357a270341c18835cf29e0903bae','2024-12-12 22:45:01','ACTIVE',NULL,'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/131.0.0.0 Safari/537.36','eDSTlxYvjAvdbBqackJctj:APA91bGT1S49p2jVDiVXi1Vr1t9YqtL0nxt0Aryi46h60Zko6W_cmrEXGzbf_XIOaAJG552sAUmg-rUcyJIvJ0yaNsDgrx7HDFYZcJDqkrF8khbCudfqZZ0','0:0:0:0:0:0:0:1','d17665f8ed424a78a292e1266e876795'),('67af7f13d09046ddbddb0f7b519b5239','2024-12-13 10:31:06','ACTIVE',NULL,'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/131.0.0.0 Safari/537.36','d0kKicxM-trY9zWt-95fNk:APA91bHzXarp4N0F8mdxtNrmHJRuwAzjT81OGCmgEpz61cH-rZ2RVUsB6R4VJsc6VTVppyUJW60bDldcTGbiVTx0VdSMiJpSzPYz4iWToDCUz3RFXgXr2Sg','103.238.72.47','d17665f8ed424a78a292e1266e876795'),('93dce0243ebc46f6b5d89d0022edf4ad','2024-12-12 22:45:01','ACTIVE',NULL,'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/131.0.0.0 Safari/537.36','eDSTlxYvjAvdbBqackJctj:APA91bGT1S49p2jVDiVXi1Vr1t9YqtL0nxt0Aryi46h60Zko6W_cmrEXGzbf_XIOaAJG552sAUmg-rUcyJIvJ0yaNsDgrx7HDFYZcJDqkrF8khbCudfqZZ0','0:0:0:0:0:0:0:1','d17665f8ed424a78a292e1266e876795'),('96c3fe6aa26c486c9e9c3d22c4dd0531','2024-12-13 11:03:07','ACTIVE','2024-12-13 14:44:14.160000','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/131.0.0.0 Safari/537.36','d0kKicxM-trY9zWt-95fNk:APA91bG4XopbO0cAzwQOGj1zMr_Ulu0UJtb8JrRyhcEkjMFy8HZfx6UAAZ1eqoarxFJGGBbB5v5cSnpx6ddo8nDwE926kOnfDjICF3AQxamZR-uKQDPlJp4','2001:ee0:4f01:ecc0:70e9:efa0:12f5:8f61','d17665f8ed424a78a292e1266e876795');
/*!40000 ALTER TABLE `device` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `file`
--

DROP TABLE IF EXISTS `file`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `file` (
  `id` varchar(32) NOT NULL,
  `created_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `system_status` enum('ACTIVE','INACTIVE') DEFAULT NULL,
  `updated_date` datetime(6) DEFAULT NULL,
  `content` text,
  `depth` int DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `parent_id` varchar(255) DEFAULT NULL,
  `path` varchar(255) DEFAULT NULL,
  `project_id` varchar(255) DEFAULT NULL,
  `type` enum('FILE','DIRECTORY','DUMMY') DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `file`
--

LOCK TABLES `file` WRITE;
/*!40000 ALTER TABLE `file` DISABLE KEYS */;
INSERT INTO `file` VALUES ('114f71df798c48c18d3a069401814180','2024-05-25 09:41:32','ACTIVE',NULL,NULL,0,'public',NULL,NULL,'e7d7a8846f8046338a3c8849870ce131','DIRECTORY'),('32b2c30980734d2dbe585c9c6125e133','2024-05-25 09:29:34','ACTIVE',NULL,'import \'./style.css\'\r\nimport javascriptLogo from \'./javascript.svg\'\r\nimport viteLogo from \'/vite.svg\'\r\nimport { setupCounter } from \'./counter.js\'\r\n\r\ndocument.querySelector(\'#app\').innerHTML = `\r\n  <div>\r\n    <a href=\"https://vitejs.dev\" target=\"_blank\">\r\n      <img src=\"${viteLogo}\" class=\"logo\" alt=\"Vite logo\" />\r\n    </a>\r\n    <a href=\"https://developer.mozilla.org/en-US/docs/Web/JavaScript\" target=\"_blank\">\r\n      <img src=\"${javascriptLogo}\" class=\"logo vanilla\" alt=\"JavaScript logo\" />\r\n    </a>\r\n    <h1>Hello Vite!</h1>\r\n    <div class=\"card\">\r\n      <button id=\"counter\" type=\"button\"></button>\r\n    </div>\r\n    <p class=\"read-the-docs\">\r\n      Click on the Vite logo to learn more\r\n    </p>\r\n  </div>\r\n`\r\n\r\nsetupCounter(document.querySelector(\'#counter\'))\r\n',0,'main.js',NULL,NULL,'e7d7a8846f8046338a3c8849870ce131','FILE'),('50df6e8d594a4c8f9e299a78986a9320','2024-05-25 09:30:51','ACTIVE',NULL,'<!doctype html>\r\n<html lang=\"en\">\r\n  <head>\r\n    <meta charset=\"UTF-8\" />\r\n    <link rel=\"icon\" type=\"image/svg+xml\" href=\"/vite.svg\" />\r\n    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />\r\n    <title>Vite App</title>\r\n  </head>\r\n  <body>\r\n    <div id=\"app\"></div>\r\n    <script type=\"module\" src=\"/main.js\"></script>\r\n  </body>\r\n</html>\r\n',0,'index.html',NULL,NULL,'e7d7a8846f8046338a3c8849870ce131','FILE'),('62d802de94af44ae867b062c034159e9','2024-05-25 09:35:12','ACTIVE',NULL,'export function setupCounter(element) {\r\n  let counter = 0\r\n  const setCounter = (count) => {\r\n    counter = count\r\n    element.innerHTML = `count is ${counter}`\r\n  }\r\n  element.addEventListener(\'click\', () => setCounter(counter + 1))\r\n  setCounter(0)\r\n}\r\n',0,'counter.js',NULL,NULL,'e7d7a8846f8046338a3c8849870ce131','FILE'),('93f1f0b28f044d5f919748b859df36cf','2024-05-25 09:43:05','ACTIVE',NULL,'<svg xmlns=\"http://www.w3.org/2000/svg\" xmlns:xlink=\"http://www.w3.org/1999/xlink\" aria-hidden=\"true\" role=\"img\" class=\"iconify iconify--logos\" width=\"31.88\" height=\"32\" preserveAspectRatio=\"xMidYMid meet\" viewBox=\"0 0 256 257\"><defs><linearGradient id=\"IconifyId1813088fe1fbc01fb466\" x1=\"-.828%\" x2=\"57.636%\" y1=\"7.652%\" y2=\"78.411%\"><stop offset=\"0%\" stop-color=\"#41D1FF\"></stop><stop offset=\"100%\" stop-color=\"#BD34FE\"></stop></linearGradient><linearGradient id=\"IconifyId1813088fe1fbc01fb467\" x1=\"43.376%\" x2=\"50.316%\" y1=\"2.242%\" y2=\"89.03%\"><stop offset=\"0%\" stop-color=\"#FFEA83\"></stop><stop offset=\"8.333%\" stop-color=\"#FFDD35\"></stop><stop offset=\"100%\" stop-color=\"#FFA800\"></stop></linearGradient></defs><path fill=\"url(#IconifyId1813088fe1fbc01fb466)\" d=\"M255.153 37.938L134.897 252.976c-2.483 4.44-8.862 4.466-11.382.048L.875 37.958c-2.746-4.814 1.371-10.646 6.827-9.67l120.385 21.517a6.537 6.537 0 0 0 2.322-.004l117.867-21.483c5.438-.991 9.574 4.796 6.877 9.62Z\"></path><path fill=\"url(#IconifyId1813088fe1fbc01fb467)\" d=\"M185.432.063L96.44 17.501a3.268 3.268 0 0 0-2.634 3.014l-5.474 92.456a3.268 3.268 0 0 0 3.997 3.378l24.777-5.718c2.318-.535 4.413 1.507 3.936 3.838l-7.361 36.047c-.495 2.426 1.782 4.5 4.151 3.78l15.304-4.649c2.372-.72 4.652 1.36 4.15 3.788l-11.698 56.621c-.732 3.542 3.979 5.473 5.943 2.437l1.313-2.028l72.516-144.72c1.215-2.423-.88-5.186-3.54-4.672l-25.505 4.922c-2.396.462-4.435-1.77-3.759-4.114l16.646-57.705c.677-2.35-1.37-4.583-3.769-4.113Z\"></path></svg>',0,'vite.svg',NULL,NULL,'e7d7a8846f8046338a3c8849870ce131','FILE'),('ae1b62dbeb3041289d90b7cc802fd37d','2024-05-25 09:38:52','ACTIVE',NULL,'<svg xmlns=\"http://www.w3.org/2000/svg\" xmlns:xlink=\"http://www.w3.org/1999/xlink\" aria-hidden=\"true\" role=\"img\" class=\"iconify iconify--logos\" width=\"32\" height=\"32\" preserveAspectRatio=\"xMidYMid meet\" viewBox=\"0 0 256 256\"><path fill=\"#F7DF1E\" d=\"M0 0h256v256H0V0Z\"></path><path d=\"m67.312 213.932l19.59-11.856c3.78 6.701 7.218 12.371 15.465 12.371c7.905 0 12.89-3.092 12.89-15.12v-81.798h24.057v82.138c0 24.917-14.606 36.259-35.916 36.259c-19.245 0-30.416-9.967-36.087-21.996m85.07-2.576l19.588-11.341c5.157 8.421 11.859 14.607 23.715 14.607c9.969 0 16.325-4.984 16.325-11.858c0-8.248-6.53-11.17-17.528-15.98l-6.013-2.58c-17.357-7.387-28.87-16.667-28.87-36.257c0-18.044 13.747-31.792 35.228-31.792c15.294 0 26.292 5.328 34.196 19.247l-18.732 12.03c-4.125-7.389-8.591-10.31-15.465-10.31c-7.046 0-11.514 4.468-11.514 10.31c0 7.217 4.468 10.14 14.778 14.608l6.014 2.577c20.45 8.765 31.963 17.7 31.963 37.804c0 21.654-17.012 33.51-39.867 33.51c-22.339 0-36.774-10.654-43.819-24.574\"></path></svg>',0,'javascript.svg',NULL,NULL,'e7d7a8846f8046338a3c8849870ce131','FILE'),('b23a716d434b4b93a338b87ac3ed6c7b','2024-05-25 09:45:45','ACTIVE',NULL,':root {\r\n  font-family: Inter, system-ui, Avenir, Helvetica, Arial, sans-serif;\r\n  line-height: 1.5;\r\n  font-weight: 400;\r\n\r\n  color-scheme: light dark;\r\n  color: rgba(255, 255, 255, 0.87);\r\n  background-color: #242424;\r\n\r\n  font-synthesis: none;\r\n  text-rendering: optimizeLegibility;\r\n  -webkit-font-smoothing: antialiased;\r\n  -moz-osx-font-smoothing: grayscale;\r\n}\r\n\r\na {\r\n  font-weight: 500;\r\n  color: #646cff;\r\n  text-decoration: inherit;\r\n}\r\na:hover {\r\n  color: #535bf2;\r\n}\r\n\r\nbody {\r\n  margin: 0;\r\n  display: flex;\r\n  place-items: center;\r\n  min-width: 320px;\r\n  min-height: 100vh;\r\n}\r\n\r\nh1 {\r\n  font-size: 3.2em;\r\n  line-height: 1.1;\r\n}\r\n\r\n#app {\r\n  max-width: 1280px;\r\n  margin: 0 auto;\r\n  padding: 2rem;\r\n  text-align: center;\r\n}\r\n\r\n.logo {\r\n  height: 6em;\r\n  padding: 1.5em;\r\n  will-change: filter;\r\n  transition: filter 300ms;\r\n}\r\n.logo:hover {\r\n  filter: drop-shadow(0 0 2em #646cffaa);\r\n}\r\n.logo.vanilla:hover {\r\n  filter: drop-shadow(0 0 2em #f7df1eaa);\r\n}\r\n\r\n.card {\r\n  padding: 2em;\r\n}\r\n\r\n.read-the-docs {\r\n  color: #888;\r\n}\r\n\r\nbutton {\r\n  border-radius: 8px;\r\n  border: 1px solid transparent;\r\n  padding: 0.6em 1.2em;\r\n  font-size: 1em;\r\n  font-weight: 500;\r\n  font-family: inherit;\r\n  background-color: #1a1a1a;\r\n  cursor: pointer;\r\n  transition: border-color 0.25s;\r\n}\r\nbutton:hover {\r\n  border-color: #646cff;\r\n}\r\nbutton:focus,\r\nbutton:focus-visible {\r\n  outline: 4px auto -webkit-focus-ring-color;\r\n}\r\n\r\n@media (prefers-color-scheme: light) {\r\n  :root {\r\n    color: #213547;\r\n    background-color: #ffffff;\r\n  }\r\n  a:hover {\r\n    color: #747bff;\r\n  }\r\n  button {\r\n    background-color: #f9f9f9;\r\n  }\r\n}\r\n',0,'style.css',NULL,NULL,'e7d7a8846f8046338a3c8849870ce131','FILE'),('da43b0f6bf2b472b81c68e0ce0f25008','2024-05-25 09:37:54','ACTIVE',NULL,'# Logs\r\nlogs\r\n*.log\r\nnpm-debug.log*\r\nyarn-debug.log*\r\nyarn-error.log*\r\npnpm-debug.log*\r\nlerna-debug.log*\r\n\r\nnode_modules\r\ndist\r\ndist-ssr\r\n*.local\r\n\r\n# Editor directories and files\r\n.vscode/*\r\n!.vscode/extensions.json\r\n.idea\r\n.DS_Store\r\n*.suo\r\n*.ntvs*\r\n*.njsproj\r\n*.sln\r\n*.sw?\r\n',0,'.gitignore',NULL,NULL,'e7d7a8846f8046338a3c8849870ce131','FILE'),('f9a5c3166ec041e3889afff60e07e6a1','2024-05-25 09:34:26','ACTIVE',NULL,'const a = 100',0,'test.js',NULL,NULL,'e7d7a8846f8046338a3c8849870ce131','FILE'),('f9a5c3166ec041e3889afff60e07e6a2','2024-05-25 09:34:26','ACTIVE',NULL,'export * from \'./Button\'',2,'index.js','f9a5c3166ec041e3889afff60e07e6a3',NULL,'e7d7a8846f8046338a3c8849870ce131','FILE'),('f9a5c3166ec041e3889afff60e07e6a3','2024-05-25 09:34:26','ACTIVE',NULL,NULL,1,'components','f9a5c3166ec041e3889afff60e07e6af',NULL,'e7d7a8846f8046338a3c8849870ce131','DIRECTORY'),('f9a5c3166ec041e3889afff60e07e6ac','2024-05-25 09:34:26','ACTIVE',NULL,'{\r\n  \"name\": \"vite-starter\",\r\n  \"private\": true,\r\n  \"version\": \"0.0.0\",\r\n  \"type\": \"module\",\r\n  \"scripts\": {\r\n    \"dev\": \"vite\",\r\n    \"build\": \"vite build\",\r\n    \"preview\": \"vite preview\"\r\n  },\r\n  \"devDependencies\": {\r\n    \"vite\": \"^5.2.11\"\r\n  }\r\n}\r\n',0,'package.json',NULL,NULL,'e7d7a8846f8046338a3c8849870ce131','FILE'),('f9a5c3166ec041e3889afff60e07e6af','2024-05-25 09:34:26','ACTIVE',NULL,NULL,0,'src',NULL,NULL,'e7d7a8846f8046338a3c8849870ce131','DIRECTORY');
/*!40000 ALTER TABLE `file` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lesson`
--

DROP TABLE IF EXISTS `lesson`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lesson` (
  `id` varchar(32) NOT NULL,
  `created_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `system_status` enum('ACTIVE','INACTIVE') DEFAULT NULL,
  `updated_date` datetime(6) DEFAULT NULL,
  `chapter_id` varchar(255) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `content` text,
  `document` text,
  `duration` bigint DEFAULT NULL,
  `lesson_status` enum('DONE','LOCKED','UNLOCKED') DEFAULT NULL,
  `lesson_type` enum('VIDEO','DOCUMENT','QUESTION') DEFAULT NULL,
  `prev_lesson_id` varchar(255) DEFAULT NULL,
  `question` text,
  `slug` text,
  `thumbnail_url` text,
  `title` varchar(255) DEFAULT NULL,
  `video_id` varchar(255) DEFAULT NULL,
  `numerical_order` int DEFAULT NULL,
  `tp_editor_url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lesson`
--

LOCK TABLES `lesson` WRITE;
/*!40000 ALTER TABLE `lesson` DISABLE KEYS */;
INSERT INTO `lesson` VALUES ('0e0fdff809f745898adb1dbb966ff72b','2024-12-12 21:50:41','ACTIVE',NULL,'776f476e222b47dfbdc38cf750c66cf8',NULL,'<p>&nbsp;</p>\n<h1>C&agrave;i đặt m&ocirc;i trường: C&ocirc;ng cụ cần thiết để bắt đầu học HTML v&agrave; CSS</h1>\n<h2>1. Tr&igrave;nh soạn thảo m&atilde; nguồn (Code Editor)</h2>\n<p>Để viết m&atilde; HTML v&agrave; CSS, bạn cần một tr&igrave;nh soạn thảo m&atilde; nguồn. Dưới đ&acirc;y l&agrave; một số tr&igrave;nh soạn thảo phổ biến:</p>\n<h3><strong>Visual Studio Code</strong></h3>\n<ul>\n<li>Tr&igrave;nh soạn thảo m&atilde; nguồn miễn ph&iacute; v&agrave; mạnh mẽ, hỗ trợ nhiều ng&ocirc;n ngữ lập tr&igrave;nh.</li>\n<li>T&iacute;nh năng nổi bật: Gợi &yacute; m&atilde;, highlight c&uacute; ph&aacute;p, t&iacute;ch hợp Git.</li>\n<li><strong>C&aacute;ch c&agrave;i đặt</strong>:\n<ol>\n<li>Truy cập trang <a href=\"https://code.visualstudio.com/\">Visual Studio Code</a>.</li>\n<li>Tải phi&ecirc;n bản ph&ugrave; hợp với hệ điều h&agrave;nh của bạn (Windows, macOS, Linux).</li>\n<li>C&agrave;i đặt theo hướng dẫn.</li>\n</ol>\n</li>\n</ul>\n<h3><strong>Alternatives</strong></h3>\n<ul>\n<li><strong>Sublime Text</strong>: Nhẹ, nhanh, hỗ trợ nhiều plugin.</li>\n<li><strong>Notepad++</strong>: Ph&ugrave; hợp cho người mới bắt đầu, dễ sử dụng.</li>\n<li><strong>WebStorm</strong>: Trả ph&iacute;, mạnh mẽ, t&iacute;ch hợp nhiều c&ocirc;ng cụ.</li>\n</ul>\n<hr>\n<h2>2. Tr&igrave;nh duyệt web</h2>\n<p>HTML v&agrave; CSS được hiển thị tr&ecirc;n tr&igrave;nh duyệt web. Bạn cần một tr&igrave;nh duyệt hiện đại để kiểm tra m&atilde;:</p>\n<h3><strong>Google Chrome</strong></h3>\n<ul>\n<li>Phổ biến, hỗ trợ tốt c&aacute;c t&iacute;nh năng Dev Tools để kiểm tra v&agrave; gỡ lỗi.</li>\n<li><strong>Tải tại</strong>: <a href=\"https://www.google.com/chrome/\">Google Chrome</a>.</li>\n</ul>\n<h3><strong>Alternatives</strong></h3>\n<ul>\n<li><strong>Mozilla Firefox</strong>: Tối ưu cho c&aacute;c c&ocirc;ng cụ ph&aacute;t triển.</li>\n<li><strong>Microsoft Edge</strong>: Dựa tr&ecirc;n Chromium, tương th&iacute;ch tốt.</li>\n</ul>\n<hr>\n<h2>3. C&ocirc;ng cụ Dev Tools</h2>\n<p>Dev Tools t&iacute;ch hợp sẵn trong tr&igrave;nh duyệt web, gi&uacute;p bạn:</p>\n<ul>\n<li>Xem v&agrave; chỉnh sửa m&atilde; HTML/CSS trực tiếp tr&ecirc;n tr&igrave;nh duyệt.</li>\n<li>Gỡ lỗi v&agrave; kiểm tra cấu tr&uacute;c DOM.</li>\n<li><strong>C&aacute;ch mở Dev Tools</strong>:\n<ul>\n<li><strong>Chrome/Edge</strong>: Nhấn <code>F12</code> hoặc <code>Ctrl + Shift + I</code> (Windows) / <code>Cmd + Option + I</code> (Mac).</li>\n<li><strong>Firefox</strong>: Nhấn <code>F12</code>.</li>\n</ul>\n</li>\n</ul>\n<hr>\n<h2>4. C&agrave;i đặt m&ocirc;i trường l&agrave;m việc</h2>\n<h3><strong>Thư mục dự &aacute;n</strong></h3>\n<ol>\n<li>Tạo một thư mục để lưu trữ c&aacute;c file HTML, CSS.</li>\n<li>Cấu tr&uacute;c cơ bản:</li>\n</ol>','',100,'DONE','VIDEO','3282f657a31d4c15ad7cd742068a0ffd',NULL,'cai-dat-vs-code-page-ruler-extension','course/lnrbzdroycyw3vljmzjz','Cài đặt VS Code, Page Ruler extension','LYnrFSGLCl8',0,NULL),('0f6fe77db7204bea88b07b682988a531','2024-12-13 10:49:41','ACTIVE',NULL,'e0138a1d35a54bf0a0afd5552eff1771',NULL,'<h1>ReactJS l&agrave; g&igrave;? Tại sao n&ecirc;n học ReactJS?</h1>\n<h2>ReactJS l&agrave; g&igrave;?</h2>\n<p>ReactJS l&agrave; một thư viện JavaScript m&atilde; nguồn mở được ph&aacute;t triển bởi Facebook, d&ugrave;ng để x&acirc;y dựng c&aacute;c giao diện người d&ugrave;ng (UI) cho c&aacute;c ứng dụng web. ReactJS gi&uacute;p việc ph&aacute;t triển giao diện trở n&ecirc;n dễ d&agrave;ng v&agrave; hiệu quả hơn nhờ v&agrave;o khả năng chia nhỏ giao diện th&agrave;nh c&aacute;c th&agrave;nh phần (components) c&oacute; thể t&aacute;i sử dụng. Với React, bạn c&oacute; thể x&acirc;y dựng c&aacute;c ứng dụng web động, dễ d&agrave;ng quản l&yacute; v&agrave; n&acirc;ng cấp.</p>\n<h2>Tại sao n&ecirc;n học ReactJS?</h2>\n<h3>1. <strong>T&iacute;nh phổ biến cao</strong></h3>\n<p>ReactJS hiện đang được sử dụng rộng r&atilde;i bởi c&aacute;c c&ocirc;ng ty lớn như Facebook, Instagram, Airbnb, Uber, Netflix v&agrave; nhiều c&ocirc;ng ty kh&aacute;c. Sự phổ biến của ReactJS tạo cơ hội nghề nghiệp rộng mở cho c&aacute;c lập tr&igrave;nh vi&ecirc;n web.</p>\n<h3>2. <strong>Cộng đồng mạnh mẽ</strong></h3>\n<p>Với cộng đồng đ&ocirc;ng đảo v&agrave; hỗ trợ li&ecirc;n tục từ Facebook, ReactJS lu&ocirc;n được cập nhật v&agrave; ph&aacute;t triển mạnh mẽ. Bạn sẽ dễ d&agrave;ng t&igrave;m thấy t&agrave;i liệu, giải đ&aacute;p thắc mắc v&agrave; c&aacute;c thư viện mở rộng gi&uacute;p tăng năng suất c&ocirc;ng việc.</p>\n<h3>3. <strong>Khả năng t&aacute;i sử dụng component</strong></h3>\n<p>ReactJS cho ph&eacute;p bạn x&acirc;y dựng c&aacute;c th&agrave;nh phần giao diện c&oacute; thể t&aacute;i sử dụng, gi&uacute;p giảm thiểu sự tr&ugrave;ng lặp m&atilde; v&agrave; tiết kiệm thời gian ph&aacute;t triển.</p>\n<h3>4. <strong>T&iacute;nh linh hoạt</strong></h3>\n<p>ReactJS c&oacute; thể được sử dụng trong nhiều loại dự &aacute;n kh&aacute;c nhau, từ c&aacute;c ứng dụng web nhỏ đến c&aacute;c ứng dụng phức tạp với h&agrave;ng triệu người d&ugrave;ng. React c&ograve;n c&oacute; thể kết hợp với c&aacute;c thư viện v&agrave; framework kh&aacute;c như Redux, React Router, hoặc Next.js để x&acirc;y dựng c&aacute;c ứng dụng phức tạp hơn.</p>\n<h3>5. <strong>Hỗ trợ cho Single Page Application (SPA)</strong></h3>\n<p>ReactJS rất th&iacute;ch hợp để x&acirc;y dựng c&aacute;c ứng dụng Single Page Application (SPA), gi&uacute;p cải thiện trải nghiệm người d&ugrave;ng với việc tải trang nhanh ch&oacute;ng m&agrave; kh&ocirc;ng phải tải lại to&agrave;n bộ trang web.</p>\n<h3>6. <strong>Hiệu suất cao</strong></h3>\n<p>ReactJS sử dụng Virtual DOM (DOM ảo) để tối ưu h&oacute;a việc cập nhật giao diện. Thay v&igrave; l&agrave;m mới to&agrave;n bộ DOM, React chỉ cập nhật c&aacute;c phần thay đổi, gi&uacute;p ứng dụng chạy mượt m&agrave; v&agrave; nhanh ch&oacute;ng.</p>\n<h3>7. <strong>Tương th&iacute;ch với c&aacute;c c&ocirc;ng nghệ kh&aacute;c</strong></h3>\n<p>React c&oacute; thể được t&iacute;ch hợp dễ d&agrave;ng với c&aacute;c c&ocirc;ng nghệ kh&aacute;c như Node.js, TypeScript, v&agrave; GraphQL, gi&uacute;p bạn x&acirc;y dựng c&aacute;c ứng dụng web hiện đại v&agrave; mạnh mẽ.</p>\n<h2>Kết luận</h2>\n<p>ReactJS l&agrave; một c&ocirc;ng cụ mạnh mẽ v&agrave; linh hoạt, gi&uacute;p c&aacute;c lập tr&igrave;nh vi&ecirc;n web x&acirc;y dựng giao diện người d&ugrave;ng hiệu quả. Với t&iacute;nh phổ biến, cộng đồng mạnh mẽ v&agrave; khả năng t&aacute;i sử dụng, ReactJS l&agrave; một kỹ năng quan trọng m&agrave; bạn n&ecirc;n học để ph&aacute;t triển trong ng&agrave;nh ph&aacute;t triển web.</p>','',100,'DONE','VIDEO',NULL,NULL,'reactjs-la-gi-tai-sao-nen-hoc-reactjs','course/pxdmuylhvakw1ryeqafa','ReactJS là gì? Tại sao nên học ReactJS?','TMA5ssSRdMg',0,NULL),('157d5b54be15459fa350d0c53ee061ac','2024-12-12 22:04:17','ACTIVE',NULL,'6d1c585f4b834f6798323663f6dcf35b',NULL,'<h1>C&agrave;i đặt m&ocirc;i trường: C&ocirc;ng cụ cần thiết để bắt đầu học JavaScript</h1>\n<h2>1. Tr&igrave;nh soạn thảo m&atilde; nguồn (Code Editor)</h2>\n<p>Để viết v&agrave; chạy JavaScript, bạn cần một tr&igrave;nh soạn thảo m&atilde; nguồn. Dưới đ&acirc;y l&agrave; c&aacute;c lựa chọn phổ biến:</p>\n<h3><strong>Visual Studio Code</strong></h3>\n<ul>\n<li>Tr&igrave;nh soạn thảo miễn ph&iacute;, mạnh mẽ, hỗ trợ tốt cho JavaScript.</li>\n<li><strong>T&iacute;nh năng nổi bật</strong>:\n<ul>\n<li>Gợi &yacute; m&atilde; th&ocirc;ng minh (IntelliSense).</li>\n<li>T&iacute;ch hợp terminal để chạy JavaScript trực tiếp.</li>\n<li>Hỗ trợ nhiều plugin cho JavaScript như ESLint, Prettier.</li>\n</ul>\n</li>\n<li><strong>C&aacute;ch c&agrave;i đặt</strong>:\n<ol>\n<li>Truy cập <a href=\"https://code.visualstudio.com/\">Visual Studio Code</a>.</li>\n<li>Tải xuống phi&ecirc;n bản ph&ugrave; hợp với hệ điều h&agrave;nh của bạn.</li>\n<li>C&agrave;i đặt v&agrave; mở ứng dụng.</li>\n</ol>\n</li>\n</ul>\n<h3><strong>Alternatives</strong></h3>\n<ul>\n<li><strong>WebStorm</strong>: IDE trả ph&iacute;, tối ưu cho JavaScript.</li>\n<li><strong>Sublime Text</strong>: Nhẹ, dễ sử dụng.</li>\n<li><strong>Notepad++</strong>: Ph&ugrave; hợp cho người mới bắt đầu.</li>\n</ul>\n<hr>\n<h2>2. Tr&igrave;nh duyệt web</h2>\n<p>JavaScript chủ yếu chạy tr&ecirc;n tr&igrave;nh duyệt web. Một tr&igrave;nh duyệt hiện đại gi&uacute;p bạn dễ d&agrave;ng kiểm tra v&agrave; gỡ lỗi m&atilde;:</p>\n<h3><strong>Google Chrome</strong></h3>\n<ul>\n<li>Phổ biến nhất với Dev Tools mạnh mẽ.</li>\n<li><strong>Tải tại</strong>: <a href=\"https://www.google.com/chrome/\">Google Chrome</a>.</li>\n</ul>\n<h3><strong>Alternatives</strong></h3>\n<ul>\n<li><strong>Mozilla Firefox</strong>: C&oacute; c&aacute;c c&ocirc;ng cụ ph&aacute;t triển tốt.</li>\n<li><strong>Microsoft Edge</strong>: Dựa tr&ecirc;n Chromium, tương th&iacute;ch tốt.</li>\n</ul>\n<hr>\n<h2>3. C&ocirc;ng cụ Dev Tools</h2>\n<p>Dev Tools được t&iacute;ch hợp sẵn trong tr&igrave;nh duyệt, hỗ trợ:</p>\n<ul>\n<li>Thực thi trực tiếp m&atilde; JavaScript.</li>\n<li>Gỡ lỗi v&agrave; kiểm tra trạng th&aacute;i biến.</li>\n<li>Xem c&aacute;c sự kiện xảy ra tr&ecirc;n trang web.</li>\n</ul>\n<p><strong>C&aacute;ch mở Dev Tools</strong>:</p>\n<ul>\n<li><strong>Chrome/Edge</strong>: Nhấn <code>F12</code> hoặc <code>Ctrl + Shift + I</code> (Windows) / <code>Cmd + Option + I</code> (Mac).</li>\n<li><strong>Firefox</strong>: Nhấn <code>F12</code>.</li>\n</ul>\n<hr>\n<h2>4. C&agrave;i đặt Node.js</h2>\n<p><strong>Node.js</strong> l&agrave; một runtime JavaScript gi&uacute;p bạn chạy m&atilde; JavaScript ngo&agrave;i tr&igrave;nh duyệt.</p>\n<h3><strong>C&aacute;ch c&agrave;i đặt Node.js</strong>:</h3>\n<ol>\n<li>Truy cập <a href=\"https://nodejs.org/\">Node.js</a>.</li>\n<li>Tải phi&ecirc;n bản LTS (khuyến nghị cho người mới).</li>\n<li>C&agrave;i đặt theo hướng dẫn tr&ecirc;n hệ điều h&agrave;nh của bạn.</li>\n</ol>\n<p><strong>Kiểm tra c&agrave;i đặt</strong>:</p>\n<ul>\n<li>Mở terminal v&agrave; chạy lệnh:\n<pre class=\"language-bash\"><code>node -v</code></pre>\n</li>\n</ul>','',100,'DONE','VIDEO',NULL,NULL,'cai-dat-moi-truong','course/dk7w1tbnseunqgaj7nl1','Cài đặt môi trường','efI98nT8Ffo',0,NULL),('3282f657a31d4c15ad7cd742068a0ffd','2024-12-12 21:47:19','ACTIVE',NULL,'776f476e222b47dfbdc38cf750c66cf8',NULL,'<h1>L&agrave;m quen với Dev Tools</h1>\n<h2>Dev Tools l&agrave; g&igrave;?</h2>\n<p>Dev Tools (Developer Tools) l&agrave; một bộ c&ocirc;ng cụ được t&iacute;ch hợp sẵn trong c&aacute;c tr&igrave;nh duyệt web như Chrome, Firefox, Edge, v&agrave; Safari. N&oacute; hỗ trợ c&aacute;c nh&agrave; ph&aacute;t triển web kiểm tra, gỡ lỗi, v&agrave; tối ưu h&oacute;a c&aacute;c trang web hoặc ứng dụng web trực tiếp từ tr&igrave;nh duyệt.</p>\n<hr>\n<h2>Tại sao n&ecirc;n sử dụng Dev Tools?</h2>\n<p>Dev Tools mang lại nhiều lợi &iacute;ch quan trọng, bao gồm:</p>\n<ul>\n<li><strong>Kiểm tra cấu tr&uacute;c HTML v&agrave; CSS</strong>: Xem, chỉnh sửa v&agrave; thử nghiệm c&aacute;c phần tử trực tiếp tr&ecirc;n trang.</li>\n<li><strong>Gỡ lỗi JavaScript</strong>: Ph&aacute;t hiện v&agrave; sửa lỗi trong m&atilde; JavaScript.</li>\n<li><strong>Ph&acirc;n t&iacute;ch hiệu suất</strong>: Đo lường thời gian tải trang, kiểm tra hiệu suất v&agrave; tối ưu h&oacute;a tốc độ.</li>\n<li><strong>Kiểm tra khả năng tương th&iacute;ch</strong>: Giả lập c&aacute;c thiết bị kh&aacute;c nhau v&agrave; kiểm tra giao diện tr&ecirc;n nhiều k&iacute;ch thước m&agrave;n h&igrave;nh.</li>\n<li><strong>Theo d&otilde;i mạng (Network)</strong>: Ph&acirc;n t&iacute;ch c&aacute;c y&ecirc;u cầu HTTP v&agrave; t&agrave;i nguy&ecirc;n được tải bởi trang.</li>\n</ul>\n<hr>\n<h2>C&aacute;c t&iacute;nh năng ch&iacute;nh của Dev Tools</h2>\n<h3>1. <strong>Elements</strong></h3>\n<ul>\n<li>Xem v&agrave; chỉnh sửa HTML, CSS.</li>\n<li>Kiểm tra c&aacute;c lớp CSS đang &aacute;p dụng v&agrave;o phần tử.</li>\n<li>Highlight cấu tr&uacute;c DOM.</li>\n</ul>\n<h3>2. <strong>Console</strong></h3>\n<ul>\n<li>Ghi log th&ocirc;ng tin từ JavaScript.</li>\n<li>Thực thi trực tiếp c&aacute;c đoạn m&atilde;.</li>\n<li>Kiểm tra v&agrave; gỡ lỗi.</li>\n</ul>\n<h3>3. <strong>Network</strong></h3>\n<ul>\n<li>Theo d&otilde;i c&aacute;c y&ecirc;u cầu HTTP/HTTPS.</li>\n<li>Kiểm tra trạng th&aacute;i v&agrave; thời gian phản hồi của c&aacute;c t&agrave;i nguy&ecirc;n.</li>\n</ul>\n<h3>4. <strong>Performance</strong></h3>\n<ul>\n<li>Ghi lại v&agrave; ph&acirc;n t&iacute;ch thời gian thực thi m&atilde;.</li>\n<li>T&igrave;m c&aacute;c điểm thắt cổ chai về hiệu suất.</li>\n</ul>\n<h3>5. <strong>Application</strong></h3>\n<ul>\n<li>Kiểm tra dữ liệu lưu trữ: Local Storage, Session Storage, Cookies.</li>\n<li>Quản l&yacute; Service Workers v&agrave; PWA.</li>\n</ul>\n<hr>\n<h2>Mở Dev Tools như thế n&agrave;o?</h2>\n<ul>\n<li><strong>Google Chrome</strong>:\n<ul>\n<li>Nhấn chuột phải v&agrave;o trang &rarr; Chọn \"Inspect\" (Kiểm tra).</li>\n<li>Hoặc nhấn <code>F12</code> hoặc tổ hợp ph&iacute;m <code>Ctrl + Shift + I</code> (Windows) / <code>Cmd + Option + I</code> (Mac).</li>\n</ul>\n</li>\n<li><strong>Firefox</strong>:\n<ul>\n<li>Nhấn chuột phải v&agrave;o trang &rarr; Chọn \"Inspect\".</li>\n<li>Hoặc nhấn <code>F12</code>.</li>\n</ul>\n</li>\n</ul>\n<hr>\n<h2>Thực h&agrave;nh cơ bản</h2>\n<h3>Kiểm tra phần tử HTML:</h3>\n<ol>\n<li>Mở tab <strong>Elements</strong>.</li>\n<li>Di chuột qua c&aacute;c thẻ để highlight c&aacute;c phần tử tr&ecirc;n trang.</li>\n</ol>\n<h3>Sửa CSS trực tiếp:</h3>\n<ol>\n<li>Chọn một phần tử trong tab <strong>Elements</strong>.</li>\n<li>Chỉnh sửa thuộc t&iacute;nh CSS trong bảng <strong>Styles</strong>.</li>\n</ol>\n<hr>\n<h2>T&oacute;m lại</h2>\n<p>Dev Tools l&agrave; c&ocirc;ng cụ mạnh mẽ gi&uacute;p c&aacute;c nh&agrave; ph&aacute;t triển web kiểm tra v&agrave; tối ưu h&oacute;a trang web hiệu quả. L&agrave;m quen với c&aacute;c t&iacute;nh năng n&agrave;y sẽ n&acirc;ng cao đ&aacute;ng kể năng suất v&agrave; chất lượng c&ocirc;ng việc.</p>','',100,'DONE','DOCUMENT','57386417e7ce4fc291741d6e4783295b',NULL,'lam-quen-voi-devtool','course/mxjlnhfvedf3runv2g33','Làm quen với Devtool',NULL,0,NULL),('3a19d0fba11a4ef49789afcfa72f4f37','2024-12-12 21:34:14','ACTIVE',NULL,'776f476e222b47dfbdc38cf750c66cf8',NULL,'','',100,'DONE','VIDEO',NULL,NULL,'ban-se-hoc-duoc-gi-tu-khoa-hoc','course/n5qgkreruobmva53gn6v','Bạn sẽ học được gì từ khoá học','AcJVy0C5WAE',0,NULL),('57386417e7ce4fc291741d6e4783295b','2024-12-12 21:39:34','ACTIVE',NULL,'776f476e222b47dfbdc38cf750c66cf8','{\"index.html\":{\"value\":\"<h1>Chào mừng đến với TechPlatform</h1>\",\"language\":\"html\"},\"style.css\":{\"value\":\"h1 {color: red;}\",\"language\":\"css\"}}','','',100,'DONE','VIDEO','3a19d0fba11a4ef49789afcfa72f4f37',NULL,'tim-hieu-ve-html-css','course/g9vjznalaelmi9epgmyz','Tìm hiểu về HTML, CSS','OZW0K9XmMSI',0,NULL),('cca2a4b58581462b93baac136f641ff4','2024-12-13 02:32:57','ACTIVE',NULL,'6d1c585f4b834f6798323663f6dcf35b',NULL,'<h1>Giới thiệu hệ thống Techplatform Editor</h1>\n<p>Hệ thống <strong>Techplatform Editor</strong> l&agrave; một c&ocirc;ng cụ mạnh mẽ được ph&aacute;t triển để hỗ trợ người d&ugrave;ng trong việc tạo, chỉnh sửa v&agrave; quản l&yacute; c&aacute;c nội dung tr&ecirc;n nền tảng <strong>Techplatform</strong>. Với giao diện th&acirc;n thiện v&agrave; t&iacute;nh năng đa dạng, Techplatform Editor gi&uacute;p người d&ugrave;ng dễ d&agrave;ng thao t&aacute;c, tối ưu h&oacute;a quy tr&igrave;nh l&agrave;m việc v&agrave; n&acirc;ng cao hiệu quả c&ocirc;ng việc.</p>\n<h2>T&iacute;nh năng nổi bật</h2>\n<ol>\n<li>\n<p><strong>Giao diện trực quan</strong>: Hệ thống cung cấp giao diện dễ sử dụng, với c&aacute;c thanh c&ocirc;ng cụ dễ tiếp cận, gi&uacute;p người d&ugrave;ng thực hiện c&aacute;c thao t&aacute;c nhanh ch&oacute;ng v&agrave; ch&iacute;nh x&aacute;c.</p>\n</li>\n<li>\n<p><strong>Chỉnh sửa nội dung đa dạng</strong>: Cho ph&eacute;p người d&ugrave;ng chỉnh sửa c&aacute;c văn bản, h&igrave;nh ảnh, video v&agrave; c&aacute;c loại nội dung kh&aacute;c một c&aacute;ch linh hoạt, gi&uacute;p tối ưu h&oacute;a trải nghiệm người d&ugrave;ng.</p>\n</li>\n<li>\n<p><strong>Quản l&yacute; dự &aacute;n hiệu quả</strong>: Hệ thống t&iacute;ch hợp c&aacute;c c&ocirc;ng cụ quản l&yacute; dự &aacute;n, cho ph&eacute;p người d&ugrave;ng theo d&otilde;i tiến độ c&ocirc;ng việc, ph&acirc;n c&ocirc;ng nhiệm vụ v&agrave; cộng t&aacute;c với c&aacute;c th&agrave;nh vi&ecirc;n kh&aacute;c.</p>\n</li>\n<li>\n<p><strong>T&iacute;nh năng hỗ trợ đa ng&ocirc;n ngữ</strong>: Techplatform Editor hỗ trợ nhiều ng&ocirc;n ngữ, gi&uacute;p người d&ugrave;ng c&oacute; thể l&agrave;m việc tr&ecirc;n c&aacute;c dự &aacute;n quốc tế m&agrave; kh&ocirc;ng gặp phải kh&oacute; khăn về r&agrave;o cản ng&ocirc;n ngữ.</p>\n</li>\n<li>\n<p><strong>Bảo mật v&agrave; bảo vệ dữ liệu</strong>: Hệ thống đảm bảo bảo mật th&ocirc;ng tin, bảo vệ dữ liệu người d&ugrave;ng với c&aacute;c phương thức m&atilde; h&oacute;a v&agrave; quyền truy cập được ph&acirc;n quyền r&otilde; r&agrave;ng.</p>\n</li>\n<li>\n<p><strong>T&iacute;ch hợp API mạnh mẽ</strong>: Hệ thống hỗ trợ t&iacute;ch hợp API cho ph&eacute;p kết nối với c&aacute;c c&ocirc;ng cụ v&agrave; dịch vụ b&ecirc;n ngo&agrave;i, gi&uacute;p mở rộng khả năng v&agrave; t&iacute;nh linh hoạt của hệ thống.</p>\n</li>\n</ol>\n<h2>C&aacute;c ứng dụng của hệ thống</h2>\n<ul>\n<li><strong>Quản l&yacute; nội dung web</strong>: Techplatform Editor gi&uacute;p c&aacute;c quản trị vi&ecirc;n v&agrave; bi&ecirc;n tập vi&ecirc;n dễ d&agrave;ng cập nhật v&agrave; quản l&yacute; nội dung cho c&aacute;c trang web, blogs v&agrave; ứng dụng.</li>\n<li><strong>Quản l&yacute; dự &aacute;n phần mềm</strong>: Hệ thống hỗ trợ c&aacute;c nh&agrave; ph&aacute;t triển phần mềm trong việc quản l&yacute; t&agrave;i liệu dự &aacute;n, m&ocirc; h&igrave;nh ph&aacute;t triển, v&agrave; c&aacute;c t&agrave;i nguy&ecirc;n phần mềm kh&aacute;c.</li>\n<li><strong>Tạo v&agrave; chia sẻ t&agrave;i liệu</strong>: Với khả năng tạo v&agrave; chỉnh sửa t&agrave;i liệu nhanh ch&oacute;ng, Techplatform Editor gi&uacute;p người d&ugrave;ng dễ d&agrave;ng chia sẻ th&ocirc;ng tin, b&aacute;o c&aacute;o v&agrave; t&agrave;i liệu c&ocirc;ng việc trong m&ocirc;i trường l&agrave;m việc nh&oacute;m.</li>\n</ul>\n<h2>Kết luận</h2>\n<p><strong>Techplatform Editor</strong> l&agrave; một c&ocirc;ng cụ l&yacute; tưởng cho những ai đang t&igrave;m kiếm một giải ph&aacute;p to&agrave;n diện v&agrave; hiệu quả để quản l&yacute; v&agrave; tạo nội dung tr&ecirc;n nền tảng của m&igrave;nh. Với c&aacute;c t&iacute;nh năng mạnh mẽ v&agrave; dễ sử dụng, hệ thống n&agrave;y gi&uacute;p người d&ugrave;ng tiết kiệm thời gian, n&acirc;ng cao năng suất v&agrave; đảm bảo c&ocirc;ng việc được ho&agrave;n th&agrave;nh một c&aacute;ch ch&iacute;nh x&aacute;c v&agrave; nhanh ch&oacute;ng.</p>','',100,'DONE','VIDEO','157d5b54be15459fa350d0c53ee061ac',NULL,'gioi-thieu-he-thong-techplatform-editor','course/gi9lrx4n22ydgayfryqx','Giới thiệu Hệ thống Techplatform Editor','qBkSHEBuENE',0,'https://editor.techplatform.click/editor/vanilajs');
/*!40000 ALTER TABLE `lesson` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lesson_answer`
--

DROP TABLE IF EXISTS `lesson_answer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lesson_answer` (
  `id` varchar(32) NOT NULL,
  `created_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `system_status` enum('ACTIVE','INACTIVE') DEFAULT NULL,
  `updated_date` datetime(6) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `is_correct` bit(1) DEFAULT NULL,
  `lesson_id` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lesson_answer`
--

LOCK TABLES `lesson_answer` WRITE;
/*!40000 ALTER TABLE `lesson_answer` DISABLE KEYS */;
/*!40000 ALTER TABLE `lesson_answer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lesson_question`
--

DROP TABLE IF EXISTS `lesson_question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lesson_question` (
  `id` varchar(32) NOT NULL,
  `created_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `system_status` enum('ACTIVE','INACTIVE') DEFAULT NULL,
  `updated_date` datetime(6) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `is_correct` bit(1) DEFAULT NULL,
  `lesson_id` varchar(255) DEFAULT NULL,
  `question` text,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lesson_question`
--

LOCK TABLES `lesson_question` WRITE;
/*!40000 ALTER TABLE `lesson_question` DISABLE KEYS */;
/*!40000 ALTER TABLE `lesson_question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `news_feed`
--

DROP TABLE IF EXISTS `news_feed`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `news_feed` (
  `id` varchar(32) NOT NULL,
  `created_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `system_status` enum('ACTIVE','INACTIVE') DEFAULT NULL,
  `updated_date` datetime(6) DEFAULT NULL,
  `content` text,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `news_feed`
--

LOCK TABLES `news_feed` WRITE;
/*!40000 ALTER TABLE `news_feed` DISABLE KEYS */;
/*!40000 ALTER TABLE `news_feed` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notification`
--

DROP TABLE IF EXISTS `notification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notification` (
  `id` varchar(32) NOT NULL,
  `created_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `system_status` enum('ACTIVE','INACTIVE') DEFAULT NULL,
  `updated_date` datetime(6) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `linked` varchar(255) DEFAULT NULL,
  `seen` bit(1) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notification`
--

LOCK TABLES `notification` WRITE;
/*!40000 ALTER TABLE `notification` DISABLE KEYS */;
/*!40000 ALTER TABLE `notification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project`
--

DROP TABLE IF EXISTS `project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `project` (
  `id` varchar(32) NOT NULL,
  `created_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `system_status` enum('ACTIVE','INACTIVE') DEFAULT NULL,
  `updated_date` datetime(6) DEFAULT NULL,
  `description` text,
  `is_template` tinyint(1) NOT NULL DEFAULT '0',
  `mode` enum('PUBLIC','PRIVATE','READONLY') DEFAULT NULL,
  `slug` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `type` enum('REACT_VITE') DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project`
--

LOCK TABLES `project` WRITE;
/*!40000 ALTER TABLE `project` DISABLE KEYS */;
INSERT INTO `project` VALUES ('e7d7a8846f8046338a3c8849870ce130','2024-05-16 12:28:36','ACTIVE',NULL,'react',0,'PUBLIC','REACT_VITE','','REACT_VITE','1'),('e7d7a8846f8046338a3c8849870ce131','2024-05-16 12:28:36','ACTIVE',NULL,'react',0,'PUBLIC','vanilajs','176bc1e3ae6a44f0b3e48efb86aeba56','REACT_VITE','0');
/*!40000 ALTER TABLE `project` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `session`
--

DROP TABLE IF EXISTS `session`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `session` (
  `id` varchar(32) NOT NULL,
  `created_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `system_status` enum('ACTIVE','INACTIVE') DEFAULT NULL,
  `updated_date` datetime(6) DEFAULT NULL,
  `data` text,
  `expire_time` bigint DEFAULT NULL,
  `session_type` enum('PRIMARY','FORGOT_PASSWORD','VERIFY_SIGNUP','GOOGLE_LOGIN','PAYMENT_COURSE') DEFAULT NULL,
  `user_id` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `session`
--

LOCK TABLES `session` WRITE;
/*!40000 ALTER TABLE `session` DISABLE KEYS */;
INSERT INTO `session` VALUES ('0e1097f021f344d5a25626dc8b2be259','2024-12-08 00:51:25','ACTIVE',NULL,NULL,1734249085009,'PRIMARY','d17665f8ed424a78a292e1266e876795'),('1f5d4549888541b0ab01d592718270cc','2024-12-13 12:13:20','ACTIVE',NULL,NULL,1734696800162,'GOOGLE_LOGIN','3717bc7983e946ef86aea677d29025f4'),('224fee5690e843a0a8c2b2d6c72daa12','2024-12-07 20:53:26','ACTIVE',NULL,NULL,1734234806251,'PRIMARY','d17665f8ed424a78a292e1266e876795'),('5da3f849538a48078f3555c94735389b','2024-12-09 18:30:37','ACTIVE',NULL,NULL,1734399036674,'GOOGLE_LOGIN','d17665f8ed424a78a292e1266e876795'),('932e1f93b5f24b4a98413962af291b83','2024-12-13 10:32:51','ACTIVE',NULL,NULL,1734690771234,'GOOGLE_LOGIN','d17665f8ed424a78a292e1266e876795'),('9cd36b04a97e4dc0bc5cd4f980fee695','2024-12-10 09:43:59','ACTIVE',NULL,NULL,1734453838937,'PRIMARY','d17665f8ed424a78a292e1266e876795'),('a4a5a456c38d4cd5b0fae8b4ad90b957','2024-12-13 12:39:53','ACTIVE',NULL,NULL,1734698392949,'PRIMARY','d17665f8ed424a78a292e1266e876795'),('b2f66abf46974e0ab4ff8a066ffdcd37','2024-12-13 11:03:01','ACTIVE',NULL,NULL,1734692581423,'PRIMARY','d17665f8ed424a78a292e1266e876795'),('ce2a47884f7343e69bcf05e24f4e21a1','2024-12-13 10:31:06','INACTIVE','2024-12-13 11:02:43.794000',NULL,1734690665701,'PRIMARY','d17665f8ed424a78a292e1266e876795');
/*!40000 ALTER TABLE `session` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaction`
--

DROP TABLE IF EXISTS `transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transaction` (
  `id` varchar(32) NOT NULL,
  `created_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `system_status` enum('ACTIVE','INACTIVE') DEFAULT NULL,
  `updated_date` datetime(6) DEFAULT NULL,
  `amount` float DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  `reference_id` varchar(255) DEFAULT NULL,
  `status` enum('SUCCEED','FAILED','PENDING') DEFAULT NULL,
  `transaction_type` enum('BUY_COURSE') DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction`
--

LOCK TABLES `transaction` WRITE;
/*!40000 ALTER TABLE `transaction` DISABLE KEYS */;
INSERT INTO `transaction` VALUES ('0077307780dc4b88b128aef8e88ccf02','2024-12-12 22:26:10','ACTIVE',NULL,10000,'KH TPIUSTK U userKDJQU E   Ma giaodich  Trace318587 Trace 318587','d112f302089d4bdf9f5f4fea708399e9','SUCCEED','BUY_COURSE','d17665f8ed424a78a292e1266e876795'),('01c922ab02af46d29863f3e31ac57c8e','2024-12-13 16:35:17','ACTIVE',NULL,100000,'KH TPDWZHA U userKDJQU E   Ma giaodich  Trace512585 Trace 512585','d64dffcb181a4af8aae61a1812e822d1','SUCCEED','BUY_COURSE','d17665f8ed424a78a292e1266e876795'),('0f57c443426540b08cbe8171f0b22de3','2024-12-12 22:43:49','ACTIVE',NULL,10000,'KH TPIUSTK U userKDJQU E   Ma giaodich  Trace348540 Trace 348540','d112f302089d4bdf9f5f4fea708399e9','SUCCEED','BUY_COURSE','d17665f8ed424a78a292e1266e876795'),('1914fc2da3984cf2b971ae06d572a5cd','2024-12-12 23:48:38','ACTIVE',NULL,10000,'KH TPIUSTK U userKDJQU E   Ma giaodich  Trace743114 Trace 743114','d112f302089d4bdf9f5f4fea708399e9','SUCCEED','BUY_COURSE','d17665f8ed424a78a292e1266e876795'),('29dbe68671fd4780beb7096666474cad','2024-12-13 10:39:09','ACTIVE',NULL,10000,'KH TP2F1L7 U USERKDJQU E- Ma GD ACSP/ mv488708','fcfdec192e5e4a528ecde3befb7648fa','SUCCEED','BUY_COURSE','d17665f8ed424a78a292e1266e876795'),('387b64549a7043f1a580d1069fd87bec','2024-12-07 07:26:13','ACTIVE',NULL,0,'BUY_COURSE','c762fdb23ee64dc7b5aecf3d243d833d','SUCCEED','BUY_COURSE','d17665f8ed424a78a292e1266e876795'),('3a30fc23f80846be9c24b740b6ed9782','2024-12-12 23:51:55','ACTIVE',NULL,10000,'KH TPIUSTK U userKDJQU E   Ma giaodich  Trace743114 Trace 743114','d112f302089d4bdf9f5f4fea708399e9','SUCCEED','BUY_COURSE','d17665f8ed424a78a292e1266e876795'),('4928a58fb30f4c38a6b26b69b7347b47','2024-12-12 22:29:19','ACTIVE',NULL,10000,'KH TPIUSTK U userKDJQU E   Ma giaodich  Trace348540 Trace 348540','d112f302089d4bdf9f5f4fea708399e9','SUCCEED','BUY_COURSE','d17665f8ed424a78a292e1266e876795'),('4b6c3751fb6348d5bfe5c3832c8a2be3','2024-12-07 12:15:17','ACTIVE',NULL,0,'BUY_COURSE','c762fdb23ee64dc7b5aecf3d243d833d','SUCCEED','BUY_COURSE','d17665f8ed424a78a292e1266e876795'),('54d0b69100df4f408a18b10f9b56e445','2024-12-07 08:14:03','ACTIVE',NULL,0,'BUY_COURSE','de42ec22e63d4158945a926cc1791c39','SUCCEED','BUY_COURSE','d17665f8ed424a78a292e1266e876795'),('72c4c26b06c54c7c8f0c0eceb9830a29','2024-12-13 11:06:06','ACTIVE',NULL,10000,'KH TPIUSTK U USERKDJQU E- Ma GD ACSP/ K6781000','d112f302089d4bdf9f5f4fea708399e9','SUCCEED','BUY_COURSE','d17665f8ed424a78a292e1266e876795'),('806052e9f6474fd7ae9a5873cac44ade','2024-12-12 22:08:36','ACTIVE',NULL,100000,'KH TPIUSTK U userKDJQU E   Ma giaodich  Trace220877 Trace 220877','d112f302089d4bdf9f5f4fea708399e9','SUCCEED','BUY_COURSE','d17665f8ed424a78a292e1266e876795'),('85a3f77e86e841c79b367c9792d3ca37','2024-12-13 01:43:06','ACTIVE',NULL,10000,'KH TPIUSTK U USERKDJQU E- Ma GD ACSP/ K6781000','d112f302089d4bdf9f5f4fea708399e9','SUCCEED','BUY_COURSE','d17665f8ed424a78a292e1266e876795'),('9aed3c1c7eee43658b688e5f067244f1','2024-12-12 22:22:52','ACTIVE',NULL,100000,'KH TPIUSTK U userKDJQU E   Ma giaodich  Trace310688 Trace 310688','d112f302089d4bdf9f5f4fea708399e9','SUCCEED','BUY_COURSE','d17665f8ed424a78a292e1266e876795'),('9eec28f8734a484a96f8be8382b5e681','2024-12-07 23:00:52','ACTIVE',NULL,0,'BUY_COURSE','46d18733975d442db90f938346d4ac3d','SUCCEED','BUY_COURSE','d17665f8ed424a78a292e1266e876795'),('a0bdc275383a43da82d24e5236c5a945','2024-12-12 22:24:26','ACTIVE',NULL,10000,'KH TPIUSTK U userKDJQU E   Ma giaodich  Trace318587 Trace 318587','d112f302089d4bdf9f5f4fea708399e9','SUCCEED','BUY_COURSE','d17665f8ed424a78a292e1266e876795'),('a1be12e1e23f46fba4283e3d38a6cb72','2024-12-12 23:58:13','ACTIVE',NULL,10000,'KH TPIUSTK U userKDJQU E   Ma giaodich  Trace743114 Trace 743114','d112f302089d4bdf9f5f4fea708399e9','SUCCEED','BUY_COURSE','d17665f8ed424a78a292e1266e876795'),('a7d913259a9f43b5a3ec3e811fedc4ab','2024-12-13 01:16:48','ACTIVE',NULL,10000,'KH TPIUSTK U userKDJQU E   Ma giaodich  Trace201659 Trace 201659','d112f302089d4bdf9f5f4fea708399e9','SUCCEED','BUY_COURSE','d17665f8ed424a78a292e1266e876795'),('afd216f1e07649c58f2e87a6ebdf5c16','2024-12-13 01:46:54','ACTIVE',NULL,0,'BUY_COURSE','46d18733975d442db90f938346d4ac3d','SUCCEED','BUY_COURSE','d17665f8ed424a78a292e1266e876795'),('c8b57659ee524c108ceb7ef4b410b538','2024-12-13 01:41:43','ACTIVE',NULL,10000,'KH TPUE9M6 U USERKDJQU E- Ma GD ACSP/ J8773890','5f44b88f7b5d4712a53041bb1abc4432','SUCCEED','BUY_COURSE','d17665f8ed424a78a292e1266e876795'),('cb053b798faa401485ebaeef7512ccd7','2024-12-13 01:39:31','ACTIVE',NULL,20000,'KH TPBIDVG U USERKDJQU E- Ma GD ACSP/ OG735441','3e3f1372074148fdabfc2888620fe62f','SUCCEED','BUY_COURSE','d17665f8ed424a78a292e1266e876795'),('e132372aa3e9407ab105151597808b89','2024-12-13 00:03:08','ACTIVE',NULL,10000,'KH TPIUSTK U userKDJQU E   Ma giaodich  Trace743114 Trace 743114','d112f302089d4bdf9f5f4fea708399e9','SUCCEED','BUY_COURSE','d17665f8ed424a78a292e1266e876795'),('e4ef7b671a7545ab8bbc2f44c0383a91','2024-12-07 07:47:27','ACTIVE',NULL,0,'BUY_COURSE','de42ec22e63d4158945a926cc1791c39','SUCCEED','BUY_COURSE','d17665f8ed424a78a292e1266e876795'),('ee16f7d5c9264e63aea2035a0fc8d014','2024-12-12 22:45:24','ACTIVE',NULL,10000,'KH TPIUSTK U userKDJQU E   Ma giaodich  Trace438831 Trace 438831','d112f302089d4bdf9f5f4fea708399e9','SUCCEED','BUY_COURSE','d17665f8ed424a78a292e1266e876795'),('fcbc6c5b029349d895edd08985e2778b','2024-12-13 01:45:30','ACTIVE',NULL,10000,'KH TPUE9M6 U USERKDJQU E- Ma GD ACSP/ SO793755','5f44b88f7b5d4712a53041bb1abc4432','SUCCEED','BUY_COURSE','d17665f8ed424a78a292e1266e876795'),('fdfb3bcceb024e9f8e535f291da47fce','2024-12-12 22:38:48','ACTIVE',NULL,10000,'KH TPIUSTK U userKDJQU E   Ma giaodich  Trace348540 Trace 348540','d112f302089d4bdf9f5f4fea708399e9','SUCCEED','BUY_COURSE','d17665f8ed424a78a292e1266e876795');
/*!40000 ALTER TABLE `transaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` varchar(32) NOT NULL,
  `created_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `system_status` enum('ACTIVE','INACTIVE') DEFAULT NULL,
  `updated_date` datetime(6) DEFAULT NULL,
  `bio` text,
  `cover_image` text,
  `date_of_birth` bigint DEFAULT NULL,
  `email` varchar(120) DEFAULT NULL,
  `first_name` varchar(50) DEFAULT NULL,
  `gender` enum('OTHER','MALE','FEMALE') DEFAULT NULL,
  `is_2_fa` tinyint(1) NOT NULL DEFAULT '0',
  `is_first` tinyint(1) NOT NULL DEFAULT '0',
  `last_ip_address` varchar(45) DEFAULT NULL,
  `last_login` datetime(6) DEFAULT NULL,
  `last_name` varchar(50) DEFAULT NULL,
  `password_hash` varchar(255) DEFAULT NULL,
  `password_salt` varchar(36) DEFAULT NULL,
  `phone_number` varchar(20) DEFAULT NULL,
  `profile_image` text,
  `profile_img_public_id` varchar(255) DEFAULT NULL,
  `user_role` enum('ADMIN','USER') DEFAULT NULL,
  `user_status` enum('VERIFIED','PENDING','BLOCKED','NONE','ACTIVE','INACTIVE') DEFAULT NULL,
  `username` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('3717bc7983e946ef86aea677d29025f4','2024-12-13 12:13:20','ACTIVE','2024-12-13 12:13:56.613000',NULL,NULL,NULL,'duc.dang@evoluter.com','Duc Dang',NULL,0,0,'103.238.72.47',NULL,NULL,NULL,NULL,NULL,'https://lh3.googleusercontent.com/a/ACg8ocLAplVvBff9WgQEZOXFTboCTuU5IwjwQiXEj1AT33d96H0rVA=s96-c',NULL,'USER','ACTIVE','user3cHzs'),('3c7d74347d3c4bb0b8b3694e064570e1','2024-12-04 15:44:20','ACTIVE','2024-12-04 22:44:37.015000',NULL,NULL,NULL,'duyduc-dev@yopmail.com','Hlu',NULL,0,0,'0:0:0:0:0:0:0:1','2024-12-04 22:44:31.422000','Nguyen','$2a$10$5ugrsSHUIwTrCqZSfRzbXuZeIqUGeSSZxD0.ZQsIq5zBMPr90bdbm','+v2re1TV',NULL,NULL,NULL,'ADMIN','ACTIVE','user0MUZN'),('d17665f8ed424a78a292e1266e876795','2024-11-30 23:07:43','ACTIVE','2024-12-13 14:44:13.817000',NULL,NULL,NULL,'dangduyduc1908@gmail.com','Duc',NULL,0,0,'2001:ee0:4f01:ecc0:70e9:efa0:12f5:8f61','2024-12-13 12:39:52.936000','Dang','$2a$10$l.9NRQjSfCZSvh0hWDyrSOFwzJSSIxZkHyHiTbPMSJHzJ7Xt088MO','CCnhZ7hh',NULL,NULL,NULL,'ADMIN','ACTIVE','userKDJQU');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_course`
--

DROP TABLE IF EXISTS `user_course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_course` (
  `id` varchar(32) NOT NULL,
  `created_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `system_status` enum('ACTIVE','INACTIVE') DEFAULT NULL,
  `updated_date` datetime(6) DEFAULT NULL,
  `course_id` varchar(255) DEFAULT NULL,
  `current_lesson_id` varchar(255) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_course`
--

LOCK TABLES `user_course` WRITE;
/*!40000 ALTER TABLE `user_course` DISABLE KEYS */;
INSERT INTO `user_course` VALUES ('7faef749d8624efcbbddcb0c9dad9c26','2024-12-13 01:45:30','ACTIVE',NULL,'5f44b88f7b5d4712a53041bb1abc4432',NULL,'d17665f8ed424a78a292e1266e876795'),('91dc90397a98417898eb1b7bb4386dd4','2024-12-13 01:46:54','ACTIVE',NULL,'46d18733975d442db90f938346d4ac3d',NULL,'d17665f8ed424a78a292e1266e876795'),('9bd1b64eb83b4237bfc6a4349015cade','2024-12-13 11:06:06','ACTIVE',NULL,'d112f302089d4bdf9f5f4fea708399e9','157d5b54be15459fa350d0c53ee061ac','d17665f8ed424a78a292e1266e876795'),('aa5faed26f844abbadaf5e4dd7ced67a','2024-12-13 10:39:09','ACTIVE',NULL,'fcfdec192e5e4a528ecde3befb7648fa',NULL,'d17665f8ed424a78a292e1266e876795'),('da5decc9aed24bdd927b171173ba91e2','2024-12-13 16:35:17','ACTIVE',NULL,'d64dffcb181a4af8aae61a1812e822d1',NULL,'d17665f8ed424a78a292e1266e876795');
/*!40000 ALTER TABLE `user_course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'techplatform'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-12-13 23:37:42
