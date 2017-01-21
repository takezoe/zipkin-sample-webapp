package servlet;

import com.github.kristofa.brave.Brave;
import com.github.kristofa.brave.okhttp.BraveTracingInterceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import zipkin.reporter.Reporter;

import java.io.IOException;

public class HttpClient {

    private static OkHttpClient client;

    public static void initialize(Reporter reporter){
        Brave clientBrave = new Brave.Builder("client").reporter(reporter).build();
        BraveTracingInterceptor tracingInterceptor = BraveTracingInterceptor.create(clientBrave);

        client = new OkHttpClient.Builder()
                .addInterceptor(tracingInterceptor)
                .addNetworkInterceptor(tracingInterceptor)
                .build();
    }

    public static String get(String url) throws IOException {
        Request request = new Request.Builder().url(url).build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

}
