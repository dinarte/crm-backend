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
import br.com.esig.edu.crm.comum.dominio.Instituicao;
import br.com.esig.edu.crm.comum.dominio.Usuario;
import br.com.esig.edu.crm.dominio.Time;
import br.com.esig.edu.crm.dominio.TimeMembro;
import br.com.esig.edu.crm.dominio.dto.TimeDTO;
import br.com.esig.edu.crm.filters.TimeSpecification;
import br.com.esig.edu.crm.filters.api.SpecificationsBuilder;
import br.com.esig.edu.crm.repository.TimeMembroRepository;
import br.com.esig.edu.crm.repository.TimeRepository;
import br.com.esig.excecoes.ResourceNotFoundException;
import br.com.esig.utils.ValidatorUtil;
import br.com.esig.utils.stream.StreamUtils;
import br.com.esig.validacao.ValidationException;

@Service
public class TimeService {

	@Autowired
	TimeRepository timeRespository;

	@Autowired
	TimeMembroRepository timeMembroRepository;
	
	@Autowired
	SpecificationsBuilder specBuilder;
	
	@Autowired
	ModelMapper mapper;
	
	/**
	 * Cria um novo time e seus membros, não pose haver um time sem membros.
	 * @param time
	 * @return
	 */
	@Transactional
	public TimeDTO create(Time time) {
		
		if(ValidatorUtil.isEmpty(time.getMembros())){
			throw new ValidationException("Um time deve conter pelo menos um membro.");
		}
		
		return mapper.map(timeRespository.save(time), TimeDTO.class);
	}
	
	/**
	 * Altera um time, não pode haver um time sem membros.
	 * @param time
	 * @return
	 */
	@Transactional
	public TimeDTO update(Time time) {
		
		if(ValidatorUtil.isEmpty(time.getMembros())){
			throw new ValidationException("Um time deve conter pelo menos um membro.");
		}
		
		List<TimeMembro> membros = StreamUtils.ofNullable(time.getMembros()).filter(i -> i.getId() > 0).collect(Collectors.toList());
		
		if (ValidatorUtil.isNotEmpty(membros) && membros.size() > 0)
			timeMembroRepository.deleteWhereNotIn(membros, time);
		
		
		throwIfNotExists(time.getId());
		return mapper.map(timeRespository.save(time), TimeDTO.class);
	}
	
	/**
	 * Remove um time e todos seus membros.
	 * @param timeId
	 */
	@Transactional
	public void delete(int timeId) {
		throwIfNotExists(timeId);
		timeRespository.deleteById(timeId);
	}
	
	/**
	 * recupera a lista de todos os times disponíveis na instituição
	 * @param instituicaoEnsinoId
	 * @param search
	 * @param paginacao
	 * @return
	 */
	@Transactional
	@SuppressWarnings("unchecked")
	public PaginatedResponse<TimeDTO> getAll(int instituicaoEnsinoId,  String search, Paginacao paginacao){
		
		specBuilder.with("instituicao", "=", instituicaoEnsinoId);
		Specification<Time> spec = (Specification<Time>) specBuilder.build(TimeSpecification.class, search);
		
		Page<Time> page = timeRespository.findAll(spec, paginacao.toPageable());
		Long count = page.getTotalElements();
		List<TimeDTO> retorno = page.stream().map(it -> mapper.map(it, TimeDTO.class)).collect(Collectors.toList());
		
		paginacao.setData("/time", search, count); 
		
		return new PaginatedResponse<TimeDTO>(retorno, paginacao);		
	}
	
	/**
	 * recupera a lista de todos os times disponíveis na instituição em que o usuário informado é um membro
	 * @param instituicaoEnsinoId
	 * @param search
	 * @param paginacao
	 * @return
	 */
	@Transactional
	public List<TimeDTO> getAllByUsuario(int instituicaoId,  Usuario usuario){
		return StreamUtils.ofNullable( timeRespository.findAllByUsuario(usuario, new Instituicao(instituicaoId)) )
				.map(t -> mapper.map(t, TimeDTO.class)).collect(Collectors.toList());
	}

	/**
	 * Recupera apenas o time do id informado
	 * @param timeId
	 * @return
	 */
	@Transactional
	public TimeDTO getOne(int timeId) {
		throwIfNotExists(timeId);
		return mapper.map( timeRespository
									.findById(timeId)
									.get(), TimeDTO.class);
	}
	
	/**
	 * Exeuta a verificação da existência de um dado time 
	 * @param timeId
	 */
	void throwIfNotExists(int timeId) {
		if ( !timeRespository.existsById(timeId) )
			throw new ResourceNotFoundException("O time que você está tentando acessar não existe ou foi removida.");
	}
}
