USE [Ofly]
GO

/****** Object:  Table [dbo].[draft]    Script Date: 27-04-2021 19:24:40 ******/

/****** Object:  Table [dbo].[draft]    Script Date: 27-04-2021 19:12:10 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[draft]') AND type in (N'U'))
DROP TABLE [dbo].[draft]
GO
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[draft](
	[PK_ID] [bigint] IDENTITY(1,1) NOT NULL,
	[draft_id] [varchar](50) NULL,
	[draft_status] [varchar](50) NULL,
	[fk_order_id] [varchar](50) NULL,
	[created_by] [varchar](50) NULL,
	[created_date] [datetime] NULL,
	[modified_by] [varchar](50) NULL,
	[modified_date] [datetime] NULL,
	[active] [bit] NULL,
 CONSTRAINT [PK_draft] PRIMARY KEY CLUSTERED 
(
	[PK_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

CREATE NONCLUSTERED INDEX IX_draft_draft_id
ON draft ([draft_id] ASC)
GO
CREATE NONCLUSTERED INDEX IX_draft_fk_order_id
ON draft ([fk_order_id] ASC)
GO

