
package com.WebApp.RequestAntibiotik.WebController;

import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {
    
    @GetMapping(value="/index")
    public String index() { 
        return "index";
    }
    
    
    @GetMapping(value="/userLogin")
    public String userLogin() { 
        return "login";
    }
    
    @PostMapping(value="/processLogin") 
    public String processLogin(MultiValueMap<String,Object> mapParam){ 
        
        return "OK";
    }
    
}
