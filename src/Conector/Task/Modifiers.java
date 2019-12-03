/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conector.Task;

/**
 *
 * @author alberto
 */
public class Modifiers {
    
    /*
        Elimina contenido del cuerpo del mensaje de entrada
        Entradas: 1, Salidas: 1
    */
    void Slimmer(){
        
    }
    /*
        Elimina contenido del cuerpo del mensaje de entrada a partir de la información de contexto ofrecida en la entrada “contexto”
        Entradas: 2 (contexto y entrada), Salidas: 1
    */
    void ContextSlimmer(){
        
    }
    /*
        Añade contenido al cuerpo del mensaje de entrada a partir de la información de contexto ofrecida en la entrada “contexto”
        Entradas: 2 (contexto y entrada), Salidas: 1

    */
    void ContextEnricher(){
        
    }
    /*
        Añade contenido a la cabecera, del cuerpo del mensaje
        Entradas: 1, Salidas: 1
    */
    void HeaderPromoter(){
        
    }
    /*
        Copia en el cuerpo del mensaje parte de la cabecera
        Entradas: 1, Salidas: 1
    */
    void HeaderDemoter(){
        
    }
    /*
        Almacena en la cabecera un ID de correlación (de utilidad para la tarea Correlator)
        Entradas: 1, Salidas: 1
    */
    void IdSetter(){
        
    }
    /*
        Almacena en la cabecera una dirección de retorno (ofrecida en la entrada “contexto”) que podrá usar un puerto de respuesta para devolver una respuesta a una aplicación externa
        Entradas: 2, Salidas: 1
    */
    void AddressSetter(){
        
    }
}
