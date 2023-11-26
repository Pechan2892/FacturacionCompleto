package coffeTime.org.ProyectoCafeteria.controller;


import coffeTime.org.ProyectoCafeteria.dao.Dto.IdAux;
import coffeTime.org.ProyectoCafeteria.dao.entity.*;
import coffeTime.org.ProyectoCafeteria.service.implementation.ContactoServiceImpl;
import coffeTime.org.ProyectoCafeteria.service.implementation.FacturaServiceImpl;
import coffeTime.org.ProyectoCafeteria.service.implementation.ItemFacturaImpl;
import coffeTime.org.ProyectoCafeteria.service.implementation.ProductosServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/facturas")
public class FacturaController {
    @Autowired
    private FacturaServiceImpl facturaService;
    @Autowired
    private ItemFacturaImpl itemFacturaService;
    @Autowired
    private ContactoServiceImpl contactoService;
    @Autowired
    private ProductosServiceImpl productoService;

    private List<ItemFactura> listaItemsFactura = new ArrayList<>();

    @GetMapping("/crear/{id}")
    public String crearFactura(@PathVariable Long id, Model model, HttpServletRequest request,@RequestParam(defaultValue = "0")int page){
        Usuario usuario = obtenerUsuarioDesdeSesion(request);
        if (usuario == null) {
            return "redirect:/logear";
        }
        if(id!=IdAux.auxId){
            IdAux.setAuxId(id);
            listaItemsFactura.clear();
        }
        Contacto contacto = contactoService.obtenerContactoPorId(id);
        model.addAttribute("contacto",contacto);
        Page<Productos> productosPage = productoService.obtenerTodosLosProductos(PageRequest.of(page,5));
        model.addAttribute("productosPage",productosPage);
        model.addAttribute("usuario", usuario);
        model.addAttribute("listaItems",listaItemsFactura);

        return "crearFactura";
    }

    @PostMapping("/agregarProducto/{id}")
    public String agregarProducto(@RequestParam Long productoId, @RequestParam int cantidad, @PathVariable Long id) {
        if (cantidad == 0) {
            return "redirect:/facturas/crear/" + id;
        }

        Productos producto = productoService.obtenerProductoPorId(productoId);

        // Verificar si el producto ya existe en la lista
        Optional<ItemFactura> itemExistente = listaItemsFactura.stream()
                .filter(item -> item.getProductos().getId().equals(productoId))
                .findFirst();

        if (itemExistente.isPresent()) {
            // Si el producto ya existe, actualizar la cantidad
            ItemFactura itemExistenteActualizado = itemExistente.get();
            itemExistenteActualizado.setCantidad(itemExistenteActualizado.getCantidad() + cantidad);
        } else {
            // Si el producto no existe, crear un nuevo objeto ItemFactura
            ItemFactura nuevoItemFactura = new ItemFactura();
            nuevoItemFactura.setProductos(producto);
            nuevoItemFactura.setCantidad(cantidad);

            // Agregar el nuevo ItemFactura a la lista
            listaItemsFactura.add(nuevoItemFactura);
        }

        // Redirigir de nuevo a la página de creación de factura
        return "redirect:/facturas/crear/" + id;
    }

    @PostMapping("/cancelar")
    public String cancelarCreacionFactura(){
        listaItemsFactura.clear();
        IdAux.setAuxId(0L);
        return "redirect:/contactos";
    }
    private Usuario obtenerUsuarioDesdeSesion(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return (Usuario) session.getAttribute("usuario");
    }

    @PostMapping("/guardar/{id}")
    public String guardarFactura(@PathVariable Long id,@RequestParam String descripcion, @RequestParam String observacion){
        if(listaItemsFactura.isEmpty()){
            return "redirect:/facturas/crear/"+id;
        }
        Factura factura= new Factura();
        factura.setDescripcion(descripcion);
        factura.setObservacion(observacion);
        factura.setCreateAt(new Date());

        Contacto contacto=contactoService.obtenerContactoPorId(id);
        factura.setContacto(contacto);

        for(ItemFactura itemFactura:listaItemsFactura){
            factura.addItemFactura(itemFactura);
        }
        facturaService.saveFactura(factura);
        listaItemsFactura.clear();
        return "redirect:/contactos";
    }

    @PostMapping("/borrarFactura/{id}")
    public String borrarFactura(@PathVariable Long id,@RequestParam Long contactoId){
        facturaService.deleteFacturaById(id);
        return "redirect:/verContacto/"+contactoId;
    }

}
