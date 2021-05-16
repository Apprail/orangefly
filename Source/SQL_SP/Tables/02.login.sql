USE [Ofly]
GO

/****** Object:  Table [dbo].[login]    Script Date: 26-04-2021 22:14:05 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[login]') AND type in (N'U'))
DROP TABLE [dbo].[login]
GO

/****** Object:  Table [dbo].[login]    Script Date: 26-04-2021 22:14:05 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[login](
	[PK_ID] [bigint] IDENTITY(1,1) NOT NULL,
	[fk_user_id] [varchar](50) NULL,
	[login_date] [datetime] NULL,
	[logout_date] [datetime] NULL,
 CONSTRAINT [PK_login] PRIMARY KEY CLUSTERED 
(
	[PK_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO


CREATE NONCLUSTERED INDEX IX_login_fk_user_id
ON login ([fk_user_id] ASC)