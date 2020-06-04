/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beansForTest;

import entidades.daily_report.DailyReport;
import entidades.daily_report.DailyRepotFacade;
import entidades.users.Users;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

/**
 *
 * @author ncabrejo
 */
@ViewScoped
@ManagedBean(name = "dailyReportBean")
public class DailyReportBean implements Serializable{

    @EJB
    DailyRepotFacade dailyRepotFacade;

    public void saveUpdate(DailyReport dailyReport) {
        if (dailyRepotFacade.updateDailyReport(dailyReport)) {
            util.Utilidades.imprimir_msg("Hecho!", "Registro actualizado");
        } else {
            util.Utilidades.imprimir_msg("Error", "No se pudo crear el registro");
        }
    }

    public void addNewDailyReport(Users users) {
        if (users.getDailyReports() == null) {
            users.setDailyReports(new ArrayList<>());
        }
        DailyReport newDailyReport = new DailyReport();
        newDailyReport.setDaily_report_date(new Date());
        System.out.println("hour:" +newDailyReport.getDaily_report_date().getHours() );
        newDailyReport.setDaily_report_user_id(users);
        newDailyReport.setDaily_report_user_fever(36.0);
        if (dailyRepotFacade.SaveDailyReport(newDailyReport)) {
            users.getDailyReports().add(newDailyReport);
        } else {
            util.Utilidades.imprimir_msg("Error", "No se pudo crear el registro");
        }
    }

    public void deleteDailyReport(DailyReport dailyReport, Users users) {
        if (dailyRepotFacade.DeleteDailyReport(dailyReport)) {
            users.getDailyReports().remove(dailyReport);
            util.Utilidades.imprimir_msg("Hecho!", "Registro eliminado");
        } else {
            util.Utilidades.imprimir_msg("Error", "No se pudo eliminar el registro");
        }
    }

}
