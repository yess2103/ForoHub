package com.example.AluraForohub.AluraForoHub.domain.respuesta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record DatosRegistroRespuesta(
        @NotBlank(message = "Mensaje es obligatorio")
        String mensaje,
        @NotNull(message = "Topico_id es obligatorio")
        Long topico_id,
        @NotNull(message = "Usuario_id es obligatorio")
        Long usuario_id
) {
}
