package com.arriola.loginpreferencia.request;

import android.content.Context;
import android.content.SharedPreferences;
import com.arriola.loginpreferencia.model.Usuario;

public class ApiClient {

    private static SharedPreferences sp;

    private static SharedPreferences getSharedPreference(Context context){
        if(sp == null){
            sp = context.getSharedPreferences("usuario", 0);
        }
        return sp;
    }

    public static void Guardar(Context context, Usuario usuario){
        SharedPreferences sp = getSharedPreference(context);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("documento", usuario.getDocumento());
        editor.putString("apellido", usuario.getApellido());
        editor.putString("nombre", usuario.getNombre());
        editor.putString("email", usuario.getMail());
        editor.putString("password", usuario.getPassword());

        editor.apply();
    }

    public static Usuario Leer(Context context){
        SharedPreferences sp = getSharedPreference(context);

        String documento = sp.getString("documento", null);
        String apellido = sp.getString("apellido", null);
        String nombre = sp.getString("nombre", null);
        String email = sp.getString("email", null);
        String password = sp.getString("password", null);
        Usuario usuario = null;
        if(documento != null && apellido != null && nombre != null && email != null && password != null){
            usuario = new Usuario();
            usuario.setDocumento(documento);
            usuario.setApellido(apellido);
            usuario.setNombre(nombre);
            usuario.setMail(email);
            usuario.setPassword(password);
        }
        return usuario;
    }

    public static boolean Login(Context context, String mail, String password){
        SharedPreferences sp = getSharedPreference(context);

        String email = sp.getString("email", null);
        String passwordSP = sp.getString("password", null);

        if((email != null) && (passwordSP != null) && email.equals(mail) && passwordSP.equals(password)){
            return true;
        }

        return false;
    }
}
