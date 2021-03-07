package mandatory_assignment.jesterday.controller_package;

import mandatory_assignment.jesterday.objects.Joke;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
public class SessionController {

    private Joke joke;
    private ArrayList<Joke> jokes = new ArrayList<>();
    private HttpSession session;

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
        return "makeajoke.html";
    }

    @GetMapping("/jokeboard.html")
    public String viewJokes() {
        return "jokeboard.html";
    }

    @PostMapping("/submit")
    public String submitJoke(HttpServletRequest request,
                             @RequestParam("title") String title,
                             @RequestParam("content") String content,
                             @RequestParam("date") String date,
                             @RequestParam("publicity") boolean publicity,
                             @RequestParam("politicalcorrect") boolean politicalcorrect, Model model) {

        model.addAttribute("jokeList",session.getAttribute("jokes"));

        joke = new Joke(title,content,date,publicity,politicalcorrect);

        model.addAttribute("jokeList",joke);

        session = request.getSession();

        session.setAttribute("jokes",model.getAttribute("jokeList"));

        return "redirect:/success.html";
    }

    @GetMapping("/jokeboard")
    public String showJokeBoard(Model model) {
        model.addAttribute("jokeList",session.getAttribute("jokes"));

        return "jokeboard.html";
    }
}