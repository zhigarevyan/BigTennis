package bigtennis.service;

import bigtennis.dao.UserDAO;
import bigtennis.entity.User;
import bigtennis.entity.dbEntity.LeagueEntity;
import bigtennis.entity.dbEntity.UserEntity;
import bigtennis.entity.dbEntity.UserRoleEntity;

import java.util.List;

public class UserService {

    private static final UserDAO userDAO = new UserDAO();

    public UserService() {
    }

    public UserEntity byId(int id) {
        return userDAO.findById(id);
    }

    public void save(UserEntity user) {
        userDAO.save(user);
    }

    public void delete(UserEntity user) {
        userDAO.delete(user);
    }

    public void update(UserEntity user) {
        userDAO.update(user);
    }

    public List<UserEntity> all() {
        return userDAO.findAll();
    }

    public boolean checkAccess(String mac){
        for(UserEntity user : userDAO.findAll()){
            if(user.getKey().equals(mac)){
                return true;
            }
        }
        return false;
    }

    public User byKey(String key) {
        List<UserEntity> userEntityList = userDAO.byKey(key);
        if (userEntityList.size() == 0) {
            return null;
        }
        return new User(userEntityList.get(0));
    }


}
