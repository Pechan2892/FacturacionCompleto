package coffeTime.org.ProyectoCafeteria.repository;

import coffeTime.org.ProyectoCafeteria.dao.entity.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FacturaRepo extends JpaRepository<Factura,Long> {
    @Query("SELECT f FROM Factura f join fetch f.contacto c join fetch f.items l join fetch l.productos p WHERE f.id=?1")
    public Factura fetchByIdWithItemFacturaWithProducto(Long id);
}
