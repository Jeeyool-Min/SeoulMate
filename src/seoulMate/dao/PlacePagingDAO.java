package seoulMate.dao;

public class PlacePagingDAO {
	String[] guArr = {"��ü","������","������","���ϱ�","������","���Ǳ�","������","���α�","��õ��","�����","������","���빮��","���۱�",
	       	"������","���빮��","���ʱ�","������","���ϱ�","���ı�","��õ��","��������","��걸","����","���α�","�߱�","�߶���"};
	
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
