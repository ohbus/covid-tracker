package xyz.subho.covidtracker.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import xyz.subho.covidtracker.models.LocationStats;
import xyz.subho.covidtracker.services.CoronaVirusDataService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MainController.class)
class MainControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CoronaVirusDataService coronaVirusDataService;

    @Test
    void shouldBe200AndReturnModelWhenHomeAPICalledSuccessfully() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        List<LocationStats> locationStatsList = mapper.readValue(readStatsFromFile(), new TypeReference<List<LocationStats>>() { });
        when(coronaVirusDataService.getAllStats()).thenReturn(locationStatsList);

        mockMvc.perform(get("/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(model().attribute("locationStats", locationStatsList))
                .andExpect(model().attribute("totalReportedCases", 87186540))
                .andExpect(model().attribute("totalNewCases", 780613))
                .andExpect(view().name("index"));

        verify(coronaVirusDataService, times(1)).getAllStats();
    }

    private String readStatsFromFile()
            throws IOException {
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br
                     = new BufferedReader(new InputStreamReader(new ClassPathResource("AllStats.txt").getInputStream()))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line);
            }
        }
        return resultStringBuilder.toString();
    }
}