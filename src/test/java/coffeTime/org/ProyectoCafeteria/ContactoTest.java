package coffeTime.org.ProyectoCafeteria;
import coffeTime.org.ProyectoCafeteria.dao.entity.Contacto;
import coffeTime.org.ProyectoCafeteria.dao.entity.Usuario;
import coffeTime.org.ProyectoCafeteria.repository.ContactoRepo;
import coffeTime.org.ProyectoCafeteria.service.implementation.ContactoServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ContactoTest {

    @Autowired
    private ContactoServiceImpl contactoService;

    @MockBean
    private ContactoRepo contactoRepository;

    @Test
    public void testGuardarContacto() {
        Contacto contacto = new Contacto();
        contacto.setId(1L);
        contacto.setNombre("Test");
        contacto.setEmail("test@test.com");
        contacto.setCelular("1234567890");
        contacto.setFechaNacimiento(LocalDate.now());
        contacto.setUsuario(new Usuario());
        contacto.setFacturas(new ArrayList<>());

        when(contactoRepository.save(any(Contacto.class))).thenReturn(contacto);

        Contacto created = contactoService.guardarContacto(contacto);

        assertEquals(created.getNombre(), "Test");
        assertEquals(created.getEmail(), "test@test.com");
        assertEquals(created.getCelular(), "1234567890");
        verify(contactoRepository, times(1)).save(contacto);
    }

    @Test
    public void testObtenerContactoPorId() {
        Contacto contacto = new Contacto();
        contacto.setId(1L);
        contacto.setNombre("Test");
        contacto.setEmail("test@test.com");
        contacto.setCelular("1234567890");
        contacto.setFechaNacimiento(LocalDate.now());
        contacto.setUsuario(new Usuario());
        contacto.setFacturas(new ArrayList<>());

        when(contactoRepository.findById(1L)).thenReturn(Optional.of(contacto));

        Contacto found = contactoService.obtenerContactoPorId(1L);

        assertEquals(found.getId(), contacto.getId());
        verify(contactoRepository, times(1)).findById(1L);
    }


    @Test
    public void testBorrarContacto() {
        Contacto contacto = new Contacto();
        contacto.setId(1L);

        when(contactoRepository.findById(1L)).thenReturn(Optional.of(contacto));
        contactoService.borrarContacto(contacto);

        verify(contactoRepository, times(1)).delete(contacto);
    }

    @Test
    public void testActualizarContacto() {
        Contacto contacto = new Contacto();
        contacto.setId(1L);
        contacto.setNombre("Test");
        contacto.setEmail("test@test.com");
        contacto.setCelular("1234567890");
        contacto.setFechaNacimiento(LocalDate.now());
        contacto.setUsuario(new Usuario());
        contacto.setFacturas(new ArrayList<>());

        when(contactoRepository.findById(1L)).thenReturn(Optional.of(contacto));

        contacto.setNombre("Test Actualizado");

        when(contactoRepository.save(ArgumentMatchers.any(Contacto.class))).thenAnswer(invocation -> {
            Contacto savedContacto = invocation.getArgument(0);
            assertEquals("Test Actualizado", savedContacto.getNombre());  // Additional check
            return savedContacto;
        });

        Contacto updated = contactoService.actualizarContacto(contacto);

        assertEquals(updated.getNombre(), "Test Actualizado");
        verify(contactoRepository, times(1)).save(ArgumentMatchers.any(Contacto.class));
    }


}
