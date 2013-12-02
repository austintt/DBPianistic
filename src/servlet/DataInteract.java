import java.sql.*;

public class DataInteract 
{


	public DataInteract()
	{
		//Place holder constructor, there are no actual member variables. 
		//This is basically a database adaptor
	}

	/**
	 * DESC: Insert an item into the piano table. All parameters must be used and have valid data.
	 * @param pBYUIPianoID, pMakeID, pModelID, pTypeID, pMfgSerial, pYear, pAge, pBuildingID
	 *        pRoomNumber, pRoomTypeID, pConditionID, pCost.
	 * 
	 *
	 */
		public void insertPiano(int pBYUIPianoID, int pMakeID, int pModelID,
			int pTypeID, String pMfgSerial, int pYear, 
			int pAge, int pBuildingID, int pRoomNumber, 
			int pRoomTypeID, int pConditionID, float pCost)
	{
		Connection c = null;
		Statement stmt = null;
		try 
		{
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:test.db");
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");
			stmt = c.createStatement();

			String sql = "INSERT INTO PIANO (byui_piano_id, make_id, model_id, type_id, mfg_serial, year, age, building_id, room_number, room_type_id, condition_id, cost) "
					+    "VALUES (" + pBYUIPianoID + "," + pMakeID + "," + pModelID + "," + pTypeID + "," + pMfgSerial + "," + pYear + "," + pAge + "," + pBuildingID + "," + pRoomNumber + "," + pRoomTypeID + "," + pConditionID + "," + pCost + ");";
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



	//TODO: Determine whether or not update method is really needed
	public void updatePiano(String pColumn, String pValue, int pPrimaryKeyValue)
	{
		Connection c = null;
		Statement stmt = null;
		try 
		{
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:test.db");
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			String sql = "UPDATE piano set " + pColumn + " = " + pValue + " where " +  "piano_sk =" + pPrimaryKeyValue+ ";";
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



	public void deletePiano(int byui_piano_id)
	{
		Connection c = null;
		Statement stmt = null;
		try
		{
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:test.db");
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			String sql = "DELETE FROM piano WHERE byui_piano_id = " + byui_piano_id + ";";
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
			c = DriverManager.getConnection("jdbc:sqlite:test.db");
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");
			stmt = c.createStatement();
			String sql = "INSERT INTO piano_service_history (byui_piano_id, date_of_service, action_performed, service_notes, next_service_date, action_performed_by, future_service_notes, previous_building_if_moved, previous_room_if_moved, current_relative_humidity, current_relative_temperature)"
					+ "VALUES (" + pByuiPianoId + "," + pDateOfService + "," + pActionPerformed + "," + pServiceNotes + "," + pNextServiceDate + "," + pActionPerformedBy + "," + pFutureServiceNotes + "," + pPreviousBuildingIfMoved + "," + pPreviousRoomIfMoved + "," + pCurrentRelativeHumidity + "," + pCurrentRelativeTemperature + ");";
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

	public void updatePianoServiceHistory(String pColumn, String pValue, int pPrimaryKeyValue)
	{
		Connection c = null;
		Statement stmt = null;
		try 
		{
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:test.db");
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
	public void deletePianoServiceHistory(int pPrimaryKeyValue)
	{
		Connection c = null;
		Statement stmt = null;
		try
		{
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:test.db");
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

	public void insertPianoType(String pPianoTypeText)
	{
		Connection c = null;
		Statement stmt = null;
		try 
		{
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:test.db");
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
	public void updatePianoType(String pColumn, String pNewValue, String pOldValue)
	{
		Connection c = null;
		Statement stmt = null;
		try 
		{
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:test.db");
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
	public void deletePianoType(String pPianoTypeText)
	{
		Connection c = null;
		Statement stmt = null;
		try
		{
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:test.db");
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

	public void insertPianoModel(String pPianoModelName)
	{
		Connection c = null;
		Statement stmt = null;
		try 
		{
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:test.db");
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
	public void updatePianoModel(String pColumn, String pValue, String pNewPianoModelName)
	{
		Connection c = null;
		Statement stmt = null;
		try 
		{
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:test.db");
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			String sql = "UPDATE piano_model set " + pColumn + " = " + pValue + " where " +  "model_name = " + pNewPianoModelName+ ";";
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
	public void deletePianoModel(String pPianoModelName)
	{
		Connection c = null;
		Statement stmt = null;
		try
		{
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:test.db");
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

	public void insertPianoMake(String pPianoMakeName)
	{
		Connection c = null;
		Statement stmt = null;
		try 
		{
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:test.db");
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
	public void updatePianoMake(String pColumn, String pNewValue, String pOldValue)
	{
		Connection c = null;
		Statement stmt = null;
		try 
		{
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:test.db");
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
	public void deletePianoMake(String pPianoMakeName)
	{
		Connection c = null;
		Statement stmt = null;
		try
		{
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:test.db");
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

	public void insertPianoCondition(String pPianoCondition)
	{
		Connection c = null;
		Statement stmt = null;
		try 
		{
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:test.db");
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
	public void updatePianoCondition(String pColumn, String pNewValue, String pOldValue)
	{
		Connection c = null;
		Statement stmt = null;
		try 
		{
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:test.db");
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
	public void deletePianoCondition(String pPianoCondition)
	{
		Connection c = null;
		Statement stmt = null;
		try
		{
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:test.db");
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

	public void insertBuilding(String pBuildingName)
	{
		Connection c = null;
		Statement stmt = null;
		try 
		{
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:test.db");
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
	public void updateBuilding(String pColumn, String pNewValue, String pOldValue)
	{
		Connection c = null;
		Statement stmt = null;
		try 
		{
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:test.db");
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
	public void deleteBuilding(String pBuildingName)
	{
		Connection c = null;
		Statement stmt = null;
		try
		{
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:test.db");
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

	public void insertRoomType(String pRoomType)
	{
		Connection c = null;
		Statement stmt = null;
		try 
		{
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:test.db");
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
	public void updateRoomType(String pColumn, String pNewValue, String pOldValue)
	{
		Connection c = null;
		Statement stmt = null;
		try 
		{
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:test.db");
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
	public void deleteRoomType(String pRoomType)
	{
		Connection c = null;
		Statement stmt = null;
		try
		{
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:test.db");
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

	public void theBigQuery()
	{

	}
	public void queryPianoMake()
	{
		Connection c = null;
		Statement stmt = null;
		try 
		{
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:test.db");
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
			c = DriverManager.getConnection("jdbc:sqlite:test.db");
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
			c = DriverManager.getConnection("jdbc:sqlite:test.db");
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
			c = DriverManager.getConnection("jdbc:sqlite:test.db");
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
			c = DriverManager.getConnection("jdbc:sqlite:test.db");
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
			c = DriverManager.getConnection("jdbc:sqlite:test.db");
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

	}

	public void queryPiano()
	{
		Connection c = null;
		Statement stmt = null;
		try 
		{
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:test.db");
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

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
				int age           = rs.getInt("age");
				int building_id   = rs.getInt("building_id");
				int room_number   = rs.getInt("room_number");
				int room_type_id  = rs.getInt("room_type_id");
				int condition_id  = rs.getInt("condition_id");
				float cost        = rs.getFloat("cost");

				System.out.println( "piano_sk = " + piano_sk );
				System.out.println( "byui_piano_id = " + byui_piano_id );
				System.out.println( "make_id = " + make_id );
				System.out.println( "model_id = " + model_id );
				System.out.println( "type_id = " + type_id );
				System.out.println( "mfg_serial = " + mfg_serial );
				System.out.println( "year = " + year );
				System.out.println( "age = " + age );
				System.out.println( "building_id = " + building_id );
				System.out.println( "room_number = " + room_number );
				System.out.println( "room_type_id = " + room_type_id );
				System.out.println( "condition_id = " + condition_id );
				System.out.println( "cost = " + cost );         
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

	public static void main( String args[] )
	{
		DataInteract dataInt = new DataInteract(); 

		//dataInt.insertPiano(43250, 1, 1, 1, "'A80200'", 2002, 11, 21, 120, 3, 2, 1500.0f);
		//dataInt.insertPianoServiceHistory(43250, "'2013-11-30 13:00:00'", "'Pedel Change'", "'Piano seemed out of tune, needs tuning'", "'2013-12-30'", "'Armstrong, Brian'", "'Needs Tuning'", 12, 105, 30, 70);
		//dataInt.updatePiano("'Age'", "'17'", 3);
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
		dataInt.queryPianoMake();
		dataInt.queryPianoModel();
		dataInt.queryPianoType();
		dataInt.queryPianoCondition();
		dataInt.queryBuilding();
		dataInt.queryRoomType();
		dataInt.queryPiano();
	}
}
