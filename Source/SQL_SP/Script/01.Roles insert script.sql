
truncate table roles
GO
insert into roles (roles_name
,roles_id
,created_by
,created_date
,active)
SELECT 'Admin','A0001','Backend',GETDATE(),0
UNION ALL 
SELECT 'User','U0001','Backend',GETDATE(),1
