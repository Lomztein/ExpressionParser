/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operatorprecedence.tokenparsers;

import operatorprecedence.IToken;
import operatorprecedence.ITokenParser;
import operatorprecedence.ParseResult;
import operatorprecedence.Value;

/**
 *
 * @author Lomztein
 */
public class NumberTokenParser implements ITokenParser {
    
    private static final char[] NUMERICS = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '.' };

    @Override
    public ParseResult parse(String from) {
        
        String number = "";
        for (int i = 0; i < from.length() ;i++) {
            if (isNumeric (from.charAt(i))) {
                number += from.charAt (i);
            } else break;
        }
        
        if (number.equals(""))
            return null;
        
        return new ParseResult (new Value (Double.parseDouble (number)), number);
    }
    
    private boolean isNumeric (char character) {
        for (char ch : NUMERICS) {
            if (ch == character)
                return true;
        }
        return false;
    }
    
}
