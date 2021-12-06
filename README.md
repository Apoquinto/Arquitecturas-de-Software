# Arquitectura de Software

## Integrantes

<center>    
    <table>
        <tr>
            <th><img src="/assets/Foto_Isra.JPG" alt="Foto de Isra" width="200" height="200"></th>
            <th><img src="/assets/Foto_Joshua.jpg" alt="Foto de Joshua" width="200"></th>
            <th><img src="/assets/Foto_Joni.jpg" alt="Foto de Jonathan" width="200"></th>
            <th><img src="/assets/Foto_Apo.jpg" alt="Foto de Roberto" width="200"></th>
        </tr>
        <tr>
            <th>Israel Cruz Morales</th>
            <th>Joshua Meza Magaña</th>
            <th>Jonathan Gómez</th>
            <th>Roberto Llanes Montero</th>
        </tr>
    </table>
</center>

## Como usar

### Dependencias

El servidor ha sido testeado empleando `Python 3.10.0`, el cual necesita de las dependencias declaradas en el archivo de requerimientos. Para instalar todo lo necesario, primero abra la consola de su computador y coloquela en el directorio en el que se encuentra el archivo `requirements.txt`, paso seguido ejecute:

> `pip install -r requirements.txt`

Por otra parte, es necesario tener el software XAMMP instalado en su sistema.

### Base de datos

El servidor emplea una base de datos local, por lo que es necesario empezar a correr el Apache y MySQL en XAMPP, y paso seguido generar un nuevo esquema denominado test. Posteriormente, colocar la consola en el directorio donde se encuentra el archivo `manage.py` y ejecutar el siguiente comando:

> `py manage.py migrate`

### Iniciar la ejecución

Una vez realizados todos los pasos anteriormente mencionados, solo será necesario colocar la consola en el directorio del archivo `manage.py` y ejecutar el siguiente comando:

> `py manage.py runserver`

Finalmente, acceda al [index](http://localhost:8000/), genere una cuenta para poder acceder y disfrute del programa.

## Advertencia

Algunas funcionalidades pueden tener un desempeño inesperado si no se ejecuta la página web en un navegador basado en el motor Chromium.
