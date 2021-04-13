package com.luciaandres.analysis;

import java.util.Objects;
import java.util.TreeSet;


public class RegionWithActivities
{
    private long numberOfActivities;
    private TreeSet<String> regionIds;

    /*
     * Constructor of the class with two parameters:
     *  numberOfActivities which indicates the number of activities of the region
     *  regionIds which indicates the id of each region
     */
    public RegionWithActivities(long numberOfActivities, TreeSet<String> regionIds)
    {
        this.numberOfActivities = numberOfActivities;
        this.regionIds = regionIds;
    }

    public long getNumberOfActivities() {
        return numberOfActivities;
    }

    public void setNumberOfActivities(long numberOfActivities) {
        this.numberOfActivities = numberOfActivities;
    }

    public TreeSet<String> getRegionIds() {
        return regionIds;
    }

    public void setRegionIds(TreeSet<String> regionIds) {
        this.regionIds = regionIds;
    }

    @Override
    public String toString() {
        return "RegionWithActivities{" +
                "numberOfActivities=" + numberOfActivities +
                ", regionIds=" + regionIds +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RegionWithActivities)) return false;
        RegionWithActivities that = (RegionWithActivities) o;
        return getNumberOfActivities() == that.getNumberOfActivities() &&
                getRegionIds().equals(that.getRegionIds());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNumberOfActivities(), getRegionIds());
    }
}

