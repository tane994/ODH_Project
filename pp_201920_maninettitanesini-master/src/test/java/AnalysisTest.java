import com.luciaandres.analysis.Analysis;
import com.luciaandres.analysis.RegionWithActivities;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class AnalysisTest
{
    TreeSet<String> regions = new TreeSet<>(Arrays.asList("region1", "region2", "region3", "region4"));
    RegionWithActivities regionWithMostActivities = new RegionWithActivities(5, regions);
    RegionWithActivities regionWithLeastActivities = new RegionWithActivities(7, regions);
    Map<String, Long> activitiesTypes = new HashMap<>();
    List<String> trackedActivityIds = new ArrayList<>();

    Analysis analysis1 = new Analysis(regionWithMostActivities, regionWithLeastActivities,  activitiesTypes,  trackedActivityIds);


    @Test
    void testEquals()
    {
        Analysis analysis2 = new Analysis(regionWithMostActivities, regionWithLeastActivities, activitiesTypes, trackedActivityIds);

        assertEquals(analysis1, analysis2);
    }

    @Test
    void testNotNull()
    {
        assertNotNull(analysis1);
    }

    @Test
    void testDiffActTypes()
    {
        Map<String, Long> activitiesTypes2 = new HashMap<>();
        activitiesTypes2.put("RandomKey", 8L);
        Analysis analysis2  = new Analysis(regionWithMostActivities, regionWithLeastActivities, activitiesTypes2, trackedActivityIds);

        assertNotEquals(analysis1, analysis2);

    }

    @Test
    void testDiffRegionActivities()
    {
        RegionWithActivities regionWithLeastActivities2 = new RegionWithActivities(3, regions);
        Analysis analysis2 = new Analysis(regionWithMostActivities, regionWithLeastActivities2, activitiesTypes, trackedActivityIds);

        assertNotEquals(analysis1, analysis2);

    }

    @Test
    void testDiffTrackedActivityIds()
    {
        List<String> trackedActivityIds2 = new ArrayList<>();
        trackedActivityIds2.add(0, "elem");

        Analysis analysis2 = new Analysis(regionWithMostActivities, regionWithLeastActivities, activitiesTypes, trackedActivityIds2);

        assertNotEquals(analysis1, analysis2);

    }

}
