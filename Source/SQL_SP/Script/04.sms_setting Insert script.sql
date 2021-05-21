


truncate table sms_setting
GO
INSERT INTO  sms_setting (account_sid,auth_token,type,created_by,created_date,active,sender_mobile_no)
SELECT 'ACc0f727c6ee9e461bbd8ac2e04506b4a8','a11620da285e9825323b62bb95bc6642','LIVE','BACKEND',GETDATE(),1,'+13203226026'
UNION ALL 
SELECT 'AC2a03017a9e545f3dff305a69f99bb080','4c328325b14d899337fe322eb727f15e','UAT','BACKEND',GETDATE(),1,'+13203226026'