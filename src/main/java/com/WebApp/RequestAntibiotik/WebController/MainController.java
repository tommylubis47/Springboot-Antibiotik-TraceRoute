
package com.WebApp.RequestAntibiotik.WebController;

import com.WebApp.RequestAntibiotik.Entity.UserApp;
import com.WebApp.RequestAntibiotik.Service.UserApp.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.security.Principal;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class MainController {
    
    
    @Autowired 
    UserServiceImpl userService; 
    
    @GetMapping(value="/index")
    public String index() { 
        
         Authentication auth = SecurityContextHolder.getContext().getAuthentication(); 
         
          if(auth == null) { 
            return "redirect:/userLogin"; 
          } else { 
              Set<String> roles = auth.getAuthorities()
                                .stream()
                                .map(r -> r.getAuthority())
                                .collect(Collectors.toSet());

              String roleName = roles.iterator().next();
              
              if(roleName.toUpperCase().equals("DOKTER")) { 
                return "redirect:/homepage/dokter";
              }
          }
        return "index";
    }
    
    
    @GetMapping(value="/userLogin")
    public String userLogin() { 
        return "login";
    }
    
    @GetMapping(value="/user/profile") 
    public String userProfileSession(Principal p,HttpServletRequest request) { 
      
        System.out.println("user profile dipanggil");
        
        HttpSession session = request.getSession();
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        
        if(auth == null) { 
            return "redirect:/userLogin";
        } else {   
            
            System.out.println("------- user from spring security ----------");
            System.out.println("--------------------------------------------");
            System.out.println(" Active Login ==> "+ auth.getPrincipal());
        
            String username = "";

            if (auth.getPrincipal() instanceof UserDetails) {
               username = ((UserDetails)auth.getPrincipal()).getUsername();
            } else {
               username = auth.getPrincipal().toString();
            }

            System.out.println(" Username ===> " + username);

            System.out.println("--------------------------------------------");
            if(session.getAttribute("username") == null) { 
                //pertama kali 
                session.setAttribute("username", username);
            } else { 

                String userSession = session.getAttribute("username").toString();

                System.out.println("User session : " + userSession);
                
                if(username != userSession) {
                    session.invalidate();
                    return "redirect:/userLogin";
                } 
            }
            
            UserDetails userdetails = (UserDetails)auth.getPrincipal();

            Set<String> roles = auth.getAuthorities()
                                .stream()
                                .map(r -> r.getAuthority())
                                .collect(Collectors.toSet());

            String roleName = roles.iterator().next();

            System.out.println("User has authorities: " + roleName);
            
            session.setAttribute("role", roleName);

            if(roleName.toUpperCase().equals("DOKTER")) { 
                return "redirect:/homepage/dokter";
            } else { 
                return "index";
            }
            
        }
    }
    
//    @PostMapping(value="/processLogin") 
//    public String processLogin(@RequestBody MultiValueMap<String,Object> mapParam){ 
//        
//        return "OK";
//    }
    
    @GetMapping(value="/user/registration")
    public String userRegistration() { 
        return "user_registration_page";
    }
    
    
    @PostMapping(value="/user/processRegistration")
    public String userRegistration(@RequestBody MultiValueMap<String,Object> mapParam) { 
        
        String email = mapParam.get("inputEmail").getFirst().toString();
        String password = mapParam.get("inputPassword").getFirst().toString();
        String firstName = mapParam.get("firstName").getFirst().toString();
        String lastName = mapParam.get("lastName").getFirst().toString();
        String role = mapParam.get("role").getFirst().toString();
        
        UserApp objUser = new UserApp(); 
        
        objUser.setEmail(email);
        objUser.setPassword(password);
        objUser.setFirstName(firstName);
        objUser.setLastName(lastName);
        objUser.setRole(role);
        
        UserApp newUser = userService.saveUser(objUser);
         
        if(newUser != null) { 
            System.out.println("Save Success");
        } else  {
            System.out.println("Failed Save");
        }
                
        return "registration_success";
    }
}
