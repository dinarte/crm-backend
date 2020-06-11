package br.com.esig.edu.crm;

import java.util.Arrays;

import br.com.esig.edu.crm.dominio.Agendamento;
import br.com.esig.edu.crm.dominio.Formulario;
import br.com.esig.utils.StringUtils;

public class GeradorUtil {
	
	
	public static final String SERVICE = 	"import { Injectable } from '@angular/core';\r\n" + 
											"import { GenericCrudService } from 'src/app/core/features-generics/generic-crud.service';\r\n" + 
											"import { HttpClient } from '@angular/common/http';\r\n" + 
											"\r\n" + 
											"@Injectable({\r\n" + 
											"  providedIn: 'root'\r\n" + 
											"})\r\n"+ 
											"export class {{nomeClasse}}Service extends GenericCrudService {\r\n" + 
											"\r\n" + 
											"  constructor(private httpClient: HttpClient) {\r\n" + 
											"    super(httpClient);\r\n" + 
											"  }\r\n" + 
											"\r\n" + 
											"  getResource() { return '/{{nomeClasseMinusculo}}'; }\r\n" + 
											"\r\n" + 
											"}\r\n";
	
	public static final String COMPONENT_LIST_TS = 	"import { Component, OnInit } from '@angular/core';\r\n" + 
													"import { {{nomeClasse}}Service } from 'src/app/services/crm/{{nomeClasseMinusculo}}.service';\r\n" + 
													"import { Router } from '@angular/router';\r\n" + 
													"import { MessageService } from 'src/app/core/services/message.service';\r\n" + 
													"import { GenericList } from 'src/app/core/features-generics/generic-list';\r\n" + 
													"import { LoaderService } from 'src/app/core/services/loader.service';\r\n" + 
													"\r\n" + 
													"@Component({\r\n" + 
													"  selector: 'app-{{nomeClasseMinusculo}}-list',\r\n" + 
													"  templateUrl: './{{nomeClasseMinusculo}}-list.component.html',\r\n" + 
													"  styleUrls: ['./{{nomeClasseMinusculo}}-list.component.css']\r\n" + 
													"})\r\n" + 
													"export class {{nomeClasse}}ListComponent extends GenericList {\r\n" + 
													"\r\n" + 
													"  constructor(\r\n" + 
													"    private service: {{nomeClasse}}Service,\r\n" + 
													"    private router: Router,\r\n" + 
													"    private messageService: MessageService,\r\n" + 
													"    private loader: LoaderService\r\n" + 
													"  ) {\r\n" + 
													"    super(service, router, messageService, loader);\r\n" + 
													"  }\r\n" + 
													"\r\n" + 
													"  getEditRoute(): string {\r\n" + 
													"    return '/crm/{{nomeClasseMinusculo}}/form';\r\n" + 
													"  }\r\n" + 
													"\r\n" + 
													"}";

	
	public static final String COMPONENT_FORM_TS =	"import { Component, OnInit } from '@angular/core';\r\n" + 
													"import { {{nomeClasse}} } from 'src/app/model/crm/{{nomeClasse}}';\r\n" + 
													"import { {{nomeClasse}}Service } from 'src/app/services/crm/{{nomeClasseMinusculo}}.service';\r\n" + 
													"import { ActivatedRoute, Router } from '@angular/router';\r\n" + 
													"import { MessageService } from 'src/app/core/services/message.service';\r\n" + 
													"import { GenericForm } from 'src/app/core/features-generics/genric-form';\r\n" + 
													"import { LoaderService } from 'src/app/core/services/loader.service';\r\n" + 
													"\r\n" + 
													"@Component({\r\n" + 
													"  selector: 'app-{{nomeClasseMinusculo}}-form',\r\n" + 
													"  templateUrl: './{{nomeClasseMinusculo}}-form.component.html',\r\n" + 
													"  styleUrls: ['./{{nomeClasseMinusculo}}-form.component.css']\r\n" + 
													"})\r\n" + 
													"export class {{nomeClasse}}FormComponent extends GenericForm {\r\n" + 
													"\r\n" + 
													"  constructor(\r\n" + 
													"    private service: {{nomeClasse}}Service,\r\n" + 
													"    private router: Router,\r\n" + 
													"    private activatedRout: ActivatedRoute,\r\n" + 
													"    private messageService: MessageService,\r\n" + 
													"    private loader: LoaderService) {\r\n" + 
													"\r\n" + 
													"      super(service, router, activatedRout, messageService, loader);\r\n" + 
													"  }\r\n" + 
													"\r\n" + 
													"  getNewEntity() { return new {{nomeClasse}}(); }\r\n" + 
													"  getListRoute() { return '/crm/{{nomeClasseMinusculo}}'; }\r\n" + 
													"\r\n" + 
													"\r\n" + 
													"}\r\n"; 
	
	
	
	public static final String COMPONENT_SUB_FORM_TS = 	"import { Component, OnInit, Output, Input } from '@angular/core';\r\n" + 
														"import { {{nomeClasse}} } from 'src/app/model/crm/{{nomeClasse}}';\r\n" + 
														"import { MessageService } from 'src/app/core/services/message.service';\r\n" + 
														"import { GenericSubForm } from 'src/app/core/features-generics/generic-sub-form';\r\n" + 
														"\r\n" + 
														"@Component({\r\n" + 
														"  selector: 'app-{{nomeClasseMinusculo}}-form',\r\n" + 
														"  templateUrl: './{{nomeClasseMinusculo}}-form.component.html',\r\n" + 
														"  styleUrls: ['./{{nomeClasseMinusculo}}-form.component.css']\r\n" + 
														"})\r\n" + 
														"export class {{nomeClasse}}FormComponent extends GenericSubForm implements OnInit {\r\n" + 
														"\r\n" + 
														"  /**\r\n" + 
														"   * Comobobox com os tipos disponíveis\r\n" + 
														"   */\r\n" + 
														"\r\n" + 
														"\r\n" + 
														"  constructor(private messageService: MessageService) {\r\n" + 
														"        super(messageService);\r\n" + 
														"        this.entity = new {{nomeClasse}}();\r\n" + 
														"  }\r\n" + 
														"\r\n" + 
														"  ngOnInit() {\r\n" + 
														"  }\r\n" + 
														"\r\n" + 
														"}";
	
	
	
	
	public static void gerarDominioTypeScript(Class<?> clazz) {
		
		StringBuffer out = new StringBuffer( "export class " + clazz.getSimpleName().replace("DTO", "") + " {" + "\r\n" );
		out.append("\r\n\n");
		Arrays.asList( clazz.getDeclaredFields() ).forEach( f -> {
			out.append(" "+ f.getName() + ": " + f.getType().getSimpleName()
					.replace("DTO", "")
					.replace("int", "number")
					.replace("String", "string")
					+ ";\r\n\n");
		
		});
		out.append("}");
		
		System.out.println(out + "\n\n");
	}
	
	
	public static void gerar(Class<?> clazz, String template) {
		
		String out = template.replace("{{nomeClasse}}", clazz
								.getSimpleName()
								.replace("DTO", ""))
								.replace("{{nomeClasseMinusculo}}", StringUtils.getSlugFromText(clazz.getSimpleName().replace("DTO", "")));
		
		System.out.println(out + "\n\n");
	}
	
	
	public static void gerarComponente(Class<?> clazz) {
		
	}
	

	public static void main(String[] args) {
		
		gerarDominioTypeScript(Formulario.class);
		gerar(Formulario.class, SERVICE);
		gerar(Formulario.class, COMPONENT_LIST_TS);
		gerar(Formulario.class, COMPONENT_FORM_TS);
		
	
		
		
		
		
		// gerar(Acao.class, COMPONENT_SUB_FORM_TS);
		// gerar(Acao.class, COMPONENT_LIST_TS);
		// gerar(Acao.class, COMPONENT_FORM_TS);
	
		//gerarDominioTypeScript(TimeDTO.class);
		//gerarDominioTypeScript(TimeMembroDTO.class);
		//gerarDominioTypeScript(UnidadeFederativa.class);
		
		//gerar(TimeDTO.class, SERVICE);
		//gerar(EnderecoEmailDTO.class, COMPONENT_SUB_FORM_TS);
		
		//gerar(TimeMembroDTO.class, COMPONENT_SUB_FORM_TS);
		
	}
	
		
	

}
