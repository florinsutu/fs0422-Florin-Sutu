package com.florinsutu.capstone.configurations;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.florinsutu.capstone.models.Role;
import com.florinsutu.capstone.models.RoleType;
import com.florinsutu.capstone.models.User;

@Configuration
@Order(1)
public class SetupConfig {

	@Bean(name = "roleAdmin")
	@Scope("singleton")
	public Role roleAdmin() {
		
		return Role.builder()
				.roleType(RoleType.ROLE_ADMIN)
				.build();

	}

	@Bean(name = "roleUser")
	@Scope("singleton")
	public Role roleUser() {
		
		return Role.builder()
				.roleType(RoleType.ROLE_USER)
				.build();
		
	}

//    @Bean(name = "user1")
//    @Scope("singleton")
//    public User user1() {
//        User user = User.builder()
//        		.name("Florin")
//        		.email("fsutu96@gmail.com")
//        		.lastname("Sutu")
//        		.username("fsutu")
//				.password(encoder.encode("test"))
//        		.roles(new HashSet<Role>())
//        		.build();
//        		
//         user.addRole(roleAdmin());
//        return user;
//    }

//    @Bean(name = "user2")
//    @Scope("singleton")
//    public User user2() {
//        User u = new User("piera", "Giacomino", "Poretti", "sugar@garolfo.cops", "test");
//        u.getRoles().add(roleUser());
//        return u;
//    }
//
//    @Bean(name = "user3")
//    @Scope("singleton")
//    public User user3() {
//        User u = new User("Pdor", "Giovanni", "Storti", "tiger@garolfo.cops", "test");
//        u.getRoles().add(roleAdmin());
//        u.getRoles().add(roleUser());
//        return u;
//    }

//    @Bean(name = "comune1")
//    @Scope("singleton")
//    public Comune comune1(){
//
//        Optional<Comune> c1 = cs.getByName("Vigonza");
//        if(c1.isPresent()){
//            return c1.get();
//        }else{
//            System.out.println("NON SEI NELL IF");
//            return null;
//        }
//
//    }
//
//
//    @Bean(name = "address1")
//    @Scope("prototype")
//    public Address address1(){
//        System.out.println("PRIMA");
//        Optional<Comune> c = cs.getByName("Vigonza");
//
//
//        if (c.isPresent()) {
//        //if(comune1() != null){
//
//            Address a = new Address(
//                    "Via Roma",
//                    15,
//                    35010,
//                    c.get()
//                    //comune1()
//            );
//            System.out.println("DOPO");
//
//            return a;
//        }
//
//        return null;
//    }
//
//    @Bean
//    @Scope("prototype")
//    public Customer customer1() {
//
//        if(address1() != null) {
//
//            Customer c = new Customer(
//                    "Evergreen di Mario Rossi",
//                    "07643520567",
//                    "Evergreen@gmail.com",
//                    10000d,
//                    "Evergreen@alibaba.com",
//                    "3214567216",
//                    "SuperMario@gmail.com",
//                    "Mario",
//                    "Rossi",
//                    "3214796652",
//                    address1(),
//                    CompanyType.SRL
//            );
//
//            return  c;
//        }
//
//        return null;
//    }
//
//    @Bean
//    @Scope("prototype")
//    public Invoice invoice1() {
//        return new Invoice(
//                new BigDecimal("1234.80"),
//                12,
//                customer1()
//        );
//    }

}
