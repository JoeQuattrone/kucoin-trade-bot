package kucoin.bot.kline;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KlineDao extends JpaRepository<Kline, Integer> {
  @Override
  Optional<Kline> findById(Integer integer);

  @Override
  <S extends Kline> List<S> saveAll(Iterable<S> klines);
}
