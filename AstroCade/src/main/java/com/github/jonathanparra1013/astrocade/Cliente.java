/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.github.jonathanparra1013.astrocade;
import java.util.Calendar;
import java.util.Date;
/**
 *
 * @author jonap
 */


public class Cliente extends Usuario {
    private Date fechaNacimiento;

    public Cliente(String id, String nombre, String email, Date fechaNacimiento) {
        super(id, nombre, email);
        this.fechaNacimiento = fechaNacimiento;
    }

    public boolean esSuCumpleanosHoy() {
        if (fechaNacimiento == null) return false;
        Calendar calNac = Calendar.getInstance();
        calNac.setTime(fechaNacimiento);
        
        Calendar calHoy = Calendar.getInstance(); // Fecha actual del sistema
        
        return (calNac.get(Calendar.MONTH) == calHoy.get(Calendar.MONTH)) &&
               (calNac.get(Calendar.DAY_OF_MONTH) == calHoy.get(Calendar.DAY_OF_MONTH));
    }   
    
    public void verPuntajes(Tarjeta tarjeta) {
        System.out.println("Saldo de jugadas: " + tarjeta.getSaldoJugadas() + " Tickets: " + tarjeta.getSaldoTickets());
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }
    
    public Object getNombre() {
        return super.nombre;
    }

    public Object getEmail() {
       return super.email;
    }
}
