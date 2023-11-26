package coffeTime.org.ProyectoCafeteria.controller;

import coffeTime.org.ProyectoCafeteria.dao.Dto.UsuarioRegistroDto;
import coffeTime.org.ProyectoCafeteria.dao.entity.Usuario;
import coffeTime.org.ProyectoCafeteria.service.Interface.UsuarioService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/logear")
public class LoginUsuarioController {
    @Autowired
    private UsuarioService servicioUsuario;
    @ModelAttribute("usuario")
    public UsuarioRegistroDto retornarNuevoUsuarioRegistro(){
        return new UsuarioRegistroDto();
    }
    @GetMapping
    public String mostrarFormularioRegistro(){
        return "logear";
    }
    @PostMapping
    public String iniciarSesionHome(@ModelAttribute("usuario") UsuarioRegistroDto registroDto, Model model, HttpServletRequest request) {

        Usuario existeUsuario = servicioUsuario.BuscarPorEmail(registroDto.getEmail());
        if (existeUsuario == null) {
            model.addAttribute("mensajeError", "Este usuario no existe. ¿Estás registrado?");
            return "logear";
        } else {
            // Utiliza el BCryptPasswordEncoder para verificar la contraseña
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            if (passwordEncoder.matches(registroDto.getPassword(), existeUsuario.getPassword())) {
                HttpSession session = request.getSession();
                session.setAttribute("usuario", existeUsuario);
                return "redirect:/home";
            } else {
                model.addAttribute("mensajeErrorP", "La contraseña es inválida.");
                return "logear";
            }
        }
    }
}
