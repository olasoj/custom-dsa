package com.dsa.misc.networking;

import com.dsa.util.PerformanceUtil;

import java.io.IOException;
import java.net.*;
import java.util.logging.Logger;

public class Util {

    private static final Logger LOGGER = Logger.getLogger(Util.class.getName());

    public static void main(String[] args) {

        PerformanceUtil.measureOperationDuration(Util::extracted);
    }

    private static void extracted() {
        String url = "http://3.249.148.206:8085";
        URL urlO = getUrl(url);

        String url2 = "http://devapi.currencycloud.com";
        int timeOutMillis = 30_000;
//        boolean b1 = PerformanceUtil.measureOperationDuration(() -> pingHost(urlO.getHost(), 443, timeOutMillis));
//        boolean b2 = PerformanceUtil.measureOperationDuration(() -> pingHost(urlO.getHost(), 80, timeOutMillis));
//        boolean b3 = PerformanceUtil.measureOperationDuration(() -> pingHost(urlO.getHost(), 25, timeOutMillis));

//        LOGGER.info(() -> String.valueOf(b1));
//        LOGGER.info(() -> String.valueOf(b2));
//        LOGGER.info(() -> String.valueOf(b3));

        boolean b4 = PerformanceUtil.measureOperationDuration(() -> pingHost2(url, timeOutMillis));
        boolean b5 = PerformanceUtil.measureOperationDuration(() -> pingHost2(url2, timeOutMillis));

        LOGGER.info(() -> String.valueOf(b4));
        LOGGER.info(() -> String.valueOf(b5));
    }

    private static boolean pingHost2(String addr, int timeOutMillis) {

        try {

            URL url = getUrl(addr);

            String host = url.getHost();
            int openPort = (url.getPort()) == -1 ? url.getDefaultPort() : url.getPort();

            InetAddress inetAddress = InetAddress.getByName(host);
            String hostAddress = inetAddress.getHostAddress();

            return pingHost(hostAddress, openPort, timeOutMillis);
        } catch (IOException ex) {
            return false;
        }
    }

    private static URL getUrl(String addr)  {
        try {
            return new URL(addr);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean pingHost(String addr, int openPort, int timeOutMillis) {

        try (Socket soc = new Socket()) {
            soc.connect(new InetSocketAddress(addr, openPort), timeOutMillis);
            return true;
        } catch (IOException ex) {
            return false;
        }
    }

    public static boolean pingHost(String url, int timeout) {

        HttpURLConnection connection = null;

        try {

            URL u = getUrl(url);

            connection = (HttpURLConnection) u.openConnection();

            connection.setRequestMethod("HEAD");
            connection.setConnectTimeout(timeout);
            connection.setReadTimeout(timeout);
            connection.connect();

            int responseCode = connection.getResponseCode();
            return responseCode > 0;
        } catch (IOException e) {
            LOGGER.info(e.getMessage());
            return false;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }

        }
    }

}
