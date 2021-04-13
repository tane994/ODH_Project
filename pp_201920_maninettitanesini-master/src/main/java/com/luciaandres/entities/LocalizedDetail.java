package com.luciaandres.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LocalizedDetail {

    @JsonSetter("de")
    Detail detailDe;
    @JsonSetter("en")
    Detail detailEn;
    @JsonSetter("it")
    Detail detailIt;

    public Detail getGermanDetail() {
        return detailDe;
    }

    public Detail getEnglishDetail() {
        return detailEn;
    }

    public Detail getItalianDetail() {
        return detailIt;
    }


}
