package app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UrlShortenerApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(UrlShortenerApplication.class, args);
    }

    @Autowired
    UrlMapRepository r;

    @Override
    public void run(String... args) throws Exception {
        UrlMap entity = new UrlMap();
        entity.setShortUrl("ggrks");
        entity.setOriginalUrl("https://www.google.co.jp/");
        r.save(entity);
    }
}
