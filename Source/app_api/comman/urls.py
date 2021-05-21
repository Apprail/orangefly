from django.conf.urls import url
from django.urls import path
from comman import views as comman_urls

app_name = "comman"

urlpatterns = [
    path('menulist/', comman_urls.menulist, name="menulist"),



]
