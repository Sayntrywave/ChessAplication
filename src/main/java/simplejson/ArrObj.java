package simplejson;

import java.util.*;

public class ArrObj extends Obj {

    private final List<Obj> objects = new LinkedList<>();

    public ArrObj() {
        super(Type.ARR);
    }

    public ArrObj(List<String> objects) {
        this();
        this.objects.addAll(objects.stream().map(Obj::fromString).toList());
    }

    @Override
    public Obj get(Integer i) {
        return objects.get(i);
    }

    @Override
    public Obj append(Obj o) {
        objects.add(o);
        return o;
    }

    @Override
    public Obj append(String s) {
        return append(Obj.fromString(s));
    }

    public static ArrObj fromString(String s) {
        char[] a = s.toCharArray();
        ArrObj arrObj = new ArrObj();
        if (a.length == 0) {
            return arrObj;
        }

        // todo check sequence if its correct using stack
        // todo handle trailing comma
        int l = 0, r, k = 0;
        k += a[0] == '[' || a[0] == '{' ? 1 : 0;
        for (r = 1; r < a.length; r++) {
            if (k == 0 && a[r] == ',') {
                arrObj.append(s.substring(l, r));
                l = r + 1;
            } else if (a[r] == '[' || a[r] == '{') {
                k++;
            } else if (a[r] == ']' || a[r] == '}') {
                k--;
            }
        }
        arrObj.append(s.substring(l, r));
        return arrObj;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (Obj o : objects) {
            sb.append(o.toString()).append(",");
        }
        if (objects.size() > 0) {
            sb.delete(sb.length() - 1, sb.length());
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public List<Obj> toList() {
        return objects;
    }

    @Override
    public Iterator<Map.Entry<String, Obj>> iterator() {
        Map<String, Obj> map = new HashMap<>();
        for (int i = 0; i < objects.size(); i++) {
            map.put(String.valueOf(i), objects.get(i));
        }
        return map.entrySet().iterator();
    }
}
