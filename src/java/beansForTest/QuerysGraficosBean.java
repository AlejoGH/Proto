/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beansForTest;

import constantes.ConstantesBean;
import entidades.areas.Areas;
import entidades.querys.facade.QuerysFacade;
import graficos.TemperaturaPromedioAreas;
import graficos.TemperaturaPromedioHoy;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.view.ViewScoped;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

/**
 *
 * @author PC1
 */
@ViewScoped
@ManagedBean(name = "QuerysGraficosBean")
public class QuerysGraficosBean implements Serializable {

    @ManagedProperty("#{constantesBean}")
    ConstantesBean cb;

    private List<TemperaturaPromedioHoy> temperaturaPromedioHoy;
    private List<TemperaturaPromedioAreas> temperaturaArea;
    private LineChartModel lineaTemperaturaPromedio;
    private LineChartModel lineaAreas;

    @EJB
    QuerysFacade querysFacade;

    @PostConstruct
    public void init() {
        temperaturaPromedioHoy = loadTemperaturaPromedioHoy();
        graficarLinea();
        graficarLineaAreas();
    }

    public List<TemperaturaPromedioHoy> loadTemperaturaPromedioHoy() {
        return querysFacade.runListQuery2(null);
    }

    public void graficarLinea() {
        lineaTemperaturaPromedio = new LineChartModel();
        ChartSeries serie = new LineChartSeries();
        for (int i = 0; i < temperaturaPromedioHoy.size(); i++) {

            serie.setLabel(temperaturaPromedioHoy.get(i).getFecha());
            serie.set(temperaturaPromedioHoy.get(i).getFecha(), temperaturaPromedioHoy.get(i).getPromedio());
            lineaTemperaturaPromedio.addSeries(serie);
        }
        lineaTemperaturaPromedio.setTitle("Promedio Temperatura general");
        //lineaTemperaturaPromedio.setLegendPosition("e");
        lineaTemperaturaPromedio.setShowPointLabels(true);
        lineaTemperaturaPromedio.getAxes().put(AxisType.X, new CategoryAxis("Fecha"));
        Axis xAxis = lineaTemperaturaPromedio.getAxis(AxisType.X);
        xAxis.setTickAngle(-30);
        Axis yAxis = lineaTemperaturaPromedio.getAxis(AxisType.Y);
        yAxis.setLabel("Temperatura");
        yAxis.setMin(34);
        yAxis.setMax(40);
    }

    public List<TemperaturaPromedioAreas> loadTemperaturaPromedioArea(int area_id) {
        HashMap hm = new HashMap();
        hm.put("area", area_id);
        return querysFacade.runListQuery4(hm);
    }

    public void graficarLineaAreas() {
        lineaAreas = new LineChartModel();
        for (Areas area : cb.getAreas()) {
            System.out.println(area.getArea_nombre());
            ChartSeries areach = new LineChartSeries();
            areach.setLabel(area.getArea_nombre());

            List<TemperaturaPromedioAreas> temperatura = loadTemperaturaPromedioArea(area.getArea_id());
            if (temperatura == null || temperatura.isEmpty()) {
                areach.set(0, 0);
            } else {
                for (TemperaturaPromedioAreas temperaturadt : temperatura) {
                    areach.set(temperaturadt.getFecha(), temperaturadt.getPromedio());
                }
            }
            lineaAreas.addSeries(areach);
        }
        lineaAreas.setTitle("Promedio temperatura por areas");
        lineaAreas.setLegendPosition("ne");
        lineaAreas.setShowPointLabels(false);
        lineaAreas.getAxes().put(AxisType.X, new CategoryAxis("Fecha"));
        lineaAreas.setAnimate(true);

        Axis xAxis = lineaAreas.getAxis(AxisType.X);
        xAxis.setTickAngle(-30);
        Axis yAxis = lineaAreas.getAxis(AxisType.Y);
        yAxis.setLabel("Temperatura");
        yAxis.setMin(33);
        yAxis.setMax(44);
    }

    public ConstantesBean getCb() {
        return cb;
    }

    public void setCb(ConstantesBean cb) {
        this.cb = cb;
    }

    public LineChartModel getLineaTemperaturaPromedio() {
        return lineaTemperaturaPromedio;
    }

    public void setLineaTemperaturaPromedio(LineChartModel lineaTemperaturaPromedio) {
        this.lineaTemperaturaPromedio = lineaTemperaturaPromedio;
    }

    public LineChartModel getLineaAreas() {
        return lineaAreas;
    }

    public void setLineaAreas(LineChartModel lineaAreas) {
        this.lineaAreas = lineaAreas;
    }

    public List<TemperaturaPromedioAreas> getTemperaturaArea() {
        return temperaturaArea;
    }

    public void setTemperaturaArea(List<TemperaturaPromedioAreas> temperaturaArea) {
        this.temperaturaArea = temperaturaArea;
    }

    public List<TemperaturaPromedioHoy> getTemperaturaPromedioHoy() {
        return temperaturaPromedioHoy;
    }

    public void setTemperaturaPromedioHoy(List<TemperaturaPromedioHoy> temperaturaPromedioHoy) {
        this.temperaturaPromedioHoy = temperaturaPromedioHoy;
    }
}
