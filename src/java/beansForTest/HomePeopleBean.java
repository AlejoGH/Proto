/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beansForTest;

import entidades.home_people.HomePeople;
import entidades.home_people.HomePeopleFacade;
import entidades.users.Users;
import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author ncabrejo
 */
@ManagedBean(name = "HomePeopleBean")
@ViewScoped
public class HomePeopleBean implements Serializable {

    @EJB
    HomePeopleFacade homePeopleFacade;

    @PostConstruct
    public void init() {
    }

    public void saveUpdate(HomePeople homePeople) {
        if (homePeopleFacade.updateHomePeople(homePeople)) {
            util.Utilidades.imprimir_msg("Hecho!", "Registro actualizado");
        } else {
            util.Utilidades.imprimir_msg("Error", "No se pudo crear el registro");
        }
    }

    public void addNewFamiliar(Users users) {
        if (users.getHomePeoples() == null) {
            users.setHomePeoples(new ArrayList<>());
        }
        HomePeople newFamiliar = new HomePeople();
        newFamiliar.setHome_people_user_id(users);
        if (homePeopleFacade.SaveHomePeople(newFamiliar)) {
            users.getHomePeoples().add(newFamiliar);
        } else {
            util.Utilidades.imprimir_msg("Error", "No se pudo crear el registro");
        }
    }

    public void deleteFamiliar(HomePeople homePeople, Users users) {
        if (homePeopleFacade.DeleteHomePeople(homePeople)) {
            users.getHomePeoples().remove(homePeople);
            util.Utilidades.imprimir_msg("Hecho!", "Registro eliminado");
        } else {
            util.Utilidades.imprimir_msg("Error", "No se pudo eliminar el registro");
        }
    }

}
