# AstroCade - Proyecto Programación 1
Sistema de simulación de un Arcade desarrollado en **Java (Programacion Orientada a Objetos)**. Este proyecto modela las operaciones clave de un arcade, gestionando la interacción entre usuarios, tarjetas recargables, máquinas de juego, tickets, saldos y premios.
# Integrantes:
- Jonathan Parra Landinez
- Joseph David Gomez Argote
- Nicolas Solarte Moncada

## Descripción del Dominio
El dominio modela el ciclo operativo de un arcade digital actual (reemplazando las fichas físicas por tarjetas electrónicas). El sistema permite registrar clientes, realizar recargas de saldo monetario, simular partidas en diferentes máquinas recreativas con base en un puntaje (*score*), acumular y transferir tickets de recompensa, y canjear premios manteniendo una estricta integridad de inventario y datos.

## Clases Elegidas 

El sistema está diseñado utilizando los principios de la **Programación Orientada a Objetos (POO)** con un encapsulamiento estricto (`private` en atributos) y validaciones lógicas en constructores y métodos. Las clases principales son:

# 1.Usuario
* **Responsabilidad:** Administra la identidad del cliente y gestiona la portabilidad temporal de una tarjeta de juego.
* **Por qué se modeló así:** Se diseñó así para imitar la vida real: una persona y una tarjeta no son la misma cosa. El usuario tiene su nombre y su ID, pero dentro de sus atributos guarda una conexión con una TarjetaArcade. Esto permite que el usuario pueda tener una tarjeta asignada, quedárselo sin ella, o incluso prestarla o transferirla a otra persona para que juegue con su saldo
  
# 2.TarjetaArcade
* **Responsabilidad:** Funciona como la entidad autónoma de transacciones económicas y de recompensa.
* **Por qué se modeló así:** Se separó del usuario para que el saldo en dinero y los tickets acumulados residan en la tarjeta misma de forma independiente. Contiene la lógica de validación para evitar montos negativos en recargas, consumos sin saldo suficiente o descuentos inválidos

# 3.MaquinaArcade
* **Responsabilidad:** Simula las atracciones físicas del arcade (ej. simuladores de juegos).
* **Por qué se modeló así:** Se modeló como una clase independiente que **depende de uso** al recibir una `TarjetaArcade` como parámetro en su método `jugar()`. Esto le permite evaluar el costo de la jugada, descontar saldo y otorgar tickets en función del puntaje obtenido sin necesidad de almacenar datos de usuarios permanentemente

# 4.Premio
* **Responsabilidad:** Controla los artículos disponibles en el mostrador de redención.
* **Por qué se modeló así:** Se estructuró para gestionar un inventario en tiempo real (`stock`) y validar el costo requerido en tickets. Interactúa mediante una **dependencia de transacción** al verificar el balance de la tarjeta y descontar automaticamente las unidades del premio al completarse la compra.

*Universidad Libre Facultad de Ingeniería*
