/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operatorprecedence.operators;

/**
 *
 * @author Lomztein
 */
public class ModulusOperator extends Operator {

    @Override
    public char getOperator() {
        return '%';
    }

    @Override
    public int getPrecedence() {
        return 0;
    }

    @Override
    protected double operate(double leftHand, double rightHand) {
        return leftHand % rightHand;
    }

    
}
