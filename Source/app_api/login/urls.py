from django.conf.urls import url
from django.urls import path
from login import views as login_urls

app_name = "login"

urlpatterns = [
    path('login/', login_urls.login, name="login"),
    path('register/', login_urls.create_accounts, name="create_accounts"),



]
