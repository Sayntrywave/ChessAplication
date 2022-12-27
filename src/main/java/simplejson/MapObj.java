package simplejson;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MapObj extends Obj {

    private final Map<String, Obj> map = new HashMap<>();

    public MapObj() {
        super(Type.MAP);
    }

    public MapObj(Map<String, String> map) {
        this();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            put(entry.getKey(), Obj.fromString(entry.getValue()));
        }
    }

    @Override
    public Obj get(String s) {
        return map.get(s);
    }

    @Override
    public Obj put(String key, Obj value) {
        map.put(key, value);
        return value;
    }

    @Override
    public Obj put(String key, String value) {
        return put(key, Obj.fromString(value));
    }

    public static MapObj fromString(String s) {
        char[] a = s.toCharArray();
        MapObj mapObj = new MapObj();
        if (a.length == 0) {
            return mapObj;
        }

        // todo check sequence if its correct
        int l = 0, r, k = 0;
        k += a[0] == '[' || a[0] == '{' ? 1 : 0;
        String key = null;
        for (r = 1; r < a.length; r++) {
            if (k == 0 && (a[r] == ':' || a[r] == ',')) {
                if (key == null) {
                    key = s.substring(l, r);
                } else {
                    mapObj.put(key.trim(), s.substring(l, r));
                    key = null;
                }
                l = r + 1;
            } else if (a[r] == '[' || a[r] == '{') {
                k++;
            } else if (a[r] == ']' || a[r] == '}') {
                k--;
            }
        }
        mapObj.put(key.trim(), s.substring(l, r));
        return mapObj;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        for (Map.Entry<String, Obj> entry : map.entrySet()) {
            sb.append(entry.getKey()).append(":").append(entry.getValue()).append(",");
        }
        if (map.size() > 0) {
            sb.delete(sb.length() - 1, sb.length());
        }
        sb.append("}");
        return sb.toString();
    }

    @Override
    public Iterator<Map.Entry<String, Obj>> iterator() {
        return map.entrySet().iterator();
    }
}
