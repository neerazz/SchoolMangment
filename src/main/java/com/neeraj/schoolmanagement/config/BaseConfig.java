package com.neeraj.schoolmanagement.config;

import com.neeraj.schoolmanagement.domain.Globals;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class BaseConfig {

    @Bean
    public Globals getGlobals(@Value("${global.school}") String school,
                              @Value("${global.freshman}") String freshman,
                              @Value("${global.sophomore}") String sophomore,
                              @Value("${global.junior}") String junior,
                              @Value("${global.senior}") String senior) {
        return new Globals() {

            @Override
            public String getSchool() {
                return school;
            }

            public String getFreshman() {
                return freshman;
            }

            public String getSophomore() {
                return sophomore;
            }

            public String getJunior() {
                return junior;
            }

            public String getSenior() {
                return senior;
            }
        };
    }
}
