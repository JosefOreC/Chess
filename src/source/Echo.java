package source;


public class Echo {
    private static boolean echo;
    private static Echo E;
    
    private Echo(boolean echos){
        echo = echos;
    }
    
    public static boolean echo(boolean echo){
        if (E == null) E = new Echo(echo);
        return E.echo;
    }
    
    public static boolean echo(){
        if (E == null) E = new Echo(true);
        return E.echo;
    }
}
