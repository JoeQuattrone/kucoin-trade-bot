package kucoin.bot.kline;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

/** Stores and Retrieves klines from database */
@RequiredArgsConstructor
public class KlineService {

  private final KlineDao klineDao;

  public List<Kline> saveAll(final List<Kline> klines) {
    return klineDao.saveAll(klines);
  }

  public Optional<Kline> findById(Integer id) {
    return klineDao.findById(id);
  }
}
