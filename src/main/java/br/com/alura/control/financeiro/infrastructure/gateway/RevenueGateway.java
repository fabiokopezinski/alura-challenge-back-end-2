package br.com.alura.control.financeiro.infrastructure.gateway;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.alura.control.financeiro.core.entities.Revenue;
import br.com.alura.control.financeiro.core.usecase.revenue.IRevenue;
import br.com.alura.control.financeiro.infrastructure.repository.RevenueRepository;
import br.com.alura.control.financeiro.validations.Message;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Service
@Slf4j
public class RevenueGateway implements IRevenue {

    private RevenueRepository revenueRepository;

    @Override
    public Page<Revenue> findAll(int limit, int offset) {

        log.info("limit={} offset={}", limit, offset);

        return this.revenueRepository.findAll(PageRequest.of(offset, limit));
    }

    @Override
    public Revenue findById(Long id) {

        return findByRevenue(id);
    }

    @Override
    public Revenue revenueSave(Revenue revenue) {

        log.info("revenue = {}", revenue.toString());

        this.revenueRepository
                .findByDescriptionAndValueAndData(revenue.getDescription(), revenue.getValue())
                .ifPresent(p -> {
                    throw Message.IS_PRESENT_REVENUE.asBusinessException();
                });

        return this.revenueRepository.save(revenue);
    }

    @Override
    public void delete(Long id) {

        findByRevenue(id);

        this.revenueRepository.deleteById(id);

    }

    private Revenue findByRevenue(Long id) {

        log.info("id={}", id);

        return this.revenueRepository.findById(id).orElseThrow(Message.NOT_FOUND_REVENUE::asBusinessException);
    }

    @Override
    public Revenue update(Long id, Revenue revenue) {

        log.info("id={} revenue={}", id, revenue);

        return revenue;
    }

    @Override
    public List<Revenue> findByDescription(String description) {

        log.info("description={}", description);

        return this.revenueRepository.findByDescription(description);
    }

    @Override
    public List<Revenue> findByData(String data) {

        log.info("data={}", data);

        return this.revenueRepository.findByData(data);
    }

    @Override
    public List<Revenue> findAll() {

        return this.revenueRepository.findAll();
    }

}
