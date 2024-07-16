package com.example.AluraForohub.AluraForoHub.domain.topico;


public record DatosActualizarTopico(
        String titulo,
        String mensaje,
        String nombreCurso,
        Long usuario_id,
        String estado
) {
}
