package org.stefaniuk.json.service.example3.model;

import java.util.Map;

@ModelTable(name = "Album")
public class Album extends AbstractModel {

    @ModelColumn(name = "AlbumId")
    private Integer id;

    @ModelColumn(name = "Title")
    private String title;

    @ModelColumn(name = "ArtistId")
    private Integer artistId;

    public Album(Integer id, String title, Integer artistId) {

        this.id = id;
        this.title = title;
        this.artistId = artistId;
    }

    public Album(Map<String, Object> row) {

        setId((Integer) row.get(getColumnName(this.getClass(), "id")));
        setTitle((String) row.get(getColumnName(this.getClass(), "title")));
        setArtistId((Integer) row.get(getColumnName(this.getClass(), "artistId")));
    }

    public Integer getId() {

        return id;
    }

    public void setId(Integer id) {

        this.id = id;
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {

        this.title = title;
    }

    public Integer getArtistId() {

        return artistId;
    }

    public void setArtistId(Integer artistId) {

        this.artistId = artistId;
    }

}
