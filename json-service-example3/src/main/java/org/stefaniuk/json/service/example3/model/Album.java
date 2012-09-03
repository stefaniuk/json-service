package org.stefaniuk.json.service.example3.model;

import java.util.Map;

@ModelTable(name = "Album")
public class Album extends AbstractModel {

    @ModelColumn(name = "AlbumId")
    private Integer id;

    public Album(Integer id) {

        this.id = id;
    }

    public Album(Map<String, Object> row) {

        setId((Integer) row.get(getColumnName(this.getClass(), "id")));
    }

    public Integer getId() {

        return id;
    }

    public void setId(Integer id) {

        this.id = id;
    }

}
