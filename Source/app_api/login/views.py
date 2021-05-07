from __future__ import unicode_literals
import sys
import os
import json
from django.http import HttpResponseRedirect, HttpResponse, Http404
from django.shortcuts import render
from django.template.defaultfilters import floatformat
from django.views.decorators.csrf import csrf_exempt
from db_config.db_utils import *
import pyodbc


@csrf_exempt
def login(request):
    returnvals = {"status": 0, "message": "", "params": {}}
    if request.method == "POST":
        try:
            db = ""
        except Exception as e:
            exc_type, exc_obj, exc_tb = sys.exc_info()
            print(str(exc_tb.tb_lineno), str(e))
    return HttpResponse(json.dumps(returnvals))
