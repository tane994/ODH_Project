package com.luciaandres.analysis;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Analysis
{
  private RegionWithActivities regionWithMostActivities;
  private RegionWithActivities regionWithLeastActivities;
  private Map<String, Long> activitiesTypes;
  private List <String> trackedActivityIds;

  /*
  * Constructor with 4 parameters:
  * @regionWithMostActivities       Indicates the region ID of the region with the most activities.
  * @regionWithLeastActivities      Indicates the region ID of the region with the least activities.
  * @activitiesTypes                Indicates the activity types with their number of occurrence.
  * @trackedActivityIds             Indicates the activity IDs of the GPS tracked activities
  *
  */
    public Analysis(RegionWithActivities regionWithMostActivities, RegionWithActivities regionWithLeastActivities, Map<String, Long> activitiesTypes, List<String> trackedActivityIds)
    {
        this.regionWithMostActivities = regionWithMostActivities;
        this.regionWithLeastActivities = regionWithLeastActivities;
        this.activitiesTypes = activitiesTypes;
        this.trackedActivityIds = trackedActivityIds;
    }

    @Override
    public String toString()
    {
        return "Analysis{" +
                "RegionWithMostActivities: " + regionWithMostActivities +
                ", RegionWithLeastActivities: " + regionWithLeastActivities +
                ", activitiesTypes: " + activitiesTypes +
                ", trackedActivityIds: " + trackedActivityIds +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Analysis)) return false;
        Analysis analysis = (Analysis) o;
        return regionWithMostActivities.equals(analysis.regionWithMostActivities) &&
                regionWithLeastActivities.equals(analysis.regionWithLeastActivities) &&
                activitiesTypes.equals(analysis.activitiesTypes) &&
                trackedActivityIds.equals(analysis.trackedActivityIds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(regionWithMostActivities, regionWithLeastActivities, activitiesTypes, trackedActivityIds);
    }
}
