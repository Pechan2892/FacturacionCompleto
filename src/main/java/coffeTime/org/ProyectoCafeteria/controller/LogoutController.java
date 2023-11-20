package coffeTime.org.ProyectoCafeteria.controller;

import coffeTime.org.ProyectoCafeteria.service.implementation.UsuarioServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogoutController {

    @Autowired
    private UsuarioServiceImpl usuarioService;

    @GetMapping("/logoutUser")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        // Realiza el proceso de logout, por ejemplo, eliminando la sesión actual.
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate(); // Invalida la sesión existente.
        }

        // Redirige a la página de inicio de sesión.
        return "redirect:/logear";
    }
}
