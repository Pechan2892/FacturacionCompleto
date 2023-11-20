package coffeTime.org.ProyectoCafeteria.dao.Dto;

import jakarta.validation.constraints.*;
import org.springframework.web.multipart.MultipartFile;

public class ProductoDto {

    @NotBlank(message = "el nombre no puede estar vacio")
    @NotEmpty(message = "el nombre no debe ser espacios")
    @Size(min = 3,max = 30)
    private String nombre;

    @NotNull(message = "El precio no puede ser nulo")
    @DecimalMin(value = "0.01", message = "El precio debe ser igual o mayor a 0.01")
    @DecimalMax(value = "99999.99", message = "El precio no puede ser mayor a 99999.99")
    private Double precio;

    private MultipartFile imagenFile;

    public ProductoDto() {
        // Constructor vacío
    }

    public ProductoDto(String nombre, Double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public MultipartFile getImagenFile() {
        return imagenFile;
    }

    public void setImagenFile(MultipartFile imagenFile) {
        this.imagenFile = imagenFile;
    }
}
