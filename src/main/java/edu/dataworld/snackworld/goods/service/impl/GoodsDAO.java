package edu.dataworld.snackworld.goods.service.impl;

import edu.dataworld.egov.cmm.service.impl.EgovComAbstractDAO;
import edu.dataworld.snackworld.common.Search;
import edu.dataworld.snackworld.goods.service.GoodsVO;
import edu.dataworld.snackworld.user.service.UserVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("GoodsDAO")
public class GoodsDAO extends EgovComAbstractDAO {

    public List<GoodsVO> retrieve(Search search){
        return selectList("goods.retrieve", search);
    }
    public void insert(GoodsVO vo) {
        insert("goods.insert", vo);
    }
    public void update(GoodsVO vo) {
        update("goods.update", vo);
    }

    public void fileUpload(GoodsVO vo) {}

    public GoodsVO selectById(String gdsId){
        return selectOne("goods.selectById", gdsId);
    }


    public int goodsCnt() {
        return selectOne("goods.goodsCnt");
    }
    public void setRowNum() { update("goods.setRowNum"); }

}
