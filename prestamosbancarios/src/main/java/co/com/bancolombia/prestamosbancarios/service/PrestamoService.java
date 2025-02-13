package co.com.bancolombia.prestamosbancarios.service;

import co.com.bancolombia.prestamosbancarios.dto.PrestamoDTO;
import co.com.bancolombia.prestamosbancarios.dto.SimulacionCuotasDTO;
import co.com.bancolombia.prestamosbancarios.dto.SolicitudPrestamoDTO;
import co.com.bancolombia.prestamosbancarios.model.Cliente;
import co.com.bancolombia.prestamosbancarios.model.Prestamo;
import co.com.bancolombia.prestamosbancarios.repository.ClienteRepository;
import co.com.bancolombia.prestamosbancarios.repository.PrestamoRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PrestamoService {

    private final ClienteRepository CLIENTES_RP;
    private final PrestamoRepository PRESTAMO_RP;

    public PrestamoService(ClienteRepository clientesRp, PrestamoRepository prestamoRp) {
        CLIENTES_RP = clientesRp;
        PRESTAMO_RP = prestamoRp;
    }

    public Prestamo registarPrestamo(SolicitudPrestamoDTO solicitudPrestamo) {
        Cliente cliente = buscarCliente(solicitudPrestamo.idCliente());
        BigDecimal interes = new BigDecimal("0.15");

        Prestamo nuevoPrestamo = new Prestamo(
                solicitudPrestamo.monto(),
                interes,
                solicitudPrestamo.duracionMeses(),
                "Pendiente",
                cliente,
                new Date(),
                new Date()
        );
        PRESTAMO_RP.save(nuevoPrestamo);

        return nuevoPrestamo;
    }

    public Prestamo gestionarPrestamo(PrestamoDTO prestamo) {
        if (prestamo.accion().equals("consultar"))
            throw new IllegalArgumentException("Error: Opcion no habilitada para la acción consultar");

        Prestamo prestamoSelect = buscarPrestamo(prestamo.idPrestamo());
        String estado = prestamo.accion().equals("aprobar") ? "Aprobado" : "Rechazado";

        prestamoSelect.nuevoEstado(estado);
        prestamoSelect.nuevaFechaActualizacion();
        PRESTAMO_RP.save(prestamoSelect);

        return prestamoSelect;
    }

    public List<Prestamo> historialPrestamos(Cliente id) {
        return PRESTAMO_RP.findByClienteOrderByIdDesc(id);
    }

    public List<Prestamo> historialPrestamos(Cliente id, Integer numPrestamos) {
        return PRESTAMO_RP.findByClienteOrderByIdDesc(id)
                .stream()
                .limit(numPrestamos)
                .collect(Collectors.toList());
    }

    public String simularCuotas(SimulacionCuotasDTO infoSimulacion) {
        // valorCuouta =
        // (monto * interesMensual * (1 + interesMensual)^duracionMeses) / ((1 + interesMensual)^duracionMeses - 1)

        int scale = 10;
        MathContext mc10 = new MathContext(10, RoundingMode.HALF_EVEN);

        BigDecimal interesMensual = BigDecimal.valueOf(infoSimulacion.porcentajeInteres())
                .divide(BigDecimal.valueOf(1200), scale, RoundingMode.HALF_EVEN);

        BigDecimal parentesis = BigDecimal.ONE.add(interesMensual).pow(infoSimulacion.duracionMeses(), mc10);

        BigDecimal valorCuota = BigDecimal.valueOf(infoSimulacion.monto()).multiply(interesMensual)
                .multiply(parentesis)
                .divide(parentesis.subtract(BigDecimal.ONE), 2, RoundingMode.HALF_EVEN);

        return "Resultado de la Simulacion {" +
                "\n Cuota simulada: $" + valorCuota +
                "\n Monto: $" + infoSimulacion.monto() +
                "\n Interes EA: " + infoSimulacion.porcentajeInteres() + "%" +
                "\n Plazo: " + infoSimulacion.duracionMeses() + " meses" +
                "\n}";
    }

    public Cliente buscarCliente(Long idCliente) {
        Optional<Cliente> clienteSelect = CLIENTES_RP.findById(idCliente);
        if (clienteSelect.isEmpty())
            throw new NoSuchElementException("Error: El cliente indicado no existe");

        return clienteSelect.get();
    }

    public Prestamo buscarPrestamo(Long idPrestamo) {
        Optional<Prestamo> prestamoSelect = PRESTAMO_RP.findById(idPrestamo);
        if (prestamoSelect.isEmpty())
            throw new NoSuchElementException("Error: El prestamo indicado no existe");

        return prestamoSelect.get();
    }
}
