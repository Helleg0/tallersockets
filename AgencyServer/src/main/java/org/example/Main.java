package org.example;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

@Slf4j
public class Main {
    public static void main(String[] args) {
        int puerto = 2222;
        AgencyServer AgencyServer = new AgencyServer();
        try (ServerSocket serverSocket = new ServerSocket(puerto)) {
            log.info("Encendido, sperando conexión");
            while (true) {
                // Se obtiene la conexión del cliente
                Socket clienteSocket = serverSocket.accept();
                log.info("Usuario conectado");
                ThreadClient hilo = new ThreadClient(clienteSocket, AgencyServer);
                new Thread(hilo).start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}