package org.enderecosquadra.domain.pessoa;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.enderecosquadra.domain.endereco.Endereco;
import org.enderecosquadra.domain.endereco.EnderecoRequestDTO;

import java.util.ArrayList;
import java.util.List;

public class PessoaRequestPutDTO {

    @NotNull(message = "O campo codigoPessoa é obrigatório")
    private Long codigoPessoa;

    @NotBlank(message = "O campo nome é obrigatório")
    private String nome;

    @NotBlank(message = "O campo sobrenome é obrigatório")
    private String sobrenome;

    @NotNull(message = "O campo idade é obrigatório")
    private Integer idade;

    @NotBlank(message = "O campo login é obrigatório")
    private String login;

    @NotBlank(message = "O campo senha é obrigatório")
    private String senha;

    @NotNull(message = "O campo status é obrigatório")
    private Integer status;

    @NotEmpty(message = "O campo endereço é obrigatório")
    List<EnderecoRequestDTO> enderecos = new ArrayList<>();

    public PessoaRequestPutDTO(Long codigoPessoa, String nome, String sobrenome, Integer idade, String login, String senha, Integer status, List<EnderecoRequestDTO> enderecos) {
        this.codigoPessoa = codigoPessoa;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.idade = idade;
        this.login = login;
        this.senha = senha;
        this.status = status;
        this.enderecos = enderecos;
    }

    public Long getCodigoPessoa() {
        return codigoPessoa;
    }

    public void setCodigoPessoa(Long codigoPessoa) {
        this.codigoPessoa = codigoPessoa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<EnderecoRequestDTO> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<EnderecoRequestDTO> enderecos) {
        this.enderecos = enderecos;
    }
}
