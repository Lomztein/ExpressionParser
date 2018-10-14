namespace Lomztein.MathematicalExpressionParser.Token {

    public class Value : IToken {

        public Value(double value) {
            Number = value;
        }

        public double Number { get; private set; }

        public override string ToString() => Number.ToString ();

    }
}