package com.surveySpringBoot.tool;

import org.springframework.data.domain.Sort;

public class SortCriteria {
    private String field;
    private String direction;

    public SortCriteria() {

    }

    public SortCriteria(String field, String direction) {
        this.field     = field;
        this.direction = direction;
    }

    public String getField()        { return this.field;     }
    public String getDirection()    { return this.direction; }

    public void setField(String field)          { this.field     = field;     }
    public void setDirection(String direction)  { this.direction = direction; }

    public Sort.Direction getSortDirection() {
        if (this.direction.equals("asc"))   { return Sort.Direction.ASC;  }
        if (this.direction.equals("desc"))  { return Sort.Direction.DESC; }

        return Sort.Direction.ASC;
    }

    @Override
    public String toString() {
        return "SortCriteria{" +
                "field='" + field + '\'' +
                ", direction='" + direction + '\'' +
                '}';
    }
}