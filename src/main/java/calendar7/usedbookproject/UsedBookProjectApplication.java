package calendar7.usedbookproject;


import calendar7.usedbookproject.Service.FileUpload.StorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(StorageProperties.class)
@SpringBootApplication
public class UsedBookProjectApplication {

    public static void main(String[] args)
    {
        SpringApplication.run(UsedBookProjectApplication.class, args);
    }


}
