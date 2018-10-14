using Lomztein.MathematicalExpressionParser.Evaluation;
using System;
using System.Collections.Generic;

namespace Lomztein.MathematicalExpressionParser.Token {

    public class Operator : IToken, ITokenable, IEvaluable {

        public Operator(String character, int precedence, Func<double, double, double> operation) {
            Operation = operation;
            Character = character;
            Precedence = precedence;
        }

        public Func<double, double, double> Operation { get; private set; }
        public string Character { get; private set; }
		public string Identifier { get => Character; set => Character = value; }
        public int Precedence { get; set; }

        public override string ToString() => Character;

        public (double result, int[] spendIndicies) Evaluate(List<Tuple<IToken, int>> allTokens, int thisIndex) {
            double left = ((Value)allTokens[thisIndex - 1].Item1).Number;
            double right = ((Value)allTokens[thisIndex + 1].Item1).Number;
            return (Operation (left, right), new int[] { thisIndex, thisIndex - 1, thisIndex + 1 });
        }

        public ITokenable GetNew() {
            return new Operator (Character, Precedence, Operation);
        }

    }
}