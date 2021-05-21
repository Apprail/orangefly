
IF OBJECT_ID('usp_menu') IS NOT NULL
DROP PROC usp_menu
GO
-- =============================================
-- Author:		ARULMANI S
-- Create date: 21MAY2020
-- Description:	Get Menu '' ,''
-- SQL:BatchCompleted	EXEC usp_menu 'rizia.arul@gmail.com','E179A1B0-48D2-48BD-838C-301ADB67BE1C','0'
                                            		

-- =============================================
CREATE PROCEDURE usp_menu
	@username	NVARCHAR(200),
	@authtoken NVARCHAR(MAX),
	@ParentID VARCHAR(50)

AS
BEGIN


	SET NOCOUNT ON;
	 EXEC usp_authkeyverification @_username=@username,@_salt=@authtoken

	select m.menu_id,m.menu_name,m.menu_icon,m.menu_parent_id from menu m (nolock)
	inner join screen S (nolock) on s.screen_id=m.screen_id
	inner join roles R (nolock) ON r.roles_id=m.role_id
	inner join users U (nolock) ON U.role_id=r.roles_id
	where (U.mobile_no=@username or email_id=@username) and salt=@authtoken and ISNULL(m.menu_parent_id,'0')=@ParentID
	
END
GO
