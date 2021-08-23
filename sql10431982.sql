-- phpMyAdmin SQL Dump
-- version 4.7.1
-- https://www.phpmyadmin.net/
--
-- Servidor: sql10.freesqldatabase.com
-- Tiempo de generación: 20-08-2021 a las 21:57:02
-- Versión del servidor: 5.5.62-0ubuntu0.14.04.1
-- Versión de PHP: 7.0.33-0ubuntu0.16.04.16

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `sql10431982`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `COMPRA`
--

CREATE TABLE `COMPRA` (
  `ID_COMPRA` bigint(19) NOT NULL,
  `CEDULA_COMPRADOR` varchar(255) NOT NULL,
  `FECHA_COMPRA` date NOT NULL,
  `NOMBRE_COMPRADOR` varchar(255) NOT NULL,
  `VALOR_PAGADO` bigint(19) NOT NULL,
  `PRODUCTO` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `PRODUCTO`
--

CREATE TABLE `PRODUCTO` (
  `CODIGO` varchar(255) NOT NULL,
  `CEDULA_VENDEDOR` varchar(255) NOT NULL,
  `DESCUENTO` bigint(19) NOT NULL,
  `FECHA` date NOT NULL,
  `NOMBRE` varchar(255) NOT NULL,
  `NOMBRE_VENDEDOR` varchar(255) NOT NULL,
  `VALOR` bigint(19) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `PRODUCTO`
--
ALTER TABLE `PRODUCTO`
  ADD PRIMARY KEY (`CODIGO`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
