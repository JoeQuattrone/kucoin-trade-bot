package app.kline;

import app.currency.CurrencyPair;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface KlineDao extends JpaRepository<Kline, Integer> {
  @Override
  Optional<Kline> findById(Integer integer);

  @Override
  <S extends Kline> List<S> saveAll(Iterable<S> klines);

  // Gets all closing prices and sorts by latest price
  @Query(value = "SELECT k.close FROM Kline k WHERE k.currency = :currency")
  List<Double> findAllByCurrencyOrdinal(@Param("currency") CurrencyPair currency, final Sort sort);
}
