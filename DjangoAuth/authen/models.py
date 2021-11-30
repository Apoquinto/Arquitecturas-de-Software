from django.db import models
from django.contrib.auth.models import AbstractUser


class CustomUser(AbstractUser):
    email = models.CharField(max_length=300, blank=False)

class Employee(models.Model):
    name = models.CharField(max_length=100, default='DEFAULT VALUE')
    email = models.CharField(max_length=100, default='DEFAULT VALUE')
    address = models.CharField(max_length=100, default='DEFAULT VALUE')
    phone_num= models.CharField(max_length=100, default='DEFAULT VALUE')
    class Meta:
        db_table = 'employees'
