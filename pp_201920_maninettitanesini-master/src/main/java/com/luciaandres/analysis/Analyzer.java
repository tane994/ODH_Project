package com.luciaandres.analysis;


import com.luciaandres.entities.ReducedActivity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
*Class that perform the analysis on the Activity ArrayList.
* The various steps of the analysis are performed by the methods of this class.
* The outcome is an "analysis" object, which contains the results of the analysis.
 **/
public class Analyzer
{

    private List<ReducedActivity> activities;

    private static Logger logger = LogManager.getLogger();
    public Analyzer(List<ReducedActivity> activities) {

        this.activities = activities;
    }

    public Analysis analyze()
    {
        logger.info("Performing analysis...");

        logger.debug("Counting the number of activities of each region.");
        Map<String, Long> regionalActivities = NumberOfRegionalActivities();

        long minValue = getMinValue(regionalActivities);

        logger.debug("Searching Region with the highest number of activities.");
        TreeSet<String> minRegion = regionalActivities.entrySet().stream()
                .filter(entry -> entry.getValue() == getMinValue(regionalActivities))
                .map(Map.Entry::getKey)
                .collect(Collectors.toCollection(TreeSet::new));

        long maxValue = getMaxValue(regionalActivities);

        logger.debug("Searching Region with the highest number of activities.");
        TreeSet<String> maxRegion = regionalActivities.entrySet().stream()
                .filter(entry -> entry.getValue() == getMaxValue(regionalActivities))
                .map(Map.Entry::getKey)
                .collect(Collectors.toCollection(TreeSet::new));

        /**
        * Computing the list of the region with the min and max number of activities.
        * Casting the TreeSet minRegion and maxRegion to a List.
         **/
        RegionWithActivities regionWithMostActivities = new RegionWithActivities(maxValue, maxRegion);
        RegionWithActivities regionWithLeastActivities = new RegionWithActivities(minValue,  minRegion);

        return new Analysis(regionWithMostActivities, regionWithLeastActivities, getActivitiesType(), getTrackedActivities());
    }

    // Extracting all the IDs of the activities with tracking available
    public List<String> getTrackedActivities()
    {
        logger.debug("Searching Activities with GPS tracking.");
        return  activities.stream()
                .filter(ReducedActivity::isHasGPSTrack)
                .map(ReducedActivity::getId)
                .collect(Collectors.toList());
    }

    // Getting the activity types and counting their occurrence
    public Map <String, Long> getActivitiesType()
    {
        logger.debug("Counting the different types of activities.");
        return activities.stream()
                .flatMap(ac -> ac.getTypes().stream())
                .collect(Collectors.groupingBy(acType -> acType, Collectors.counting()));
    }

    //Getting the number of the minimum activities per region.
    public long getMinValue(Map <String, Long> regionalActivities)
    {
        long minValue = regionalActivities.values().stream()
                        .min(Long::compareTo)
                        .orElse((long) - 1);
        return minValue;

    }

    //Getting the number of the maximum activities per region.
    public long getMaxValue(Map <String, Long> regionalActivities)
    {
        long maxValue = regionalActivities.values().stream()
                .max(Long::compareTo)
                .orElse((long) -1);

        return maxValue;

    }


    //Counting the number of activities in distinct regions.
    public Map<String, Long> NumberOfRegionalActivities()
    {
        return activities.stream()
                .map(ReducedActivity::getRegion)
                .distinct()
                .collect(Collectors.toMap(region -> region, region -> activities.stream()
                        .filter(activity -> activity.getRegion().equals(region))
                        .count()));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Analyzer)) return false;
        Analyzer analyzer = (Analyzer) o;
        return activities.equals(analyzer.activities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(activities);
    }
}
