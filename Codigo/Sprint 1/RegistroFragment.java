package com.example.brailleexpress.ui.Registro;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.brailleexpress.R;
import com.google.android.material.textfield.TextInputEditText;

public class RegistroFragment extends Fragment {

    private RegistroViewModel registroViewModel;

    private TextInputEditText inputNombre, inputApellido, inputEdad, inputDNI, inputPassword;
    private RadioGroup radioGroupGenero;
    private Button btnRegistrar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_registro, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        registroViewModel = new ViewModelProvider(this).get(RegistroViewModel.class);

        inputNombre = view.findViewById(R.id.Nombre);
        inputApellido = view.findViewById(R.id.Apellidos);
        inputEdad = view.findViewById(R.id.Edad);
        inputDNI = view.findViewById(R.id.Dni);
        inputPassword = view.findViewById(R.id.password);
        radioGroupGenero = view.findViewById(R.id.radioGroupGenero);
        btnRegistrar = view.findViewById(R.id.btnRegistrar);

        btnRegistrar.setOnClickListener(v -> {
            String nombre = inputNombre.getText().toString().trim();
            String apellido = inputApellido.getText().toString().trim();
            String edad = inputEdad.getText().toString().trim();
            String dni = inputDNI.getText().toString().trim();
            String password = inputPassword.getText().toString().trim();

            int selectedGeneroId = radioGroupGenero.getCheckedRadioButtonId();
            String genero = "";
            if (selectedGeneroId != -1) {
                RadioButton selectedRadio = view.findViewById(selectedGeneroId);
                genero = selectedRadio.getText().toString();
            }

            registroViewModel.registrarUsuario(nombre, apellido, edad, genero, dni, password);
        });

        registroViewModel.getRegistroExitoso().observe(getViewLifecycleOwner(), exito -> {
            if (exito != null && exito) {
                Toast.makeText(getContext(), "Registro exitoso", Toast.LENGTH_SHORT).show();
                Navigation.findNavController(view).navigate(R.id.action_RegistroFragment_to_LoginFragment);
            }
        });

        registroViewModel.getMensajeError().observe(getViewLifecycleOwner(), error -> {
            if (!TextUtils.isEmpty(error)) {
                Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
