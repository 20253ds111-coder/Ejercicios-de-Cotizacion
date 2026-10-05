package mx.edu.utez.proyecto1.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CotizacionResponse {
    private String servicio;
    private double costoTotal;
}