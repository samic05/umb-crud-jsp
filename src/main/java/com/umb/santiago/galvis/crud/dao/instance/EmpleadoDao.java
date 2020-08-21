package com.umb.santiago.galvis.crud.dao.instance;

import com.umb.santiago.galvis.crud.config.database.Conexion;
import com.umb.santiago.galvis.crud.dao.interfaces.Crud;
import com.umb.santiago.galvis.crud.modelo.Empleado;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class EmpleadoDao implements Crud<Empleado> {

    public static final String SELECT_ALL_EMPLOYEES = "select * from employees";
    public static final String INSERT_EMPLOYEE = "INSERT INTO public.employees (tipodocumento, numerodocumento, nombre, apellido, departamento) VALUES(?, ?, ?, ?, ?)";
    public static final String SELECT_SINGLE_EMPLOYEE = "SELECT * FROM employees where id=?";
    public static final String EDIT_EMPLOYEE = "UPDATE employees SET  tipodocumento=?, nombre=?, apellido=?, departamento=?,numerodocumento=? WHERE id=?";

    @Override
    public List<Empleado> list() {
        Connection connection = Conexion.getConnection();
        List<Empleado> empleados = new ArrayList<>();
        try {
            ResultSet result = connection.prepareStatement(SELECT_ALL_EMPLOYEES).executeQuery();
            while (result.next()) {
                empleados.add(Empleado.builder()
                        .id(Integer.parseInt(result.getString("id")))
                        .name(result.getString("Nombre"))
                        .lastName(result.getString("Apellido"))
                        .docType(result.getString("TipoDocumento"))
                        .docNumber(result.getString("NumeroDocumento"))
                        .department(result.getString("Departamento"))
                        .build());
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return empleados;
    }

    @Override
    public Empleado findById(int id) {
        Connection connection = Conexion.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_SINGLE_EMPLOYEE);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            result.next();
            return Empleado.builder()
                    .id(Integer.parseInt(result.getString("id")))
                    .name(result.getString("Nombre"))
                    .lastName(result.getString("Apellido"))
                    .docType(result.getString("TipoDocumento"))
                    .docNumber(result.getString("NumeroDocumento"))
                    .department(result.getString("Departamento"))
                    .build();
        } catch (Exception e) {
            log.error("Error al buscar empleado");
            return Empleado.builder().id(0).build();
        }
    }

    @Override
    public boolean add(Empleado toAdd) {
        Connection connection = Conexion.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(INSERT_EMPLOYEE);
            statement.setString((1), toAdd.getDocType());
            statement.setInt((2), Integer.parseInt(toAdd.getDocNumber()));
            statement.setString((3), toAdd.getName());
            statement.setString((4), toAdd.getLastName());
            statement.setString((5), toAdd.getDepartment());
            statement.executeUpdate();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean edit(Empleado toEdit) {
        Connection connection = Conexion.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(EDIT_EMPLOYEE);
            statement.setString((1), toEdit.getDocType());
            statement.setString((2), toEdit.getName());
            statement.setString((3), toEdit.getLastName());
            statement.setString((4), toEdit.getDepartment());
            statement.setInt((5), Integer.parseInt(toEdit.getDocNumber()));
            statement.setInt((6), toEdit.getId());
            statement.executeUpdate();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(int id)  {
        Connection connection = Conexion.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM employees WHERE id=?");
            statement.setInt(1, id);
            statement.executeUpdate();
        }catch(SQLException e){
            return false;
        }
        return true;
    }
}
