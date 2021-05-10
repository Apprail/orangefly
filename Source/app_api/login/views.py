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
            check_user_query = """select * from users"""
            db.execute(check_user_query)
            check_user = db.fetchall()
            # print(check_user)
            arr = []
            if check_user:
                returnvals['status'] = 1
                returnvals['message'] = "Successfully logged in"
                print(check_user)
                for i in check_user:
                    arr.append({"username": i[get_sql_column_index("user_id")],
                                "password": i[get_sql_column_index("password")]})
                # params = {"username": i[1], "password":i[2]}
                returnvals['params'] = arr
            else:
                returnvals['message'] = "Invalid Credentials"
        except Exception as e:
            exc_type, exc_obj, exc_tb = sys.exc_info()
            print(str(exc_tb.tb_lineno), str(e))
    return HttpResponse(json.dumps(returnvals))


def get_sql_column_index(column_name):
    mapping = {"user_id": 1,
               "password": 2
               }
    return mapping[column_name]


def create_accounts(request):
    create_account_returns = {"status": 0, "message": "", "params": {}}
    if request.method == "POST":
        try:
            db = db_connection()
            firstname = request.POST.get('firstname')
            lastname = request.POST.get('lastname')
            email = request.POST.get('email')
            password = request.POST.get('password')
            print(firstname)
            # mix_query = """sp_GetGenealogyReportDetails '{curing_lot}'""".format(curing_lot=curing_lot)
            # check_user_query = """select * from users where user_id = '{uid}' and password = '{pwd}'
            #             """.format(uid=username, pwd=password)
            check_user_query = """usp_create_account '{firstName}','{lastName}','{email}','{password}'
                                """.format(firstName=firstname, lastName=lastname, email=email, password=password)
            db.execute(check_user_query)
            check_user = db.fetchall()
            print(check_user)
            create_account_returns = check_user
        except Exception as e:
            exc_type, exc_obj, exc_tb = sys.exc_info()
            print(str(exc_tb.tb_lineno), str(e))
    return HttpResponse(json.dumps(create_account_returns))
