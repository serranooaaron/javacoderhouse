package com.serranoaaron.app.config;

import com.serranoaaron.app.model.Administrador;
import com.serranoaaron.app.model.Turno;
import com.serranoaaron.app.model.Usuario;
import com.serranoaaron.app.repository.AdminRepository;
import com.serranoaaron.app.repository.TurnoRepository;
import com.serranoaaron.app.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;


// Clase para inicializar datos de ejemplo al arrancar la aplicación
// Harcodeo de usuarios, turnos y administradores
// Simulación de datos iniciales en la base de datos para pruebas o demostraciones.
@Component
public class DataInitializer implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final AdminRepository adminRepository;
    private final TurnoRepository turnoRepository;

    public DataInitializer(UsuarioRepository usuarioRepository, AdminRepository adminRepository, TurnoRepository turnoRepository) {
        this.usuarioRepository = usuarioRepository;
        this.adminRepository = adminRepository;
        this.turnoRepository = turnoRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        // Crear 3 usuarios
        Usuario u1 = new Usuario();
        u1.setNombre("Ana");
        u1.setEmail("ana@email.com");
        u1.setPassword("1111111");

        Usuario u2 = new Usuario();
        u2.setNombre("Luis");
        u2.setEmail("luis@email.com");
        u2.setPassword("2222222");

        Usuario u3 = new Usuario();
        u3.setNombre("María");
        u3.setEmail("maria@email.com");
        u3.setPassword("3333333");

        usuarioRepository.saveAll(Arrays.asList(u1, u2, u3));

       
        Turno t1 = new Turno();
        t1.setNombre("Peluquería");
        t1.setDescripcion("Corte y peinados");
        t1.setUsuario(u1);

        Turno t2 = new Turno();
        t2.setNombre("Estudio de Uñas");
        t2.setDescripcion("Manicura y pedicura");
        t2.setUsuario(u2);

        turnoRepository.saveAll(Arrays.asList(t1, t2));

        
        Administrador admin = new Administrador();
        admin.setNombre("AdminPrincipal");
        admin.setEmail("admin@example.com");
        admin.setDni(12345678);
        admin.setUsuario(u3); // relaciona el admin con María
        admin.setTurno(t1); // relaciona admin con turno Peluquería

        adminRepository.save(admin);

    }
}
