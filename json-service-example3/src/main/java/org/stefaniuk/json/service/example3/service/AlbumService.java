package org.stefaniuk.json.service.example3.service;

import java.util.List;

import org.stefaniuk.json.service.JsonService;
import org.stefaniuk.json.service.example3.model.Album;
import org.stefaniuk.json.service.example3.model.AlbumDao;

public class AlbumService extends AbstractService<AlbumDao> {

    @JsonService
    public List<Album> list(Integer artistId) {

        return dao.findByArtistId(artistId);
    }
}
