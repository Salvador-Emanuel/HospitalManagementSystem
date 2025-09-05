-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 24-Ago-2025 às 19:16
-- Versão do servidor: 10.4.22-MariaDB
-- versão do PHP: 8.1.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `hospital`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `admin`
--

CREATE TABLE `admin` (
  `admin_id` int(11) DEFAULT NULL,
  `email` varchar(150) DEFAULT NULL,
  `username` varchar(100) DEFAULT NULL,
  `password` varchar(100) NOT NULL,
  `conf_password` varchar(100) DEFAULT NULL,
  `full_name` varchar(100) DEFAULT NULL,
  `image` varchar(500) DEFAULT NULL,
  `gender` varchar(50) DEFAULT NULL,
  `date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `admin`
--

INSERT INTO `admin` (`admin_id`, `email`, `username`, `password`, `conf_password`, `full_name`, `image`, `gender`, `date`) VALUES
(1, 'admin@gmail.com', 'admin', '12345678', NULL, NULL, 'C:\\Users\\Elga\\Documents\\NetBeansProjects\\HospitalManagementSystem\\src\\Admin_Directory\\1.jpg', 'Masculino', '2025-02-19'),
(2, 'test', 'Test', 'test12345', NULL, NULL, 'C:\\Users\\Elga\\Documents\\NetBeansProjects\\HospitalManagementSystem\\src\\Admin_Directory\\2.jpg', 'Feminimo', '2025-02-25');

-- --------------------------------------------------------

--
-- Estrutura da tabela `appointment`
--

CREATE TABLE `appointment` (
  `id` int(11) NOT NULL,
  `appointment_id` int(50) NOT NULL,
  `patient_id` bigint(50) DEFAULT NULL,
  `name` varchar(100) NOT NULL,
  `gender` varchar(50) NOT NULL,
  `description` varchar(500) NOT NULL,
  `diagnosis` varchar(500) DEFAULT NULL,
  `treatment` varchar(500) DEFAULT NULL,
  `mobile_number` bigint(50) NOT NULL,
  `address` varchar(500) NOT NULL,
  `date` date DEFAULT NULL,
  `date_modify` date DEFAULT NULL,
  `date_delete` date DEFAULT NULL,
  `status` varchar(50) NOT NULL,
  `doctor` varchar(100) DEFAULT NULL,
  `specialized` varchar(100) DEFAULT NULL,
  `schedule` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `appointment`
--

INSERT INTO `appointment` (`id`, `appointment_id`, `patient_id`, `name`, `gender`, `description`, `diagnosis`, `treatment`, `mobile_number`, `address`, `date`, `date_modify`, `date_delete`, `status`, `doctor`, `specialized`, `schedule`) VALUES
(1, 1, 20250001, 'Samuel', 'Male', 'White man and hair.', 'Diabetes', 'Injectar insulina', 843781419, 'Marracuene,Guava', '2025-03-20', '2025-03-04', '2025-03-08', 'Activo', 'Matias', 'Generalista', '2025-03-28'),
(2, 2, 3, 'Marcos', 'Male', 'Jovem negro', 'Atrofia cerrebelosa', 'Injeccao', 841234567, 'Marracuen', '2025-03-20', '2025-03-04', '2025-03-08', 'Activo', 'Matias', 'Generalista', '2025-03-28'),
(3, 3, 3, 'Joao', 'Masculino', 'Negro', 'TDAH', 'Medicacao', 844567876, 'Activo', '2025-03-08', '2025-07-12', NULL, 'Activo', 'Marcelo', 'Genecologista', '2025-07-25'),
(4, 4, NULL, 'Paulo Amosse', 'Masculino', 'Alto, Magro', 'Hemoroidas', 'Cirurgia', 840909900, 'Activo', '2025-03-08', '2025-03-09', NULL, 'Activo', '20250001', '', '2025-03-21'),
(5, 5, NULL, 'Helton Cumbe', 'Masculino', 'Alto, Claro', 'Estomago', 'Paracetamol', 850870800, 'Guava', '2025-03-08', NULL, NULL, 'Activo', '20250001', NULL, '2025-03-28'),
(6, 6, NULL, 'Janny', 'Feminimo', 'Alta,Clara', 'Autista', 'Medicacao', 854345456, 'Activo', '2025-03-09', '2025-03-09', '2025-05-12', 'Activo', '20250001', NULL, '2025-03-27'),
(7, 7, NULL, 'Alda', 'Feminimo', 'Magra', 'Gravida', 'Parto', 842323456, 'Activo', '2025-03-09', '2025-07-12', NULL, 'Activo', '20250001', NULL, '2025-07-26'),
(8, 8, NULL, 'Ezequiel Manjate', 'Masculino', 'Alto Claro', 'Tosse intestinal', 'Alimentação com Fibras.', 841212345, 'Activo', '2025-07-12', '2025-07-17', NULL, 'Activo', '20250001', NULL, '2025-07-10');

-- --------------------------------------------------------

--
-- Estrutura da tabela `doctor`
--

CREATE TABLE `doctor` (
  `id` int(11) NOT NULL,
  `doctor_id` int(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  `full_name` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `gender` varchar(100) DEFAULT NULL,
  `mobile_number` bigint(100) DEFAULT NULL,
  `specialized` varchar(100) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `image` varchar(500) DEFAULT NULL,
  `date` date NOT NULL,
  `modify_date` date DEFAULT NULL,
  `delete_date` date DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `doctor`
--

INSERT INTO `doctor` (`id`, `doctor_id`, `password`, `full_name`, `email`, `gender`, `mobile_number`, `specialized`, `address`, `image`, `date`, `modify_date`, `delete_date`, `status`) VALUES
(1, 20250001, '123456789', 'Salvador Cossa', 'salvadorcossa@gmail.com', 'Masculino', 843781419, 'Cardiologista, gender =Masculino, gender =Masculino, gender =Masculino', 'Marracuene, Guava', 'C:\\Users\\Elga\\Documents\\NetBeansProjects\\HospitalManagementSystem\\src\\Doctor_Directory\\20250001.jpg', '2025-02-21', '2025-07-31', NULL, 'Activo'),
(2, 20250002, '123456789', 'admin admin', 'admin@gmail.com', NULL, NULL, 'Obstetra', NULL, NULL, '2025-02-21', NULL, '2025-08-20', 'Activo'),
(3, 0, '123456789', 'admin admin', 'admin@gmail.com', NULL, NULL, 'Pediatra', NULL, NULL, '2025-02-21', NULL, '2025-08-20', 'Activo');

-- --------------------------------------------------------

--
-- Estrutura da tabela `patient`
--

CREATE TABLE `patient` (
  `id` int(50) NOT NULL,
  `patient_id` int(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  `full_name` varchar(100) DEFAULT NULL,
  `gender` varchar(200) DEFAULT NULL,
  `mobile_number` varchar(50) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `image` varchar(500) DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL,
  `diagnosis` varchar(200) DEFAULT NULL,
  `treatment` varchar(200) DEFAULT NULL,
  `doctor` varchar(100) DEFAULT NULL,
  `specialized` varchar(100) DEFAULT NULL,
  `date` date NOT NULL,
  `date_modify` date DEFAULT NULL,
  `date_delete` date DEFAULT NULL,
  `status` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `patient`
--

INSERT INTO `patient` (`id`, `patient_id`, `password`, `full_name`, `gender`, `mobile_number`, `address`, `image`, `description`, `diagnosis`, `treatment`, `doctor`, `specialized`, `date`, `date_modify`, `date_delete`, `status`) VALUES
(1, 20250001, 'paciente123', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2025-02-14', NULL, '2025-08-22', ''),
(2, 2025002, '123456789', 'Kensane de Jesus Salvador', 'Feminimo', '843781419', 'Marracuene, Guava, Q:92 CN:71 Rua do Pinga-Pinga', NULL, NULL, NULL, NULL, '20250001', 'Genecologista', '2025-07-12', '2025-08-24', NULL, 'Activo'),
(3, 2025003, '123456789', 'Elga de Jesus Salvador Cossa', 'Feminimo', '864563331', 'Ka Mavota, Mahotas, Q:92 CN:71', NULL, NULL, 'Super Dotada', 'Resolver qualquer problema', '20250001', 'Genecologista', '2025-07-12', '2025-08-24', NULL, 'Confirmado'),
(4, 20250009, '123456789', 'Artu Chaisso', 'Masculino', '841232435', 'GUAVA CIRCULAR', NULL, NULL, NULL, NULL, '20250001', 'Genecologista', '2025-07-17', '2025-08-20', '2025-08-24', 'Confirm'),
(5, 20250010, '123456789', 'Anabela Magaia', 'Feminimo', '875656678', 'Marracuene, Guava Q:25, CN: 12', NULL, NULL, NULL, NULL, '20250001', 'Genecologista', '2025-07-17', '2025-08-20', '2025-08-24', 'Confirm'),
(6, 2025011, '123456789', 'Lucia Langa', NULL, '868503092', 'Patrice', NULL, NULL, NULL, NULL, '20250002', 'Genecologista', '2025-07-22', NULL, '2025-08-22', 'Confirm'),
(7, 2025012, '123456789', 'Marta Kambule', NULL, '842312123', 'Matola 700', NULL, NULL, NULL, NULL, '20250002', 'Genecologista', '2025-07-30', NULL, '2025-08-22', 'Confirm');

--
-- Índices para tabelas despejadas
--

--
-- Índices para tabela `appointment`
--
ALTER TABLE `appointment`
  ADD PRIMARY KEY (`id`);

--
-- Índices para tabela `doctor`
--
ALTER TABLE `doctor`
  ADD PRIMARY KEY (`id`);

--
-- Índices para tabela `patient`
--
ALTER TABLE `patient`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `appointment`
--
ALTER TABLE `appointment`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de tabela `doctor`
--
ALTER TABLE `doctor`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de tabela `patient`
--
ALTER TABLE `patient`
  MODIFY `id` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
