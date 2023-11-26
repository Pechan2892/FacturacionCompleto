package coffeTime.org.ProyectoCafeteria.service.Interface;

import coffeTime.org.ProyectoCafeteria.dao.Dto.UsuarioRegistroDto;
import coffeTime.org.ProyectoCafeteria.dao.entity.Usuario;

import java.io.IOException;

public interface UsuarioService{

    public Usuario guardarUsuario(UsuarioRegistroDto registroDto) throws IOException;

    public Usuario BuscarPorEmail(String email);

    public Usuario buscarPorId(Long id);


}
