# AstroCade - Proyecto Programación 1
Sistema de simulación de un Arcade desarrollado en **Java (Programacion Orientada a Objetos)**. Este proyecto modela las operaciones clave de un arcade, gestionando la interacción entre usuarios, tarjetas recargables, máquinas de juego, tickets, saldos y premios
# Integrantes:
- Jonathan Parra Landinez
- Joseph David Gomez Argote
- Nicolas Solarte Moncada

## Descripción del Dominio
El dominio modela el ciclo operativo de un arcade digital actual (reemplazando las fichas físicas por tarjetas electrónicas). El sistema permite registrar clientes, realizar recargas de saldo monetario, simular partidas en diferentes máquinas recreativas con base en un puntaje (*score*), acumular y transferir tickets de recompensa, y canjear premios manteniendo una estricta integridad de inventario y datos

## Clases Elegidas 

El sistema está diseñado utilizando los principios de la **Programación Orientada a Objetos (POO)** con un encapsulamiento estricto (private en atributos) y validaciones lógicas en constructores y métodos. Las clases principales son:

# 1.Usuario
* **Responsabilidad:** Administra la identidad del cliente y gestiona la portabilidad temporal de una tarjeta de juego.
* **Por qué se modeló así:** Se diseñó así para imitar la vida real: una persona y una tarjeta no son la misma cosa. El usuario tiene su nombre y su ID, pero dentro de sus atributos guarda una conexión con una TarjetaArcade. Esto permite que el usuario pueda tener una tarjeta asignada, quedárselo sin ella, o incluso prestarla o transferirla a otra persona para que juegue con su saldo
  
# 2.TarjetaArcade
* **Responsabilidad:** Funciona como la entidad autónoma de transacciones económicas y de recompensa.
* **Por qué se modeló así:** Se separó del usuario para que el saldo en dinero y los tickets acumulados residan en la tarjeta misma de forma independiente. Contiene la lógica de validación para evitar montos negativos en recargas, consumos sin saldo suficiente o descuentos inválidos

# 3.MaquinaArcade
* **Responsabilidad:** Simula las maquinas de un Arcade, contando con sus atributos mismos.
* **Por qué se modeló así:** Se modeló como una clase independiente que **depende de uso** al recibir una `TarjetaArcade` como parámetro en su método `jugar()`. Esto le permite evaluar el costo de la jugada, descontar saldo y otorgar tickets en función del puntaje obtenido sin necesidad de almacenar datos de usuarios permanentemente

# 4.Premio
* **Responsabilidad:** Controla los artículos disponibles en el mostrador de redención.
* **Por qué se modeló así:** Se estructuró para gestionar un inventario de que premios hay disponibles y validar el costo requerido en tickets. Interactúa mediante una **dependencia de transacción** al verificar el balance de la tarjeta y descontar automaticamente las unidades del premio al completarse la compra

# Instrucciones Ejecución Codigo:
* Lo primero hacer descarga de la carpeta AstroCade, la cual te permitira tener acceso al codigo, y su ejecucion
* Abir Netbeans
* En el menu superior en la pestaña **file**, selecciona **Open Project**
* El siguiente paso es navegar hasta la direccion/ubicacion de la carpeta **AstroCade**
* Abrir el Archivo
* Tendras acceso al main (AstroCade_Main.java), y las clases correspondientes: MaquinaArcade.java - Premios.java - Tarjeta.java - Usuario.java
* Al momento de ejecutarlo se abrira un menu, el cual te permitira seleccionar que acción deseas realizar: Ver estado de los usuarios, de la tarjeta - Recargar la tarjeta - prestar o transferir tarjeta - simular una partida - canjear un premio -ya por ultimo salir
* Cada opción del menu te permite realizar una parte de la simulación del arcade
* Actualmente tenemos tres Usuarios, Solo hay una Tarjeta, Existen tres premios (Peluche, Audifonos, Consola) y dos maquinas, por ahora el codigo cuenta siendo un prototipo, con planificación a una alta evolucion tanto para más usuarios - tarjetas - maquinas - premios

*Universidad Libre Facultad de Ingeniería*
