package calendar7.usedbookproject.Security;

import calendar7.usedbookproject.Service.UserInfo;
import calendar7.usedbookproject.Service.UserService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DbInit implements InitializingBean {

    @Autowired
    private UserService userService;

    @Override
    public void afterPropertiesSet() throws Exception {
        if (!userService.findUser("user").isPresent()) {
            UserInfo user = userService.save(UserInfo.builder()
                    .userId(20l)
                    .userIdentity("user")
                    .email("user@test.com")
                    .password("1111")
                    .nickname("닉닉이")
                    .phonenumber("01011112222")
                    .username("testName")
                    .enabled(true)
                    .build());
            userService.addAuthority(user.getUserId(), "ROLE_USER");
        }
    }
}
