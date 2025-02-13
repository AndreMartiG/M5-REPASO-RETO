package co.com.bancolombia.prestamosbancarios.controller;

import co.com.bancolombia.prestamosbancarios.dto.ClienteDTO;
import co.com.bancolombia.prestamosbancarios.dto.PrestamoDTO;
import co.com.bancolombia.prestamosbancarios.dto.SimulacionCuotasDTO;
import co.com.bancolombia.prestamosbancarios.dto.SolicitudPrestamoDTO;
import co.com.bancolombia.prestamosbancarios.model.Cliente;
import co.com.bancolombia.prestamosbancarios.model.Prestamo;
import co.com.bancolombia.prestamosbancarios.service.PrestamoService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prestamo")
public class PrestamoController {

    private final PrestamoService PRESTAMO_SERVICE;

    public PrestamoController(PrestamoService prestamoService) {
        PRESTAMO_SERVICE = prestamoService;
    }

    @PostMapping("/solicitarPrestamo")
    public String solicitarPrestamo(@Valid @RequestBody SolicitudPrestamoDTO sulicitudPrestamo) {
        return "Prestamo solicitado con éxito!\n"
                + PRESTAMO_SERVICE.registarPrestamo(sulicitudPrestamo).toString();
    }

    @PostMapping("/gestionarPrestamo")
    public String gestionarPrestamo(@Valid @RequestBody PrestamoDTO prestamo) {
        return "Prestamo actualizado con éxito!\n"
                + PRESTAMO_SERVICE.gestionarPrestamo(prestamo).toString();
    }

    @GetMapping("/consultarPrestamo")
    public String consultarPrestamo(@Valid @RequestBody PrestamoDTO prestamo) {
        return PRESTAMO_SERVICE.buscarPrestamo(prestamo.idPrestamo()).toString();
    }

    @GetMapping("/historialPorCliente/{id}")
    public String historialPorCliente(@PathVariable Cliente id) {
        List<Prestamo> prestamos = PRESTAMO_SERVICE.historialPrestamos(id);
        return prestamos.toString();
    }

    @GetMapping("/historialPorCliente2")
    public String historialPorCliente(@Valid @RequestBody ClienteDTO clienteDTO) {
        Cliente cliente = PRESTAMO_SERVICE.buscarCliente(clienteDTO.idCliente());
        List<Prestamo> prestamos = PRESTAMO_SERVICE.historialPrestamos(cliente, clienteDTO.numPrestamos());
        return prestamos.toString();
    }

    @GetMapping("/simularCuotas")
    public String simularCuotas(@Valid @RequestBody SimulacionCuotasDTO infoSimulacion) {
        return PRESTAMO_SERVICE.simularCuotas(infoSimulacion);
    }
}
