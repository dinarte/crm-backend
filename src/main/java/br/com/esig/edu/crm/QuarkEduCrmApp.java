package br.com.esig.edu.crm;

import org.joda.time.DateTimeZone;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.scheduling.annotation.EnableAsync;

import br.com.esig.core.orm.EsigCrudRepositoryImpl;

@SpringBootApplication(scanBasePackages = "br.com.esig", exclude = { FlywayAutoConfiguration.class })
@EnableJpaRepositories(repositoryBaseClass = EsigCrudRepositoryImpl.class, basePackages = "br.com.esig")
@EntityScan("br.com.esig")
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableSpringDataWebSupport
@EnableJpaAuditing
@EnableAsync
public class QuarkEduCrmApp extends SpringBootServletInitializer {

   public static void main(String[] args) {
      DateTimeZone.setDefault(DateTimeZone.forOffsetHours(-3));
      SpringApplication.run(QuarkEduCrmApp.class, args);
   }

   @Override
   protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
      return application.sources(QuarkEduCrmApp.class);
   }

}