USE [Ofly]
GO

/****** Object:  Table [dbo].[orders]    Script Date: 27-04-2021 19:46:25 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[orders]') AND type in (N'U'))
DROP TABLE [dbo].[orders]
GO

/****** Object:  Table [dbo].[orders]    Script Date: 27-04-2021 19:46:25 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[orders](
	[PK_ID] [bigint] IDENTITY(1,1) NOT NULL,
	[order_id] [varchar](50) NULL,
	[transaction_id] [varchar](50) NULL,
	[mail_sent] [varchar](10) NULL,
	[sms_sent] [varchar](10) NULL,
	[total_amount] [numeric](18, 2) NULL,
	[gst] [numeric](18, 2) NULL,
	[promocode] [varchar](50) NULL,
	[accept_date] [datetime] NULL,
	[dispatch_date] [datetime] NULL,
	[delivery_date] [datetime] NULL,
	[delivery_charges] [numeric](18, 2) NULL,
	[address_id] [varchar](50) NULL,
	[status] [varchar](50) NULL,
	[created_by] [varchar](50) NULL,
	[created_date] [datetime] NULL,
	[modified_by] [varchar](50) NULL,
	[modified_date] [datetime] NULL,
	[active] [bit] NULL,
 CONSTRAINT [PK_orders] PRIMARY KEY CLUSTERED 
(
	[PK_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO



CREATE NONCLUSTERED INDEX IX_orders_order_id
ON orders ([order_id] ASC)
GO
CREATE NONCLUSTERED INDEX IX_order_transaction_id
ON orders ([transaction_id] ASC)
GO
CREATE NONCLUSTERED INDEX IX_order_address_id
ON orders ([address_id] ASC)
GO