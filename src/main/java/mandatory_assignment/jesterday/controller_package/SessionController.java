package mandatory_assignment.jesterday.controller_package;

import mandatory_assignment.jesterday.objects.Joke;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
public class SessionController {

    private Joke joke;
    private HttpSession session;

    //Wrote getmapping urls as .html in order to make it dynamic testing through springboot and HTMLs
    @GetMapping("/")
    public String introduction() {
        return "index.html";
    }

    @GetMapping("/index.html")
    public String goBack() {
        return "index.html";
    }

    @GetMapping("/makeajoke.html")
    public String beginJoke() {
        return "submit.html";
    }


    @GetMapping("/submit-post")
    public String submitJoke(HttpServletRequest request, Model model,
                             @RequestParam(name = "title") String title,
                             @RequestParam(name = "content") String content,
                             @RequestParam(name = "date") String date,
                             @RequestParam(name = "publicity") boolean publicity,
                             @RequestParam(name = "politicalcorrect") boolean politicalcorrect) {

        System.out.println("Arrived in submit-post...");

        model.addAttribute("jokeList",session.getAttribute("jokes"));

        joke = new Joke(title,content,date,publicity,politicalcorrect);

        model.addAttribute("jokeList",joke);

        session = request.getSession();

        session.setAttribute("currentJoke", joke);
        session.setAttribute("jokes",model.getAttribute("jokeList"));

        System.out.println("Joke is " + joke.toString());

        return "redirect:/success.html";
    }

    @GetMapping("/jokeboard.html")
    public String showJokeBoard(Model model) {
        model.addAttribute("jokeList",session.getAttribute("jokes"));

        return "jokeboard.html";
    }

}
