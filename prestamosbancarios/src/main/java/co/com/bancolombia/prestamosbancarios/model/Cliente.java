package co.com.bancolombia.prestamosbancarios.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    protected String nombre;
    protected String email;
    protected String telefono;
    protected String direccion;

    @OneToMany(mappedBy = "cliente")
    @OrderBy("id DESC")
    protected List<Prestamo> historialPrestamos;

    public Cliente() { }

    public Cliente(Long id, String nombre, String email, String telefono, String direccion) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.direccion = direccion;
    }
}
