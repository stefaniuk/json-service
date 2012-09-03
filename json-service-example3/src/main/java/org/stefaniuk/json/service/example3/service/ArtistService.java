package org.stefaniuk.json.service.example3.service;

import java.util.List;

import org.stefaniuk.json.service.JsonService;
import org.stefaniuk.json.service.example3.model.Artist;
import org.stefaniuk.json.service.example3.model.ArtistDao;

public class ArtistService extends AbstractService<ArtistDao> {

    @JsonService
    public List<Artist> list() {

        return dao.findAll();
    }
}
