USE [Ofly]
GO

/****** Object:  Table [dbo].[offers]    Script Date: 27-04-2021 11:41:06 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[offers]') AND type in (N'U'))
DROP TABLE [dbo].[offers]
GO

/****** Object:  Table [dbo].[offers]    Script Date: 27-04-2021 11:41:06 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[offers](
	[PK_ID] [bigint] IDENTITY(1,1) NOT NULL,
	[content] [varchar](100) NULL,
	[url] [nvarchar](500) NULL,
	[serial_no] [int] NULL,
	[pincode] [varchar](10) NULL,
	[active] [bit] NULL,
	[created_by] [varchar](50) NULL,
	[created_date] [datetime] NULL,
	[modified_by] [varchar](50) NULL,
	[modified_date] [datetime] NULL,
	[effective_from] [datetime] NULL,
	[effective_to] [datetime] NULL,
 CONSTRAINT [PK_offers] PRIMARY KEY CLUSTERED 
(
	[PK_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

CREATE NONCLUSTERED INDEX IX_offers_pincode
ON offers ([pincode] ASC)
Go
CREATE NONCLUSTERED INDEX IX_offers_effective_from
ON offers ([effective_from] ASC)
go
CREATE NONCLUSTERED INDEX IX_offers_offers_effective_to
ON offers ([effective_to] ASC)

