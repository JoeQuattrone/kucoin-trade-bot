package app.kline;

import app.currency.CurrencyPair;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/** Stores and Retrieves klines from database */
@RequiredArgsConstructor
@Service
public class KlineService {

  private final KlineDao klineDao;

  public List<Kline> saveAll(final List<Kline> klines) {
    return klineDao.saveAll(klines);
  }

  public Optional<Kline> findById(Integer id) {
    return klineDao.findById(id);
  }

  public List<Double> findAllByCurrencyOrdinal(CurrencyPair currency) {
    return klineDao.findAllByCurrencyOrdinal(currency, Sort.by("time").descending());
  }
}
