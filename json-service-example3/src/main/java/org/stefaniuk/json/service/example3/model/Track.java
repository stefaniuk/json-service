package org.stefaniuk.json.service.example3.model;

import java.util.Map;

@ModelTable(name = "Track")
public class Track extends AbstractModel {

    @ModelColumn(name = "TrackId")
    private Integer id;

    public Track(Integer id) {

        this.id = id;
    }

    public Track(Map<String, Object> row) {

        setId((Integer) row.get(getColumnName(this.getClass(), "id")));
    }

    public Integer getId() {

        return id;
    }

    public void setId(Integer id) {

        this.id = id;
    }

}
