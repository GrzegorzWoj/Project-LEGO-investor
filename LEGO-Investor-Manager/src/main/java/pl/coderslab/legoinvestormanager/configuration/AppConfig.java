//package pl.coderslab.legoinvestormanager.configuration;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//import javax.persistence.EntityManagerFactory;
//import javax.validation.Validator;
//
//@Configuration
//@EnableWebMvc
//@EnableTransactionManagement
//@EnableJpaRepositories(basePackages = "pl.coderslab.legoinvestormanager")
//@ComponentScan(basePackages = "pl.coderslab.legoinvestormanager")
//public class AppConfig implements WebMvcConfigurer {
//
//    @Bean
//    public LocalEntityManagerFactoryBean entityManagerFactory() {
//        LocalEntityManagerFactoryBean entityManagerFactoryBean
//                = new LocalEntityManagerFactoryBean();
//        entityManagerFactoryBean.setPersistenceUnitName("articlesPersistenceUnit");
//        return entityManagerFactoryBean;
//    }
//    @Bean
//    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
//        JpaTransactionManager jpaTransactionManager =
//                new JpaTransactionManager(entityManagerFactory);
//        return jpaTransactionManager;
//    }
//
//    @Bean
//    public Validator validator() {
//        return new LocalValidatorFactoryBean();
//    }
//
//}