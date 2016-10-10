package app;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ExpandUrlController {

    private final UrlMapRepository repository;

    @Autowired
    public ExpandUrlController(UrlMapRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(method = RequestMethod.GET, value = "{shortUrl}")
    public String expand(@PathVariable String shortUrl) {
        String originalUrl = Optional.ofNullable(repository.findOne(shortUrl))
                .map(UrlMap::getOriginalUrl)
                .orElseThrow(UrlMappingNotFoundException::new);
        return "redirect:" + originalUrl;
    }
}
