from __future__ import unicode_literals

import sys
import os
import json
import datetime
import smtplib
from random import randint
from django.http import HttpResponseRedirect, HttpResponse, Http404
from django.conf import settings
from django.shortcuts import render
from django.template.defaultfilters import floatformat, random
from django.views.decorators.csrf import csrf_exempt
from db_config.db_utils import *
from errorlog.error_log import *
from twilio.rest import Client


@csrf_exempt
def login(request):
    returnvals = {"status": 0, "message": "", "params": {}}
    if request.method == "POST":
        try:
            db = db_connection()
            username = request.POST.get('username')
            password = request.POST.get('password')
            # mix_query = """sp_GetGenealogyReportDetails '{curing_lot}'""".format(curing_lot=curing_lot)
            # check_user_query = """select * from users where user_id = '{uid}' and password = '{pwd}'
            #             """.format(uid=username, pwd=password)
            check_user_query = """EXEC usp_login '{uid}' , '{pwd}'""".format(uid=username, pwd=password)
            db.execute(check_user_query)
            check_user = db.fetchall()
            db.close()
            # print(check_user)
            arr = []
            if check_user:
                returnvals['status'] = 1
                returnvals['message'] = "Successfully logged in"
                print(check_user)
                for i in check_user:
                    returnvals['status'] = i[get_sql_column_index_ac("status")]
                    returnvals['message'] = i[get_sql_column_index_ac("message")]
                    arr.append({"username": i[get_sql_column_index("user_id")],
                                "name": i[get_sql_column_index("username")],
                                "salt": i[get_sql_column_index("salt")]
                                })
                # params = {"username": i[1], "password":i[2]}
                returnvals['params'] = arr
            else:
                returnvals['message'] = "Invalid Credentials"
        except Exception as e:
            exc_type, exc_obj, exc_tb = sys.exc_info()
            developerLog("login", "Method:login Line No:" + str(exc_tb.tb_lineno), str(e))

    return HttpResponse(json.dumps(returnvals))


def get_sql_column_index(column_name):
    mapping = {"status": 0,
               "message": 1,
               "user_id": 2,
               "username": 3,
               "salt": 4
               }
    return mapping[column_name]


@csrf_exempt
def create_accounts(request):
    create_account_returns = {"status": 0, "message": "", "params": {}}

    if request.method == "POST":
        try:
            db = db_connection()
            firstname = request.POST.get('firstname','')
            lastname = request.POST.get('lastname','')
            email = request.POST.get('email','')
            password = request.POST.get('password','')
            mobileno = request.POST.get('mobile','')

            check_user_query = """EXEC usp_create_account '{firstName}','{lastName}','{email}','{password}','{mobileno}'
                                """.format(firstName=firstname, lastName=lastname, email=email, password=password,
                                           mobileno=mobileno)
            db.execute(check_user_query)
            check_user = db.fetchall()
            db.close()
            arr = []
            if check_user:
                for i in check_user:
                    create_account_returns['status'] = i[get_sql_column_index_ac("status")]
                    create_account_returns['message'] = i[get_sql_column_index_ac("message")]
                    arr.append({"username": i[get_sql_column_index_ac("user_id")],
                                "name": i[get_sql_column_index_ac("username")],
                                "email": i[get_sql_column_index_ac("email_id")],
                                "salt": i[get_sql_column_index_ac("salt")]
                                })
                # params = {"username": i[1], "password":i[2]}
                create_account_returns['params'] = arr
            else:
                create_account_returns['message'] = "Error in Account creation"
        except Exception as e:
            exc_type, exc_obj, exc_tb = sys.exc_info()
            developerLog("login", "Method:create_accounts Line No:" + str(exc_tb.tb_lineno), str(e))
    return HttpResponse(json.dumps(create_account_returns))
    # return HttpResponse(create_account_returns)


def get_sql_column_index_ac(column_name):
    mapping = {"status": 0,
               "message": 1,
               "user_id": 2,
               "username": 3,
               "email_id": 4,
               "salt": 5
               }

    return mapping[column_name]


@csrf_exempt
def resetpassword(request):
    returns = {"status": 0, "message": "", "params": {}}
    userid = request.POST.get('userid')
    currentpassword = request.POST.get('currentpassword')
    newpassword = request.POST.get('newpassword')
    salt = request.POST.get('salt')

    if request.method == "POST":
        try:
            db = db_connection()

            check_user_query = """EXEC usp_resetpassword '{username}','{password}','{newpassword}','{salt}'
                                            """.format(username=userid, password=currentpassword,
                                                       newpassword=newpassword, salt=salt)
            print(check_user_query)
            db.execute(check_user_query)
            check_user = db.fetchall()
            db.close()
            arr = []
            if check_user:

                for i in check_user:
                    returns['status'] = i[get_sql_column_index_ac("status")]
                    returns['message'] = i[get_sql_column_index_ac("message")]
                    arr.append({"username": i[get_sql_column_index_ac("user_id")],
                                "firstname": i[get_sql_column_index_ac("first_name")],
                                "email": i[get_sql_column_index_ac("email_id")],
                                "salt": i[get_sql_column_index_ac("salt")]
                                })
                    # params = {"username": i[1], "password":i[2]}
                    returns['params'] = arr

            else:
                returns['message'] = "Error in Reset password"
        except Exception as e:
            exc_type, exc_obj, exc_tb = sys.exc_info()
            developerLog("login", "Method:resetpassword Line No:" + str(exc_tb.tb_lineno), str(e))
    return HttpResponse(json.dumps(returns))
    # return HttpResponse(create_account_returns)


@csrf_exempt
def logout(request):
    returnvals = {"status": 0, "message": "", "params": {}}
    if request.method == "POST":
        try:
            db = db_connection()
            username = request.POST.get('username')
            salt = request.POST.get('salt')
            print(username,salt)
            # mix_query = """sp_GetGenealogyReportDetails '{curing_lot}'""".format(curing_lot=curing_lot)
            # check_user_query = """select * from users where user_id = '{uid}' and password = '{salt}'
            #             """.format(uid=username, pwd=password)
            check_user_query = """EXEC usp_logout '{uid}' , '{salt}'""".format(uid=username, salt=salt)
            db.execute(check_user_query)
            check_user = db.fetchall()
            db.close()
            print("check_user",check_user)
            arr = []
            if check_user:
                returnvals['status'] = 1
                returnvals['message'] = "Successfully logged out"
                print(check_user)
                for i in check_user:
                    returnvals['status'] = i[get_sql_column_index_ac("status")]
                    returnvals['message'] = i[get_sql_column_index_ac("message")]
                    arr.append({"username": i[get_sql_column_index("user_id")],
                                "name": i[get_sql_column_index("username")],
                                "salt": i[get_sql_column_index("salt")]
                                })
                # params = {"username": i[1], "password":i[2]}
                returnvals['params'] = arr
            else:
                returnvals['message'] = "Invalid Credentials"
        except Exception as e:
            exc_type, exc_obj, exc_tb = sys.exc_info()
            developerLog("login", "Method:logout Line No:" + str(exc_tb.tb_lineno), str(e))

    return HttpResponse(json.dumps(returnvals))


@csrf_exempt
def sendotp(request):
    returnvals = {"status": 0, "message": "", "params": {}}
    if request.method == "POST":

        try:
            username = request.POST.get('username')
            otp_type = request.POST.get('otp_type')
            mode = str(1)

            db = db_connection()

            check_user_query_User = """select user_id from users where mobile_no = '{uid}' and active = '1'
                                                           """.format(uid=username)
            db.execute(check_user_query_User)
            check_user_ = db.fetchall()
            db.close()

            if not check_user_:
                returnvals["message"] = "You are not authorized user"
                return HttpResponse(json.dumps(returnvals))

            # smsid = request.POST.get('smsid')
            returnvals = one_time_password(settings.SMS_SETTING, username, otp_type, mode, '')
            print(returnvals)

        except Exception as e:
            exc_type, exc_obj, exc_tb = sys.exc_info()
            developerLog("login", "Method:sendotp Line No:" + str(exc_tb.tb_lineno), str(e))

    return HttpResponse(json.dumps(returnvals))


def one_time_password(input, username, otp_type, mode, sms_id):
    try:

        returnvals = {"status": 0, "message": "", "params": {}}

        db = db_connection()

        check_user_query_sms = """select account_sid,auth_token,sender_mobile_no from sms_setting where type = '{type}' 
                                    and active = '1'""".format(type=input)

        print(check_user_query_sms)
        db.execute(check_user_query_sms)
        check_sms = db.fetchall()
        db.close()
        AUTH_SID = ''
        AUTH_TOKEN = ''
        sender_mobile_no = ''
        if not check_sms:
            returnvals["message"] = "SMS setting invalid"
            return HttpResponse(json.dumps(returnvals))
        else:
            for i in check_sms:
                AUTH_SID = i[0]
                AUTH_TOKEN = i[1]
                sender_mobile_no = i[2]

        if not AUTH_SID and not AUTH_TOKEN and not sender_mobile_no:
            returnvals["message"] = "SMS setting invalid"
            return HttpResponse(json.dumps(returnvals))

        client = Client(AUTH_SID, AUTH_TOKEN)

        otpcode = random_with_N_digits(4)
        print(otpcode)

        ''' Change the value of 'from' with the number 
        received from Twilio and the value of 'to'
        with the number in which you want to send message.'''
        message = client.messages.create(
            from_=sender_mobile_no,
            body='Your Orangefly OTP code is ' + str(otpcode),
            to='+91' + str(username)
        )
        returnvals = one_time_password_DB(message.sid, username, otpcode, otp_type, mode)
    except Exception as e:
        exc_type, exc_obj, exc_tb = sys.exc_info()
        developerLog("login", "Method:one_time_password Line No:" + str(exc_tb.tb_lineno), str(e))

    return returnvals


def one_time_password_DB(sid, username, otpcode, otp_type, mode):
    returnvals = {"status": 0, "message": "", "params": {}}
    if sid:
        sms_id = sid
        print(sid)
        db = db_connection()
        check_user_query = """EXEC usp_otp '{uid}' , '{otpcode}','{otp_type}','{mode}','{sid}'""".format(
            uid=username,
            otpcode=otpcode,
            otp_type=otp_type,
            mode=mode,
            sid=sms_id)
        db.execute(check_user_query)
        check_user = db.fetchall()
        db.close()
        # print(check_user)
        arr = []
        if check_user:
            returnvals['status'] = 1
            returnvals['message'] = "Successfully logged out"
            print(check_user)
            for i in check_user:
                returnvals['status'] = i[get_sql_column_index_ac("status")]
                returnvals['message'] = i[get_sql_column_index_ac("message")]
                arr.append({"username": i[get_sql_column_index("user_id")],
                            "name": i[get_sql_column_index("username")],
                            "salt": i[get_sql_column_index("salt")]
                            })
            # params = {"username": i[1], "password":i[2]}
            returnvals['params'] = arr
        else:
            returnvals['message'] = "Invalid Credentials"
    else:
        returnvals['message'] = "Error in message service"

    return returnvals


def random_with_N_digits(n):
    range_start = 10 ** (n - 1)
    range_end = (10 ** n) - 1
    return randint(range_start, range_end)


@csrf_exempt
def verifyotp(request):
    returnvals = {"status": 0, "message": "", "params": {}}
    if request.method == "POST":
        try:
            username = request.POST.get('username')
            otpcode = request.POST.get('otpcode')
            otp_type = request.POST.get('otp_type')
            mode = str(2)
            smsid = request.POST.get('smsid')
            db = db_connection()

            check_user_query = """EXEC usp_otp '{uid}' , '{otpcode}','{otp_type}','{mode}','{sid}'
                                """.format(uid=username,
                                           otpcode=otpcode,
                                           otp_type=otp_type,
                                           mode=mode,
                                           sid=smsid)

            db.execute(check_user_query)
            check_user = db.fetchall()
            db.close()

            arr = []
            pwd = ''
            status = ''
            if check_user:
                # returnvals['status'] = 1
                # returnvals['message'] = "Successfully logged out"

                for i in check_user:
                    returnvals['status'] = i[get_sql_column_index_ac("status")]
                    returnvals['message'] = i[get_sql_column_index_ac("message")]
                    arr.append({"username": i[get_sql_column_index("user_id")],
                                "name": i[get_sql_column_index("username")],
                                "salt": i[get_sql_column_index("salt")]
                                })
                    pwd = i[5]
                    status = i[0]
                    print(pwd)
                # params = {"username": i[1], "password":i[2]}
                returnvals['params'] = arr
                print(returnvals)
                if status == str(1):

                    db = db_connection()

                    check_user_query_sms = """select account_sid,auth_token,sender_mobile_no from sms_setting where type = '{type}' 
                                                and active = '1'""".format(type=settings.SMS_SETTING)

                    print(check_user_query_sms)
                    db.execute(check_user_query_sms)
                    check_sms = db.fetchall()
                    db.close()
                    AUTH_SID = ''
                    AUTH_TOKEN = ''
                    sender_mobile_no = ''
                    if not check_sms:
                        returnvals["message"] = "SMS setting invalid"
                        return HttpResponse(json.dumps(returnvals))
                    else:
                        for i in check_sms:
                            AUTH_SID = i[0]
                            AUTH_TOKEN = i[1]
                            sender_mobile_no = i[2]

                    if not AUTH_SID and not AUTH_TOKEN and not sender_mobile_no:
                        returnvals["status"] = "SMS setting invalid"
                        returnvals["message"] = "SMS setting invalid"
                        return HttpResponse(json.dumps(returnvals))

                    client = Client(AUTH_SID, AUTH_TOKEN)

                    ''' Change the value of 'from' with the number 
                    received from Twilio and the value of 'to'
                    with the number in which you want to send message.'''
                    message = client.messages.create(
                        from_=sender_mobile_no,
                        body='Your Orangefly Reset password code is ' + str(pwd),
                        to='+91' + str(username)
                    )
                    print("sms sent")
                else:
                    print("no sms")
            else:
                returnvals['status'] = 0
                returnvals['message'] = "Error while verify OTP."
        except Exception as e:
            exc_type, exc_obj, exc_tb = sys.exc_info()
            developerLog("login", "Method:sendotp Line No:" + str(exc_tb.tb_lineno), str(e))

    return HttpResponse(json.dumps(returnvals))
