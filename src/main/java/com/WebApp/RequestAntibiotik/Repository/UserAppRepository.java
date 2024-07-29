
package com.WebApp.RequestAntibiotik.Repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.WebApp.RequestAntibiotik.Entity.UserApp;

public interface UserAppRepository extends JpaRepository<UserApp,Integer>  {
    public UserApp findByEmail(String emaill);
}
