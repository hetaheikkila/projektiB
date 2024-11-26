package s24.ruokasovelluss.web;

@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/login")
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/signup")
    public String signup(Model model) {
        model.addAttribute("signupform", new SignupForm());
        return "signup";
    }

    @RequestMapping(value = "/saveuser", method = RequestMethod.POST)
    public String save(@Valid @ModelAttribute("signupform") SignupForm signupForm, BindingResult bindingResult) {
        // validating errors
        if (bindingResult.hasErrors()) {
            return "signup";
        }
        // checking password match
        if (!signupForm.getPassword().equals(signupForm.getPasswordCheck())) {
            bindingResult.rejectValue("passwordCheck", "err.passCheck", "Passwords does not match");
            return "signup";
        } else {
            String pwd = signupForm.getPassword();
            BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
            String hashPwd = bc.encode(pwd);

            User newUser = new User();
            newUser.setPasswordHash(hashPwd);
            newUser.setUsername(signupForm.getUsername());
            newUser.setRole("USER");

            // checking if user already exists
            if (userRepository.findByUsername(signupForm.getUsername()) == null) {
                userRepository.save(newUser);
            } else {
                bindingResult.rejectValue("username", "err.username", "Username already exists");
                return "signup";
            }
        }
        return "redirect:/login";
    }
}
