-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : sam. 04 nov. 2023 à 21:45
-- Version du serveur : 8.0.31
-- Version de PHP : 8.0.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `hotel`
--
CREATE DATABASE IF NOT EXISTS `hotel` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
USE `hotel`;

-- --------------------------------------------------------

--
-- Structure de la table `chambres`
--

DROP TABLE IF EXISTS `chambres`;
CREATE TABLE IF NOT EXISTS `chambres` (
  `numero_chambre` int NOT NULL AUTO_INCREMENT,
  `etage` int NOT NULL,
  `etoiles` int NOT NULL,
  `reserved` tinyint(1) NOT NULL DEFAULT '0',
  `description` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT 'Une chambre',
  PRIMARY KEY (`numero_chambre`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `chambres`
--

INSERT INTO `chambres` (`numero_chambre`, `etage`, `etoiles`, `reserved`, `description`) VALUES
(1, 1, 3, 1, 'Une chambre'),
(2, 2, 3, 1, 'Une chambre'),
(3, 4, 2, 0, 'klllllllllllllllllllllll');

-- --------------------------------------------------------

--
-- Structure de la table `client`
--

DROP TABLE IF EXISTS `client`;
CREATE TABLE IF NOT EXISTS `client` (
  `nom` varchar(30) NOT NULL,
  `tel` int NOT NULL,
  `adresse` varchar(40) NOT NULL,
  `ID` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `tel` (`tel`)
) ENGINE=MyISAM AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `client`
--

INSERT INTO `client` (`nom`, `tel`, `adresse`, `ID`) VALUES
('FOX', 658866639, 'CAMER', 1),
('FOX', 677777777, 'NAMEK', 4),
('toto', 611111111, 'CAMEROUN', 9),
('tata fox', 655555555, 'CAMEROUN', 8),
('dd', 866666666, 'CAMEROUN', 12),
('uu', 888888888, 'CAMEROUN', 11),
('fox c', 111111111, 'CAMEROUN', 13);

-- --------------------------------------------------------

--
-- Structure de la table `reservation`
--

DROP TABLE IF EXISTS `reservation`;
CREATE TABLE IF NOT EXISTS `reservation` (
  `id_reservation` int NOT NULL AUTO_INCREMENT,
  `date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `duree` int NOT NULL DEFAULT '1',
  `numero_chambre` int NOT NULL,
  `ID_client` int NOT NULL,
  PRIMARY KEY (`id_reservation`),
  KEY `fk_numero_chambre` (`numero_chambre`),
  KEY `fk_ID_client` (`ID_client`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `reservation`
--

INSERT INTO `reservation` (`id_reservation`, `date`, `duree`, `numero_chambre`, `ID_client`) VALUES
(1, '2023-11-04 00:00:00', 2, 1, 1),
(3, '2023-11-07 00:00:00', 5, 2, 13);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
