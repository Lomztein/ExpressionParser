/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package expressionparser.tokenparsers;

import expressionparser.operators.*;
import expressionparser.ITokenParser;
import expressionparser.ITokenable;
import expressionparser.ParseResult;

/**
 *
 * @author Lomztein
 */
public class FromTokenableParser implements ITokenParser {

    ITokenable[] tokenables = new ITokenable[]{
        new Operator("+", 0, (left, right) -> left + right),
        new Operator("-", 0, (left, right) -> left - right),
        new Operator("**", 2, (left, right) -> Math.pow (left, right)),
        new Operator("*", 1, (left, right) -> left * right),
        new Operator("/", 1, (left, right) -> left / right),
        new Operator("%", 1, (left, right) -> left % right),
        
        new Operator("&", 2, (left, right) -> (int)left & (int)right),
        new Operator("|", 2, (left, right) -> (int)left | (int)right),
        new Operator("^", 2, (left, right) -> (int)left ^ (int)right),
        
        new Function("sqrt", 2, (numbers) -> Math.sqrt(numbers[0])),
        new Function("log2", 2, (numbers) -> Math.log(numbers[0]) / Math.log(2))
    };

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
