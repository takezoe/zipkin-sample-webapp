package servlet;

import com.github.kristofa.brave.Brave;
import com.github.kristofa.brave.okhttp.BraveTracingInterceptor;
import com.github.kristofa.brave.servlet.BraveServletFilter;
import zipkin.reporter.AsyncReporter;
import zipkin.reporter.Reporter;
import zipkin.reporter.okhttp3.OkHttpSender;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.DispatcherType;
import javax.servlet.annotation.WebListener;
import java.util.EnumSet;

@WebListener
public class InitializeListener implements ServletContextListener {

    private static final String ZIPKIN_SERVER_URL = "http://localhost:9411/api/v1/spans";

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        Reporter reporter = AsyncReporter.builder(OkHttpSender.create(ZIPKIN_SERVER_URL)).build();

        // Register servlet filter
        Brave serverBrave = new Brave.Builder("server").reporter(reporter).build();
        ServletContext context = servletContextEvent.getServletContext();
        context.addFilter("BraveServletFilter", BraveServletFilter.builder(serverBrave).build());
        context.getFilterRegistration("BraveServletFilter").addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");

        // Initialize http client
        HttpClient.initialize(reporter);
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }

}
