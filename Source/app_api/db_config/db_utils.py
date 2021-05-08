import time
import pyodbc
from db_config import config

def db_connection():
    sql_con = config.credentials[config.db_environment]
    sql_con_str = """DRIVER=SQL Server;SERVER={server}; 
                        PORT={port};DATABASE={db};
                        UID={uid};PWD={pwd};
                        PERSIST_SECURITY=false""".format(
                                                            server=sql_con["host"],
                                                            port=sql_con["port"],
                                                            db=sql_con["database"],
                                                            uid=sql_con["user"],
                                                            pwd=sql_con["password"])


    # Connection string QA Staging TABLE **START**
    cnxnMS = pyodbc.connect(sql_con_str)
    cursor = cnxnMS.cursor()
    return cursor


class DBManager:
    __config = None
    __sleep_sec_to_reset = 0
    __connection = None

    def __init__(self, config, sleep_sec_to_reset=0):
        self.__config = config
        self.__sleep_sec_to_reset = sleep_sec_to_reset
        self.__open_connection()

    def __open_connection(self):
        self.__connection = pyodbc.connect(**self.__config)


    def close(self):
        self.__connection.close()

    def cursor(self, reset_connection=False):
        if reset_connection:
            self.reset_connection()
            return self.__connection.cursor()
        try:
            return self.__connection.cursor()
        except pyodbc.Error as e:
            if _is_conn_close_error(e):
                self.reset_connection()
                return self.__connection.cursor()

    def reset_connection(self):
        self.close()
        time.sleep(self.__sleep_sec_to_reset)
        self.__open_connection()
