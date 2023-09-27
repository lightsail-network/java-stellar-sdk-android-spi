package org.stellar.sdkandroidspi;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.stellar.sdk.Base64;

class AndroidBase64 implements Base64 {
  private static final Class<?> BASE64_CLASS_ANDROID = getBase64ClassAndroid();

  private static Class<?> getBase64ClassAndroid() {
    try {
      return Class.forName("android.util.Base64");
    } catch (ClassNotFoundException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public String encodeToString(byte[] data) {
    try {
      Method encodeToString =
          BASE64_CLASS_ANDROID.getDeclaredMethod("encodeToString", byte[].class, int.class);
      return (String) encodeToString.invoke(null, data, 2);
    } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public byte[] encode(byte[] data) {
    try {
      Method encode = BASE64_CLASS_ANDROID.getDeclaredMethod("encode", byte[].class, int.class);
      return (byte[]) encode.invoke(null, data, 2);
    } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public byte[] decode(String data) {
    try {
      Method decode = BASE64_CLASS_ANDROID.getDeclaredMethod("decode", String.class, int.class);
      return (byte[]) decode.invoke(null, data, 2);
    } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
      throw new RuntimeException(e);
    }
  }
}
