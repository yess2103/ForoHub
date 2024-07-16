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
import com.example.AluraForohub.AluraForoHub.domain.topico.*;


@RestController
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearer-key")
public class TopicoController {

    @Autowired
    private TopicoService service;

    @PostMapping
    @Transactional
    public ResponseEntity<DatosRespuestaTopico> registrarTopico(@RequestBody @Valid DatosRegistroTopico datosRegistroTopico, UriComponentsBuilder uriComponentsBuilder){

        return  service.registrar(datosRegistroTopico, uriComponentsBuilder);
    }

    @GetMapping
    public ResponseEntity<Page> listadoTopicos(@PageableDefault(size = 10)Pageable paginacion){

        return service.listarTopicos(paginacion);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosListadoTopicoConRespuestas> listarDetalleTopico(@PathVariable Long id){

        return service.listarDetalleTopicos(id);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DatosRespuestaTopico> actualizaTopico(@RequestBody @Valid DatosActualizarTopico datosActualizarTopico, @PathVariable Long id, UriComponentsBuilder uriComponentsBuilder){

        return service.actualizarTopico(datosActualizarTopico, id, uriComponentsBuilder);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarTopico(@PathVariable Long id){

        return service.eliminarTopico(id);
    }

}


