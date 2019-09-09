package co.com.assessment.weather.demo.util;

import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;

public class MD5 {

  public static String getMD5(String source) {
    String result = null;
    MessageDigest md;
    try {
      md = MessageDigest.getInstance("MD5");
      md.update(source.getBytes(Charset.forName("UTF-8")));
      result = String.format(Locale.ROOT, "%032x", new BigInteger(1, md.digest()));
    } catch (NoSuchAlgorithmException exception) {
      throw new IllegalStateException(exception);
    }
    return result;
  }

}
