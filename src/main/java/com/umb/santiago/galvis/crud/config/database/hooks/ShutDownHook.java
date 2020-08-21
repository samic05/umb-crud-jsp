package com.umb.santiago.galvis.crud.config.database.hooks;

import com.umb.santiago.galvis.crud.config.database.Conexion;

import java.sql.Connection;

/**
 *
 * @author samic
 */
public class ShutDownHook extends Thread {

    @Override
    public void run(){
        try
        {
            Connection connection = Conexion.getConnection();
            connection.close();
        }catch(Exception e)
        {
            throw new RuntimeException(e);
        }
    }
}
