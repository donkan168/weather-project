package co.com.assessment.weather.demo;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;



@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {WeatherApplication.class})
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class WeatherApplicationTests {

  @Autowired
  private WeatherApplication weatherApplication;

  @Test
  public void contextLoads() {
    Assert.assertNotNull(weatherApplication);
  }

}
