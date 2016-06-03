/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.unidavi.gamepong;

import java.io.InputStream;
import java.util.Scanner;


public class TrataCliente implements Runnable {
 
   private int id;
   private InputStream cliente;
   private Servidor servidor;
 
   public TrataCliente(InputStream cliente, Servidor servidor) {
     this.cliente = cliente;
     this.servidor = servidor;
   }
 
   public void run() {
     // quando chegar uma msg, distribui pra todos
     Scanner s = new Scanner(this.cliente);
     while (s.hasNextLine()) {
         String msg = s.nextLine();
         System.out.println(msg);
         servidor.distribuiMensagem(msg);
     }
     s.close();
   }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
 }