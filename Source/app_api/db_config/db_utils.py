import time
import pyodbc


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

    def cursor(self, reset_connection=False, name=None):
        if reset_connection:
            self.reset_connection()
            return self.__connection.cursor(name=name)
        try:
            return self.__connection.cursor(name=name)
        except pyodbc.Error as e:
            if _is_conn_close_error(e):
                self.reset_connection()
                return self.__connection.cursor(name=name)

    def reset_connection(self):
        self.close()
        time.sleep(self.__sleep_sec_to_reset)
        self.__open_connection()
