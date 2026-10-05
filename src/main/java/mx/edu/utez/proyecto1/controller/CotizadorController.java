package mx.edu.utez.proyecto1.controller;

import mx.edu.utez.proyecto1.model.dto.*;
import mx.edu.utez.proyecto1.service.CotizadorService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cotizador")
public class CotizadorController {

    private final CotizadorService servicio;

    public CotizadorController(CotizadorService servicio) {
        this.servicio = servicio;
    }

    @PostMapping("/envio")
    public ResponseEntity<CotizacionResponse> calcularEnvio(@Valid @RequestBody EnvioRequest datos) {
        return ResponseEntity.ok(servicio.calcularEnvio(datos));
    }

    @PostMapping("/vehiculo")
    public ResponseEntity<CotizacionResponse> calcularVehiculo(@Valid @RequestBody RentaVehiculoRequest datos) {
        return ResponseEntity.ok(servicio.calcularVehiculo(datos));
    }

    @PostMapping("/hospedaje")
    public ResponseEntity<CotizacionResponse> calcularHospedaje(@Valid @RequestBody HospedajeRequest datos) {
        return ResponseEntity.ok(servicio.calcularHospedaje(datos));
    }
}