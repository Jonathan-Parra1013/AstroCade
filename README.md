# AstroCade - Proyecto Programación 1
Sistema de simulación de un Arcade desarrollado en **Java (Programacion Orientada a Objetos)**. Este proyecto modela las operaciones clave de un arcade, gestionando la interacción entre usuarios, tarjetas recargables, máquinas de juego, tickets, saldos y premios
# Integrantes:
- Jonathan Parra Landinez
- Joseph David Gomez Argote
- Nicolas Solarte Moncada

## Descripción del Dominio
El dominio modela el ciclo operativo de un arcade digital avanzado (reemplazando fichas físicas por tarjetas electrónicas). El sistema permite registrar clientes con validación automática de cumpleaños y administradores con seguridad por contraseña, gestionar tarjetas inteligentes con límites y descuentos polimórficos (Basica, Gold, Diamond), simular partidas en máquinas especializadas mediante herencia, acumular tickets y controlar eventos globales en tiempo real (Black Friday) manteniendo una estricta integridad de inventario y datos.

## Clases Elegidas 

El sistema está diseñado utilizando los principios avanzados de la Programación Orientada a Objetos (POO) con un encapsulamiento estricto (private en atributos) y validaciones lógicas. Las clases y jerarquías principales son:

# Jerarquía de Usuarios (Usuario, Cliente, Administrador)
* **Responsabilidad:** Administrar la identidad de las personas en el sistema.
* **Por qué se modeló así:** Se estructuró mediante herencia. La clase padre Usuario maneja los datos base, mientras que Cliente incorpora la validación automática de la fecha de nacimiento para otorgar beneficios de cumpleaños, y Administrador incorpora seguridad por contraseña para restringir el acceso a la gestión global.
  
# 2.Jerarquía de Tarjetas (Tarjeta - Clase Abstracta y Polimorfismo)
* **Responsabilidad:** Funcionar como la entidad autónoma de transacciones económicas y de recompensa.
* **Por qué se modeló así:** Se diseñó como una clase abstracta con identificadores generados automáticamente. Las clases hijas (Basica, Gold, Diamond) implementan polimorfismo mediante @Override para calcular descuentos personalizados (0%, 10%, 25%) y definir topes de saldo máximos.

# 3.Jerarquía de Máquinas (Maquina y especializadas)
* **Responsabilidad:** Simular las atracciones mecánicas del arcade (como MaquinaGarra, MaquinaCarreras, etc.).
* **Por qué se modeló así:** Heredan de una clase padre Maquina que controla el estado operativo (Activa o En Mantenimiento). Utilizan una dependencia de uso al recibir la tarjeta como parámetro para descontar saldos y otorgar tickets.

# Gestión de Premios y Eventos (Premios, Evento)
* **Responsabilidad:** Controlar el inventario en el mostrador de redención y las reglas temporales del local.
* **Por qué se modeló así:** Permiten validar el costo en tickets requeridos, descontar stock en tiempo real y modular el costo de las partidas según eventos globales como el Black Friday.

# Instrucciones Ejecución Codigo:
* Lo primero hacer Descarga o clona la carpeta del proyecto AstroCade para tener acceso a los archivos fuente, y su ejecucion
* Abre tu entorno de desarrollo Apache NetBeans.
* En el menu superior en la pestaña **file**, selecciona **Open Project**
* El siguiente paso es navegar hasta la direccion/ubicacion de la carpeta **AstroCade**
* Abrir el Archivo
* Abre el archivo principal de ejecución (AstroCade.java dentro del paquete correspondiente).
* Al ejecutarlo, se abrirá un menú interactivo en la consola que te permitirá:
* - Registrar nuevos clientes y asignarles su tarjeta de forma automática.
  - Entrar como cliente para consultar saldos y jugar en las máquinas.
  - Entrar como administrador (ingresando la contraseña de seguridad) para recargar tarjetas, poner máquinas en mantenimiento o cambiar el evento global.
  - Canjear premios utilizando los tickets acumulados.
  


*Universidad Libre Facultad de Ingeniería*
