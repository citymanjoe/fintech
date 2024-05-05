package com.zenpay.fintech;

import com.zenpay.fintech.model.dto.enumv.AccountTypeEnum;
import com.zenpay.fintech.model.dto.enumv.CurrencyEnum;
import com.zenpay.fintech.model.entity.Accounts;
import com.zenpay.fintech.model.entity.Roles;
import com.zenpay.fintech.model.entity.Users;
import com.zenpay.fintech.repository.RoleRepository;
import com.zenpay.fintech.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.lang.NonNullApi;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.zenpay.fintech.util.HelperUtils.generateRandomNumber;

@Component
@Slf4j
public class SetUpLoader implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    RoleRepository roleRepos;

    @Autowired
    UserRepository userRepos;

    boolean alreadySetup = false;
    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (alreadySetup)
            return;
        Roles user = createRoleIfNotFound("ROLE_USER","ROLE USER");
        Roles admin = createRoleIfNotFound("ROLE_ADMIN","ROLE ADMIN");
        Roles sup = createRoleIfNotFound("ROLE_SUPER","ROLE SUPER");
        List<Roles> listRole = new ArrayList<>();
        listRole.add(user);
        listRole.add(admin);
        listRole.add(sup);
        Users admin_user = createUseIfNotFound(listRole);
        log.info(String.valueOf(admin_user));
        alreadySetup = true;
    }

    @Transactional
    Roles createRoleIfNotFound (String name, String desc) {
        Optional<Roles> roles = roleRepos.findByName(name);
        Roles role = new Roles(name, desc);
        if (roles.isPresent()) {
            role = roles.get();
        }else {
            role = roleRepos.save(role);
        }
        return role;
    }
    @Transactional
    Users createUseIfNotFound (List<Roles> roles) {
        if (this.userRepos.findByEmail("admin@zenpay.com").isEmpty()) {
            String accountNo = "NGN001" + generateRandomNumber(10);
            Accounts account = new Accounts(BigDecimal.ZERO, accountNo, "ZENPAY COLLECTION ACCOUNT", AccountTypeEnum.OFFICEACCOUNT, new Date(), CurrencyEnum.NAIRA);
            List<Accounts> listAccount = new ArrayList<>();
            listAccount.add(account);
            String encryptPassword = "infy@123";
            String password = (new BCryptPasswordEncoder(11)).encode(encryptPassword);
            Users user = new Users("admin@zenpay.com", "09010023801", new Date(), "ZENPAY COLLECTION ACCOUNT", "ZENPAY VICTORIA ISLAND", "", "", true, new Date(), password, listAccount, roles);
            userRepos.save(user);
        }
        Optional<Users> u = this.userRepos.findByEmail("admin@zenpay.com");
        return u.orElse(null);
    }

    @Override
    public boolean supportsAsyncExecution() {
        return ApplicationListener.super.supportsAsyncExecution();
    }
}
