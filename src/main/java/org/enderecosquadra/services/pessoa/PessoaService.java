package org.enderecosquadra.services.pessoa;

import jakarta.transaction.Transactional;
import org.enderecosquadra.domain.bairro.Bairro;
import org.enderecosquadra.domain.endereco.Endereco;
import org.enderecosquadra.domain.endereco.EnderecoRequestDTO;
import org.enderecosquadra.domain.endereco.EnderecoRequestPutDTO;
import org.enderecosquadra.domain.pessoa.Pessoa;
import org.enderecosquadra.domain.pessoa.PessoaRequestDTO;
import org.enderecosquadra.domain.pessoa.PessoaRequestPutDTO;
import org.enderecosquadra.domain.pessoa.PessoaResponseDTO;
import org.enderecosquadra.repositories.BairroRepository;
import org.enderecosquadra.repositories.EnderecoRepository;
import org.enderecosquadra.repositories.PessoaRepository;
import org.enderecosquadra.services.bairro.BairroValidacao;
import org.enderecosquadra.services.endereco.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private BairroRepository bairroRepository;

    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private PessoaValidacao pessoaValidacao;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private BairroValidacao bairroValidacao;

    public List<PessoaResponseDTO> buscarPessoa(Long cosigoPessoa, String login, Integer status) {
        List<Pessoa> pessoas = pessoaRepository.buscarPessoaPorParametros(cosigoPessoa, login, status);
        return converterPessoaEmDTO(pessoas);
    }

    public Pessoa buscarPessoaDetalhada(Long codigoPessoa){
        return pessoaRepository.pessoaDetalhada(codigoPessoa);
    }

    @Transactional
    public List<PessoaResponseDTO> adicionarPessoa(PessoaRequestDTO pessoaRequestDTO){

        pessoaValidacao.verificarSeLoginExiste(pessoaRequestDTO.getLogin());

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
                )).toList();

        for(Endereco endereco : enderecos){
            enderecoService.adicionarEndereco(endereco);
        }
        return buscarPessoa(null, null, null);
    }

    // EDITAR
    @Transactional
    public List<PessoaResponseDTO> editarPessoa(PessoaRequestPutDTO pessoaRequestPutDTO){
        Pessoa pessoaExistente = pessoaValidacao.verificarSePessoaExistePorId(pessoaRequestPutDTO.getCodigoPessoa());

        List<Endereco> enderecosParaRemover = new ArrayList<>();

        //REMOVENDO ENDERECOS
        for(Endereco enderecoExistente : pessoaExistente.getEnderecos()){
            boolean enderecoExiste = false;

            for(Endereco enderecoRecebidos : pessoaRequestPutDTO.getEnderecos()){
                if(enderecoExistente.getCodigoEndereco().equals(enderecoRecebidos.getCodigoEndereco())){
                    enderecoExiste = true;
                    break;
                }
            }

            if(!enderecoExiste)
                enderecosParaRemover.add(enderecoExistente);
        }

        pessoaExistente.getEnderecos().removeAll(enderecosParaRemover);

        Map<Long, Endereco> mapaEnderecosRecebidos = pessoaRequestPutDTO.getEnderecos().stream()
                .collect(Collectors.toMap(Endereco::getCodigoEndereco, endereco -> endereco));

        for(Endereco enderecoExistente : pessoaExistente.getEnderecos()){
            Endereco enderecoRecebido = mapaEnderecosRecebidos.get(enderecoExistente.getCodigoEndereco());
            if(enderecoRecebido != null){
                // Verifique se o bairro não é null
                if (enderecoRecebido.getBairro() != null) {
                    Bairro bairro = bairroValidacao.verificarSeBairroExiste(enderecoRecebido.getBairro().getCodigoBairro());
                    enderecoExistente.setBairro(bairro);
                }
                enderecoExistente.setNomeRua(enderecoRecebido.getNomeRua());
                enderecoExistente.setNumero(enderecoRecebido.getNumero());
                enderecoExistente.setComplemento(enderecoRecebido.getComplemento());
                enderecoExistente.setCep(enderecoRecebido.getCep());

                mapaEnderecosRecebidos.remove(enderecoExistente.getCodigoEndereco());
            }
        }

        pessoaExistente.getEnderecos().addAll(mapaEnderecosRecebidos.values());

        pessoaExistente.setNome(pessoaRequestPutDTO.getNome());
        pessoaExistente.setSobrenome(pessoaRequestPutDTO.getSobrenome());
        pessoaExistente.setIdade(pessoaRequestPutDTO.getIdade());
        pessoaExistente.setLogin(pessoaRequestPutDTO.getLogin());
        pessoaExistente.setSenha(pessoaRequestPutDTO.getSenha());
        pessoaExistente.setStatus(pessoaRequestPutDTO.getStatus());

        pessoaRepository.save(pessoaExistente);
        return converterPessoaEmDTO(pessoaRepository.findAll());
    }


    private Endereco editarEndereco(Endereco endereco){
        return endereco;
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
