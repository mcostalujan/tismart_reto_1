package max.costa.reto1.controllers;

import java.text.ParseException;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import max.costa.reto1.models.Usuario;
import max.costa.reto1.services.UsuarioService;

@Controller
@RequestMapping(value = {"","/"})
public class HomeController {

	private UsuarioService usuarioService;

	@Autowired
	public HomeController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	@GetMapping("/signup")
	public String registrarse(Usuario usuario) {
		return "formRegistro";
	}

	@PostMapping("/signup")
	public String guardarRegistro(Usuario usuario, RedirectAttributes attributes) throws ParseException, MessagingException {
		usuarioService.guardar(usuario);
		attributes.addFlashAttribute("msgSuccess", "¡Usuario registrado correctamente! La contraseña se envió a: " + usuario.getEmail());
		return "redirect:/login";
	}

	@GetMapping(value = {"","/login"})
	public String mostrarLogin() {
		return "formLogin";
	}

	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
		logoutHandler.logout(request, null, null);
		return "redirect:/login";
	}

}
