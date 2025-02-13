package co.com.bancolombia.prestamosbancarios.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

import java.math.BigDecimal;

public class SolicitudPrestamoDTO {

    @NotEmpty(message = "Debe ingresar un ID de cliente")
    @Min(message = "Debe ingresar un ID de cliente válido", value = 1L)
    protected String idCliente;

    @NotEmpty(message = "Debe ingresar un monto válido")
    @Min(message = "Debe ingresar un monto mayor o igual a $1000", value = 1000L)
    protected String monto;

    @NotEmpty(message = "Debe ingresar un número de meses válido")
    @Min(message = "Debe ingresar una duracion mayor o igual a 3 meses", value = 3L)
    protected String duracionMeses;

    public SolicitudPrestamoDTO(String idCliente, String monto, String duracionMeses) {
        this.idCliente = idCliente;
        this.monto = monto;
        this.duracionMeses = duracionMeses;
    }

    public Long idCliente() {
        return Long.valueOf(idCliente);
    }

    public BigDecimal monto() {
        return new BigDecimal(monto);
    }

    public Integer duracionMeses() {
        return Integer.valueOf(duracionMeses);
    }
}
