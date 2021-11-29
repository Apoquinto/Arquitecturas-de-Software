from django import forms
from django.contrib.auth.forms import UserCreationForm, UserChangeForm
from .models import Productos, Ventas
from .models import CustomUser


class SignUpForm(UserCreationForm):
    full_name = forms.CharField(max_length=100, help_text='Required. 100 charaters of fewer.')
    class Meta:
        model = CustomUser
        fields = UserCreationForm.Meta.fields + ('full_name', 'age',)

class ProductoForm(forms.ModelForm):
    class Meta:
        model = Productos
        fields = "__all__"

class VentaForm(forms.ModelForm):
    class Meta:
        model = Ventas
        fields = "__all__"

