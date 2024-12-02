package org.enderecosquadra.services.pessoa;

import org.enderecosquadra.domain.endereco.Endereco;
import org.enderecosquadra.domain.pessoa.Pessoa;
import org.enderecosquadra.domain.pessoa.PessoaRequestDTO;
import org.enderecosquadra.domain.pessoa.PessoaResponseDTO;
import org.enderecosquadra.repositories.BairroRepository;
import org.enderecosquadra.repositories.PessoaRepository;
import org.enderecosquadra.services.endereco.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private BairroRepository bairroRepository;

    @Autowired
    private EnderecoService enderecoService;

    public List<PessoaResponseDTO> buscarPessoa(Long cosigoPessoa, String login, Integer status) {
        List<Pessoa> pessoas = pessoaRepository.buscarPessoaPorParametros(cosigoPessoa, login, status);
        return converterPessoaEmDTO(pessoas);
    }

    public Pessoa buscarPessoaDetalhada(Long codigoPessoa){
        return pessoaRepository.pessoaDetalhada(codigoPessoa);
    }

    public List<PessoaResponseDTO> adicionarPessoa(PessoaRequestDTO pessoaRequestDTO){
        Pessoa pessoa = new Pessoa(
                pessoaRequestDTO.getNome(),
                pessoaRequestDTO.getSobrenome(),
                pessoaRequestDTO.getIdade(),
                pessoaRequestDTO.getLogin(),
                pessoaRequestDTO.getSenha(),
                pessoaRequestDTO.getStatus()
        );

        pessoaRepository.save(pessoa);

        List<Endereco> enderecos = pessoaRequestDTO.getEnderecos().stream()
                .map(enderecoDTO -> new Endereco(
                        pessoa,
                        bairroRepository.findById(enderecoDTO.getCodigoBairro()).get(),
                        enderecoDTO.getNomeRua(),
                        enderecoDTO.getNumero(),
                        enderecoDTO.getComplemento(),
                        enderecoDTO.getCep()
                )).collect(Collectors.toList());

        for(Endereco endereco : enderecos){
            enderecoService.adicionarEndereco(endereco);
        }
        return buscarPessoa(null, null, null);
    }


    private List<PessoaResponseDTO> converterPessoaEmDTO(List<Pessoa> pessoas){
        return pessoas.stream()
                .map(pessoa -> new PessoaResponseDTO(
                        pessoa.getCodigoPessoa(),
                        pessoa.getNome(),
                        pessoa.getSobrenome(),
                        pessoa.getIdade(),
                        pessoa.getLogin(),
                        pessoa.getSenha(),
                        pessoa.getStatus()
                )).collect(Collectors.toList());
    }
}
