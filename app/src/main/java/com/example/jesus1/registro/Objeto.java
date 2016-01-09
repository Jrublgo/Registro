package com.example.jesus1.registro;

/**
 * Created by jesus1 on 09/01/2016.
 */
public class Objeto {

    private String emisor;
    private String destinatario;
    private String texto;
    private int nivel_urgencia;

    public Objeto(String emisor, String destinatario, String texto) {
        this.emisor = emisor;
        this.destinatario = destinatario;
        this.texto = texto;
        nivel_urgencia = 0;
    }

    public Objeto(String emisor, String destinatario, int nivel_urgencia, String texto) {
        this.emisor = emisor;
        this.destinatario = destinatario;
        this.nivel_urgencia = nivel_urgencia;
        this.texto = texto;
    }

    public String getEmisor() {
        return emisor;
    }

    public void setEmisor(String emisor) {
        this.emisor = emisor;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public int getNivel_urgencia() {
        return nivel_urgencia;
    }

    public void setNivel_urgencia(int nivel_urgencia) {
        this.nivel_urgencia = nivel_urgencia;
    }

    @Override
    public String toString() {
        return "Objeto{" +
                "emisor='" + emisor + '\'' +
                ", destinatario='" + destinatario + '\'' +
                ", texto='" + texto + '\'' +
                ", nivel_urgencia=" + nivel_urgencia +
                '}';
    }
}
