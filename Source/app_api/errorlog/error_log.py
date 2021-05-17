import os.path
import datetime
from app_api import settings
import sys
import os




def developerLog(modulename, method_name,error):
    directory = settings.CONFIG_PATH+'\\API Developer Log\\'+modulename
    fileName=""
    # Check above directory exists or not
    if not os.path.exists(directory):
        os.makedirs(directory)  # Create a directory if not exists
    if not os.path.exists(os.path.join(directory, "APILog_"+str(datetime.date.today())+"_"+".txt")):
        fileName = os.path.join(directory, "APILog_"+str(datetime.date.today())+"_"+".txt")  # Text file name initialize
        file1 = open(fileName, "w")  # a means append error in already existsing file
        file1.write("*****"+str(datetime.datetime.now())+"*****"+'\n'
                    +method_name+":"+error+'\n'
                    "********************End**********************")
        file1.close()
    else:
        fileName = os.path.join(directory,
                                "APILog_" + str(datetime.date.today()) + "_" + ".txt")  # Text file name initialize
        file1 = open(fileName, "a")  # a means append error in already existsing file
        file1.write("*****" + str(datetime.datetime.now()) + "*****" + '\n'
                    + method_name + ":" + error + '\n'
                    "*********************End*********************")
        file1.close()
