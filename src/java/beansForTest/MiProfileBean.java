/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beansForTest;

import entidades.home_people.HomePeopleFacade;
import entidades.users.UserFacade;
import entidades.users.Users;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

/**
 *
 * @author ncabrejo
 */
@ViewScoped
@ManagedBean(name = "miProfileBean")
public class MiProfileBean implements Serializable {

    @EJB
    UserFacade userFacade;

    @EJB
    HomePeopleFacade homePeopleFacade;

    public void guardarInfor(Users user) {

        if (userFacade.saveUser(user)) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Successful", "Datos Persoales Guardados"));
            //Utilidades.imprimir_msg("Bien!", "Datos Guardados");
        } else {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Error", "Datos No Guardados"));
            //Utilidades.imprimir_msg("Error!", "Inconsistencia al guardar");
        }

    }

}
