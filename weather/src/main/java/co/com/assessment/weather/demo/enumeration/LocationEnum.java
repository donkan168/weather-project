package co.com.assessment.weather.demo.enumeration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.Arrays;
import lombok.Getter;

@Getter
public enum LocationEnum {
  NEWYORK("New york", "40.730610", "73.935242"),
  LONDON("London", "51.509865", "-0.118092"),
  MADRID("London", "40.416775", "-3.703790"),
  BOGOTA("London", "40.416775", "-3.703790"),
  LIVERPOOL("London", "40.416775", "-3.703790");


  private String location;
  private String latitude;
  private String longitude;

  @JsonCreator
  LocationEnum(String location, String latitude, String longitude) {
    this.location = location;
    this.latitude = latitude;
    this.longitude = longitude;
  }

  public static LocationEnum getBankByName(String name) {

    return Arrays.stream(values()).filter(bank -> bank.name().equals(name))
        .findFirst().orElseThrow(IllegalArgumentException::new);
  }

  @JsonValue
  public String toJson() {
    return location;
  }
}
