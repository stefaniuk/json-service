package org.stefaniuk.json.service.example3.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ArtistDao extends AbstractModelDao<Artist> {

    @Override
    public Integer create(Artist model) {

        return null;
    }

    @Override
    public Integer update(Artist model) {

        return null;
    }

    @Override
    public Integer update(Artist model, Artist changed) {

        return null;
    }

    @Override
    public Integer remove(Artist model) {

        return null;
    }

    @Override
    public List<Artist> findAll() {

        List<Artist> list = new ArrayList<Artist>();

        String sql = String.format("select * from Artist");
        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);
        for(Map<String, Object> row: rows) {
            // TODO
            //list.add(new Artist(row));
        }

        return list;
    }

    @Override
    public Artist findById(Integer id) {

        return null;
    }

    @Override
    public Integer findIdByModel(Artist model) {

        return null;
    }

    @Override
    public Integer countAll() {

        return null;
    }

}
