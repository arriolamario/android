package com.arriola.fotoperfil.request;

import android.content.Context;
import android.net.Uri;
import android.widget.Toast;

import com.arriola.fotoperfil.model.Usuario;

import java.io.*;
import java.util.Base64;

public class ApiClient {

    private static final String nameFile = "usuario.dat";
    public static void Guardar(Context context, Usuario usuario){
        File archivo = new File(context.getFilesDir(), nameFile);
        try (FileOutputStream fos = new FileOutputStream(archivo);
             BufferedOutputStream bos = new BufferedOutputStream(fos);
             DataOutputStream dos = new DataOutputStream(bos)) {

            dos.writeUTF(usuario.getDocumento());
            dos.writeUTF(usuario.getApellido());
            dos.writeUTF(usuario.getNombre());
            dos.writeUTF(usuario.getMail());
            dos.writeUTF(usuario.getPassword());
            dos.writeUTF(usuario.getFoto());

        } catch (FileNotFoundException e) {
            Toast.makeText(context, "Error de archivo", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(context, "Error IO", Toast.LENGTH_SHORT).show();
        }
    }

    public static Usuario Leer(Context context){
        Usuario usuario = null;
        File archivo = new File(context.getFilesDir(), nameFile);

        try (FileInputStream fis = new FileInputStream(archivo);
             BufferedInputStream bis = new BufferedInputStream(fis);
             DataInputStream dis = new DataInputStream(bis);){

            usuario = new Usuario();
            usuario.setDocumento(dis.readUTF());
            usuario.setApellido(dis.readUTF());
            usuario.setNombre(dis.readUTF());
            usuario.setMail(dis.readUTF());
            usuario.setPassword(dis.readUTF());
            usuario.setFoto(dis.readUTF());

        } catch (FileNotFoundException fnfe) {
            Toast.makeText(context, "Error de Archivo", Toast.LENGTH_SHORT).show();
        } catch (IOException ioe) {
            Toast.makeText(context, "Error de IO", Toast.LENGTH_SHORT).show();
        }
        return usuario;
    }

    public static boolean Login(Context context, String mail, String password){
        boolean result = false;
        Usuario usuario = Leer(context);

        if(usuario != null && usuario.getMail().equals(mail) && usuario.getPassword().equals(password)){
            result = true;
        }
        return result;
    }
}
