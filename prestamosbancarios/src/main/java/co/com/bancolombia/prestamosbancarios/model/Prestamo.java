package co.com.bancolombia.prestamosbancarios.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class Prestamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    protected BigDecimal monto;
    protected BigDecimal interes;
    protected Integer duracionMeses;
    protected String estado;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    protected Cliente cliente;

    public Prestamo() { }

    public Prestamo(Long id, BigDecimal monto, BigDecimal interes, Integer duracionMeses, String estado, Cliente cliente) {
        this.id = id;
        this.monto = monto;
        this.interes = interes;
        this.duracionMeses = duracionMeses;
        this.estado = estado;
        this.cliente = cliente;
    }
}
