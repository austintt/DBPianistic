package servlet;
import java.sql.*;

public class DataToucher
{
	public static void main(String args[])
	{
		DataToucher queryDb = new DataToucher();
		queryDb.run();
	}
	public static void run()
	{
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:testpianoDB.db");
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery( "SELECT * FROM piano;" );
			while ( rs.next() ) 
			{
				int byui_piano_sk = rs.getInt("byui_piano_sk");
				int byui_piano_id = rs.getInt("byui_piano_id");
				int make_id       = rs.getInt("make_id");
				int model_id      = rs.getInt("model_id");
				int type_id       = rs.getInt("type_id");
				String mfg_serial = rs.getString("mfg_serial");
				int year          = rs.getInt("year");
				int age           = rs.getInt("age");
				int location_id   = rs.getInt("location_id");
				int condition_id  = rs.getInt("condition_id");
				float cost        = rs.getFloat("cost");
				System.out.println( "byui_piano_sk = " + byui_piano_sk );
				System.out.println( "byui_piano_id = " + byui_piano_id );
				System.out.println( "make_id = " + make_id );
				System.out.println( "model_id = " + model_id );
				System.out.println( "type_id = " + type_id );
				System.out.println( "mfg_serial = " + mfg_serial );
				System.out.println( "year = " + year );
				System.out.println( "age = " + age );
				System.out.println( "location_id = " + location_id );
				System.out.println( "condition_id = " + condition_id );
				System.out.println( "cost = " + cost );         
				System.out.println();
			}
			rs.close();
			stmt.close();
			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		System.out.println("Operation done successfully");
	}
}