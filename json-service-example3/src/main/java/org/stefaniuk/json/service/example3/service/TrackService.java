package org.stefaniuk.json.service.example3.service;

import java.util.List;

import org.stefaniuk.json.service.JsonService;
import org.stefaniuk.json.service.example3.model.Track;
import org.stefaniuk.json.service.example3.model.TrackDao;

public class TrackService extends AbstractService<TrackDao> {

    @JsonService
    public List<Track> list() {

        return dao.findAll();
    }

}
