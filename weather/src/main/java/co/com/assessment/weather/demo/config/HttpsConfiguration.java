
package co.com.assessment.weather.demo.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.Collections;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import lombok.extern.slf4j.Slf4j;
import okhttp3.CipherSuite;
import okhttp3.ConnectionSpec;
import okhttp3.OkHttpClient;
import okhttp3.TlsVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Configuration
public class HttpsConfiguration {

  @Value("${client.ssl.trust.store.name}")
  private Resource file;

  @Value("${client.ssl.trust.store.pass:changeit}")
  private String credential;

  @Bean
  public OkHttpClient getHttpClient() throws IOException {

    InputStream inputFile = file.getInputStream();

    if (areKeyStoreParametersInValid(inputFile, credential)){
      throw new IllegalArgumentException("Missing or wrong parameter in SSL configuration");
    }
    OkHttpClient.Builder clientHttp = null;
    try {

      String keyStoreType = KeyStore.getDefaultType();
      KeyStore keyStore = KeyStore.getInstance(keyStoreType);
      keyStore.load(inputFile, credential.toCharArray());

      TrustManagerFactory managerFactory = TrustManagerFactory
          .getInstance(TrustManagerFactory.getDefaultAlgorithm());

      managerFactory.init(keyStore);

      SSLContext sslContext = SSLContext.getInstance("TLS");
      sslContext.init(null, managerFactory.getTrustManagers(), null);

      SSLContext.setDefault(sslContext);

      ConnectionSpec spec = new ConnectionSpec.Builder(ConnectionSpec.COMPATIBLE_TLS)
          .tlsVersions(TlsVersion.TLS_1_2, TlsVersion.SSL_3_0)
          .cipherSuites(
              CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256)
          .build();

      TrustManager[] trustManagers = managerFactory.getTrustManagers();

      clientHttp = new OkHttpClient.Builder().sslSocketFactory(sslContext.getSocketFactory(),
          (X509TrustManager) trustManagers[0]).connectionSpecs(Collections.singletonList(spec))
          .hostnameVerifier((hostname, session) -> true);

    } catch (IOException | KeyStoreException | NoSuchAlgorithmException
        | CertificateException | KeyManagementException ex) {
      log.error("Error trying to load SSL configuration, with cause: {}", ex);
    }

    return clientHttp.build();
  }

  private boolean areKeyStoreParametersInValid(InputStream file, String credential) {
    return ObjectUtils.isEmpty(file) || "test".equalsIgnoreCase(credential);
  }

}
