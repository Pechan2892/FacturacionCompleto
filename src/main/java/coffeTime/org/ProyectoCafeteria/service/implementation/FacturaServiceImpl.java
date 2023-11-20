package coffeTime.org.ProyectoCafeteria.service.implementation;

import coffeTime.org.ProyectoCafeteria.dao.entity.Contacto;
import coffeTime.org.ProyectoCafeteria.dao.entity.Factura;
import coffeTime.org.ProyectoCafeteria.repository.ContactoRepo;
import coffeTime.org.ProyectoCafeteria.repository.FacturaRepo;
import coffeTime.org.ProyectoCafeteria.service.Interface.FacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.stream.FactoryConfigurationError;

@Service
public class FacturaServiceImpl implements FacturaService {

    @Autowired
    private FacturaRepo facturaRepo;
    @Autowired
    private ContactoRepo contactoRepo;
    @Override
    public void saveFactura(Factura factura) {
        facturaRepo.save(factura);
    }

    @Override
    public Factura findFacturaById(Long id) {
        return facturaRepo.findById(id).orElse(null);
    }

    @Override
    public void deleteFacturaById(Long id) {
        facturaRepo.deleteById(id);
    }

    @Override
    public Factura fetchByIdWithItemFacturaWithProducto(Long id) {
        return facturaRepo.fetchByIdWithItemFacturaWithProducto(id);
    }
    @Override
    public Contacto fetchByIdWithFacturas(Long id) {
        return contactoRepo.fetchByIdWithFacturas(id);
    }
}
