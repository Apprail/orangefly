from __future__ import unicode_literals

import sys
import os
import json
import datetime
from random import randint
from django.http import HttpResponseRedirect, HttpResponse, Http404
from django.conf import settings
from django.shortcuts import render
from django.template.defaultfilters import floatformat, random
from django.views.decorators.csrf import csrf_exempt
from db_config.db_utils import *
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
            print(str(exc_tb.tb_lineno), str(e))
            # creating / opening a file
            x = datetime.datetime.now()
            logfilename = settings.LOG_FILE + "\loginlogfile.txt_" + str(x.year) + "_" + str(x.month) + "_" + str(x.day)
            f = open(logfilename, "a")
            # writing  in the file
            f.writelines("\n login : method : ")
            f.writelines("\n---------------------------------------")
            f.writelines("\n" + username)
            f.writelines("\n")
            f.writelines("\n" + str(e))
            f.writelines("\n---------------------------------------")
            f.close()

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
            firstname = request.POST.get('firstname')
            lastname = request.POST.get('lastname')
            email = request.POST.get('email')
            password = request.POST.get('password')
            mobileno = request.POST.get('mobileno')

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
                                "firstname": i[get_sql_column_index_ac("first_name")],
                                "email": i[get_sql_column_index_ac("email_id")],
                                "salt": i[get_sql_column_index_ac("salt")]
                                })
                # params = {"username": i[1], "password":i[2]}
                create_account_returns['params'] = arr
            else:
                create_account_returns['message'] = "Error in Account creation"
        except Exception as e:
            exc_type, exc_obj, exc_tb = sys.exc_info()
            print(str(exc_tb.tb_lineno), str(e))

            # creating / opening a file
            x = datetime.datetime.now()
            logfilename = settings.LOG_FILE + "\loginlogfile.txt_" + str(x.year) + "_" + str(x.month) + "_" + str(x.day)
            f = open(logfilename, "a")
            # writing  in the file
            f.writelines("\n create_accounts : method : ")
            f.writelines("\n---------------------------------------")
            f.writelines("\n" + mobileno)
            f.writelines("\n")
            f.writelines("\n" + str(e))
            f.writelines("\n---------------------------------------")
            f.close()
    return HttpResponse(json.dumps(create_account_returns))
    # return HttpResponse(create_account_returns)


def get_sql_column_index_ac(column_name):
    mapping = {"status": 0,
               "message": 1,
               "user_id": 2,
               "first_name": 3,
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
            print(str(exc_tb.tb_lineno), str(e))
            # creating / opening a file
            x = datetime.datetime.now()
            logfilename = settings.LOG_FILE + "\loginlogfile.txt_" + str(x.year) + "_" + str(x.month) + "_" + str(x.day)
            f = open(logfilename, "a")
            # writing  in the file
            f.writelines("\n resetpassword : method : ")
            f.writelines("\n---------------------------------------")
            f.writelines("\n" + userid)
            f.writelines("\n")
            f.writelines("\n" + str(e))
            f.writelines("\n---------------------------------------")
            f.close()
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
            # mix_query = """sp_GetGenealogyReportDetails '{curing_lot}'""".format(curing_lot=curing_lot)
            # check_user_query = """select * from users where user_id = '{uid}' and password = '{salt}'
            #             """.format(uid=username, pwd=password)
            check_user_query = """EXEC usp_logout '{uid}' , '{salt}'""".format(uid=username, salt=salt)
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
        except Exception as e:
            exc_type, exc_obj, exc_tb = sys.exc_info()
            print(str(exc_tb.tb_lineno), str(e))
            # creating / opening a file
            x = datetime.datetime.now()
            logfilename = settings.LOG_FILE + "\loginlogfile.txt_" + str(x.year) + "_" + str(x.month) + "_" + str(x.day)
            f = open(logfilename, "a")
            # writing  in the file
            f.writelines("\n logout : method : ")
            f.writelines("\n---------------------------------------")
            f.writelines("\n" + username)
            f.writelines("\n")
            f.writelines("\n" + str(e))
            f.writelines("\n---------------------------------------")
            f.close()

    return HttpResponse(json.dumps(returnvals))


@csrf_exempt
def sendotp(request):
    returnvals = {"status": 0, "message": "", "params": {}}
    if request.method == "POST":
        try:
            db = db_connection()
            username = request.POST.get('username')
            # account_sid = os.environ['ACc0f727c6ee9e461bbd8ac2e04506b4a8']
            # auth_token = os.environ['9163bdd65bb8b73f1e898f06b7c31d01']
            # client = Client("ACc0f727c6ee9e461bbd8ac2e04506b4a8", "8e9b5062a9310bbae06a6613c10cb2af")

            client = Client(settings.ACCOUNT_SID, settings.AUTH_TOKEN)

            number = random_with_N_digits(4)
            print(number)

            ''' Change the value of 'from' with the number 
            received from Twilio and the value of 'to'
            with the number in which you want to send message.'''
            message = client.messages.create(
                from_='+13203226026',
                body='Your Orangefly OTP code is ' + str(number),
                to='+91' + str(username)
            )

            print(message.sid)

        except Exception as e:
            exc_type, exc_obj, exc_tb = sys.exc_info()
            print(str(exc_tb.tb_lineno), str(e))
            # creating / opening a file
            x = datetime.datetime.now()
            logfilename = settings.LOG_FILE + "\loginlogfile.txt_" + str(x.year) + "_" + str(x.month) + "_" + str(x.day)
            f = open(logfilename, "a")
            # writing  in the file
            f.writelines("\n logout : method : ")
            f.writelines("\n---------------------------------------")
            f.writelines("\n" + username)
            f.writelines("\n")
            f.writelines("\n" + str(e))
            f.writelines("\n---------------------------------------")
            f.close()

    return HttpResponse(json.dumps(returnvals))


def random_with_N_digits(n):
    range_start = 10 ** (n - 1)
    range_end = (10 ** n) - 1
    return randint(range_start, range_end)
