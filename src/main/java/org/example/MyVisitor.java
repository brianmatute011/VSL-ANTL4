package org.example;

import org.apache.http.HttpHost;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.example.parser.TestBaseVisitor;
import org.example.parser.TestParser;

import java.io.IOException;

public class MyVisitor extends TestBaseVisitor<String> {
    private RestClient esClient;


    public MyVisitor(){
        this.esClient = RestClient.builder(new HttpHost("172.16.152.80", 9200, "http")).build();
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
                System.out.println(response.getEntity().getContent());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return super.visitStatement(ctx);
    }
}

