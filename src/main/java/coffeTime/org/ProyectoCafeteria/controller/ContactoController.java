package coffeTime.org.ProyectoCafeteria.controller;
import coffeTime.org.ProyectoCafeteria.dao.entity.Contacto;
import coffeTime.org.ProyectoCafeteria.dao.entity.Factura;
import coffeTime.org.ProyectoCafeteria.dao.entity.Usuario;
import coffeTime.org.ProyectoCafeteria.service.implementation.ContactoServiceImpl;
import coffeTime.org.ProyectoCafeteria.service.implementation.FacturaServiceImpl;
import coffeTime.org.ProyectoCafeteria.service.implementation.UsuarioServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class ContactoController {

    @Autowired
    private UsuarioServiceImpl serviciosUsuario;

    @Autowired
    private FacturaServiceImpl facturaService;
    @Autowired
    private ContactoServiceImpl serviciosContactos;

    @GetMapping("/contactos")
    public String mostrarContactosPage(HttpServletRequest request, Model model, Pageable pageable) {
        Usuario usuario = obtenerUsuarioDesdeSesion(request);
        if (usuario == null) {
            return "redirect:/logear";
        }

        Page<Contacto> contactoPage = serviciosContactos.obtenerContactosUsuario(usuario, pageable);
        model.addAttribute("usuario", usuario);
        model.addAttribute("contactosPage", contactoPage);
        return "Contactos";
    }

    @GetMapping("/nuevoContacto")
    public String mostrarFormularioContacto(Model model, HttpServletRequest request) {
        Usuario usuario = obtenerUsuarioDesdeSesion(request);
        if (usuario == null) {
            return "redirect:/logear";
        }
        model.addAttribute("contacto", new Contacto());
        model.addAttribute("Titulo", "Registrar Contacto");
        return "formContacto";
    }

    @PostMapping("/nuevoContacto")
    public String guardarContacto(@Valid @ModelAttribute("contacto") Contacto contacto,
                                  BindingResult bindingResult,
                                  HttpServletRequest request, Model model) {
        Usuario usuario = obtenerUsuarioDesdeSesion(request);
        if (usuario == null) {
            return "redirect:/logear";
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("contacto", contacto);
            return "formContacto";
        }
        contacto.asignarFechaRegistro();

        contacto.setUsuario(usuario);
        serviciosContactos.guardarContacto(contacto);

        return "redirect:/contactos";
    }

    // Método auxiliar para obtener el usuario de la sesión
    private Usuario obtenerUsuarioDesdeSesion(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return (Usuario) session.getAttribute("usuario");
    }

    @GetMapping("/editarContacto/{id}")
    public String mostrarFormularioEditarContacto(@PathVariable Long id, Model model, HttpServletRequest request) {
        Usuario usuario = obtenerUsuarioDesdeSesion(request);
        if (usuario == null) {
            return "redirect:/logear";
        }

        Contacto contacto = serviciosContactos.obtenerContactoPorId(id);

        model.addAttribute("contacto", contacto);
        model.addAttribute("Titulo", "Editar Contacto");
        return "formContacto";
    }

    // En el controlador
    @PostMapping("/editarContacto/{id}")
    public String actualizarContacto(@PathVariable Long id, @Valid @ModelAttribute("contacto") Contacto contacto,
                                     BindingResult bindingResult, HttpServletRequest request, Model model) {
        Usuario usuario = obtenerUsuarioDesdeSesion(request);
        if (usuario == null) {
            return "redirect:/logear";
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("contacto", contacto);
            model.addAttribute("Titulo", "Editar Contacto");
            return "formContacto";
        }

        Contacto existingContacto = serviciosContactos.obtenerContactoPorId(id);
        if (existingContacto == null || !existingContacto.getUsuario().equals(usuario)) {
            // Manejar el caso en que el contacto no pertenezca al usuario
            return "redirect:/contactos";
        }

        existingContacto.setNombre(contacto.getNombre());
        existingContacto.setCelular(contacto.getCelular());
        existingContacto.asignarFechaRegistro();
        serviciosContactos.actualizarContacto(existingContacto);

        return "redirect:/contactos";
    }


    @PostMapping("/borrarContacto/{id}")
    public String borrarContacto(@PathVariable Long id, Model model ) {
        Contacto contacto = serviciosContactos.obtenerContactoPorId(id);
        serviciosContactos.borrarContacto(contacto);
        return "redirect:/contactos";
    }

    @GetMapping("/verContacto/{id}")
    public String verDatosContacto(@PathVariable Long id,Model model,HttpServletRequest request){
        Usuario usuario = obtenerUsuarioDesdeSesion(request);
        if (usuario == null) {
            return "redirect:/home";
        }
        Contacto contacto=serviciosContactos.obtenerContactoPorId(id);
        model.addAttribute("contacto",contacto);
        return "verContacto";
    }

    @GetMapping("verContacto/verFactura/{id}")
    public String verFactura(@PathVariable Long id,Model model){
        Factura factura=facturaService.findFacturaById(id);
        if(factura==null){
            return "redirect:/contactos";
        }
        model.addAttribute(factura);
        return "verFactura";
    }

}

