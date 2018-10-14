using System;

namespace Lomztein.MathematicalExpressionParser.Token {

    public class Comma : IToken, ITokenable {

        public string Identifier { get => ","; set => throw new NotImplementedException (); }

        public ITokenable GetNew() {
            return new Comma ();
        }

        public override string ToString() => Identifier;

    }

}