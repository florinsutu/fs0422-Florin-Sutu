package com.florinsutu.capstone.configurations;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.florinsutu.capstone.models.Role;
import com.florinsutu.capstone.models.User;
import com.florinsutu.capstone.services.RoleService;
import com.florinsutu.capstone.services.UserService;

@Slf4j
@Component
@Order(2)
public class SetupRunner implements CommandLineRunner {

    AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(SetupConfig.class);

    @Autowired
    RoleService rs;
    
    @Autowired
    UserService us;

    @Override
    public void run(String... args) throws Exception {

        log.info("RUNNER SETUP START");

        createData();

        log.info("RUNNER SETUP END");

    }

    private void createData() {

//        rs.save(ctx.getBean("roleAdmin", Role.class));
//        rs.save(ctx.getBean("roleUser", Role.class));

//        us.save(ctx.getBean("user1", User.class));
        
//        us.save(ctx.getBean("user2", User.class));
//        us.save(ctx.getBean("user3", User.class));
//
//        as.save(ctx.getBean("address1", Address.class));
//
//        custS.save(ctx.getBean("customer1", Customer.class));
//
//        is.save(ctx.getBean("invoice1", Invoice.class));


    }
}
