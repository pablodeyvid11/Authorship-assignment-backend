/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dweauthorshipattribution.tests;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author elenilson
 */
@Entity
public class Test implements Serializable {

    private long id;
    private String v;

    @Id
    @GeneratedValue
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getV() {
        return v;
    }

    public void setV(String v) {
        this.v = v;
    }

    public Test() {
    }

    public Test(String v) {
        this.v = v;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Test other = (Test) obj;
        if ((this.v == null) ? (other.v != null) : !this.v.equals(other.v)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + (this.v != null ? this.v.hashCode() : 0);
        return hash;
    }
}
