package br.com.alura.control.financeiro.infrastructure.gateway;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import br.com.alura.control.financeiro.core.entities.Revenue;
import br.com.alura.control.financeiro.core.usecase.revenue.RevenueDelete;
import br.com.alura.control.financeiro.core.usecase.revenue.RevenueFindAll;
import br.com.alura.control.financeiro.core.usecase.revenue.RevenueFindById;
import br.com.alura.control.financeiro.core.usecase.revenue.RevenueSave;
import br.com.alura.control.financeiro.core.usecase.revenue.RevenueUpdate;
import br.com.alura.control.financeiro.infrastructure.repository.RevenueRepository;
import br.com.alura.control.financeiro.validations.Message;
import br.com.alura.control.financeiro.validations.OnCreate;
import br.com.alura.control.financeiro.validations.OnUpdate;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Validated
@Service
@Slf4j
public class RevenueGateway implements RevenueFindAll, RevenueFindById, RevenueSave, RevenueDelete, RevenueUpdate {

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
    @Validated(OnCreate.class)
    public Revenue revenueSave(@Valid Revenue revenue) {

        log.info("revenue = {}", revenue);

        this.revenueRepository
                .findByDescriptionAndValueAndData(revenue.getDescription(), revenue.getValue(), revenue.getData())
                .orElseThrow(Message.IS_PRESENT_REVENUE::asBusinessException);

        return this.revenueSave(revenue);
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
    @Validated(OnUpdate.class)
    @Transactional
    public Revenue update(Long id, @Valid Revenue revenue) {

        log.info("id={} revenue={}", id, revenue);

        return revenue;
    }

}
