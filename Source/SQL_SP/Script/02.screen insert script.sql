
truncate table screen
GO
INSERT INTO screen (screen_name,screen_link,screen_id,created_by,created_date,active)
SELECT 'Print Photo','','S0001','BACKEND',GETDATE(),1
UNION ALL
SELECT 'Print Book','','S0002','BACKEND',GETDATE(),1