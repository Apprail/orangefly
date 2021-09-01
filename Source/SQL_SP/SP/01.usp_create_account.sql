
IF OBJECT_ID('usp_create_account') IS NOT NULL
DROP PROC usp_create_account
GO
-- =============================================
-- Author:		ARULMANI S
-- Create date: 10MAY2020
-- Description:	Create Account
-- =============================================
CREATE PROCEDURE usp_create_account
	@firstName VARCHAR(100),
	@lastName  VARCHAR(100),
	@email	NVARCHAR(200),
	@password NVARCHAR(4000),
	@mobileno VARCHAR(15)

AS
BEGIN


	SET NOCOUNT ON;
	 DECLARE @salt UNIQUEIDENTIFIER=NEWID()

	 
DECLARE @chars NCHAR(36)

SET @chars = N'0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ'

DECLARE @result NCHAR(2)

SET @result = SUBSTRING(@chars, CAST((RAND() * LEN(@chars)) AS INT) + 1, 1)

+ SUBSTRING(@chars, CAST((RAND() * LEN(@chars)) AS INT) + 1, 1)

+ SUBSTRING(@chars, CAST((RAND() * LEN(@chars)) AS INT) + 1, 1)

+ SUBSTRING(@chars, CAST((RAND() * LEN(@chars)) AS INT) + 1, 1)

+ SUBSTRING(@chars, CAST((RAND() * LEN(@chars)) AS INT) + 1, 1)



    -- Insert statements for procedure here
	IF NOT EXISTS (SELECT [user_id] FROM users WHERE (email_id=@email OR mobile_no=@mobileno) AND active=1)
	BEGIN
	
		INSERT INTO users ( [user_id], [password],email_id,first_name,last_name,active,created_by,created_date,salt,mobile_no,role_id)
		SELECT  CONCAT(@firstName,YEAR(GETDATE()),MONTH(GETDATE()),DAY(GETDATE()),@result), HASHBYTES('SHA2_512', @password),@email,@firstName,@lastName,1,'Self',GETDATE(),@salt
		,@mobileno,'U0001'

		SELECT  '1' status,'Account created successfully' message 
		, user_id ,CONCAT(first_name, ' ' ,last_name) username ,email_id,salt
		--AS[params.user_id] 
		
		from users WHERE email_id=@email AND active=1 
		--FOR JSON PATH   
		return 1
	END
	ELSE IF  EXISTS (SELECT [user_id] FROM users WHERE mobile_no=@mobileno AND active=1)
	BEGIN
		SELECT '0' status,'This Mobile no. already exists in our system, Please use another' message
		, '' [user_id],'' username ,'' email_id	,'' salt
		return 1
	END
	ELSE
	BEGIN

		SELECT '0' status,'This email already exists in our system, Please use another' message
		, '' [user_id],'' username ,'' email_id	,'' salt
		--AS[params.user_id] 
		
		return 1
		--FOR JSON PATH  
	END
END
GO
