package seoulMate.dto;

import seoulMate.dao.PlacePagingDAO;

public class PagingDTO {
	private final static int pageCnt = 5; //�� ���� ������ ��
	private int blockStartNo; //���� �������� ���� ���� ���� ��������ȣ. 13��������� 11
	private int blockLastNo; //���� �������� ���� ���� ������ ������ ��ȣ. 13��������� 15
	private int lastPageNo; //������ ������ ��ȣ. ��ü �� ���� 20����� ������ ��������ȣ�� 4
	
	public int getBlockStartNo() {	return blockStartNo;}
	public void setBlockStartNo(int blockStartNo) {	this.blockStartNo = blockStartNo;}
	public int getBlockLastNo() {return blockLastNo;}
	public void setBlockLastNo(int blockLastNo) {this.blockLastNo = blockLastNo;}
	public int getLastPageNo() {return lastPageNo;}
	public void setLastPageNo(int lastPageNo) {	this.lastPageNo = lastPageNo;}
	public static int getPagecnt() {return pageCnt;}
	
	//���� �������� ���� block�� ���۰� �� ��ȣ ���
	public void makeBlock(int curPage) {
		int blockNo=0; //�ش� �������� �����ִ� ���� ��ȣ
		
		if(curPage%pageCnt == 0) { //5, 10, 15���������
			blockNo = curPage / pageCnt;
			blockLastNo = blockNo * pageCnt; //�� curPage�� ����
			blockStartNo = blockLastNo - pageCnt +1;
		}else {  //���� 6~9��������� �ι�°��
			blockNo = curPage/pageCnt +1;
			blockLastNo = blockNo * pageCnt;
			blockStartNo = blockLastNo - pageCnt +1;
		}
	}
	
	//�� ��ü ������ ������ ��ȣ
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
	
	//�˻� ��� ������ ������ ��ȣ (�����ε�)
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
