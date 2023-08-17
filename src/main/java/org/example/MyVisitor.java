package org.example;

import org.example.parser.TestBaseVisitor;
import org.example.parser.TestParser;

public class MyVisitor extends TestBaseVisitor<String> {
    @Override
    public String visitStatement(TestParser.StatementContext ctx) {
        String statementText = ctx.getText();
        //System.out.print("ZZZZ");

        if (statementText.equals("K")) {
            System.out.println("XCX");
        }
        return super.visitStatement(ctx);
    }
}

