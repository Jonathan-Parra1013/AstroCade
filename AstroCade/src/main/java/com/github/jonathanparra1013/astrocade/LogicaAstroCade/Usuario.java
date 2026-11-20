/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.github.jonathanparra1013.astrocade;
import java.util.Date;
import java.util.List;
/**
 *
 * @author jonap
 */


public class Usuario {
    protected String id;
    protected String nombre;
    protected String email;

    public Usuario(String id, String nombre, String email) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
    }

    public boolean login() {
        System.out.println(nombre + " ha iniciado sesion.");
        return true;
    }
}

