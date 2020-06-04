/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beansForTest;

import entidades.external_contact.ExternalContact;
import entidades.external_contact.ExternalContactFacade;
import entidades.users.Users;
import java.io.Serializable;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

/**
 *
 * @author ncabrejo
 */
@ManagedBean(name = "ExtrenalContactBean")
@ViewScoped
public class ExtrenalContactBean implements Serializable {

    @EJB
    ExternalContactFacade externalContactFacade;

    public void saveUpdate(ExternalContact externalContact) {
        if (externalContactFacade.updateExternalContact(externalContact)) {
            util.Utilidades.imprimir_msg("Hecho!", "Registro actualizado");
        } else {
            util.Utilidades.imprimir_msg("Error", "No se pudo crear el registro");
        }
    }

    public void addNewExternalContact(Users users) {
        if (users.getExternalContacts() == null) {
            users.setExternalContacts(new ArrayList<>());
        }
        ExternalContact newExternalContact = new ExternalContact();
        newExternalContact.setExternal_contact_user_id(users);
        if (externalContactFacade.SaveExternalContact(newExternalContact)) {
            users.getExternalContacts().add(newExternalContact);
        } else {
            util.Utilidades.imprimir_msg("Error", "No se pudo crear el registro");
        }
    }

    public void deleteExternalContact(ExternalContact externalContact, Users users) {
        if (externalContactFacade.DeleteExternalContact(externalContact)) {
            users.getExternalContacts().remove(externalContact);
            util.Utilidades.imprimir_msg("Hecho!", "Registro eliminado");
        } else {
            util.Utilidades.imprimir_msg("Error", "No se pudo eliminar el registro");
        }
    }

}
