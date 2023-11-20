package coffeTime.org.ProyectoCafeteria.repository;

import coffeTime.org.ProyectoCafeteria.dao.entity.ItemFactura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemFacturaRepo extends JpaRepository<ItemFactura,Long> {

}
