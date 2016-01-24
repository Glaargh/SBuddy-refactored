
public class ClientMethods 
{
    public static String Login(String ID, String Pass) 
    {
            return "INCOMING-LOGIN¿"+ID+"¿"+Pass;
    }
    public static String Register(String Firstname, String Lastname, String ID, String Pass)
    {
            return "INCOMING-REGISTER¿"+ID+"¿"+Pass+"¿"+Firstname+"¿"+Lastname;
    }

    
}
