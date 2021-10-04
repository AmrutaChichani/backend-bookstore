
CREATE TABLE `author` (
  `author_id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `author_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`author_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `publisher` (
  `publisher_id` int NOT NULL AUTO_INCREMENT,
  `contact_no` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`publisher_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `book` (
  `book_id` int NOT NULL AUTO_INCREMENT,
  `active_flag` tinyint(1) DEFAULT '1',
  `category` varchar(255) DEFAULT NULL,
  `book_name` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `publish_date` date DEFAULT NULL,
  `publisher_id` int DEFAULT NULL,
  PRIMARY KEY (`book_id`),
  KEY `FKgtvt7p649s4x80y6f4842pnfq` (`publisher_id`),
  CONSTRAINT `FKgtvt7p649s4x80y6f4842pnfq` FOREIGN KEY (`publisher_id`) REFERENCES `publisher` (`publisher_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `book_author` (
  `book_id` int NOT NULL,
  `author_id` int NOT NULL,
  KEY `FKbjqhp85wjv8vpr0beygh6jsgo` (`author_id`),
  KEY `FKhwgu59n9o80xv75plf9ggj7xn` (`book_id`),
  CONSTRAINT `FKbjqhp85wjv8vpr0beygh6jsgo` FOREIGN KEY (`author_id`) REFERENCES `author` (`author_id`),
  CONSTRAINT `FKhwgu59n9o80xv75plf9ggj7xn` FOREIGN KEY (`book_id`) REFERENCES `book` (`book_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `customer` (
  `customer_id` int NOT NULL AUTO_INCREMENT,
  `active_flag` tinyint(1) DEFAULT '1',
  `contact_no` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`customer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `cart` (
  `cart_id` int NOT NULL AUTO_INCREMENT,
  `active_flag` tinyint(1) DEFAULT '1',
  `customer_id` int DEFAULT NULL,
  PRIMARY KEY (`cart_id`),
  KEY `FKdebwvad6pp1ekiqy5jtixqbaj` (`customer_id`),
  CONSTRAINT `FKdebwvad6pp1ekiqy5jtixqbaj` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `cart_items` (
  `id` int NOT NULL AUTO_INCREMENT,
  `quantity` int DEFAULT NULL,
  `book_book_id` int DEFAULT NULL,
  `cart_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKbse7548m104ur6863mfjrawiy` (`book_book_id`),
  KEY `FK99e0am9jpriwxcm6is7xfedy3` (`cart_id`),
  CONSTRAINT `FK99e0am9jpriwxcm6is7xfedy3` FOREIGN KEY (`cart_id`) REFERENCES `cart` (`cart_id`),
  CONSTRAINT `FKbse7548m104ur6863mfjrawiy` FOREIGN KEY (`book_book_id`) REFERENCES `book` (`book_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



CREATE TABLE `customer_address` (
  `id` int NOT NULL AUTO_INCREMENT,
  `city` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `locality` varchar(255) DEFAULT NULL,
  `pincode` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `customer_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKr9ofa0ydsgbaqmt9leb3v5eii` (`customer_id`),
  CONSTRAINT `FKr9ofa0ydsgbaqmt9leb3v5eii` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `orders` (
  `order_id` int NOT NULL AUTO_INCREMENT,
  `active_flag` tinyint(1) DEFAULT '1',
  `order_date` date DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `total_amt` double DEFAULT NULL,
  `customer_id` int DEFAULT NULL,
  PRIMARY KEY (`order_id`),
  KEY `FK624gtjin3po807j3vix093tlf` (`customer_id`),
  CONSTRAINT `FK624gtjin3po807j3vix093tlf` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `order_items` (
  `id` int NOT NULL AUTO_INCREMENT,
  `quantity` int DEFAULT NULL,
  `book_id` int DEFAULT NULL,
  `order_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKqscqcme08spiyt2guyqdj72eh` (`book_id`),
  KEY `FKbioxgbv59vetrxe0ejfubep1w` (`order_id`),
  CONSTRAINT `FKbioxgbv59vetrxe0ejfubep1w` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`),
  CONSTRAINT `FKqscqcme08spiyt2guyqdj72eh` FOREIGN KEY (`book_id`) REFERENCES `book` (`book_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `shipping_address` (
  `shipping_id` int NOT NULL AUTO_INCREMENT,
  `city` varchar(255) DEFAULT NULL,
  `locality` varchar(255) DEFAULT NULL,
  `pincode` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `order_id` int DEFAULT NULL,
  PRIMARY KEY (`shipping_id`),
  KEY `FKl88fq4d2ypn9qvg8x90uimnca` (`order_id`),
  CONSTRAINT `FKl88fq4d2ypn9qvg8x90uimnca` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

