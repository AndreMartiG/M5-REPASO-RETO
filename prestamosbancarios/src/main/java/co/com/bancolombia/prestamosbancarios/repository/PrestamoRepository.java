package co.com.bancolombia.prestamosbancarios.repository;

import co.com.bancolombia.prestamosbancarios.model.Cliente;
import co.com.bancolombia.prestamosbancarios.model.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrestamoRepository extends JpaRepository<Prestamo,Long> {
    List<Prestamo> findByClienteOrderByIdDesc(Cliente clienteId);
}
