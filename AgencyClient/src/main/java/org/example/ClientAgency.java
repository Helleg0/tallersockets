package org.example;

import lombok.extern.slf4j.Slf4j;
import org.example.model.Mensaje;
import org.example.model.Persona;
import org.example.model.Reserva;
import org.example.model.PaqueteTuristico;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.UUID;

@Slf4j
public class ClientAgency {

    private static final String HOST = "localhost";

    private static final int PORT = 2222;

    private static int countId = 0;


    ArrayList<PaqueteTuristico> listarPaquetes() {
        try (Socket socket = new Socket(HOST, PORT)) {

            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            out.writeObject(Mensaje.builder().kind("listarPaquetesTurismo").build());

            Object respuesta = in.readObject();
            ArrayList<PaqueteTuristico> list = (ArrayList<PaqueteTuristico>) respuesta;

            in.close();
            out.close();
            return list;

        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }

    }

    public void registrarPersona(String name, String documentNumber, String email, String
            address, String phoneNumber) {

        try (Socket socket = new Socket(HOST, PORT)) {

            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            String id = UUID.randomUUID().toString();

            Persona persona = Persona.builder()
                    .id(countId++)
                    .name(name)
                    .documentNumber(documentNumber)
                    .email(email)
                    .phoneNumber(phoneNumber)
                    .address(address)
                    .build();

            out.writeObject(Mensaje.builder().kind("agregarPersona").content(persona).build());

            Object respuesta = in.readObject();
            log.info((String) respuesta);

            in.close();
            out.close();
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Persona> listarPersonas() {

        try (Socket socket = new Socket(HOST, PORT)) {

            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            out.writeObject(Mensaje.builder().kind("listarPersonas").build());
            Object respuesta = in.readObject();
            ArrayList<Persona> list = (ArrayList<Persona>) respuesta;

            in.close();
            out.close();
            return list;

        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void registrarReserva(String id_persona, Integer touristPackageId, String date, Integer
            amount_people) {

        try (Socket socket = new Socket(HOST, PORT)) {
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            String id = UUID.randomUUID().toString();

            Reserva reserva = Reserva.builder()
                    .id(countId++)
                    .idPerson(id_persona)
                    .touristPackageId(touristPackageId)
                    .date(date)
                    .amountPeople(amount_people)
                    .build();

            out.writeObject(Mensaje.builder().kind("agregarReserva").content(reserva).build());
            Object respuesta = in.readObject();
            log.info((String) respuesta);

            in.close();
            out.close();
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    ArrayList<Reserva> listarReservas() {
        try (Socket socket = new Socket(HOST, PORT)) {

            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            out.writeObject(Mensaje.builder().kind("listarReservas").build());

            Object respuesta = in.readObject();
            ArrayList<Reserva> list = (ArrayList<Reserva>) respuesta;

            in.close();
            out.close();
            return list;

        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }

    }


}
