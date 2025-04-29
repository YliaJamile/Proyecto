package com.example.brailleexpress.data.Repositorio;

import androidx.annotation.NonNull;

import com.example.brailleexpress.data.model.UsuarioModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UsuarioRepositorio {

    private final DatabaseReference usuariosRef;

    public UsuarioRepositorio() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        usuariosRef = database.getReference("usuarios");
    }

    public void registrarUsuario(UsuarioModel usuario, String dni,String password, OnUsuarioRegistradoListener listener) {
        usuariosRef.child(dni).setValue(usuario)
                .addOnSuccessListener(aVoid -> listener.onRegistrado())
                .addOnFailureListener(listener::onError);
    }

    public void validarLogin(String dni, OnLoginValidadoListener listener) {
        usuariosRef.child(dni).get().addOnCompleteListener(task -> {
            if (task.isSuccessful() && task.getResult().exists()) {
                UsuarioModel usuario = task.getResult().getValue(UsuarioModel.class);
                if (usuario != null) {
                    listener.onLoginExitoso(usuario);
                } else {
                    listener.onLoginFallido();
                }
            } else {
                listener.onLoginFallido();
            }
        }).addOnFailureListener(listener::onError);
    }

    // Interfaces de retorno de llamada
    public interface OnUsuarioRegistradoListener {
        void onRegistrado();
        void onError(@NonNull Exception e);
    }

    public interface OnLoginValidadoListener {
        void onLoginExitoso(UsuarioModel usuario);
        void onLoginFallido();
        void onError(@NonNull Exception e);
    }
}
