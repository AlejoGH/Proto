/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.querys.facade;

import DAO.EntityManager;
import entidades.querys.notResolvetoday.NotResolveToday;
import graficos.TemperaturaPromedioAreas;
import graficos.TemperaturaPromedioHoy;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import org.hibernate.transform.Transformers;

/**
 *
 * @author ncabrejo
 */
@Stateless
public class QuerysFacade {

    private final EntityManager em = EntityManager.getInstance("/hibernate.cfg.xml");

    public List<NotResolveToday> runListQuery(HashMap hm, String sql) {
        try {
            return (List<NotResolveToday>) em.selectNativoSql(hm, sql, Transformers.aliasToBean(NotResolveToday.class));
        } catch (Exception ex) {
            Logger.getLogger(QuerysFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public List<TemperaturaPromedioHoy> runListQuery2(HashMap hm) {
        try {
            String sql = "SELECT TO_CHAR(dr.daily_report_date, 'yyyy-mm-dd') AS fecha, AVG(dr.daily_report_user_fever) AS promedio "
                + "FROM DailyReport dr "
                + "GROUP BY TO_CHAR(dr.daily_report_date, 'yyyy-mm-dd')"
                + "ORDER BY TO_CHAR(dr.daily_report_date, 'yyyy-mm-dd')";
            return (List<TemperaturaPromedioHoy>) em.selectNativoSql(hm, sql, Transformers.aliasToBean(TemperaturaPromedioHoy.class));
        } catch (Exception ex) {
            Logger.getLogger(QuerysFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public List<TemperaturaPromedioAreas> runListQuery4(HashMap hm) {
        try {
            String sql = "SELECT TO_CHAR(dr.daily_report_date, 'yyyy-mm-dd') AS fecha, AVG(dr.daily_report_user_fever) AS promedio "
                + "FROM DailyReport dr, Users us, Areas ar "
                + "WHERE dr.daily_report_user_id=us.user_id AND us.area_id = ar.area_id AND ar.area_id = :area "
                + "GROUP BY TO_CHAR(dr.daily_report_date, 'yyyy-mm-dd')"
                + "ORDER BY TO_CHAR(dr.daily_report_date, 'yyyy-mm-dd')";
            return (List<TemperaturaPromedioAreas>) em.selectNativoSql(hm, sql, Transformers.aliasToBean(TemperaturaPromedioAreas.class));
        } catch (Exception ex) {
            Logger.getLogger(QuerysFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
