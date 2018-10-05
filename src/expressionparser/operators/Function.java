/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package expressionparser.operators;

import expressionparser.EvaluateResult;
import expressionparser.IToken;
import expressionparser.ITokenable;
import expressionparser.TokenData;
import expressionparser.Value;
import java.util.ArrayList;

/**
 *
 * @author Lomztein
 */
public class Function implements IToken, ITokenable {
    
    public Function (String name, int precedence, Func function) {
        this.name = name;
        this.precedence = precedence;
        this.function = function;
    }
    
    private final String name;
    private final int precedence;
    private final Func function;

    @Override
    public int getPrecedence() {
        return precedence;
    }

    @Override
    public EvaluateResult evaluate(ArrayList<TokenData> allTokens, int thisIndex) {
        double value = ((Value)allTokens.get(thisIndex + 1).token).getValue ();
        return new EvaluateResult (function.evaluate(value), new int[] {thisIndex, thisIndex + 1} );
    }

    @Override
    public String getIdentifier() {
        return name;
    }

    @Override
    public ITokenable getNew() {
        return new Function (name, precedence, function);
    }
    
}
