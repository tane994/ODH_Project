import com.luciaandres.analysis.RegionWithActivities;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.*;

public class RegionWithActivitiesTest
{
    TreeSet<String> regionsIDs = new TreeSet<>(Arrays.asList("region1", "region2", "region3", "region4"));

    @Test
    @DisplayName("New instantion with valid parameter.")
    void create()
    {
        assertDoesNotThrow(() -> new RegionWithActivities(10, regionsIDs));
    }

    @Test
    void testEquals()
    {
        RegionWithActivities regionWithActivities1 = new RegionWithActivities(4, regionsIDs);
        RegionWithActivities regionWithActivities2 = new RegionWithActivities(4, regionsIDs);

        assertEquals(regionWithActivities1, regionWithActivities2);
    }

    @Test
    void testDiffNumActivities()
    {
        RegionWithActivities regionWithActivities1 = new RegionWithActivities(7, regionsIDs);
        RegionWithActivities regionWithActivities2 = new RegionWithActivities(5, regionsIDs);

        assertNotEquals(regionWithActivities1, regionWithActivities2);
    }

    @Test
    void testDiffRegions()
    {
        RegionWithActivities regionWithActivities1 = new RegionWithActivities(5, regionsIDs);
        RegionWithActivities regionWithActivities2 = new RegionWithActivities(5, null);

        assertNotEquals(regionWithActivities1, regionWithActivities2);
    }

}
