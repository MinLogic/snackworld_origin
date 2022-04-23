package edu.dataworld.snackworld.order.service.impl;

import edu.dataworld.egov.cmm.service.impl.EgovComAbstractDAO;
import edu.dataworld.snackworld.common.Search;
import edu.dataworld.snackworld.order.service.OrderVO;
import edu.dataworld.snackworld.user.service.UserVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("OrderDAO")
public class OrderDAO extends EgovComAbstractDAO {


    public List<OrderVO> cartRetrieve(OrderVO vo) {
        return selectList("order.cartRetrieve", vo);
    }

    public int cartInsert(OrderVO vo) {
        return insert("order.cartInsert", vo);
    }

    public void cartQtyUpdate(OrderVO vo) {
        update("order.cartQtyUpdate", vo);
    }

    public void cartDelete(OrderVO vo) {
        update("order.cartDelete", vo);
    }

    public List<OrderVO> listOption() {
        return selectList("order.listOption");
    }

    public int orderCnt(Search search) {
        return selectOne("order.orderCnt", search);
    }

    public List<OrderVO> orderRetrieve(Search search) {
        return selectList("order.orderRetrieve", search);
    }

    public int orderInsert(OrderVO vo) { return insert("order.orderInsert", vo); }

    public void orderDetailInsert(OrderVO vo){
        insert("order.orderDetailInsert", vo);
    }
}
