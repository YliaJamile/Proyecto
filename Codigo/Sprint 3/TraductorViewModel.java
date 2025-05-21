package com.example.brailleexpress.ui.Traductor;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.brailleexpress.data.model.BrailleTraductorModel;

// Clase TraductorViewModel que extiende de ViewModel, usada para mantener y gestionar datos relacionados con la interfaz de usuario de manera independiente del ciclo de vida de la actividad o fragmento.
public class TraductorViewModel extends ViewModel {

    // LiveData para almacenar y observar el texto original ingresado por el usuario.
    private final MutableLiveData<String> textoOriginal = new MutableLiveData<>();

    // LiveData para almacenar y observar el texto traducido a Braille.
    private final MutableLiveData<String> textoBraille = new MutableLiveData<>();

    // Instancia del modelo que contiene la lógica de conversión a Braille.
    private final BrailleTraductorModel brailleTraductorModel = new BrailleTraductorModel();

    // Metodo público para establecer el texto original desde la vista.
    // Al establecer el texto, automáticamente se llama a la función de traducción.
    public void setTextoOriginal(String texto) {
        textoOriginal.setValue(texto);  // Actualiza el texto original
        traducirTextoABraille(texto);   // Traduce el texto al formato Braille
    }

    // Metodo para exponer el texto original como LiveData para ser observado por la UI.
    public LiveData<String> getTextoOriginal() {
        return textoOriginal;
    }

    // Metodo para exponer el texto traducido a Braille como LiveData.
    public LiveData<String> getTextoBraille() {
        return textoBraille;
    }

    // Metodo privado que realiza la conversión del texto original a Braille.
    // Utiliza el modelo BrailleTraductorModel para obtener el resultado.
    private void traducirTextoABraille(String texto) {
        String resultado = brailleTraductorModel.convertirTextoABraille(texto); // Llama al modelo para convertir
        textoBraille.setValue(resultado); // Actualiza el LiveData con el resultado traducido
    }
}