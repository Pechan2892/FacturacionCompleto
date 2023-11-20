package coffeTime.org.ProyectoCafeteria.service.implementation;

import coffeTime.org.ProyectoCafeteria.dao.entity.ItemFactura;
import coffeTime.org.ProyectoCafeteria.repository.ItemFacturaRepo;
import coffeTime.org.ProyectoCafeteria.service.Interface.ItemFacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemFacturaImpl implements ItemFacturaService {
    @Autowired
    private ItemFacturaRepo itemFacturaRepo;

    @Override
    public void GuardarItem(ItemFactura itemFactura) {
        itemFacturaRepo.save(itemFactura);
    }

    @Override
    public ItemFactura buscarItemPorId(Long id) {
        return itemFacturaRepo.findById(id).orElse(null);
    }

    @Override
    public void borrarItemPorId(Long id) {
        itemFacturaRepo.deleteById(id);
    }
}
