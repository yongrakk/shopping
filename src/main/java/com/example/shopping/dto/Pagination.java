package com.example.shopping.dto;

import lombok.Data;

@Data
public class Pagination {
    private int pageSize = 10;      //페이지 당 노출 리스트 수
    private int blockSize = 5;     //페이지 버튼 블럭 수
    private int page = 1;           //현재 페이지
    private int block = 1;          //현재 블럭
    private int totalListCount;     //총 리스트 수
    private int totalPageCount;     //총 페이지 수
    private int totalBlockCount;    //총 블럭 수
    private int startPage = 1;      //시작 페이지
    private int endPage = 1 ;       //끝 페이지
    private int startIndex = 0 ;    //DB 시작 index
    private int prevBlock;          //이전 블럭 끝페이지
    private int nextBlock;          //다음 블럭 시작페이지

    public Pagination(int totalListCount, int page){

        setPage(page);
        setTotalListCount(totalListCount);
        setTotalPageCount((int)Math.ceil(totalListCount * 1.0 / pageSize));
        setTotalBlockCount((int)Math.ceil(totalPageCount * 1.0 / blockSize));
        setBlock((int)Math.ceil((page * 1.0) / blockSize));
        setStartPage((block-1)*blockSize +1);
        setEndPage(startPage+blockSize -1);
        if(endPage > totalPageCount){
            this.endPage = totalPageCount;
        }
        setPrevBlock((block * blockSize) -blockSize);
        if(prevBlock < 1 ){
            this.prevBlock =1 ;
        }
        setNextBlock((block * blockSize) + 1);
        if(nextBlock > totalPageCount ){
            nextBlock = totalPageCount ;
        }
        setStartIndex((page-1) * pageSize);

    }
}
