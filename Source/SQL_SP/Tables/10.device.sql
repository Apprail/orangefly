USE [Ofly]
GO

/****** Object:  Table [dbo].[device]    Script Date: 27-04-2021 18:59:28 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[device]') AND type in (N'U'))
DROP TABLE [dbo].[device]
GO

/****** Object:  Table [dbo].[device]    Script Date: 27-04-2021 18:59:28 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[device](
	[PK_ID] [bigint] IDENTITY(1,1) NOT NULL,
	[device_id] [nvarchar](max) NULL,
	[android_id] [nvarchar](max) NULL,
	[fk_user_id] [varchar](50) NULL,
	[version] [varchar](50) NULL,
	[created_by] [varchar](50) NULL,
	[created_date] [datetime] NULL,
	[modified_by] [varchar](50) NULL,
	[modified_date] [datetime] NULL,
	[active] [bit] NULL,
 CONSTRAINT [PK_device] PRIMARY KEY CLUSTERED 
(
	[PK_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO




CREATE NONCLUSTERED INDEX IX_device_version
ON device ([version] ASC)
GO
CREATE NONCLUSTERED INDEX IX_device_fk_user_id
ON device ([fk_user_id] ASC)