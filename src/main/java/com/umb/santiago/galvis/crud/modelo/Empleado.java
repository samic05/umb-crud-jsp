package com.umb.santiago.galvis.crud.modelo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Empleado {
    private int id;
    private String docType;
    private String docNumber;
    private String name;
    private String lastName;
    private String department;

}
