/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package expressionparser;

import java.util.ArrayList;

/**
 *
 * @author Lomztein
 */
public interface IEvaluable extends IToken {
    
    int getPrecedence ();
    
    EvaluateResult evaluate (ArrayList<TokenData> data, int thisIndex);
    
}
