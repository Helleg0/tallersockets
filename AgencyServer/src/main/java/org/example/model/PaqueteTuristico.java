package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

@Data
@Builder
@ToString
@AllArgsConstructor
public class PaqueteTuristico implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String name;
    private Date date;
    private Integer time;
    private Double price;
    private ArrayList<String> destinations;

}
