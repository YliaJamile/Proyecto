package com.example.brailleexpress.data.model;

public class BrailleTraductorModel {

    private MapaBrailleModel mapaBrailleModel;

    public BrailleTraductorModel() {
        mapaBrailleModel = new MapaBrailleModel();
    }

    /**
     * Convierte un texto completo a su representación en Braille,
     * incluyendo prefijos para letras mayúsculas.
     *
     * @param texto El texto a convertir.
     * @return El texto traducido a Braille.
     */
    public String convertirTextoABraille(String texto) {
        StringBuilder resultado = new StringBuilder();

        for (char c : texto.toCharArray()) {
            if (Character.isUpperCase(c)) {
                resultado.append("⠠"); // Prefijo de mayúscula
                c = Character.toLowerCase(c); // Convertir a minúscula para obtener su símbolo Braille
            }

            String simboloBraille = mapaBrailleModel.obtenerBraille(c);
            resultado.append(simboloBraille);
        }

        return resultado.toString();
    }
}
