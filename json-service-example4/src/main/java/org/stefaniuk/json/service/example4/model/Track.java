package org.stefaniuk.json.service.example4.model;

import java.util.Map;

@ModelTable(name = "Track")
public class Track extends AbstractModel {

    @ModelColumn(name = "TrackId")
    private Integer id;

    @ModelColumn(name = "Name")
    private String name;

    @ModelColumn(name = "AlbumId")
    private Integer albumId;

    @ModelColumn(name = "Composer")
    private String composer;

    @ModelColumn(name = "Miliseconds")
    private Integer miliseconds;

    @ModelColumn(name = "Bytes")
    private Integer bytes;

    @ModelColumn(name = "UnitPrice")
    private Double unitPrice;

    public Track(Integer id) {

        this.id = id;
    }

    public Track(Map<String, Object> row) {

        setId((Integer) row.get(getColumnName(this.getClass(), "id")));
        setName((String) row.get(getColumnName(this.getClass(), "name")));
        setAlbumId((Integer) row.get(getColumnName(this.getClass(), "albumId")));
        setComposer((String) row.get(getColumnName(this.getClass(), "composer")));
        setMiliseconds((Integer) row.get(getColumnName(this.getClass(), "miliseconds")));
        setBytes((Integer) row.get(getColumnName(this.getClass(), "bytes")));
        setUnitPrice((Double) row.get(getColumnName(this.getClass(), "unitPrice")));     
    }

    public Integer getId() {

        return id;
    }

    public void setId(Integer id) {

        this.id = id;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public Integer getAlbumId() {

        return albumId;
    }

    public void setAlbumId(Integer albumId) {

        this.albumId = albumId;
    }

    public String getComposer() {

        return composer;
    }

    public void setComposer(String composer) {

        this.composer = composer;
    }

    public Integer getMiliseconds() {

        return miliseconds;
    }

    public void setMiliseconds(Integer miliseconds) {

        this.miliseconds = miliseconds;
    }

    public Integer getBytes() {

        return bytes;
    }

    public void setBytes(Integer bytes) {

        this.bytes = bytes;
    }

    public Double getUnitPrice() {

        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {

        this.unitPrice = unitPrice;
    }

}
