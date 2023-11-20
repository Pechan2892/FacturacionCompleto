package coffeTime.org.ProyectoCafeteria.controller;

import coffeTime.org.ProyectoCafeteria.dao.Dto.ProductoDto;
import coffeTime.org.ProyectoCafeteria.dao.entity.Productos;
import coffeTime.org.ProyectoCafeteria.dao.entity.Usuario;
import coffeTime.org.ProyectoCafeteria.service.implementation.ProductosServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
@RequestMapping("/productos")
public class ProductoController {
    @Autowired
    private ProductosServiceImpl productosService;


    @GetMapping("/listar")
    public String listarProductos(HttpServletRequest request,Model model,@RequestParam(defaultValue = "0")int page){
        Usuario usuario = obtenerUsuarioDesdeSesion(request);
        if (usuario == null) {
            return "redirect:/logear";
        }

        Page<Productos> productosPage = productosService.obtenerTodosLosProductos(PageRequest.of(page,5));
        model.addAttribute("productosPage",productosPage);
        model.addAttribute("usuario", usuario);
        return "listarProductos";
    }

    @GetMapping("/crear")
    public String mostrarFormularioCreacion(Model model) {
        model.addAttribute("productoDto", new ProductoDto());
        return "crearProducto";
    }

    @PostMapping("/crear")
    public String crearProducto(@ModelAttribute ProductoDto productoDto) throws IOException {
        productosService.guardarProducto(productoDto);
        return "redirect:/productos/listar";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable Long id, Model model) {
        Productos producto = productosService.obtenerProductoPorId(id);
        model.addAttribute("producto", producto);
        return "editarProducto";
    }

    @PostMapping("/editar/{id}")
    public String editarProducto(@PathVariable Long id, @ModelAttribute ProductoDto productoDto) throws IOException {
        productosService.actualizarProducto(id,productoDto);
        return "redirect:/productos/listar";
    }

    @GetMapping("/borrar/{id}")
    public String borrarProducto(@PathVariable Long id) {
        Productos producto = productosService.obtenerProductoPorId(id);
        productosService.borrarProducto(producto);
        return "redirect:/productos/listar";
    }

    @GetMapping("/imagen/{id}")
    public ResponseEntity<byte[]> mostrarImagen(@PathVariable Long id) {
        Productos producto = productosService.obtenerProductoPorId(id);

        if (producto == null || producto.getImagen() == null) {
            // Puedes devolver una imagen de marcador de posición o un error aquí
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Obtener la imagen del producto como un array de bytes
        byte[] imagenBytes = producto.getImagen();

        // Crear una respuesta con el contenido de la imagen y el tipo de contenido adecuado
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG); // Ajusta esto según el tipo de imagen que estás manejando
        headers.setContentLength(imagenBytes.length);

        return new ResponseEntity<>(imagenBytes, headers, HttpStatus.OK);
    }


    private Usuario obtenerUsuarioDesdeSesion(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return (Usuario) session.getAttribute("usuario");
    }
}




