package br.com.fiap.api_rest.service;

import br.com.fiap.api_rest.dto.ClienteRequest;
import br.com.fiap.api_rest.dto.ClienteResponse;
import br.com.fiap.api_rest.model.Cliente;
import br.com.fiap.api_rest.repository.ClienteRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }
//    @Autowired

    public Cliente requestToCliente(ClienteRequest clienteRequest) {
        return new Cliente(null,
                clienteRequest.getNome(),
                clienteRequest.getIdade(),
                clienteRequest.getEmail(),
                clienteRequest.getSenha(),
                clienteRequest.getCpf(),
                clienteRequest.getCategoria());
    }

    public ClienteResponse clienteToResponse(Cliente cliente) {
        return new ClienteResponse(cliente.getId(), cliente.getNome());
    }

    public List<ClienteResponse> clientesToResponse(List<Cliente> clientes) {
        List<ClienteResponse> clientesResponse = new ArrayList<>();
        for (Cliente cliente : clientes) {
            clientesResponse.add(clienteToResponse(cliente));
        }
        return clientesResponse;
    }

    public Page<ClienteResponse> findAll(Pageable pageable) {
        //return clienteRepository.findAll(pageable).map(cliente -> clienteToResponse(cliente));
        return clienteRepository.findAll(pageable).map(this::clienteToResponse);
    }
}
