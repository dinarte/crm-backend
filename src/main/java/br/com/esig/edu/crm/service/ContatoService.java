package br.com.esig.edu.crm.service;

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
import br.com.esig.edu.crm.dominio.Contato;
import br.com.esig.edu.crm.dominio.Endereco;
import br.com.esig.edu.crm.dominio.EnderecoEmail;
import br.com.esig.edu.crm.dominio.Telefone;
import br.com.esig.edu.crm.dominio.dto.ContatoDTO;
import br.com.esig.edu.crm.dominio.dto.ContatoListDTO;
import br.com.esig.edu.crm.filters.ContatoSpecification;
import br.com.esig.edu.crm.filters.api.SpecificationsBuilder;
import br.com.esig.edu.crm.repository.ContatoRepository;
import br.com.esig.edu.crm.repository.EnderecoEmailRepository;
import br.com.esig.edu.crm.repository.EnderecoRepository;
import br.com.esig.edu.crm.repository.TelefoneRepository;
import br.com.esig.excecoes.ResourceNotFoundException;
import br.com.esig.utils.ValidatorUtil;
import br.com.esig.utils.stream.StreamUtils;

@Service
public class ContatoService {

	@Autowired
	ContatoRepository contatoRespository;
	
	@Autowired
	EnderecoRepository enderecoRespository;
	
	@Autowired
	EnderecoEmailRepository emailRespository;
	
	@Autowired
	TelefoneRepository telefoneRespository;
	
	@Autowired
	SpecificationsBuilder specBuilder;
	
	@Autowired
	ModelMapper mapper;
	
	@Autowired
	TelefoneRepository telefoneRepository;
	
	@Autowired
	EnderecoRepository enderecoRepository;

	@Autowired
	EnderecoEmailRepository emailRepository;
	
	@Transactional
	public ContatoDTO create(Contato contato) {
		relacionarContato(contato);
		return mapper.map(contatoRespository.save(contato), ContatoDTO.class);
	}
	
	@Transactional
	public ContatoDTO update(Contato contato) {
		throwIfNotExists(contato.getId());
		relacionarContato(contato);
		removerItens(contato);
		return mapper.map(contatoRespository.save(contato), ContatoDTO.class);
	}

	private void removerItens(Contato contato) {
		
		List<Telefone> telefones = StreamUtils.ofNullable(contato.getTelefones()).filter(i -> i.getId() > 0).collect(Collectors.toList());
		List<EnderecoEmail> emails = StreamUtils.ofNullable(contato.getEmails()).filter(i -> i.getId() > 0).collect(Collectors.toList());
		List<Endereco> enderecos = StreamUtils.ofNullable(contato.getEnderecos()).filter(i -> i.getId() > 0).collect(Collectors.toList());
		
		if (ValidatorUtil.isNotEmpty(telefones) && telefones.size() > 0)
			telefoneRepository.deleteWhereNotIn(telefones);
		else
			telefoneRepository.deleteAllByContato(contato);
		
		if (ValidatorUtil.isNotEmpty(emails) && emails.size() > 0)
			emailRepository.deleteWhereNotIn(emails);
		else
			emailRepository.deleteAllByContato(contato);
		
		if (ValidatorUtil.isNotEmpty(enderecos) && enderecos.size() > 0)
			enderecoRepository.deleteWhereNotIn(enderecos);
		else
			enderecoRepository.deleteAllByContato(contato);
	}

	private void relacionarContato(Contato contato) {
		StreamUtils.ofNullable(contato.getTelefones()).forEach(t->t.setContato(contato));
		StreamUtils.ofNullable(contato.getEmails()).forEach(t->t.setContato(contato));
		StreamUtils.ofNullable(contato.getEnderecos()).forEach(t->t.setContato(contato));
	}
	
	public void delete(int contatoId) {
		throwIfNotExists(contatoId);
		contatoRespository.deleteById(contatoId);
	}
	
	@SuppressWarnings("unchecked")
	public PaginatedResponse<ContatoListDTO> getAll(int instituicaoEnsinoId,  String search, Paginacao paginacao){
		
		specBuilder.with("instituicao", "=", instituicaoEnsinoId);
		Specification<Contato> spec = (Specification<Contato>) specBuilder.build(ContatoSpecification.class, search);
		
		Page<Contato> page = contatoRespository.findAll(spec, paginacao.toPageable());
		Long count = page.getTotalElements();
		List<ContatoListDTO> retorno = page.stream().map(it -> mapper.map(it, ContatoListDTO.class)).collect(Collectors.toList());
		
		paginacao.setData("/contato", search, count); 
		
		return new PaginatedResponse<ContatoListDTO>(retorno, paginacao);		
	}

	@Transactional
	public ContatoDTO getOne(int contatoId) {
		throwIfNotExists(contatoId);
		Contato contato = contatoRespository
				.findById(contatoId)
				.get();
		
		return mapper.map( contato, ContatoDTO.class);
	}
	
	void throwIfNotExists(int contatoId) {
		if ( !contatoRespository.existsById(contatoId) )
			throw new ResourceNotFoundException("A campaanha que você está tentando acessar não existe ou foi removida.");
	}
}
