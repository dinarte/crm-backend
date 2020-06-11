package br.com.esig.edu.crm.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import br.com.esig.edu.crm.Paginacao;
import br.com.esig.edu.crm.PaginatedResponse;
import br.com.esig.edu.crm.auth.AutenticacaoService;
import br.com.esig.edu.crm.dominio.Acao;
import br.com.esig.edu.crm.dominio.AcaoCanal;
import br.com.esig.edu.crm.dominio.Lead;
import br.com.esig.edu.crm.dominio.dto.LeadDTO;
import br.com.esig.edu.crm.filters.LeadSpecification;
import br.com.esig.edu.crm.filters.api.SpecificationsBuilder;
import br.com.esig.edu.crm.repository.FunilVendaEtapaRepository;
import br.com.esig.edu.crm.repository.LeadRepository;
import br.com.esig.excecoes.ResourceNotFoundException;
import br.com.esig.validacao.ValidationException;

@Service
public class LeadService {

	@Autowired
	LeadRepository leadRespository;
	
	@Autowired
	AcaoService acaoService;
	
	@Autowired
	FunilVendaEtapaRepository etapaRepo;
	
	@Autowired
	SpecificationsBuilder specBuilder;
	
	@Autowired
	ModelMapper mapper;
	
	@Autowired
	AutenticacaoService auth;
	
	
	/**
	 * Cria um novo lead
	 * @param lead
	 * @return
	 */
	@Transactional
	public LeadDTO create(Lead lead) {
		Date hoje = new Date();
		lead.setDataCadastro(hoje);
		lead.setDataUltimaInteracao(hoje);
		lead.setUsuarioCadastro(auth.getUsuarioLogado());
		lead.setUsuarioResponsavel(auth.getUsuarioLogado());
		lead.getProdutos().forEach(lp -> lp.setLead(lead));
		return mapper.map(leadRespository.save(lead), LeadDTO.class);
	}
	
	/**
	 * Altera um lead
	 * @param lead
	 * @return
	 */
	@Transactional
	public LeadDTO update(Lead lead) {
		throwIfNotExists(lead.getId());
		lead.getProdutos().forEach(lp -> lp.setLead(lead));
		return mapper.map(leadRespository.save(lead), LeadDTO.class);
	}
	
	/**
	 * Deleta um lead
	 * @param leadId
	 */
	public void delete(int leadId) {
		throwIfNotExists(leadId);
		leadRespository.deleteById(leadId);
	}
	
	/**
	 * Recupera todos os leads com a consulta padrão da api
	 * @param instituicaoId
	 * @param search
	 * @param paginacao
	 * @return
	 */
	@Transactional
	@SuppressWarnings("unchecked")
	public PaginatedResponse<LeadDTO> getAll(int instituicaoId,  String search, Paginacao paginacao){
		
		specBuilder.with("instituicao", "=", instituicaoId);
		Specification<Lead> spec = (Specification<Lead>) specBuilder.build(LeadSpecification.class, search);
		
		Page<Lead> page = leadRespository.findAll(spec, paginacao.toPageable());
		Long count = page.getTotalElements();
		List<LeadDTO> retorno = page.stream().map(it -> mapper.map(it, LeadDTO.class)).collect(Collectors.toList());
		
		paginacao.setData("/lead", search, count); 
		
		return new PaginatedResponse<LeadDTO>(retorno, paginacao);		
	}
	
	
	/**
	 * Retorna de acordo com os filtros informados.
	 * @param instituicaoId
	 * @param etapaId
	 * @param tipoEtapaId
	 * @param timeId
	 * @param campanhaId
	 * @param produtoId
	 * @return
	 */
	@Transactional
	public List<LeadDTO> getAllByFiltersNoPaginated(
			int instituicaoId,  
			int etapaId, 
			int tipoEtapaId, 
			int timeId, 
			int campanhaId, 
			int produtoId){
			
		List<Lead> leads = leadRespository.findAllByEtapaTtipoEtapaTimeCampanhaProduto(instituicaoId, etapaId, tipoEtapaId, timeId, campanhaId, produtoId);
		List<LeadDTO> leadsDto = leads.stream().map(it -> mapper.map(it, LeadDTO.class)).collect(Collectors.toList()); 
		return leadsDto;	
		
	}

	/**
	 * Rqcupera aprnas o lead em questão
	 * @param leadId
	 * @return
	 */
	@Transactional
	public LeadDTO getOne(int leadId) {
		throwIfNotExists(leadId);
		return mapper.map( leadRespository
									.findById(leadId)
									.get(), LeadDTO.class);
	}
	
	/**
	 * Dispara erro caso o lead não exista
	 * @param leadId
	 */
	void throwIfNotExists(int leadId) {
		if ( !leadRespository.existsById(leadId) )
			throw new ResourceNotFoundException("O Lead que você está tentando acessar não existe ou foi removida.");
	}


	/**
	 * Move um lead entre etapas, esta operação não é recomendada atravpes do método update
	 * @param leadId
	 * @param etapaOrigemId
	 * @param etapaDestinoId
	 * @return
	 */
	@Transactional
	public Object mover(Integer leadId, Integer etapaOrigemId, Integer etapaDestinoId) {
		if (leadId == null || etapaOrigemId == null || etapaDestinoId == null) {
			throw new ValidationException("os paramtros leadId, etapaOrigemId e etapaDestinoId são obrigatórios para esta opreção");
		}
		
		if (etapaOrigemId == etapaDestinoId) {
			throw new ValidationException("O item não pode ser movimentado pois a etapa de origem é a mesma de destino.");
		}
		
		Lead lead = mapper.map(getOne(leadId), Lead.class);
		if (lead.getEtapa().getId() != etapaOrigemId ) {
			throw new ValidationException("O lead em questão não pertense mais a etapa de origem informada.");
		}
		
		lead.getEtapa().setId(etapaDestinoId);
		
		leadRespository.save(lead);
		
		Acao a = new Acao();
		a.setLead(lead);
		a.setResumo("Movimentação no Funil");
		a.setDescricao("O Lead foi movido para " + etapaRepo.findById(etapaDestinoId).get().getNome());
		a.setDataOperacao(new Date());
		a.setUsuarioOperacao(lead.getUsuarioCadastro());
		a.setTipo(Acao.INTRENO);
		a.setCanal(AcaoCanal.QUARK_CRM);
		
		acaoService.create(a);
		
		return null;
	}
}
