package edu.dataworld.snackworld.user.web;

import edu.dataworld.snackworld.common.Pagination;
import edu.dataworld.snackworld.common.Search;
import edu.dataworld.snackworld.common.Util;
import edu.dataworld.snackworld.user.service.UserService;
import edu.dataworld.snackworld.user.service.UserVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.registry.infomodel.User;
import java.io.PrintWriter;
import java.util.List;

@Controller
@RequestMapping(value="/user")
public class UserController {

    @Resource(name="UserService")
    private UserService userService;

//    @RequestMapping(value="/userMng.do")
//    public String getUser(@ModelAttribute("searchVO") UserVO vo, ModelMap model) {
//        List<UserVO> userList = userService.retrieve(vo);
//        model.addAttribute("userList", userList);
//        return "/user/userMng.view";
//    }

    @RequestMapping(value="/userMng", method = RequestMethod.GET)
    public String showUserList(
            @RequestParam(required=false,defaultValue="1")int page
            , @RequestParam(required=false,defaultValue="1")int range
            , @RequestParam(required=false)String searchType
            , @RequestParam(required=false)String keyword
            , @ModelAttribute("searchVO") Search search, ModelMap model) {

        model.addAttribute("search", search);
        search.setSearchType(searchType);
        search.setKeyword(keyword);

        int listCnt = userService.userCnt();

        //검색 후 페이지
        search.pageInfo(page, range, listCnt);

        //페이징
        model.addAttribute("pagination", search);

        userService.setRowNum();
        List<UserVO> userList = userService.retrieve(search);
        model.addAttribute("userList", userList);
        model.addAttribute("pageNum", search.getPage());
        return "/user/userMng.view";
    }

    @RequestMapping(value="/userAdd", method = RequestMethod.POST)
    public String addUser(HttpServletRequest req, @ModelAttribute("userVO") UserVO userVO) throws Exception{

        UserVO existUser = userService.getUserByLoginId(userVO.getUserId());

        if(existUser != null) {
            return Util.msgAndBack(req,userVO.getUserId() + "(은)는 이미 사용중인 로그인 아이디 입니다.");
        }

        int joinUserCount = userService.addUser(userVO);
        System.out.println("회원 추가 확인값!!!!!!!! : " + joinUserCount);

        if(joinUserCount == 0) {
            return Util.msgAndBack(req,"회원 추가에 실패하였습니다.");
        }
        return Util.msgAndReplace(req,"회원이 추가되었습니다.", "/user/userMng.view");
    }

    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
    public String deleteUser(HttpServletRequest req, @RequestParam(value="checkBoxArr[]") List<String> checkBoxArr) {

        if(checkBoxArr.size() == 0) {
            return Util.msgAndBack(req,"선택된 회원이 없습니다.");
        }
        System.out.println("회원 지우기 탔삽!!!!:" + checkBoxArr.get(0));

        userService.deleteUser(checkBoxArr);

        return Util.msgAndReplace(req,"회원이 삭제되었습니다.", "/user/userMng.view");
    }

}
