/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beansForTest;

import entidades.home_people.HomePeople;
import entidades.internal_contact.InternalContact;
import entidades.internal_contact.InternalContactFadace;
import entidades.users.Users;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

/**
 *
 * @author ncabrejo
 */

@ManagedBean(name = "InternalContactBean")
@ViewScoped
public class InternalContactBean implements Serializable {

    @EJB
    InternalContactFadace internalContactFadace;

    private Integer home_people_id;

    public void saveUpdate(InternalContact internalContact, Users users) {

        if (home_people_id != null) {
            HomePeople aux = null;
            for (HomePeople hp : users.getHomePeoples()) {
                if (Objects.equals(home_people_id, hp.getHome_people_id())) {
                    aux = hp;
                }
            }
            
            internalContact.setInternal_contact_home_people_id(aux);

            if (internalContactFadace.updateInternalContact(internalContact)) {
                util.Utilidades.imprimir_msg("Hecho!", "Registro actualizado");
            } else {
                util.Utilidades.imprimir_msg("Error", "No se pudo crear el registro");
            }
        }
    }

    public void addNewInternalContact(Users users) {
        if (users.getInternalContacts() == null) {
            users.setInternalContacts(new ArrayList<>());
        }
        InternalContact newInternalContact = new InternalContact();
        newInternalContact.setInternal_contact_user_id(users);
        newInternalContact.setInternal_contact_date(new Date());
        newInternalContact.setInternal_contact_fever(36.0);
        if (internalContactFadace.SaveInternalContact(newInternalContact)) {
            users.getInternalContacts().add(newInternalContact);
        } else {
            util.Utilidades.imprimir_msg("Error", "No se pudo crear el registro");
        }
    }

    public void deleteInternalContact(InternalContact internalContact, Users users) {
        if (internalContactFadace.DeleteInternalContact(internalContact)) {
            users.getInternalContacts().remove(internalContact);
            util.Utilidades.imprimir_msg("Hecho!", "Registro eliminado");
        } else {
            util.Utilidades.imprimir_msg("Error", "No se pudo eliminar el registro");
        }
    }

    /**
     * @return the home_people_id
     */
    public Integer getHome_people_id() {
        return home_people_id;
    }

    /**
     * @param home_people_id the home_people_id to set
     */
    public void setHome_people_id(Integer home_people_id) {
        this.home_people_id = home_people_id;
    }
}
