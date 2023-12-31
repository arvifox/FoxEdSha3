package com.arvifox.libed.ed25519;

import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.Provider;
import java.security.Security;

/**
 * A security {@link Provider} that can be registered via {@link Security#addProvider(Provider)}
 *
 * @author str4d
 */
public class EdDSASecurityProvider extends Provider {

  public static final String PROVIDER_NAME = "EdDSA";
  private static final long serialVersionUID = 1210027906682292307L;

  public EdDSASecurityProvider() {
    super(PROVIDER_NAME, 0.3 /* should match POM major.minor version */,
        "str4d " + PROVIDER_NAME + " security provider wrapper");

    AccessController.doPrivileged(new PrivilegedAction<Object>() {
      @Override
      public Object run() {
        setup();
        return null;
      }
    });
  }

  protected void setup() {
    // See https://docs.oracle.com/javase/8/docs/technotes/guides/security/crypto/HowToImplAProvider.html
    put("KeyFactory." + EdDSAKey.KEY_ALGORITHM, "jp.co.soramitsu.crypto.ed25519.KeyFactory");
    put("KeyPairGenerator." + EdDSAKey.KEY_ALGORITHM,
        "jp.co.soramitsu.crypto.ed25519.KeyPairGenerator");
    put("Signature." + EdDSAEngine.SIGNATURE_ALGORITHM,
        "jp.co.soramitsu.crypto.ed25519.EdDSAEngine");

    // OID Mappings
    // See section "Mapping from OID to name".
    // The Key* -> OID mappings correspond to the default algorithm in KeyPairGenerator.
    //
    // From draft-ieft-curdle-pkix-04:
    //   id-Ed25519   OBJECT IDENTIFIER ::= { 1 3 101 112 }
    put("Alg.Alias.KeyFactory.1.3.101.112", EdDSAKey.KEY_ALGORITHM);
    put("Alg.Alias.KeyFactory.OID.1.3.101.112", EdDSAKey.KEY_ALGORITHM);
    put("Alg.Alias.KeyPairGenerator.1.3.101.112", EdDSAKey.KEY_ALGORITHM);
    put("Alg.Alias.KeyPairGenerator.OID.1.3.101.112", EdDSAKey.KEY_ALGORITHM);
    put("Alg.Alias.Signature.1.3.101.112", EdDSAEngine.SIGNATURE_ALGORITHM);
    put("Alg.Alias.Signature.OID.1.3.101.112", EdDSAEngine.SIGNATURE_ALGORITHM);
  }
}
