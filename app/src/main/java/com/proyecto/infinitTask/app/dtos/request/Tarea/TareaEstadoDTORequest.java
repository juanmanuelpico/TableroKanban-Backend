package com.proyecto.infinitTask.app.dtos.request.Tarea;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class TareaEstadoDTORequest {

    private int idTarea;
    private String estado;

}
