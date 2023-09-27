package org.stellar.sdkandroidspi;

import org.stellar.sdk.Base32;

class AndroidBase32 implements Base32 {
  public static final ApacheCodec.Base32 base32 = new ApacheCodec.Base32();

  @Override
  public byte[] encode(byte[] data) {
    return base32.encode(data);
  }

  @Override
  public byte[] decode(String data) {
    return base32.decode(data);
  }

  @Override
  public byte[] decode(byte[] data) {
    return base32.decode(data);
  }
}
