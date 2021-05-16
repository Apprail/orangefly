
IF OBJECT_ID('usp_login') IS NOT NULL
DROP PROC usp_login
GO
-- =============================================
-- Author:		ARULMANI S
-- Create date: 10MAY2020
-- Description:	login '' ,''
-- =============================================
CREATE PROCEDURE usp_login
	@username	NVARCHAR(200),
	@password NVARCHAR(4000)

AS
BEGIN


	SET NOCOUNT ON;
	 
	 DECLARE @salt UNIQUEIDENTIFIER=NEWID()
    -- Insert statements for procedure here
	IF NOT EXISTS (SELECT [user_id] FROM users WHERE (email_id=@username OR mobile_no=@username ) AND active=1)
	BEGIN
	
		SELECT '0' status,'Invalid Username' message ,'' [user_id],''username,'' salt
		update users set login_attempt=1 where email_id=@username and active=1
		return -1
	END
	IF NOT EXISTS (SELECT [user_id] FROM users WHERE (email_id=@username OR mobile_no=@username ) AND active=1 and password= HASHBYTES('SHA2_512', @password))
	BEGIN
	
		SELECT '0' status,'Invalid Passowrd' message,'' [user_id],''username,'' salt
		update users set login_attempt=1 where email_id=@username and active=1
		return -1
	END
	ELSE 
	BEGIN
		update users set first_login=1 ,salt= @salt where (email_id=@username OR mobile_no=@username ) and active=1

		SELECT '1' status,'Successfully login' message,[user_id],CONCAT(first_name, ' ' ,last_name) username ,salt
		from users where (email_id=@username OR mobile_no=@username )AND active=1 and password= HASHBYTES('SHA2_512', @password)
		
		SET @username = (SELECT [user_id] FROM users where active=1 and (email_id=@username OR mobile_no=@username ) )

		INSERT INTO login (fk_user_id,login_date)
		SELECT @username,GETDATE()

	END
	
END
GO
