package tabletennis.service;

import tabletennis.dao.AppuserDao;
import tabletennis.entity.dbEntity.AppusersEntity;

import java.util.List;

public class AppuserService {

    private AppuserDao appuserDao = new AppuserDao();

    public AppuserService() {
    }

    public AppusersEntity byId(int id) {
        return appuserDao.findById(id);
    }

    public void save(AppusersEntity appuser) {
        appuserDao.save(appuser);
    }

    public void delete(AppusersEntity appuser) {
        appuserDao.delete(appuser);
    }

    public void update(AppusersEntity appuser) {
        appuserDao.update(appuser);
    }

    public List<AppusersEntity> all() {
        return appuserDao.findAll();
    }

    public boolean checkAccess(String mac){
        for(AppusersEntity user : appuserDao.findAll()){
            if(user.getMac().equals(mac)){
                return true;
            }
        }
        return false;
    }
    
}