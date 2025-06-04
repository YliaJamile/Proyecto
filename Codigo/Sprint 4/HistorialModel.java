package com.example.brailleexpress.data.model;

public class HistorialModel {
    private String textoOriginal;
    private String traduccionBraille;
    private String fechaHora;
    private String idDNI;

    public HistorialModel() {
    }

    public HistorialModel(String textoOriginal, String traduccionBraille, String fechaHora, String idDNI) {
        this.textoOriginal = textoOriginal;
        this.traduccionBraille = traduccionBraille;
        this.fechaHora = fechaHora;
        this.idDNI = idDNI;
    }

    public String getTextoOriginal() {
        return textoOriginal;
    }

    public void setTextoOriginal(String textoOriginal) {
        this.textoOriginal = textoOriginal;
    }

    public String getTraduccionBraille() {
        return traduccionBraille;
    }

    public void setTraduccionBraille(String traduccionBraille) {
        this.traduccionBraille = traduccionBraille;
    }

    public String getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(String fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getIdDNI() {
        return idDNI;
    }

    public void setIdDNI(String idDNI) {
        this.idDNI = idDNI;
    }
}