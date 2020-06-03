/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.preocupational_test;

import DAO.EntityManager;
import entidades.users.Users;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;

/**
 *
 * @author ncabrejo
 */
@Stateless
public class PreocupationalFacade {

    private final EntityManager em = EntityManager.getInstance("/hibernate.cfg.xml");

    public boolean savePreocupational(PreocupationalTest preocupationalTest) {
        try {
            Integer id = (Integer) em.selectNameQueryUniqueResult("getIdPT");
            try {
                preocupationalTest.setPreocupational_test_id(id);
                return em.save2(preocupationalTest, "PreocupationalTest");
            } catch (Exception ex) {
                Logger.getLogger(PreocupationalFacade.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        } catch (Exception ex) {
            Logger.getLogger(PreocupationalFacade.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean PreocupationalDone(Users user) {
        try {
            HashMap hm = new HashMap();
            hm.put("user", user);
            List<PreocupationalTest> test = (List<PreocupationalTest>) em.selectNameQueryParamList(hm, "getListPTByUser");
            if(test == null)
                return false;
            return !test.isEmpty();
        } catch (Exception ex) {
            Logger.getLogger(PreocupationalFacade.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }
}
