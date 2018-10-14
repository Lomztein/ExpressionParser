using Lomztein.MathematicalExpressionParser.Token;
using System;

namespace Lomztein.MathematicalExpressionParser.Parsers {

    public class FromTokenableParser : ITokenParser {

        ITokenable[] Tokenables { get; set; } = new ITokenable[]{

        new Comma(),

        new Operator("+", 0, (left, right) => left + right),
        new Operator("-", 0, (left, right) => left - right),
        new Operator("**", 2, (left, right) => Math.Pow(left, right)),
        new Operator("*", 1, (left, right) => left * right),
        new Operator("/", 1, (left, right) => left / right),
        new Operator("%", 1, (left, right) => left % right),
        new Operator("&", 2, (left, right) => (int) left & (int) right),
        new Operator("|", 2, (left, right) => (int) left | (int) right),
        new Operator("^", 2, (left, right) => (int) left ^ (int) right),

        new Function("pi", 2, 0, (numbers) => Math.PI),
        new Function("tau", 2, 0, (numbers) => Math.PI * 2),

        new Function("sqrt", 2, 1, (numbers) => Math.Sqrt(numbers[0])),
        new Function("pow", 2, 2, (numbers) => Math.Pow(numbers[0], numbers[1])),
        new Function("logn", 2, 2, (numbers) => Math.Log(numbers[0]) / Math.Log(numbers[1])),
        new Function("log", 2, 1, (numbers) => Math.Log(numbers[0])),
        new Function("log10", 2, 1, (numbers) => Math.Log(numbers[0]) / Math.Log(10)),};

        public (IToken token, string result) Parse(String from) {

            for (int i = 0; i < from.Length; i++) {

                foreach (ITokenable tokenable in Tokenables) {

                    String identifier = tokenable.Identifier;
                    if (from.StartsWith (identifier)) {
                        return (tokenable.GetNew (), identifier);
                    }

                }

            }

            return (null, null);
        }

    }
}
