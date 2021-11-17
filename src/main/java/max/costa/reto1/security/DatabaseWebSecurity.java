// package max.costa.reto1.security;

// import javax.sql.DataSource;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;

// @Configuration
// @EnableWebSecurity
// public class DatabaseWebSecurity extends WebSecurityConfigurerAdapter {
//     @Autowired
//     private DataSource dataSource;

//     @Override
//     protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//         auth.jdbcAuthentication().dataSource(dataSource)
//                 .usersByUsernameQuery("select username, password, estatus from usuario where username=?")
//                 .authoritiesByUsernameQuery("select u.username, p.nombre from usuario_perfil up "
//                         + "inner join usuario u on u.id_usuario = up.id_usuario "
//                         + "inner join perfil p on p.id_perfil = up.id_perfil " + "where u.username = ?");
//     }

//     @Override
//     protected void configure(HttpSecurity http) throws Exception {
//         http.authorizeRequests()
//                 // Los recursos estáticos no requieren autenticación
//                 .antMatchers("/bootstrap/**", "/images/**", "/tinymce/**", "/logos/**").permitAll()
//                 // Las vistas públicas no requieren autenticación
//                 .antMatchers("/", "/signup", "/search", "/bcrypt/**", "/vacantes/view/**").permitAll()

//                 // Asignar permisos a URLs por ROLES
//                 .antMatchers("/vacantes/**").hasAnyAuthority("SUPERVISOR", "ADMINISTRADOR")
//                 .antMatchers("/categorias/**").hasAnyAuthority("SUPERVISOR", "ADMINISTRADOR")
//                 .antMatchers("/usuarios/**").hasAnyAuthority("ADMINISTRADOR")

//                 // Todas las demás URLs de la Aplicación requieren autenticación
//                 .anyRequest().authenticated()
//                 // El formulario de Login no requiere autenticacion
//                 .and().formLogin().loginPage("/login").permitAll();
//     }

//     @Bean
//     public PasswordEncoder passwordEncoder() {
//         return new BCryptPasswordEncoder();
//     }

// }
