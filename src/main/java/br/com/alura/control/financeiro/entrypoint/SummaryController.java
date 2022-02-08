package br.com.alura.control.financeiro.entrypoint;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.control.financeiro.core.response.SummaryResponse;
import br.com.alura.control.financeiro.core.usecase.SummaryUseCase;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
public class SummaryController {

    private SummaryUseCase summaryUseCase;

    @GetMapping("/{year}/{month}")
    public ResponseEntity<SummaryResponse> summary(@PathVariable("year") String year,
            @PathVariable("month") String month) {
        return ResponseEntity.ok(summaryUseCase.summary(month, year));
    }
}
