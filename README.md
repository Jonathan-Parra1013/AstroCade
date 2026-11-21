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

## Requisitos y ejecución

**Requisitos:** JDK 21, Apache NetBeans (o Maven) y MySQL (XAMPP) encendido en el puerto 3306 con usuario `root` y contraseña vacía.

**1. Crear la base de datos**
1. Inicia **MySQL** desde el panel de XAMPP.
2. Abre phpMyAdmin (`http://localhost/phpmyadmin`) → pestaña **Importar** → elige [`database/astrocade_db.sql`](database/astrocade_db.sql) → **Continuar**.
3. Se crea la base `astrocade_db` con sus 7 tablas y los datos de prueba (un aviso `#1046 No database selected` al inicio es normal: el script crea y selecciona la base más adelante).

La conexión está en `BaseDatos/Conexion.java` (`jdbc:mysql://localhost:3306/astrocade_db`, usuario `root`, sin contraseña).

**2. Ejecutar la aplicación**
1. Clona el repositorio y abre en NetBeans la carpeta del proyecto Maven (la que contiene `pom.xml`: `AstroCade/`).
2. Ejecuta `AstroCade.java` (clase principal `com.github.jonathanparra1013.astrocade.AstroCade`): se abre la ventana de ingreso.

**Datos de prueba:** administrador con contraseña `1234`; cliente de ejemplo `Juanito Perez` (tarjeta Diamond `TARJ-001`, sin contraseña).

**¿Qué se puede hacer?**
- **Cliente:** ver saldo y tickets, jugar en las máquinas (el precio depende de su tarjeta y del evento activo) y canjear premios en la tienda.
- **Administrador:** recargar tarjetas, registrar clientes, poner máquinas en mantenimiento o reactivarlas y cambiar el evento global (Normal, Black Friday, Cumpleaños).

## Estructura de paquetes

Todos bajo `com.github.jonathanparra1013.astrocade`:

| Paquete | Contenido |
|---|---|
| (raíz) | `AstroCade` (clase principal) |
| `Usuarios` | `Usuario`, `Cliente`, `Administrador`, `GestorClientes` |
| `Tarjetas` | `Tarjeta`, `Basica`, `Gold`, `Diamond`, `EstrategiaDescuento`, `DescuentoBasico/Gold/Diamond`, `TarjetaFactory` |
| `Maquinas` | `Maquina`, `MaquinaCarreras`, `MaquinaDisparos`, `MaquinaGarra`, `MaquinaRuleta`, `EstadoMaquina`, `EstadoActiva/Mantenimiento/FueraDeServicio`, `MaquinaFactory` |
| `Premios` | `Premios`, `PremioPorMaquina`, `PremioPorTickets` |
| `LogicaAstroCade` | `AstroCadeFacade`, `Evento`, `Entregable` |
| `Repositorios` | Interfaces `ClienteRepositorio`, `MaquinaRepositorio`, `PremioRepositorio`, `UsuarioRepositorio`, `EventoRepositorio` |
| `BaseDatos` | `Conexion` y los `*DAO` (implementan los repositorios), `PersistenciaException` |
| `ManejoErrores` | Excepciones propias del dominio |
| `AstroCadeVisual` | Pantallas Swing |

## Mapa del proyecto

### Pilares de POO

| Concepto | Dónde está | Cómo se usa |
|---|---|---|
| Herencia | `Usuarios`: `Usuario` → `Cliente`, `Administrador`; `Tarjetas`: `Tarjeta` → `Basica`, `Gold`, `Diamond`; `Maquinas`: `Maquina` → `MaquinaCarreras`, `MaquinaDisparos`, `MaquinaGarra`, `MaquinaRuleta`; `Premios`: `Premios` → `PremioPorMaquina`, `PremioPorTickets` | Cada subclase reutiliza los atributos y la lógica del padre y especializa su comportamiento. |
| Polimorfismo | `Maquina.jugarPartida()` (`@Override` en cada máquina), `Tarjeta.getTipo()`, `EstrategiaDescuento.calcular()`, `EstadoMaquina.puedeJugar()` | `AstroCadeFacade.jugar(Maquina, Tarjeta)` recibe una referencia del tipo padre y cada máquina juega a su manera; `Tarjeta.calcularDescuento()` delega en la estrategia de su tipo. |
| Encapsulamiento | Clases de `Usuarios`, `Tarjetas`, `Maquinas` y `Premios` | Atributos `private`, acceso por métodos y validación antes de cambiar el estado (`Tarjeta.recargarSaldo`, `Tarjeta.descontarTickets`, constructores de `Usuario` y `Maquina`). |
| Abstracción | `Usuario` (`getRol`), `Tarjeta` (`getTipo`), `Maquina` (`jugarPartida`), `Premios` (`mostrarPremio`) | Modelan conceptos generales del arcade con métodos abstractos que implementa cada hija. |
| Interfaces | `EstrategiaDescuento` (3 implementaciones), `EstadoMaquina` (3 implementaciones), `ClienteRepositorio`, `MaquinaRepositorio`, `PremioRepositorio`, `UsuarioRepositorio`, `EventoRepositorio` | Se usan como tipo: `Tarjeta` guarda una `EstrategiaDescuento`, `Maquina` guarda un `EstadoMaquina` y `AstroCadeFacade` trabaja con los repositorios. |
| Manejo de errores | `ManejoErrores`: `AstroCadeException` (extends `Exception`), `DatoInvalidoException`, `LimiteSaldoException`, `SaldoInsuficienteException`, `StockAgotadoException`, `MaquinaNoDisponibleException`; `BaseDatos.PersistenciaException` | `AstroCadeFacade` lanza las excepciones y las pantallas las capturan con `try/catch` y muestran el mensaje al usuario sin cerrar la aplicación. |

### Persistencia

| Concepto | Dónde está | Cómo se usa |
|---|---|---|
| Base de datos MySQL | `database/astrocade_db.sql` (tablas `usuarios`, `tarjetas`, `maquinas`, `premios`, `premios_redimidos`, `eventos`, `partidas`) | Guarda clientes, tarjetas, máquinas, stock de premios, eventos y el historial de partidas; los datos se conservan al cerrar la aplicación. |
| Acceso a datos | `BaseDatos`: `ClienteDAO`, `MaquinaDAO`, `PremioDAO`, `UsuarioDAO`, `EventoDAO` | Cada DAO ejecuta con `PreparedStatement` las consultas de su tabla; las operaciones que tocan varias tablas van en una transacción. |

### Interfaz gráfica

| Concepto | Dónde está | Cómo se usa |
|---|---|---|
| Swing | `AstroCadeVisual`: `IngresarVista`, `ClienteVista`, `AdminVista`, `MaquinaVista`, `TiendaVista` | Login por rol, panel del cliente, panel del administrador, juego en máquinas y tienda de premios; todas usan `AstroCadeFacade`. |

### Principios SOLID

| Principio | Dónde está | Cómo se usa |
|---|---|---|
| S – Responsabilidad única | `*DAO` (persistencia), `AstroCadeFacade` (casos de uso), `AstroCadeVisual` (presentación), `Tarjeta` y `Maquina` (reglas del dominio) | Cada clase tiene una sola razón para cambiar. |
| O – Abierto/cerrado | `EstrategiaDescuento`, `EstadoMaquina` | Agregar un nuevo descuento o estado se hace con una clase nueva, sin modificar las existentes. |
| L – Sustitución de Liskov | `Tarjeta`, `Maquina`, `Premios`, `Usuario` y sus hijas | Cualquier subclase se usa donde se espera el padre (por ejemplo `AstroCadeFacade.jugar(Maquina, Tarjeta)`). |
| I – Segregación de interfaces | `EstrategiaDescuento` (1 método), `EstadoMaquina` (2 métodos), un `*Repositorio` por tema | Interfaces pequeñas: cada clase implementa solo lo que necesita. |
| D – Inversión de dependencias | `AstroCadeFacade` → `*Repositorio` ← `*DAO`; `Tarjeta` → `EstrategiaDescuento`; `Maquina` → `EstadoMaquina` | La fachada depende de interfaces (inyectadas por constructor), no de MySQL; los DAO son las implementaciones. |

### Patrones de diseño

| Tipo | Patrón | Dónde está | Cómo se usa |
|---|---|---|---|
| Creacional | Singleton | `BaseDatos.Conexion` | Toda la aplicación comparte una única conexión a MySQL. |
| Creacional | Factory | `TarjetaFactory`, `MaquinaFactory` | `ClienteDAO` y `MaquinaDAO` crean cada tarjeta y máquina según el tipo guardado; `AstroCadeFacade` y `AdminVista` crean la tarjeta al registrar un cliente. |
| Estructural | Facade | `LogicaAstroCade.AstroCadeFacade` | Las pantallas usan un único punto de entrada (jugar, recargar, registrar, redimir, cambiar evento) sin conocer DAO ni reglas internas. |
| Comportamiento | Strategy | `EstrategiaDescuento` y `DescuentoBasico/Gold/Diamond` | Cada tarjeta calcula su descuento con una estrategia intercambiable. |
| Comportamiento | State | `EstadoMaquina` y `EstadoActiva/Mantenimiento/FueraDeServicio` | El estado decide si la máquina puede jugar (`puedeJugar()`). |
| Comportamiento | Template Method | `Maquina.jugar()` (final) → `jugarPartida()` | Define los pasos comunes y cada máquina implementa su juego. |

*Universidad Libre – Facultad de Ingeniería*
