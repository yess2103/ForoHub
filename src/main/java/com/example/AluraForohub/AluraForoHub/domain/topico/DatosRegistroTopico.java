package com.example.AluraForohub.AluraForoHub.domain.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroTopico (
        @NotBlank(message = "Título es obligatorio")
        String titulo,
        @NotBlank(message = "Mensaje es obligatorio")
        String mensaje,
        @NotBlank(message = "Nombre del curso es obligatorio")
        String nombreCurso,
        @NotNull(message = "Usuario_id es obligatorio")
        Long usuario_id
) {

        public DatosRegistroTopico(String titulo, String mensaje, String nombreCurso, Long usuario_id){
                this.titulo = titulo;
                this.mensaje = mensaje;
                this.nombreCurso = nombreCurso;
                this.usuario_id = usuario_id;
        }
}
