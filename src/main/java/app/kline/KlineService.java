package app.kline;

import app.currency.CurrencyPair;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
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

  @Transactional
  public List<Double> findAllByCurrencyOrdinal(CurrencyPair currency) {
    return klineDao.findAllByCurrencyOrdinal(currency, Sort.by("time").descending());
  }

  @Transactional
  public Kline findLatestKline() {
    return klineDao
        .findLatestKline(PageRequest.of(0, 1, Sort.by("time").descending()))
        .getContent()
        .get(0);
  }
}
