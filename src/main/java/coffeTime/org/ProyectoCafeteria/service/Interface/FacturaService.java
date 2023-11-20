package coffeTime.org.ProyectoCafeteria.service.Interface;

import coffeTime.org.ProyectoCafeteria.dao.entity.Contacto;
import coffeTime.org.ProyectoCafeteria.dao.entity.Factura;

public interface FacturaService {
    public void saveFactura(Factura factura);
    public Factura findFacturaById(Long id);
    public void deleteFacturaById(Long id);
    public Factura fetchByIdWithItemFacturaWithProducto(Long id);
    public Contacto fetchByIdWithFacturas(Long id);
}
