package seoulMate.dto;

import seoulMate.dao.PlacePagingDAO;

public class PagingDTO {
	private final static int pageCnt = 5; //한 블럭당 페이지 수
	private int blockStartNo; //현재 페이지가 속한 블럭의 시작 페이지번호. 13페이지라면 11
	private int blockLastNo; //현재 페이지가 속한 블럭의 마지막 페이지 번호. 13페이지라면 15
	private int lastPageNo; //마지막 페이지 번호. 전체 글 수가 20개라면 마지막 페이지번호는 4
	
	public int getBlockStartNo() {	return blockStartNo;}
	public void setBlockStartNo(int blockStartNo) {	this.blockStartNo = blockStartNo;}
	public int getBlockLastNo() {return blockLastNo;}
	public void setBlockLastNo(int blockLastNo) {this.blockLastNo = blockLastNo;}
	public int getLastPageNo() {return lastPageNo;}
	public void setLastPageNo(int lastPageNo) {	this.lastPageNo = lastPageNo;}
	public static int getPagecnt() {return pageCnt;}
	
	//현재 페이지가 속한 block의 시작과 끝 번호 계산
	public void makeBlock(int curPage) {
		int blockNo=0; //해당 페이지가 속해있는 블럭의 번호
		
		if(curPage%pageCnt == 0) { //5, 10, 15페이지라면
			blockNo = curPage / pageCnt;
			blockLastNo = blockNo * pageCnt; //즉 curPage와 같음
			blockStartNo = blockLastNo - pageCnt +1;
		}else {  //현재 6~9페이지라면 두번째블럭
			blockNo = curPage/pageCnt +1;
			blockLastNo = blockNo * pageCnt;
			blockStartNo = blockLastNo - pageCnt +1;
		}
	}
	
	//글 전체 마지막 페이지 번호
    public void makeLastPageNum() {
        PlacePagingDAO dao = new PlacePagingDAO();
        int total = dao.getCount();

        if( total % pageCnt == 0 ) {
            lastPageNo = (int)Math.floor(total/pageCnt);
        }
        else {
            lastPageNo = (int)Math.floor(total/pageCnt) + 1;
        }
    }
	
	//검색 결과 마지막 페이지 번호 (오버로딩)
    public void makeLastPageNum(String kwd) {
        PlacePagingDAO dao = new PlacePagingDAO();
        int total = dao.getCount(kwd);

        if( total % pageCnt == 0 ) {
            lastPageNo = (int)Math.floor(total/pageCnt);
        }
        else {
            lastPageNo = (int)Math.floor(total/pageCnt) + 1;
        }
    }
}
