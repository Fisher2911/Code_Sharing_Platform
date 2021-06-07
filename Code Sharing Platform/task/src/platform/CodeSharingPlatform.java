package platform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;
import platform.repositories.DatedCodeRepository;


@SpringBootApplication
@RestController
public class CodeSharingPlatform {

    @Autowired
    private DateManager dateManager;

    public static void main(String[] args) {
        SpringApplication.run(CodeSharingPlatform.class, args);
    }

    @Bean
    public CommandLineRunner runApplication(final DatedCodeRepository datedCodeRepository/*,*/
                                            /*final DateManagerRepository dateManagerRepository*/) {
        return (args -> {
            // call methods you want to use
            datedCodeRepository.findAll().forEach(dateManager::addDatedCode);
        });
    }

}
