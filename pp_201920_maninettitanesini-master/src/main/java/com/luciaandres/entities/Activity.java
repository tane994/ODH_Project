package com.luciaandres.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.luciaandres.Formatter;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Activity {



    private String name;
    private String description;
    private List<String> type;
    private boolean hasGPSTrack;
    private String region;
    private String regionId;


    @JsonSetter("ODHTags")
    Type[] types;
    @JsonSetter("GpsTrack")
    List<GpsTrack> gpsTrack;
    @JsonSetter("GpsInfo")
    List<GpsInfo> gpsInfo;
    @JsonSetter("GpsPoints")
    GpsPoints gpsPoints;
    @JsonSetter("Detail")
    LocalizedDetail localizedDetail;
    @JsonSetter("LocationInfo")
    LocationInfo locationInfo;
    @JsonSetter("Id")
    private String id;


    public boolean hasGpsTrack() {

        return (gpsTrack.size() != 0) || (gpsInfo.size() != 0) || (gpsPoints.getPosition() != null);

    }


    public String getDesc() {
        String desc = (localizedDetail.getEnglishDetail() != null) ? ((localizedDetail.getEnglishDetail()).getDescription()) : ((localizedDetail.getItalianDetail() != null) ? (localizedDetail.getItalianDetail()).getDescription() : (localizedDetail.getGermanDetail()).getDescription());
        return Formatter.cleanHtmlFormatting(desc);
    }


    public String getName() {

        String name;
        if (localizedDetail.getEnglishDetail() != null)
            name = (localizedDetail.getEnglishDetail()).getName();
        else if ((localizedDetail.getItalianDetail() != null))
            name = (localizedDetail.getItalianDetail()).getName();
        else name = (localizedDetail.getGermanDetail()).getName();
        return Formatter.cleanHtmlFormatting(name);
    }

    public String getLocalizedName() {
        LocalizedName name = locationInfo.getRegionInfo().getName();
        String region;
        if ((region = name.getEnglishName()) == null) {
            if ((region = name.getItalianName()) == null) {
                region = name.getGermanName();
            }
        }

        return region;
    }

    public ArrayList<String> getTypes() {
        ArrayList<String> typeDesc = new ArrayList<>();

        for (Type type : types) {

            typeDesc.add(type.getId());

        }

        return typeDesc;
    }

    public String getRegionId() {
        String id = locationInfo.getRegionInfo().getId();
        return id;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Activity {" +
                "\n id='" + getId() + '\'' +
                "\n types='" + getTypes() + '\'' +
                "\n hasGPSTrack='" + hasGpsTrack() + '\'' +
                "\n detail= '" + getName() + " " + getDesc() +
                "\n region= '" + getLocalizedName() +
                "\n region Id= '" + getRegionId() + "\n";
    }


}
