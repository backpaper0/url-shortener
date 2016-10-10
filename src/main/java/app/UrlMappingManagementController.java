package app;

import java.util.Random;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/urlmappings")
public class UrlMappingManagementController {

    private final UrlMapRepository repository;

    @Autowired
    public UrlMappingManagementController(UrlMapRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model) {
        Iterable<UrlMap> urlMaps = repository.findAll();
        model.addAttribute("urlMaps", urlMaps);
        return "mappings";
    }

    //    @RequestMapping(method = RequestMethod.GET, value = "{shortUrl}")
    //    public UrlMap get(@PathVariable String shortUrl) {
    //        return repository.findOne(shortUrl);
    //    }

    @RequestMapping(method = RequestMethod.POST)
    public String create(@RequestParam String url) {
        String urlChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random r = new Random();
        int[] cs = IntStream.range(0, 8)
                .map(i -> r.nextInt(urlChars.length()))
                .map(i -> urlChars.codePointAt(i))
                .toArray();
        String shortUrl = new String(cs, 0, cs.length);
        UrlMap entity = new UrlMap();
        entity.setShortUrl(shortUrl);
        entity.setOriginalUrl(url);
        repository.save(entity);
        return "redirect:/urlmappings";
    }

    @RequestMapping(method = RequestMethod.POST, value = "{shortUrl}")
    public String remove(@PathVariable String shortUrl) {
        repository.delete(shortUrl);
        return "redirect:/urlmappings";
    }
}
