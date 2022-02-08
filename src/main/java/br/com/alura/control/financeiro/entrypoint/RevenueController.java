package br.com.alura.control.financeiro.entrypoint;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.control.financeiro.core.request.RevenueRequest;
import br.com.alura.control.financeiro.core.response.RevenueResponse;
import br.com.alura.control.financeiro.core.usecase.revenue.RevenueDeleteUseCase;
import br.com.alura.control.financeiro.core.usecase.revenue.RevenueFindAllUseCase;
import br.com.alura.control.financeiro.core.usecase.revenue.RevenueFindByDataUseCase;
import br.com.alura.control.financeiro.core.usecase.revenue.RevenueFindByDescriptionUseCase;
import br.com.alura.control.financeiro.core.usecase.revenue.RevenueFindByIdUseCase;
import br.com.alura.control.financeiro.core.usecase.revenue.RevenueSaveUseCase;
import br.com.alura.control.financeiro.core.usecase.revenue.RevenueUpdateUseCase;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/receitas")
public class RevenueController {

    private RevenueFindAllUseCase revenueFindAllUseCase;

    private RevenueFindByIdUseCase revenueFindByIdUseCase;

    private RevenueDeleteUseCase revenueDeleteUseCase;

    private RevenueSaveUseCase revenueSaveUseCase;

    private RevenueUpdateUseCase revenueUpdateUseCase;

    private RevenueFindByDescriptionUseCase revenueFindByDescriptionUseCase;

    private RevenueFindByDataUseCase revenueFindByDataUseCase;

    @GetMapping("/{year}/{month}")
    public ResponseEntity<List<RevenueResponse>> findByData(@PathVariable("year") String year,
            @PathVariable("month") String month) {
        return ResponseEntity.ok(revenueFindByDataUseCase.findByData(month, year));
    }

    @GetMapping
    public ResponseEntity<List<RevenueResponse>> findAll(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "limit", required = false, defaultValue = "10") int limit,
            @RequestParam(name = "descricao", required = false) String descricao) {
        return descricao == null ? ResponseEntity.ok(revenueFindAllUseCase.findAll(limit, page))
                : ResponseEntity.ok(revenueFindByDescriptionUseCase.findByDescrRevenue(descricao));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RevenueResponse> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(revenueFindByIdUseCase.findById(id));
    }

    @PostMapping
    public ResponseEntity<RevenueResponse> save(@RequestBody RevenueRequest revenueRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(revenueSaveUseCase.save(revenueRequest));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<RevenueResponse> update(@PathVariable("id") Long id,
            @RequestBody RevenueRequest revenueRequest) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(revenueUpdateUseCase.revenueUpdate(id, revenueRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        revenueDeleteUseCase.revenueDelete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
