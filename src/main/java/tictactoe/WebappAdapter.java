package tictactoe;

import clojure.lang.IFn;
import clojure.lang.IPersistentMap;
import clojure.lang.ISeq;
import com.github.demonh3x.server.http.Request;
import com.github.demonh3x.server.http.RequestHandler;
import com.github.demonh3x.server.http.Response;

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
        Map<String, String> headers = new CljMap(map.valAt(read(":headers")));
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
        byte allData[] = new byte[16000];
        try {
            int bytesRead = inputStream.read(allData);
            return Arrays.copyOfRange(allData, 0, bytesRead);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static class CljMap extends AbstractMap<String, String> {
        private final Set<Entry<String, String>> entries;

        private static Set<Entry<String, String>> entries(IPersistentMap map) {
            HashSet<Entry<String, String>> entries = new HashSet<>();
            for (ISeq s=map.seq(); s != null; s=s.next()) {
                final Map.Entry e = (Map.Entry) s.first();
                entries.add(new Entry<String, String>() {
                    @Override
                    public String getKey() {
                        return e.getKey().toString();
                    }

                    @Override
                    public String getValue() {
                        return e.getValue().toString();
                    }

                    @Override
                    public String setValue(String value) {
                        return null;
                    }
                });
            }
            return entries;
        }

        public CljMap(Object map) {
            this.entries = entries((IPersistentMap) map);
        }

        @Override
        public Set<Entry<String, String>> entrySet() {
            return entries;
        }
    }
}
