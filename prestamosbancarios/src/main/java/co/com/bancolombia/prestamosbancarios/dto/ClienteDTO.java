package co.com.bancolombia.prestamosbancarios.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

public class ClienteDTO {
    @NotEmpty(message = "Debe ingresar un ID de cliente")
    @Min(message = "Debe ingresar un ID de cliente válido", value = 1L)
    protected String idCliente;

    @Min(message = "Para consultar el número de préstamos a consultar, debe ingresar un valor igual o mayor a 1", value = 1L)
    protected String numPrestamos;

    public ClienteDTO(String idCliente, String numPrestamos) {
        this.idCliente = idCliente;
        this.numPrestamos = numPrestamos == null ? "3" : numPrestamos;
    }

    public Long idCliente() {
        return Long.valueOf(idCliente);
    }

    public Integer numPrestamos() {
        return Integer.valueOf(numPrestamos);
    }
}
