package seoulMate;

import java.sql.SQLException;
import java.sql.Connection;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
 
public class DBConnection{
    public static Connection getConnection() throws SQLException, NamingException, 
    ClassNotFoundException{
            Context initCtx = new InitialContext();
            
            //initCtx�� lookup�޼��带 �̿��ؼ� "java:comp/env" �� �ش��ϴ� ��ü�� ã�Ƽ� evnCtx�� ����
            Context envCtx = (Context) initCtx.lookup("java:comp/env");
            
            
            //envCtx�� lookup�޼��带 �̿��ؼ� "jdbc/orcl"�� �ش��ϴ� ��ü�� ã�Ƽ� ds�� ����
            DataSource ds = (DataSource) envCtx.lookup("jdbc/orcl");
            
            //getConnection�޼��带 �̿��ؼ� Ŀ�ؼ� Ǯ�� ���� Ŀ�ؼ� ��ü�� ���� conn������ ����
            Connection conn = ds.getConnection();
            return conn;
            
            /*
             * ���� �ڵ带 �Ʒ��� ���� �ٿ��� �ۼ� �����ϴ�.
             Context context = new InitialContext();
             DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc/oracle");
             Connection con = dataSource.getConnection(); 
             
             */
    }
}