package org.stefaniuk.json.service.example4.service;

import java.util.List;
import java.util.Map;

import org.stefaniuk.json.service.JsonService;
import org.stefaniuk.json.service.example4.model.Album;
import org.stefaniuk.json.service.example4.model.AlbumDao;
import org.stefaniuk.json.service.example4.store.AbstractCRUDStore;
import org.stefaniuk.json.service.example4.store.QueryStore;

public class AlbumService extends AbstractCRUDStore<Album, AlbumDao> {

    @Override
    @JsonService
    public List<Album> select(QueryStore query) {

        Map<String, Object> queryOptions = query.getQueryOptions();
        if(queryOptions != null && queryOptions.containsKey("parentId")) {
            System.out.println("AlbumService parentId: " + queryOptions.get("parentId"));
            return dao.findByArtistId((Integer) queryOptions.get("parentId"));
        }
        else {
            System.out.println("AlbumService all");
            return dao.findAll();
        }
    }

    @Override
    @JsonService
    public Integer update(Integer id, Album model) {

        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @JsonService
    public Integer insert(Album model) {

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
