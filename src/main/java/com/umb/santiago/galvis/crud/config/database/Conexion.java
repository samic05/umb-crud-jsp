package com.umb.santiago.galvis.crud.config.database;

import com.umb.santiago.galvis.crud.config.Configuration;
import com.umb.santiago.galvis.crud.config.database.hooks.ShutDownHook;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Slf4j
public class Conexion {
    private static Connection connection=null;
    private static final String ERROR_CREATING_CONNECTION = "Error creando la conexion con la base de datos";

    public static Connection getConnection(){
        try
        {
            if(connection == null)
            {

                Runtime.getRuntime().addShutdownHook(new ShutDownHook());
                DatabaseProperties properties = Configuration.getDatabaseProperties();
                Class.forName(properties.getDriver());
                String url = properties.getPrefix()+properties.getHost()+":"+properties.getPort()+"/"+properties.getDatabase();
                connection =  DriverManager.getConnection(url,properties.getUser(),properties.getPassword());
            }
        }catch(SQLException | ClassNotFoundException e){
            log.error(ERROR_CREATING_CONNECTION,e);
        }
        return connection;
    }
}
