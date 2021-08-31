IF OBJECT_ID('usp_logout') IS NOT NULL
DROP PROC usp_logout
GO
-- =============================================
-- Author:		ARULMANI S
-- Create date: 11MAY2020
-- Description:	reset password '' ,''
-- SQL:BatchCompleted	EXEC usp_logout 'rizia.arul@gmail.com','0AA8551E-6D87-4DA0-92C1-F14029B6F4DB'
                                            		

-- =============================================
CREATE PROCEDURE usp_logout
	@_username	NVARCHAR(200),
	@_salt NVARCHAR(MAX)

AS
BEGIN


	SET NOCOUNT ON;

	IF NOT EXISTS (SELECT [user_id] FROM users where active=1 and (email_id=@_username OR mobile_no=@_username OR user_id=@_username) and salt=@_salt)
	BEGIN
		SELECT '0' status,'You are not authorized user' message ,'' [user_id],'' username , '' salt
		
		return -1
	END
	ELSE
	BEGIN
		UPDATE users set salt='' where active=1 and  (mobile_no=@_username OR email_id =@_username) and salt=@_salt
		DECLARE @PK_ID bigint

		SET @_username = (SELECT [user_id] FROM users where active=1 and (email_id=@_username OR mobile_no=@_username OR user_id=@_username) )

		SELECT TOP 1 @PK_ID=PK_ID FROM login where fk_user_id=@_username order by PK_ID desc

		UPDATE login set logout_date=GETDATE() where PK_ID=@PK_ID

		SELECT '1' status,'Logged out successfully' message ,'' [user_id],'' username , '' salt
	END
	 
END
