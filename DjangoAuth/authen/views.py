from django.db.models.manager import EmptyManager
from django.shortcuts import render, redirect, get_object_or_404
from django.contrib.auth import login, authenticate
from django.views.generic.base import TemplateView
from .forms import SignUpForm, ProductoForm, VentaForm
from .models import Productos, Registros, Ventas
from django.views.generic import ListView, DetailView
from datetime import datetime


def home(request):
    return render(request, 'home/home.html')


def signup(request):
    if request.method == 'POST':
        form = SignUpForm(request.POST)
        if form.is_valid():
            user = form.save()
            user.save()
            raw_password = form.cleaned_data.get('password1')
            user = authenticate(username=user.username, password=raw_password)
            login(request, user)
    else:
        form = SignUpForm()
    return render(request, 'auth/signup.html', {'form': form})


class IndexView(ListView):
    template_name = 'manage/employees.html'
    context_object_name = 'product_list'

    def get_queryset(self):
        return Productos.objects.all()


"""
class SummaryView(ListView):
    template_name = 'manage/summary.html'
    context_object_name = 'register_list'

    def get_queryset(self):
        summary_list = []

        for action in Registros.objects.all():
            summary_list.insert(0, action)

        return summary_list


class SalesView(TemplateView):
    template_name = 'manage/sales.html'

    def get_context_data(self, *args, **kwargs):
        ventas = Ventas.objects.all()
        productos = Productos.objects.all()
        not_products = []
        nombres_productos = []
        productos_ventas = []
        for producto in productos:
            nombres_productos.append(producto.nombre)
        for venta in ventas:
            productos_ventas.append(venta.producto)
        for nombre in nombres_productos:
            if not any([nombre == oferta for oferta in productos_ventas]):
                not_products.append(nombre)
        return {'ventas': ventas, 'productos': productos, "not_products": not_products}
"""


class ProductDetailView(DetailView):
    model = Productos
    template_name = 'auth/product-detail.html'


def createEvent(accion, descripcion, usuario):
    now = datetime.now()
    fecha = str(now.date())
    hora = str(now.time())
    Registros.objects.create(
        accion=accion, descripcion=descripcion, usuario=usuario, fecha=fecha, hora=hora)
    return redirect('/login')


def create(request, user, type):
    if type == 'Product':
        if request.method == 'POST':
            form = ProductoForm(request.POST)
            if form.is_valid():
                form.save()
                accion = type + " \"" + form.data['nombre'] + "\" CREATED"
                descripcion = "A " + type + " has been CREATED"
                usuario = user
                createEvent(accion, descripcion, usuario)
                return redirect('/products')
        form = Productos()
    else:
        if request.method == 'POST':
            form = VentaForm(request.POST)
            if form.is_valid():
                form.save()
                accion = type + " \"" + form.data['producto'] + "\" CREATED"
                descripcion = "A " + type + " has been CREATED"
                usuario = user
                createEvent(accion, descripcion, usuario)
                return redirect('/discounts')
        form = Productos()


def update(request, id, user, type):
    if type == 'Product':
        producto = Productos.objects.get(id=id)
        form = ProductoForm(request.POST, instance=producto)
        if form.is_valid():
            form.save()
            accion = type + " \"" + form.data['nombre'] + "\" UPDATED"
            descripcion = "A " + type + " has been UPDATED"
            usuario = user
            createEvent(accion, descripcion, usuario)
            return redirect("/products")
    else:
        venta = Ventas.objects.get(id=id)
        form = VentaForm(request.POST, instance=venta)
        if form.is_valid():
            form.save()
            accion = type + " \"" + venta.producto + "\" UPDATED"
            descripcion = "A " + type + " has been UPDATED"
            usuario = user
            createEvent(accion, descripcion, usuario)
            return redirect("/discounts")


def delete(request, nombre, user, type):
    if type == 'Product':
        producto = Productos.objects.get(nombre=nombre)
        producto.delete()
        accion = type + " \"" + nombre + "\" DELETED"
        descripcion = "A " + type + " has been DELETED"
        usuario = user
        createEvent(accion, descripcion, usuario)
        return redirect("/products")
    else:
        venta = Ventas.objects.get(producto=nombre)
        venta.delete()
        accion = type + " \"" + nombre + "\" DELETED"
        descripcion = "A " + type + " has been DELETED"
        usuario = user
        createEvent(accion, descripcion, usuario)
        return redirect("/discounts")
