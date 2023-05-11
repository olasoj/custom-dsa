package com.dsa.question.hackerank;

import javax.annotation.processing.Generated;
import java.io.IOException;
import java.io.Serializable;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static java.net.http.HttpClient.Redirect.ALWAYS;
import static java.net.http.HttpClient.Version.HTTP_1_1;

public class SS {

    private static final HttpClient javaHttpClient = HttpClient.newBuilder()
            .followRedirects(ALWAYS)
            .version(HTTP_1_1)
            .connectTimeout(Duration.ofSeconds(15))
            .build();

    public static void main(String[] args) {

        Thread thread = new Thread();
        thread.start();

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(url(1)))
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();


        try {
            HttpResponse<String> httpResponse = javaHttpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));
            String body = httpResponse.body();
            System.out.println(body);


        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }


    private static String url(int pageNumber) {
        return "https://jsonmock.hackerrank.com/api/article_users?page=" + pageNumber;
    }

    @Generated("jsonschema2pojo")
    public class Datum implements Serializable {

        private final static long serialVersionUID = -3460625928944362791L;
        private Integer id;
        private String username;
        private String about;
        private Integer submitted;
        private String updatedAt;
        private Integer submissionCount;
        private Integer commentCount;
        private Integer createdAt;
        private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getAbout() {
            return about;
        }

        public void setAbout(String about) {
            this.about = about;
        }

        public Integer getSubmitted() {
            return submitted;
        }

        public void setSubmitted(Integer submitted) {
            this.submitted = submitted;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public Integer getSubmissionCount() {
            return submissionCount;
        }

        public void setSubmissionCount(Integer submissionCount) {
            this.submissionCount = submissionCount;
        }

        public Integer getCommentCount() {
            return commentCount;
        }

        public void setCommentCount(Integer commentCount) {
            this.commentCount = commentCount;
        }

        public Integer getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(Integer createdAt) {
            this.createdAt = createdAt;
        }

        public Map<String, Object> getAdditionalProperties() {
            return this.additionalProperties;
        }

        public void setAdditionalProperty(String name, Object value) {
            this.additionalProperties.put(name, value);
        }

    }


    @Generated("jsonschema2pojo")
    public class Result implements Serializable {

        private final static long serialVersionUID = 886355404618748184L;
        private Integer page;
        private Integer perPage;
        private Integer total;
        private Integer totalPages;
        private List<Datum> data = new ArrayList<Datum>();
        private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

        public Integer getPage() {
            return page;
        }

        public void setPage(Integer page) {
            this.page = page;
        }

        public Integer getPerPage() {
            return perPage;
        }

        public void setPerPage(Integer perPage) {
            this.perPage = perPage;
        }

        public Integer getTotal() {
            return total;
        }

        public void setTotal(Integer total) {
            this.total = total;
        }

        public Integer getTotalPages() {
            return totalPages;
        }

        public void setTotalPages(Integer totalPages) {
            this.totalPages = totalPages;
        }

        public List<Datum> getData() {
            return data;
        }

        public void setData(List<Datum> data) {
            this.data = data;
        }

        public Map<String, Object> getAdditionalProperties() {
            return this.additionalProperties;
        }

        public void setAdditionalProperty(String name, Object value) {
            this.additionalProperties.put(name, value);
        }

    }
}
