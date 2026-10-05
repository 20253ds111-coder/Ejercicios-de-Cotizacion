package mx.edu.utez.proyecto1.model.dto;

import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.*;

@Getter
@Setter
public class RentaVehiculoRequest {

    @NotBlank(message = "El nombre del cliente es obligatorio")
    private String nombreCliente;

    @NotNull(message = "La edad es obligatoria")
    @Min(value = 18, message = "El conductor debe ser mayor o igual a 18 años")
    private Integer edadConductor;

    @NotBlank(message = "El tipo de vehículo es obligatorio")
    private String tipoVehiculo;

    @NotNull(message = "Los días de renta son obligatorios")
    @Min(value = 1, message = "Mínimo 1 día de renta")
    @Max(value = 30, message = "La renta no puede superar los 30 días")
    private Integer diasRenta;

    @NotNull(message = "Los kilómetros estimados son obligatorios")
    @PositiveOrZero(message = "Los kilómetros no pueden ser negativos")
    @Max(value = 5000, message = "Los kilómetros estimados no pueden superar los 5,000 km")
    private Integer kilometrosEstimados;

    private Boolean seguroCompleto = false;
}