-- phpMyAdmin SQL Dump
-- version 4.4.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Dec 03, 2015 at 11:19 PM
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
-- Table structure for table `tbl_member`
--

CREATE TABLE IF NOT EXISTS `tbl_member` (
  `memberId` int(11) NOT NULL,
  `memberName` varchar(32) NOT NULL,
  `memberRole` varchar(32) NOT NULL,
  `memberSpecialty` varchar(32) NOT NULL,
  `memberStatus` varchar(32) NOT NULL,
  `memberUsername` varchar(32) NOT NULL,
  `memberPassword` varchar(32) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_member`
--

INSERT INTO `tbl_member` (`memberId`, `memberName`, `memberRole`, `memberSpecialty`, `memberStatus`, `memberUsername`, `memberPassword`) VALUES
(1, 'Dejan Ivanovic', 'Administrator', 'Supervisor', 'Active', 'admin', 'admin'),
(2, 'Team Member', 'Member', 'Modeling', 'Active', 'team', 'team'),
(4, 'Supervisor', 'Administrator', 'Lighting', 'Suspended', 'sup', 'sup');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_project`
--

CREATE TABLE IF NOT EXISTS `tbl_project` (
  `projectId` int(11) NOT NULL,
  `projectTitle` varchar(32) NOT NULL,
  `projectDescription` text NOT NULL,
  `projectClient` varchar(32) NOT NULL,
  `projectCategory` varchar(32) NOT NULL DEFAULT 'Other',
  `projectStart` date DEFAULT NULL,
  `projectEnd` date DEFAULT NULL,
  `projectDue` date DEFAULT NULL,
  `projectTime` float NOT NULL,
  `projectStatus` varchar(32) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_project`
--

INSERT INTO `tbl_project` (`projectId`, `projectTitle`, `projectDescription`, `projectClient`, `projectCategory`, `projectStart`, `projectEnd`, `projectDue`, `projectTime`, `projectStatus`) VALUES
(1, 'Project 1', 'Some project description', 'Company X', 'Animation', '2015-12-10', NULL, '2015-12-25', 0, 'Active'),
(2, 'Project 2', '', 'Company Y', 'Animation', '2015-12-12', NULL, '2015-12-14', 0, 'Active'),
(5, 'Project 3', 'Description of project 3', 'Client Z', 'Motion_Graphics', '2015-12-17', NULL, '2015-12-19', 4, 'Active'),
(6, 'Project 4', 'Project 4 description', 'Client A', 'Game_Modeling', '2015-12-12', NULL, '2015-12-31', 0, 'Active'),
(7, 'Project Z', 'Project Z short description', 'Client Z', 'TV_Commercial', '0015-11-26', NULL, '0015-11-30', 0, 'Active'),
(8, 'Project 6', 'Description of project 6', 'Client A', 'Motion_Graphics', '2015-10-09', NULL, '0205-10-18', 0, 'Active'),
(9, 'New project', 'new project description', 'some client', 'TV_Commercial', '2015-12-01', NULL, '2015-12-17', 14, 'Active');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tbl_member`
--
ALTER TABLE `tbl_member`
  ADD PRIMARY KEY (`memberId`);

--
-- Indexes for table `tbl_project`
--
ALTER TABLE `tbl_project`
  ADD PRIMARY KEY (`projectId`),
  ADD UNIQUE KEY `projectId` (`projectId`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tbl_member`
--
ALTER TABLE `tbl_member`
  MODIFY `memberId` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `tbl_project`
--
ALTER TABLE `tbl_project`
  MODIFY `projectId` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=10;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
