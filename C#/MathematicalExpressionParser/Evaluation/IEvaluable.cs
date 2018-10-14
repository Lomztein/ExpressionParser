using Lomztein.MathematicalExpressionParser.Token;
using System;
using System.Collections.Generic;

namespace Lomztein.MathematicalExpressionParser.Evaluation {

    public interface IEvaluable : IToken {
    
        int Precedence { get; set; }

        (double result, int[] spendIndicies) Evaluate (List<Tuple<IToken, int>> data, int thisIndex);

    }

}
