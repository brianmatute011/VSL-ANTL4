import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.example.MyVisitor;
import org.example.parser.TestLexer;
import org.example.parser.TestParser;

public class Main {
    public static void main(String[] args) {
        String input = "KII";
        CharStream inputStream = CharStreams.fromString(input); // Usar CharStreams en lugar de ANTLRInputStream
        TestLexer lexer = new TestLexer(inputStream);
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        TestParser parser = new TestParser(tokenStream);
        TestParser.ProgramContext programContext = parser.program();
        MyVisitor visitor = new MyVisitor();
        visitor.visit(programContext);
    }
}
