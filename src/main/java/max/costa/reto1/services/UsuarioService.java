package max.costa.reto1.services;

import java.text.ParseException;
import java.util.List;

import javax.mail.MessagingException;

import max.costa.reto1.models.Usuario;

public interface UsuarioService {

	void guardar(Usuario usuario) throws ParseException, MessagingException;

	void eliminar(Long idUsuario);

	List<Usuario> buscarTodos();

	Usuario buscarPorUsername(String username);
}
