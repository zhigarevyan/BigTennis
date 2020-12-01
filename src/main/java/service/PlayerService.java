package service;

import dao.PlayerDAO;

import java.util.ArrayList;
import java.util.List;

public class PlayerService {
    private PlayerDAO playerDao = new PlayerDAO();

    public PlayerService() {
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
        for(PlayersEntity player : playerDao.findAll()){
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

    public PlayerEntity getOrNewByName(String name) {
        PlayerEntity player = byName(name);
        if (player == null) {
            PlayerEntity newPlayer = new PlayerEntity();
            newPlayer.setName(name);
            save(newPlayer);

            return byName(name);
        }
        return player;
    }

}
