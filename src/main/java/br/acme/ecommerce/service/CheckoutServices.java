package br.acme.ecommerce.service;

import br.acme.ecommerce.configuration.RabbitConfigure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.acme.ecommerce.model.Sale;
import br.acme.ecommerce.model.Venda;
import br.acme.ecommerce.repository.SaleRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.client.RestTemplate;

@Service
public class CheckoutServices {

    @Autowired
    SaleRepository salesRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    RestTemplate restTemplate;

    public void saleProduct(final Sale sale) {
        salesRepository.save(sale);
        //atualizaEstoque(sale);
        sendHistoryToStockByRabbt(sale);
    }

    private void atualizaEstoque(Sale sale) {
        Venda venda = new Venda();
        venda.setId(sale.getIdProduct());
        venda.setQuantidade(sale.getAmount());
        restTemplate.postForObject("http://localhost:8090/estoque/atualizaVenda", venda, Venda.class);
    }

    /*
     * Atualiza estoque via fila de maneira assincrona
     */
    private void sendHistoryToStockByRabbt(final Sale sale) {
        Venda venda = new Venda();
        venda.setId(sale.getIdProduct());
        venda.setQuantidade(sale.getAmount());
        rabbitTemplate.convertAndSend(RabbitConfigure.SALE_EX, "", venda);
    }
}
