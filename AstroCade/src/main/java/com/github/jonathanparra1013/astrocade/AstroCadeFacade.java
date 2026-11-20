package com.github.jonathanparra1013.astrocade;

public class AstroCadeFacade {
    public void recargarTarjeta(Administrador administrador,
                                Tarjeta tarjeta,
                                int cantidad) {
        administrador.recargarTarjeta(tarjeta, cantidad);
    }

    public GestorClientes.ClienteConTarjeta registrarCliente(
            String id, String nombre, String correo,
            int saldoInicial, TarjetaFactory.TipoTarjeta tipoTarjeta) {
        Cliente cliente = new Cliente(
                id, nombre, correo, new java.util.Date());
        Tarjeta tarjeta = TarjetaFactory.crear(tipoTarjeta, id);
        tarjeta.recargarSaldo(saldoInicial);
        GestorClientes.agregarCliente(cliente, tarjeta);
        return new GestorClientes.ClienteConTarjeta(cliente, tarjeta);
    }

    public boolean redimirPremio(Cliente cliente, Tarjeta tarjeta,
                                 String nombrePremio, int costoTickets) {
        if (tarjeta == null || tarjeta.getSaldoTickets() < costoTickets) {
            return false;
        }

        tarjeta.setSaldoTickets(tarjeta.getSaldoTickets() - costoTickets);
        if (cliente != null) {
            cliente.agregarPremio(nombrePremio);
        }
        return true;
    }

    public void cambiarEstadoMaquina(Administrador administrador,
                                     Maquina maquina,
                                     EstadoMaquina estado) {
        if (administrador == null || maquina == null || estado == null) {
            return;
        }
        maquina.setEstado(estado);
    }

    public void ponerMaquinaEnMantenimiento(Administrador administrador,
                                            Maquina maquina) {
        if (administrador != null) {
            administrador.ponerMaquinaEnMantenimiento(maquina);
        }
    }

    public void jugar(Maquina maquina, Tarjeta tarjeta) {
        if (maquina != null) {
            maquina.jugar(tarjeta);
        }
    }
}
