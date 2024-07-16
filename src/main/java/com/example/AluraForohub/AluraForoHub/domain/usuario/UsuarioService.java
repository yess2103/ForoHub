package com.example.AluraForohub.AluraForoHub.domain.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;
import com.example.AluraForohub.AluraForoHub.domain.usuario.validaciones.ValidadorDeUsuarios;
import com.example.AluraForohub.AluraForoHub.infra.errores.ValidacionDeIntegridad;

import java.net.URI;
import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    List<ValidadorDeUsuarios> validadores;

    public ResponseEntity<DatosRespuestaUsuario> registarUsuario(DatosRegistroUsuario datosRegistroUsuario,
                                                                 UriComponentsBuilder uriComponentsBuilder) {
        var usuario = new Usuario(datosRegistroUsuario);
        validadores.forEach(v -> v.validar(datosRegistroUsuario));
        Usuario usuarioConId = usuarioRepository.save(usuario);

        DatosRespuestaUsuario datosRespuestaUsuario = new DatosRespuestaUsuario(usuarioConId);
        URI url = uriComponentsBuilder.path("/usuarios/{id}").buildAndExpand(usuarioConId.getId()).toUri();

        return ResponseEntity.created(url).body(datosRespuestaUsuario);
    }

    public ResponseEntity<DatosRespuestaUsuario> actualizarUsuario(DatosActualizarUsuario datosActualizarUsuario, Long id, UriComponentsBuilder uriComponentsBuilder) {

        if (usuarioRepository.findById(id).isEmpty()){
            throw new ValidacionDeIntegridad("El usuario no fue encontrado. Verifique el id.");
        }

        Usuario usuario = usuarioRepository.getReferenceById(id);

        DatosRegistroUsuario datosRegistroUsuario = realizarCopiaActualizado(usuario, datosActualizarUsuario);

        validadores.forEach(v -> v.validar(datosRegistroUsuario));

        if (datosActualizarUsuario.nombre() != null){
            usuario.setNombre(datosActualizarUsuario.nombre());
        }
        if (datosActualizarUsuario.email() != null){
            usuario.setEmail(datosActualizarUsuario.email());
        }
        if (datosActualizarUsuario.clave() != null){
            usuario.setClave(datosActualizarUsuario.clave());
        }
        if (datosActualizarUsuario.perfil() != null){
            usuario.setPerfil(Perfil.fromString(datosActualizarUsuario.perfil()));
        }

        DatosRespuestaUsuario datosRespuestaUsuario = new DatosRespuestaUsuario(usuario);
        URI url = uriComponentsBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();

        return ResponseEntity.created(url).body(datosRespuestaUsuario);

    }

    public ResponseEntity<Page> listarUsuarios(Pageable paginacion) {

        return ResponseEntity.ok(usuarioRepository.listarUsuarios(paginacion)
                .map(DatosRespuestaUsuario::new));
    }

    public ResponseEntity eliminarUsuario(Long id) {

        if (usuarioRepository.findById(id).isEmpty()){
            throw new ValidacionDeIntegridad("El usuario no fue encontrado. Verifique el id.");
        }

        usuarioRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    private DatosRegistroUsuario realizarCopiaActualizado(Usuario usuario, DatosActualizarUsuario datosActualizarUsuario){
        String nombre = usuario.getNombre();
        String email = usuario.getEmail();
        String clave = usuario.getClave();
        String perfil = usuario.getPerfil().toString();

        if (datosActualizarUsuario.nombre() != null){
            nombre = datosActualizarUsuario.nombre();
        }
        if (datosActualizarUsuario.email() != null){
            email = datosActualizarUsuario.email();
        }
        if (datosActualizarUsuario.clave() != null){
            clave = datosActualizarUsuario.clave();
        }
        if (datosActualizarUsuario.perfil() != null){
            perfil = datosActualizarUsuario.perfil();
        }

        DatosRegistroUsuario datosRegistroUsuario = new DatosRegistroUsuario(nombre, email, clave, perfil);
        return datosRegistroUsuario;
    }

}
