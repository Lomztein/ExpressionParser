/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package expressionparser;

/**
 *
 * @author Lomztein
 */
public class EvaluateResult {
    
    public double result;
    public int[] spendTokenIndices;
    
    public EvaluateResult (double result, int[] spendTokenIndices) {
        this.result = result;
        this.spendTokenIndices = spendTokenIndices;
    }
    
}
