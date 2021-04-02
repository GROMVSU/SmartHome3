package service.impl;

import dao.ScoreboardDao;
import exception.PersistentException;
import model.Scoreboard;
import service.ScoreboardService;

public class ScoreboardServiceImpl extends ServiceImpl implements ScoreboardService {
    @Override
    public void updateScore(Scoreboard sc) throws PersistentException {
        ScoreboardDao dao = factory.createDao(ScoreboardDao.class);
        Scoreboard scoreboard = dao.read(1);
        if (scoreboard == null) {
            scoreboard = new Scoreboard();
            dao.create(scoreboard);
        }
        dao.update(sc);
    }

    @Override
    public Scoreboard getScoreboard() throws PersistentException {
        ScoreboardDao dao = factory.createDao(ScoreboardDao.class);
        Scoreboard sc = dao.read(1);
        if (sc == null) {
            sc = new Scoreboard();
            dao.create(sc);
        }
        return sc;
    }

    @Override
    public void close() throws PersistentException {
        factory.close();
    }
}
