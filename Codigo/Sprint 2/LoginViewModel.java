package com.example.brailleexpress.ui.Login;

import androidx.lifecycle.ViewModel;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginViewModel extends ViewModel {

    public interface LoginCallback {
        void onResult(boolean isExitoso);
    }
    // Metodo para iniciar sesión usando el DNI y la contraseña
    public void iniciarSesion(String dni, String password, LoginCallback callback) {
        // Usamos Firebase para obtener los datos del usuario desde la base de datos
        FirebaseDatabase.getInstance().getReference("usuarios").child(dni)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        // Verificamos si el usuario existe en la base de datos
                        if (snapshot.exists()) {
                            // Obtenemos la contraseña almacenada en la base de datos para el usuario con el DNI proporcionado
                            String storedPassword = snapshot.child("Password").getValue(String.class);
                            // Si la contraseña almacenada no es nula y coincide con la contraseña ingresada
                            if (storedPassword != null && storedPassword.equals(password)) {
                                callback.onResult(true);
                            } else {
                                callback.onResult(false);
                            }
                        } else {
                            callback.onResult(false);
                        }
                    }
                    // En caso de error al intentar acceder a la base de datos
                    @Override
                    public void onCancelled(DatabaseError error) {
                        callback.onResult(false);
                    }
                });
    }
}
