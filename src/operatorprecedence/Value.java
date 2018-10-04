/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operatorprecedence;

import java.util.ArrayList;

/**
 *
 * @author Lomztein
 */
public class Value implements IToken {
    
    public Value (double value) {
        this.value = value;
    }
    
    private final double value;
    
    public double getValue () {
        return value;
    }
    
    @Override
    public String toString () {
        return Double.toString(value);
    }

    @Override
    public int getPrecedence() {
        return Integer.MIN_VALUE;
    }

    @Override
    public EvaluateResult evaluate(ArrayList<TokenData> allTokens, int thisIndex) {
        return new EvaluateResult (getValue (), new int [] {thisIndex});
    }
    
}
