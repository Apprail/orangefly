USE [Ofly]
GO

/****** Object:  Table [dbo].[screen]    Script Date: 27-04-2021 12:02:53 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[screen]') AND type in (N'U'))
DROP TABLE [dbo].[screen]
GO

/****** Object:  Table [dbo].[screen]    Script Date: 27-04-2021 12:02:53 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[screen](
	[PK_ID] [bigint] IDENTITY(1,1) NOT NULL,
	[screen_name] [varchar](100) NULL,
	[screen_link] [varchar](500) NULL,
	[screen_id] [varchar](50) NULL,
	[created_by] [varchar](50) NULL,
	[created_date] [datetime] NULL,
	[modified_by] [varchar](50) NULL,
	[modified_date] [datetime] NULL,
	[active] [bit] NULL,
 CONSTRAINT [PK_screen] PRIMARY KEY CLUSTERED 
(
	[PK_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO


CREATE NONCLUSTERED INDEX IX_screen_screen_id
ON screen ([screen_id] ASC)

