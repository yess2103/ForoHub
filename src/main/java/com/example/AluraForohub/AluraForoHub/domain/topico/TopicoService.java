package com.example.AluraForohub.AluraForoHub.domain.topico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;
import com.example.AluraForohub.AluraForoHub.domain.curso.Curso;
import com.example.AluraForohub.AluraForoHub.domain.curso.CursoRepository;
import com.example.AluraForohub.AluraForoHub.domain.topico.validaciones.ValidadorDeTopicos;
import tech.challenge.foroHub.domain.usuario.Usuario;
import com.example.AluraForohub.AluraForoHub.domain.usuario.UsuarioRepository;
import com.example.AluraForohub.AluraForoHub.infra.errores.ValidacionDeIntegridad;

import java.net.URI;
import java.util.List;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    List<ValidadorDeTopicos> validadores;

    public ResponseEntity<DatosRespuestaTopico> registrar(DatosRegistroTopico datosRegistroTopico, UriComponentsBuilder uriComponentsBuilder){

        if (cursoRepository.findByNombreContainsIgnoreCase(datosRegistroTopico.nombreCurso()).isEmpty()){
            throw new ValidacionDeIntegridad("El curso no fue encontrado");
        }

        if (usuarioRepository.findById(datosRegistroTopico.usuario_id()).isEmpty()){
            throw new ValidacionDeIntegridad("El usuario no fue encontrado");
        }

        validadores.forEach(v -> v.validar(datosRegistroTopico));

        var topico = new Topico(datosRegistroTopico);
        topico.setCurso(cursoRepository.findByNombreContainsIgnoreCase(datosRegistroTopico.nombreCurso()).get());
        topico.setAutor(usuarioRepository.findById(datosRegistroTopico.usuario_id()).get());
        Topico topicoRet = topicoRepository.save(topico);

        DatosRespuestaTopico datosRespuestaTopico = new DatosRespuestaTopico(topicoRet.getId(), topicoRet.getTitulo(),
                topicoRet.getMensaje(), topicoRet.getFecha_creacion().toString(), topicoRet.getEstado().toString(),
                topicoRet.getCurso().getId(), topicoRet.getAutor().getId());

        URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topicoRet.getId()).toUri();

        return ResponseEntity.created(url).body(datosRespuestaTopico);
    }

    public ResponseEntity<Page> listarTopicos(Pageable paginacion){

        return ResponseEntity.ok(topicoRepository.listarTopicos(paginacion)
                .map(DatosListadoTopicoConRespuestas::new));
    }

    public ResponseEntity<DatosListadoTopicoConRespuestas> listarDetalleTopicos(Long id){

        if (topicoRepository.findById(id).isEmpty()){
            throw new ValidacionDeIntegridad("El tópico no fue encontrado. Verifique el id.");
        }

        Topico topico = topicoRepository.getReferenceById(id);
        var datosTopico = new DatosListadoTopicoConRespuestas(topico);

        return ResponseEntity.ok(datosTopico);
    }


    public ResponseEntity<DatosRespuestaTopico> actualizarTopico(DatosActualizarTopico datosActualizarTopico,
                                                                 Long id, UriComponentsBuilder uriComponentsBuilder) {

        Curso curso = null;
        Usuario usuario = null;

        if (topicoRepository.findById(id).isEmpty()){
            throw new ValidacionDeIntegridad("El tópico no fue encontrado. Verifique el id.");
        }

        if (datosActualizarTopico.nombreCurso() != null) {
            if (cursoRepository.findByNombreContainsIgnoreCase(datosActualizarTopico.nombreCurso()).isEmpty()) {
                throw new ValidacionDeIntegridad("El curso no fue encontrado");
            }
            curso = cursoRepository.findByNombreContainsIgnoreCase(datosActualizarTopico.nombreCurso()).get();
        }

        if (datosActualizarTopico.usuario_id() != null) {
            if (usuarioRepository.findById(datosActualizarTopico.usuario_id()).isEmpty()) {
                throw new ValidacionDeIntegridad("El usuario no fue encontrado");
            }
            usuario = usuarioRepository.findById(datosActualizarTopico.usuario_id()).get();
        }

        Topico topico = topicoRepository.getReferenceById(id);

        DatosRegistroTopico datosRegistroTopico = new DatosRegistroTopico(datosActualizarTopico.titulo(),
                datosActualizarTopico.mensaje(), datosActualizarTopico.nombreCurso(),
                datosActualizarTopico.usuario_id());

        validadores.forEach(v -> v.validar(datosRegistroTopico));

        topico.actualizarDatos(datosActualizarTopico, curso, usuario);

        DatosRespuestaTopico datosRespuestaTopico = new DatosRespuestaTopico(topico.getId(), topico.getTitulo(),
                topico.getMensaje(), topico.getFecha_creacion().toString(), topico.getEstado().toString(),
                topico.getCurso().getId(), topico.getAutor().getId());

        URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();

        return ResponseEntity.created(url).body(datosRespuestaTopico);

    }


    public ResponseEntity eliminarTopico(Long id) {

        if (topicoRepository.findById(id).isEmpty()){
            throw new ValidacionDeIntegridad("El tópico no fue encontrado. Verifique el id.");
        }

        topicoRepository.borrarTopico(id);

        return ResponseEntity.noContent().build();
    }
}
