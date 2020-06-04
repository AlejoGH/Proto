/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beansForTest;

import entidades.querys.facade.QuerysFacade;
import entidades.querys.notResolvetoday.NotResolveToday;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import mailService.CorreoService;

/**
 *
 * @author ncabrejo
 */
@ViewScoped
@ManagedBean(name = "QuerysBean")
public class QuerysBean implements Serializable {

    private List<NotResolveToday> notResolveTodays;

    private List<NotResolveToday> notResolveFirstForm;

    @EJB
    QuerysFacade querysFacade;

    @EJB
    CorreoService correoService;

    @PostConstruct
    public void init() {
        notResolveTodays = loadNotResolveTodays();
        notResolveFirstForm = loadNotResolveFirstForm();
    }

    public List<NotResolveToday> loadNotResolveTodays() {

        HashMap hm = new HashMap();
        Date today = new Date();
        hm.put("date", today.getYear() + 1900 + "-" + today.getMonth() + 1 + "-" + today.getDate());
        String query = "SELECT c.user_id as user_id, c.name as name, c.mail as correo FROM Users c WHERE c.user_id "
                + "NOT IN (SELECT b.daily_report_user_id FROM DailyReport b WHERE DATE(b.daily_report_date) = current_date())";
        return querysFacade.runListQuery(null, query);

    }

    public List<NotResolveToday> loadNotResolveFirstForm() {

        String query = "SELECT c.user_id as user_id, c.name as name, c.mail as correo FROM Users c WHERE c.user_id "
                + "NOT IN (SELECT b.preocupational_test_user FROM PreocupationalTest b)";
        return querysFacade.runListQuery(null, query);

    }

    public void enviarCorreoResolveFirstForm() {
        List<String> correos = new ArrayList<>();
        for (NotResolveToday aux : notResolveFirstForm) {
            correos.add(aux.getCorreo());
        }
        String msg = "";
        correoService.enviarNotificacion(correos, "Aviso Encuesta", msg);
    }

    public void enviarCorreoNotResolveTodays() {
        List<String> correos = new ArrayList<>();
        for (NotResolveToday aux : notResolveTodays) {
            correos.add(aux.getCorreo());
        }
        String msg = "";
        correoService.enviarNotificacion(correos, "Aviso Encuesta", msg);
    }

    /**
     * @return the notResolveTodays
     */
    public List<NotResolveToday> getNotResolveTodays() {
        return notResolveTodays;
    }

    /**
     * @param notResolveTodays the notResolveTodays to set
     */
    public void setNotResolveTodays(List<NotResolveToday> notResolveTodays) {
        this.notResolveTodays = notResolveTodays;
    }

    /**
     * @return the notResolveFirstForm
     */
    public List<NotResolveToday> getNotResolveFirstForm() {
        return notResolveFirstForm;
    }

    /**
     * @param notResolveFirstForm the notResolveFirstForm to set
     */
    public void setNotResolveFirstForm(List<NotResolveToday> notResolveFirstForm) {
        this.notResolveFirstForm = notResolveFirstForm;
    }

}
