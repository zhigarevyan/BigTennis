package bigtennis.service;

import bigtennis.dao.CourtTypeDAO;
import bigtennis.entity.dbEntity.CourtTypeEntity;
import bigtennis.entity.dbEntity.PlayerEntity;

import java.util.HashMap;
import java.util.List;

public class CourtTypeService {
    private CourtTypeDAO courtTypeDAO = new CourtTypeDAO();

    private HashMap<String, CourtTypeEntity> courtTypeMap;

    public CourtTypeService() {
        updateCourtTypeMap();
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

    private void updateCourtTypeMap() {
        courtTypeMap = new HashMap<>();
        List<CourtTypeEntity> courtTypeEntityList = courtTypeDAO.findAll();

        courtTypeEntityList.forEach(courtTypeEntity -> courtTypeMap.put(courtTypeEntity.getName(),courtTypeEntity));
    }

    public CourtTypeEntity fromMap(String name) {
        return courtTypeMap.get(name);
    }

    public CourtTypeEntity getOrNewByName(String name) {
        CourtTypeEntity courtType = fromMap(name);
        if (courtType != null) {
            return courtType;
        }
        CourtTypeEntity newCourtType = new CourtTypeEntity();

        newCourtType.setName(name);
        save(newCourtType);

        updateCourtTypeMap();
        return fromMap(name);
    }

    public CourtTypeEntity byName(String name) {
        List<CourtTypeEntity> courtTypeList = courtTypeDAO.byName(name);
        if (courtTypeList.size() == 0) {
            return null;
        }
        return courtTypeList.get(0);
    }

}
