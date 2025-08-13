package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.example.model.SampleModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Component("SELECT")
public class SelectService implements Function<Mono<SampleModel>, Mono<String>> {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Mono<String> apply(Mono<SampleModel> mono) {
        return mono.map(sample -> {

            String responseMessage = "SQL RESULT:";

            System.out.println("Query data example:");
            System.out.println("=========================================");
            
            String sql = "SELECT id, product, price, quantity FROM kadaiTable";
            
            List<SampleModel> samples = jdbcTemplate.query(sql, (rs, rowNum) -> {
                SampleModel rsSample = new SampleModel(rs.getInt("id"), rs.getString("product"),rs.getInt("price"),rs.getInt("quantity"));
                System.out.println("id=" + rsSample.getId() + ", product=" + rsSample.getproduct() + "price=" + rsSample.getprice()+ "quantity=" + rsSample.getquantity());
                return rsSample;
            });

            ObjectMapper responseMapper = new ObjectMapper();
            try {  
                Map<String, Object> responseMap = new HashMap<>();
                responseMap.put("List", samples);  
                responseMessage = responseMapper.writeValueAsString(responseMap);    
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                responseMessage = "JSON変換時エラー";
            }

            return responseMessage;
        
        });
    }
}
