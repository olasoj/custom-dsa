package com.dsa.question.hackerank.cf;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static java.lang.System.out;

public class numDevicesHttp {

    public static void main(String[] args) throws IOException {
        int numDevices = numDevices("STOPPED", 2, "33");
        out.println(numDevices);
    }

    public static int numDevices(String statusQuery, int threshold, String dateStr) throws IOException {

        long total = 0L;
        int page = 1;
        long maxPage = 1L;
        //INIT REQUEST
        while (page <= maxPage) {

            URL url = new URL(
                    "https://jsonmock.hackerrank.com/api/iot_devices/search?status=" + statusQuery + "&page=" + page++
            );

            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            try {
                int resCode = con.getResponseCode();

                if (resCode == 200) {

                    StringBuilder content = getRequestBody(con);

                    out.println(content);

//                    try {
//
//                        JSONParser parser = new JSONParser();
//                        Object jsonObj = parser.parse(content.toString());
//                        JSONObject jsonObject = (JSONObject) jsonObj;
//                        total = (Long) jsonObject.get("total");
//                        maxPage = (Long) jsonObject.get("total_pages");
//                        JSONArray data = (JSONArray) jsonObject.get("data");
//
//                        out.println("Subjects:" + data);
//                        Iterator iterator = data.iterator();
//                        while (iterator.hasNext()) {
//                            JSONObject next = (JSONObject) iterator.next();
//                            Instant ins = Instant.ofEpochMilli((Long) next.get("timestamp"));
//
//                            out.println(ins);
//                        }
//                    } catch (ParseException e) {
//                        throw new RuntimeException(e);
//                    }

                    return (int) total;
                }
            } finally {
                con.disconnect();
            }
        }
        return (int) total;

    }

    private static StringBuilder getRequestBody(HttpURLConnection con) throws IOException {
        StringBuilder content = new StringBuilder();
        InputStreamReader inputStreamReader = new InputStreamReader(con.getInputStream());
        try (inputStreamReader; BufferedReader in = new BufferedReader(inputStreamReader)) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
        }
        return content;
    }

}
