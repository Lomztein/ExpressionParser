/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package expressionparser;

import java.util.Scanner;

/**
 *
 * @author Lomztein
 */
public class OperatorPrecedence {

    public static final Scanner INPUT = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {
            String input = INPUT.nextLine();
            ExpressionParser parser = new ExpressionParser();
            double result = parser.parse(input);
            System.out.println(result);
        }

    }

}
