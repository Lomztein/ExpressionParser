/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operatorprecedence.tokenparsers;

import operatorprecedence.IToken;
import operatorprecedence.ITokenParser;
import operatorprecedence.ParseResult;
import operatorprecedence.operators.*;

/**
 *
 * @author Lomztein
 */
public class OperatorTokenParser implements ITokenParser {

    @Override
    public ParseResult parse(String from) {

        char zero = from.charAt(0);

        switch (zero) {
            case '+':
                return new ParseResult (new PlusOperator(), zero + "");
            case '-':
                return new ParseResult (new MinusOperator(), zero + "");
            case '/':
                return new ParseResult (new DivideOperator(), zero + "");
            case '*':
                return new ParseResult (new MultiplyOperator(), zero + "");
            case '%':
                return new ParseResult (new ModulusOperator (), zero + "");

        }

        return null;
    }

}
