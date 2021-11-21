package max.costa.reto1.services.impl;

import java.text.ParseException;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import max.costa.reto1.dao.UsuarioRepository;
import max.costa.reto1.models.Usuario;
import max.costa.reto1.services.PerfilService;
import max.costa.reto1.services.UsuarioService;
import max.costa.reto1.utility.Utility;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	private UsuarioRepository usuariosRepository;
	private PerfilService perfilService;
	private PasswordEncoder passwordEncoder;
	private Utility utility;

	public UsuarioServiceImpl(UsuarioRepository usuariosRepository, PerfilService perfilService,
			PasswordEncoder passwordEncoder, Utility utility) {
		this.usuariosRepository = usuariosRepository;
		this.perfilService = perfilService;
		this.passwordEncoder = passwordEncoder;
		this.utility = utility;
	}

	public void guardar(Usuario usuario) throws ParseException {
		String pwdEncriptado = this.passwordEncoder.encode(usuario.getPassword());
		usuario.setPassword(pwdEncriptado);
		usuario.setEstatus(1);
		usuario.setFechaRegistro(this.utility.obtenerFechaActual());
		usuario.agregar(this.perfilService.buscarPorId(Long.valueOf(2)));
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

}
