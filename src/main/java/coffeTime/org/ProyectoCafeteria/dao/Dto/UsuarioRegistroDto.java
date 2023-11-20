package coffeTime.org.ProyectoCafeteria.dao.Dto;


import jakarta.validation.constraints.*;

public class UsuarioRegistroDto {
    private Long id;
    @NotBlank(message = "el nombre no puede estar vacio")
    @NotEmpty(message = "el nombre no debe ser espacios")
    @Size(min = 3,max = 30)
    private String nombre;
    @NotEmpty(message = "El apellido no puede estar vacio")
    @NotBlank(message = "El apellido no debe ser espacios")
    @Size(min = 3,max = 30)
    private String apellido;
    @Email(message = "El formato del correo electrónico no es válido")
    @Pattern(
            regexp = "^[A-Za-z0-9_\\-+]+(\\.[A-Za-z0-9_\\-+]+)*@(gmail\\.com|hotmail\\.com|outlook\\.com)$",
            message = "El correo electrónico no es válido"
    )
    private String email;
    @NotEmpty(message = "El contraseña no puede estar vacio")
    @NotBlank(message = "El contraseña no debe ser espacio")
    @Size(min = 4,max = 15)
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UsuarioRegistroDto(Long id, String nombre, String apellido, String email, String password) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.password = password;
    }

    public UsuarioRegistroDto(String nombre, String apellido, String email, String password) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.password = password;
    }

    public UsuarioRegistroDto(String email) {
        this.email = email;
    }

    public UsuarioRegistroDto() {
    }
}
