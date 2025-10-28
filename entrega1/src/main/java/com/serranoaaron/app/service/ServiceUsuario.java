package com.serranoaaron.app.service;



import com.serranoaaron.app.model.Usuario;
import com.serranoaaron.app.repository.UsuarioRepository;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ServiceUsuario {
    private final UsuarioRepository usuarioRepository;

    public ServiceUsuario(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    /*
      Constructor injection (reemplaza el uso de @Autowired en el campo):
        Hace la dependencia explícita y permite declarar el repo como final.
        Mejora la testabilidad y evita NullPointerExceptions al inicializar el servicio.
     */
    @Transactional(readOnly = true)
    public Usuario ObtenerUsuario(Long id){
        return usuarioRepository.findById(id).orElse(null);
    }
    @Transactional(readOnly = true)
    public List<Usuario> ObtenerUsuarios(){
        return usuarioRepository.findAll();
    }
    /*
      @Transactional: asegura que la operación de escritura se ejecute dentro de una transacción.
      Si algo falla (excepción no controlada), la transacción se revertirá y no habrá cambios parciales en la BD.
     */
    @Transactional
    public void CrearUsuario(Usuario usuario){
        usuarioRepository.save(usuario);
    }
    public void ActualizarUsuario(Usuario usuarioActualizado, Long id){
        if (usuarioRepository.existsById(id)){
            usuarioActualizado.setId(id);
            usuarioRepository.save(usuarioActualizado);
        }
    }
    @Transactional
    public void EliminarUsuario(Usuario usuario){
        usuarioRepository.delete(usuario);

    }


}
