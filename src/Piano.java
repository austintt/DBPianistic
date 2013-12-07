
public class Piano 
{
	int piano_sk;
	int byui_piano_id;
	int make_id;
	int model_id;
	int type_id;
	String mfg_serial;
	int year;
	int building_id;
	int room_number;
	int room_type_id;
	int condition_id;
	float cost;
	
	Piano(int pPiano_sk, int pByui_piano_id, int pMake_id, int pModel_id, int pType_id, 
		  String pMfg_serial, int pYear, int pBuilding_id, int pRoom_number, 
		  int pRoom_type_id, int pCondition_id, float pCost)
	{
		piano_sk = pPiano_sk;
		byui_piano_id = pByui_piano_id;
		make_id = pMake_id;
		model_id = pModel_id;
		type_id = pType_id;
		mfg_serial = pMfg_serial;
		year = pYear;
		building_id = pBuilding_id;
		room_number = pRoom_number;
		room_type_id = pRoom_type_id;
		condition_id = pCondition_id;
		cost = pCost;
	}
}

