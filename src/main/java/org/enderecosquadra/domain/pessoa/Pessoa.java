package org.enderecosquadra.domain.pessoa;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import org.enderecosquadra.domain.endereco.Endereco;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TB_PESSOA")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonPropertyOrder({"codigoPessoa", "nome", "sobrenome", "idade", "login", "senha", "status", "enderecos"})
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqPessoa")
    @SequenceGenerator(name = "seqPessoa", sequenceName = "SEQUENCE_PESSOA", allocationSize = 1)
    @Column(name = "CODIGO_PESSOA")
    private Long codigoPessoa;

    private String nome;

    private String sobrenome;

    private Integer idade;

    private String login;

    private String senha;

    private Integer status;

    @OneToMany(mappedBy = "pessoa", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Endereco> enderecos = new ArrayList<>();

    // CONSTRUCTORS ------------------------------------------------------------------------------------------------
    public Pessoa() {

    }

    public Pessoa(String nome, String sobrenome, Integer idade, String login, String senha, Integer status) {
        setNome(nome);
        setSobrenome(sobrenome);
        setIdade(idade);
        setLogin(login);
        setSenha(senha);
        setStatus(status);
    }

    // GETTERS AND SETTERS ----------------------------------------------------------------------------------------
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
        this.nome = nome.toUpperCase();
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome.toUpperCase();
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

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }
}
