package tictactoe;

import clojure.lang.IFn;

public class Clojure {
    private static final IFn REQUIRE = clojure.java.api.Clojure.var("clojure.core", "require");

    public static IFn function(String namespace, String functionName) {
        REQUIRE.invoke(clojure.java.api.Clojure.read(namespace));
        return clojure.java.api.Clojure.var(namespace, functionName);
    }
}
