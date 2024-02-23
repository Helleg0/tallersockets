package org.example;

import org.example.model.Mensaje;
import org.example.model.Persona;
import org.example.model.Reserva;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ThreadClient implements Runnable {

    private final Socket socket;
    private final AgencyServer agencia;

    public ThreadClient(Socket socket, AgencyServer agencia) {
        this.socket = socket;
        this.agencia = agencia;
    }

    @Override
    public void run() {
        try {
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            Mensaje message = (Mensaje) in.readObject();
            String tipo = message.getKind();
            Object contenido = message.getContent();
            switch (tipo) {
                case "agregarPersona":
                    agregarPersona((Persona) contenido, out);
                    break;
                case "listarPersonas":
                    listarPersonas(out);
                    break;
                case "listarPaquetesTurismo":
                    listarPaquetes(out);
                    break;
                case "agregarReserva":
                    agregarReserva((Reserva) contenido, out);
                    break;
                case "listarReservas":
                    listarReservas(out);
                    break;
            }
            socket.close();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void listarPersonas(ObjectOutputStream out) throws IOException {
        out.writeObject(agencia.listarPersonas());
    }
    public void listarReservas(ObjectOutputStream out) throws IOException {
        out.writeObject(agencia.listarReservas());
    }
    public void listarPaquetes(ObjectOutputStream out) throws IOException {
        out.writeObject(agencia.listarPaquetesTurismo());
    }

    public void agregarPersona(Persona persona, ObjectOutputStream out) throws IOException {
        try {
            agencia.agregarPersona(persona);
            out.writeObject("registro usuario valido");
        }catch (Exception e){
            out.writeObject(e.getMessage());
        }
    }

    public void agregarReserva(Reserva reserva, ObjectOutputStream out) throws IOException {
        try {
            agencia.agregarReserva(reserva);
            out.writeObject("Reserva registrada");
        }catch (Exception e){
            out.writeObject(e.getMessage());
        }
    }
}
