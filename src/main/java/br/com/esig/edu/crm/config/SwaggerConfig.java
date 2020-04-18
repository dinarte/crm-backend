package br.com.esig.edu.crm.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.google.common.base.Predicates;

import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.paths.RelativePathProvider;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurerAdapter {

	@Autowired
	ServletContext servletContext;

	@Bean
	public Docket api() {

		ParameterBuilder headers = new ParameterBuilder();
		List<Parameter> headersList = new ArrayList<Parameter>();

		headers.name("Access-Token").modelRef(new ModelRef("string")).parameterType("header").required(true)
				.description("Token de acesso do usuário").build();
		headersList.add(headers.build());

		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("br.com.esig.clinic.core"))
				.paths(Predicates.not(PathSelectors.regex("/error.*"))).build().apiInfo(apiInfo())
				.useDefaultResponseMessages(false)
				.globalResponseMessage(RequestMethod.GET,
						Arrays.asList(new ResponseMessageBuilder().code(401)
								.message("Acesso negado! Verificar se o Access Token de acesso é válido.")
								// .responseModel(new ModelRef("Error"))
								.build(),
								new ResponseMessageBuilder().code(500).message("Erro no processamento da requisição")
										// .responseModel(new ModelRef("Error"))
										.build()))
				.pathProvider(new RelativePathProvider(servletContext) {
					@Override
					public String getApplicationBasePath() {
						return servletContext.getContextPath();
					}
				})
				.globalOperationParameters(headersList);
	}

	private ApiInfo apiInfo() {
		return new ApiInfo("QuarkClinic Reports API", "Microservice API Clinic Core"
				+ " Todas as requisições deverão ser acompanhadas do Request Header Access-Token com o token de acesso do usuário. ",
				"QuarkERP API", "", new Contact("Contato", "www.quarkerp.com.br", "erp@quarkcloud.com.br"),
				"License of API", "API license URL", Collections.emptyList());
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");

		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}

	
}
