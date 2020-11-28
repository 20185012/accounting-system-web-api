-- phpMyAdmin SQL Dump
-- version 5.0.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 26, 2020 at 12:08 AM
-- Server version: 10.4.14-MariaDB
-- PHP Version: 7.2.34

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `accountingsystem`
--

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `categoryID` int(11) NOT NULL,
  `categoryName` varchar(255) DEFAULT NULL,
  `overallFinances` float NOT NULL,
  `parentCategory_categoryID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`categoryID`, `categoryName`, `overallFinances`, `parentCategory_categoryID`) VALUES
(9, 'root', 0, NULL),
(10, 'asdasd', 0, 9),
(11, 'asdsd', 0, 9),
(12, 'sdsd', 0, 11),
(24, 'Pavad', 0, 9);

-- --------------------------------------------------------

--
-- Table structure for table `payment`
--

CREATE TABLE `payment` (
  `paymentId` int(11) NOT NULL,
  `paymentDate` date DEFAULT NULL,
  `paymentSum` float NOT NULL,
  `category_categoryID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `payment`
--

INSERT INTO `payment` (`paymentId`, `paymentDate`, `paymentSum`, `category_categoryID`) VALUES
(6, '2020-10-25', 7988, 10),
(7, '2020-11-25', 798798, 9),
(8, NULL, 0, 10),
(9, NULL, 0, 10);

-- --------------------------------------------------------

--
-- Table structure for table `receivable`
--

CREATE TABLE `receivable` (
  `receivableId` int(11) NOT NULL,
  `receivableDate` date DEFAULT NULL,
  `receivableSum` float NOT NULL,
  `category_categoryID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `receivable`
--

INSERT INTO `receivable` (`receivableId`, `receivableDate`, `receivableSum`, `category_categoryID`) VALUES
(8, '2020-12-31', 78954.5, 9),
(9, '2020-10-10', 46546, 9),
(10, '2020-07-07', 465, 10),
(11, '2020-07-07', 465, 10);

-- --------------------------------------------------------

--
-- Table structure for table `systemroot`
--

CREATE TABLE `systemroot` (
  `systemId` int(11) NOT NULL,
  `companyName` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `systemroot_category`
--

CREATE TABLE `systemroot_category` (
  `SystemRoot_systemId` int(11) NOT NULL,
  `rootCategories_categoryID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `systemroot_user`
--

CREATE TABLE `systemroot_user` (
  `SystemRoot_systemId` int(11) NOT NULL,
  `users_userID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `userID` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `companyCode` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `userType` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`userID`, `address`, `companyCode`, `email`, `name`, `password`, `phone`, `surname`, `userType`, `username`) VALUES
(1, NULL, NULL, '', 'sim', 'admin', '', 'baz', 'Individual', 'admin'),
(2, NULL, NULL, '', 'simasNaujas', 'adminNaujas', '', 'bazNaujas', 'Individual', 'adminNaujas'),
(5, 'Siaure', '456489498', '', 'tom', 'tom', '', NULL, 'Legal', 'tom'),
(6, NULL, NULL, 'dienaaaa', 'laba', 'laba', '88465456', 'diena', 'Individual', 'laba');

-- --------------------------------------------------------

--
-- Table structure for table `user_category`
--

CREATE TABLE `user_category` (
  `responsibleUsers_userID` int(11) NOT NULL,
  `categoriesResponsible_categoryID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user_category`
--

INSERT INTO `user_category` (`responsibleUsers_userID`, `categoriesResponsible_categoryID`) VALUES
(1, 24),
(1, 24),
(1, 10);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`categoryID`),
  ADD KEY `FKb8w16aec4nlnb80vmbhc5fnqm` (`parentCategory_categoryID`);

--
-- Indexes for table `payment`
--
ALTER TABLE `payment`
  ADD PRIMARY KEY (`paymentId`),
  ADD KEY `FKmh4dx3sdhjeho38sep0mreic` (`category_categoryID`);

--
-- Indexes for table `receivable`
--
ALTER TABLE `receivable`
  ADD PRIMARY KEY (`receivableId`),
  ADD KEY `FKg1hg9nhg7qs50i7llb7lm7pb7` (`category_categoryID`);

--
-- Indexes for table `systemroot`
--
ALTER TABLE `systemroot`
  ADD PRIMARY KEY (`systemId`);

--
-- Indexes for table `systemroot_category`
--
ALTER TABLE `systemroot_category`
  ADD UNIQUE KEY `UK_qu902src7y1buf52j3bs898xt` (`rootCategories_categoryID`),
  ADD KEY `FKgub14r8tcml6jv4u5j962rypv` (`SystemRoot_systemId`);

--
-- Indexes for table `systemroot_user`
--
ALTER TABLE `systemroot_user`
  ADD UNIQUE KEY `UK_h6cvfx3xk3v07cwabrjds4ujf` (`users_userID`),
  ADD KEY `FK5v7i1amfd5wswx3kiomw3t46k` (`SystemRoot_systemId`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`userID`);

--
-- Indexes for table `user_category`
--
ALTER TABLE `user_category`
  ADD KEY `FKohp46m9vxa0dpxsv2r7x0e37o` (`categoriesResponsible_categoryID`),
  ADD KEY `FK7y3lekwaukgc4p5lcdrvocu3g` (`responsibleUsers_userID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `category`
--
ALTER TABLE `category`
  MODIFY `categoryID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT for table `payment`
--
ALTER TABLE `payment`
  MODIFY `paymentId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `receivable`
--
ALTER TABLE `receivable`
  MODIFY `receivableId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `systemroot`
--
ALTER TABLE `systemroot`
  MODIFY `systemId` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `userID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `category`
--
ALTER TABLE `category`
  ADD CONSTRAINT `FKb8w16aec4nlnb80vmbhc5fnqm` FOREIGN KEY (`parentCategory_categoryID`) REFERENCES `category` (`categoryID`);

--
-- Constraints for table `payment`
--
ALTER TABLE `payment`
  ADD CONSTRAINT `FKmh4dx3sdhjeho38sep0mreic` FOREIGN KEY (`category_categoryID`) REFERENCES `category` (`categoryID`);

--
-- Constraints for table `receivable`
--
ALTER TABLE `receivable`
  ADD CONSTRAINT `FKg1hg9nhg7qs50i7llb7lm7pb7` FOREIGN KEY (`category_categoryID`) REFERENCES `category` (`categoryID`);

--
-- Constraints for table `systemroot_category`
--
ALTER TABLE `systemroot_category`
  ADD CONSTRAINT `FKgub14r8tcml6jv4u5j962rypv` FOREIGN KEY (`SystemRoot_systemId`) REFERENCES `systemroot` (`systemId`),
  ADD CONSTRAINT `FKhp7lqyndu9u48qq7n7lg6x8mb` FOREIGN KEY (`rootCategories_categoryID`) REFERENCES `category` (`categoryID`);

--
-- Constraints for table `systemroot_user`
--
ALTER TABLE `systemroot_user`
  ADD CONSTRAINT `FK5v7i1amfd5wswx3kiomw3t46k` FOREIGN KEY (`SystemRoot_systemId`) REFERENCES `systemroot` (`systemId`),
  ADD CONSTRAINT `FKd0tnab7youpfegk41ipmc5nof` FOREIGN KEY (`users_userID`) REFERENCES `user` (`userID`);

--
-- Constraints for table `user_category`
--
ALTER TABLE `user_category`
  ADD CONSTRAINT `FK7y3lekwaukgc4p5lcdrvocu3g` FOREIGN KEY (`responsibleUsers_userID`) REFERENCES `user` (`userID`),
  ADD CONSTRAINT `FKohp46m9vxa0dpxsv2r7x0e37o` FOREIGN KEY (`categoriesResponsible_categoryID`) REFERENCES `category` (`categoryID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
