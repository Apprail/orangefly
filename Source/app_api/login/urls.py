from django.conf.urls import url
from django.urls import path
from login import views as login_urls

app_name = "login"

urlpatterns = [
    path('login/', login_urls.login, name="login"),
    path('register/', login_urls.create_accounts, name="create_accounts"),
    path('resetpassword/', login_urls.resetpassword, name="resetpassword"),
    path('logout/', login_urls.logout, name="logout"),
    path('sendotp/', login_urls.sendotp, name="sendotp"),
    path('verifyotp/', login_urls.verifyotp, name="verifyotp"),


]
