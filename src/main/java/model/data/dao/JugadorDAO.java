package model.data.dao;

import model.Jugador;
import org.jooq.*;
import org.jooq.impl.DSL;

import java.util.ArrayList;
import java.util.List;

import static org.jooq.impl.DSL.*;

public class JugadorDAO {

    public static void agregarJugador(DSLContext query, Jugador jugador){
        Table tablaJugador= table(name("Jugador"));
        Field[] columnas = tablaJugador.fields("n°","nombre","posicion","id");
        query.insertInto(tablaJugador, columnas[0], columnas[1],columnas[2],columnas[3])
                .values(jugador.getNumero(),jugador.getNombre(),jugador.getPosicion(),jugador.getSeleccion().getId())
                .execute();

    }

    public static int getNumeroJugador(DSLContext query, String nombre, String seleccion) {
        Table tablaJugador= table(name("Jugador"));
        Table tablaSeleccion= table(name("Seleccion"));
        Result resultados = query.select().from(tablaJugador).join(tablaSeleccion).on(DSL.field("id").eq(DSL.field("id")))
                .where(DSL.field("nombre").eq(nombre)).and(DSL.field("id").eq(seleccion)).fetch();
        return (int) resultados.getValue(0,"n°");
    }
    public void modificarJugador(DSLContext query,int numero, String columnaTabla, Object dato){
        query.update(DSL.table("Jugador")).set(DSL.field(columnaTabla),dato).
                where(DSL.field("n°").eq(numero)).execute();
    }
    public List obtenerJugador(DSLContext query, String columnaTabla, Object dato){
        Result resultados = query.select().from(DSL.table("Jugador")).where(DSL.field(columnaTabla).eq(dato)).fetch();
        return obtenerListaJugadores(resultados);
    }
    public void eliminarJugador(DSLContext query, int numero){
        Table tablaJugador= table(name("Jugador"));
        query.delete(DSL.table("Jugador")).where(DSL.field("n°").eq(numero)).execute();
    }
    public List obtenerPoscionJugador(DSLContext query){
        Table tablaJugador= table(name("Jugador"));
        Result resultados= query.select().from(DSL.table("posicion"))
                .fetch();
        return obtenerListaJugadores(resultados);
    }
    private List obtenerListaJugadores(Result resultados){
        List<Jugador> jugadores= new ArrayList<>();
        for(int fila=0; fila<resultados.size();fila++){
            int numero = (int) resultados.getValue(fila,"n°");
            String nombre = (String) resultados.getValue(fila,"nombre");
            String posicion = (String) resultados.getValue(fila,"posicion");
            jugadores.add(new Jugador(nombre,numero,posicion,null));
        }
        return jugadores;
    }
    private static String[][] exportardatos(Result resultados){
        String[][] datosResultado=new String[resultados.size()][4];
        for(int registro = 0; registro < resultados.size(); registro ++){
            datosResultado[registro][0] = (String) resultados.getValue(registro,"nombre");
            datosResultado[registro][1] = (String) resultados.getValue(registro,"posicion");
            datosResultado[registro][2] = (String) resultados.getValue(registro,"nombre_seleccion");
            datosResultado[registro][3] = (String) resultados.getValue(registro,"id");
        }
        return datosResultado;
    }
    public static boolean validarExistenciaJugador(DSLContext query,String columnaTabla, Object dato){
        Result resultados = query.select().from(DSL.table("Jugador")).where(DSL.field(columnaTabla).eq(dato)).fetch();
        if(resultados.size()>=1){
            return true;
        }
        else{
            return false;
        }
    }
}
