/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beansForTest;

import constantes.ConstantesBean;
import entidades.areas.Areas;
import entidades.rol.Rol;
import entidades.users.UserFacade;
import entidades.users.Users;
import java.io.Serializable;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author ncabrejo
 */
@ViewScoped

@ManagedBean(name = "usersManagementBean")
public class UsersManagementBean implements Serializable {

    @ManagedProperty("#{constantesBean}")
    private ConstantesBean constantesBean;

    @EJB
    UserFacade userFacade;

    private Users newUser;
    private Integer rol_id;
    private Integer area_id;

    @PostConstruct
    public void init() {
        setNewUser(new Users());
        if (constantesBean.getRoles() != null && !constantesBean.getRoles().isEmpty()) {
            rol_id = constantesBean.getRoles().get(0).getRol_id();
        }
        if (constantesBean.getAreas() != null && !constantesBean.getAreas().isEmpty()) {
            area_id = constantesBean.getAreas().get(0).getArea_id();
        }
    }

    public void crearUsuario() {
        for (Areas area : constantesBean.getAreas()) {
            if (Objects.equals(area_id, area.getArea_id())) {
                newUser.setArea_id(area);
            }
        }

        for (Rol rol : constantesBean.getRoles()) {
            if (Objects.equals(rol_id, rol.getRol_id())) {
                newUser.setRol_id(rol);
            }
        }

        newUser.setPassword(BCrypt.hashpw(newUser.getCc(), BCrypt.gensalt()));

        if (userFacade.SaveNewUser(newUser)) {
            util.Utilidades.imprimir_msg("Hecho!", "Usuario creado");
        } else {
            util.Utilidades.imprimir_msg("Error!", "Usuario No creado");
        }

        newUser = new Users();
    }

    /**
     * @return the newUser
     */
    public Users getNewUser() {
        return newUser;
    }

    /**
     * @param newUser the newUser to set
     */
    public void setNewUser(Users newUser) {
        this.newUser = newUser;
    }

    /**
     * @return the rol_id
     */
    public Integer getRol_id() {
        return rol_id;
    }

    /**
     * @param rol_id the rol_id to set
     */
    public void setRol_id(Integer rol_id) {
        this.rol_id = rol_id;
    }

    /**
     * @return the area_id
     */
    public Integer getArea_id() {
        return area_id;
    }

    /**
     * @param area_id the area_id to set
     */
    public void setArea_id(Integer area_id) {
        this.area_id = area_id;
    }

    /**
     * @return the constantesBean
     */
    public ConstantesBean getConstantesBean() {
        return constantesBean;
    }

    /**
     * @param constantesBean the constantesBean to set
     */
    public void setConstantesBean(ConstantesBean constantesBean) {
        this.constantesBean = constantesBean;
    }

}
