public interface ITokenable : IToken {
    
    String getIdentifier ();
    
    ITokenable getNew ();
    
}
