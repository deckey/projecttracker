-- phpMyAdmin SQL Dump
-- version 4.3.11
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Dec 12, 2015 at 02:15 AM
-- Server version: 5.6.24
-- PHP Version: 5.5.24

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

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
(32, 31),
(34, 31),
(36, 31),
(37, 31),
(38, 31),
(32, 32),
(34, 32),
(36, 32),
(37, 32),
(39, 32),
(32, 33),
(33, 33),
(34, 33),
(35, 33),
(33, 34),
(34, 34),
(32, 36),
(34, 36),
(35, 36),
(32, 37),
(34, 37),
(35, 37),
(36, 37),
(38, 37),
(32, 38),
(34, 38),
(35, 38),
(36, 38);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_log`
--

CREATE TABLE IF NOT EXISTS `tbl_log` (
  `logId` int(11) NOT NULL,
  `logMemberId` int(11) NOT NULL,
  `logProjectId` int(11) NOT NULL,
  `logComment` text NOT NULL,
  `logAdded` datetime NOT NULL,
  `logTime` float NOT NULL,
  `logWork` varchar(32) NOT NULL,
  `logMemberName` varchar(32) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_member`
--

CREATE TABLE IF NOT EXISTS `tbl_member` (
  `memberId` int(11) NOT NULL,
  `memberName` varchar(255) DEFAULT NULL,
  `memberUsername` varchar(255) DEFAULT NULL,
  `memberPassword` varchar(255) DEFAULT NULL,
  `memberRole` varchar(255) DEFAULT NULL,
  `memberSpecialty` varchar(255) DEFAULT NULL,
  `memberStatus` varchar(255) DEFAULT NULL,
  `memberTotalHours` double NOT NULL DEFAULT '0'
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_member`
--

INSERT INTO `tbl_member` (`memberId`, `memberName`, `memberUsername`, `memberPassword`, `memberRole`, `memberSpecialty`, `memberStatus`, `memberTotalHours`) VALUES
(32, 'Administrator', 'admin', 'admin', 'Administrator', 'Supervision', 'Active', 32),
(33, 'Team member', 'team', 'team', 'Member', 'Modeling', 'Active', 28),
(34, 'Supervisor', 'sup', 'sup', 'Supervisor', 'Supervision', 'Active', 0),
(35, 'Petar Peric', 'petar', 'petar', 'Member', 'Lighting', 'Active', 0),
(36, 'Mika Mikic', 'mika', 'mika', 'Member', 'VFX', 'Active', 13),
(37, 'Zika Zikic', 'zika', 'zika', 'Member', 'Lighting', 'Active', 0),
(38, 'Nenad Sovic', 'nesa', 'nesa', 'Administrator', 'Animation', 'Active', 0),
(39, 'Sasa', 'sasa', 'sasa', 'Administrator', 'Rigging', 'Active', 0),
(40, 'dragan', 'dragan', 'dragan', 'Supervisor', 'Lighting', 'Active', 0);

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
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_project`
--

INSERT INTO `tbl_project` (`projectId`, `projectCategory`, `projectClient`, `projectCreationDate`, `projectDescription`, `projectDue`, `projectEnd`, `projectStart`, `projectStatus`, `projectTime`, `projectTitle`) VALUES
(31, 'Other', 'Company A', '2015-12-10 14:21:04', 'No description provided ...', '2015-12-31 00:00:00', NULL, '2015-12-03 00:00:00', 'Active', 43, 'Project 3546'),
(32, 'Other', 'Company B', '2015-12-10 14:23:53', 'No description provided ...', '2015-12-31 00:00:00', NULL, '2015-07-01 00:00:00', 'Active', 0, 'Project 55'),
(33, 'Animation', 'Company C', '2015-12-10 14:26:57', 'No description provided ...', '2015-12-22 00:00:00', NULL, '2015-10-01 00:00:00', 'Active', 123, 'Project 3'),
(34, 'Other', 'Company AB', '2015-12-10 14:27:51', 'No description provided ...', '2015-12-31 00:00:00', NULL, '2015-12-16 00:00:00', 'Active', 0, 'New project 1'),
(36, 'Other', 'Company X', '2015-12-10 15:25:29', 'No description provided ...', '2015-12-23 00:00:00', NULL, '2015-12-08 00:00:00', 'Active', 5, 'Project 4'),
(37, 'Motion_Graphics', 'Company X', '2015-12-10 15:25:53', 'No description provided ...', '2015-12-29 00:00:00', NULL, '2015-12-24 00:00:00', 'Inactive', 9, 'Project 41'),
(38, 'Game_Modeling', 'company', '2015-12-11 10:25:03', 'No description provided ...', '2015-12-31 00:00:00', NULL, '2015-12-24 00:00:00', 'Active', 0, 'Novi');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `project_member`
--
ALTER TABLE `project_member`
  ADD PRIMARY KEY (`memberId`,`projectId`), ADD KEY `FK_projectID` (`projectId`);

--
-- Indexes for table `tbl_log`
--
ALTER TABLE `tbl_log`
  ADD PRIMARY KEY (`logId`), ADD UNIQUE KEY `logId` (`logId`);

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
-- AUTO_INCREMENT for table `tbl_log`
--
ALTER TABLE `tbl_log`
  MODIFY `logId` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=44;
--
-- AUTO_INCREMENT for table `tbl_member`
--
ALTER TABLE `tbl_member`
  MODIFY `memberId` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=41;
--
-- AUTO_INCREMENT for table `tbl_project`
--
ALTER TABLE `tbl_project`
  MODIFY `projectId` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=39;
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
