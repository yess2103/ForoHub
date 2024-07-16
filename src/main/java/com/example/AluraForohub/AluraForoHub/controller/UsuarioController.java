package com.example.AluraForohub.AluraForoHub.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import com.example.AluraForohub.AluraForoHub.domain.usuario.*;

@RestController
@RequestMapping("/usuarios")
@SecurityRequirement(name = "bearer-key")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @PostMapping
    @Transactional
    public ResponseEntity<DatosRespuestaUsuario> registrarUsuario(@RequestBody @Valid DatosRegistroUsuario datosRegistroUsuario, UriComponentsBuilder uriComponentsBuilder){
        return service.registarUsuario(datosRegistroUsuario, uriComponentsBuilder);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DatosRespuestaUsuario> actualizarUsuario(@RequestBody DatosActualizarUsuario datosActualizarUsuario, @PathVariable Long id, UriComponentsBuilder uriComponentsBuilder){
        return service.actualizarUsuario(datosActualizarUsuario, id, uriComponentsBuilder);
    }

    @GetMapping
    public ResponseEntity<Page> listadoUsuarios(@PageableDefault(size = 10) Pageable paginacion){

        return service.listarUsuarios(paginacion);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarUsuario(@PathVariable Long id){

        return service.eliminarUsuario(id);
    }

}
