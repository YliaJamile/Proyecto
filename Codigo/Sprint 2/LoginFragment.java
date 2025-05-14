package com.example.brailleexpress.ui.Login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import com.example.brailleexpress.R;
import com.example.brailleexpress.databinding.FragmentLoginBinding;

public class LoginFragment extends Fragment {

    private FragmentLoginBinding binding;
    private LoginViewModel loginViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        // Configuración del botón de iniciar sesión
        binding.btnLogin.setOnClickListener(view -> {
            String dni = binding.Dni.getText().toString().trim();
            String password = binding.password.getText().toString().trim();

            // Verificamos si algún campo está vacío
            if (dni.isEmpty() || password.isEmpty()) {
                // Mostramos un mensaje de advertencia si los campos no están completos
                Toast.makeText(getContext(), "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }

            // Llamamos al metodo iniciarSesion del ViewModel para autenticar al usuario
            loginViewModel.iniciarSesion(dni, password, isExitoso -> {
                if (isExitoso) {
                    // Llamamos al metodo iniciarSesion del ViewModel para autenticar al usuario
                    NavHostFragment.findNavController(LoginFragment.this)
                            .navigate(R.id.nav_Traductor);
                } else {
                    // Si las credenciales son incorrectas, mostramos un mensaje de error
                    Toast.makeText(getContext(), "DNI o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                }
            });
        });
        // Configuración del link para ir al fragmento de registro
        binding.linkRegistro.setOnClickListener(v -> {
            NavHostFragment.findNavController(LoginFragment.this)
                    .navigate(R.id.nav_Registro);
        });
        // Retornamos la vista raíz del fragment para que se muestre en la interfaz
        return binding.getRoot();

    }
}
