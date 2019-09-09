package co.com.assessment.weather.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Daily {

  @JsonProperty("time")
  public String time;
  @JsonProperty("summary")
  public String summary;
  @JsonProperty("icon")
  public String icon;
  @JsonProperty("data")
  public List<Day> data = new ArrayList<>();

}