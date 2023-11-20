package coffeTime.org.ProyectoCafeteria.service.Interface;

import coffeTime.org.ProyectoCafeteria.dao.entity.Contacto;
import coffeTime.org.ProyectoCafeteria.dao.entity.Factura;
import coffeTime.org.ProyectoCafeteria.dao.entity.ItemFactura;

public interface ItemFacturaService {
    public void GuardarItem(ItemFactura itemFactura);
    public ItemFactura buscarItemPorId(Long id);
    public void borrarItemPorId(Long id);

}
