package org.example.model;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
@Data
@Builder
@ToString
public class Reserva implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String idPerson;

    private Integer touristPackageId;
    private String date;
    private Integer amountPeople;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Cliente: ").append(idPerson).append("\n");
        sb.append("Fecha de Reserva: ").append(date).append("\n");
        sb.append("Cantidad de Personas: ").append(amountPeople).append("\n");
        return sb.toString();
    }

}
