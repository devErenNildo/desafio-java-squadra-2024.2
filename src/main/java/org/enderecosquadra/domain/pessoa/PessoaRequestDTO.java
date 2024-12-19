package org.enderecosquadra.domain.pessoa;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.enderecosquadra.domain.endereco.EnderecoRequestDTO;
import org.enderecosquadra.exceptions.exception.ExceptionDeRetorno;

import java.util.ArrayList;
import java.util.List;

public class PessoaRequestDTO {
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

    @NotEmpty(message = "Informa pelo menos um endereço")
    @Size(min = 1, message = "Informe pelo menos um endereço")
    @Valid
    List<EnderecoRequestDTO> enderecos = new ArrayList<>();

    public PessoaRequestDTO(String nome, String sobrenome, Integer idade, String login, String senha, Integer status, @NotEmpty @Valid List<EnderecoRequestDTO> enderecos) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.idade = idade;
        this.login = login;
        this.senha = senha;
        this.status = status;
        setEnderecos(enderecos);
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
        if (enderecos == null || enderecos.isEmpty()) {
            throw new IllegalArgumentException("A lista de endereços não pode estar vazia.");
        }
        this.enderecos = enderecos;
    }
}
