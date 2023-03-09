package ptt;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PTTScraperTest {
    @Test
    void getProductID_shouldReturnCorrectProductID() {
        String title = "Garmin Forerunner 945";
        String expectedProductId = "945";
        String actualProductId = PTTScraper.getProductID(title);
        assertEquals(expectedProductId, actualProductId);
    }

    @Test
    void shouldSkip_shouldReturnTrueForIrrelevantTitle() {
        String title = "徵求 Fitbit Versa 2";
        boolean expected = true;
        boolean actual = PTTScraper.shouldSkip(title);
        assertEquals(expected, actual);
    }

    @Test
    void shouldSkip_shouldReturnFalseForRelevantTitle() {
        String title = "Garmin Forerunner 935";
        boolean expected = false;
        boolean actual = PTTScraper.shouldSkip(title);
        assertEquals(expected, actual);
    }

}