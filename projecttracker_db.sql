-- phpMyAdmin SQL Dump
-- version 4.3.11
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Dec 14, 2015 at 12:34 PM
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
(44, 51),
(44, 52),
(46, 52),
(44, 53),
(46, 53),
(44, 54),
(46, 54),
(47, 54);

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
) ENGINE=InnoDB AUTO_INCREMENT=81 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_log`
--

INSERT INTO `tbl_log` (`logId`, `logMemberId`, `logProjectId`, `logComment`, `logAdded`, `logTime`, `logWork`, `logMemberName`) VALUES
(67, 44, 52, ' wr g3345 y45h 46j yj y5m ', '2015-12-13 00:00:00', 7, 'Rigging', 'Administrator'),
(68, 44, 51, 'q fq fwg 3g 45hy46567', '2015-12-13 00:00:00', 9, 'Compositing', 'Administrator'),
(71, 44, 53, 'wrq qew w t w wewww', '2015-12-13 00:00:00', 12, 'Rigging', 'Administrator'),
(72, 44, 53, 'w egrg esrh 4rtj tdyj ftykk tyuky7 k67', '2015-12-13 00:00:00', 35, 'Rigging', 'Administrator'),
(74, 47, 54, 'fsdf  sd sdgweg ewg w', '2015-12-13 00:00:00', 5, 'Animation', 'Supervisor'),
(80, 44, 54, 'dfh xf g xfj fj f', '2015-12-13 00:00:00', 7, 'Storyboard', 'Administrator');

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
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_member`
--

INSERT INTO `tbl_member` (`memberId`, `memberName`, `memberUsername`, `memberPassword`, `memberRole`, `memberSpecialty`, `memberStatus`, `memberTotalHours`) VALUES
(44, 'Administrator', 'admin', 'admin', 'Administrator', 'Supervision', 'Active', 70),
(46, 'test', 'test', 'test', 'Supervisor', 'Animation', 'Active', 0),
(47, 'Supervisor', 'sup', 'sup', 'Supervisor', 'Animation', 'Active', 5),
(48, 'Team member', 'team', 'team', 'Member', 'Modeling', 'Active', 0);

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
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_project`
--

INSERT INTO `tbl_project` (`projectId`, `projectTitle`, `projectCategory`, `projectClient`, `projectCreationDate`, `projectDescription`, `projectDue`, `projectEnd`, `projectStart`, `projectStatus`, `projectTime`) VALUES
(51, 'Project 3', 'Other', 'Company Z', '2015-12-13 18:07:21', 'No description provided ...', '2015-12-16 00:00:00', NULL, '2015-12-02 00:00:00', 'Active', 9),
(52, 'Project 1', 'Other', 'Company A', '2015-12-13 18:08:10', 'No description provided ...', '2015-12-23 00:00:00', NULL, '2015-12-01 00:00:00', 'Active', 7),
(53, 'Project 4', 'Game_Modeling', 'Company Y', '2015-12-13 20:47:07', 'No description provided ...', '2015-12-18 00:00:00', NULL, '2015-12-03 00:00:00', 'Active', 47),
(54, 'Project 2', 'Motion_Graphics', 'Company B', '2015-12-13 20:59:19', 'No description provided ...', '2015-12-17 00:00:00', NULL, '2015-12-02 00:00:00', 'Active', 12);

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
  MODIFY `logId` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=81;
--
-- AUTO_INCREMENT for table `tbl_member`
--
ALTER TABLE `tbl_member`
  MODIFY `memberId` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=49;
--
-- AUTO_INCREMENT for table `tbl_project`
--
ALTER TABLE `tbl_project`
  MODIFY `projectId` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=55;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `project_member`
--
ALTER TABLE `project_member`
ADD CONSTRAINT `FK_memberID` FOREIGN KEY (`memberId`) REFERENCES `tbl_member` (`memberId`),
ADD CONSTRAINT `FK_projectID` FOREIGN KEY (`projectId`) REFERENCES `tbl_project` (`projectId`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
