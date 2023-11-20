package coffeTime.org.ProyectoCafeteria.service.Interface;

import coffeTime.org.ProyectoCafeteria.dao.Dto.UsuarioRegistroDto;
import coffeTime.org.ProyectoCafeteria.dao.entity.Usuario;

public interface UsuarioService{

    public Usuario guardarUsuario(UsuarioRegistroDto registroDto);

    public Usuario BuscarPorEmail(String email);


}
