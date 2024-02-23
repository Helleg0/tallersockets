package org.example.model;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

@Data
@Builder
@ToString
public class PaqueteTuristico implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String name;
    private Date date;
    private Integer time;
    private Double price;
    private ArrayList<String> destinations;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Nombre Paquete: ").append(name).append("\n");
        sb.append("Fecha de Salida: ").append(date.toString()).append("\n");
        sb.append("Cubre: ").append(time).append(" días\n");
        sb.append("Precio Total: $").append(price).append("\n");
        sb.append("Lugares a visitar: ").append(destinations.toString()).append("\n");
        return sb.toString();
    }


}
