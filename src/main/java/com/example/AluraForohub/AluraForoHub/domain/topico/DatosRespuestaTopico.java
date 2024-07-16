package com.example.AluraForohub.AluraForoHub.domain.topico;

public record DatosRespuestaTopico(
        Long id,
        String titulo,
        String mensaje,
        String fecha_creacion,
        String estado,
        Long curso_id,
        Long usuario_id
) {
}
