package com.example.AluraForohub.AluraForoHub.infra.errores;

public class ValidacionDeIntegridad extends RuntimeException{

    public ValidacionDeIntegridad(String s){
        super(s);
    }

}
