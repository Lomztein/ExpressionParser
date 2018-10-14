/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package expressionparser.tokenparsers;

import expressionparser.ITokenParser;
import expressionparser.ITokenable;
import expressionparser.ParseResult;
import expressionparser.tokens.*;

/**
 *
 * @author Lomztein
 */
public class FromTokenableParser implements ITokenParser {

    ITokenable[] tokenables = new ITokenable[]{
        new Comma(),
        
        new Operator("+", 0, (left, right) -> left + right),
        new Operator("-", 0, (left, right) -> left - right),
        new Operator("**", 2, (left, right) -> Math.pow(left, right)),
        new Operator("*", 1, (left, right) -> left * right),
        new Operator("/", 1, (left, right) -> left / right),
        new Operator("%", 1, (left, right) -> left % right),
        new Operator("&", 2, (left, right) -> (int) left & (int) right),
        new Operator("|", 2, (left, right) -> (int) left | (int) right),
        new Operator("^", 2, (left, right) -> (int) left ^ (int) right),
        
        new Function("pi", 2, 0, (numbers) -> Math.PI),
        new Function("tau", 2, 0, (numbers) -> Math.PI * 2),
        
        new Function("sqrt", 2, 1, (numbers) -> Math.sqrt(numbers[0])),
        new Function("pow", 2, 2, (numbers) -> Math.pow(numbers[0], numbers[1])),
        new Function("logn", 2, 2, (numbers) -> Math.log(numbers[0]) / Math.log(numbers[1])),
        new Function("log", 2, 1, (numbers) -> Math.log(numbers[0])),
        new Function("log10", 2, 1, (numbers) -> Math.log(numbers[0]) / Math.log(10)),};

    @Override
    public ParseResult parse(String from) {

        for (int i = 0; i < from.length(); i++) {

            for (ITokenable tokenable : tokenables) {

                String identifier = tokenable.getIdentifier();
                // 523+234+(234-23)*2
                if (from.startsWith(identifier)) {
                    return new ParseResult(tokenable.getNew(), identifier);
                }

            }

        }

        return null;
    }

}
