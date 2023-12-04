package org.test.dyq;

import org.dyq.httpx.core.tls.PemTlsUtil;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class SSLContextExample {
    public static RSAPrivateKey readX509PublicKey(File file) throws Exception {
        String key = new String(Files.readAllBytes(file.toPath()), Charset.defaultCharset());

        String publicKeyPEM = key
                .replace("-----BEGIN PUBLIC KEY-----", "")
                .replaceAll(System.lineSeparator(), "")
                .replace("-----END PUBLIC KEY-----", "");

        byte[] encoded = Base64.getDecoder().decode(publicKeyPEM);

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(encoded);
        return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
    }
    static {

        System.setProperty("keystore.type","PEM");
    }
    public static void main(String[] args) throws Exception {


        var sslSocketFactory= PemTlsUtil.createSSLFactory(new File("D:\\code\\java\\LightJ\\httpx\\src\\main\\resources\\cert.key"),
                new File("D:\\code\\java\\LightJ\\httpx\\src\\main\\resources\\cert.pem"),"");

        // Now you can use the SSL context
        // For example, you can use it to create an SSLSocketFactory
//        SSLServerSocketFactory sslSocketFactory = sslContext.getServerSocketFactory();

        var ss  = sslSocketFactory.createServerSocket(8080);
        while (true) {
            var s = ss.accept();
            s.getOutputStream().write("""
                    HTTP/1.1 200
                    Content-Length: 5
                    Content-Type: text/plain
                    
                    hello""".getBytes());
            s.close();
        }
    }


    public void testv() {

    }
}