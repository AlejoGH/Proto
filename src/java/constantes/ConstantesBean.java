package constantes;

import entidades.areas.AreaFacade;
import entidades.areas.Areas;
import entidades.rol.Rol;
import entidades.rol.RolFacade;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ApplicationScoped

@ManagedBean(name = "constantesBean")
public class ConstantesBean implements Serializable {

    private List<Rol> roles;
    private List<Areas> areas;

    @EJB
    RolFacade rolFacade;

    @EJB
    AreaFacade areaFacade;

    @PostConstruct
    public void init() {
        System.out.println("Cargando Constantes");
        roles = rolFacade.getRoles();
        areas = areaFacade.getAreas();
    }

    public boolean isToday(Date date) {
        Date aux = new Date();
        if (date != null) {
            if (date.getYear() == aux.getYear()
                    && date.getMonth() == aux.getMonth()
                    && date.getDate() == aux.getDate()) {
                return true;
            }
            return false;
        } 
        return true;
    }

    /**
     * @return the roles
     */
    public List<Rol> getRoles() {
        return roles;
    }

    /**
     * @param roles the roles to set
     */
    public void setRoles(List<Rol> roles) {
        this.roles = roles;
    }

    /**
     * @return the areas
     */
    public List<Areas> getAreas() {
        return areas;
    }

    /**
     * @param areas the areas to set
     */
    public void setAreas(List<Areas> areas) {
        this.areas = areas;
    }

}
