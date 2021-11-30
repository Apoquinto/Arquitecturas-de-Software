from django.urls import path, re_path
from django.contrib.auth import views as auth_views
from django.contrib.auth.views import PasswordResetView, PasswordResetDoneView, PasswordResetConfirmView, PasswordResetCompleteView
from authen import views

urlpatterns = [
    path('', views.home, name='home'),
    path('login/', auth_views.LoginView.as_view(template_name='auth/login.html'), name='login'),
    path('logout/', auth_views.LogoutView.as_view(), name='logout'),
    path('signup/', views.signup, name='signup'),
    path('successRecovery/', auth_views.LoginView.as_view(
        template_name='auth/successrecovery.html'), name='successRecovery'),
    path('reset/password_reset', PasswordResetView.as_view(template_name='auth/password_reset_forms.html',
         email_template_name="auth/password_reset_email.html"), name='password_reset'),
    path('reset/password_reset_done', PasswordResetDoneView.as_view(
        template_name='auth/password_reset_done.html'), name='password_reset_done'),
    re_path(r'^reset/(?P<uidb64>[0-9A-za-z_\-]+)/(?P<token>.+)/$', PasswordResetConfirmView.as_view(
        template_name='auth/password_reset_confirms.html'), name='password_reset_confirm'),
    path('reset/done', PasswordResetCompleteView.as_view(
        template_name='auth/password_reset_complete.html'), name='password_reset_complete'),
    path('employees/', views.IndexView.as_view(), name='index'),
    path('employees/update/<int:id>/',
         views.update, name='edit'),
    path('employees/create/', views.create, name='create'),
    path('employees/delete/<int:id>/',
         views.delete, name='delete'),
    path('employees/deletelist/',
    views.deletelist, name='deletelist')
]
