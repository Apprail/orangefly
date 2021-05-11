from __future__ import unicode_literals
import sys
import os
import json
from django.http import HttpResponseRedirect, HttpResponse, Http404
from django.shortcuts import render
from django.template.defaultfilters import floatformat
from django.views.decorators.csrf import csrf_exempt
from db_config.db_utils import *


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
                                "name": i[get_sql_column_index("username")]})
                # params = {"username": i[1], "password":i[2]}
                returnvals['params'] = arr
            else:
                returnvals['message'] = "Invalid Credentials"
        except Exception as e:
            exc_type, exc_obj, exc_tb = sys.exc_info()
            print(str(exc_tb.tb_lineno), str(e))
    return HttpResponse(json.dumps(returnvals))


def get_sql_column_index(column_name):
    mapping = {"status": 0,
               "message": 1,
               "user_id": 2,
               "username": 3
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

            check_user_query = """EXEC usp_create_account '{firstName}','{lastName}','{email}','{password}'
                                """.format(firstName=firstname, lastName=lastname, email=email, password=password)
            db.execute(check_user_query)
            check_user = db.fetchall()
            db.close()
            arr = []
            if check_user:

                print(check_user)
                for i in check_user:
                    create_account_returns['status'] = i[get_sql_column_index_ac("status")]
                    create_account_returns['message'] = i[get_sql_column_index_ac("message")]
                    arr.append({"username": i[get_sql_column_index_ac("user_id")],
                                "firstname": i[get_sql_column_index_ac("first_name")],
                                "email": i[get_sql_column_index_ac("email_id")]})
                # params = {"username": i[1], "password":i[2]}
                create_account_returns['params'] = arr
            else:
                create_account_returns['message'] = "Error in Account creation"
        except Exception as e:
            exc_type, exc_obj, exc_tb = sys.exc_info()
            print(str(exc_tb.tb_lineno), str(e))
    return HttpResponse(json.dumps(create_account_returns))
    # return HttpResponse(create_account_returns)


def get_sql_column_index_ac(column_name):
    mapping = {"status": 0,
               "message": 1,
               "user_id": 2,
               "first_name": 3,
               "email_id": 4
               }

    return mapping[column_name]
