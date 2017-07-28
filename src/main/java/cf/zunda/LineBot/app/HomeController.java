package cf.zunda.LineBot.app;

import cf.zunda.LineBot.service.LineBotService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author NGN5.
 * @since 2017/06/13.
 */
@RestController
@RequiredArgsConstructor
public class HomeController {

    final LineBotService lineBotService;

    @RequestMapping("/")
    public String home(Model model){
        model.addAttribute("message", "Hello, home");
        return "HomeController.home()";
    }

    @RequestMapping("trans")
    public String translate(Model model, @RequestParam("text")String text){
        String translated = lineBotService.replyMessage(text);

        model.addAttribute("translated", translated);
        return translated;
    }

}
