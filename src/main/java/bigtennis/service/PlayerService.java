package bigtennis.service;

import bigtennis.dao.PlayerDAO;
import bigtennis.entity.dbEntity.PlayerEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PlayerService {
    private static final PlayerDAO playerDao = new PlayerDAO();

    private HashMap<String, PlayerEntity> playerMap;

    public PlayerService() {
        updatePlayerMap();
    }

    public PlayerEntity byId(int id) {
        return playerDao.findById(id);
    }

    public void save(PlayerEntity player) {
        playerDao.save(player);
    }

    public void delete(PlayerEntity player) {
        playerDao.delete(player);
    }

    public void update(PlayerEntity player) {
        playerDao.update(player);
    }

    public List<String> all() {
        ArrayList<String> playerNames = new ArrayList<>();
        for(PlayerEntity player : playerDao.findAll()){
            playerNames.add(player.getName());
        }
        return playerNames;
    }

    public PlayerEntity byName(String name) {
        List<PlayerEntity> playerList = playerDao.byName(name);
        if (playerList.size() == 0) {
            return null;
        }
        return playerList.get(0);
    }

    public PlayerEntity fromMap(String name) {
        return playerMap.get(name);
    }

    private void updatePlayerMap() {
        playerMap = new HashMap<>();
        List<PlayerEntity> playerEntityList = playerDao.findAll();

        playerEntityList.forEach(playerEntity -> playerMap.put(playerEntity.getName(),playerEntity));
    }

    public PlayerEntity getOrNewByName(String name) {
        PlayerEntity player = fromMap(name);
        if (player != null) {
            return player;
        }

        PlayerEntity newPlayer = new PlayerEntity();
        newPlayer.setName(name);
        save(newPlayer);

        updatePlayerMap();
        return fromMap(name);
    }

}
