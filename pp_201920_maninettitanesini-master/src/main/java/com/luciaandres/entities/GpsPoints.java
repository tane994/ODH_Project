package com.luciaandres.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GpsPoints {

    @JsonSetter("position")
    Position position;

    public Position getPosition() {
        return position;
    }
}
