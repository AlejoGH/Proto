/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.querys.notResolvetoday;

import java.io.Serializable;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SqlResultSetMapping;

/**
 *
 * @author ncabrejo
 */
@Entity
@SqlResultSetMapping(
        name = "consulta1",
        classes = @ConstructorResult(
                targetClass = NotResolveToday.class,
                columns = {
                    @ColumnResult(name = "user_id")
                    ,
                @ColumnResult(name = "name")}))
public class NotResolveToday implements Serializable {

    @Id
    private Integer user_id;

    private String name;

    /**
     * @return the user_id
     */
    public Integer getUser_id() {
        return user_id;
    }

    /**
     * @param user_id the user_id to set
     */
    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
}
