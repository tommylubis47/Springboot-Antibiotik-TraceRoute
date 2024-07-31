
package com.WebApp.RequestAntibiotik.WebController.DOKTER;

import jakarta.servlet.http.HttpServletRequest;
import java.security.Principal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DokterMainController {
    
    @RequestMapping(value="/homepage/dokter")
    public String getHompageDokter() {
        
         Authentication auth = SecurityContextHolder.getContext().getAuthentication(); 
         
          if(auth == null) { 
            return "redirect:/userLogin"; 
         } else { 
            return "/dokter/index";
          }
    }
    
}
