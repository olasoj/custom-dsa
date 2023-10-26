package com.dsa.question.hackerank;

import java.util.*;

public class CC {


    String[] solution(String[][] queries) {
        Map<String, String> files = new HashMap<>();

        List<Object> resultList = new ArrayList<>(queries.length);

        final String ADD_FILE = "ADD_FILE";
        final String COPY_FILE = "COPY_FILE";
        final String GET_FILE_SIZE = "GET_FILE_SIZE";
        final String FIND_FILE = "FIND_FILE";
        final String ADD_USER = "ADD_USER";

        for (String[] query : queries) {
            String queryInt = query[0];

            switch (queryInt) {
                case ADD_FILE -> addHandler(files, resultList, query);
                case COPY_FILE -> copyHandler(files, resultList, query);
                case FIND_FILE -> findFileHandler(files, resultList, query);
                case GET_FILE_SIZE -> {
                    String fileName = query[1];
                    String orDefault = files.getOrDefault(fileName, "");
                    resultList.add(orDefault);
                }
                default -> throw new IllegalStateException("Unexpected value: " + queryInt);
            }
        }
        String[] strings = new String[queries.length];
        return resultList.toArray(strings);
    }

    private void findFileHandler(Map<String, String> files, List<Object> resultList, String[] query) {
        String prefix = query[1];
        String suffix = query[2];
        Queue<String> foundItems = new PriorityQueue<>(Comparator.reverseOrder());

        files.forEach((k, v) -> {
            k = k.trim();
            v = v.trim();
            if (k.startsWith(prefix) && k.endsWith(suffix)) {
                String value = k + "(" + v + ")";
                foundItems.add(value);
            }
        });

        StringBuilder stringBuilder = new StringBuilder();

        int count = 0;
        for (String entry : foundItems) {
            if (count > 0 && count <= foundItems.size()) {
                stringBuilder.append(", ");
            }
            stringBuilder.append(entry);
            count++;
        }
        resultList.add(stringBuilder.toString());
    }

    private void copyHandler(Map<String, String> files, List<Object> resultList, String[] query) {
        String successResult = "true";
        String failedResult = "false";

        String fileName = query[1];
        String newFileLocation = query[2];

        if (!files.containsKey(fileName) || files.containsKey(newFileLocation)) {
            resultList.add(failedResult);
            return;
        }

        String fileSize = files.get(fileName);

        files.put(newFileLocation, fileSize);
        resultList.add(successResult);
    }

    private void addHandler(Map<String, String> files, List<Object> resultList, String[] query) {
        String successResult = "true";
        String failedResult = "false";

        String fileName = query[1];

        if (files.containsKey(fileName)) {
            resultList.add(failedResult);
            return;
        }

        String fileSize = query[2];
        files.put(fileName, fileSize);
        //return true
        resultList.add(successResult);
    }


}
