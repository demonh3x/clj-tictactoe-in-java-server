package tictactoe;

import com.github.demonh3x.server.Server;
import com.github.demonh3x.server.ThreadedHandler;
import com.github.demonh3x.server.http.Http;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        final WebappAdapter webapp = new WebappAdapter(Clojure.function("tictactoe.web", "webapp"));
        ExecutorService executor = Executors.newCachedThreadPool();
        new Server(executor, 8080, new ThreadedHandler(executor, new Http(webapp))).start();

        System.out.println("Server running at port 8080.");
    }
}
