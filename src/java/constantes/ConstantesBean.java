package constantes;

import entidades.areas.AreaFacade;
import entidades.areas.Areas;
import entidades.rol.Rol;
import entidades.rol.RolFacade;
import java.io.Serializable;
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
