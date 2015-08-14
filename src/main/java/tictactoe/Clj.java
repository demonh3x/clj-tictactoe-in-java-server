package tictactoe;

import clojure.java.api.Clojure;
import clojure.lang.IFn;

public class Clj {
    private static final IFn REQUIRE = Clojure.var("clojure.core", "require");

    public static IFn fn(String namespace, String functionName) {
        REQUIRE.invoke(Clojure.read(namespace));
        return Clojure.var(namespace, functionName);
    }

    public static Object eval(String clojureCode) {
        return Clojure.var("clojure.core", "eval").invoke(Clojure.read(clojureCode));
    }
}
