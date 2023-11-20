package coffeTime.org.ProyectoCafeteria.controller;

import coffeTime.org.ProyectoCafeteria.dao.Dto.UsuarioRegistroDto;
import coffeTime.org.ProyectoCafeteria.dao.entity.Usuario;
import coffeTime.org.ProyectoCafeteria.service.Interface.UsuarioService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String iniciarSesionHome(@ModelAttribute("usuario") UsuarioRegistroDto registroDto, Model model, HttpServletRequest request){

        Usuario ExisteUsuario=servicioUsuario.BuscarPorEmail(registroDto.getEmail());
        if(ExisteUsuario==null){
            model.addAttribute("mensajeError", "Este usuario no existe estas registrado?");
            return "logear";
        } else if (!ExisteUsuario.getPassword().equals(registroDto.getPassword())) {
            model.addAttribute("mensajeErrorP","La contraseña es invalida !!");
            return "logear";
        }
        HttpSession session=request.getSession();
        session.setAttribute("usuario",ExisteUsuario);
        return "redirect:/home";
    }
}
