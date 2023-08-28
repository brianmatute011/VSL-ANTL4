package org.example;

import org.apache.http.HttpHost;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.example.parser.TestBaseVisitor;
import org.example.parser.TestParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class MyVisitor extends TestBaseVisitor<String> {
    private RestClient esClient;


    public MyVisitor(){
        this.esClient = RestClient.builder(new HttpHost("172.16.152.80", 9200, "http")).build();
    }

    private String convertInputStreamToString(InputStream inputStream) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
            return bufferedReader.lines().collect(Collectors.joining("\n"));
        }
    }

    @Override
    public String visitStatement(TestParser.StatementContext ctx) {
        String statementText = ctx.getText();
        //System.out.print("ZZZZ");

        if (statementText.equals("K")) {
            System.out.println("XCX");

            String endpoint = "amazon_test_meta/_search";
            String query = "{ \"query\": { \"term\": { \"asin.keyword\": \"076458605X\" } } }";
            Request request = new Request("GET", endpoint);
            request.setJsonEntity(query);
            try {
                Response response = esClient.performRequest(request);
                // Procesar la respuesta de Elasticsearch según tus necesidades
                System.out.println(convertInputStreamToString(response.getEntity().getContent()));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return super.visitStatement(ctx);
    }
}

