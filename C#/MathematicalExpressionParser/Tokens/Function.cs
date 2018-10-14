using Lomztein.MathematicalExpressionParser.Evaluation;
using System;
using System.Collections.Generic;

namespace Lomztein.MathematicalExpressionParser.Token {

    public class Function : IToken, ITokenable, IEvaluable {

        public Function(String name, int precedence, int argCount, Func<double[], double> function) {
            Name = name;
            Precedence = precedence;
            Func = function;
            ArgCount = argCount; ;
        }

        public string Name { get; set; }
        public string Identifier { get => Name; set => Name = value; }
        public int Precedence { get; set; }
        public Func<double[], double> Func { get; set; }
        public int ArgCount { get; set; }

        private List<Tuple<IToken, int>> GetArguments(List<Tuple<IToken, int>> from, int startIndex) {

            var arguments = new List<Tuple<IToken, int>> ();
            for (int i = startIndex + 1; i < from.Count; i++) {

                var current = from[i];

                if (from.Count > i + 1) {

                    if (current.Item1 is Value)
                        arguments.Add (current);
                    var next = from[i + 1];

                    if (next.Item1 is Comma) {
                        i++;
                    } else {
                        return arguments;
                    }

                } else {
                    arguments.Add (current);
                    return arguments;
                }
            }

            return arguments;
        }

        public (double result, int[] spendIndicies) Evaluate(List<Tuple<IToken, int>> allTokens, int thisIndex) {

            var arguments = GetArguments (allTokens, thisIndex);
            if (arguments.Count != ArgCount) throw new InvalidOperationException ("Invalid argument amount for " + ToString () + ". Expected " + ArgCount + ", got " + arguments.Count + ".");

            int[] spendTokens = new int[arguments.Count * 2];
            if (arguments.Count == 0)
                spendTokens = new int[1];

            double[] values = new double[arguments.Count];
            spendTokens[0] = thisIndex;

            for (int i = 0; i < arguments.Count; i++) {
                spendTokens[i * 2 + 1] = thisIndex + i * 2 + 1; // Values

                if (i != arguments.Count - 1) {
                    spendTokens[i * 2 + 2] = thisIndex + i * 2 + 2; // Commas
                }

                values[i] = ((Value)arguments[i].Item1).Number;
            }

            return (Func (values), spendTokens);
        }

        public ITokenable GetNew() {
            return new Function (Name, Precedence, ArgCount, Func);
        }

        public override string ToString() => Identifier;

    }
}
