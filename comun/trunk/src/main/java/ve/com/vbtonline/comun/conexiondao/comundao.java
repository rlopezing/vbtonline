package ve.com.vbtonline.comun.conexiondao;


import org.apache.log4j.Logger;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by IntelliJ IDEA.
 * User: Manuel Serrano
 * Date: 25/05/2010
 * Time: 02:13:10 AM
 * To change this template use File | Settings | File Templates.
 */
public class comundao {
        private static final Logger logger = Logger.getLogger (comundao.class);

    /** La fuente de datos que apunta al repositorio de base de datos */
    private DataSource dataSource;

    /** El constructor de la clase
     */
    public comundao () {
    }

    /** Obtiene la fuente de datos
     * @return La fuente de datos que apunta al repositorio de base de datos
     */
    public DataSource getDataSource () {
        return dataSource;
    }

    /** Asigna la fuente de datos
     * @param dataSource La fuente de datos que apunta al repositorio de base de datos
     */
    public void setDataSource (DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /** Obtiene una conexi�n a la base de datos
     * @return La conexi�n a la base de datos
     * @throws java.sql.SQLException
     */
    protected Connection getConnection () throws Exception {
        Connection connection = null;

        try {
            connection = dataSource.getConnection ();
        }
        catch (SQLException e) {

        }

        return connection;
    }

    /** Metodo que cierra los objetos de base de datos
     * @param connection La conexion de base de datos
     * @param statement El objeto statement
     * @param resultado
     */
    protected void closeJdbcObjects(Connection connection, Statement statement, int resultado) throws Exception {
        closeJdbcObjects (connection, statement, null);
    }

    /** Metodo que cierra los objetos de base de datos
     * @param connection La conexion de base de datos
     * @param statement El objeto statement
     * @param result El objeto result
     */
    protected void closeJdbcObjects (Connection connection, Statement statement, ResultSet result) throws Exception {
        try {
            if (result != null) result.close ();
        } catch (SQLException e) {

        }

        try {
            if (statement != null) statement.close ();
        } catch (SQLException e) {

        }

        try {
            if (connection != null) connection.close ();
        } catch (SQLException e) {
          
        }
    }
}
