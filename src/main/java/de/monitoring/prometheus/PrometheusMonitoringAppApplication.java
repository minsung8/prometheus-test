package de.monitoring.prometheus;
import java.io.IOException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.prometheus.client.exporter.HTTPServer;
import io.prometheus.client.hotspot.DefaultExports;
@SpringBootApplication
public class PrometheusMonitoringAppApplication {
    public static void main(String[] args) throws IOException {
        DefaultExports.initialize();
        HTTPServer server = new HTTPServer.Builder()
                .withPort(1234)
                .build();
        SpringApplication.run(PrometheusMonitoringAppApplication.class, args);
    }
}
