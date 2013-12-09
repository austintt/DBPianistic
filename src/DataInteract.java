import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataInteract 
{

	String dataSource =  "jdbc:sqlite::resource:test.db";
	
	public DataInteract()
	{
		//Place holder constructor, there are no actual member variables. 
		//This is basically a database adaptor containing functions for interacting with
		//the piano table
	}

	/**
	 * DESC: Insert an item into the piano table. All parameters must be used and have valid data.
	 * @param pBYUIPianoID, pPianoMake, pPianoModel, pPianoType, pMfgSerial, pYear, pBuildingName
	 *        pRoomNumber, pRoomType, pPianoCondition, pCost.
	 * 
	 *		  Each parameter maps to a column in the piano table and is named accordingly
	 *
	 */
	public void insertPiano(int pBYUIPianoID, String pPianoMake, String pPianoModel,
						    String pPianoType, String pMfgSerial, int pYear, 
						    String pBuildingName, int pRoomNumber, 
						    String pRoomType, String pPianoCondition, float pCost)
	{
		Connection c = null;
		Statement stmt = null;
		try 
		{
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:resource:test.db");
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");
			stmt = c.createStatement();

			String sql = "INSERT INTO PIANO "
					   + "( byui_piano_id "
					   + ", make_id "
					   + ", model_id "
					   + ", type_id "
					   + ", mfg_serial "
					   + ", year "
					   + ", building_id "
					   + ", room_number "
					   + ", room_type_id "
					   + ", condition_id "
					   + ", cost) "
					   + "VALUES ( " + pBYUIPianoID + " "
					   + ", (SELECT make_id from piano_make WHERE make_name = '" + pPianoMake + "')"
					   + ", (SELECT model_id from piano_model WHERE model_name = '" + pPianoModel + "')"
					   + ", (SELECT type_id from piano_type WHERE type_text = '" + pPianoType + "')"
					   + ", '" + pMfgSerial + "'"
					   + ", " + pYear + " "
					   + ", (SELECT byui_building_id from building WHERE building_name = '" + pBuildingName + "')"
					   + ", " + pRoomNumber + " "
					   + ", (SELECT room_type_id from ROOM_TYPE WHERE room_type_text = '" + pRoomType + "')"
					   + ", (SELECT condition_id from PIANO_CONDITION WHERE condition_text = '" + pPianoCondition + "')"
					   + ", " + pCost + ");";
			System.out.println("About to execute statement: " + sql);

			stmt.executeUpdate(sql);
			stmt.close();
			c.commit();
			c.close();
		}
		catch(Exception e)
		{
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		System.out.println("Records created successfully");
	}



	/**
	 * DESC: Update an item in the piano table. All parameters must be used and have valid data.
	 * @param pColumn, pValue, pPrimaryKeyValue
	 *        
	 * 		  pComumn          -- name of the column to be updated.
	 * 		  pValue           -- value to be inserted
	 *        pPrimaryKeyValue -- select the row in the table where the value will be inserted
	 *		  
	 *		  //TODO: Determine whether or not update method is really needed
	 */
	public void updatePiano(String pColumn, String pValue, int pByuiPianoId)
	{
		Connection c = null;
		Statement stmt = null;
		try 
		{
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection(dataSource);
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			String sql = "UPDATE piano "
					   + "SET " + pColumn + " = " + pValue + " where " +  "byui_piano_id =" + pByuiPianoId + ";";
			System.out.println("About to execute statement: " + sql);
			stmt.executeUpdate(sql);
			c.commit();
			stmt.close();
			c.close();
		}
		catch (Exception e)
		{
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		System.out.println("Table Update done successfully");
	}



	
	/**
	 * DESC: Delete an item from the piano table
	 * @param pBYUIPianoID, 
	 *        
	 * 
	 *		  pBYUIPiano ID is a unique value to the piano so that is the only parameter
	 *		  needed to do a successful delete.
	 */
	
	public void deletePiano(int byuiPianoId)
	{
		Connection c = null;
		Statement stmt = null;
		try
		{
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection(dataSource);
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			String sql = "DELETE FROM piano WHERE byui_piano_id = " + byuiPianoId + ";";
			System.out.println("About to execute statement: " + sql);
			stmt.executeUpdate(sql);
			c.commit();
			stmt.close();
			c.close();
		}
		catch (Exception e)
		{
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		System.out.println("Delete operation done successfully");
	}

	/**
	 * DESC: Insert an entry into the Piano Service History table all parameters must have valid data
	 * @param pBYUIPianoID, pDateOfService, pActionPerformed, pServiceNotes, pNextServiceDate, pActionPerformedBy,
	 *        pFutureServiceNotes, pPreviousBuildingIfMoved, pPreviousRoomIfMoved, pCurrentRelativeHumidity, 
	 *        pCurrentRelativeTemperature
	 * 
	 *		  Each parameter maps to a column in the piano table and is named accordingly
	 *		  
	 */
	public void insertPianoServiceHistory(int pByuiPianoId, String pDateOfService, String pActionPerformed,
			String pServiceNotes, String pNextServiceDate, String pActionPerformedBy,
			String pFutureServiceNotes, int pPreviousBuildingIfMoved, int pPreviousRoomIfMoved,
			int pCurrentRelativeHumidity, int pCurrentRelativeTemperature)
	{
		Connection c = null;
		Statement stmt = null;
		try 
		{
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection(dataSource);
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");
			stmt = c.createStatement();
			String sql = "INSERT INTO "
					   + "piano_service_history "
					   + "( byui_piano_id"
					   + ", date_of_service"
					   + ", action_performed"
					   + ", service_notes"
					   + ", next_service_date"
					   + ", action_performed_by"
					   + ", future_service_notes"
					   + ", previous_building_if_moved"
					   + ", previous_room_if_moved"
					   + ", current_relative_humidity"
					   + ", current_relative_temperature)"
					   + "VALUES (" + pByuiPianoId + "," 
					                + pDateOfService + "," 
					                + pActionPerformed + "," 
					                + pServiceNotes + "," 
					                + pNextServiceDate + "," 
					                + pActionPerformedBy + "," 
					                + pFutureServiceNotes + "," 
					                + pPreviousBuildingIfMoved + "," 
					                + pPreviousRoomIfMoved + "," 
					                + pCurrentRelativeHumidity + "," 
					                + pCurrentRelativeTemperature + ");";
			System.out.println("About to execute statement: " + sql);

			stmt.executeUpdate(sql);
			stmt.close();
			c.commit();
			c.close();
		}
		catch(Exception e)
		{
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		System.out.println("Records created successfully");

	}

	/**
	 * DESC: Update an item in the piano table. All parameters must be used and have valid data.
	 * @param pColumn, pValue, pPrimaryKeyValue
	 *        
	 * 		  pComumn          -- name of the column to be updated.
	 * 		  pValue           -- value to be inserted
	 *        pPrimaryKeyValue -- select the row in the table where the value will be inserted
	 *		  
	 *
	 */
	public void updatePianoServiceHistory(String pColumn, String pValue, int pPrimaryKeyValue)
	{
		Connection c = null;
		Statement stmt = null;
		try 
		{
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection(dataSource);
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			String sql = "UPDATE piano_service_history set " + pColumn + " = " + pValue + " where " +  "job_id_pk =" + pPrimaryKeyValue+ ";";
			System.out.println("About to execute statement: " + sql);
			stmt.executeUpdate(sql);
			c.commit();
			stmt.close();
			c.close();
		}
		catch ( Exception e )
		{
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		System.out.println("Table Update done successfully");

	}

	/**
	 * DESC: Delete an item from the piano_service_history table
	 * @param pPrimaryKeyValue, 
	 *        
	 * 
	 *		  pBYUIPiano ID is a unique value to the piano so that is the only parameter
	 *		  needed to do a successful delete.
	 */
	public void deletePianoServiceHistory(int pPrimaryKeyValue)
	{
		Connection c = null;
		Statement stmt = null;
		try
		{
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection(dataSource);
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			String sql = "DELETE FROM piano_service_history WHERE job_id_pk = " + pPrimaryKeyValue + ";";
			System.out.println("About to execute statement: " + sql);
			stmt.executeUpdate(sql);
			c.commit();
			stmt.close();
			c.close();
		}
		catch (Exception e)
		{
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		System.out.println("Delete operation done successfully");

	}

	/**
	 * DESC: Add an additiona piano type to the piano_type table
	 * @param pPianoTypeText 
	 *        
	 * 
	 *		  pPianoTypeText is a unique value to the piano so that is the only parameter
	 *		  needed to do a successful insert.
	 */
	public void insertPianoType(String pPianoTypeText)
	{
		Connection c = null;
		Statement stmt = null;
		try 
		{
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection(dataSource);
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");
			stmt = c.createStatement();

			String sql = "INSERT INTO piano_type (type_text)"
					+    "VALUES (" + pPianoTypeText + ");";
			System.out.println("About to execute statement: " + sql);

			stmt.executeUpdate(sql);
			stmt.close();
			c.commit();
			c.close();
		}
		catch(Exception e)
		{
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		System.out.println("Records created successfully");

	}

	/**
	 * DESC: Add an additional piano type to the piano_type table
	 * @param pNewValue, pOldValue
	 *        
	 * 
	 *		  pPianoTypeText is a unique value to the piano so that is the only parameter
	 *		  needed to do a successful insert.
	 */

	public void updatePianoType(String pColumn, String pNewValue, String pOldValue)
	{
		Connection c = null;
		Statement stmt = null;
		try 
		{
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection(dataSource);
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			String sql = "UPDATE piano_type set " + pColumn + " = " + pNewValue + " where " +  "piano_type =" + pOldValue+ ";";
			System.out.println("About to execute statement: " + sql);
			stmt.executeUpdate(sql);
			c.commit();
			stmt.close();
			c.close();
		}
		catch ( Exception e )
		{
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		System.out.println("Table Update done successfully");
	}

	/**
	 * DESC: Delete a piano type from the piano type table
	 * @param pPianoTypeText,
	 *        
	 * 
	 *		  pPianoTypeText is a unique value to the piano so that is the only parameter
	 *		  needed to do a successful delete.
	 */
	public void deletePianoType(String pPianoTypeText)
	{
		Connection c = null;
		Statement stmt = null;
		try
		{
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection(dataSource);
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			String sql = "DELETE FROM piano_type WHERE type_text = " + pPianoTypeText + ";";
			System.out.println("About to execute statement: " + sql);
			stmt.executeUpdate(sql);
			c.commit();
			stmt.close();
			c.close();
		}
		catch (Exception e)
		{
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		System.out.println("Delete operation done successfully");


	}

	/**
	 * DESC: Insert a piano model into the piano model table.
	 * @param pPianoModelName,
	 *        
	 * 
	 *		  pPianoModelName is a unique value to the piano so that is the only parameter
	 *		  needed to do a successful insert.
	 */
	public void insertPianoModel(String pPianoModelName)
	{
		Connection c = null;
		Statement stmt = null;
		try 
		{
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection(dataSource);
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");
			stmt = c.createStatement();

			String sql = "INSERT INTO piano_model (model_name)"
					+    "VALUES (" + pPianoModelName + ");";
			System.out.println("About to execute statement: " + sql);

			stmt.executeUpdate(sql);
			stmt.close();
			c.commit();
			c.close();
		}
		catch(Exception e)
		{
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		System.out.println("Records created successfully");


	}

	/**
	 * DESC: Update a value in the piano model table
	 * @param pColumn, pValue, pNewPianoModelName
	 *        
	 * 		  pComumn            -- name of the column to be updated.
	 * 		  pValue             -- value to be inserted
	 *        pOldPianoModelName -- Select the old value to be overwritten.
	 */
	public void updatePianoModel(String pColumn, String pValue, String pOldPianoModelName)
	{
		Connection c = null;
		Statement stmt = null;
		try 
		{
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection(dataSource);
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			String sql = "UPDATE piano_model set " + pColumn + " = " + pValue + " where " +  "model_name = " + pOldPianoModelName+ ";";
			System.out.println("About to execute statement: " + sql);
			stmt.executeUpdate(sql);
			c.commit();
			stmt.close();
			c.close();
		}
		catch ( Exception e )
		{
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		System.out.println("Table Update done successfully");

	}

	/**
	 * DESC: Delete a piano model from the piano type table
	 * @param pPianoModelName,
	 *        
	 * 
	 *		  pPianoModelName is a unique value to the piano so that is the only parameter
	 *		  needed to do a successful delete.
	 */
	public void deletePianoModel(String pPianoModelName)
	{
		Connection c = null;
		Statement stmt = null;
		try
		{
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection(dataSource);
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			String sql = "DELETE FROM piano_model WHERE model_name = " + pPianoModelName + ";";
			System.out.println("About to execute statement: " + sql);
			stmt.executeUpdate(sql);
			c.commit();
			stmt.close();
			c.close();
		}
		catch (Exception e)
		{
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		System.out.println("Delete operation done successfully");
	}

	/**
	 * DESC: Insert a piano make into the piano make table.
	 * @param pPianoMakeName,
	 *        
	 * 
	 *		  pPianoMakeName is a unique value to the room type table so that is the only parameter
	 *		  needed to do a successful insert.
	 */
	public void insertPianoMake(String pPianoMakeName)
	{
		Connection c = null;
		Statement stmt = null;
		try 
		{
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection(dataSource);
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");
			stmt = c.createStatement();

			String sql = "INSERT INTO piano_make (make_name)"
					+    "VALUES (" + pPianoMakeName + ");";
			System.out.println("About to execute statement: " + sql);

			stmt.executeUpdate(sql);
			stmt.close();
			c.commit();
			c.close();
		}
		catch(Exception e)
		{
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		System.out.println("Records created successfully");
	}

	/**
	 * DESC: Add an additional piano type to the piano make table
	 * @param pColumn, pNewValue, oldValue
	 *        
	 * 
	 *		  pPianoTypeText is a unique value to the piano make table so that is the only parameter
	 *		  needed to do a successful insert.
	 */
	public void updatePianoMake(String pColumn, String pNewValue, String pOldValue)
	{
		Connection c = null;
		Statement stmt = null;
		try 
		{
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection(dataSource);
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			String sql = "UPDATE piano_make set " + pColumn + " = " + pNewValue + " where " +  "make_name = " + pOldValue+ ";";
			System.out.println("About to execute statement: " + sql);
			stmt.executeUpdate(sql);
			c.commit();
			stmt.close();
			c.close();
		}
		catch (Exception e)
		{
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		System.out.println("Table Update done successfully");


	}

    /**
	 * DESC: Delete a piano condition from the room type condition table
	 * @param pPianoMakeName,
	 *        
	 * 
	 *		  pPianoMakeName is a unique value to the room type table so that is the only parameter
	 *		  needed to do a successful delete.
	 */
	public void deletePianoMake(String pPianoMakeName)
	{
		Connection c = null;
		Statement stmt = null;
		try
		{
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection(dataSource);
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			String sql = "DELETE FROM piano_make WHERE make_name = " + pPianoMakeName + ";";
			System.out.println("About to execute statement: " + sql);
			stmt.executeUpdate(sql);
			c.commit();
			stmt.close();
			c.close();
		}
		catch (Exception e)
		{
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		System.out.println("Delete operation done successfully");
	}

	/**
	 * DESC: Insert a building into the piano condition table.
	 * @param pPianoCondition,
	 *        
	 * 
	 *		  pPianoCondition is a unique value to the piano condition table so that is the only parameter
	 *		  needed to do a successful insert.
	 */
	public void insertPianoCondition(String pPianoCondition)
	{
		Connection c = null;
		Statement stmt = null;
		try 
		{
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection(dataSource);
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");
			stmt = c.createStatement();

			String sql = "INSERT INTO piano_condition (condition_text)"
					+    "VALUES (" + pPianoCondition + ");";
			System.out.println("About to execute statement: " + sql);

			stmt.executeUpdate(sql);
			stmt.close();
			c.commit();
			c.close();
		}
		catch(Exception e)
		{
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		System.out.println("Records created successfully");

	}
	
	/**
	 * DESC: Add an additional piano type to the piano condition table
	 * @param pColumn, pNewValue, oldValue
	 *        
	 * 
	 *		  pPianoTypeText is a unique value to the piano condition table so that is the only parameter
	 *		  needed to do a successful insert.
	 */
	public void updatePianoCondition(String pColumn, String pNewValue, String pOldValue)
	{
		Connection c = null;
		Statement stmt = null;
		try 
		{
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection(dataSource);
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			String sql = "UPDATE piano_condition set " + pColumn + " = " + pNewValue + " where " +  "condition_text = " + pOldValue+ ";";
			System.out.println("About to execute statement: " + sql);
			stmt.executeUpdate(sql);
			c.commit();
			stmt.close();
			c.close();
		}
		catch (Exception e)
		{
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		System.out.println("Table Update done successfully");
	}

    /**
	 * DESC: Delete a piano condition from the piano condition table
	 * @param pPianoCondition,
	 *        
	 * 
	 *		  pPianoCondition is a unique value to the piano condition table so that is the only parameter
	 *		  needed to do a successful delete.
	 */
	public void deletePianoCondition(String pPianoCondition)
	{
		Connection c = null;
		Statement stmt = null;
		try
		{
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection(dataSource);
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			String sql = "DELETE FROM piano_condition WHERE condition_text = " + pPianoCondition + ";";
			System.out.println("About to execute statement: " + sql);
			stmt.executeUpdate(sql);
			c.commit();
			stmt.close();
			c.close();
		}
		catch (Exception e)
		{
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		System.out.println("Delete operation done successfully");

	}

	
	/**
	 * DESC: Insert a building into the piano building table.
	 * @param pBuildingName,
	 *        
	 * 
	 *		  pBuildingName is a unique value to the building table so that is the only parameter
	 *		  needed to do a successful insert.
	 */
	public void insertBuilding(String pBuildingName)
	{
		Connection c = null;
		Statement stmt = null;
		try 
		{
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection(dataSource);
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");
			stmt = c.createStatement();

			String sql = "INSERT INTO building (building_name)"
					+    "VALUES (" + pBuildingName + ");";
			System.out.println("About to execute statement: " + sql);

			stmt.executeUpdate(sql);
			stmt.close();
			c.commit();
			c.close();
		}
		catch(Exception e)
		{
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		System.out.println("Records created successfully");
	}

	/**
	 * DESC: Add an additional piano type to the building table
	 * @param pColumn, pNewValue, oldValue
	 *        
	 * 
	 *		  pPianoTypeText is a unique value to the building table so that is the only parameter
	 *		  needed to do a successful insert.
	 */
	public void updateBuilding(String pColumn, String pNewValue, String pOldValue)
	{
		Connection c = null;
		Statement stmt = null;
		try 
		{
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection(dataSource);
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			String sql = "UPDATE building set " + pColumn + " = " + pNewValue + " where " +  "building_name = " + pOldValue+ ";";
			System.out.println("About to execute statement: " + sql);
			stmt.executeUpdate(sql);
			c.commit();
			stmt.close();
			c.close();
		}
		catch (Exception e)
		{
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		System.out.println("Table Update done successfully");
	}

    /**
	 * DESC: Delete a building from the building table
	 * @param pBuildingName,
	 *        
	 * 
	 *		  pBuildingName is a unique value to the building table so that is the only parameter
	 *		  needed to do a successful delete.
	 */
	public void deleteBuilding(String pBuildingName)
	{
		Connection c = null;
		Statement stmt = null;
		try
		{
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection(dataSource);
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			String sql = "DELETE FROM building WHERE building_name = " + pBuildingName + ";";
			System.out.println("About to execute statement: " + sql);
			stmt.executeUpdate(sql);
			c.commit();
			stmt.close();
			c.close();
		}
		catch (Exception e)
		{
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		System.out.println("Delete operation done successfully");
	}


	/**
	 * DESC: Insert a building into the room type condition table.
	 * @param pRoomType,
	 *        
	 * 
	 *		  pRoomType is a unique value to the room type table so that is the only parameter
	 *		  needed to do a successful insert.
	 */
	public void insertRoomType(String pRoomType)
	{
		Connection c = null;
		Statement stmt = null;
		try 
		{
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection(dataSource);
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");
			stmt = c.createStatement();

			String sql = "INSERT INTO room_type (room_type_text)"
					+    "VALUES (" + pRoomType + ");";
			System.out.println("About to execute statement: " + sql);

			stmt.executeUpdate(sql);
			stmt.close();
			c.commit();
			c.close();
		}
		catch(Exception e)
		{
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		System.out.println("Records created successfully");
	}

	/**
	 * DESC: Add an additional piano type to the room type table
	 * @param pColumn, pNewValue, oldValue
	 *        
	 * 
	 *		  pPianoTypeText is a unique value to the room type table so that is the only parameter
	 *		  needed to do a successful insert.
	 */
	public void updateRoomType(String pColumn, String pNewValue, String pOldValue)
	{
		Connection c = null;
		Statement stmt = null;
		try 
		{
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection(dataSource);
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			String sql = "UPDATE room_type set " + pColumn + " = " + pNewValue + " where " +  "room_type_text = " + pOldValue+ ";";
			System.out.println("About to execute statement: " + sql);
			stmt.executeUpdate(sql);
			c.commit();
			stmt.close();
			c.close();
		}
		catch (Exception e)
		{
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		System.out.println("Table Update done successfully");
	}

    /**
	 * DESC: Delete a piano condition from the room type condition table
	 * @param pRoomType,
	 *        
	 * 
	 *		  pRoomType is a unique value to the room type table so that is the only parameter
	 *		  needed to do a successful delete.
	 */
	public void deleteRoomType(String pRoomType)
	{
		Connection c = null;
		Statement stmt = null;
		try
		{
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection(dataSource);
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			String sql = "DELETE FROM room_type WHERE room_type_text = " + pRoomType + ";";
			System.out.println("About to execute statement: " + sql);
			stmt.executeUpdate(sql);
			c.commit();
			stmt.close();
			c.close();
		}
		catch (Exception e)
		{
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		System.out.println("Delete operation done successfully");
	}

	public List<Piano> pianoDumpQuery()
	{
		Connection c = null;
		Statement stmt = null;
		try 
		{
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection(dataSource);
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");
			List<Piano> pianoData = new ArrayList<Piano>();

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery( "SELECT p.byui_piano_id "
					                        + ",      p.mfg_serial "
					                        + ",      p.year "
					                        + ",      (SELECT Date('now') - year) AS age "
					                        + ",      b.building_name "
					                        + ",      p.room_number "
					                        + ",      rt.room_type_text "
					                        + ",      pc.condition_text "
					                        + ",      pmk.make_name "
					                        + ",      pm.model_name "
					                        + ",      pt.type_text "
					                        + ",      p.cost "
					                        + "FROM piano p "
					                        + "LEFT OUTER JOIN building b ON b.byui_building_id = p.building_id "
					                        + "LEFT OUTER JOIN piano_condition pc ON pc.condition_id = p.condition_id "
					                        + "LEFT OUTER JOIN piano_make pmk ON pmk.make_id = p.make_id "
					                        + "LEFT OUTER JOIN room_type rt ON rt.room_type_id = p.room_type_id "
					                        + "LEFT OUTER JOIN piano_type pt ON pt.type_id = p.type_id "
					                        + "LEFT OUTER JOIN piano_model pm ON pm.model_id = p.model_id;");
			
			while (rs.next()) 
			{
				int byui_piano_id     = rs.getInt("byui_piano_id");
				String make_name      = rs.getString("make_name");
				String model_name     = rs.getString("model_name");
				String type_text      = rs.getString("type_text");
				String mfg_serial     = rs.getString("mfg_serial");
				int year              = rs.getInt("year");
				int age               = rs.getInt("age");
				String building_name  = rs.getString("building_name");
				int room_number       = rs.getInt("room_number");
				String room_type_text = rs.getString("room_type_text");
				int condition_text    = rs.getInt("condition_text");
				float cost            = rs.getFloat("cost");

				System.out.println( "byui_piano_id = " + byui_piano_id );
				System.out.println( "make_name = " + make_name );
				System.out.println( "model_name = " + model_name );
				System.out.println( "type_text = " + type_text );
				System.out.println( "mfg_serial = " + mfg_serial );
				System.out.println( "year = " + year );
				System.out.println( "age = " + age );
				System.out.println( "building_name = " + building_name );
				System.out.println( "room_number = " + room_number );
				System.out.println( "room_type_text = " + room_type_text );
				System.out.println( "condition_text = " + condition_text );
				System.out.println( "cost = " + cost );         
				System.out.println();

				pianoData.add(new Piano(byui_piano_id, make_name,
				model_name,     
				type_text,      
				mfg_serial,     
				year,             
				age,               
				building_name,  
				room_number,      
				room_type_text, 
				condition_text,   
				cost));

			
			}
			rs.close();
			stmt.close();
			c.close();
			
			return pianoData;
		} 
		catch (Exception e) 
		{
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		System.out.println("Operation done successfully");
		return null;
	}
	
	public void pianoServiceHistoryDumpQuery()
	{
		Connection c = null;
		Statement stmt = null;
		try 
		{
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection(dataSource);
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery( "SELECT psh.byui_piano_id"
					                        + ",      psh.date_of_service"
					                        + ",      psh.action_performed"
					                        + ",      psh.service_notes"
					                        + ",      psh.next_service_date"
					                        + ",      psh.action_performed_by"
					                        + ",      psh.future_service_notes"
					                        + ",      b.building_name"
					                        + ",      psh.previous_room_if_moved"
					                        + ",      psh.current_relative_humidity"
					                        + ",      psh.current_relative_temperature "
					                        + "FROM piano_service_history psh "
					                        + "LEFT OUTER JOIN building b ON psh.previous_building_if_moved = b.byui_building_id "
					                        + "LEFT OUTER JOIN piano p ON psh.byui_piano_id = p.byui_piano_id;");
						
			while (rs.next()) 
			{
				int byui_piano_id                 = rs.getInt("byui_piano_id");
				String date_of_service            = rs.getString("date_of_service");
				String action_performed           = rs.getString("action_performed");
				String service_notes              = rs.getString("service_notes");
				String next_service_date          = rs.getString("next_service_date");
				String action_performed_by        = rs.getString("action_performed_by");
				String future_service_notes       = rs.getString("future_service_notes");
				String previous_building_if_moved = rs.getString("building_name");
				int previous_room_if_moved        = rs.getInt("previous_room_if_moved");
				int current_relative_humidity     = rs.getInt("current_relative_humidity");
				int current_relative_temperature  = rs.getInt("current_relative_temperature");

				System.out.println( "byui_piano_id = "                + byui_piano_id );
				System.out.println( "date_of_service = "              + date_of_service );
				System.out.println( "action_performed = "             + action_performed );
				System.out.println( "service_notes = "                + service_notes );
				System.out.println( "next_service_date = "            + next_service_date );
				System.out.println( "action_performed_by = "          + action_performed_by );
				System.out.println( "future_service_notes = "         + future_service_notes );
				System.out.println( "previous_building_if_moved = "   + previous_building_if_moved );
				System.out.println( "previous_room_if_moved = "       + previous_room_if_moved );
				System.out.println( "current_relative_humidity = "    + current_relative_humidity );
				System.out.println( "current_relative_temperature = " + current_relative_temperature );  
				System.out.println();
			}
			rs.close();
			stmt.close();
			c.close();
		} 
		catch (Exception e) 
		{
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		System.out.println("Operation done successfully");
	}	

	public List<Piano> theBigDump()
	{
		Connection c = null;
		Statement stmt = null;
		try 
		{
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:test.db");
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");
			List<Piano> pianoData = new ArrayList<Piano>();

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery( "SELECT p.byui_piano_id "
					                        + ",      p.mfg_serial "
					                        + ",      p.year "
					                        + ",      (SELECT Date('now') - year) AS age "
					                        + ",      b.building_name "
					                        + ",      room_number "
					                        + ",      rt.room_type_text "
					                        + ",      pc.condition_text "
					                        + ",      pmk.make_name "
					                        + ",      pm.model_name "
					                        + ",      pt.type_text "
					                        + ",      p.cost "
					                        + ",      psh.date_of_service "
					                        + ",      psh.action_performed "
					                        + ",      psh.service_notes "
					                        + ",      psh.next_service_date "
					                        + ",      psh.action_performed_by "
					                        + ",      psh.future_service_notes "
					                        + ",      bl.building_name  AS previous_building_if_moved "
					                        + ",      psh.previous_room_if_moved "
					                        + ",      psh.current_relative_humidity "
					                        + ",      psh.current_relative_temperature "
					                        + "FROM piano p "
					                        + "LEFT OUTER JOIN building b ON b.byui_building_id = p.building_id "
					                        + "LEFT OUTER JOIN piano_condition pc ON pc.condition_id = p.condition_id "
					                        + "LEFT OUTER JOIN piano_make pmk ON pmk.make_id = p.make_id "
					                        + "LEFT OUTER JOIN room_type rt ON rt.room_type_id = p.room_type_id "
					                        + "LEFT OUTER JOIN piano_type pt ON pt.type_id = p.type_id "
					                        + "LEFT OUTER JOIN piano_model pm ON pm.model_id = p.model_id "
					                        + "LEFT OUTER JOIN piano_service_history psh ON psh.byui_piano_id = p.byui_piano_id "
					                        + "LEFT OUTER JOIN building bl ON psh.previous_building_if_moved = bl.byui_building_id; "
					                        + "");
			
			while (rs.next()) 
			{
				int byui_piano_id                 = rs.getInt("byui_piano_id");
				String make_name                  = rs.getString("make_name");
				String model_name                 = rs.getString("model_name");
				String type_text                  = rs.getString("type_text");
				String mfg_serial                 = rs.getString("mfg_serial");
				int year                          = rs.getInt("year");
				int age                           = rs.getInt("age");
				String building_name              = rs.getString("building_name");
				int room_number                   = rs.getInt("room_number");
				String room_type_text             = rs.getString("room_type_text");
				int condition_text                = rs.getInt("condition_text");
				float cost                        = rs.getFloat("cost");
				String date_of_service            = rs.getString("date_of_service");
				String action_performed           = rs.getString("action_performed");
				String service_notes              = rs.getString("service_notes");
				String next_service_date          = rs.getString("next_service_date");
				String action_performed_by        = rs.getString("action_performed_by");
				String future_service_notes       = rs.getString("future_service_notes");
				String previous_building_if_moved = rs.getString("previous_building_if_moved");
				int previous_room_if_moved        = rs.getInt("previous_room_if_moved");
				int current_relative_humidity     = rs.getInt("current_relative_humidity");
				int current_relative_temperature  = rs.getInt("current_relative_temperature");
				
				

				System.out.println( "byui_piano_id = "                + byui_piano_id );
				System.out.println( "make_name = "                    + make_name );
				System.out.println( "model_name = "                   + model_name );
				System.out.println( "type_text = "                    + type_text );
				System.out.println( "mfg_serial = "                   + mfg_serial );
				System.out.println( "year = "                         + year );
				System.out.println( "age = "                          + age );
				System.out.println( "building_name = "                + building_name );
				System.out.println( "room_number = "                  + room_number );
				System.out.println( "room_type_text = "               + room_type_text );
				System.out.println( "condition_text = "               + condition_text );
				System.out.println( "cost = "                         + cost );         
				System.out.println( "date_of_service = "              + date_of_service );
				System.out.println( "action_performed = "             + action_performed );
				System.out.println( "service_notes = "                + service_notes );
				System.out.println( "next_service_date = "            + next_service_date );
				System.out.println( "action_performed_by = "          + action_performed_by );
				System.out.println( "future_service_notes = "         + future_service_notes );
				System.out.println( "previous_building_if_moved = "   + previous_building_if_moved );
				System.out.println( "previous_room_if_moved = "       + previous_room_if_moved );
				System.out.println( "current_relative_humidity = "    + current_relative_humidity );
				System.out.println( "current_relative_temperature = " + current_relative_temperature );  
				
				System.out.println();

				pianoData.add(new Piano(piano_sk, byui_piano_id, make_id, model_id, 
						                type_id, mfg_serial, year, building_id, room_number, 
						                room_type_id, condition_id, cost, date_of_service,
						                action_performed, service_notes, action_performed_by,
						                future_service_notes, previous_building_if_moved,
						                previous_room_if_moved, current_relative_temperature,
						                current_relative_humidity));

			
			}
			rs.close();
			stmt.close();
			c.close();
			
			return pianoData;
		} 
		catch (Exception e) 
		{
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		System.out.println("Operation done successfully");
		return null;
	}	

	public void queryPianoMake()
	{
		Connection c = null;
		Statement stmt = null;
		try 
		{
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection(dataSource);
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery( "SELECT * FROM piano_make;" );
			while (rs.next()) 
			{
				int makeId = rs.getInt("make_id");
				String  makeName = rs.getString("make_name");

				System.out.println( "ID        = " + makeId );
				System.out.println( "MAKE_NAME = " + makeName );
				System.out.println("");
			}
			rs.close();
			stmt.close();
			c.close();
		} 
		catch (Exception e) 
		{
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		System.out.println("Operation done successfully");
	}
	public void queryPianoModel()
	{
		Connection c = null;
		Statement stmt = null;
		try 
		{
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection(dataSource);
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery( "SELECT * FROM piano_model;" );
			while (rs.next()) 
			{
				int modelId       = rs.getInt("model_id");
				String modelName = rs.getString("model_name");
				String modelDesc  = rs.getString("model_description");

				System.out.println("ID:                = " + modelId);
				System.out.println("MODEL_NAME:        = " + modelName);
				System.out.println("MODEL_DESCRIPTION: = " + modelDesc);
				System.out.println("");
			}
			rs.close();
			stmt.close();
			c.close();
		} 
		catch (Exception e) 
		{
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		System.out.println("Operation done successfully");		
	}
	public void queryPianoType()
	{
		Connection c = null;
		Statement stmt = null;
		try 
		{
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection(dataSource);
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery( "SELECT * FROM piano_type;" );
			while (rs.next()) 
			{
				int typeID       = rs.getInt("type_id");
				String typeText = rs.getString("type_text");

				System.out.println("ID:        = " + typeID);
				System.out.println("TYPE_TEXT: = " + typeText);
				System.out.println("");
			}
			rs.close();
			stmt.close();
			c.close();
		} 
		catch (Exception e) 
		{
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		System.out.println("Operation done successfully");
	}
	public void queryPianoCondition()
	{
		Connection c = null;
		Statement stmt = null;
		try 
		{
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection(dataSource);
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery( "SELECT * FROM piano_condition;" );
			while (rs.next()) 
			{
				int    conditionID   = rs.getInt("condition_id");
				String conditionText = rs.getString("condition_text");

				System.out.println("ID:             = " + conditionID);
				System.out.println("CONDITION_TEXT: = " + conditionText);
				System.out.println("");
			}
			rs.close();
			stmt.close();
			c.close();
		} 
		catch (Exception e) 
		{
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		System.out.println("Operation done successfully");
	}
	public void queryBuilding()
	{
		Connection c = null;
		Statement stmt = null;
		try 
		{
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection(dataSource);
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery( "SELECT * FROM building;" );
			while (rs.next()) 
			{
				int    byuiBuildingID = rs.getInt("byui_building_id");
				String buildingName   = rs.getString("building_name");

				System.out.println("ID:            = " + byuiBuildingID);
				System.out.println("BUILDING_NAME: = " + buildingName);
				System.out.println("");
			}
			rs.close();
			stmt.close();
			c.close();
		} 
		catch (Exception e) 
		{
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		System.out.println("Operation done successfully");
	}
	public void queryRoomType()
	{
		Connection c = null;
		Statement stmt = null;
		try 
		{
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection(dataSource);
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery( "SELECT * FROM room_type;" );
			while (rs.next()) 
			{
				int    roomTypeId   = rs.getInt("room_type_id");
				String roomTypeText = rs.getString("room_type_text");

				System.out.println("ID:        = " + roomTypeId);
				System.out.println("ROOM_TYPE: = " + roomTypeText);
				System.out.println("");
			}
			rs.close();
			stmt.close();
			c.close();
		} 
		catch (Exception e) 
		{
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		System.out.println("Operation done successfully");
	}

	public void queryPianoServiceHistory()
	{
		Connection c = null;
		Statement stmt = null;
		try 
		{
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection(dataSource);
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery( "SELECT * FROM piano_service_history;" );
			while ( rs.next() ) 
			{
				int job_id_pk                     = rs.getInt("job_id_pk");
				int byui_piano_id                 = rs.getInt("byui_piano_id");
				String date_of_service            = rs.getString("date_of_service");
				String action_performed           = rs.getString("action_performed");
				String service_notes              = rs.getString("service_notes");
				String next_service_date          = rs.getString("next_service_date");
				String action_performed_by        = rs.getString("action_performed_by");
				String future_service_notes       = rs.getString("future_service_notes");
				String previous_building_if_moved = rs.getString("previous_building_if_moved");
				int previous_room_if_moved        = rs.getInt("previous_room_if_moved");
				int current_relative_humidity     = rs.getInt("current_relative_humidity");
				int current_relative_temperature  = rs.getInt("current_relative_temperature");

				System.out.println( "job_id_pk = "                    + job_id_pk );
				System.out.println( "byui_piano_id = "                + byui_piano_id );
				System.out.println( "date_of_service = "              + date_of_service );
				System.out.println( "action_performed = "             + action_performed );
				System.out.println( "service_notes = "                + service_notes );
				System.out.println( "next_service_date = "            + next_service_date );
				System.out.println( "action_performed_by = "          + action_performed_by );
				System.out.println( "future_service_notes = "         + future_service_notes );
				System.out.println( "previous_building_if_moved = "   + previous_building_if_moved );
				System.out.println( "previous_room_if_moved = "       + previous_room_if_moved );
				System.out.println( "current_relative_humidity = "    + current_relative_humidity );
				System.out.println( "current_relative_temperature = " + current_relative_temperature );  
				System.out.println();
			}
			rs.close();
			stmt.close();
			c.close();
		} 
		catch (Exception e) 
		{
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		System.out.println("Operation done successfully");
	}
		


	public List<Piano> queryPiano()
	{
		Connection c = null;
		Statement stmt = null;
		try 
		{
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite::resource:test.db");
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");
			List<Piano> pianoData = new ArrayList<Piano>();

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery( "SELECT * FROM piano;" );
			while ( rs.next() ) 
			{
				int piano_sk      = rs.getInt("piano_sk");
				int byui_piano_id = rs.getInt("byui_piano_id");
				int make_id       = rs.getInt("make_id");
				int model_id      = rs.getInt("model_id");
				int type_id       = rs.getInt("type_id");
				String mfg_serial = rs.getString("mfg_serial");
				int year          = rs.getInt("year");
				int building_id   = rs.getInt("building_id");
				int room_number   = rs.getInt("room_number");
				int room_type_id  = rs.getInt("room_type_id");
				int condition_id  = rs.getInt("condition_id");
				float cost        = rs.getFloat("cost");

				pianoData.add(new Piano(piano_sk, byui_piano_id, make_id, model_id, 
						type_id, mfg_serial, year, building_id, room_number, 
						room_type_id, condition_id, cost));
				
			}
			
			rs.close();
			stmt.close();
			c.close();
			
			return pianoData;
		} 
		catch (Exception e) 
		{
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		System.out.println("Operation done successfully");
		return null;
	}

	public static void main(String args[])
	{
		DataInteract dataInt = new DataInteract(); 

		//dataInt.insertPiano(43250, 1, 1, 1, "'A80200'", 2002, 21, 120, 3, 2, 1500.0f);
		//dataInt.insertPianoServiceHistory(43250, "'2013-11-30 13:00:00'", "'Pedel Change'", "'Piano seemed out of tune, needs tuning'", "'2013-12-30'", "'Armstrong, Brian'", "'Needs Tuning'", 12, 105, 30, 70);
		//dataInt.updatePiano("'Year'", "'2000'", 101554);
		//dataInt.deletePiano(43250);
		//dataInt.updatePianoServiceHistory("'action_performed_by'", "'Austin Tooley'", 1);
		//dataInt.insertPianoServiceHistory(43250, "'2013-12-01 11:00:00'", "'Tuning'", "'Tuning successful'", "'2014-1-15'", "'Heyen, Kenneth'", "'Replace Pedels'", 12, 105, 30, 70);
		//dataInt.deletePianoServiceHistory(1);
		//dataInt.insertPianoType("'BriansGreatPianos'");
		//dataInt.updatePianoType("'type_text'", "'AustinPianos'", 6);
		//dataInt.deletePianoType("'BriansGreatPianos'");
		//dataInt.insertPianoModel("'AAAAAAA'");
		//dataInt.updatePianoModel("'model_name'", "'BBBBB'", "'AAAAAAA'");
		//dataInt.deletePianoModel("'BBBBB'");
		//dataInt.insertPianoMake("'BrianPiano'");
		//dataInt.updatePianoMake("'make_name'", "'AustinPiano'", "'BrianPiano'");
		//dataInt.deletePianoMake("'AustinPiano'");
		//dataInt.insertPianoCondition("'Wierd Condition'");
		//dataInt.updatePianoCondition("'condition_text'", "'Wierder Condition'", "'Wierd Condition'");
		//dataInt.deletePianoCondition("'Wierder Condition'");
		//dataInt.insertBuilding("'The Armstrong'");
		//dataInt.updateBuilding("'building_name'", "'The Tooley'", "'The Armstrong'");
		//dataInt.deleteBuilding("'The Tooley'");
		//dataInt.insertRoomType("'Odd Room'");
		//dataInt.updateRoomType("'room_type_text'", "'Really Odd Room'", "'Odd Room'");
		//dataInt.deleteRoomType("'Really Odd Room'");
//		dataInt.queryPianoMake();
//		dataInt.queryPianoModel();
//		dataInt.queryPianoType();
//		dataInt.queryPianoCondition();
//		dataInt.queryBuilding();
//		dataInt.queryRoomType();
//		dataInt.queryPiano();
//		dataInt.queryPianoServiceHistory();
//		dataInt.pianoServiceHistoryDumpQuery();
//		dataInt.pianoDumpQuery();
		dataInt.theBigDump();
	}
}
