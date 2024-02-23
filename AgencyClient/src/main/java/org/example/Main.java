package org.example;

import lombok.extern.slf4j.Slf4j;
import org.example.model.Persona;
import org.example.model.Reserva;
import org.example.model.PaqueteTuristico;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.*;

@Slf4j
public class Main {
    private static ClientAgency clientAgency = new ClientAgency();
    public static void main(String[] args) {
        boolean active = true;

        while (active) {

            String[] opciones = {"Lista de Paquetes", "Registrarse", "Realizar Reserva", "Lista de Usuarios", "Consulta de Reserva","Salir"};

            int seleccion = JOptionPane.showOptionDialog(null, "Seleccione", "Menú Principal",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]);

            switch (seleccion) {
                case 0:

                    JOptionPane.showMessageDialog(null, "Se ha seleccionado Lista de Paquetes Turisticos");
                    String message = "Lista de paquetes registrados en el sistema:\n";
                    int count = 1;
                    for (PaqueteTuristico paquete : clientAgency.listarPaquetes()) {
                        message += "Paquete " + count + ":\n" + paquete.toString() + "\n";
                        count++;
                    }
                    JOptionPane.showMessageDialog(null, message);

                    break;

                case 1:
                JOptionPane.showMessageDialog(null, "Se ha seleccionado la opción de registro, diligenciar la siguiente información personal");

                String name;
                do{
                    name = JOptionPane.showInputDialog("Nombre Completo");
                }while(name.isEmpty());

                String documentNumber;
                do{
                    documentNumber = JOptionPane.showInputDialog("Número de Documento");
                }while(documentNumber.isEmpty());
                String email;
                do{
                    email = JOptionPane.showInputDialog("Correo Electronico");
                }while(email.isEmpty());
                String address;
                do{
                    address = JOptionPane.showInputDialog("Dirección de Residencia");
                }while(address.isEmpty());
                String phoneNumber;
                do{
                    phoneNumber = JOptionPane.showInputDialog("Número de Teléfono");
                }while(phoneNumber.isEmpty());
                clientAgency.registrarPersona(name, documentNumber, email, address, phoneNumber);
                break;

                case 2:
                    JOptionPane.showMessageDialog(null, "Se ha seleccionado realizar reserva, diligenciar la siguiente información para que sea valida.");
                    String num_paquete;
                    do{
                        num_paquete = JOptionPane.showInputDialog("Número de paquete turistico de interes.");
                    }while(num_paquete.isEmpty());
                    Integer num_paquete_int = Integer.parseInt(num_paquete);
                    String documento_id;
                    do{
                        documento_id = JOptionPane.showInputDialog("Número de documento de identidad");
                    }while(!buscarPersonaId(documento_id));
                    String num_participantes;
                    do{
                        num_participantes = JOptionPane.showInputDialog("Número de asistentes de esta reserva");
                    }while(num_participantes.isEmpty());
                    int num_participantes_int = Integer.parseInt(num_participantes);
                    LocalDateTime now = LocalDateTime.now();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    String formattedDateTime = now.format(formatter);
                    String fecha_hora = formattedDateTime;
                    clientAgency.registrarReserva(documento_id, (num_paquete_int-1), fecha_hora, num_participantes_int);

                    break;
                case 3:
                    String message_ = "Usuarios registrados en el sistema:\n";
                    int countTwo = 1;
                    for (Persona persona : clientAgency.listarPersonas()) {
                       message_ += "Cliente " + countTwo + ":\n" +  persona.toString() + "\n";
                    }
                    JOptionPane.showMessageDialog(null, message_);

                    break;
                case 4:

                    JOptionPane.showMessageDialog(null, "Se ha seleccionado Consulta de reservas");
                    String documento_persona;
                    do{
                        documento_persona = JOptionPane.showInputDialog("Ingrese nro. de documento para consultar su reserva");
                    }while(!buscarPersonaId(documento_persona));
                    Reserva reserva= buscarReserva(documento_persona);
                    assert reserva != null;
                    PaqueteTuristico touristPackage = buscarPaqueteId(reserva.getTouristPackageId());

                    assert touristPackage != null;
                    JOptionPane.showMessageDialog(null, reserva.toString() + "\n" + touristPackage.toString() );
                    break;
                case 5:
                    active = false;
                    System.exit(0);
                    break;
                default:
                    break;
            }
        }
    }


    private static PaqueteTuristico buscarPaqueteId (Integer id){
        for (PaqueteTuristico touristPackage : clientAgency.listarPaquetes()) {
            if (touristPackage.getId().equals(id)) {
                return touristPackage;
            }
        }
        return null;
    }

    private static Boolean buscarPersonaId (String id){
        for (Persona persona : clientAgency.listarPersonas()) {
            if (persona.getDocumentNumber().equals(id)) {
                return true;
            }
        }
        return false;
    }

    private static Reserva buscarReserva (String id){
        for (Reserva reserva : clientAgency.listarReservas()) {
            if (reserva.getIdPerson().equals(id)) {
                if(buscarPaqueteId(reserva.getTouristPackageId()) != null){
                    return reserva;
                }
            }
        }
        return null;
    }

}