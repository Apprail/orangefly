USE [Ofly]
GO

/****** Object:  Table [dbo].[settings]    Script Date: 27-04-2021 19:05:30 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[settings]') AND type in (N'U'))
DROP TABLE [dbo].[settings]
GO

/****** Object:  Table [dbo].[settings]    Script Date: 27-04-2021 19:05:30 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[settings](
	[PK_ID] [bigint] IDENTITY(1,1) NOT NULL,
	[code_type] [varchar](200) NULL,
	[code] [varchar](50) NULL,
	[code_description] [varchar](500) NULL,
	[created_by] [varchar](50) NULL,
	[created_date] [datetime] NULL,
	[modified_by] [varchar](50) NULL,
	[modified_date] [datetime] NULL,
	[active] [bit] NULL,
 CONSTRAINT [PK_settings] PRIMARY KEY CLUSTERED 
(
	[PK_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO


CREATE NONCLUSTERED INDEX IX_settings_code_type
ON settings ([code_type] ASC)
GO
CREATE NONCLUSTERED INDEX IX_settings_code_description
ON settings ([code_description] ASC)
GO
CREATE NONCLUSTERED INDEX IX_settings_code
ON settings ([code] ASC)