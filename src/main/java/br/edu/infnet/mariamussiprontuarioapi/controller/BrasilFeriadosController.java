package br.edu.infnet.mariamussiprontuarioapi.controller;

import br.edu.infnet.mariamussiprontuarioapi.model.domain.Feriado;
import br.edu.infnet.mariamussiprontuarioapi.model.service.FeriadosService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/consulta-feriados")
public class BrasilFeriadosController {

    private final FeriadosService feriadosService;

    public BrasilFeriadosController(FeriadosService feriadosService) {
        this.feriadosService = feriadosService;
    }

    @GetMapping("/{ano}")
    public ResponseEntity<List<Feriado>> obterFeriadosPorAno (@PathVariable String ano) {
        List<Feriado> feriadosResult = feriadosService.obterFeriadosPorAno(ano);

        return ResponseEntity.ok(feriadosResult);
    }
}
