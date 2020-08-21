package com.umb.santiago.galvis.crud.controlador;

import com.umb.santiago.galvis.crud.services.EmpleadosService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Slf4j
@WebServlet(name = "MainController", urlPatterns = {"/crud-app"})
public class EmpleadosController extends HttpServlet {

    public static final String LISTAR = "list";
    public static final String VISTA_LISTAR = "vistas/listar.jsp";
    public static final String VISTA_AÑADIR = "vistas/añadir.jsp";
    public static final String VISTA_ERROR = "vistas/error.jsp";
    public static final String VISTA_EDITAR = "vistas/editar.jsp";
    public static final String ADD = "add";
    public static final String ADD_NEW_EMPLOYEE = "addNew";
    public static final String EDIT_EMPLOYEE = "editView";
    public static final String EDIT_EXISTING_ONE = "editExistingOne";
    public static final String DELETE = "delete";


    EmpleadosService service = new EmpleadosService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("Recibiendo nuevo request.");
        String destination;
        String action = req.getParameter("action");
        if (StringUtils.isNoneBlank(action)) {
            switch (action) {
                case LISTAR:
                    log.info("Listar empleados.");
                    destination = VISTA_LISTAR;
                    break;
                case ADD:
                    log.info("Añadir empleados.");
                    destination = VISTA_AÑADIR;
                    break;
                case ADD_NEW_EMPLOYEE:
                    log.info("Añadiendo nuevo empleado.");
                    service.insertNewEmpleado(req);
                    destination = VISTA_LISTAR;
                    break;
                case EDIT_EMPLOYEE:
                    log.info("Editar usuario existente.");
                    try {
                        service.editAnEmployeeView(req, resp);
                        destination = VISTA_EDITAR;
                    } catch (Exception e) {
                        req.setAttribute("ErrorFindingEmployee", Boolean.TRUE);
                        destination = VISTA_LISTAR;
                    }
                    break;
                case EDIT_EXISTING_ONE:
                    log.info("Editando usuario existente.");
                    service.editEmployee(req);
                    destination = VISTA_LISTAR;
                    break;
                case DELETE:
                    log.warn("Eliminando usuario existente.");
                    service.deleteEmployee(req);
                    destination = VISTA_LISTAR;
                    break;
                default:
                    log.error("No se encuentra accion deseada.");
                    destination = VISTA_ERROR;
            }
            req.getRequestDispatcher(destination).forward(req, resp);
        }else{
            destination = "/index.jsp";
            req.getRequestDispatcher(destination).forward(req,resp);
        }

    }
}
