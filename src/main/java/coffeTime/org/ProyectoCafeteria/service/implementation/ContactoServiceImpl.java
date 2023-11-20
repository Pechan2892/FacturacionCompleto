package coffeTime.org.ProyectoCafeteria.service.implementation;

import coffeTime.org.ProyectoCafeteria.dao.entity.Contacto;
import coffeTime.org.ProyectoCafeteria.dao.entity.Usuario;
import coffeTime.org.ProyectoCafeteria.repository.ContactoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ContactoServiceImpl {
    @Autowired
    private ContactoRepo contactoRepo;

    public Page<Contacto> obtenerContactosUsuario(Usuario usuario, Pageable pageable){
        return contactoRepo.findByUsuario(usuario, pageable);
    }
    public Contacto guardarContacto(Contacto contacto){
        return contactoRepo.save(contacto);
    }

    public Contacto findOne(Long id){return contactoRepo.findById(id).orElse(null);}

    public void borrarContacto(Contacto contacto) {
        contactoRepo.delete(contacto);
    }

    public Contacto obtenerContactoPorId(Long id) {
        return contactoRepo.findById(id).orElse(null);
    }

    public Contacto actualizarContacto(Contacto existingContacto) {
        return contactoRepo.save(existingContacto);
    }

}
