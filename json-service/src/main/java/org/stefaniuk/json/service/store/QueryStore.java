package org.stefaniuk.json.service.store;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * Dojo Toolkit query class.
 * 
 * @author Daniel Stefaniuk
 * @version 1.2.0
 * @since 2012/11/08
 */
public class QueryStore {

    public static class SortColumn {

        private String attribute;

        private boolean descending;

        public SortColumn() {

        }

        public SortColumn(String attribute, boolean descending) {

            this.attribute = attribute;
            this.descending = descending;
        }

        public String getAttribute() {

            return attribute;
        }

        public void setAttribute(String attribute) {

            this.attribute = attribute;
        }

        public boolean getDescending() {

            return descending;
        }

        public void setDescending(boolean descending) {

            this.descending = descending;
        }

    }

    private LinkedHashMap<String, Object> query;

    private LinkedHashMap<String, Object> queryOptions;

    private Integer count;

    private Integer start;

    private List<SortColumn> sort;

    private boolean isRender;

    public QueryStore() {

    }

    public QueryStore(LinkedHashMap<String, Object> query, LinkedHashMap<String, Object> queryOptions, Integer count,
            Integer start, List<SortColumn> sort, boolean isRender) {

        this.query = query;
        this.queryOptions = queryOptions;
        this.count = count;
        this.start = start;
        this.sort = sort;
        this.isRender = isRender;
    }

    public LinkedHashMap<String, Object> getQuery() {

        return query;
    }

    public void setQuery(LinkedHashMap<String, Object> query) {

        this.query = query;
    }

    public LinkedHashMap<String, Object> getQueryOptions() {

        return queryOptions;
    }

    public void setQueryOptions(LinkedHashMap<String, Object> queryOptions) {

        this.queryOptions = queryOptions;
    }

    public Integer getCount() {

        return count;
    }

    public void setCount(Integer count) {

        this.count = count;
    }

    public Integer getStart() {

        return start;
    }

    public void setStart(Integer start) {

        this.start = start;
    }

    public List<SortColumn> getSort() {

        return sort;
    }

    public void setSort(List<SortColumn> sort) {

        this.sort = sort;
    }

    public boolean getIsRender() {

        return isRender;
    }

    public void setIsRender(boolean isRender) {

        this.isRender = isRender;
    }

}
