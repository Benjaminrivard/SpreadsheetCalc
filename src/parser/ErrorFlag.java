package parser;

public class ErrorFlag {

    private static boolean flag = false;

    public static void setFlag()
    {
        flag = true;
    }
    public static void reset()
    {
        flag = false;
    }

    public static boolean getErrorFlag()
    {
        return ErrorFlag.flag;
    }

}
