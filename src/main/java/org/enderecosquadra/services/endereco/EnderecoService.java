package org.enderecosquadra.services.endereco;

import org.enderecosquadra.domain.endereco.Endereco;
import org.enderecosquadra.repositories.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    public void adicionarEndereco(Endereco endereco){
        enderecoRepository.save(endereco);
    }
}
