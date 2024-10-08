package com.arriola.loginpreferencia.model;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Usuario {
    public String Documento;
    public String Apellido;
    public String Nombre;
    public String Mail;
    public String Password;


    public Usuario() {
    }

    public Usuario(String documento, String apellido, String nombre, String mail, String password) {
        Documento = documento;
        Apellido = apellido;
        Nombre = nombre;
        Mail = mail;
        Password = password;
    }

    public Usuario(String datos){
        List<String> datosRecuperados = Arrays.asList(datos.split(";"));
        Documento = datosRecuperados.get(0);
        Apellido = datosRecuperados.get(1);
        Nombre = datosRecuperados.get(2);
        Mail = datosRecuperados.get(3);
        Password = datosRecuperados.get(4);
    }

    public String getDocumento() {
        return Documento;
    }

    public void setDocumento(String documento) {
        Documento = documento;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getMail() {
        return Mail;
    }

    public void setMail(String mail) {
        Mail = mail;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
