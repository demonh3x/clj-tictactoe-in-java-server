package tictactoe;

import clojure.lang.IFn;
import clojure.lang.ISeq;

public class IFnDouble implements IFn {
    private final Object returnValue;
    private Object[] receivedArguments = null;

    public IFnDouble(Object returnValue) {
        this.returnValue = returnValue;
    }

    public Object[] getReceivedArguments() {
        return receivedArguments;
    }

    @Override
    public Object invoke() {
        return null;
    }

    @Override
    public Object invoke(Object o) {
        receivedArguments = new Object[] {o};
        return returnValue;
    }

    @Override
    public Object invoke(Object o, Object o1) {
        return null;
    }

    @Override
    public Object invoke(Object o, Object o1, Object o2) {
        return null;
    }

    @Override
    public Object invoke(Object o, Object o1, Object o2, Object o3) {
        return null;
    }

    @Override
    public Object invoke(Object o, Object o1, Object o2, Object o3, Object o4) {
        return null;
    }

    @Override
    public Object invoke(Object o, Object o1, Object o2, Object o3, Object o4, Object o5) {
        return null;
    }

    @Override
    public Object invoke(Object o, Object o1, Object o2, Object o3, Object o4, Object o5, Object o6) {
        return null;
    }

    @Override
    public Object invoke(Object o, Object o1, Object o2, Object o3, Object o4, Object o5, Object o6, Object o7) {
        return null;
    }

    @Override
    public Object invoke(Object o, Object o1, Object o2, Object o3, Object o4, Object o5, Object o6, Object o7, Object o8) {
        return null;
    }

    @Override
    public Object invoke(Object o, Object o1, Object o2, Object o3, Object o4, Object o5, Object o6, Object o7, Object o8, Object o9) {
        return null;
    }

    @Override
    public Object invoke(Object o, Object o1, Object o2, Object o3, Object o4, Object o5, Object o6, Object o7, Object o8, Object o9, Object o10) {
        return null;
    }

    @Override
    public Object invoke(Object o, Object o1, Object o2, Object o3, Object o4, Object o5, Object o6, Object o7, Object o8, Object o9, Object o10, Object o11) {
        return null;
    }

    @Override
    public Object invoke(Object o, Object o1, Object o2, Object o3, Object o4, Object o5, Object o6, Object o7, Object o8, Object o9, Object o10, Object o11, Object o12) {
        return null;
    }

    @Override
    public Object invoke(Object o, Object o1, Object o2, Object o3, Object o4, Object o5, Object o6, Object o7, Object o8, Object o9, Object o10, Object o11, Object o12, Object o13) {
        return null;
    }

    @Override
    public Object invoke(Object o, Object o1, Object o2, Object o3, Object o4, Object o5, Object o6, Object o7, Object o8, Object o9, Object o10, Object o11, Object o12, Object o13, Object o14) {
        return null;
    }

    @Override
    public Object invoke(Object o, Object o1, Object o2, Object o3, Object o4, Object o5, Object o6, Object o7, Object o8, Object o9, Object o10, Object o11, Object o12, Object o13, Object o14, Object o15) {
        return null;
    }

    @Override
    public Object invoke(Object o, Object o1, Object o2, Object o3, Object o4, Object o5, Object o6, Object o7, Object o8, Object o9, Object o10, Object o11, Object o12, Object o13, Object o14, Object o15, Object o16) {
        return null;
    }

    @Override
    public Object invoke(Object o, Object o1, Object o2, Object o3, Object o4, Object o5, Object o6, Object o7, Object o8, Object o9, Object o10, Object o11, Object o12, Object o13, Object o14, Object o15, Object o16, Object o17) {
        return null;
    }

    @Override
    public Object invoke(Object o, Object o1, Object o2, Object o3, Object o4, Object o5, Object o6, Object o7, Object o8, Object o9, Object o10, Object o11, Object o12, Object o13, Object o14, Object o15, Object o16, Object o17, Object o18) {
        return null;
    }

    @Override
    public Object invoke(Object o, Object o1, Object o2, Object o3, Object o4, Object o5, Object o6, Object o7, Object o8, Object o9, Object o10, Object o11, Object o12, Object o13, Object o14, Object o15, Object o16, Object o17, Object o18, Object o19) {
        return null;
    }

    @Override
    public Object invoke(Object o, Object o1, Object o2, Object o3, Object o4, Object o5, Object o6, Object o7, Object o8, Object o9, Object o10, Object o11, Object o12, Object o13, Object o14, Object o15, Object o16, Object o17, Object o18, Object o19, Object... objects) {
        return null;
    }

    @Override
    public Object applyTo(ISeq iSeq) {
        return null;
    }

    @Override
    public Object call() throws Exception {
        return null;
    }

    @Override
    public void run() {

    }
}
