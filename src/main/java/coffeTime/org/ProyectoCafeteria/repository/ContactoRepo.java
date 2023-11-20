package coffeTime.org.ProyectoCafeteria.repository;

import coffeTime.org.ProyectoCafeteria.dao.entity.Contacto;
import coffeTime.org.ProyectoCafeteria.dao.entity.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactoRepo extends JpaRepository<Contacto,Long> {
    @Query("SELECT c FROM Contacto c WHERE c.usuario = :usuario")
    Page<Contacto>findByUsuario(@Param("usuario") Usuario usuario, Pageable pageable);

    @Query("select c from Contacto c left join fetch c.facturas f where c.id = ?1")
    public Contacto fetchByIdWithFacturas(Long id);

}
