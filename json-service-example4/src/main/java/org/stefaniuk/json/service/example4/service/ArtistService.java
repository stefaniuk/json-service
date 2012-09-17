package org.stefaniuk.json.service.example4.service;

import java.util.List;

import org.stefaniuk.json.service.JsonService;
import org.stefaniuk.json.service.example4.model.Artist;
import org.stefaniuk.json.service.example4.model.ArtistDao;
import org.stefaniuk.json.service.example4.store.AbstractCRUDStore;
import org.stefaniuk.json.service.example4.store.QueryStore;

public class ArtistService extends AbstractCRUDStore<Artist, ArtistDao> {

    @Override
    @JsonService
    public List<Artist> select(QueryStore query) {

        return dao.findAll();
    }

    @Override
    @JsonService
    public Integer update(Integer id, Artist model) {

        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @JsonService
    public Integer insert(Artist model) {

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
