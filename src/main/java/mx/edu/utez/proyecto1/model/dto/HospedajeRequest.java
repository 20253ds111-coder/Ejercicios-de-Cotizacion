package mx.edu.utez.proyecto1.model.dto;

import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.*;

@Getter
@Setter
public class HospedajeRequest {

    @NotBlank(message = "El nombre del huésped es obligatorio")
    private String nombreHuesped;

    @NotBlank(message = "El tipo de habitación es obligatorio")
    private String tipoHabitacion;

    @NotNull(message = "El número de noches es obligatorio")
    @Min(value = 1, message = "Mínimo 1 noche")
    @Max(value = 30, message = "El número de noches no puede superar 30")
    private Integer numeroNoches;

    @NotNull(message = "El número de huéspedes es obligatorio")
    @Min(value = 1, message = "Debe registrar al menos 1 huésped")
    private Integer numeroHuespedes;

    @NotBlank(message = "La temporada es obligatoria")
    private String temporada;

    private Boolean incluyeDesayuno = false;
    private Boolean incluyeEstacionamiento = false;
}