package com.upwork.test.component.cache;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CacheServiceImplTest {

  @Autowired
  private CacheService cacheService;

  @Test
  public void put() {
    String hash = "someHash";
    cacheService.put(hash, BigDecimal.ONE);
    BigDecimal one = cacheService.get(hash);
    assertThat(one).isNotNull();
    assertThat(one).isEqualTo(BigDecimal.ONE);
  }

  @Test
  public void shouldReturnNullValue() {
    BigDecimal value = cacheService.get("no-one-here");
    assertThat(value).isNull();
  }
}
