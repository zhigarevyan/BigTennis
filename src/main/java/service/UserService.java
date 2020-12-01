package service;

import dao.UserDAO;
import entity.dbEntity.ResultEntity;
import entity.dbEntity.UserEntity;

import java.util.List;

public class UserService {

    private UserDAO userDAO = new UserDAO();

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


}
