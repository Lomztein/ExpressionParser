namespace Lomztein.MathematicalExpressionParser.Token {

    public interface ITokenParser {

        (IToken token, string result) Parse(string from);

    }

}

