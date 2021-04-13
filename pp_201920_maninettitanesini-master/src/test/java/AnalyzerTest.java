import com.luciaandres.analysis.Analysis;
import com.luciaandres.analysis.Analyzer;
import com.luciaandres.entities.ReducedActivity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AnalyzerTest
{
    List<ReducedActivity> activities = new ArrayList<>();
    List<ReducedActivity> activities2 = new ArrayList<>();

    @BeforeEach
    void start()
    {

        activities.add(new ReducedActivity("Id", "Name", "Description", new ArrayList(Arrays.asList("type1", "type2")),true, "Region"));
        activities.add(new ReducedActivity("Id 2", "Name 2", "Description 2", new ArrayList(Arrays.asList("type1", "type3")),false, "Region 2"));
        activities.add(new ReducedActivity("Id 3", "Name 3", "Description 3", new ArrayList(Arrays.asList("type1", "type2", "type3")),true, "Region 3"));

    }

    @Test
    void testNotNull()
    {
        Analyzer analyzer = new Analyzer(activities);
        Analysis analysis = analyzer.analyze();


        assertNotNull(analysis);
    }

    @Test
    void testIsEqual()
    {
        Analyzer analyzer1 = new Analyzer(activities);
        Analyzer analyzer2 = new Analyzer(activities);

        assertEquals(analyzer1, analyzer2);
    }
    @Test
    void testNotEqual()
    {

        activities2.add(new ReducedActivity(null, null, "Description", new ArrayList(Arrays.asList("type1", "type2")),true, "Region"));

        Analyzer analyzer1 = new Analyzer(activities);
        Analyzer analyzer2 = new Analyzer(activities2);

        assertNotEquals(analyzer1, analyzer2);
    }


}
