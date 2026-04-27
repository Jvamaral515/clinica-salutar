package br.com.isiflix.salutar.controller;

import br.com.isiflix.salutar.model.Midia;
import br.com.isiflix.salutar.service.Midia.IMidiaService;
import br.com.isiflix.salutar.service.Midia.MidiaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.reactive.ClientHttpResponseDecorator;
import org.springframework.web.bind.annotation.*;

@RestController("/")
@CrossOrigin("*")
public class MidiaController {

    @Autowired
    private IMidiaService service;

    @GetMapping("/midias/{id}")
    public ResponseEntity<Midia> recuperarPeloId(@PathVariable Integer id){
        Midia m = service.buscarPorId(id);
        if (m != null)
            return ResponseEntity.ok(m);
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/midias")
    public ResponseEntity<Midia> adicionarNova(@RequestBody Midia midia){
        Midia res = service.cadastrarNova(midia);
        if (res != null)
            return ResponseEntity.status(201).body(res);
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/midias/{id}")
    public ResponseEntity<Midia> atualizarMidia(@RequestBody Midia midia, @PathVariable Integer id){
        if (midia.getNumSeq() == null){
            midia.setNumSeq(id);
        }
        Midia res = service.alterarDados(midia);
        if (res != null)
            return ResponseEntity.ok(res);
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/midias/{id}")
    public ResponseEntity<?> excluirMidia(@PathVariable Integer id){
         if (service.excluir(id))
             return ResponseEntity.ok("Ok");
         return ResponseEntity.notFound().build();
    }
}
