package com.example.AluraForohub.AluraForoHub.domain.topico;

public enum Estado {

    SOLUCIONADO("Solucionado"),
    SIN_SOLUCION("Sin_solucion");

    private String estado;

    Estado(String estado){
        this.estado = estado;
    }

    public static Estado fromString(String text){
        for(Estado estado : Estado.values()){
            if(estado.estado.equalsIgnoreCase(text)){
                return estado;
            }
        }
        throw new IllegalArgumentException("Ningún estado encontrado: " + text);
    }

}
