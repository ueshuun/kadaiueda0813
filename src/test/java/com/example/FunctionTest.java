package com.example;

// import com.example.model.SampleModel;
// import com.example.service.SelectService;

// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.boot.test.mock.mockito.MockBean;
// import org.springframework.jdbc.core.JdbcTemplate;
// import org.springframework.jdbc.core.RowMapper;
// import reactor.core.publisher.Mono;

// import java.util.List;

// import static org.assertj.core.api.Assertions.assertThat;
// import static org.mockito.ArgumentMatchers.any;
// import static org.mockito.ArgumentMatchers.anyString;
// import static org.mockito.Mockito.when;

@SpringBootTest
public class FunctionTest {

    // @Autowired
    // private SelectService selectService;

    // @MockBean
    // private JdbcTemplate jdbcTemplate;

    // @Test
    // public void testResponseApply() {
    //     // JdbcTemplate のモック動作を定義
    //     List<SampleModel> mockResult = List.of(new SampleModel(1, "テストユーザー"));
    //     when(jdbcTemplate.query(anyString(), any(RowMapper.class))).thenReturn(mockResult);

    //     SampleModel sample = new SampleModel(1, "テストユーザー");
    //     Mono<SampleModel> input = Mono.just(sample);

    //     Mono<String> result = selectService.apply(input);
    //     String output = result.block();

    //     assertThat(output).contains("テストユーザー");
    // }
}