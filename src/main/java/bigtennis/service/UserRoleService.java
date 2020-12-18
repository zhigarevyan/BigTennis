package bigtennis.service;

import bigtennis.dao.UserRoleDAO;
import bigtennis.entity.Role;
import bigtennis.entity.dbEntity.UserEntity;
import bigtennis.entity.dbEntity.UserRoleEntity;

import java.util.List;

public class UserRoleService {

    private UserRoleDAO userRoleDAO = new UserRoleDAO();

    public UserRoleService() {
    }

    public UserRoleEntity getEntitybyId(int id) {
        return userRoleDAO.findById(id);
    }

    public Role byID(int id) {
        return new Role(getEntitybyId(id));
    }

    public void save(UserRoleEntity role) {
        userRoleDAO.save(role);
    }

    public void delete(UserRoleEntity role) {
        userRoleDAO.delete(role);
    }

    public void update(UserRoleEntity role) {
        userRoleDAO.update(role);
    }

    public List<UserRoleEntity> all() {
        return userRoleDAO.findAll();
    }

}
