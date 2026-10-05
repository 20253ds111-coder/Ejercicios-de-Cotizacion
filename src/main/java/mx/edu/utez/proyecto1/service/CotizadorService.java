package mx.edu.utez.proyecto1.service;

import mx.edu.utez.proyecto1.exception.ReglaNegocioException;
import mx.edu.utez.proyecto1.model.dto.*;
import org.springframework.stereotype.Service;

@Service
public class CotizadorService {

    public CotizacionResponse calcularEnvio(EnvioRequest datos) {
        double volumen = datos.getLargoCm() * datos.getAnchoCm() * datos.getAltoCm();

        if (volumen > 1000000) {
            throw new ReglaNegocioException("El volumen del paquete no puede superar los 1,000,000 cm³");
        }

        double costo = 80.0;
        costo += datos.getPesoKg() * 12.0;

        if (volumen > 50000) {
            costo += 100.0;
        }

        String tipo = datos.getTipoEnvio().toUpperCase();
        if (tipo.equals("EXPRESS")) {
            costo *= 1.40;
        } else if (tipo.equals("MISMO_DIA")) {
            costo *= 1.70;
        }

        if (datos.getValorDeclarado() > 10000) {
            costo += datos.getValorDeclarado() * 0.02;
        }

        return new CotizacionResponse("Cotización de Envío", Math.round(costo * 100.0) / 100.0);
    }

    public CotizacionResponse calcularVehiculo(RentaVehiculoRequest datos) {
        String tipo = datos.getTipoVehiculo().toUpperCase();

        if (tipo.equals("CAMIONETA") && datos.getEdadConductor() < 25) {
            throw new ReglaNegocioException("No se permite la renta de CAMIONETA a conductores menores de 25 años");
        }

        double costoDiario = switch (tipo) {
            case "COMPACTO" -> 550.0;
            case "SEDAN" -> 700.0;
            case "SUV" -> 950.0;
            case "CAMIONETA" -> 1200.0;
            default -> throw new ReglaNegocioException("Tipo de vehículo no válido: " + datos.getTipoVehiculo());
        };

        double costoRenta = costoDiario * datos.getDiasRenta();

        int kmIncluidos = datos.getDiasRenta() * 100;
        double cargoKmAdicional = 0.0;
        if (datos.getKilometrosEstimados() > kmIncluidos) {
            cargoKmAdicional = (datos.getKilometrosEstimados() - kmIncluidos) * 4.0;
        }

        double cargoEdad = 0.0;
        if (datos.getEdadConductor() >= 18 && datos.getEdadConductor() <= 24) {
            cargoEdad = (costoRenta + cargoKmAdicional) * 0.15;
        }

        double cargoSeguro = 0.0;
        if (Boolean.TRUE.equals(datos.getSeguroCompleto())) {
            cargoSeguro = datos.getDiasRenta() * 180.0;
        }

        double descuento = 0.0;
        if (datos.getDiasRenta() >= 7) {
            descuento = costoRenta * 0.10;
        }

        double total = (costoRenta - descuento) + cargoKmAdicional + cargoEdad + cargoSeguro;

        return new CotizacionResponse("Cotización de Renta de Vehículo", Math.round(total * 100.0) / 100.0);
    }

    public CotizacionResponse calcularHospedaje(HospedajeRequest datos) {
        String tipo = datos.getTipoHabitacion().toUpperCase();

        double costoNoche;
        int capacidad;

        switch (tipo) {
            case "INDIVIDUAL" -> { costoNoche = 700.0; capacidad = 1; }
            case "DOBLE" -> { costoNoche = 1100.0; capacidad = 2; }
            case "SUITE" -> { costoNoche = 1800.0; capacidad = 4; }
            default -> throw new ReglaNegocioException("Tipo de habitación no válido: " + datos.getTipoHabitacion());
        }

        if (datos.getNumeroHuespedes() > capacidad) {
            throw new ReglaNegocioException("El número de huéspedes supera la capacidad de la habitación (" + capacidad + " persona/s max)");
        }

        double costoHospedaje = costoNoche * datos.getNumeroNoches();

        String temporada = datos.getTemporada().toUpperCase();
        double ajusteTemporada = 0.0;
        if (temporada.equals("BAJA")) {
            ajusteTemporada = - (costoHospedaje * 0.10);
        } else if (temporada.equals("ALTA")) {
            ajusteTemporada = costoHospedaje * 0.25;
        }

        double costoHospedajeConTemporada = costoHospedaje + ajusteTemporada;

        double descuentoLargaEstancia = 0.0;
        if (datos.getNumeroNoches() >= 7) {
            descuentoLargaEstancia = costoHospedaje * 0.08;
        }

        double hospedajeFinal = costoHospedajeConTemporada - descuentoLargaEstancia;

        double costoDesayuno = 0.0;
        if (Boolean.TRUE.equals(datos.getIncluyeDesayuno())) {
            costoDesayuno = datos.getNumeroHuespedes() * datos.getNumeroNoches() * 150.0;
        }

        double costoEstacionamiento = 0.0;
        if (Boolean.TRUE.equals(datos.getIncluyeEstacionamiento())) {
            costoEstacionamiento = datos.getNumeroNoches() * 100.0;
        }

        double subtotal = hospedajeFinal + costoDesayuno + costoEstacionamiento;
        double impuesto = subtotal * 0.04;
        double total = subtotal + impuesto;

        return new CotizacionResponse("Cotización de Hospedaje", Math.round(total * 100.0) / 100.0);
    }
}