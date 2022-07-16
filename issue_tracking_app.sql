-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 15, 2022 at 08:47 PM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `issue_tracking_app`
--
CREATE DATABASE IF NOT EXISTS `issue_tracking_app` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `issue_tracking_app`;

-- --------------------------------------------------------

--
-- Table structure for table `issues`
--

CREATE TABLE `issues` (
  `id` int(11) NOT NULL,
  `title` varchar(100) NOT NULL,
  `description` text NOT NULL,
  `type` varchar(20) NOT NULL,
  `assigned_to` int(11) DEFAULT NULL COMMENT 'user_id',
  `created_at` date NOT NULL,
  `edited_at` date DEFAULT NULL,
  `edited_by` int(11) DEFAULT NULL COMMENT 'user_id',
  `created_by` int(11) NOT NULL COMMENT 'user_id',
  `status` varchar(20) NOT NULL DEFAULT 'New'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `issues`
--

INSERT INTO `issues` (`id`, `title`, `description`, `type`, `assigned_to`, `created_at`, `edited_at`, `edited_by`, `created_by`, `status`) VALUES
(2, 'Application Error', 'Memory Violation Error ', 'Bug', NULL, '2022-07-13', '2022-07-15', 9, 0, 'In Progress'),
(3, 'Internet Connectivity', 'ADSL Signal Lost', 'Bug', NULL, '2022-07-13', NULL, NULL, 6, 'New'),
(4, 'Configuration', 'Edit Menu not accessible', 'Bug', NULL, '2022-07-13', NULL, NULL, 6, 'New'),
(8, 'UI Issue', 'BootStrap', 'Feature', 10, '2022-07-14', '2022-07-14', 9, 10, 'New'),
(9, 'Management', 'Test UI', 'Feature', 10, '2022-07-14', NULL, NULL, 9, 'New'),
(10, 'test', 'Testing', 'Bug', 10, '2022-07-14', '2022-07-15', 10, 9, 'In Progress');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `username` varchar(100) NOT NULL,
  `lastName` varchar(300) NOT NULL,
  `firstName` varchar(300) NOT NULL,
  `password` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `username`, `lastName`, `firstName`, `password`) VALUES
(9, 'kostas', 'Galatis', 'Konstantinos', '7c4a8d09ca3762af61e59520943dc26494f8941b'),
(10, 'pantelis', 'Galatis', 'Pantelis', '7110eda4d09e062aa5e4a390b0a572ac0d2c0220');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `issues`
--
ALTER TABLE `issues`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `issues`
--
ALTER TABLE `issues`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
