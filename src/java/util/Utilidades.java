package util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class Utilidades {

    public static void imprimir_msg(String top, String msg) {
//        PrimeFaces reqcontext = PrimeFaces.current();
//        reqcontext.dialog().showMessageDynamic(new FacesMessage(top, msg));
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(top,msg));
    }

}
