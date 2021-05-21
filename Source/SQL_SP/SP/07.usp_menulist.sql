
IF OBJECT_ID('usp_menulist') IS NOT NULL
DROP PROC usp_menulist
GO
-- =============================================
-- Author:		ARULMANI S
-- Create date: 21MAY2020
-- Description:	Get Menu '' ,''
-- SQL:BatchCompleted	EXEC usp_menulist 'rizia.arul@gmail.com','E179A1B0-48D2-48BD-838C-301ADB67BE1C'
                                            		

-- =============================================
CREATE PROCEDURE usp_menulist
	@username	NVARCHAR(200),
	@authtoken NVARCHAR(MAX)

AS
BEGIN


	SET NOCOUNT ON;
	 EXEC usp_authkeyverification @_username=@username,@_salt=@authtoken

	select m.menu_id,m.menu_name,m.menu_icon,m.menu_parent_id from menu m (nolock)
	inner join screen S (nolock) on s.screen_id=m.screen_id
	inner join roles R (nolock) ON r.roles_id=m.role_id
	inner join users U (nolock) ON U.role_id=r.roles_id
	where (U.mobile_no=@username or email_id=@username) and salt=@authtoken
	
END
GO
