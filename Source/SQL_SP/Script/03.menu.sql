
truncate table menu
GO
insert into menu (menu_name,menu_id,menu_icon,menu_parent_id,screen_id,role_id,serial_no,created_by,created_date,active)
select 'Prints','M0001','',NULL,'S0001','U0001',1,'BACKEND',GETDATE(),1
UNION ALL
select 'Books','M0002','',NULL,'S0002','U0001',1,'BACKEND',GETDATE(),1
UNION ALL
select '4x4','M0001','',1,'S0001','U0001',1,'BACKEND',GETDATE(),1
UNION ALL
select '4x6','M0001','',1,'S0001','U0001',1,'BACKEND',GETDATE(),1
UNION ALL
select '6x4','M0001','',1,'S0001','U0001',1,'BACKEND',GETDATE(),1
UNION ALL
select '5x7','M0001','',1,'S0001','U0001',1,'BACKEND',GETDATE(),1
UNION ALL
select 'Packet (4)','M0001','',1,'S0001','U0001',1,'BACKEND',GETDATE(),1
UNION ALL
select '8x8','M0001','',1,'S0001','U0001',1,'BACKEND',GETDATE(),1
UNION ALL
select '8x10','M0001','',1,'S0001','U0001',1,'BACKEND',GETDATE(),1
UNION ALL
select '11x14','M0001','',1,'S0001','U0001',1,'BACKEND',GETDATE(),1
UNION ALL
select '12x12','M0001','',1,'S0001','U0001',1,'BACKEND',GETDATE(),1
UNION ALL
select '16x20','M0001','',1,'S0001','U0001',1,'BACKEND',GETDATE(),1
UNION ALL
select '20x30','M0001','',1,'S0001','U0001',1,'BACKEND',GETDATE(),1
UNION ALL
select '5x15','M0001','',1,'S0001','U0001',1,'BACKEND',GETDATE(),1
UNION ALL
select '8x24','M0001','',1,'S0001','U0001',1,'BACKEND',GETDATE(),1
UNION ALL
select '12x36','M0001','',1,'S0001','U0001',1,'BACKEND',GETDATE(),1
-- Menu Books
UNION ALL 
select '6x6 Hardcover','M0002','',2,'S0002','U0001',1,'BACKEND',GETDATE(),1
UNION ALL
select '6x8 Hardcover','M0002','',2,'S0002','U0001',2,'BACKEND',GETDATE(),1
UNION ALL
select '8x11 Hardcover','M0002','',2,'S0002','U0001',3,'BACKEND',GETDATE(),1
UNION ALL 
select '10x10 Hardcover','M0002','',2,'S0002','U0001',4,'BACKEND',GETDATE(),1
UNION ALL
select '12x12 Hardcover','M0002','',2,'S0002','U0001',5,'BACKEND',GETDATE(),1
UNION ALL
select '11x14 Hardcover','M0002','',2,'S0002','U0001',6,'BACKEND',GETDATE(),1
UNION ALL
select '6x6 Softcover','M0002','',2,'S0002','U0001',7,'BACKEND',GETDATE(),1
UNION ALL 
select '8x8 Softcover','M0002','',2,'S0002','U0001',8,'BACKEND',GETDATE(),1
UNION ALL
select '8x11 Softcover','M0002','',2,'S0002','U0001',9,'BACKEND',GETDATE(),1
UNION ALL
select '10x10 Softcover','M0002','',2,'S0002','U0001',10,'BACKEND',GETDATE(),1