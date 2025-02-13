package co.com.bancolombia.prestamosbancarios.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public class SimulacionCuotasDTO {

    @NotEmpty(message = "Debe ingresar un monto de válido")
    @Min(message = "Debe ingresar un monto mayor o igual a $1000", value = 1000L)
    protected String monto;

    @NotEmpty(message = "Debe ingresar un porcentaje de interés válido")
    @DecimalMin(message = "Debe ingresar un porcentaje entre 1 y 100", value = "1.00")
    @DecimalMax(message = "Debe ingresar un porcentaje entre 1 y 100", value = "100.00") // 100% de interes
    protected String porcentajeInteres;

    @NotEmpty(message = "Debe ingresar un número de meses válido")
    @Min(message = "Debe ingresar una duracion entre 3 y 120 meses", value = 3)
    @Max(message = "Debe ingresar una duracion entre 3 y 120 meses", value = 120)   // 10 años
    protected String duracionMeses;

    public SimulacionCuotasDTO(String monto, String porcentajeInteres, String duracionMeses) {
        this.monto = monto;
        this.porcentajeInteres = porcentajeInteres;
        this.duracionMeses = duracionMeses;
    }

    public Long monto() {
        return Long.valueOf(monto);
    }

    public Float porcentajeInteres() {
        return Float.valueOf(porcentajeInteres);
    }

    public Integer duracionMeses() {
        return Integer.valueOf(duracionMeses);
    }
}
