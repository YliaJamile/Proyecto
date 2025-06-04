package com.example.brailleexpress.ui.Traductor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.brailleexpress.databinding.FragmentTraductorBinding;

import java.util.ArrayList;
import java.util.Locale;

public class TraductorFragment extends Fragment {

    private FragmentTraductorBinding binding;
    private TraductorViewModel traductorViewModel;

    private static final int REQUEST_CODE_SPEECH_INPUT = 1;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        traductorViewModel = new ViewModelProvider(this).get(TraductorViewModel.class);
        binding = FragmentTraductorBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Traducción automática al escribir
        binding.txtEntrada.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
                traductorViewModel.setTextoOriginal(s.toString());
            }
            @Override public void afterTextChanged(Editable s) {}
        });

        // Observa el resultado traducido
        traductorViewModel.getTextoBraille().observe(getViewLifecycleOwner(), braille -> {
            binding.txtSalida.setText(braille);
        });

        // 🎤 Botón de micrófono
        binding.btnHablar.setOnClickListener(v -> iniciarReconocimientoVoz());

        return root;
    }

    private void iniciarReconocimientoVoz() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Habla ahora...");

        try {
            startActivityForResult(intent, REQUEST_CODE_SPEECH_INPUT);
        } catch (Exception e) {
            Toast.makeText(getContext(), "Error al iniciar el reconocimiento de voz", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode,
                                 @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SPEECH_INPUT && resultCode == Activity.RESULT_OK && data != null) {
            ArrayList<String> resultados = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            if (resultados != null && resultados.size() > 0) {
                String textoReconocido = resultados.get(0);
                binding.txtEntrada.setText(textoReconocido); // Esto activa automáticamente la traducción
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
