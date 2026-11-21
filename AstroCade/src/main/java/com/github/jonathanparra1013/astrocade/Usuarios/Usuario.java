package com.github.jonathanparra1013.astrocade.Usuarios;


public abstract class Usuario {

    private final String id;
    private final String nombre;
    private final String email;

    protected Usuario(String id, String nombre, String email) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("El id del usuario es obligatorio.");
        }
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre del usuario es obligatorio.");
        }
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("El correo del usuario no es válido.");
        }
        this.id = id.trim();
        this.nombre = nombre.trim();
        this.email = email.trim();
    }


    public abstract String getRol();

    public boolean login() {
        System.out.println(nombre + " ha iniciado sesion.");
        return true;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }
}
