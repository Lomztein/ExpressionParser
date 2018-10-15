using Lomztein.MathematicalExpressionParser.Evaluation;
using Lomztein.MathematicalExpressionParser.Parsers;
using Lomztein.MathematicalExpressionParser.Token;
using System;
using System.Collections.Generic;

namespace Lomztein.MathematicalExpressionParser {

    public class ExpressionParser {

        public ITokenParser[] Parsers { get; set; } = new ITokenParser[]{
            new NumberTokenParser(),
            new FromTokenableParser(),
        };

        private List<Tuple<IToken, int>> Tokens { get; set; } = new List<Tuple<IToken, int>> ();

        public double Parse(String input) {

            input = RemoveWhitespace (input);
            ParseTokens (input);

            while (Tokens.Count > 1) {

                var next = FindNextToEvaluate ();
                int nextIndex = Tokens.IndexOf (next);

                if (next == null)
                    break;

                var result = ((IEvaluable)next.Item1).Evaluate (Tokens, nextIndex);

                int emptySpot = 0;
                for (int i = 0; i < result.spendIndicies.Length; i++) {
                    Tokens[result.spendIndicies[i]] = null;
                    emptySpot = result.spendIndicies[i];
                }

                Tokens[emptySpot] = new Tuple<IToken, int> (new Value (result.result), next.Item2);

                for (int i = 0; i < Tokens.Count; i++) {
                    if (Tokens[i] == null) {
                        Tokens.RemoveAt (i);
                        i--;
                    }
                }

            }

            if (Tokens[0].Item1 is IEvaluable evaluable) {
                return (evaluable.Evaluate (Tokens, 0)).result;
            }
            return ((Value)Tokens[0].Item1).Number;

        }

        private void ParseTokens(String input) {

            int balance = 0;
            for (int i = 0; i < input.Length; i++) {

                if (input[i] == '(') {
                    balance++;
                }
                if (input[i] == ')') {
                    balance--;
                }

                string substring = input.Substring (i);
                foreach (ITokenParser parser in Parsers) {

                    var result = parser.Parse (substring);

                    if (result.token != null) {
                        Tokens.Add (new Tuple<IToken, int> (result.token, balance));
                        i += result.result.Length - 1;
                    }

                }

            }

        }

        private Tuple<IToken, int> FindNextToEvaluate() {

            int highestPrecedence = int.MinValue;
            Tuple<IToken, int> highestToken = null;

            for (int i = 0; i < Tokens.Count; i++) {

                var at = Tokens[i];

                if (at.Item1 is IEvaluable evaluable) {

                    int precedence = evaluable.Precedence + (at.Item2 * 10);
                    if (highestPrecedence < precedence) {
                        highestToken = at;
                        highestPrecedence = precedence;
                    }
                }
            }

            return highestToken;

        }

    private static string RemoveWhitespace(String input) {
        string newString = "";
        for (int i = 0; i < input.Length; i++) {

            char curChar = input[i];
            if (curChar != ' ') {
                newString += curChar;
            }

        }

        return newString;
    }

}
}