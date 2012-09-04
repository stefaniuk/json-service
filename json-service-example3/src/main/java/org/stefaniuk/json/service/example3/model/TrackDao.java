package org.stefaniuk.json.service.example3.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TrackDao extends AbstractModelDao<Track> {

    @Override
    public Integer create(Track model) {

        return null;
    }

    @Override
    public Integer update(Track model) {

        return null;
    }

    @Override
    public Integer update(Track model, Track changed) {

        return null;
    }

    @Override
    public Integer remove(Track model) {

        return null;
    }

    @Override
    public List<Track> findAll() {

        return null;
    }

    public List<Track> findByAlbumId(Integer albumId) {

        List<Track> list = new ArrayList<Track>();

        String sql = String.format("select * from %1$s where AlbumID = " + albumId, TABLE_NAME);
        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);
        for(Map<String, Object> row: rows) {
            list.add(new Track(row));
        }

        return list;
    }

    @Override
    public Track findById(Integer id) {

        return null;
    }

    @Override
    public Integer findIdByModel(Track model) {

        return null;
    }

    @Override
    public Integer countAll() {

        return null;
    }

}
