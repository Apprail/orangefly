
IF OBJECT_ID('usp_resetpassword') IS NOT NULL
DROP PROC usp_resetpassword
GO
-- =============================================
-- Author:		ARULMANI S
-- Create date: 11MAY2020
-- Description:	reset password '' ,''
-- SQL:BatchCompleted	EXEC usp_resetpassword 'rizia.arul@gmail.com','Welcome@123','Welcome@12345'
                                            		

-- =============================================
CREATE PROCEDURE usp_resetpassword
	@username	NVARCHAR(200),
	@password NVARCHAR(4000),
	@newpassword NVARCHAR(4000),
	@authtoken NVARCHAR(MAX)

AS
BEGIN


	SET NOCOUNT ON;
	 EXEC usp_authkeyverification @_username=@username,@_salt=@authtoken

	 DECLARE @salt UNIQUEIDENTIFIER=NEWID()
    -- Insert statements for procedure here
	IF NOT EXISTS (SELECT [user_id] FROM users WHERE email_id=@username AND active=1)
	BEGIN
	
		SELECT '0' status,'Invalid Username' message ,'' [user_id],'' first_name ,'' email_id, '' salt
		update users set login_attempt=1 where email_id=@username and active=1
		return -1
	END
	ELSE IF NOT EXISTS (SELECT [user_id] FROM users WHERE email_id=@username AND active=1 and password= HASHBYTES('SHA2_512', @password))
	BEGIN
	
		SELECT '0' status,'Invalid Passowrd' message,'' [user_id],'' first_name ,'' email_id , '' salt
		update users set login_attempt=1 where email_id=@username and active=1
		return -1
	END
	ELSE 
	BEGIN
		SELECT '1' status,'Password reset successfully' message,[user_id],CONCAT(first_name, ' ' ,last_name) first_name,email_id ,salt
		from users where email_id=@username AND active=1 and password= HASHBYTES('SHA2_512', @password)

		UPDATE users set password=HASHBYTES('SHA2_512', @newpassword) , modified_by=@username,salt=@salt where active=1 and email_id=@username 
		

	END
	
END
GO
