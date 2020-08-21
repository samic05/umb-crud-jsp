package com.umb.santiago.galvis.crud.services;

import com.umb.santiago.galvis.crud.dao.instance.EmpleadoDao;
import com.umb.santiago.galvis.crud.dao.interfaces.Crud;
import com.umb.santiago.galvis.crud.modelo.Empleado;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
@Slf4j
public class EmpleadosService {

    Crud<Empleado> dao = new EmpleadoDao();

    public List<Empleado> getEmpleados(){
        return  dao.list();
    }

    public void insertNewEmpleado(HttpServletRequest req){
        Empleado empleado = Empleado.builder()
                .docType(req.getParameter("docType"))
                .docNumber((!StringUtils.isAllBlank((req.getParameter("docNum")))?req.getParameter("docNum"):""))
                .name((!StringUtils.isAllBlank((req.getParameter("name")))?req.getParameter("name"):""))
                .lastName((!StringUtils.isAllBlank((req.getParameter("lastName")))?req.getParameter("lastName"):""))
                .department((!StringUtils.isAllBlank((req.getParameter("department")))?req.getParameter("department"):""))
                .build();
        boolean result = dao.add(empleado);
        if(result){
            log.info("Se ha guardado el nuevo empleado");
        }else{
            log.error("Ha ocurrido un problema guardando el empleado");
        }
    }

    public void editEmployee(HttpServletRequest req){
        Empleado empleado = Empleado.builder()
                .id((!StringUtils.isAllBlank((req.getParameter("id")))?Integer.parseInt(req.getParameter("id")):0))
                .docType(req.getParameter("docType"))
                .docNumber((!StringUtils.isAllBlank((req.getParameter("docNum")))?req.getParameter("docNum"):""))
                .name((!StringUtils.isAllBlank((req.getParameter("name")))?req.getParameter("name"):""))
                .lastName((!StringUtils.isAllBlank((req.getParameter("lastName")))?req.getParameter("lastName"):""))
                .department((!StringUtils.isAllBlank((req.getParameter("department")))?req.getParameter("department"):""))
                .build();
        boolean result = dao.edit(empleado);
        if(result){
            log.info("Se ha modificado elempleado");
        }else{
            log.error("Ha ocurrido un problema modificando el empleado");
        }
    }

    public void editAnEmployeeView(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        int id = Integer.parseInt(req.getParameter("id"));
        Empleado empleado = dao.findById(id);
        if(empleado.getId()==0){
            throw new Exception("Error al procesar el usuario.");
        }else {
            req.setAttribute("employeeToEdit",empleado);
        }
    }

    public void deleteEmployee(HttpServletRequest req){
        int id = Integer.parseInt(req.getParameter("id"));
        Empleado empleado = dao.findById(id);
        if(empleado.getId()==0){
            log.error("Error al eliminar usuario");
        }else {
            boolean result = dao.delete(id);
            if(result) {
                log.warn(String.format("Eliminando usuario: %s con id: %d", empleado.getName(), empleado.getId()));
            }else {
                log.error("Error al eliminar usuario");
            }
        }

    }
}
