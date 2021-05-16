USE [Ofly]
GO

/****** Object:  Table [dbo].[product]    Script Date: 27-04-2021 19:12:10 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[product]') AND type in (N'U'))
DROP TABLE [dbo].[product]
GO

/****** Object:  Table [dbo].[product]    Script Date: 27-04-2021 19:12:10 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[product](
	[PK_ID] [bigint] IDENTITY(1,1) NOT NULL,
	[product_id] [varchar](500) NULL,
	[product_name] [varchar](500) NULL,
	[parent_id] [bigint] NULL,
	[product_type] [varchar](500) NULL,
	[created_by] [varchar](50) NULL,
	[created_date] [datetime] NULL,
	[modified_by] [varchar](50) NULL,
	[modified_date] [datetime] NULL,
	[active] [bit] NULL,
 CONSTRAINT [PK_product] PRIMARY KEY CLUSTERED 
(
	[PK_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO



CREATE NONCLUSTERED INDEX IX_product_product_id
ON product ([product_id] ASC)
GO
CREATE NONCLUSTERED INDEX IX_product_parent_id
ON product ([parent_id] ASC)
GO
CREATE NONCLUSTERED INDEX IX_product_product_type
ON product ([product_type] ASC)