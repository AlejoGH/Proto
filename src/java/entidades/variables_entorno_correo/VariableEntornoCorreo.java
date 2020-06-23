package entidades.variables_entorno_correo;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

@Entity
@NamedQueries({
    @NamedQuery(name = "VariableEntornoCorreo.getAll",
            query = "SELECT c FROM VariableEntornoCorreo c")
    ,
        @NamedQuery(name = "VariableEntornoCorreo.getId",
            query = "SELECT CASE WHEN MAX(c.variable_id) IS NULL "
            + "THEN 1 ELSE (MAX(c.variable_id) + 1) END FROM VariableEntornoCorreo c")
    ,
        @NamedQuery(name = "VariableEntornoCorreo.getActive", query = ""
                + "SELECT c FROM VariableEntornoCorreo c WHERE c.variable_active = true")

}
)

@Table(name = "variables_entorno_correo")
public class VariableEntornoCorreo implements Serializable {

    @Id
    private Integer variable_id;

    private String correo_from;
    private String correo_pass;
    private String correo_host;
    private String correo_port;
    private Boolean variable_active;

    public VariableEntornoCorreo() {

    }

    /**
     * @return the variable_id
     */
    public Integer getVariable_id() {
        return variable_id;
    }

    /**
     * @param variable_id the variable_id to set
     */
    public void setVariable_id(Integer variable_id) {
        this.variable_id = variable_id;
    }

    /**
     * @return the correo_from
     */
    public String getCorreo_from() {
        return correo_from;
    }

    /**
     * @param correo_from the correo_from to set
     */
    public void setCorreo_from(String correo_from) {
        this.correo_from = correo_from;
    }

    /**
     * @return the correo_pass
     */
    public String getCorreo_pass() {
        return correo_pass;
    }

    /**
     * @param correo_pass the correo_pass to set
     */
    public void setCorreo_pass(String correo_pass) {
        this.correo_pass = correo_pass;
    }

    /**
     * @return the correo_host
     */
    public String getCorreo_host() {
        return correo_host;
    }

    /**
     * @param correo_host the correo_host to set
     */
    public void setCorreo_host(String correo_host) {
        this.correo_host = correo_host;
    }

    /**
     * @return the correo_port
     */
    public String getCorreo_port() {
        return correo_port;
    }

    /**
     * @param correo_port the correo_port to set
     */
    public void setCorreo_port(String correo_port) {
        this.correo_port = correo_port;
    }

    /**
     * @return the variable_active
     */
    public Boolean getVariable_active() {
        return variable_active;
    }

    /**
     * @param variable_active the variable_active to set
     */
    public void setVariable_active(Boolean variable_active) {
        this.variable_active = variable_active;
    }

}
