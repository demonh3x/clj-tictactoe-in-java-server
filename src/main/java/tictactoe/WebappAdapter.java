package tictactoe;

import clojure.lang.IFn;
import clojure.lang.IPersistentMap;
import clojure.lang.ISeq;
import com.github.demonh3x.server.http.Request;
import com.github.demonh3x.server.http.RequestHandler;
import com.github.demonh3x.server.http.Response;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import static clojure.java.api.Clojure.read;

public class WebappAdapter implements RequestHandler {
    private final IFn handler;

    public WebappAdapter(IFn handler) {
        this.handler = handler;
    }

    @Override
    public Response handle(Request request) {
        return parseResponse(handler.invoke(ringRequest(request.getUri())));
    }

    private Object ringRequest(String uri) {
        String[] uriParts = uri.split("\\?");
        String url = uriParts[0];
        String query = uriParts.length > 1 ? uriParts[1] : "";
        String cljMap = String.format(
                "{:request-method :get :uri \"%s\" :query-string \"%s\"}",
                url,
                query
        );
        return read(cljMap);
    }

    private Response parseResponse(Object ringResponse) {
        IPersistentMap map = (IPersistentMap) ringResponse;

        int statusCode = ((Long) map.valAt(read(":status"))).intValue();
        byte[] bodyContent = readBodyContent(map.valAt(read(":body")));
        Map<String, String> headers = new ClojureMap((IPersistentMap) map.valAt(read(":headers")));

        return new Response(statusCode, "", bodyContent, headers);
    }

    private byte[] readBodyContent(Object body) {
        if (body instanceof String)
            return ((String) body).getBytes();

        if (body instanceof InputStream)
            return consume((InputStream) body);

        throw new IllegalArgumentException();
    }

    private byte[] consume(InputStream inputStream) {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        try {
            int b = inputStream.read();
            while (b != -1) {
                buffer.write(b);
                b = inputStream.read();
            }
            return buffer.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static class ClojureMap extends AbstractMap<String, String> {
        private final Set<Entry<String, String>> entries;

        private static Set<Entry<String, String>> entries(IPersistentMap map) {
            HashSet<Entry<String, String>> entries = new HashSet<>();

            for (ISeq sequence = map.seq(); sequence != null; sequence = sequence.next()) {
                final Map.Entry entry = (Map.Entry) sequence.first();

                entries.add(new Entry<String, String>() {
                    @Override
                    public String getKey() {
                        return entry.getKey().toString();
                    }

                    @Override
                    public String getValue() {
                        return entry.getValue().toString();
                    }

                    @Override
                    public String setValue(String value) {
                        return null;
                    }
                });
            }

            return entries;
        }

        public ClojureMap(IPersistentMap map) {
            this.entries = entries(map);
        }

        @Override
        public Set<Entry<String, String>> entrySet() {
            return entries;
        }
    }
}
