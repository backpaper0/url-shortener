package app;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ExpandUrlController {

    private final UrlMapRepository repository;

    public ExpandUrlController(UrlMapRepository repository) {
        this.repository = repository;
    }

    @GetMapping("{shortUrl}")
    public String expand(@PathVariable String shortUrl) {
        String originalUrl = Optional.ofNullable(repository.findOne(shortUrl))
                .map(UrlMap::getOriginalUrl)
                .orElseThrow(UrlMappingNotFoundException::new);
        return "redirect:" + originalUrl;
    }
}
