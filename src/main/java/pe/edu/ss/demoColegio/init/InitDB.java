package pe.edu.ss.demoColegio.init;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.logging.java.SimpleFormatter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import pe.edu.ss.demoColegio.model.entity.Alumno;
import pe.edu.ss.demoColegio.model.entity.Usuario;
import pe.edu.ss.demoColegio.model.repository.AuthorityRepository;
import pe.edu.ss.demoColegio.model.repository.UsuarioRepository;



@Service
public class InitDB implements CommandLineRunner{

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private AuthorityRepository authorityRepository;
	
	@Autowired
    private PasswordEncoder passwordEncoder;

	@Override
	public void run(String... args) throws Exception {
		
		this.usuarioRepository.deleteAll();
		this.authorityRepository.deleteAll();
		
		/*Usuario juan = new Usuario();
		juan.setUsername("juan");
		juan.setPassword(passwordEncoder.encode("juan"));
		juan.setApellidos("Flores");
		juan.setNombres("Juan");
		juan.setCargo("cargo");
		juan.setEnable(true);
		*/
		Usuario admin = new Usuario();
		admin.setUsername("admin");
		admin.setPassword(passwordEncoder.encode("123admin123"));
		admin.setEnable(true);
		
		Usuario beto = new Usuario();
		beto.setUsername("beto");
		beto.setPassword(passwordEncoder.encode("beto"));
		beto.setEnable(true);
		
		Usuario starchild = new Usuario();
		starchild.setUsername("starchild");
		starchild.setPassword(passwordEncoder.encode("elpanzas"));
		starchild.setEnable(true);
		
		/*
        
        juan.addAuthority("ROLE_USER");
        juan.addAuthority("ACCESS_MEDICO_READ");
        */
        admin.addAuthority("ROLE_ADMIN");
        admin.addAuthority("ACCESS_REST1");
        admin.addAuthority("ACCESS_REST2");
        
        beto.addAuthority("ROLE_ADMIN");
        beto.addAuthority("ACCESS_REST1");
        beto.addAuthority("ACCESS_REST2");
        
        starchild.addAuthority("ROLE_ADMIN");
        starchild.addAuthority("ACCESS_REST1");
        starchild.addAuthority("ACCESS_REST2");
       
        //estudiante.addAuthority("ACCES_REST1");
        ///estudiante.addAuthority("");
        /*
        manager.addAuthority("ROLE_MANAGER");
        manager.addAuthority("ACCESS_REST1");
        */
        List<Usuario> usuarios = Arrays.asList(admin, beto, starchild);        
        this.usuarioRepository.saveAll(usuarios);
        
        SimpleDateFormat formatter= new SimpleDateFormat("dd-MM-yyyy");
      
        Alumno alumno1=new Alumno();
        alumno1.setCodigo("ES001");
        alumno1.setNombre("Diego");
        alumno1.setApePat("Verdeguer");
        alumno1.setApeMat("Valderrama");
        alumno1.setDNI("72870063");
        alumno1.setEdad(12);
        alumno1.setDireccion("dverdeguerv@gmail.com");
        alumno1.setFechaNac(formatter.parse("02-05-2019"));
        alumno1.setFechaIngreso(formatter.parse("02-05-2019"));
        alumno1.setGenero('M');
        
	}
}
