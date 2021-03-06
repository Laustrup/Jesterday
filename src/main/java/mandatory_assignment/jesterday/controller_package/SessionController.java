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
    private ArrayList<Joke> jokes = new ArrayList<>();
    private HttpSession session;

    @GetMapping("/")
    public String goBack() {
        return "index.html";
    }

    @GetMapping("/submit.html")
    public String makeAJoke() {
        return "submit.html";
    }

    @PostMapping("/submit-post")
    public String submitJoke(HttpServletRequest request, Model model,
                             @RequestParam(name = "title") String title,
                             @RequestParam(name = "content") String content,
                             @RequestParam(name = "date") String date,
                             @RequestParam(name = "publicity", required = false) boolean publicity,
                             @RequestParam(name = "politicalcorrect", required = false) boolean politicalcorrect) {

        //If none checkboxes are edited
        if (publicity != true) {
            publicity = Boolean.FALSE.equals(publicity);
        }
        if (politicalcorrect != true) {
            politicalcorrect = Boolean.FALSE.equals(politicalcorrect);
        }

        joke = new Joke(title,content,date,publicity,politicalcorrect);

        jokes.add(joke);

        session = request.getSession();
        session.setAttribute("sessionJoke",joke);

        System.out.println("Joke is " + jokes.get(jokes.size()-1));

        return "redirect:/success.html";
    }


    @GetMapping("/success.html")
    public String directToSuccess(Model model) {

        model.addAttribute("currentJoke", session.getAttribute("sessionJoke"));

        return "success.html";
    }

    @GetMapping("/jokeboard.html")
    public String showJokeBoard(Model model) {
        model.addAttribute("allJoke",jokes);
        return "jokeboard.html";
    }

}
