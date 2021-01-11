package xyz.subho.covidtracker.models;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
public class LocationStatsTest {

    @Test
    public void shouldBeReturningLocationStatisWithStateCountryAndLatestTotalCases(){
        LocationStats locationStats = new
                LocationStats();
        locationStats.setCountry("India");
        locationStats.setLatestTotalCases(1234);
        locationStats.setState("Maharastra");

        assertThat(locationStats.toString()).isEqualTo("LocationStats{state='Maharastra', country='India', latestTotalCases=1234}");

    }
}
