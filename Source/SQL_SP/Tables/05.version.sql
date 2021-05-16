USE [Ofly]
GO

/****** Object:  Table [dbo].[version]    Script Date: 27-04-2021 11:48:52 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[version]') AND type in (N'U'))
DROP TABLE [dbo].[version]
GO

/****** Object:  Table [dbo].[version]    Script Date: 27-04-2021 11:48:52 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[version](
	[PK_ID] [bigint] IDENTITY(1,1) NOT NULL,
	[app_name] [varchar](50) NULL,
	[version] [varchar](50) NULL,
	[reason] [varchar](500) NULL,
	[source] [varchar](50) NULL,
	[created_by] [varchar](50) NULL,
	[created_date] [datetime] NULL,
	[modified_by] [varchar](50) NULL,
	[modified_date] [datetime] NULL,
	[active] [bit] NULL,
 CONSTRAINT [PK_version] PRIMARY KEY CLUSTERED 
(
	[PK_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO


CREATE NONCLUSTERED INDEX IX_version_source
ON version ([source] ASC)
Go
CREATE NONCLUSTERED INDEX IX_version_version
ON version ([version] ASC)

