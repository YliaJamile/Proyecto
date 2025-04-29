package com.example.brailleexpress.ui.Registro;

import android.text.TextUtils;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegistroViewModel extends ViewModel {

    private final MutableLiveData<Boolean> registroExitoso = new MutableLiveData<>();
    private final MutableLiveData<String> mensajeError = new MutableLiveData<>();

    public LiveData<Boolean> getRegistroExitoso() {
        return registroExitoso;
    }

    public LiveData<String> getMensajeError() {
        return mensajeError;
    }

    public void registrarUsuario(String nombre, String apellido, String edad, String genero, String dni, String password) {
        if (TextUtils.isEmpty(nombre) || TextUtils.isEmpty(apellido) ||
                TextUtils.isEmpty(edad) || TextUtils.isEmpty(genero) || TextUtils.isEmpty(dni)) {
            mensajeError.setValue("Todos los campos son obligatorios.");
            return;
        }

        DatabaseReference database = FirebaseDatabase.getInstance().getReference("usuarios");

        HashMap<String, Object> datosUsuario = new HashMap<>();
        datosUsuario.put("Nombre", nombre);
        datosUsuario.put("Apellidos", apellido);
        datosUsuario.put("Edad", edad);
        datosUsuario.put("Genero", genero);
        datosUsuario.put("Dni", dni);
        datosUsuario.put("Password", password);

        database.child(dni).setValue(datosUsuario)
                .addOnSuccessListener(unused -> registroExitoso.setValue(true))
                .addOnFailureListener(e -> mensajeError.setValue("Error al registrar: " + e.getMessage()));
    }
}
