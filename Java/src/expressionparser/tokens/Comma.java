/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package expressionparser.tokens;

import expressionparser.IToken;
import expressionparser.ITokenable;

/**
 *
 * @author Lomztein
 */
public class Comma implements IToken, ITokenable {


    @Override
    public String getIdentifier() {
        return ",";
    }

    @Override
    public ITokenable getNew() {
        return new Comma ();
    }
    
    @Override
    public String toString () {
        return getIdentifier();
    }
    
}
