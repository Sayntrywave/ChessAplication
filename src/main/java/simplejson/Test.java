package simplejson;

import java.util.Map;

public class Test {
    static class F {
        int a;
        int b;
    }
    public static void main(String[] args) {
        Obj arr = new ArrObj();
        for (int i = 0; i < 10; i++) {
            F f = new F();
            f.a = i;
            f.b = 10 - i;

            Obj fObj = new MapObj();
            fObj.put("a", String.valueOf(f.a));
            fObj.put("b", String.valueOf(f.b));

            for (Map.Entry<String, Obj> entry : fObj) {
                System.out.println("key: " + entry.getKey() + " " + entry.getValue());
            }

            arr.append(fObj);
        }

//        System.out.println(arr);

        // out.printLine(arr.toString()

        // s = in.readLine()
        String s = arr.toString();
        Obj response = Obj.fromString(s);
        for (Obj o : response.toList()) {
            System.out.println(o);
            int a = Integer.parseInt(o.get("a").val());
            int b = Integer.parseInt(o.get("b").val());
//            System.out.println(a + " " + b);
        }
    }
}
