/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package expressionparser;

import expressionparser.tokens.Value;
import expressionparser.tokenparsers.NumberTokenParser;
import expressionparser.tokenparsers.FromTokenableParser;
import java.util.ArrayList;

/**
 *
 * @author Lomztein
 */
public class ExpressionParser {

    private final ITokenParser[] parsers = new ITokenParser[]{
        new NumberTokenParser(),
        new FromTokenableParser(),};

    private final ArrayList<TokenData> tokens = new ArrayList<>();

    public double parse(String input) {

        input = removeWhitespace(input);

        parseTokens(input);

        while (tokens.size() > 1) {

            TokenData next = findNextToEvaluate(tokens);
            int nextIndex = tokens.indexOf(next);
            
            if (next == null)
                break;

            EvaluateResult result = ((IEvaluable) next.token).evaluate(tokens, nextIndex);

            int emptySpot = 0;
            for (int i = 0; i < result.spendTokenIndices.length; i++) {
                tokens.set(result.spendTokenIndices[i], null);
                emptySpot = result.spendTokenIndices[i];
            }
            
            tokens.set(emptySpot, new TokenData(new Value(result.result), next.depth));

            for (int i = 0; i < tokens.size(); i++) {
                if (tokens.get(i) == null) {
                    tokens.remove(i);
                    i--;
                }
            }

        }

        if (tokens.get(0).token instanceof IEvaluable) {
            return ((IEvaluable)tokens.get(0).token).evaluate(tokens, 0).result;
        }
        return ((Value) tokens.get(0).token).getValue();

    }

    private void parseTokens(String input) {

        System.out.println("Parsing: " + input);

        int balance = 0;
        for (int i = 0; i < input.length(); i++) {

            if (input.charAt(i) == '(') {
                balance++;
            }
            if (input.charAt(i) == ')') {
                balance--;
            }

            String substring = input.substring(i);
            for (ITokenParser parser : parsers) {
                ParseResult result = parser.parse(substring);
                if (result != null) {
                    tokens.add(new TokenData(result.token, balance));
                    i += result.getLength() - 1;
                }
            }

        }

    }

    private TokenData findNextToEvaluate(ArrayList<TokenData> tokens) {

        int highestPrecedence = Integer.MIN_VALUE;
        TokenData highestToken = null;

        for (int i = 0; i < tokens.size(); i++) {

            TokenData at = tokens.get(i);

            if (!(at.token instanceof IEvaluable)) {
                continue;
            }

            IEvaluable evaluable = (IEvaluable) at.token;

            int precedence = evaluable.getPrecedence() + (at.depth * 10);
            if (highestPrecedence < precedence) {
                highestToken = at;
                highestPrecedence = precedence;
            }

        }

        return highestToken;

    }

    private static String removeWhitespace(String input) {
        String newString = "";
        for (int i = 0; i < input.length(); i++) {

            char curChar = input.charAt(i);
            if (curChar != ' ') {
                newString += curChar;
            }

        }

        return newString;
    }

}
