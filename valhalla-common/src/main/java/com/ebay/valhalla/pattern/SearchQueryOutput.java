package com.ebay.valhalla.pattern;


import com.ebay.valhalla.api.aggregate.ElasticAggregate;
import com.ebay.valhalla.api.filter.ElasticFilter;
import com.ebay.valhalla.api.sort.ElasticSort;
import com.ebay.valhalla.pattern.g4.SearchQueryLexer;
import com.ebay.valhalla.pattern.g4.SearchQueryParser;
import com.ebay.valhalla.type.ElasticSortOrder;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;

import java.util.List;

public class SearchQueryOutput {
    public String clzName;
    public ElasticFilter[] filters;
    public ElasticAggregate[] aggregates;
    public String[] targetFields;
    public ElasticSort[] sorts;
    public int maxSize = 100;

    public static SearchQueryOutput fromString(String inputString) {
        SearchQueryLexer lexer = new SearchQueryLexer(CharStreams.fromString(inputString));
        List<Token> xx = new CommonTokenStream(lexer).getTokens();

        SearchQueryParser parser = new SearchQueryParser(new CommonTokenStream(lexer));
        SearchQueryOutputVisitor visitor = new SearchQueryOutputVisitor();
        return visitor.visit(parser.query());
    }

    public static void main(String[] args) {
        SearchQueryOutput output =
            SearchQueryOutput.fromString(
                "running_queue_app_metrics[@snapTimestamp>1524553500000](@allocatedMB>)@1000");


        System.out.println(output.clzName);
    }
}
