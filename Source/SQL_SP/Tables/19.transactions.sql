USE [Ofly]
GO

/****** Object:  Table [dbo].[transactions]    Script Date: 27-04-2021 19:54:40 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[transactions]') AND type in (N'U'))
DROP TABLE [dbo].[transactions]
GO

/****** Object:  Table [dbo].[transactions]    Script Date: 27-04-2021 19:54:40 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[transactions](
	[PK_ID] [bigint] IDENTITY(1,1) NOT NULL,
	[transaction_id] [varchar](50) NULL,
	[status_code] [varchar](50) NULL,
	[paid_status] [varchar](50) NULL,
	[paid_mode] [varchar](50) NULL,
	[source] [nvarchar](500) NULL,
	[transaction_date] [datetime] NULL,
	[status_message] [nvarchar](500) NULL,
	[flag] [varchar](50) NULL,
	[amount] [numeric](18, 2) NULL,
	[fk_draft_id] [varchar](50) NULL,
	[created_by] [varchar](50) NULL,
	[created_date] [datetime] NULL,
	[modified_by] [varchar](50) NULL,
	[modified_date] [datetime] NULL,
	[active] [bit] NULL,
 CONSTRAINT [PK_transactions] PRIMARY KEY CLUSTERED 
(
	[PK_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO


CREATE NONCLUSTERED INDEX IX_transactions_transaction_id
ON transactions ([transaction_id] ASC)
GO
CREATE NONCLUSTERED INDEX IX_transactions_transaction_date
ON transactions ([transaction_date] ASC)
GO
CREATE NONCLUSTERED INDEX IX_transactions_fk_draft_id
ON transactions ([fk_draft_id] ASC)
GO
CREATE NONCLUSTERED INDEX IX_transactions_paid_mode
ON transactions ([paid_mode] ASC)
GO
CREATE NONCLUSTERED INDEX IX_transactions_status_code
ON transactions ([status_code] ASC)
GO