from django.conf.urls import url
from django.urls import path
from login import views as login_urls

app_name = "login"

urlpatterns = [
    path('', login_urls.login, name="login"),
]
