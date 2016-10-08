package app;

import java.util.Random;
import java.util.stream.IntStream;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/urlmappings")
public class UrlMappingManagementController {

    private final UrlMapRepository repository;

    public UrlMappingManagementController(UrlMapRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public Iterable<UrlMap> list() {
        return repository.findAll();
    }

    @GetMapping("{shortUrl}")
    public UrlMap get(@PathVariable String shortUrl) {
        return repository.findOne(shortUrl);
    }

    @PostMapping
    public UrlMap create(@RequestParam String url) {
        IntStream a = IntStream.rangeClosed('A', 'Z');
        IntStream b = IntStream.rangeClosed('a', 'z');
        IntStream c = IntStream.rangeClosed('0', '9');
        int[] xs = IntStream.concat(a, IntStream.concat(b, c)).toArray();
        Random r = new Random();
        int[] ys = IntStream.range(0, 8)
                .map(i -> r.nextInt(xs.length))
                .map(i -> xs[i])
                .toArray();
        String shortUrl = new String(ys, 0, ys.length);
        UrlMap entity = new UrlMap();
        entity.setShortUrl(shortUrl);
        entity.setOriginalUrl(url);
        repository.save(entity);
        return entity;
    }

    @PostMapping("{shortUrl}")
    public void remove(@PathVariable String shortUrl) {
        repository.delete(shortUrl);
    }

}
