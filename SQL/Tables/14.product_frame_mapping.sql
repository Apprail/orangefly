USE [Ofly]
GO

/****** Object:  Table [dbo].[product_frame_mapping]    Script Date: 27-04-2021 19:12:10 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[product_frame_mapping]') AND type in (N'U'))
DROP TABLE [dbo].[product_frame_mapping]
GO

/****** Object:  Table [dbo].[product_frame_mapping]    Script Date: 27-04-2021 19:12:10 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[product_frame_mapping](
	[PK_ID] [bigint] IDENTITY(1,1) NOT NULL,
	[product_id] [varchar](500) NULL,
	[frame_id] [varchar](500) NULL,
	[created_by] [varchar](50) NULL,
	[created_date] [datetime] NULL,
	[modified_by] [varchar](50) NULL,
	[modified_date] [datetime] NULL,
	[active] [bit] NULL,
 CONSTRAINT [PK_product_frame_mapping] PRIMARY KEY CLUSTERED 
(
	[PK_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO



CREATE NONCLUSTERED INDEX IX_product_frame_mapping_product_id
ON product_frame_mapping ([product_id] ASC)
GO
CREATE NONCLUSTERED INDEX IX_product_frame_mapping_frame_id
ON product_frame_mapping ([frame_id] ASC)
GO
