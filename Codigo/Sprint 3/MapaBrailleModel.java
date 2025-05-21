package com.example.brailleexpress.data.model;

import java.util.HashMap;
import java.util.Map;

public class MapaBrailleModel {

    private final Map<Character, String> mapaBraille;

    public MapaBrailleModel() {
        mapaBraille = new HashMap<>();

        // Letras (usando representación Unicode Braille ⠁ = U+2801)
        mapaBraille.put('a', "⠁");
        mapaBraille.put('b', "⠃");
        mapaBraille.put('c', "⠉");
        mapaBraille.put('d', "⠙");
        mapaBraille.put('e', "⠑");
        mapaBraille.put('f', "⠋");
        mapaBraille.put('g', "⠛");
        mapaBraille.put('h', "⠓");
        mapaBraille.put('i', "⠊");
        mapaBraille.put('j', "⠚");
        mapaBraille.put('k', "⠅");
        mapaBraille.put('l', "⠇");
        mapaBraille.put('m', "⠍");
        mapaBraille.put('n', "⠝");
        mapaBraille.put('o', "⠕");
        mapaBraille.put('p', "⠏");
        mapaBraille.put('q', "⠟");
        mapaBraille.put('r', "⠗");
        mapaBraille.put('s', "⠎");
        mapaBraille.put('t', "⠞");
        mapaBraille.put('u', "⠥");
        mapaBraille.put('v', "⠧");
        mapaBraille.put('w', "⠺");
        mapaBraille.put('x', "⠭");
        mapaBraille.put('y', "⠽");
        mapaBraille.put('z', "⠵");

        // Números (Braille literario usual usa prefijo de número)
        mapaBraille.put('1', "⠼⠁");
        mapaBraille.put('2', "⠼⠃");
        mapaBraille.put('3', "⠼⠉");
        mapaBraille.put('4', "⠼⠙");
        mapaBraille.put('5', "⠼⠑");
        mapaBraille.put('6', "⠼⠋");
        mapaBraille.put('7', "⠼⠛");
        mapaBraille.put('8', "⠼⠓");
        mapaBraille.put('9', "⠼⠊");
        mapaBraille.put('0', "⠼⠚");

        // Signos de puntuación comunes
        mapaBraille.put('.', "⠲");
        mapaBraille.put(',', "⠂");
        mapaBraille.put(';', "⠆");
        mapaBraille.put(':', "⠒");
        mapaBraille.put('?', "⠦");
        mapaBraille.put('!', "⠖");
        mapaBraille.put('(', "⠶");
        mapaBraille.put(')', "⠶");
        mapaBraille.put('"', "⠦");
        mapaBraille.put('-', "⠤");
        mapaBraille.put('\'', "⠄");
        mapaBraille.put('/', "⠌");
        mapaBraille.put('@', "⠈");
        mapaBraille.put(' ', " "); // Espacio
    }

    /**
     * Devuelve la representación en braille de un carácter dado.
     */
    public String obtenerBraille(char c) {
        c = Character.toLowerCase(c); // Para que sea case-insensitive
        return mapaBraille.getOrDefault(c, "⍰"); // Caracter desconocido
    }

}
