package br.edu.unidavi.gamepong;

import java.io.InputStream;
import java.util.Scanner;

public class Recebedor implements Runnable {

    public int id;
    
    GeradorPonto gp = new GeradorPonto();

    private InputStream servidor;

    public Recebedor(InputStream servidor) {
        this.servidor = servidor;
    }

    public void run() {
        // recebe msgs do servidor e imprime na tela

        Scanner s = new Scanner(this.servidor);
        while (s.hasNextLine()) {
            String resposta = s.nextLine();
            String respostaSeparada[] = resposta.split(";");
            if (respostaSeparada[0].equals("gamer01")) {
                gp.setBarra01(respostaSeparada[1]);
            } 
            else if (respostaSeparada[0].equals("gamer02")) {
                gp.setBarra02(respostaSeparada[1]);
            }
            else if (respostaSeparada[0].equals("bola01")) {
                gp.setBola01x(respostaSeparada[1]);
                gp.setBola01y(respostaSeparada[2]);
            } 
            else if (respostaSeparada[0].equals("pontos")) {
                gp.setPontuacaoa(respostaSeparada[1]);
                gp.setPontuacaob(respostaSeparada[2]);
            }
            else if (respostaSeparada[0].equals("seuId")) {
                id = Integer.parseInt(respostaSeparada[1]);
            }
        }
    }
}
