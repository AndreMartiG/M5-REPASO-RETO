package co.com.bancolombia.prestamosbancarios.repository;

import co.com.bancolombia.prestamosbancarios.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Long> {

}
