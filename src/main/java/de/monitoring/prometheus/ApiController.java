package de.monitoring.prometheus;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
@RestController
public class ApiController {
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }
    @GetMapping("/cpurun")
    public ResponseEntity<String> runCpu(
            @RequestParam(value = "divider", defaultValue = "65536") long divider) {
        long counter = 0;
        do {
            counter++;
        } while (counter <= (Long.MAX_VALUE / divider));
        return ResponseEntity.ok("finished");
    }
    @GetMapping("/users/{userId}")
    public ResponseEntity<String> getUser(@PathVariable String userId) {
        return ResponseEntity.ok("user_id: " + userId);
    }
    @GetMapping("/website")
    public ResponseEntity<String> getSite(
            @RequestParam(value = "site", defaultValue = "http://www.example.com") String site) {
        RestTemplate restTemplate = new RestTemplate();
        System.out.println("site: " + site);
        String response = restTemplate.getForObject(site, String.class);
        return ResponseEntity.ok(response);
    }
}
