package coffeTime.org.ProyectoCafeteria.controller;

import coffeTime.org.ProyectoCafeteria.dao.entity.Productos;
import coffeTime.org.ProyectoCafeteria.dao.entity.Usuario;
import coffeTime.org.ProyectoCafeteria.service.Interface.UsuarioService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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

    @GetMapping("/imagen/{id}")
    public ResponseEntity<byte[]> mostrarImagen(@PathVariable Long id) {
        Usuario usuario = servicioUsuario.buscarPorId(id);

        if (usuario == null || usuario.getImagen() == null) {
            // Puedes devolver una imagen de marcador de posición o un error aquí
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Obtener la imagen del producto como un array de bytes
        byte[] imagenBytes = usuario.getImagen();

        // Crear una respuesta con el contenido de la imagen y el tipo de contenido adecuado
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG); // Ajusta esto según el tipo de imagen que estás manejando
        headers.setContentLength(imagenBytes.length);

        return new ResponseEntity<>(imagenBytes, headers, HttpStatus.OK);
    }



}
