package com.example.AluraForohub.AluraForoHub.domain.usuario.validaciones;

import com.example.AluraForohub.AluraForoHub.domain.topico.DatosRegistroTopico;
import com.example.AluraForohub.AluraForoHub.domain.usuario.DatosRegistroUsuario;
import com.example.AluraForohub.AluraForoHub.domain.usuario.DatosRespuestaUsuario;

public interface ValidadorDeUsuarios {

    public void validar(DatosRegistroUsuario datosRegistroUsuario);
}
