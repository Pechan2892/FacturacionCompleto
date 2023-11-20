package coffeTime.org.ProyectoCafeteria.controller;

import coffeTime.org.ProyectoCafeteria.dao.Dto.UsuarioRegistroDto;
import coffeTime.org.ProyectoCafeteria.dao.entity.Usuario;
import coffeTime.org.ProyectoCafeteria.service.Interface.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registro")
public class RegistroUsuarioController {
    @Autowired
    private UsuarioService servicioUsuario;

    @ModelAttribute("usuario")
    public UsuarioRegistroDto retornarNuevoUsuarioRegistro(){
        return new UsuarioRegistroDto();
    }
    @GetMapping
    public String mostrarFormularioRegistro(){
        return "registro";
    }

    @PostMapping
    public String RegistrarNuevoUsuario(@Validated @ModelAttribute ("usuario") UsuarioRegistroDto registroDto, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "registro";
        }
        Usuario usuarioExistente=servicioUsuario.BuscarPorEmail(registroDto.getEmail());
        if(usuarioExistente!=null){
            model.addAttribute("mensajeError", "El correo electrónico ya está registrado.");
            return "registro";
        }

        servicioUsuario.guardarUsuario(registroDto);
        return "redirect:/logear?exito";
    }
}
