namespace Lomztein.MathematicalExpressionParser.Token {

    public interface ITokenable : IToken {

        string Identifier { get; set; }

        ITokenable GetNew();

    }

}