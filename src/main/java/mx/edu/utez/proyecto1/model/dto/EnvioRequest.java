package mx.edu.utez.proyecto1.model.dto;

import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.*;

@Getter
@Setter
public class EnvioRequest {

    @NotBlank(message = "El código postal es obligatorio")
    private String codigoPostal;

    @NotNull(message = "El peso es obligatorio")
    @Positive(message = "El peso debe ser positivo")
    @Max(value = 50, message = "No se aceptan paquetes que pesen más de 50 kg")
    private Double pesoKg;

    @NotNull(message = "El largo es obligatorio")
    @Positive(message = "El largo debe ser positivo")
    @Max(value = 150, message = "No se aceptan dimensiones superiores a 150 cm")
    private Double largoCm;

    @NotNull(message = "El ancho es obligatorio")
    @Positive(message = "El ancho debe ser positivo")
    @Max(value = 150, message = "No se aceptan dimensiones superiores a 150 cm")
    private Double anchoCm;

    @NotNull(message = "El alto es obligatorio")
    @Positive(message = "El alto debe ser positivo")
    @Max(value = 150, message = "No se aceptan dimensiones superiores a 150 cm")
    private Double altoCm;

    @NotBlank(message = "El tipo de envío es obligatorio")
    private String tipoEnvio;

    @NotNull(message = "El valor declarado es obligatorio")
    @PositiveOrZero(message = "El valor declarado no puede ser negativo")
    private Double valorDeclarado;
}