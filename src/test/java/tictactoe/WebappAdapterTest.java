package tictactoe;

import clojure.java.api.Clojure;
import com.github.demonh3x.server.http.Request;
import com.github.demonh3x.server.http.Response;
import org.hamcrest.CoreMatchers;
import org.junit.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class WebappAdapterTest {
    @org.junit.Test
    public void sendsTheAppropriateRequestWithQuery() {
        assertThat(requestSentFor("/path/to/route?arg1=val1&arg2=val2"), is(Clojure.read(
                "{:request-method :get :uri \"/path/to/route\" :query-string \"arg1=val1&arg2=val2\"}"
        )));
    }

    @org.junit.Test
    public void sendsTheAppropriateRequestWithoutQuery() {
        assertThat(requestSentFor("/path/to/route"), is(Clojure.read(
                "{:request-method :get :uri \"/path/to/route\" :query-string \"\"}"
        )));
    }

    @Test
    public void returnsTheAppropriateResponseWithAStringBody() {
        Response response = responseReturnedFor(
                "{:status 200 :body \"response body\" :headers {\"key1\" \"value1\" \"key2\" \"value2\"}}"
        );

        assertThat(response.getStatusCode(), is(200));
        assertThat(response.getMessageBody(), is("response body".getBytes()));
        assertThat(response.getHeaders(), CoreMatchers.<Map<String, String>>is(new HashMap<String, String>() {{
            put("key1", "value1");
            put("key2", "value2");
        }}));
    }

    @Test
    public void returnsTheAppropriateResponseWithAnInputStreamBody() {
        Response response = responseReturnedFor(
                "{:status 200 :body (java.io.ByteArrayInputStream. (.getBytes \"response body\")) :headers {\"key1\" \"value1\" \"key2\" \"value2\"}}"
        );

        assertThat(response.getStatusCode(), is(200));
        assertThat(response.getMessageBody(), is("response body".getBytes()));
        assertThat(response.getHeaders(), CoreMatchers.<Map<String, String>>is(new HashMap<String, String>() {{
            put("key1", "value1");
            put("key2", "value2");
        }}));
    }

    private Object requestSentFor(String uri) {
        Object nullResponse = Clj.eval(
                "{:status 200 :body \"response body\" :headers {\"key1\" \"value1\" \"key2\" \"value2\"}}"
        );
        IFnDouble clojureHandler = new IFnDouble(nullResponse);
        WebappAdapter webapp = new WebappAdapter(clojureHandler);
        webapp.handle(new Request("GET", uri, "HTTP1.1", new byte[0], Collections.<String, String>emptyMap()));

        return clojureHandler.getReceivedArguments()[0];
    }

    private Response responseReturnedFor(String ringResponse) {
        IFnDouble clojureHandler = new IFnDouble(Clj.eval(ringResponse));
        WebappAdapter webapp = new WebappAdapter(clojureHandler);
        Request nullRequest = new Request("GET", "/", "HTTP1.1", new byte[0], Collections.<String, String>emptyMap());
        return webapp.handle(nullRequest);
    }
}
