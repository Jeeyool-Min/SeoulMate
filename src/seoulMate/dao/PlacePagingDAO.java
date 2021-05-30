package seoulMate.dao;

public class PlacePagingDAO {
	String[] guArr = {"전체","강남구","강동구","강북구","강서구","관악구","광진구","구로구","금천구","노원구","도봉구","동대문구","동작구",
	       	"마포구","서대문구","서초구","성동구","성북구","송파구","양천구","영등포구","용산구","은평구","종로구","중구","중랑구"};
	
	public String areaQuery(int areaNo) {
		String gu = guArr[areaNo];
		String query = "select pno from place_locinfo where address like %'"+gu+"'%";
		return query; 
	}
	
	public int getCount() {
		return 0;
	}

	public int getCount(String kwd) {
		return 0;
	}
	
	public String searchPlace() {
		

		
		    
		return null;
	}


}
