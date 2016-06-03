/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.unidavi.gamepong;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Servidor {

    private int __contador = 0;
    
    public static void main(String[] args) throws IOException {
        // inicia o servidor
        new Servidor(5555).executa();
    }

    private int porta;
    private List<PrintStream> clientes;

    public Servidor(int porta) {
        this.porta = porta;
        this.clientes = new ArrayList<PrintStream>();
    }

    public void executa() throws IOException {
        new Thread(new ServerStatus(this)).start();
        ServerSocket servidor = new ServerSocket(this.porta);
        System.out.println("Porta 5555 aberta para novas conexões!");

        while (true) {
            // aceita um cliente
            Socket cliente = servidor.accept();
            System.out.println("Nova conexão do cliente no IP "
                    + cliente.getInetAddress().getHostAddress()
            );

            // adiciona saida do cliente à lista
            PrintStream ps = new PrintStream(cliente.getOutputStream());
            this.clientes.add(ps);

            // cria tratador de cliente numa nova thread
            TrataCliente tc = new TrataCliente(cliente.getInputStream(), this);
            tc.setId(++__contador);
            ps.println("seuId;" + __contador);
            new Thread(tc).start();
        }

    }

    public void distribuiMensagem(String msg) {
        // envia msg para todo mundo
        for (PrintStream cliente : this.clientes) {
            cliente.println(msg);
        }
    }
}
