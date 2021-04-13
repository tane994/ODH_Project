package com.luciaandres.entities;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.List;
import java.util.Objects;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ReducedActivity {
    String id;
    String name;
    String description;
    List<String> types;
    boolean hasGPSTrack;
    String region;


    public ReducedActivity(String id, String name, String description, List<String> types, boolean hasGPSTrack, String region) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.types = types;
        this.hasGPSTrack = hasGPSTrack;
        this.region = region;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getTypes() {
        return types;
    }

    public boolean isHasGPSTrack() {
        return hasGPSTrack;
    }

    public String getRegion() {
        return region;
    }

    @Override
    public String toString() {
        return "ReducedActivity {" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", types=" + types +
                ", hasGPSTrack=" + hasGPSTrack +
                ", region='" + region + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReducedActivity)) return false;
        ReducedActivity that = (ReducedActivity) o;
        return isHasGPSTrack() == that.isHasGPSTrack() &&
                getId().equals(that.getId()) &&
                getName().equals(that.getName()) &&
                getDescription().equals(that.getDescription()) &&
                getTypes().equals(that.getTypes()) &&
                getRegion().equals(that.getRegion());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getDescription(), getTypes(), isHasGPSTrack(), getRegion());
    }
}