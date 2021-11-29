from django.db import models
from django.contrib.auth.models import AbstractUser


class CustomUser(AbstractUser):
    full_name = models.CharField(max_length=100, blank=False)
    age = models.PositiveIntegerField(null=True, blank=True)


class Productos(models.Model):
    nombre = models.CharField(max_length=100, default='DEFAULT VALUE')
    precio = models.CharField(max_length=20, default='DEFAULT VALUE')
    stock = models.CharField(max_length=100, default='DEFAULT VALUE')

    class Meta:
        db_table = 'productos'


class Registros(models.Model):
    accion = models.CharField(max_length=200, default='DEFAULT VALUE')
    descripcion = models.CharField(max_length=200, default='DEFAULT VALUE')
    usuario = models.CharField(max_length=200, default='DEFAULT VALUE')
    fecha = models.CharField(max_length=200, default='DEFAULT VALUE')
    hora = models.CharField(max_length=200, default='DEFAULT VALUE')

    class Meta:
        db_table = 'registros'


class Ventas(models.Model):
    producto = models.CharField(max_length=100, default='DEFAULT VALUE')
    descuento = models.CharField(max_length=200, default='DEFAULT VALUE')
    inicio = models.CharField(max_length=200, default='DEFAULT VALUE')
    final = models.CharField(max_length=200, default='DEFAULT VALUE')

    class Meta:
        db_table = 'ventas'
