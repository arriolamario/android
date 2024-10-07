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

        editor.putString(usuario.getMail(), usuario.getDatos());

        editor.apply();
    }

    public static Usuario Leer(Context context, String mail){
        SharedPreferences sp = getSharedPreference(context);

        String usuarioStr = sp.getString(mail,null);

        Usuario usuario = null;
        if(usuarioStr != null){
            usuario = new Usuario(usuarioStr);
        }
        return usuario;
    }

    public static boolean Login(Context context, String mail, String password){
        SharedPreferences sp = getSharedPreference(context);

        String usuarioStr = sp.getString(mail,null);
        boolean result = false;
        if(usuarioStr != null){
            Usuario usuario = new Usuario(usuarioStr);
            if(usuario.getPassword().equals(password)){
                result = true;
            }
        }
        return result;
    }
}
