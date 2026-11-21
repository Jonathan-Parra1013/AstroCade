package com.github.jonathanparra1013.astrocade.LogicaAstroCade;

import com.github.jonathanparra1013.astrocade.Premios.PremioPorTickets;
import com.github.jonathanparra1013.astrocade.Maquinas.Maquina;
import com.github.jonathanparra1013.astrocade.Tarjetas.TarjetaFactory;
import com.github.jonathanparra1013.astrocade.Tarjetas.Tarjeta;
import com.github.jonathanparra1013.astrocade.Usuarios.GestorClientes;
import com.github.jonathanparra1013.astrocade.Usuarios.Administrador;
import com.github.jonathanparra1013.astrocade.Usuarios.Cliente;
import com.github.jonathanparra1013.astrocade.BaseDatos.ClienteDAO;
import com.github.jonathanparra1013.astrocade.Repositorios.ClienteRepositorio;
import com.github.jonathanparra1013.astrocade.BaseDatos.EventoDAO;
import com.github.jonathanparra1013.astrocade.Repositorios.EventoRepositorio;
import com.github.jonathanparra1013.astrocade.BaseDatos.MaquinaDAO;
import com.github.jonathanparra1013.astrocade.Repositorios.MaquinaRepositorio;
import com.github.jonathanparra1013.astrocade.BaseDatos.PersistenciaException;
import com.github.jonathanparra1013.astrocade.BaseDatos.PremioDAO;
import com.github.jonathanparra1013.astrocade.Repositorios.PremioRepositorio;
import com.github.jonathanparra1013.astrocade.BaseDatos.UsuarioDAO;
import com.github.jonathanparra1013.astrocade.Repositorios.UsuarioRepositorio;
import com.github.jonathanparra1013.astrocade.ManejoErrores.AstroCadeException;
import com.github.jonathanparra1013.astrocade.ManejoErrores.DatoInvalidoException;
import com.github.jonathanparra1013.astrocade.ManejoErrores.LimiteSaldoException;
import com.github.jonathanparra1013.astrocade.ManejoErrores.MaquinaNoDisponibleException;
import com.github.jonathanparra1013.astrocade.ManejoErrores.SaldoInsuficienteException;
import com.github.jonathanparra1013.astrocade.ManejoErrores.StockAgotadoException;
import java.util.Date;
import java.util.List;


public class AstroCadeFacade {

    private final ClienteRepositorio clientes;
    private final MaquinaRepositorio maquinas;
    private final PremioRepositorio premios;
    private final UsuarioRepositorio usuarios;
    private final EventoRepositorio eventos;

    public AstroCadeFacade() {
        this(new ClienteDAO(), new MaquinaDAO(), new PremioDAO(), new UsuarioDAO(), new EventoDAO());
    }

    public AstroCadeFacade(ClienteRepositorio clientes, MaquinaRepositorio maquinas,
                           PremioRepositorio premios, UsuarioRepositorio usuarios,
                           EventoRepositorio eventos) {
        this.clientes = clientes;
        this.maquinas = maquinas;
        this.premios = premios;
        this.usuarios = usuarios;
        this.eventos = eventos;
    }



    public Administrador autenticarAdministrador(String password) throws AstroCadeException {
        if (password == null || password.isBlank()) {
            throw new DatoInvalidoException("Debe escribir la contraseña.");
        }
        return usuarios.autenticarAdministrador(password);
    }

    public List<GestorClientes.ClienteConTarjeta> cargarClientes() throws AstroCadeException {
        GestorClientes.cargar(clientes.listar());
        return GestorClientes.getClientes();
    }

    public List<Maquina> cargarMaquinas() throws AstroCadeException {
        return maquinas.listar();
    }

    public List<PremioPorTickets> listarPremiosTienda() throws AstroCadeException {
        return premios.listarTienda();
    }



    public Evento obtenerEventoActivo() throws AstroCadeException {
        return eventos.obtenerActivo();
    }

    public List<Evento> listarEventos() throws AstroCadeException {
        return eventos.listar();
    }

    public Evento cambiarEvento(Administrador administrador, String tipoEvento) throws AstroCadeException {
        if (administrador == null) {
            throw new DatoInvalidoException("Solo un administrador puede cambiar el evento.");
        }
        eventos.activar(tipoEvento);
        return eventos.obtenerActivo();
    }



    public void recargarTarjeta(Administrador administrador, Tarjeta tarjeta, int cantidad)
            throws AstroCadeException {
        if (administrador == null || tarjeta == null) {
            throw new DatoInvalidoException("Administrador y tarjeta son obligatorios.");
        }
        if (cantidad <= 0) {
            throw new DatoInvalidoException("La recarga debe ser un valor positivo.");
        }
        if (!tarjeta.puedeRecargar(cantidad)) {
            throw new LimiteSaldoException(tarjeta.getSaldoJugadas(), cantidad, tarjeta.getLimiteSaldo());
        }
        administrador.recargarTarjeta(tarjeta, cantidad);
        clientes.actualizarTarjeta(tarjeta);
    }

    public GestorClientes.ClienteConTarjeta registrarCliente(
            String id, String nombre, String correo,
            int saldoInicial, TarjetaFactory.TipoTarjeta tipoTarjeta) throws AstroCadeException {
        // La pantalla de registro no pide fecha de nacimiento: se usa una fecha por defecto.
        return registrarCliente(id, nombre, correo, saldoInicial, tipoTarjeta,
                java.sql.Date.valueOf("2000-01-01"));
    }

    public GestorClientes.ClienteConTarjeta registrarCliente(
            String id, String nombre, String correo,
            int saldoInicial, TarjetaFactory.TipoTarjeta tipoTarjeta,
            Date fechaNacimiento) throws AstroCadeException {
        if (id == null || id.isBlank() || nombre == null || nombre.isBlank()) {
            throw new DatoInvalidoException("El id y el nombre son obligatorios.");
        }
        if (id.trim().length() > 20) {
            throw new DatoInvalidoException("El id no puede tener más de 20 caracteres.");
        }
        if (saldoInicial < 0) {
            throw new DatoInvalidoException("El saldo inicial no puede ser negativo.");
        }
        Cliente cliente;
        Tarjeta tarjeta;
        try {
            cliente = new Cliente(id, nombre, correo, fechaNacimiento);
            tarjeta = TarjetaFactory.crear(tipoTarjeta, id);
        } catch (IllegalArgumentException e) {
            throw new DatoInvalidoException(e.getMessage());
        }
        if (!tarjeta.puedeRecargar(saldoInicial) && saldoInicial > 0) {
            throw new LimiteSaldoException(0, saldoInicial, tarjeta.getLimiteSaldo());
        }
        tarjeta.recargarSaldo(saldoInicial);
        clientes.insertar(cliente, tarjeta);                 // primero se guarda en la base de datos
        GestorClientes.agregarCliente(cliente, tarjeta);     // y luego en memoria
        return new GestorClientes.ClienteConTarjeta(cliente, tarjeta);
    }

    public void ponerMaquinaEnMantenimiento(Administrador administrador, Maquina maquina)
            throws AstroCadeException {
        if (administrador == null || maquina == null) {
            throw new DatoInvalidoException("Administrador y máquina son obligatorios.");
        }
        administrador.ponerMaquinaEnMantenimiento(maquina);
        maquinas.actualizarEstado(maquina);
    }

    public void cambiarEstadoMaquina(Administrador administrador, Maquina maquina, String estado)
            throws AstroCadeException {
        if (administrador == null || maquina == null) {
            throw new DatoInvalidoException("Administrador y máquina son obligatorios.");
        }
        try {
            administrador.cambiarEstadoMaquina(maquina, estado);
        } catch (IllegalArgumentException e) {
            throw new DatoInvalidoException(e.getMessage());
        }
        maquinas.actualizarEstado(maquina);
    }

    // ------------------------------------------------------------------ cliente

    public void jugar(Maquina maquina, Tarjeta tarjeta) throws AstroCadeException {
        if (maquina == null || tarjeta == null) {
            throw new DatoInvalidoException("Máquina y tarjeta son obligatorias.");
        }
        if (!maquina.estaDisponible()) {
            throw new MaquinaNoDisponibleException(maquina.getNombre(), maquina.getEstado());
        }
        maquina.setFactorEvento(eventos.obtenerActivo().getFactorPrecio());
        double costo = maquina.calcularCostoPartida(tarjeta);
        if (tarjeta.getSaldoJugadas() < costo) {
            throw new SaldoInsuficienteException("jugadas", tarjeta.getSaldoJugadas(), costo);
        }
        int saldoAntes = tarjeta.getSaldoJugadas();
        int ticketsAntes = tarjeta.getSaldoTickets();
        maquina.jugar(tarjeta);   // polimorfismo: cada máquina juega a su manera
        maquinas.guardarPartida(maquina, tarjeta, saldoAntes, ticketsAntes);
    }

    public void redimirPremio(Cliente cliente, Tarjeta tarjeta, String nombrePremio)
            throws AstroCadeException {
        if (cliente == null || tarjeta == null || nombrePremio == null || nombrePremio.isBlank()) {
            throw new DatoInvalidoException("Cliente, tarjeta y premio son obligatorios.");
        }
        PremioPorTickets premio = null;
        for (PremioPorTickets p : premios.listarTienda()) {
            if (p.getNombre().equalsIgnoreCase(nombrePremio)) {
                premio = p;
                break;
            }
        }
        if (premio == null) {
            throw new DatoInvalidoException("El premio \"" + nombrePremio + "\" no existe en la tienda.");
        }
        if (!premio.hayStock()) {
            throw new StockAgotadoException(premio.getNombre());
        }
        int costo = premio.getCostoTickets();
        if (tarjeta.getSaldoTickets() < costo) {
            throw new SaldoInsuficienteException("tickets", tarjeta.getSaldoTickets(), costo);
        }
        tarjeta.descontarTickets(costo);
        premio.reducirStock();
        try {
            premios.redimir(cliente, tarjeta, premio);
        } catch (PersistenciaException e) {
            tarjeta.agregarTickets(costo);   // se deshace el cambio en memoria si la base falló
            throw e;
        }
        cliente.agregarPremio(premio.getNombre());
    }
}
