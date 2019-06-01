package hive.caronte.log;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class LoggerController {
  @GetMapping(value = "/log", produces = MediaType.TEXT_HTML_VALUE)
  public String getLog() {
    return Logger.instance.toHtmlTable();
  }
}
