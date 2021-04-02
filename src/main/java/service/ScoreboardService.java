package service;

import exception.PersistentException;
import model.Scoreboard;

public interface ScoreboardService extends Service {
    void updateScore(Scoreboard sc) throws PersistentException;

    Scoreboard getScoreboard() throws PersistentException;

    void close() throws PersistentException;
}
