package com.example.AluraForohub.AluraForoHub.domain.usuario;

public enum Perfil {

    ADMINISTRADOR("Administrador"),
    MODERADOR("Moderador"),
    INSTRUCTOR("Instructor"),
    AYUDANTE("Ayudante"),
    ESTUDIANTE("Estudiante");

    private String perfil;

    Perfil(String perfil){
        this.perfil = perfil;
    }

    public static Perfil fromString(String text){
        for(Perfil perfil: Perfil.values()){
            if (perfil.perfil.equalsIgnoreCase(text)){
                return perfil;
            }
        }
        throw new IllegalArgumentException("Ningún perfil fue encontrado: " + text);
    }
}
