package org.example;

import lombok.extern.slf4j.Slf4j;
import org.example.model.Persona;
import org.example.model.Reserva;
import org.example.model.PaqueteTuristico;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

@Slf4j
public class AgencyServer {
    private ArrayList<Persona> personas;
    private ArrayList<PaqueteTuristico> paquetesTuristicos;
    private ArrayList<Reserva> reservas;

    public AgencyServer() {
        this.personas = new ArrayList<>();
        this.paquetesTuristicos = new ArrayList<>();
        this.reservas = new ArrayList<>();

        generateTouristPackages();
    }

    public ArrayList<Persona> listarPersonas() {
        return personas;
    }

    public ArrayList<PaqueteTuristico> listarPaquetesTurismo() {
        return paquetesTuristicos;
    }

    public ArrayList<Reserva> listarReservas() {
        return reservas;
    }


    public void agregarPersona(Persona persona) throws Exception {
        personas.add(persona);
        log.info("Persona agregada correctamente");
    }

    public void agregarReserva(Reserva reserva) throws Exception {
        reservas.add(reserva);
        log.info("Reserva agregada correctamente");
    }

    public void generateTouristPackages(){
        Random random = new Random();
        for (int i = 0; i < 5; i++){
            PaqueteTuristico tp = new PaqueteTuristico(i, "PT " + (i+1) , Operations.generateRandomDate(), random.nextInt(11), 500.0 + (200.0 - 500.0) * random.nextDouble(3),
                    Operations.generateRandomCities(4));
            this.paquetesTuristicos.add(tp);
        }
        log.info("Paquetes: " + paquetesTuristicos);
    }

}
