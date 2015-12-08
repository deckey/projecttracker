-- phpMyAdmin SQL Dump
-- version 4.4.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Dec 09, 2015 at 12:22 AM
-- Server version: 5.6.26
-- PHP Version: 5.5.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `projecttracker.db`
--

-- --------------------------------------------------------

--
-- Table structure for table `project_member`
--

CREATE TABLE IF NOT EXISTS `project_member` (
  `memberId` int(11) NOT NULL,
  `projectId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `project_member`
--

INSERT INTO `project_member` (`memberId`, `projectId`) VALUES
(7, 22),
(1, 23),
(7, 24),
(1, 27);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_member`
--

CREATE TABLE IF NOT EXISTS `tbl_member` (
  `memberId` int(11) NOT NULL,
  `memberName` varchar(255) DEFAULT NULL,
  `memberPassword` varchar(255) DEFAULT NULL,
  `memberRole` varchar(255) DEFAULT NULL,
  `memberSpecialty` varchar(255) DEFAULT NULL,
  `memberStatus` varchar(255) DEFAULT NULL,
  `memberTotalHours` double NOT NULL,
  `memberUsername` varchar(255) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_member`
--

INSERT INTO `tbl_member` (`memberId`, `memberName`, `memberPassword`, `memberRole`, `memberSpecialty`, `memberStatus`, `memberTotalHours`, `memberUsername`) VALUES
(1, 'Administrator', 'admin', 'Administrator', 'Supervisor', NULL, 0, 'admin'),
(7, 'TestMember3', NULL, NULL, NULL, NULL, 0, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_project`
--

CREATE TABLE IF NOT EXISTS `tbl_project` (
  `projectId` int(11) NOT NULL,
  `projectCategory` varchar(255) DEFAULT NULL,
  `projectClient` varchar(255) DEFAULT NULL,
  `projectCreationDate` datetime DEFAULT NULL,
  `projectDescription` varchar(255) DEFAULT NULL,
  `projectDue` datetime DEFAULT NULL,
  `projectEnd` date DEFAULT NULL,
  `projectStart` datetime DEFAULT NULL,
  `projectStatus` varchar(255) DEFAULT NULL,
  `projectTime` double DEFAULT NULL,
  `projectTitle` varchar(255) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_project`
--

INSERT INTO `tbl_project` (`projectId`, `projectCategory`, `projectClient`, `projectCreationDate`, `projectDescription`, `projectDue`, `projectEnd`, `projectStart`, `projectStatus`, `projectTime`, `projectTitle`) VALUES
(22, 'Other', 'Company 7', '2015-12-08 12:17:35', 'Some description', '2015-12-17 00:00:00', NULL, '2015-12-02 00:00:00', 'Active', 0, 'Project 1'),
(23, 'Other', 'Company 5', '2015-12-08 12:28:57', 'Project 4 description ', '2015-12-17 00:00:00', NULL, '2015-12-05 00:00:00', 'Archived', 0, 'Project 4'),
(24, 'Other', NULL, '2015-12-08 12:59:27', 'dawdawdawd', '2015-12-16 00:00:00', NULL, '2015-12-02 00:00:00', 'Active', 0, 'test project'),
(25, 'Other', 'dawdaw', '2015-12-03 00:00:00', 'dawdawd', '2015-12-25 00:00:00', NULL, '2015-12-18 00:00:00', 'Active', 0, 'dawdawd awdawd a'),
(26, 'Game_Modeling', 'Posh & Media', '2015-12-08 13:26:11', 'tetetet', '2015-12-30 00:00:00', NULL, '2015-12-02 00:00:00', 'Completed', 0, 'Pestan animacija 325'),
(27, 'Game_Modeling', 'Some', '2015-12-09 00:11:39', 'No description provided ...', '2015-12-29 00:00:00', NULL, '2015-12-08 00:00:00', 'Active', 0, 'Working assignments');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `project_member`
--
ALTER TABLE `project_member`
  ADD PRIMARY KEY (`memberId`,`projectId`),
  ADD KEY `FK_projectID` (`projectId`);

--
-- Indexes for table `tbl_member`
--
ALTER TABLE `tbl_member`
  ADD PRIMARY KEY (`memberId`);

--
-- Indexes for table `tbl_project`
--
ALTER TABLE `tbl_project`
  ADD PRIMARY KEY (`projectId`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tbl_member`
--
ALTER TABLE `tbl_member`
  MODIFY `memberId` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT for table `tbl_project`
--
ALTER TABLE `tbl_project`
  MODIFY `projectId` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=28;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `project_member`
--
ALTER TABLE `project_member`
  ADD CONSTRAINT `FK_memberID` FOREIGN KEY (`memberId`) REFERENCES `tbl_member` (`memberId`),
  ADD CONSTRAINT `FK_projectID` FOREIGN KEY (`projectId`) REFERENCES `tbl_project` (`projectId`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
