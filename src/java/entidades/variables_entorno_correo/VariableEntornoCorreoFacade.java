/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.variables_entorno_correo;

import DAO.EntityManager;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;

/**
 *
 * @author linuxserver
 */
@Stateless
public class VariableEntornoCorreoFacade {

    EntityManager em = EntityManager.getInstance("hibernate.cfg.xml");

    public VariableEntornoCorreo getActive() {
        try {
            VariableEntornoCorreo retorno = null;
            
            List<VariableEntornoCorreo> aux= (List<VariableEntornoCorreo>) em.selectNameQueryParamList(null, "VariableEntornoCorreo.getActive");
            if(aux != null && !aux.isEmpty()){
                retorno = aux.get(0);
            }
            return retorno;
        } catch (Exception ex) {
            Logger.getLogger(VariableEntornoCorreoFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public Integer getNewId() {
        try {
            return (Integer) em.selectNameQueryUniqueResult("VariableEntornoCorreo.getId");
        } catch (Exception ex) {
            Logger.getLogger(VariableEntornoCorreoFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public boolean crearVariableEntornoCorreo(VariableEntornoCorreo nuevo) {
        nuevo.setVariable_id(getNewId());
        try {
            return em.save2(nuevo, "VariableEntornoCorreo");
        } catch (Exception ex) {
            Logger.getLogger(VariableEntornoCorreoFacade.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean guardarVariableEntornoCorreo(VariableEntornoCorreo nuevo) {
        try {
            return em.save2(nuevo, "VariableEntornoCorreo");
        } catch (Exception ex) {
            Logger.getLogger(VariableEntornoCorreoFacade.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

}
