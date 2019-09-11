package co.com.assessment.weather.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.IOException;
import lombok.Data;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.BufferedSink;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Weather {

  @JsonProperty("timezone")
  private String timezone;
  @JsonProperty("currently")
  private Currently currently;
  @JsonProperty("daily")
  private Daily daily;
  @JsonProperty("offset")
  private Integer offset;

}