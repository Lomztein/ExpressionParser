/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operatorprecedence;

/**
 *
 * @author Lomztein
 */
public class TokenData {
    
    public IToken token;
    public int depth;
    
    public TokenData (IToken token, int depth) {
        this.token = token;
        this.depth = depth;
    }
    
}
