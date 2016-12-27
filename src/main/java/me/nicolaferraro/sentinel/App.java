package me.nicolaferraro.sentinel;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Component
    static class Routes extends RouteBuilder {

        @Autowired
        private ClientTracker tracker;

        @Autowired
        private SentinelConfiguration config;

        public void configure() throws Exception {

            restConfiguration().port(config.getWebPort());

            rest("/sentinel")
                .post("/ping/{ClientCode}")
                .produces("text/plain")
                .route()
                .setBody().header("ClientCode")
                .log("Ping from code: ${body}")
                .bean(tracker, "ping")
                .setBody().constant("pong");



            from("timer:check?period=" + config.getCheckPeriod())
            .bean(tracker, "getExpired")
            .split().body()
            .log("Expired ${body}")
            .bean(config, "getEmail")
            .choice()
                .when().simple("${body} != null")
                    .log("Sending email to ${body}")
                    .setHeader("from", method(config, "getMailFrom"))
                    .setHeader("to", body())
                    .setHeader("subject", method(config, "getMailSubject"))
                    .setBody().method(config, "getMailBody")
                    .to("smtp://" + config.getMailHost() + ":" + config.getMailPort())
                .otherwise()
                    .log("No email configured")
            .endChoice();

        }
    }

}
