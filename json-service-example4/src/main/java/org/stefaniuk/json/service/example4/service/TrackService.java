package org.stefaniuk.json.service.example4.service;

import java.util.List;
import java.util.Map;

import org.stefaniuk.json.service.JsonService;
import org.stefaniuk.json.service.example4.model.Track;
import org.stefaniuk.json.service.example4.model.TrackDao;
import org.stefaniuk.json.service.example4.store.AbstractCRUDStore;
import org.stefaniuk.json.service.example4.store.QueryStore;

public class TrackService extends AbstractCRUDStore<Track, TrackDao> {

    @Override
    @JsonService
    public List<Track> select(QueryStore query) {

        Map<String, Object> queryOptions = query.getQueryOptions();
        if(queryOptions != null && queryOptions.containsKey("parentId")) {
            System.out.println("TrackService parentId: " + queryOptions.get("parentId"));
            return dao.findByAlbumId((Integer) queryOptions.get("parentId"));
        }
        else {
            System.out.println("TrackService all");
            return dao.findAll();
        }
    }

    @Override
    @JsonService
    public Integer update(Integer id, Track model) {

        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @JsonService
    public Integer insert(Track model) {

        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @JsonService
    public Integer delete(Integer id) {

        // TODO Auto-generated method stub
        return null;
    }

}
