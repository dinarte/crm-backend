package br.com.esig.edu.crm.task;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.esig.edu.crm.service.IntegracaoProdutoService;


//@ConditionalOnProperty(prefix = "quarkedu.jobs.atualizacaoJurosMultaTask", name = "ativo", matchIfMissing = true)
//@ESIGJob(nome = "atualizadorIntegracaoEduTask", delay = ESIGJob.DELAY_UM_MINUTO, descricao = "Job para atualização de produtos a partir dos Serviços do QuarkEdu")
@Component
public class AtualizadorIntegracaoEduTask {

	@Autowired
	IntegracaoProdutoService integracaoProdutoService;
	
	
	@Scheduled(fixedDelay = 50000)
	public void execute(JobExecutionContext context) throws JobExecutionException {
		integracaoProdutoService.atualizarProdutosComServicosQuarkEdu();
	}

}
