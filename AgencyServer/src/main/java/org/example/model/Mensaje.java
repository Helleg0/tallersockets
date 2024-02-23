package org.example.model;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class Mensaje implements Serializable {

    private static final long serialVersionUID = 1L;
    private String kind;
    private Object content;
    public static final String LISTAR_DESTINOS = "listarDestinos";
    public static final String AGREGAR_RESERVA = "agregarReserva";

}
