/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mensajes;


/**
 *
 * @author USER
 */
public class Mensaje {

    int codigo;
    String contenido;

    public Mensaje(int codigo, String contenido) {
        this.codigo = codigo;
        this.contenido = contenido;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    @Override
    public String toString() {
        return "Mensaje{" + "codigo=" + codigo + ", contenido=" + contenido + '}';
    }
}
