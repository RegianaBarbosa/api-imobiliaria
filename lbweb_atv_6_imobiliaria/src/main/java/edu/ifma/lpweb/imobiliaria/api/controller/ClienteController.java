package edu.ifma.lpweb.imobiliaria.api.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URI;
import java.rmi.ServerException;
import java.util.Optional;

import org.apache.tomcat.util.http.parser.MediaType;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import edu.ifma.lpweb.imobiliaria.domain.model.Cliente;
import edu.ifma.lpweb.imobiliaria.domain.service.ClienteService;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private ClienteService clienteService;

    ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }


    @GetMapping
    public Iterable<Cliente> lista(String nome) {
        if (nome == null) {
            return clienteService.todos();
        } else {
            return clienteService.buscaPor(nome);
        }

    }

    @GetMapping("paginacao/{numPagina}/{atdPagina}")
    public Iterable<Cliente> buscaPaginada(
            @PathVariable int numPagina,
            @PathVariable int qtdPagina) {
        if (qtdPagina > 10)
            qtdPagina = 10;
        PageRequest page = PageRequest.of(numPagina, qtdPagina);
        return clienteService.buscaPaginada(page);
    }

    @GetMapping("/paginacao")
    public Iterable<Cliente> lista(
            @RequestParam(required = false) String nome,
            @PageableDefault(sort = "nome", direction = Sort.Direction.ASC, page = 0, size = 5) Pageable paginacao) {
        if (nome == null) {
            return clienteService.buscaPaginada(paginacao);
        } else {
            return clienteService.buscaPor(nome, paginacao);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscaPor(@PathVariable Integer id) {
        return clienteService.buscaPor(id)
                .map(ResponseEntity::ok) // .map(cliente -> ResponseEntity.ok(cliente))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Cliente> cadastro(@Valid @RequestBody Cliente cliente, UriComponentsBuilder builder) {
        final Cliente clienteSalvo = clienteService.salva(cliente);
        final URI uri = builder
                     .path("/clientes/{id}")
                     .buildAndExpand(clienteSalvo.getId()).toUri();

        return ResponseEntity.created(uri).body(clienteSalvo );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualiza(@PathVariable Integer id,
            @Valid @RequestBody Cliente cliente) {
        if (clienteService.naoExisteClienteCom(id)) {
            return ResponseEntity.notFound().build();

        } else {
            cliente.setId(id);
            Cliente clienteAtualizado = clienteService.salva(cliente);
            return ResponseEntity.ok(clienteAtualizado);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remover(@PathVariable Integer id) {

        Optional<Cliente> optional = clienteService.buscaPor(id);

        if (optional.isPresent()) {
            clienteService.removePelo(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}