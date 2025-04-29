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

    public void iniciarSesion(String dni, String password, LoginCallback callback) {
        FirebaseDatabase.getInstance().getReference("usuarios").child(dni)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            String storedPassword = snapshot.child("password").getValue(String.class);
                            if (storedPassword != null && storedPassword.equals(password)) {
                                callback.onResult(true);
                            } else {
                                callback.onResult(false);
                            }
                        } else {
                            callback.onResult(false);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        callback.onResult(false);
                    }
                });
    }
}
