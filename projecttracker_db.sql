-- phpMyAdmin SQL Dump
-- version 4.4.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Dec 15, 2015 at 08:59 AM
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

-- --------------------------------------------------------

--
-- Table structure for table `tbl_activity`
--

CREATE TABLE IF NOT EXISTS `tbl_activity` (
  `activityId` int(11) NOT NULL,
  `activityMemberId` int(11) NOT NULL,
  `activityAction` varchar(128) NOT NULL,
  `activityDate` date NOT NULL,
  `activityOutput` text NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_log`
--

CREATE TABLE IF NOT EXISTS `tbl_log` (
  `logId` int(11) NOT NULL,
  `logMemberId` int(11) NOT NULL,
  `logProjectId` int(11) NOT NULL,
  `logMemberName` varchar(32) NOT NULL,
  `logComment` text NOT NULL,
  `logAdded` datetime NOT NULL,
  `logTime` float NOT NULL,
  `logWork` varchar(32) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=91 DEFAULT CHARSET=latin1;

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
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_member`
--

INSERT INTO `tbl_member` (`memberId`, `memberName`, `memberUsername`, `memberPassword`, `memberRole`, `memberSpecialty`, `memberStatus`, `memberTotalHours`) VALUES
(48, 'Team member', 'team', 'test', 'Member', 'Modeling', 'Active', 0),
(54, 'Administrator', 'admin', 'admin', 'Administrator', 'Supervision', 'Active', 0),
(58, 'Supervisor', 'sup', 'sup', 'Supervisor', 'Rigging', 'Active', 0);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_project`
--

CREATE TABLE IF NOT EXISTS `tbl_project` (
  `projectId` int(11) NOT NULL,
  `projectTitle` varchar(255) DEFAULT NULL,
  `projectCategory` varchar(255) DEFAULT NULL,
  `projectClient` varchar(255) DEFAULT NULL,
  `projectCreationDate` datetime DEFAULT NULL,
  `projectDescription` varchar(255) DEFAULT NULL,
  `projectDue` datetime DEFAULT NULL,
  `projectEnd` date DEFAULT NULL,
  `projectStart` datetime DEFAULT NULL,
  `projectStatus` varchar(255) DEFAULT NULL,
  `projectTime` double DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=78 DEFAULT CHARSET=latin1;

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
-- Indexes for table `tbl_activity`
--
ALTER TABLE `tbl_activity`
  ADD PRIMARY KEY (`activityId`),
  ADD UNIQUE KEY `activityId` (`activityId`);

--
-- Indexes for table `tbl_log`
--
ALTER TABLE `tbl_log`
  ADD PRIMARY KEY (`logId`),
  ADD UNIQUE KEY `logId` (`logId`);

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
-- AUTO_INCREMENT for table `tbl_activity`
--
ALTER TABLE `tbl_activity`
  MODIFY `activityId` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=14;
--
-- AUTO_INCREMENT for table `tbl_log`
--
ALTER TABLE `tbl_log`
  MODIFY `logId` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=91;
--
-- AUTO_INCREMENT for table `tbl_member`
--
ALTER TABLE `tbl_member`
  MODIFY `memberId` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=64;
--
-- AUTO_INCREMENT for table `tbl_project`
--
ALTER TABLE `tbl_project`
  MODIFY `projectId` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=78;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `project_member`
--
ALTER TABLE `project_member`
  ADD CONSTRAINT `FK_memberID` FOREIGN KEY (`memberId`) REFERENCES `tbl_member` (`memberId`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_projectID` FOREIGN KEY (`projectId`) REFERENCES `tbl_project` (`projectId`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
