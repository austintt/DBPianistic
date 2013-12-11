import java.util.ArrayList;
import java.util.List;


public class Piano 
{
	int byui_piano_id;
	String make_name;
	String model_name;     
	String type_text;      
	String mfg_serial;     
	int year;              
	int age;               
	String building_name;  
	int room_number;      
	String room_type_text; 
	int condition_text;    
	float cost;
	
	List<Event> eventData = new ArrayList<Event>();
	
	Piano(int pByui_piano_id,
			String pMake_name,
			String pModel_name,
			String pType_text,
			String pMfg_serial,
			int pYear,
			int pAge,
			String pBuilding_name,
			int pRoom_number,
			String pRoom_type_text,
			int pCondition_text,
			float pCost, 
			List<Event> pEventData)
	{
		byui_piano_id  				= pByui_piano_id;
		make_name 					= pMake_name;
		model_name 					= pModel_name;    
		type_text 					= pType_text;      
		mfg_serial 					= pMfg_serial;    
		year 						= pYear;              
		age 						= pAge;               
		building_name 				= pBuilding_name;
		room_number 				= pRoom_number;
		room_type_text 				= pRoom_type_text;
		condition_text 				= pCondition_text;  
		cost 						= pCost;
		eventData.addAll(pEventData);
	}
}