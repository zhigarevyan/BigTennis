package service;

import dao.CourtTypeDAO;
import dao.LeagueDAO;
import entity.dbEntity.CourtTypeEntity;
import entity.dbEntity.LeagueEntity;

import java.util.List;

public class CourtTypeService {
    private CourtTypeDAO courtTypeDAO = new CourtTypeDAO();

    public CourtTypeService() {
    }

    public CourtTypeEntity byId(int id) {
        return courtTypeDAO.findById(id);
    }

    public void save(CourtTypeEntity courtType) {
        courtTypeDAO.save(courtType);
    }

    public void delete(CourtTypeEntity courtType) {
        courtTypeDAO.delete(courtType);
    }

    public void update(CourtTypeEntity courtType) {
        courtTypeDAO.update(courtType);
    }

    public List<CourtTypeEntity> all() {
        return courtTypeDAO.findAll();
    }

    public CourtTypeEntity getOrNewByName(String name) {
        CourtTypeEntity courtType = byName(name);
        if (courtType == null) {
            CourtTypeEntity newCourtType = new CourtTypeEntity();

            newCourtType.setName(name);
            save(newCourtType);
            return byName(name);
        }
        return courtType;
    }

    public CourtTypeEntity byName(String name) {
        List<CourtTypeEntity> courtTypeList = courtTypeDAO.byName(name);
        if (courtTypeList.size() == 0) {
            return null;
        }
        return courtTypeList.get(0);
    }

}
