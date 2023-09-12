package mju.chatuniv.statistic.application;

import java.util.List;
import mju.chatuniv.chat.domain.word.Word;
import mju.chatuniv.statistic.domain.Statistic;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StatisticService {

    @Transactional(readOnly = true)
    public List<Word> findStatistics() {
        return Statistic.getWords();
    }

    @Scheduled(cron = "0 0 * * * *")
    public void updateStatistics() {
        Statistic.reset();
    }
}
