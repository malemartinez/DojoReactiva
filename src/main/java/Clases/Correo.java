package Clases;

import java.util.Objects;

public class Correo {

    private String username;
    private String Domino;
    private String nombre;
    private boolean enviado;
    private String Asunto;
    private String CorreoCompleto;

    public String getCorreoCompleto() {
        return CorreoCompleto;
    }

    public void setCorreoCompleto(String correoCompleto) {
        CorreoCompleto = correoCompleto;
    }

    public Correo(String username, String domino, String nombre, boolean enviado, String asunto) {
        this.username = username;
        Domino = domino;
        this.nombre = nombre;
        this.enviado = enviado;
        Asunto = asunto;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDomino() {
        return Domino;
    }

    public void setDomino(String domino) {
        Domino = domino;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isEnviado() {
        return enviado;
    }

    public void setEnviado(boolean enviado) {
        this.enviado = enviado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Correo correo = (Correo) o;
        return username.equals(correo.username) && Domino.equals(correo.Domino) && nombre.equals(correo.nombre) && Asunto.equals(correo.Asunto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, Domino, nombre, Asunto);
    }

    @Override
    public String toString() {
        return "Correo{" +
                "username='" + username + '\'' +
                ", Domino='" + Domino + '\'' +
                ", nombre='" + nombre + '\'' +
                ", enviado=" + enviado +
                ", Asunto='" + Asunto + '\'' +
                ", CorreoCompleto='" + CorreoCompleto + '\'' +
                '}';
    }
}
