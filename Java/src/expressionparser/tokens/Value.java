/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package expressionparser.tokens;

import expressionparser.IToken;

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
    
}
