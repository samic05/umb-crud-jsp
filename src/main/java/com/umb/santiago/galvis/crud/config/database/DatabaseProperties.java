package com.umb.santiago.galvis.crud.config.database;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DatabaseProperties {
    private String url;
    private String driver;
    private String host;
    private String port;
    private String database;
    private String prefix;
    private String user;
    private String password;

}
