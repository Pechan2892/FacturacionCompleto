package coffeTime.org.ProyectoCafeteria.service.implementation;

import coffeTime.org.ProyectoCafeteria.dao.Dto.ProductoDto;
import coffeTime.org.ProyectoCafeteria.dao.entity.Productos;
import coffeTime.org.ProyectoCafeteria.repository.ProductoRepository;
import coffeTime.org.ProyectoCafeteria.service.Interface.ProductosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;


@Service
public class ProductosServiceImpl implements ProductosService {

    @Autowired
    private ProductoRepository repositorioProductos;

    @Override
    public Page<Productos> obtenerTodosLosProductos(Pageable pageable) {
        return repositorioProductos.findAll(pageable);
    }

    @Override
    public List<Productos> findByNombre(String term) {
        return repositorioProductos.findByNombre(term);
    }

    @Override
    public Productos obtenerProductoPorId(Long id) {
        return repositorioProductos.findById(id).orElse(null);
    }

    @Override
    public Productos guardarProducto(ProductoDto productoDto) throws IOException {
        Productos productos = new Productos(
                productoDto.getNombre(),
                productoDto.getPrecio()
        );
        byte [] imagen=productoDto.getImagenFile().getBytes();
        productos.setImagen(imagen);
        return repositorioProductos.save(productos);
    }

    @Override
    public Productos actualizarProducto(Long id,ProductoDto productoDto) throws IOException {
        Productos productoExistente=repositorioProductos.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
        productoExistente.setNombre(productoDto.getNombre());
        productoExistente.setPrecio(productoDto.getPrecio());
        if (productoDto.getImagenFile() != null && productoDto.getImagenFile().getSize() > 0) {
            byte[] nuevaImagen = productoDto.getImagenFile().getBytes();
            productoExistente.setImagen(nuevaImagen);
        }
        return  repositorioProductos.save(productoExistente);
    }

    @Override
    public void borrarProducto(Productos productos) {
        repositorioProductos.delete(productos);
    }
}
