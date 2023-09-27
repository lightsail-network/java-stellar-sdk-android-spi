package org.stellar.sdkandroidspi;

import org.stellar.sdk.Base32;
import org.stellar.sdk.Base64;
import org.stellar.sdk.spi.SdkProvider;

/**
 * AndroidSdkProvider implements {@link SdkProvider} interface and provides Android specific
 * implementations.
 */
public class AndroidSdkProvider implements SdkProvider {
  @Override
  public Base64 createBase64() {
    return new AndroidBase64();
  }

  @Override
  public Base32 createBase32() {
    return new AndroidBase32();
  }
}
