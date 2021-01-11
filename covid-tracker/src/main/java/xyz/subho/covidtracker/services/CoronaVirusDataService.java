package xyz.subho.covidtracker.services;


import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import xyz.subho.covidtracker.client.CoronaVirusDataClient;
import xyz.subho.covidtracker.models.LocationStats;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class CoronaVirusDataService {

    @Autowired
    private CoronaVirusDataClient coronaVirusDataClient;

    private List<LocationStats> allStats = new ArrayList<>();

    public List<LocationStats> getAllStats() {
        return allStats;
    }

    CoronaVirusDataService(CoronaVirusDataClient coronaVirusDataClient)
    {
        this.coronaVirusDataClient = coronaVirusDataClient;
    }

    @PostConstruct
    @Scheduled(cron = "0 0/30 * * * *")
    public void fetchVirusData() throws IOException, InterruptedException {
        List<LocationStats> newStats = new ArrayList<>();

        StringReader csvBodyReader = new StringReader(coronaVirusDataClient.getVirusData());

        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);

        readCSV(newStats, records);
        this.allStats = newStats;
    }

    private void readCSV(List<LocationStats> newStats, Iterable<CSVRecord> records) {
        for (CSVRecord record : records) {
            LocationStats locationStat = new LocationStats();

            locationStat.setState(record.get("Province/State"));
            locationStat.setCountry(record.get("Country/Region"));

            int latestCases = Integer.parseInt(record.get(record.size() - 1));
            int prevDayCases = Integer.parseInt(record.get(record.size() - 2));

            locationStat.setLatestTotalCases(latestCases);
            locationStat.setDiffFromPrevDay(latestCases - prevDayCases);

            newStats.add(locationStat);
        }
    }
}
