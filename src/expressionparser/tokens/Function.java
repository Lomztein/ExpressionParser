/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package expressionparser.tokens;

import expressionparser.EvaluateResult;
import expressionparser.IEvaluable;
import expressionparser.IToken;
import expressionparser.ITokenable;
import expressionparser.TokenData;
import java.util.ArrayList;

/**
 *
 * @author Lomztein
 */
public class Function implements IToken, ITokenable, IEvaluable {

    public Function(String name, int precedence, int argCount, Func function) {
        this.name = name;
        this.precedence = precedence;
        this.function = function;
        this.argCount = argCount;
    }

    private final String name;
    private final int precedence;
    private final Func function;
    private final int argCount;

    @Override
    public int getPrecedence() {
        return precedence;
    }

    private ArrayList<TokenData> getArguments(ArrayList<TokenData> from, int startIndex) {
        ArrayList<TokenData> arguments = new ArrayList<>();
        for (int i = startIndex + 1; i < from.size(); i++) {
            
            TokenData current = from.get(i);

            if (from.size() > i + 1) {
                
                if (current.token instanceof Value)
                    arguments.add (current);
                TokenData next = from.get(i + 1);
                
                if (next.token instanceof Comma) {
                    i++;
                }else {
                    return arguments;
                }
                
            } else {
                arguments.add(current);
                return arguments;
            }
        }
        
        return arguments;
    }

    // sqrt (pow (10, 2) + pow (10, 2))
    
    @Override
    public EvaluateResult evaluate(ArrayList<TokenData> allTokens, int thisIndex) {
        
        ArrayList<TokenData> arguments = getArguments (allTokens, thisIndex);
        if (arguments.size () != argCount) throw new IllegalArgumentException ("Invalid argument amount for " + toString () + ". Expected " + argCount + ", got " + arguments.size () + ".");
        
        int[] spendTokens = new int[arguments.size () * 2];
        if (arguments.isEmpty())
            spendTokens = new int[1];
        
        double[] values = new double[arguments.size ()];
        spendTokens[0] = thisIndex;
        
        for (int i = 0; i < arguments.size (); i++) {
            spendTokens[i * 2 + 1] = thisIndex + i * 2 + 1; // Values
            
            if (i != arguments.size() - 1) {
                spendTokens[i * 2 + 2] = thisIndex + i * 2 + 2; // Commas
            }
            
            values[i] = ((Value) arguments.get(i).token).getValue();
        }
        
        return new EvaluateResult(function.evaluate(values), spendTokens);
    }

    @Override
    public String getIdentifier() {
        return name;
    }

    @Override
    public ITokenable getNew() {
        return new Function(name, precedence, argCount, function);
    }
    
    @Override
    public String toString () {
        return getIdentifier ();
    }

}
