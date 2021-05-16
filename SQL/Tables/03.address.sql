USE [Ofly]
GO

/****** Object:  Table [dbo].[address]    Script Date: 27-04-2021 11:24:00 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[address]') AND type in (N'U'))
DROP TABLE [dbo].[address]
GO

/****** Object:  Table [dbo].[address]    Script Date: 27-04-2021 11:24:00 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[address](
	[PK_ID] [bigint] IDENTITY(1,1) NOT NULL,
	[fk_user_id] [varchar](50) NULL,
	[flat_no] [nvarchar](500) NULL,
	[address_1] [nvarchar](500) NULL,
	[address_2] [nvarchar](500) NULL,
	[area] [nvarchar](500) NULL,
	[city] [nvarchar](500) NULL,
	[district] [varchar](50) NULL,
	[state] [varchar](50) NULL,
	[pincode] [varchar](10) NULL,
	[country] [varchar](50) NULL,
	[address_type] [varchar](50) NULL,
	[address_id] [varchar](50) NULL,
	[created_by] [varchar](50) NULL,
	[created_date] [datetime] NULL,
	[modified_by] [varchar](50) NULL,
	[modified_date] [datetime] NULL,
	[active] [bit] NULL,
 CONSTRAINT [PK_address] PRIMARY KEY CLUSTERED 
(
	[PK_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
CREATE NONCLUSTERED INDEX IX_address_fk_user_id
ON address ([fk_user_id] ASC)
Go
CREATE NONCLUSTERED INDEX IX_address_pincode
ON address ([pincode] ASC)
go
CREATE NONCLUSTERED INDEX IX_address_address_type
ON address ([address_type] ASC)
Go
CREATE NONCLUSTERED INDEX IX_address_address_id
ON address ([address_id] ASC)
