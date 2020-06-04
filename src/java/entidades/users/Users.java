/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.users;

import entidades.areas.Areas;
import entidades.daily_report.DailyReport;
import entidades.external_contact.ExternalContact;
import entidades.home_people.HomePeople;
import entidades.internal_contact.InternalContact;
import entidades.preocupational_test.PreocupationalTest;
import entidades.rol.Rol;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author ncabrejo
 */
@Entity
@Table(name = "users", schema = "public")
@NamedQueries({
    @NamedQuery(name = "Users.getUserByUser", query = "SELECT c FROM Users c WHERE c.nickname = :user")
    ,
@NamedQuery(name = "getIdU", query = "SELECT CASE WHEN MAX(c.user_id) IS NULL THEN 1 ELSE (MAX(c.user_id) + 1) END FROM Users c")}
)
public class Users implements Serializable {

    @Id
    private Integer user_id;
    private String name;
    private String position;
    private String adress;
    private String number;
    private String nickname;
    private String password;
    private String cc;
    private String mail;

    @ManyToOne
    @JoinColumn(name = "rol_id")
    private Rol rol_id;
    @ManyToOne
    @JoinColumn(name = "area_id")
    private Areas area_id;

    @OneToMany(mappedBy = "daily_report_user_id", fetch = FetchType.EAGER)
    private List<DailyReport> dailyReports;
    @OneToMany(mappedBy = "external_contact_user_id", fetch = FetchType.EAGER)
    private List<ExternalContact> externalContacts;
    @OneToMany(mappedBy = "home_people_user_id", fetch = FetchType.EAGER)
    private List<HomePeople> homePeoples;
    @OneToMany(mappedBy = "internal_contact_user_id", fetch = FetchType.EAGER)
    private List<InternalContact> internalContacts;
    @OneToMany(mappedBy = "preocupational_test_user", fetch = FetchType.EAGER)
    private List<PreocupationalTest> preocupationalTests;

    public Users() {

    }

    /**
     * @return the user_id
     */
    public Integer getUser_id() {
        return user_id;
    }

    /**
     * @return the nickname
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * @param nickname the nickname to set
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @param user_id the user_id to set
     */
    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the position
     */
    public String getPosition() {
        return position;
    }

    /**
     * @param position the position to set
     */
    public void setPosition(String position) {
        this.position = position;
    }

   

    /**
     * @return the adress
     */
    public String getAdress() {
        return adress;
    }

    /**
     * @param adress the adress to set
     */
    public void setAdress(String adress) {
        this.adress = adress;
    }

    /**
     * @return the number
     */
    public String getNumber() {
        return number;
    }

    /**
     * @param number the number to set
     */
    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * @return the area_id
     */
    public Areas getArea_id() {
        return area_id;
    }

    /**
     * @param area_id the area_id to set
     */
    public void setArea_id(Areas area_id) {
        this.area_id = area_id;
    }

    /**
     * @return the rol_id
     */
    public Rol getRol_id() {
        return rol_id;
    }

    /**
     * @param rol_id the rol_id to set
     */
    public void setRol_id(Rol rol_id) {
        this.rol_id = rol_id;
    }

    /**
     * @return the dailyReports
     */
    public List<DailyReport> getDailyReports() {
        return dailyReports;
    }

    /**
     * @param dailyReports the dailyReports to set
     */
    public void setDailyReports(List<DailyReport> dailyReports) {
        this.dailyReports = dailyReports;
    }

    /**
     * @return the externalContacts
     */
    public List<ExternalContact> getExternalContacts() {
        return externalContacts;
    }

    /**
     * @param externalContacts the externalContacts to set
     */
    public void setExternalContacts(List<ExternalContact> externalContacts) {
        this.externalContacts = externalContacts;
    }

    /**
     * @return the homePeoples
     */
    public List<HomePeople> getHomePeoples() {
        return homePeoples;
    }

    /**
     * @param homePeoples the homePeoples to set
     */
    public void setHomePeoples(List<HomePeople> homePeoples) {
        this.homePeoples = homePeoples;
    }

    /**
     * @return the internalContacts
     */
    public List<InternalContact> getInternalContacts() {
        return internalContacts;
    }

    /**
     * @param internalContacts the internalContacts to set
     */
    public void setInternalContacts(List<InternalContact> internalContacts) {
        this.internalContacts = internalContacts;
    }

    /**
     * @return the preocupationalTests
     */
    public List<PreocupationalTest> getPreocupationalTests() {
        return preocupationalTests;
    }

    /**
     * @param preocupationalTests the preocupationalTests to set
     */
    public void setPreocupationalTests(List<PreocupationalTest> preocupationalTests) {
        this.preocupationalTests = preocupationalTests;
    }

    /**
     * @return the cc
     */
    public String getCc() {
        return cc;
    }

    /**
     * @param cc the cc to set
     */
    public void setCc(String cc) {
        this.cc = cc;
    }

    /**
     * @return the mail
     */
    public String getMail() {
        return mail;
    }

    /**
     * @param mail the mail to set
     */
    public void setMail(String mail) {
        this.mail = mail;
    }

}
