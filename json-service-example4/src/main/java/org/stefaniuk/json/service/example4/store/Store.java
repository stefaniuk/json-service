package org.stefaniuk.json.service.example4.store;

import java.util.List;

public class Store<M> {

    private String identifier;

    private String idAttribute;

    private String label;

    private List<M> items;

    public Store(String identifier, String idAttribute, String label, List<M> items) {

        this.identifier = identifier;
        this.idAttribute = idAttribute;
        this.label = label;
        this.items = items;
    }

    public String getIdentifier() {

        return identifier;
    }

    public void setIdentifier(String identifier) {

        this.identifier = identifier;
    }

    public String getIdAttribute() {

        return idAttribute;
    }

    public void setIdAttribute(String idAttribute) {

        this.idAttribute = idAttribute;
    }

    public String getLabel() {

        return label;
    }

    public void setLabel(String label) {

        this.label = label;
    }

    public List<M> getItems() {

        return items;
    }

    public void setItems(List<M> items) {

        this.items = items;
    }

}
