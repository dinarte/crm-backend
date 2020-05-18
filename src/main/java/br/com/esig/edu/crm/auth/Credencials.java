package br.com.esig.edu.crm.auth;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor 
public class Credencials{
	private String login;
	private String senha;
}