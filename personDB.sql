USE [master]
GO
/****** Object:  Database [personDB]    Script Date: 8/14/2017 3:47:48 PM ******/
CREATE DATABASE [personDB]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'personDB', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL11.MSSQLSERVER\MSSQL\DATA\personDB.mdf' , SIZE = 64512KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'personDB_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL11.MSSQLSERVER\MSSQL\DATA\personDB_log.ldf' , SIZE = 1280KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [personDB] SET COMPATIBILITY_LEVEL = 110
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [personDB].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [personDB] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [personDB] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [personDB] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [personDB] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [personDB] SET ARITHABORT OFF 
GO
ALTER DATABASE [personDB] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [personDB] SET AUTO_CREATE_STATISTICS ON 
GO
ALTER DATABASE [personDB] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [personDB] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [personDB] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [personDB] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [personDB] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [personDB] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [personDB] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [personDB] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [personDB] SET  DISABLE_BROKER 
GO
ALTER DATABASE [personDB] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [personDB] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [personDB] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [personDB] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [personDB] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [personDB] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [personDB] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [personDB] SET RECOVERY FULL 
GO
ALTER DATABASE [personDB] SET  MULTI_USER 
GO
ALTER DATABASE [personDB] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [personDB] SET DB_CHAINING OFF 
GO
ALTER DATABASE [personDB] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [personDB] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
EXEC sys.sp_db_vardecimal_storage_format N'personDB', N'ON'
GO
USE [personDB]
GO
/****** Object:  StoredProcedure [dbo].[saveMultipleRows]    Script Date: 8/14/2017 3:47:48 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE PROCEDURE [dbo].[saveMultipleRows] 
	-- Add the parameters for the stored procedure here
	@fname varchar(20),
	@surname varchar(20),
	@mobileNumber varchar(20)
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
	INSERT INTO TBPERSON (fname,surname,mobileNumber) values(@fname,@surname,@mobileNumber);

END

GO
/****** Object:  Table [dbo].[TBPERSON]    Script Date: 8/14/2017 3:47:48 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TBPERSON](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[fname] [nchar](20) NOT NULL,
	[surname] [nchar](20) NOT NULL,
	[mobileNumber] [nchar](20) NOT NULL
) ON [PRIMARY]

GO
ALTER TABLE [dbo].[TBPERSON] ADD  CONSTRAINT [DF_TBPERSON_fname]  DEFAULT ('') FOR [fname]
GO
ALTER TABLE [dbo].[TBPERSON] ADD  CONSTRAINT [DF_TBPERSON_surname]  DEFAULT ('') FOR [surname]
GO
ALTER TABLE [dbo].[TBPERSON] ADD  CONSTRAINT [DF_TBPERSON_mobileNumber]  DEFAULT ('') FOR [mobileNumber]
GO
USE [master]
GO
ALTER DATABASE [personDB] SET  READ_WRITE 
GO
