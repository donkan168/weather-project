package co.com.assessment.weather.demo.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import java.io.IOException;
import java.io.InputStream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class FireBaseConfiguration {

  @Value("${clent.firebase.file}")
  private InputStream securityFirebase;

  @Value("${clent.firebase.url}")
  private String urlBaseFirebase;

  @Bean
  public void loadFireBaseConfiguration(){
    try {

     FirebaseOptions options = new FirebaseOptions.Builder()
          .setCredentials(GoogleCredentials.fromStream(securityFirebase))
          .setDatabaseUrl(urlBaseFirebase)
          .build();

      FirebaseApp.initializeApp(options);

    } catch (IOException e) {
      log.error("Error trying to save information in the backup source", e);
    }
  }
}
