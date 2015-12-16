-- phpMyAdmin SQL Dump
-- version 4.4.14
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Dec 16, 2015 at 01:32 AM
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
(58, 1),
(48, 2),
(58, 2),
(58, 3),
(59, 3),
(54, 4),
(58, 4),
(59, 4),
(54, 5),
(58, 5),
(60, 5);

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
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_activity`
--

INSERT INTO `tbl_activity` (`activityId`, `activityMemberId`, `activityAction`, `activityDate`, `activityOutput`) VALUES
(1, 54, 'created project Project A', '2015-12-15', 'Administrator created project Project A  on Dec 15, 2015 at 12:05'),
(2, 54, 'created project Project B', '2015-12-15', 'Administrator created project Project B  on Dec 15, 2015 at 12:05'),
(3, 54, ' created new member  Member 1', '2015-12-15', 'Administrator  created new member  Member 1  on Dec 15, 2015 at 12:07'),
(4, 54, 'created project Project C', '2015-12-15', 'Administrator created project Project C  on Dec 15, 2015 at 12:13'),
(5, 54, 'updated Project C project ', '2015-12-15', 'Administrator updated Project C project   on Dec 15, 2015 at 12:13'),
(6, 54, 'logged 5.0 hours of Rigging on Project C', '2015-12-15', 'Administrator logged 5.0 hours of Rigging on Project C  on Dec 15, 2015 at 12:13'),
(7, 54, 'updated Project C project ', '2015-12-15', 'Administrator updated Project C project   on Dec 15, 2015 at 12:13'),
(8, 54, ' created new member  Member 2', '2015-12-15', 'Administrator  created new member  Member 2  on Dec 15, 2015 at 12:14'),
(9, 54, 'created project Project D', '2015-12-15', 'Administrator created project Project D  on Dec 15, 2015 at 12:14'),
(10, 54, 'updated Project D project ', '2015-12-15', 'Administrator updated Project D project   on Dec 15, 2015 at 12:14'),
(11, 54, 'logged 5.0 hours of Modeling on Project D', '2015-12-15', 'Administrator logged 5.0 hours of Modeling on Project D  on Dec 15, 2015 at 12:14'),
(12, 54, 'logged 6.0 hours of Rendering on Project D', '2015-12-15', 'Administrator logged 6.0 hours of Rendering on Project D  on Dec 15, 2015 at 12:15'),
(13, 54, 'created project Project E', '2015-12-15', 'Administrator created project Project E  on Dec 15, 2015 at 12:17'),
(14, 54, 'logged 5.0 hours of Modeling on Project E', '2015-12-15', 'Administrator logged 5.0 hours of Modeling on Project E  on Dec 15, 2015 at 12:17'),
(15, 54, 'logged 3.0 hours of Rigging on Project E', '2015-12-15', 'Administrator logged 3.0 hours of Rigging on Project E  on Dec 15, 2015 at 12:17'),
(16, 54, 'logged 5.0 hours of Concept on Project A', '2015-12-15', 'Administrator logged 5.0 hours of Concept on Project A  on Dec 15, 2015 at 02:19'),
(17, 54, 'logged 5.0 hours of Concept on Project D', '2015-12-15', 'Administrator logged 5.0 hours of Concept on Project D  on Dec 15, 2015 at 02:25'),
(18, 54, 'updated Project D project ', '2015-12-15', 'Administrator updated Project D project   on Dec 15, 2015 at 02:25'),
(19, 54, 'updated Project D project ', '2015-12-15', 'Administrator updated Project D project   on Dec 15, 2015 at 02:25'),
(20, 54, 'logged 5.0 hours of Rendering on Project D', '2015-12-15', 'Administrator logged 5.0 hours of Rendering on Project D  on Dec 15, 2015 at 02:25'),
(21, 54, 'logged 0.0 hours of Illustration on Project E', '2015-12-15', 'Administrator logged 0.0 hours of Illustration on Project E  on Dec 15, 2015 at 02:27'),
(22, 54, 'updated Project A project ', '2015-12-15', 'Administrator updated Project A project   on Dec 15, 2015 at 02:27'),
(23, 54, 'logged 0.0 hours of Storyboard on Project A', '2015-12-15', 'Administrator logged 0.0 hours of Storyboard on Project A  on Dec 15, 2015 at 02:28'),
(24, 54, 'updated Project E project ', '2015-12-15', 'Administrator updated Project E project   on Dec 15, 2015 at 03:34'),
(25, 54, 'updated Project E project ', '2015-12-15', 'Administrator updated Project E project   on Dec 15, 2015 at 03:34'),
(26, 54, 'updated Project E project ', '2015-12-15', 'Administrator updated Project E project   on Dec 15, 2015 at 03:34'),
(27, 54, 'updated Project D project ', '2015-12-15', 'Administrator updated Project D project   on Dec 15, 2015 at 03:46'),
(28, 54, 'updated Project E project ', '2015-12-16', 'Administrator updated Project E project   on Dec 16, 2015 at 12:23'),
(29, 54, 'updated Project E project ', '2015-12-16', 'Administrator updated Project E project   on Dec 16, 2015 at 12:23'),
(30, 54, 'updated Project E project ', '2015-12-16', 'Administrator updated Project E project   on Dec 16, 2015 at 12:28'),
(31, 54, 'updated Project B project ', '2015-12-16', 'Administrator updated Project B project   on Dec 16, 2015 at 01:11');

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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_log`
--

INSERT INTO `tbl_log` (`logId`, `logMemberId`, `logProjectId`, `logMemberName`, `logComment`, `logAdded`, `logTime`, `logWork`) VALUES
(1, 54, 3, 'Administrator', 'Far far away, behind the word mountains, far from the countries Vokalia and Cons', '2015-12-15 00:00:00', 5, 'Rigging'),
(2, 54, 4, 'Administrator', 'Far far away, behind the word mountains, far from the countries Vokalia and Cons', '2015-12-15 00:00:00', 5, 'Modeling'),
(3, 54, 4, 'Administrator', 'Far far away, behind the word mountains, far from the countries Vokalia and Cons', '2015-12-15 00:00:00', 6, 'Rendering'),
(4, 54, 5, 'Administrator', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula.', '2015-12-15 00:00:00', 5, 'Modeling'),
(5, 54, 5, 'Administrator', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula.', '2015-12-15 00:00:00', 3, 'Rigging');

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
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_member`
--

INSERT INTO `tbl_member` (`memberId`, `memberName`, `memberUsername`, `memberPassword`, `memberRole`, `memberSpecialty`, `memberStatus`, `memberTotalHours`) VALUES
(48, 'Team member', 'team', 'test', 'Member', 'Modeling', 'Inactive', 0),
(54, 'Administrator', 'admin', 'admin', 'Administrator', 'Supervision', 'Active', 24),
(58, 'Supervisor', 'sup', 'sup', 'Supervisor', 'Rigging', 'Active', 0),
(59, 'Member 1', 'mem1', 'mem1', 'Member', 'Lighting', 'Active', 0),
(60, 'Member 2', 'mem2', 'mem2', 'Member', 'Rigging', 'Active', 0);

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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_project`
--

INSERT INTO `tbl_project` (`projectId`, `projectTitle`, `projectCategory`, `projectClient`, `projectCreationDate`, `projectDescription`, `projectDue`, `projectEnd`, `projectStart`, `projectStatus`, `projectTime`) VALUES
(1, 'Project A', 'Motion_Graphics', 'Company Z', '2015-12-15 12:05:19', 'No description provided ...', '2015-12-23 00:00:00', NULL, '2015-12-15 00:00:00', 'Active', 0),
(2, 'Project B', 'Animation', 'Company Y', '2015-12-15 12:05:42', 'No description provided ...', '2015-12-18 00:00:00', NULL, '2015-12-02 00:00:00', 'Active', 0),
(3, 'Project C', 'Motion_Graphics', 'Company X', '2015-12-15 12:13:19', 'No description provided ...', '2015-12-17 00:00:00', NULL, '2015-12-01 00:00:00', 'Active', 5),
(4, 'Project D', 'TV_Commercial', 'Company W', '2015-12-15 12:14:47', 'No description provided ...', '2015-12-29 00:00:00', NULL, '2015-12-10 00:00:00', 'Active', 11),
(5, 'Project E', 'Game_Modeling', 'Company V', '2015-12-15 12:17:18', 'No description provided ...', '2015-12-09 00:00:00', NULL, '2015-12-03 00:00:00', 'Inactive', 8);

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
  MODIFY `activityId` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=32;
--
-- AUTO_INCREMENT for table `tbl_log`
--
ALTER TABLE `tbl_log`
  MODIFY `logId` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT for table `tbl_member`
--
ALTER TABLE `tbl_member`
  MODIFY `memberId` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=61;
--
-- AUTO_INCREMENT for table `tbl_project`
--
ALTER TABLE `tbl_project`
  MODIFY `projectId` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=6;
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
