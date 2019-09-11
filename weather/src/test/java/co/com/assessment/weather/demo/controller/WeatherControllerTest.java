package co.com.assessment.weather.demo.controller;

import co.com.assessment.weather.demo.service.api.WeatherService;
import co.com.assessment.weather.demo.util.RestResourceConstants;
import co.com.assessment.weather.demo.util.Sample;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(MockitoJUnitRunner.class)
public class WeatherControllerTest {


  @Mock
  private WeatherService weatherService;

  @InjectMocks
  private WeatherController weatherController;

  private MockMvc mockMvc;

  @Before
  public void setUp() {

    MockitoAnnotations.initMocks(this);
    mockMvc = MockMvcBuilders.standaloneSetup(weatherController).build();

  }

  @Test
  public void givenRequestToGetWeatherWhenTheparameterIsOkThenReturnSuccessResponse()
      throws Exception {

    //when
    ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get(
        RestResourceConstants.WEATHER_RESOURCE.concat(RestResourceConstants.SEPARATOR)
            .concat(Sample.CITY_MADRID)));

    // then
    result.andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isOk());
  }

  @Test
  public void givenRequestToGetWeatherWhenTheParameterIsMissingThenReturnNotFound()
      throws Exception {

    //when
    ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get(
        RestResourceConstants.WEATHER_RESOURCE.concat(RestResourceConstants.SEPARATOR)));

    // then
    result.andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isNotFound());
  }

  @Test
  public void givenRequestToGetWeatherWhenTheParameterIsWrongThenReturnBadRequest()
      throws Exception {

    //when
    ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get(
        RestResourceConstants.WEATHER_RESOURCE.concat(RestResourceConstants.SEPARATOR)
            .concat(Sample.CITY_SANTORINI)));

    // then
    result.andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isBadRequest());
  }

}
