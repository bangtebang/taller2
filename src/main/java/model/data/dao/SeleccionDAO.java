package model.data.dao;

import model.Seleccion;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Result;
import org.jooq.Table;
import org.jooq.impl.DSL;

import java.util.List;

import static model.data.dao.JugadorDAO.exportardatos;
import static org.jooq.impl.DSL.name;
import static org.jooq.impl.DSL.table;

public class SeleccionDAO {
    public static void agregarSeleccion(DSLContext query, Seleccion seleccion){
        Table tablaCarrera= table(name("Seleccion"));
        Field[] columnas = tablaCarrera.fields("nombre_seleccion","ranking","bandera","id");
        query.insertInto(tablaCarrera, columnas[0], columnas[1],columnas[2],columnas[3])
                .values(seleccion.getNombre(),seleccion.getRanking(),seleccion.getBandera(),seleccion.getId())
                .execute();
    }
    public static boolean validarExistenciaSelecciom(DSLContext query,String columnaTabla, Object dato){
        Result resultados = query.select().from(DSL.table("Seleccion")).where(DSL.field(columnaTabla).eq(dato)).fetch();
        if(resultados.size()>=1){
            return true;
        }
        else{
            return false;
        }
    }
    public static Seleccion buscarSeleccion(DSLContext query, Object dato){
        Result resultados= (Result) buscarSeleccion(query,"id",dato);
        String id = (String) resultados.getValue(0,"id");
        String nombreSeleccion = (String) resultados.getValue(0,"nombre_selecion");
        int ranking = (int) resultados.getValue(0,"ranking");
        String bandera = (String) resultados.getValue(0,"bandera");
    return new Seleccion(nombreSeleccion,ranking,bandera,id);
    }

    public static List buscarSeleccion(DSLContext query, String columnaTabla, Object dato){
        Result resultados = query.select().from(DSL.table("Seleccion")).where(DSL.field(columnaTabla).eq(dato)).fetch();
        return resultados;
    }
    public static Object[] getCodigoSelecciones(DSLContext query){
        Table seleccion= DSL.table("Seleccion");
        Result resultados = query.select(seleccion.field("id")).from(seleccion).fetch();
        if(resultados.size()==0){
            return new String[]{"Error no existen selecciones"};
        }
        else {
            return resultados.getValues("id").toArray();
        }
    }

    public static String[][] obtenerSeleccion(DSLContext query, String id) {
        Table tablaSeleccion= table(name("Seleccion"));
        Result resultados = query.select().from(tablaSeleccion).where(DSL.field("id").eq(id)).fetch();
        return exportardatos(resultados);
    }
}