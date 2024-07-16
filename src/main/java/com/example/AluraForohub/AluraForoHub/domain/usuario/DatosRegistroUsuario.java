package com.example.AluraForohub.AluraForoHub.domain.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroUsuario(
        @NotBlank(message = "Nombre es obligatorio")
        String nombre,
        @Email
        @NotNull(message = "Email es obligatorio")
        String email,
        @NotBlank(message = "Clave es obligatorio")
        String clave,
        @NotBlank(message = "Perfil es obligatorio")
        String perfil
) {

        public DatosRegistroUsuario(String nombre, String email, String clave, String perfil) {
                this.nombre = nombre;
                this.email = email;
                this.clave = clave;
                this.perfil = perfil;
        }
}
