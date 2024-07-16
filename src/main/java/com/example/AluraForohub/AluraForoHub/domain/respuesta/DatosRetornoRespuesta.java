package com.example.AluraForohub.AluraForoHub.domain.respuesta;

import com.example.AluraForohub.AluraForoHub.domain.topico.DatosListadoTopico;

import java.time.LocalDateTime;

public record DatosRetornoRespuesta(
        Long id,
        String mensaje,
        LocalDateTime fecha_creacion,
        DatosListadoTopico topico,
        String autor,
        Boolean solucion
) {
}
