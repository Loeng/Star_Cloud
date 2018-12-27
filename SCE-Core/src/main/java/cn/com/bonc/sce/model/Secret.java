package cn.com.bonc.sce.model;

import cn.com.bonc.sce.tool.CryptoUtil;
import cn.hutool.crypto.asymmetric.RSA;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * 基于 2048bit-RSA 的秘钥对
 *
 * @author Leucippus
 * @version 0.1
 * @since 2018/12/25 16:23
 */
public class Secret {
    private KeyPair keyPair;
    private String algorithm;

    public Secret( String secret ) {
        String privateKey = secret.substring( 0, 1624 );
        String publicKey = secret.substring( 1624, 2016 );

        RSA rsa = new RSA( privateKey, publicKey );
        this.keyPair = new KeyPair( rsa.getPublicKey(), rsa.getPrivateKey() );
        this.algorithm = "RSA";
    }

    public Secret() {
        this.keyPair = CryptoUtil.RSA_CRYPTO_UTIL_INSTANCE.generateKeyPair();
    }

    public String getSecretPair() {
        if ( keyPair != null ) {
            this.keyPair = CryptoUtil.RSA_CRYPTO_UTIL_INSTANCE.generateKeyPair();
        }
        RSA rsa = new RSA( getPrivateKey(), getPublicKey() );
        return rsa.getPrivateKeyBase64() + rsa.getPublicKeyBase64();
    }

    public PrivateKey getPrivateKey() {
        return keyPair.getPrivate();
    }

    public PublicKey getPublicKey() {
        return this.keyPair.getPublic();
    }

    public static void main( String[] args ) {
        System.out.println("MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDEUlEF2scV61nJ0rUR1Tm77/32BPVucHXTRdXo4SbSzul6yP435ZqFH1I0xCv+TEOdPc0OSd8jjUZleQ1EmXeJkGnT6jyUXcpi4GgPcjcFxybZiX2KQJIB+p+g5nsPREBsFZ/NurAuZjIIeWJrsa+/CKsMUabBmx6mUNBPna5x7eTFzSmpI8QtsTxbED2bFq7JfRbfkMGoT99cSk1DkGD+PhvQS6Fieg+BSNYs9EvgFCDXIfbGNq9CIRVSYPg7brrzeq1k+c8e3xzPhKNTLazF+B2ItEsDuPhdXzks8mnxMxBdSHOX5F8nWSS+6UPeVPJQdiIHsNXK8Xo9LUMWPui7AgMBAAECggEABYJF0cM82OmcwGQQPqrcDuECIFgtRucS91W7VeiTfVDfDln7gDgKKPgjB2YF0gHz18K3MVJzV30MX6aQ777astqBjbfB8WvcVP/1C5Hp51iaR3prMmjzb+jHd+1fwKLqp93HeS9m6DZn6wL09w6qXtLwcc0Mv9wJdSNJZSKIlZvJt8KPV+P9s4a72Nn5tBDMTPph6kO086mOi0L1CmL532m/vJYIisLOGMFoZCk5uc/KaX2qWyPvNR5U38rsNBOMX2h74ghvuptmoXMNdXueAeyRa4DQQUh/j1c5jSocaq6HAGkWBw7IG/nvlrDiJirFUbFEA9wG9WeXwuNVtfdS2QKBgQDuQkT+E5gLXPIVWVpw18js+pHj2lPV5JgCv7zNwkHNx0yIhQ7o7nwkt8WXFSfDQLKAtiyDBqvJxR8HjB/VDdf9LnG5CsceVGA/MSVzMcolqi+BS/KtvEVmVMxE7eO8HauYJqDG8BWj0xvn05z4TadEfMmjVe6ozy+M+5EapmO/lwKBgQDS8KF9ytCvjkjqF5cY5yYr6huoa5tpao413jAwHBoO6ieRBRv9P+FmUvpi7c/j78VEEwcDb5kMCoFtq44ecfk8l6x7K8xEny806/QmuqTi6MELl6AZb/6ovxpqqBDTTTP9cT+gYqYPcXdY85Wt1m6NDIcW1EaWBh8IVaRtTVQEfQKBgCrquvy9UMzzMBQSZU0gQWwWcoMFh8Zmh88u9HZnwGytsqaAuT/R/YCrrabaQ7BTDhRLPFdZs0YEAOPeJLm+5aIQZKG9REK9Vhzr4GAQnuYL8sSqhosu951INwdPho8TFR6yDpHnGy7ITyWCI6tCaZfXloaaawPzVhBiHDAduqf/AoGARRkGDYrOaXOWdvhbSpENc/0wKi91rUZ2ZzbtWhUahCA37c1aqc6vKzGrS5TTMLtuRypkdb+sxsD6PM6hs19qYWrd2UszkpcCg7d3NuiU8q+D7SQyEaMaLHTNVnrPaCg2C6N5UDSAaTal/cFtGpDPjey4o8XNiX9f0v8a6aMXDpECgYEA0Bft2Yf4X4sqX1Gqwk+S5ECQIrkJB015FbSe5pUKr64VENRhfxi+uv2h5i+Q5eV7xAcETK+2DEzf+yu5w8bikdUsjRfz7MO9La2J50RvQK65+K0je7uOuOui0rEy25qfmLyW3JErjrvPF8mqKDvsy6Ue/Qigapdcm0vcBkVKV10=MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAxFJRBdrHFetZydK1EdU5u+/99gT1bnB100XV6OEm0s7pesj+N+WahR9SNMQr/kxDnT3NDknfI41GZXkNRJl3iZBp0+o8lF3KYuBoD3I3Bccm2Yl9ikCSAfqfoOZ7D0RAbBWfzbqwLmYyCHlia7GvvwirDFGmwZseplDQT52uce3kxc0pqSPELbE8WxA9mxauyX0W35DBqE/fXEpNQ5Bg/j4b0EuhYnoPgUjWLPRL4BQg1yH2xjavQiEVUmD4O26683qtZPnPHt8cz4SjUy2sxfgdiLRLA7j4XV85LPJp8TMQXUhzl+RfJ1kkvulD3lTyUHYiB7DVyvF6PS1DFj7ouwIDAQAB".length());
        Secret secret = new Secret( "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" );
        System.out.println(secret.getSecretPair());
    }
}
