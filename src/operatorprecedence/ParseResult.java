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
public class ParseResult {
    
    public IToken token;
    public String parsed;
    
    public int getLength () {
        return parsed.length();
    }
    
    public ParseResult (IToken token, String parsed) {
        this.token = token;
        this.parsed = parsed;
    }
    
}
