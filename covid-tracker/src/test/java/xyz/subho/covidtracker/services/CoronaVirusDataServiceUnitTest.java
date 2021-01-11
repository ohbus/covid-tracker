package xyz.subho.covidtracker.services;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.support.CronSequenceGenerator;
import xyz.subho.covidtracker.client.CoronaVirusDataClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CoronaVirusDataServiceUnitTest {

    CoronaVirusDataService coronaVirusDataService;

    @Mock
    CoronaVirusDataClient client ;

    @BeforeEach
    void setUp() {
        coronaVirusDataService = new CoronaVirusDataService(client);
    }

    @Test
    public void shouldBeReturningListOfLocationStats() throws IOException, InterruptedException {
        when(client.getVirusData()).thenReturn(readStatsFromFile());

        coronaVirusDataService.fetchVirusData();

        assertThat(coronaVirusDataService.getAllStats()).isNotNull();
    }

    @Test
    public void shouldBeRunningJobOnEvery30minutes() {
        assertThat(new CronSequenceGenerator("0 0/30 * * * *").next(new Date(2012, 6, 1, 9, 00, 00))).isEqualTo(new Date(2012, 6, 1, 9, 30));
    }

    private String readStatsFromFile()
            throws IOException {
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br
                     = new BufferedReader(new InputStreamReader(new ClassPathResource("HttpResponse.csv").getInputStream()))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line);
            }
        }
        return resultStringBuilder.toString();
    }
}
