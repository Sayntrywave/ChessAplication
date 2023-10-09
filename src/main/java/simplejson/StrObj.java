package simplejson;

public class StrObj extends Obj {

    private String string;

    public StrObj(String s) {
        super(Type.STR);
        string = s;
    }

    public StrObj() {
        this("");
    }

    @Override
    public String val() {
        return string;
    }

    @Override
    public String set(String s) {
        string = s;
        return s;
    }

    public static StrObj fromString(String s) {
        return new StrObj(s.trim());
    }

    public String toString() {
        return val();
    }
}
