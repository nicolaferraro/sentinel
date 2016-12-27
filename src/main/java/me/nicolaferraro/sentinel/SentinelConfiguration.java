package me.nicolaferraro.sentinel;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("sentinel")
public class SentinelConfiguration {

    private Integer webPort = 8080;

    private Long expirationDelay = 90000L;

    private Long checkPeriod = 300000L;

    private Map<String, String> emails = new HashMap<>();

    private String mailFrom;

    private String mailSubject;

    private String mailBody;

    private String mailHost = "localhost";

    private Integer mailPort = 25;

    public Integer getWebPort() {
        return webPort;
    }

    public void setWebPort(Integer webPort) {
        this.webPort = webPort;
    }

    public Long getExpirationDelay() {
        return expirationDelay;
    }

    public void setExpirationDelay(Long expirationDelay) {
        this.expirationDelay = expirationDelay;
    }

    public Long getCheckPeriod() {
        return checkPeriod;
    }

    public void setCheckPeriod(Long checkPeriod) {
        this.checkPeriod = checkPeriod;
    }

    public Map<String, String> getEmails() {
        return emails;
    }

    public String getEmail(String name) {
        return emails.get(name);
    }

    public String getMailFrom() {
        return mailFrom;
    }

    public void setMailFrom(String mailFrom) {
        this.mailFrom = mailFrom;
    }

    public String getMailSubject() {
        return mailSubject;
    }

    public void setMailSubject(String mailSubject) {
        this.mailSubject = mailSubject;
    }

    public String getMailBody() {
        return mailBody;
    }

    public void setMailBody(String mailBody) {
        this.mailBody = mailBody;
    }

    public String getMailHost() {
        return mailHost;
    }

    public void setMailHost(String mailHost) {
        this.mailHost = mailHost;
    }

    public Integer getMailPort() {
        return mailPort;
    }

    public void setMailPort(Integer mailPort) {
        this.mailPort = mailPort;
    }
}
