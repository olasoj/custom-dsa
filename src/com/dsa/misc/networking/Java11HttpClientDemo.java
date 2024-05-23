package com.dsa.misc.networking;

import com.dsa.util.PerformanceUtil;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Set;

import static java.lang.System.out;
import static java.net.http.HttpClient.Redirect.ALWAYS;
import static java.net.http.HttpClient.Version.HTTP_1_1;

public class Java11HttpClientDemo {

    private static final HttpClient javaHttpClient = HttpClient.newBuilder()
            .followRedirects(ALWAYS)
            .version(HTTP_1_1)
            .connectTimeout(Duration.ofSeconds(15))
            .build();

    private static final Set<Integer> argsSAV = Set.of(
            766, 761, 474, 763, 795, 590, 796, 798, 757, 750, 952, 69, 193, 71, 72, 73, 74, 75, 800, 759, 116, 1719, 80, 81, 82, 83, 70, 85, 86,
            87, 88, 89, 90, 91, 92, 93, 475, 94, 95, 76, 745, 97, 78, 118, 99, 758, 119, 102, 120, 103, 104, 121, 105, 106, 122, 107, 108, 123,
            109, 110, 124, 111, 112, 125, 113, 114, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 256, 138, 194, 96, 361, 77, 812,
            148, 149, 258, 98, 259, 264, 265, 601, 100, 79, 267, 268, 269, 270, 271, 276, 287, 240, 283, 241, 242, 243, 244, 284, 245, 246,
            285, 247, 252, 286, 253, 254, 255, 137, 362, 140, 476, 142, 143, 144, 797, 145, 146, 147, 151, 799, 152, 153, 154, 802, 155, 805,
            811, 162, 163, 164, 165, 166, 167, 168, 177, 183, 260, 226, 195, 261, 262, 953, 263, 274, 275, 277, 279, 280, 204, 281, 282, 288,
            289, 139, 224, 225, 227, 117, 794, 230, 231, 232, 233, 234, 235, 236, 150, 238, 239, 248, 249, 250, 251, 363, 291, 477, 266, 364,
            366, 215, 368, 369, 384, 386, 387, 389, 391, 396, 398, 399, 400, 401, 408, 410, 411, 412, 416, 417, 418, 422, 423, 424, 428, 292,
            294, 295, 296, 297, 298, 299, 300, 301, 302, 304, 305, 306, 307, 308, 309, 310, 311, 312, 313, 314, 315, 317, 318, 319, 320, 322,
            323, 324, 325, 329, 328, 330, 331, 333, 334, 335, 478, 336, 338, 339, 801, 340, 341, 343, 344, 345, 346, 348, 349, 350, 351, 385,
            353, 388, 354, 355, 390, 356, 392, 393, 394, 395, 397, 403, 404, 405, 479, 429, 430, 954, 434, 435, 436, 440, 441, 442, 446, 447,
            453, 457, 456, 458, 462, 463, 464, 468, 469, 470, 480, 481, 482, 483, 484, 803, 485, 486, 487, 488, 804, 489, 490, 491, 806, 502,
            503, 504, 506, 509, 510, 511, 513, 517, 518, 519, 290, 955, 694, 807, 705, 634, 630, 808, 228, 141, 229, 257, 549, 809, 550, 551,
            552, 704, 626, 814, 639, 706, 815, 637, 641, 956, 810, 237, 816, 702, 817, 570, 819, 583, 584, 820, 821, 585, 586, 587, 638, 589,
            640, 708, 719, 709, 293, 717, 710, 711, 712, 713, 720, 367, 723, 716, 721, 724, 722, 727, 726, 729, 728, 731, 730, 740, 741, 742,
            957, 743, 762, 775
    );

    private static final int SIZE = argsSAV.size();

    public static void main(String[] args) {

        PerformanceUtil.measureOperationDuration(() -> doOps());
    }

    private static void doOps() {

        int tempSize = SIZE;

        out.println("Executing " + SIZE + " deposits");

        for (Integer arg : argsSAV) {

            if (tempSize % 10 == 0) {
                sleep(5_200);
            }

            PerformanceUtil.measureOperationDuration(() -> doOps(arg));
            out.println("Remaining " + --tempSize);
        }
    }

    private static void doOps(Integer arg) {


        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(url(arg)))
                .headers(
                        "Content-Type", "application/json",
                        "Fineract-Platform-TenantId", "default",
                        "Authorization", "Basic bWlmb3M6cGFzc3dvcmQ=",
                        "Accept-Encoding", "gzip, deflate, br"
                )
                .method("POST", HttpRequest.BodyPublishers.ofString("""                                                
                            {
                                "locale": "en",
                                "dateFormat": "dd MMMM yyyy",
                                "transactionDate": "23 May 2024",
                                "transactionAmount": "500000",
                                "paymentTypeId": "1",
                                "transactionUniqueId": transactionUniqueId
                            }
                        """))
                .build();


        try {
            HttpResponse<String> httpResponse = javaHttpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));
            String body = httpResponse.body();
            out.println(body);

            if (httpResponse.statusCode() >= 200 && httpResponse.statusCode() < 300) {
                out.println("Success for savings account: " + arg);
            } else {
                out.println("Failed for savings account: " + arg);
            }

        } catch (IOException e) {

            out.println("Out Failed for savings account: " + arg);
            e.printStackTrace();
        } catch (InterruptedException e) {

            out.println("Out Failed for savings account: " + arg);

            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }

    private static String url(int accId) {
        return "https://fineract-service-uat.projectx-core.com/fineract-provider/api/v1/savingsaccounts/" + accId + "/transactions?command=deposit";
    }

    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {

            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }
}
