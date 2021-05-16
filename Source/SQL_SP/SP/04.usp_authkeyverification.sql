
IF OBJECT_ID('usp_authkeyverification') IS NOT NULL
DROP PROC usp_authkeyverification
GO
-- =============================================
-- Author:		ARULMANI S
-- Create date: 11MAY2020
-- Description:	reset password '' ,''
-- SQL:BatchCompleted	EXEC usp_authkeyverification 'rizia.arul@gmail.com','Welcome@123'
                                            		

-- =============================================
CREATE PROCEDURE usp_authkeyverification
	@_username	NVARCHAR(200),
	@_salt NVARCHAR(MAX)

AS
BEGIN


	SET NOCOUNT ON;

	IF NOT EXISTS (SELECT [user_id] FROM users where active=1 and (email_id=@_username OR mobile_no=@_username) and salt=@_salt)
	BEGIN
		SELECT '0' status,'You are not authorized user' message ,'' [user_id],'' first_name ,'' email_id, '' salt
		
		return -1
	END
	 
END

