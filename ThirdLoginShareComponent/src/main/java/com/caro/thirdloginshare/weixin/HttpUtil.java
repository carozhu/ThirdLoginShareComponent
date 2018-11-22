package com.caro.thirdloginshare.weixin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * http请求封装
 * 为Https添加受信任证书
 */
public class HttpUtil {

    public static void sendGetRequest(final String u, final DataGetSuccessListener dataGetSuccessListener) {
        new Thread() {
            @Override
            public void run() {
                try {
                    URL url = new URL(u);
                    boolean useHttps = u.startsWith("https");
                    HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
                    SSLSocketFactory oldSocketFactory = null;
                    HostnameVerifier oldHostnameVerifier = null;
                    oldSocketFactory = trustAllHosts(connection);
                    oldHostnameVerifier = connection.getHostnameVerifier();
                    connection.setHostnameVerifier(DO_NOT_VERIFY);

                    connection.setConnectTimeout(1000 * 30);
                    BufferedReader inputStream = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuffer sb = new StringBuffer();
                    String line;
                    while ((line = inputStream.readLine()) != null) {
                        sb.append(line);
                    }
                    dataGetSuccessListener.dataGetSuccess(sb.toString());
                    inputStream.close();
                    connection.disconnect();

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                    dataGetSuccessListener.dataGetIOError(e);
                }
            }
        }.start();
    }

    /**
     * 覆盖java默认的证书验证
     *    
     */
    private static final TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {

        }

        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
            return new java.security.cert.X509Certificate[]{};
        }

    }};

    /**
     * 设置不验证主机
     *    
     */
    private static final HostnameVerifier DO_NOT_VERIFY = new HostnameVerifier() {
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    };

    /**
     *  信任所有
     *  @param connection
     *  @return
     *    
     */
    private static SSLSocketFactory trustAllHosts(HttpsURLConnection connection) {
        SSLSocketFactory oldFactory = connection.getSSLSocketFactory();
        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            SSLSocketFactory newFactory = sc.getSocketFactory();
            connection.setSSLSocketFactory(newFactory);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return oldFactory;
    }

}
