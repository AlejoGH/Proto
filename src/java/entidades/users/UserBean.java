/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.users;

import entidades.preocupational_test.PreocupationalTest;
import facade.LoginFacade;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author ncabrejo
 */
@SessionScoped
@ManagedBean(name = "UserBean")
public class UserBean implements Serializable {

    private static final Logger LOGGER1 = LoggerFactory.getLogger(UserBean.class);//Impresion en la consola
    public static final String HOME_PAGE_REDIRECT1 = "/secured/principal.xhtml?faces-redirect=true";//Ruta pagina de inicio de formatos
    public static final String LOGOUT_PAGE_REDIRECT1 = "/index.xhtml?faces-redirect=true";//Ruta página de Login
    private Users currentUser = null;// Variable que almacena los datos del usuario que  inicia sesion

    @EJB
    LoginFacade loginFacade;

    private String userNick;//Id del usuario que va a iniciar sesion
    private String userPassword;// Contrasenia del usuario que inicia sesion 
    private String userPasswordNew;// Nueva contrasenia del usuario que inicia sesion
    private String userPasswordSecond; //Duplicado de nueva contrasenia  para validacion

    //Metodo que valida los datos de un usuario y lo redirige en caso de ser exitosa la validacion de los datos
    public String loginpRO() {
        currentUser = (find(userNick, userPassword));//Usuario logueado
        if (currentUser != null) {
            LOGGER1.info("Bienvenido '{}'", userNick);
            // Que hace referencia a los conratos en ejecucion y se  injecta en el contexto web el valor
            return "/secured/principal.xhtml";// Redireccion
        } else {
            LOGGER1.info("Credenciales inválidas '{}'", userNick);

            util.Utilidades.imprimir_msg_advertencia("Credenciales inválidas",
                    "Usuario o contraseña incorrectos");
            return null;
        }
    }

    public PreocupationalTest obtenerPreo() {
        if (currentUser.getPreocupationalTests() != null && !currentUser.getPreocupationalTests().isEmpty()) {
            return currentUser.getPreocupationalTests().get(0);
        }
        return new PreocupationalTest();
    }

    //Metodo que valida que la clave ingresada corresponda a la clave del usuario
    private Users find(String userId, String password) {
        Users usuarioLogin;
        usuarioLogin = loginFacade.getUserByNick(userId);//Se consulta la base de datos para obtener el usuario    
        if (usuarioLogin != null) {
            if (usuarioLogin.getPassword() != null && !BCrypt.checkpw(password, usuarioLogin.getPassword())) {
                return null;//Si la constrasenia no es igual se retorna  un  nulo
            }
        }
        return usuarioLogin;// Si la contrasenia es correcta se retorna el usuario correspondiente
    }

    //Metodo que valida si la sesion de un usuario está abierta
    public boolean isLoggedIn() {
        return currentUser != null;
    }

    //Metodo que redirige a pagina  de inicio si el usuario sigue activo,
    //Evita que al cerrar las pestanias el usuario tenga que volver a iniciar sesion
    public String isLoggedInForwardHome() {
        if (isLoggedIn()) {
            return HOME_PAGE_REDIRECT1;
        }
        return null;
    }

    //Metodo que invalida la sesion de hibernate y cierra conexiones abiertas 
    public String logout() {
        String identifier = userNick;
        LOGGER1.debug("Sesión invalidada para '{}'", identifier);
        try {
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();

        } catch (NullPointerException e) {
            System.out.println("Nulo - USerManager Logout");
        } catch (Exception e) {
            System.out.println("error - USerManager Logout");
        }

        LOGGER1.info("Sesión terminada para '{}'", identifier);
        // lf.cerrarSesiones();
        return LOGOUT_PAGE_REDIRECT1;// Redirige a pagina de loguep
    }

    public void prueba(Integer val) {
        System.out.println(val);
    }

    /**
     * @return the currentUser
     */
    public Users getCurrentUser() {
        return currentUser;
    }

    /**
     * @param currentUser the currentUser to set
     */
    public void setCurrentUser(Users currentUser) {
        this.currentUser = currentUser;
    }

    /**
     * @return the userNick
     */
    public String getUserNick() {
        return userNick;
    }

    /**
     * @param userNick the userNick to set
     */
    public void setUserNick(String userNick) {
        this.userNick = userNick;
    }

    /**
     * @return the userPassword
     */
    public String getUserPassword() {
        return userPassword;
    }

    /**
     * @param userPassword the userPassword to set
     */
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    /**
     * @return the userPasswordNew
     */
    public String getUserPasswordNew() {
        return userPasswordNew;
    }

    /**
     * @param userPasswordNew the userPasswordNew to set
     */
    public void setUserPasswordNew(String userPasswordNew) {
        this.userPasswordNew = userPasswordNew;
    }

    /**
     * @return the userPasswordSecond
     */
    public String getUserPasswordSecond() {
        return userPasswordSecond;
    }

    /**
     * @param userPasswordSecond the userPasswordSecond to set
     */
    public void setUserPasswordSecond(String userPasswordSecond) {
        this.userPasswordSecond = userPasswordSecond;
    }

}
