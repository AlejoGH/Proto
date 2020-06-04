/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contentcontroller;

import entidades.preocupational_test.PreocupationalFacade;
import entidades.users.UserBean;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author ncabrejo
 */
@ViewScoped
@ManagedBean(name = "contentController")
public class ContentController implements Serializable {

    private Integer activePage;
    private boolean isPreoupationalReady;

    @EJB
    PreocupationalFacade preocupationalFacade;

    @ManagedProperty("#{UserBean}")
    private UserBean userBean;

    @PostConstruct
    public void init() {
        activePage = 1;
        isPreoupationalReady = preocupationalFacade.PreocupationalDone(userBean.getCurrentUser());
    }

    public void changeActivePage(Integer val) {
        activePage = val;
    }

    /**
     * @return the activePage
     */
    public Integer getActivePage() {
        return activePage;
    }

    /**
     * @param activePage the activePage to set
     */
    public void setActivePage(Integer activePage) {
        this.activePage = activePage;
    }

    /**
     * @return the isPreoupationalReady
     */
    public boolean isIsPreoupationalReady() {
        return isPreoupationalReady;
    }

    /**
     * @param isPreoupationalReady the isPreoupationalReady to set
     */
    public void setIsPreoupationalReady(boolean isPreoupationalReady) {
        this.isPreoupationalReady = isPreoupationalReady;
    }

    /**
     * @return the userBean
     */
    public UserBean getUserBean() {
        return userBean;
    }

    /**
     * @param userBean the userBean to set
     */
    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }

}
