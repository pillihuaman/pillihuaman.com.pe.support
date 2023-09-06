package pillihuaman.com.support;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import pillihuaman.com.lib.exception.CustomRestExceptionHandlerGeneric;


import java.util.Collections;




@EnableAsync
@EnableAutoConfiguration(exclude = {ErrorMvcAutoConfiguration.class})
@EnableScheduling
@Import(CustomRestExceptionHandlerGeneric.class)
@SpringBootApplication(scanBasePackages = {"pillihuaman.com.basebd.config", "pillihuaman.com.basebd","pillihuaman.com.security","pillihuaman.com.support"})
public class Application {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Application.class);
        app.setDefaultProperties(Collections.singletonMap("server.port", "8091"));
        app.run(args);
    }


}

