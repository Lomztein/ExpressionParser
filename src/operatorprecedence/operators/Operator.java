/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operatorprecedence.operators;

import java.util.ArrayList;
import operatorprecedence.EvaluateResult;
import operatorprecedence.IToken;
import operatorprecedence.TokenData;
import operatorprecedence.Value;

/**
 *
 * @author Lomztein
 */
public abstract class Operator implements IToken {

    public abstract char getOperator();
    
    @Override
    public abstract int getPrecedence ();
    
    protected abstract double operate (double leftHand, double rightHand);
    
    @Override
    public String toString () {
        return getOperator () + "";
    }
    
    @Override
    public EvaluateResult evaluate (ArrayList<TokenData> allTokens, int thisIndex) {
        double left = ((Value)allTokens.get (thisIndex - 1).token).getValue();
        double right = ((Value)allTokens.get (thisIndex + 1).token).getValue();
        return new EvaluateResult (operate (left, right), new int[] { thisIndex, thisIndex - 1, thisIndex + 1});
    }
    
}