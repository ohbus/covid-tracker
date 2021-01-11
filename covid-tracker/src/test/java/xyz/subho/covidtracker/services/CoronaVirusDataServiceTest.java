package xyz.subho.covidtracker.services;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class CoronaVirusDataServiceTest {
    @Autowired
    CoronaVirusDataService coronaVirusDataService;

    @Test
    public void shouldBeReturningListOfLocationStats() throws IOException, InterruptedException {
        coronaVirusDataService.fetchVirusData();

        assertThat(coronaVirusDataService.getAllStats()).isNotNull();
    }

}
