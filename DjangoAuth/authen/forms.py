from django import forms
from django.contrib.auth.forms import UserCreationForm, UserChangeForm
from .models import Employee
from .models import CustomUser

class SignUpForm(UserCreationForm):
    email = forms.CharField(max_length=400)
    class Meta:
        model = CustomUser
        fields = UserCreationForm.Meta.fields + ('email',)

class EmployeeForm(forms.ModelForm):
    class Meta:
        model = Employee
        fields = "__all__"
