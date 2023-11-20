package coffeTime.org.ProyectoCafeteria.service.Interface;

import coffeTime.org.ProyectoCafeteria.dao.Dto.ProductoDto;
import coffeTime.org.ProyectoCafeteria.dao.entity.Productos;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.util.List;

public interface ProductosService {

    public Page<Productos> obtenerTodosLosProductos(Pageable pageable);

    public List<Productos> findByNombre(String term);

    public Productos obtenerProductoPorId(Long id);

    public Productos guardarProducto(ProductoDto productoDto) throws IOException;

    public Productos actualizarProducto(Long id,ProductoDto productoEditado) throws IOException;

    public void borrarProducto(Productos productos);


}
