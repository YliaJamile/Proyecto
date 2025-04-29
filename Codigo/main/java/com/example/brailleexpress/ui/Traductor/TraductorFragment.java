package com.example.brailleexpress.ui.Traductor;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.brailleexpress.databinding.FragmentTraductorBinding;

public class TraductorFragment extends Fragment {

    private FragmentTraductorBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        TraductorViewModel homeViewModel =
                new ViewModelProvider(this).get(TraductorViewModel.class);

        binding = FragmentTraductorBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}