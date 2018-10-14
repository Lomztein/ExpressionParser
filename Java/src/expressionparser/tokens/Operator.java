/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package expressionparser.tokens;

import java.util.ArrayList;
import expressionparser.EvaluateResult;
import expressionparser.IEvaluable;
import expressionparser.IToken;
import expressionparser.ITokenable;
import expressionparser.TokenData;

/**
 *
 * @author Lomztein
 */
public class Operator implements IToken, ITokenable, IEvaluable {
    
    public Operator (String operator, int precedence, Operation operation) {
        this.operator = operator;
        this.precedence = precedence;
        this.operation = operation;
    }
    
    private final Operation operation;
    private final String operator;
    private final int precedence;

    public String getOperator() {
        return operator;
    }
    
    @Override
    public int getPrecedence () {
        return precedence;
    }
    
    @Override
    public String toString () {
        return getOperator () + "";
    }
    
    @Override
    public EvaluateResult evaluate (ArrayList<TokenData> allTokens, int thisIndex) {
        double left = ((Value)allTokens.get (thisIndex - 1).token).getValue();
        double right = ((Value)allTokens.get (thisIndex + 1).token).getValue();
        return new EvaluateResult (operation.operate(left, right), new int[] { thisIndex, thisIndex - 1, thisIndex + 1});
    }

    @Override
    public String getIdentifier() {
        return getOperator () + "";
    }

    @Override
    public ITokenable getNew() {
        return new Operator (operator, precedence, operation);
    }
    
}