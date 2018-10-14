using Lomztein.MathematicalExpressionParser.Token;

namespace Lomztein.MathematicalExpressionParser.Parsers {


public class NumberTokenParser : ITokenParser {
    
    private static readonly char[] NUMERICS = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '.' };

    public (IToken token, string result) Parse(string from) {
        
        string number = "";
        for (int i = 0; i < from.Length ;i++) {
            if (IsNumeric (from[i])) {
                number += from[i];
            } else break;
        }
        
        if (string.IsNullOrEmpty (number))
            return (null, null);
        
        return (new Value (double.Parse (number)), number);
    }
    
    private bool IsNumeric (char character) {
        foreach (char ch in NUMERICS) {
            if (ch == character)
                return true;
        }
        return false;
    }

}
}
