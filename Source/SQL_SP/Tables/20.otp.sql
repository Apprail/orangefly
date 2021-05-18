USE [Ofly]
GO

/****** Object:  Table [dbo].[otp]    Script Date: 18-05-2021 11:41:03 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[otp]') AND type in (N'U'))
DROP TABLE [dbo].[otp]
GO

/****** Object:  Table [dbo].[otp]    Script Date: 18-05-2021 11:41:03 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[otp](
	[PK_ID] [bigint] IDENTITY(1,1) NOT NULL,
	[fk_user_id] [varchar](50) NULL,
	[otp_code] INT NULL,
	[otp_type] [varchar](500) NULL,
	[otp_status] [varchar](50) NULL,
	[sms_id] [NVARCHAR] (MAX) NULL,
	[created_by] [varchar](50) NULL,
	[created_date] [datetime] NULL,
	[modified_by] [varchar](50) NULL,
	[modified_date] [datetime] NULL,
	[active] [bit] NULL,
 CONSTRAINT [PK_otp] PRIMARY KEY CLUSTERED 
(
	[PK_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO


