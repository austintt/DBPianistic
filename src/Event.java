public class Event 
{
	String dateOfService;
	String actionPerformed;
	String serviceNote;
	String nextServiceDate;
	String actionPerformedBy;
	String futureServiceNotes;
	String previousBuildingIfMoved;
	String previousRoomIfMoved;
	int currentRelativeHumidity;
	int currentRelativeTemperature;

	Event(String pDateOfService,
		  String pActionPerformed,
		  String pServiceNote,
		  String pNextServiceDate,
		  String pActionPerformedBy,
		  String pFutureServiceNotes,
		  String pPreviousBuildingIfMoved,
		  String pPreviousRoomIfMoved,
		  int pCurrentRelativeHumidity,
		  int pCurrentRelativeTemperature)
	{
		dateOfService 				= pDateOfService;
		actionPerformed 			= pActionPerformed;
		serviceNote 				= pServiceNote;
		nextServiceDate 			= pNextServiceDate;
		actionPerformedBy 			= pActionPerformedBy;
		futureServiceNotes 			= pFutureServiceNotes;
		previousBuildingIfMoved 	= pPreviousBuildingIfMoved;
		previousRoomIfMoved 		= pPreviousRoomIfMoved;
		currentRelativeHumidity 	= pCurrentRelativeHumidity;
		currentRelativeTemperature 	= pCurrentRelativeTemperature;
	}
}