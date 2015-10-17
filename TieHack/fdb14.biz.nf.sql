-- phpMyAdmin SQL Dump
-- version 3.4.3.1
-- http://www.phpmyadmin.net
--
-- Host: fdb14.biz.nf
-- Generation Time: Oct 17, 2015 at 05:35 AM
-- Server version: 5.5.38
-- PHP Version: 5.3.27

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `1973905_tie`
--
CREATE DATABASE `1973905_tie` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `1973905_tie`;

-- --------------------------------------------------------

--
-- Table structure for table `register`
--

CREATE TABLE IF NOT EXISTS `register` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `tid` varchar(255) NOT NULL,
  `pwd` varchar(40) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `register`
--

INSERT INTO `register` (`id`, `email`, `tid`, `pwd`) VALUES
(1, '', 'hhh', 'c84c766f873ecedf75aa6cf35f1e305e095fec83'),
(2, 'as@gmail.com', 'hh', '9a900f538965a426994e1e90600920aff0b4e8d2'),
(3, 'rr@gmail.com', 'gg', '9a900f538965a426994e1e90600920aff0b4e8d2'),
(4, 'bb@cc.com', 'jamun', 'jamun');

-- --------------------------------------------------------

--
-- Table structure for table `reviews`
--

CREATE TABLE IF NOT EXISTS `reviews` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `item` text NOT NULL,
  `desc` text NOT NULL,
  `tid` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=12 ;

--
-- Dumping data for table `reviews`
--

INSERT INTO `reviews` (`id`, `item`, `desc`, `tid`) VALUES
(5, 'jamun', 'jamun', 'jamun'),
(8, 'Macbook Pro', 'Windows users missing a lot.', 'PreetamPrashant'),
(9, 'AsusZenfone2', 'Best Phone Under 15k', 'AbhishekGhazu'),
(10, 'MacBook Air', 'Light and Great', 'Ritwiktwiks'),
(11, 'Nexus 6', 'Lots of Promises', 'PreetamPrashant');

-- --------------------------------------------------------

--
-- Table structure for table `tweets`
--

CREATE TABLE IF NOT EXISTS `tweets` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tweets` text NOT NULL,
  `text` text NOT NULL,
  `tid` varchar(255) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Dumping data for table `tweets`
--

INSERT INTO `tweets` (`id`, `tweets`, `text`, `tid`, `created_at`) VALUES
(1, '#TieHackReview Nexus 6', 'Nexus 6', 'PreetamPrashant', '2015-10-17 04:31:38'),
(2, '#TieHackReview Macbook Pro', 'Macbook Pro', 'PreetamPrashant', '2015-10-17 04:37:04'),
(3, '#TieHackReview Asus Zenfone2', 'Asus Zenfone2', 'AbhishekGhazu', '2015-10-17 04:37:04'),
(4, '#TieHackReview MotoX Gen3', 'MotoX Gen3', 'Ritwiktwiks', '2015-10-17 04:39:07'),
(5, '#TieHackReview Jamun', 'Jamun', 'Jamun', '2015-10-17 04:39:07');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
