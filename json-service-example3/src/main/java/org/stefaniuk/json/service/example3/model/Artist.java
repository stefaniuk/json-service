package org.stefaniuk.json.service.example3.model;

import java.util.Map;

@ModelTable(name = "Artist")
public class Artist extends AbstractModel {

    @ModelColumn(name = "ArtistId")
    private Integer id;

    @ModelColumn(name = "Name")
    private String name;

    public Artist(Integer id, String name) {

        this.id = id;
        this.name = name;
    }

    public Artist(Map<String, Object> row) {

        setId((Integer) row.get(getColumnName(this.getClass(), "id")));
        setName((String) row.get(getColumnName(this.getClass(), "name")));
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

}
