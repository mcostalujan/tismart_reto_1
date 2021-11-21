package max.costa.reto1.services.impl;

import java.text.ParseException;
import java.util.List;

import javax.mail.MessagingException;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import max.costa.reto1.dao.UsuarioRepository;
import max.costa.reto1.models.Usuario;
import max.costa.reto1.services.EmailService;
import max.costa.reto1.services.PerfilService;
import max.costa.reto1.services.UsuarioService;
import max.costa.reto1.utility.Utility;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	private UsuarioRepository usuariosRepository;
	private PerfilService perfilService;
	private PasswordEncoder passwordEncoder;
	private EmailService emailService;
	private Utility utility;

	public UsuarioServiceImpl(UsuarioRepository usuariosRepository, PerfilService perfilService,
			PasswordEncoder passwordEncoder, EmailService emailService, Utility utility) {
		this.usuariosRepository = usuariosRepository;
		this.perfilService = perfilService;
		this.passwordEncoder = passwordEncoder;
		this.emailService = emailService;
		this.utility = utility;
	}

	public void guardar(Usuario usuario) throws ParseException, MessagingException {
		// String password = generatePassword();
		String password =  usuario.getPassword();
		String pwdEncriptado = this.passwordEncoder.encode(password);
		usuario.setPassword(pwdEncriptado);
		usuario.setEstatus(1);
		usuario.setFechaRegistro(this.utility.obtenerFechaActual());
		usuario.agregar(this.perfilService.buscarPorNombre("USUARIO"));
		// emailService.sendNewPasswordEmail(usuario.getNombre(),password,usuario.getEmail());
		this.usuariosRepository.save(usuario);
	}

	public void eliminar(Long idUsuario) {
		usuariosRepository.deleteById(idUsuario);
	}

	public List<Usuario> buscarTodos() {
		return usuariosRepository.findAll();
	}

	@Override
	public Usuario buscarPorUsername(String username) {
		return this.usuariosRepository.findByUsername(username);
	}

	private String generatePassword() {
        return RandomStringUtils.randomAlphanumeric(10);
    }

}
