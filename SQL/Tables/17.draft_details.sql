USE [Ofly]
GO

/****** Object:  Table [dbo].[draft_details]    Script Date: 27-04-2021 19:36:10 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[draft_details]') AND type in (N'U'))
DROP TABLE [dbo].[draft_details]
GO

/****** Object:  Table [dbo].[draft_details]    Script Date: 27-04-2021 19:36:10 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[draft_details](
	[PK_ID] [bigint] IDENTITY(1,1) NOT NULL,
	[fk_draft_id] [varchar](50) NULL,
	[image_url] [nvarchar](500) NULL,
	[image_path] [nvarchar](500) NULL,
	[serial_number] [int] NULL,
	[fk_product_id] [varchar](50) NULL,
	[fk_frame_id] [varchar](50) NULL,
	[amount] [numeric](18, 2) NULL,
	[discount] [numeric](18, 2) NULL,
	[created_by] [varchar](50) NULL,
	[created_date] [datetime] NULL,
	[modified_by] [varchar](50) NULL,
	[modified_date] [datetime] NULL,
	[active] [bit] NULL,
 CONSTRAINT [PK_draft_details] PRIMARY KEY CLUSTERED 
(
	[PK_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO


CREATE NONCLUSTERED INDEX IX_draft_fk_draft_id
ON draft_details ([fk_draft_id] ASC)
GO
CREATE NONCLUSTERED INDEX IX_draft_details_fk_product_id
ON draft_details ([fk_product_id] ASC)
GO
CREATE NONCLUSTERED INDEX IX_draft_details_fk_frame_id
ON draft_details ([fk_frame_id] ASC)
GO