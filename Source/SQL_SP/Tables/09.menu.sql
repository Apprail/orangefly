USE [Ofly]
GO

/****** Object:  Table [dbo].[menu]    Script Date: 27-04-2021 12:02:53 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[menu]') AND type in (N'U'))
DROP TABLE [dbo].[menu]
GO

/****** Object:  Table [dbo].[menu]    Script Date: 27-04-2021 12:02:53 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[menu](
	[PK_ID] [bigint] IDENTITY(1,1) NOT NULL,
	[menu_name] [varchar](100) NULL,
	[menu_id] [varchar](50) NULL,
	[menu_icon] [varchar](500) NULL,
	[menu_parent_id] [bigint] NULL,
	[screen_id] [varchar](50) NULL,
	[role_id] [varchar](50) NULL,
	[serial_no] [int] NULL,
	[created_by] [varchar](50) NULL,
	[created_date] [datetime] NULL,
	[modified_by] [varchar](50) NULL,
	[modified_date] [datetime] NULL,
	[active] [bit] NULL,
 CONSTRAINT [PK_menu] PRIMARY KEY CLUSTERED 
(
	[PK_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO


CREATE NONCLUSTERED INDEX IX_menu_menu_id
ON menu ([menu_id] ASC)
GO
CREATE NONCLUSTERED INDEX IX_menu_screen_id
ON menu ([screen_id] ASC)
GO
CREATE NONCLUSTERED INDEX IX_menu_role_id
ON menu ([role_id] ASC)

