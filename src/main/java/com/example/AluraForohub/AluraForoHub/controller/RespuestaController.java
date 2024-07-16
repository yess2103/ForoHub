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
import com.example.AluraForohub.AluraForoHub.domain.respuesta.*;


@RestController
@RequestMapping("/respuestas")
@SecurityRequirement(name = "bearer-key")
public class RespuestaController {

    @Autowired
    private RespuestaRepository respuestaRepository;

    @Autowired
    private RespuestaService service;

    @PostMapping
    @Transactional
    public ResponseEntity<DatosRetornoRespuesta> registrarRespuesta(@RequestBody @Valid DatosRegistroRespuesta datosRegistroRespuesta, UriComponentsBuilder uriComponentsBuilder){

        return  service.registrarRespuesta(datosRegistroRespuesta, uriComponentsBuilder);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DatosRetornoRespuesta> actualizaRespuesta(@RequestBody @Valid DatosActualizarRespuesta datosActualizarRespuesta,
                                                                    @PathVariable Long id, UriComponentsBuilder uriComponentsBuilder){

        return service.actualizarRespuesta(datosActualizarRespuesta, id, uriComponentsBuilder);
    }

    @GetMapping
    public ResponseEntity<Page> listadoRespuesta(@PageableDefault(size = 10) Pageable paginacion){

        return service.listarRespuestas(paginacion);
    }


    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarRespuesta(@PathVariable Long id){

        return service.eliminarRespuesta(id);
    }
}
