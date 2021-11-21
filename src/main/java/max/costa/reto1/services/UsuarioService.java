package max.costa.reto1.services;

import java.text.ParseException;
import java.util.List;

import max.costa.reto1.models.Usuario;

public interface UsuarioService {

	void guardar(Usuario usuario) throws ParseException;

	void eliminar(Long idUsuario);

	List<Usuario> buscarTodos();

	Usuario buscarPorUsername(String username);
}
