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


# Create your views here.
@csrf_exempt
def menulist(request):
    returnvals = {"status": 0, "message": "", "params": {}}
    if request.method == "POST":
        try:
            db = db_connection()
            username = request.POST.get('username')
            salt = request.POST.get('salt')
            parentid = request.POST.get('parentid')

            check_menu_query = """EXEC usp_menu '{uid}' , '{salt}', '{parentid}'
                                """.format(uid=username, salt=salt, parentid=parentid)

            db.execute(check_menu_query)
            check_menu = db.fetchall()
            db.close()

            arr = []
            if check_menu:
                returnvals['status'] = 1
                returnvals['message'] = "Successfully logged in"

                for i in check_menu:
                    returnvals['status'] = "1"
                    returnvals['message'] = "Success"
                    arr.append({"menu_id": i[get_sql_column_index("menu_id")],
                                "menu_name": i[get_sql_column_index("menu_name")],
                                "menu_icon": i[get_sql_column_index("menu_icon")],
                                "menu_parent_id": i[get_sql_column_index("menu_parent_id")]
                                })
                # params = {"username": i[1], "password":i[2]}
                returnvals['params'] = arr
            else:
                returnvals['message'] = "Menu not configuration"
        except Exception as e:
            exc_type, exc_obj, exc_tb = sys.exc_info()
            developerLog("comman", "Method:menulist Line No:" + str(exc_tb.tb_lineno), str(e))

    return HttpResponse(json.dumps(returnvals))


def get_sql_column_index(column_name):
    mapping = {"menu_id": 0,
               "menu_name": 1,
               "menu_icon": 2,
               "menu_parent_id": 3
               }
    return mapping[column_name]
