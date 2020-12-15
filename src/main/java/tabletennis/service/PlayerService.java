package tabletennis.service;

import tabletennis.dao.PlayerDao;
import tabletennis.entity.dbEntity.PlayersEntity;

import java.util.ArrayList;
import java.util.List;

public class PlayerService {

    private PlayerDao playerDao = new PlayerDao();

    public PlayerService() {
    }

    public PlayersEntity byId(int id) {
        return playerDao.findById(id);
    }

    public void save(PlayersEntity player) {
        playerDao.save(player);
    }

    public void delete(PlayersEntity player) {
        playerDao.delete(player);
    }

    public void update(PlayersEntity player) {
        playerDao.update(player);
    }

    public List<String> all() {
        ArrayList<String> playerNames = new ArrayList<>();
        for(PlayersEntity player : playerDao.findAll()){
            playerNames.add(player.getName());
        }
        return playerNames;
    }

    public PlayersEntity byName(String name) {
        List<PlayersEntity> playerList = playerDao.byName(name);
        if (playerList.size() == 0) {
            return null;
        }
        return playerList.get(0);
    }

    public PlayersEntity getOrNewByName(String name) {
        PlayersEntity player = byName(name);
        if (player == null) {
            PlayersEntity newPlayer = new PlayersEntity();
            newPlayer.setName(name);
            save(newPlayer);

            return byName(name);
        }
        return player;
    }
}