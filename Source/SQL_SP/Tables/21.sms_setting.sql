USE [Ofly]
GO

/****** Object:  Table [dbo].[sms_setting]    Script Date: 18-05-2021 11:41:03 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[sms_setting]') AND type in (N'U'))
DROP TABLE [dbo].[sms_setting]
GO

/****** Object:  Table [dbo].[sms_setting]    Script Date: 18-05-2021 11:41:03 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[sms_setting](
	[PK_ID] [bigint] IDENTITY(1,1) NOT NULL,
	[account_sid] [NVARCHAR] (MAX) NULL,
	[auth_token] [NVARCHAR] (MAX) null,
	[type] [varchar] (50) null,
	[sender_mobile_no] [NVARCHAR] (500) NULL,
	[created_by] [varchar](50) NULL,
	[created_date] [datetime] NULL,
	[modified_by] [varchar](50) NULL,
	[modified_date] [datetime] NULL,
	[active] [bit] NULL,
 CONSTRAINT [PK_sms_setting] PRIMARY KEY CLUSTERED 
(
	[PK_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO


