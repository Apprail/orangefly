USE [Ofly]
GO

/****** Object:  Table [dbo].[users]    Script Date: 26-04-2021 21:51:24 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[users]') AND type in (N'U'))
DROP TABLE [dbo].[users]
GO

/****** Object:  Table [dbo].[users]    Script Date: 26-04-2021 21:51:24 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[users](
	[PK_ID] [bigint] IDENTITY(1,1) NOT NULL,
	[user_id] [varchar](50) NOT NULL,
	[password] [VARBINARY](MAX) NOT NULL,
	[salt]   [NVARCHAR] (MAX) NOT NULL,
	[email_id] [nvarchar](100) NULL,
	[first_name] [nvarchar](100) NULL,
	[last_name] [nvarchar](100) NULL,
	[mobile_no] [varchar](10) NULL,
	[country_code] [varchar](10) NULL,
	[first_login] [bit] NULL,
	[login_attempt] [int] NULL,
	[created_by] [varchar](50) NULL,
	[created_date] [datetime] NULL,
	[modified_by] [varchar](50) NULL,
	[active] [bit] NULL,
	[role_id] [varchar](50) NULL,
	[source] [varchar](10) NULL,
	[subscribtion_type] [varchar](50) NULL,
 CONSTRAINT [PK_users] PRIMARY KEY CLUSTERED 
(
	[PK_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

CREATE NONCLUSTERED INDEX IX_users_usersdetails
ON users ([user_id] ASC, [mobile_no] ASC, [email_id] ASC)
