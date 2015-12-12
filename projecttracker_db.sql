-- phpMyAdmin SQL Dump
-- version 4.4.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Dec 10, 2015 at 08:25 PM
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
(32, 31),
(34, 31),
(34, 32),
(35, 32),
(36, 32),
(37, 32),
(33, 33),
(34, 33),
(35, 33),
(32, 34),
(33, 34),
(34, 34),
(34, 37),
(35, 37);

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
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_member`
--

INSERT INTO `tbl_member` (`memberId`, `memberName`, `memberUsername`, `memberPassword`, `memberRole`, `memberSpecialty`, `memberStatus`, `memberTotalHours`) VALUES
(32, 'Administrator', 'admin', 'admin', 'Administrator', 'Supervisor', 'Active', 32),
(33, 'Team member', 'team', 'team', 'Member', 'Modeling', 'Active', 28),
(34, 'Supervisor', 'sup', 'sup', 'Supervisor', 'Supervisor', 'Active', 0),
(35, 'Petar Peric', 'petar', 'petar', 'Member', 'Lighting', 'Active', 0),
(36, 'Mika Mikic', 'mika', 'mika', 'Member', 'VFX', 'Active', 13),
(37, 'Zika Zikic', 'zika', 'zika', 'Member', 'Lighting', 'Active', 0);

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
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_project`
--

INSERT INTO `tbl_project` (`projectId`, `projectCategory`, `projectClient`, `projectCreationDate`, `projectDescription`, `projectDue`, `projectEnd`, `projectStart`, `projectStatus`, `projectTime`, `projectTitle`) VALUES
(31, 'Other', 'Company A', '2015-12-10 14:21:04', 'No description provided ...', '2015-12-31 00:00:00', NULL, '2015-12-03 00:00:00', 'Active', 0, 'Project 3546'),
(32, 'Other', 'Company B', '2015-12-10 14:23:53', 'No description provided ...', '2015-12-31 00:00:00', NULL, '2015-07-01 00:00:00', 'Active', 0, 'Project 55'),
(33, 'Animation', 'Company C', '2015-12-10 14:26:57', 'No description provided ...', '2015-12-22 00:00:00', NULL, '2015-10-28 00:00:00', 'Active', 0, 'Project 3'),
(34, 'Other', 'Company Dd', '2015-12-10 14:27:51', 'No description provided ...', '2015-12-31 00:00:00', NULL, '2015-12-16 00:00:00', 'Active', 0, 'Project 55'),
(36, 'Other', 'Company X', '2015-12-10 15:25:29', 'No description provided ...', '2015-12-23 00:00:00', NULL, '2015-12-08 00:00:00', 'Active', 0, 'Project 4'),
(37, 'Other', 'Company X', '2015-12-10 15:25:53', 'No description provided ...', '2015-12-29 00:00:00', NULL, '2015-12-01 00:00:00', 'Active', 0, 'Project 41');

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
  MODIFY `memberId` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=38;
--
-- AUTO_INCREMENT for table `tbl_project`
--
ALTER TABLE `tbl_project`
  MODIFY `projectId` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=38;
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
