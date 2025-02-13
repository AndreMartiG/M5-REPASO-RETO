package co.com.bancolombia.prestamosbancarios.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
public class Prestamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    protected BigDecimal monto;
    protected BigDecimal interes;
    protected Integer duracionMeses;
    protected String estado;
    protected Date fechaCreacion;
    protected Date fechaActualizacion;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    protected Cliente cliente;

    public Prestamo() { }

    public Prestamo(
            BigDecimal monto, BigDecimal interes, Integer duracionMeses, String estado,
            Cliente cliente, Date fechaCreacion, Date fechaActualizacion) {
        this.monto = monto;
        this.interes = interes;
        this.duracionMeses = duracionMeses;
        this.estado = estado;
        this.cliente = cliente;
        this.fechaCreacion = fechaCreacion;
        this.fechaActualizacion = fechaActualizacion;
    }

    public void nuevoEstado(String estado) {
        this.estado = estado;
    }

    public void nuevaFechaActualizacion() {
        this.fechaActualizacion = new Date();
    }

    @Override
    public String toString() {
        return "\nPrestamo {" +
                "\n id: " + id +
                "\n Monto: $" + monto +
                "\n Interes EA: " + interes.multiply(BigDecimal.valueOf(100)) + "%" +
                "\n Plazo: " + duracionMeses + " meses" +
                "\n Estado: '" + estado + '\'' +
                "\n Fecha Solicitud: " + fechaCreacion +
                "\n Fecha Actualizacion: " + fechaActualizacion +
                "\n}\n";
    }
}
