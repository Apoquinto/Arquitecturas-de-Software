from django.db.models.manager import EmptyManager
from django.shortcuts import render, redirect, get_object_or_404
from django.contrib.auth import login, authenticate
from django.views.generic.base import TemplateView
from .forms import SignUpForm, EmployeeForm
from .models import Employee
from django.views.generic import ListView, DetailView
from datetime import datetime


def home(request):
    """
    Renderiza una pantalla de inicio.

    **Template:**

    :template:`home/home.html`
    """
    return render(request, 'home/home.html')


def signup(request):
    """
    Despliega el login de usuario.

    **Template:**

    :template:`auth/signup.html`
    """
    if request.method == 'POST':
        form = SignUpForm(request.POST)
        if form.is_valid():
            user = form.save()
            user.save()
            raw_password = form.cleaned_data.get('password1')
            user = authenticate(username=user.username, password=raw_password)
            login(request, user)
            return redirect('/login')
    else:
        form = SignUpForm()
    return render(request, 'auth/signup.html', {'form': form})


class IndexView(ListView):
    """
    Visualiza la lista de empleados :model:`authen.Employee`.

    **Context**

    ``Employee``
        Una instancia de :model:`authen.Employee`.

    **Template:**

    :template:`manage/employees.html`
    """
    template_name = 'manage/employees.html'
    context_object_name = 'employee_list'

    def get_queryset(self):
        return Employee.objects.all()

def create(request):
    """
    despliega un formulario para capturar los datos de un empleado.

    **Template:**

    :template:`manage/employees.html`
    """
    if request.method == 'POST':
        form = EmployeeForm(request.POST)
        if form.is_valid():
            form.save()
            return redirect('/employees')
    form = Employee()
    return redirect('/employees')


def update(request, id ):
    """
    Despliega un formulario para actualizar los datos de un determinado empleado.

    **Template:**

    :template:`manage/employees.html`
    """
    employee = Employee.objects.get(id=id)
    form = EmployeeForm(request.POST, instance=employee)
    if form.is_valid():
        form.save()
        return redirect("/employees")


def delete(request, id):
    """
    Despliega un modal de confirmación para eliminar un empleado en concreto.

    **Template:**

    :template:`manage/employees.html`
    """
    employee = Employee.objects.get(id=id)
    employee.delete()
    return redirect("/employees")

def deletelist(request):
    """
    Despliega un modal de confirmación para eliminar varios empleados en concreto.

    **Template:**

    :template:`manage/employees.html`
    """
    lista = dict(request.POST)
    for element in lista['idCollection[]']:
        employee = Employee.objects.get(id=element)
        employee.delete()
