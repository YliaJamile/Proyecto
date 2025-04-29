package com.example.brailleexpress.ui.Login;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.example.brailleexpress.databinding.FragmentLoginBinding;
import com.example.brailleexpress.MainActivity;

public class LoginFragment extends Fragment {

    private FragmentLoginBinding binding;
    private LoginViewModel loginViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        binding.btnLogin.setOnClickListener(view -> {
            String dni = binding.Dni.getText().toString().trim();
            String password = binding.password.getText().toString().trim();

            if (dni.isEmpty() || password.isEmpty()) {
                Toast.makeText(getContext(), "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }

            loginViewModel.iniciarSesion(dni, password, isExitoso -> {
                if (isExitoso) {
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    startActivity(intent);
                    requireActivity().finish();
                } else {
                    Toast.makeText(getContext(), "DNI o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                }
            });
        });

        return binding.getRoot();
    }
}
