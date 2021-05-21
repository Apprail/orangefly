
IF OBJECT_ID('usp_otp') IS NOT NULL
DROP PROC usp_otp
GO
-- =============================================
-- Author:		ARULMANI S
-- Create date: 18MAY2020
-- Description:	otp  '' ,''
-- SQL:BatchCompleted	EXEC usp_otp 'rizia.arul@gmail.com','0AA8551E-6D87-4DA0-92C1-F14029B6F4DB'
                                            		

-- =============================================
CREATE PROCEDURE usp_otp
	@username	NVARCHAR(200),
	@otpcode NVARCHAR(MAX),
	@otp_type VARCHAR(50), -- Forget password ;
	@mode CHAR(1)  ,--  1 : OTP Creation ; 2 : OTP check
	@smsid NVARCHAR(max)


AS
BEGIN

DECLARE @userid VARCHAR(50)
	SET NOCOUNT ON;
	SET @userid=(SELECT [user_id] FROM users where active=1 and (email_id=@username OR mobile_no=@username))

	if ISNULL(@userid,'')=''
	BEGIN
		SELECT '0' status,'You are not authorized user' message ,'' [user_id],'' username , '' salt
		return 1
	END
	IF @mode='1'
	BEGIN


		IF NOT EXISTS (SELECT [user_id] FROM users where active=1 and (email_id=@username OR mobile_no=@username))
		BEGIN
			SELECT '0' status,'You are not authorized user' message ,'' [user_id],'' username , '' salt
			
			return -1
		END
		ELSE
		BEGIN

		
			
			INSERT INTO otp (fk_user_id,otp_type,otp_status,created_by,created_date,active,otp_code , sms_id)
			SELECT @userid , @otp_type , 'P',@username , GETDATE(),1,@otpcode,@smsid

			SELECT '1' status,'OTP sent to your register mobile no.' + LEFT(@username, 2) + 'XXXXXX' + RIGHT(@username, 2)  message ,'' [user_id],'' username , @smsid salt
		END
	END
	ELSE IF @mode='2'
	BEGIN
		IF EXISTS (select * from otp where active=1 and fk_user_id=@userid and sms_id=@smsid and otp_code=@otpcode and otp_status='P')
		BEGIN

			UPDATE otp SET otp_status='V' where active=1 and fk_user_id=@userid and sms_id=@smsid and otp_code=@otpcode and otp_status='P'

			declare @LENGTH INT,@CharPool varchar(26),@PoolLength varchar(26),@LoopCount  INT  
DECLARE @RandomString VARCHAR(10),@CHARPOOLINT VARCHAR(9)  
  
    
SET @CharPool = 'A!B@C!D#E@FG#H$IJ$K%LM%N*PQR%ST&U*V(W)X_YZ'  
DECLARE @TMPSTR VARCHAR(3)  

SET @PoolLength = DataLength(@CharPool)  
SET @LoopCount = 0  
SET @RandomString = ''  
  
    WHILE (@LoopCount <10)  
    BEGIN  
        SET @TMPSTR =   SUBSTRING(@Charpool, CONVERT(int, RAND() * @PoolLength), 1)           
        SELECT @RandomString  = @RandomString + CONVERT(VARCHAR(5), CONVERT(INT, RAND() * 10))  
        IF(DATALENGTH(@TMPSTR) > 0)  
        BEGIN   
            SELECT @RandomString = @RandomString + @TMPSTR    
            SELECT @LoopCount = @LoopCount + 1  
        END  
    END  
    SET @LOOPCOUNT = 0    
    -- @RandomString 

			UPDATE users set password= HASHBYTES('SHA2_512', @RandomString) where active=1 and (mobile_no=@username OR email_id=@username)

			SELECT '1' status,'OTP Verified successfully' message ,'' [user_id],'' username , @smsid salt ,@RandomString RandomString
		END
		ELSE
		BEGIN
			SELECT '0' status,'Invlaid OTP' message ,'' [user_id],'' username , '' salt,'' RandomString
		END
	END
	 
END

