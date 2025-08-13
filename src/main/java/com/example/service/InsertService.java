package com.example.service;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.example.model.SampleModel;
import com.fasterxml.jackson.databind.ObjectMapper;

import reactor.core.publisher.Mono;

@Component("INSERT")
public class InsertService implements Function<Mono<SampleModel>, Mono<String>> {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Mono<String> apply(Mono<SampleModel> mono) {
        return mono.map(sample -> {

            String responseMessage = "INSERT RESULT";

            String id = String.valueOf(sample.getId());
            String product = sample.getproduct();
            String price = String.valueOf(sample.getprice());
            String quantity = String.valueOf(sample.getquantity());
 
            if (id != null && product != null && price != null && quantity != null &&  !id.isEmpty() && !product.isEmpty() &&  !price.isEmpty() &&  !quantity.isEmpty()) {

                String sql = "INSERT INTO kadaiTable (id, product, price, quantity) VALUES (?, ?, ?, ?)";
                
                try {
                    int rows = jdbcTemplate.update(sql, Integer.parseInt(id), product,Integer.parseInt(price),Integer.parseInt(quantity));

                    Map<String, Object> result = new HashMap<>();
                    result.put(responseMessage, rows + " 行挿入されました");

                    ObjectMapper objectMapper = new ObjectMapper();
                    responseMessage = objectMapper.writeValueAsString(result);

                } catch (Exception e) {
                    e.printStackTrace();
                    responseMessage = "DBエラー: " + e.getMessage();
                }

            } else {
                responseMessage = "パラメーターが設定されていません";
            }
        
            return responseMessage;

        });
    }
}
