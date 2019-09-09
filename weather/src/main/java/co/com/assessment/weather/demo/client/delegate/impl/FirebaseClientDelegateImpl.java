package co.com.assessment.weather.demo.client.delegate.impl;

import co.com.assessment.weather.demo.client.delegate.api.FirebaseClientDelegate;
import co.com.assessment.weather.demo.dto.WeatherRequestDTO;
import co.com.assessment.weather.demo.model.Weather;
import co.com.assessment.weather.demo.util.MD5;
import co.com.assessment.weather.demo.util.RestResourceConstants;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class FirebaseClientDelegateImpl extends AbstractCommonClientDelegate implements
    FirebaseClientDelegate {

  @Value("${clent.firebase.backup.url}")
  private String urlBackup;

  public Weather getInfoFromBackupFirebase(WeatherRequestDTO requestDTO) {
    Request request = new Request.Builder()
        .url(buildUrlBackup(requestDTO))
        .get().build();

    try {
      return parseResponse(new OkHttpClient().newCall(request).execute());
    } catch (IOException e) {
      log.error("Error trying to retrieve information in the backup source", e);
    }
    return new Weather();
  }

  private String buildUrlBackup(WeatherRequestDTO requestDTO) {
    return urlBackup.replace(RestResourceConstants.LOCATION, RestResourceConstants.WEATER)
        .concat(MD5.getMD5(requestDTO.getLatitude().concat(requestDTO.getLongitude())));
  }

  public void postInfoFromBackupFirebase(WeatherRequestDTO mainRequest, Weather weather) {

    log.info("Save information in backup");

    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference();

    DatabaseReference weatherLocation = ref.child("test");

    Map<String, Weather> location = new HashMap<>();
    location.put(MD5.getMD5(mainRequest.getLatitude().concat(mainRequest.getLongitude())), weather);
    ref.setValueAsync(location);
    weatherLocation.setValueAsync(location);

  }
}
