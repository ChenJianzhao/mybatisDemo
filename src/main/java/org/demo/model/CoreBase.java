package org.demo.model;

import java.io.Serializable;

/**
 * Created by pc on 2017/4/13.
 */
public abstract class CoreBase implements Serializable {

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer id;
}
