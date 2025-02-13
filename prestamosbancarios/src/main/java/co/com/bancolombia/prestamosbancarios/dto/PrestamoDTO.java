package co.com.bancolombia.prestamosbancarios.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public class PrestamoDTO {
    @NotEmpty(message = "Debe ingresar un ID de un prestamo")
    @Min(message = "Debe ingresar un ID de prestamo valido", value = 1L)
    protected String idPrestamo;

    @Pattern(regexp = "^(consultar|aprobar|rechazar)$", message = "Acción no válida")
    protected String accion;

    public PrestamoDTO(String idPrestamo, String accion) {
        this.idPrestamo = idPrestamo;
        this.accion = accion == null ? "consultar" : accion;
    }

    public Long idPrestamo() {
        return Long.valueOf(idPrestamo);
    }

    public String accion() {
        return accion;
    }
}
