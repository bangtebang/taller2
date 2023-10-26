package model.data;

import org.jooq.DSLContext;
import org.jooq.DataType;
import org.jooq.impl.DSL;

import java.sql.Connection;

import static org.jooq.impl.DSL.*;
import static org.jooq.impl.SQLDataType.*;

public class DBGenerator {

    public static void iniciarBD(String nombreBD) throws ClassNotFoundException {
        Connection connection = DBConnector.connection("root","");
        DSLContext create = DSL.using(connection);
        crearBaseDato(create,nombreBD);
        create = actualizarConexion(connection,nombreBD);
        crearTablaSelecion(create);
        crearTablaJugador(create);
        relacionarTabla(create,"Jugador","id","Seleccion");
        DBConnector.closeConnection();
    }
    public static DSLContext conectarBD(String nombre) throws ClassNotFoundException {
        Connection connection = DBConnector.connection(nombre,"root","");
        DSLContext create = DSL.using(connection);
        return create;
    }
    private static void crearBaseDato(DSLContext create, String nombreBD){
        create.createDatabaseIfNotExists(nombreBD).execute();
    }

    private static DSLContext actualizarConexion(Connection connection,String nombreBD){
        DBConnector.closeConnection();
        connection= DBConnector.connection(nombreBD,"root","");
        DSLContext create=DSL.using(connection);
        return create;
    }

    private static void crearTablaSelecion(DSLContext create){
        create.createTableIfNotExists("Seleccion").column("nombre_pais",VARCHAR(100))
                .column("ranking",INTEGER)
                .column("bandera",VARCHAR(100))
                .column("id",VARCHAR(50))
                .constraint(primaryKey("id")).execute();
    }

    private static void crearTablaJugador(DSLContext create){
        create.createTableIfNotExists("Jugador").column("n°",VARCHAR(50))
                .column("nombre",VARCHAR(100))
                .column("posicion",VARCHAR(100))
                .column("id",VARCHAR(50))
                .constraint(primaryKey("n°")).execute();

    }
    private static void relacionarTabla(DSLContext create, String nombreTabla, String claveForanea, String nombreTablaRelacion){
        create.alterTableIfExists(nombreTabla).alterConstraint(foreignKey(claveForanea).references(nombreTablaRelacion)).enforced();
    }
    private static void agregarColumnaTabla(DSLContext create, String nombreTabla, String columna, DataType tipoColumna){
        create.alterTableIfExists(nombreTabla).addColumn(columna,tipoColumna);
    }
}