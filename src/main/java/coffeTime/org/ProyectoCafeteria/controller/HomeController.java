package coffeTime.org.ProyectoCafeteria.controller;

import coffeTime.org.ProyectoCafeteria.dao.entity.Usuario;
import coffeTime.org.ProyectoCafeteria.service.Interface.UsuarioService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    UsuarioService servicioUsuario;

    @GetMapping("/home")
    public String mostrarHomePage(HttpServletRequest request, Model model){
        HttpSession session=request.getSession();
        Usuario usuario=(Usuario)session.getAttribute("usuario");
        if (usuario == null) {
            return "redirect:/logear";
        }
        model.addAttribute("User",usuario);
        return "home";
    }


}
