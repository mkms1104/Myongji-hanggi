package com.example.mj.fragment;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Hyeeun on 2017-11-28.
 */

public class ExpandableListData {
    public static HashMap<String, List<String>> getData() {
        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();


        List<String> question1 = new ArrayList<String>();
        question1.add("A) 명지한끼 서비스를 이용하기 위해서는 회원가입이 꼭 필요합니다. \n\n" +
                "회원가입 후 로그인에 성공하게 되면 슬라이드 메뉴에 가입 시 작성했던 해당 이름과 함께 나타나는 것을 볼 수 있습니다. \n\n" +
                "해당 이름을 바꾸기 위해서는 슬라이드 메뉴 프로필 사진 밑에 보면, '회원정보수정' 버튼을 클릭 후 " +
                "원하는 이름으로 변경 후 수정버튼을 누르면 앱을 껐다가 켰을 때 변경된 이름으로 업데이트가 되게 됩니다. \n\n" +
                "Update 2017.12.18");

        List<String> question2 = new ArrayList<String>();
        question2.add("A) 명지한끼의 리뷰 작성은 메뉴를 구매한 사람들에게 주어집니다. \n\n하지만 메뉴를 구매 후 일주일 " +
                "이내에 한하여 리뷰를 작성할 수 있습니다. \n\n일주일이 지난 구매내역에는 리뷰작성 버튼이 자동으로 " +
                "사라지니, 이 점 참고하여 구매한 후에는 바로 리뷰 작성을 할 수 있도록 주의해주세요. \n\n" +
                "Update 2017.12.18");

        List<String> question3 = new ArrayList<String>();
        question3.add("A) 메뉴는 매주 토요일에 업로드 됩니다.\n\n" +
                "그렇기 때문에 메뉴는 일주일 단위로 확인이 가능합니다. \n\n" +
                "Update 2017.12.18");

        List<String> question4 = new ArrayList<String>();
        question4.add("A) 메뉴를 여러 개의 수량을 구매하기 위해서는 원하는 메뉴를 모두 선택 후 장바구니에 담아 " +
                "원하는 만큼의 수량을 조절한 후 구매할 수 있습니다. \n\n" +
                "Update 2017.12.18");

        List<String> question5 = new ArrayList<String>();
        question5.add("A) 원하는 메뉴를 즐겨찾기에 등록했을 경우 해당 이용자가 삭제하기 전까지는 " +
                "메뉴가 계속해서 등록되어 있습니다. \n\n즐겨찾기 목록에 있는 메뉴가 나올 시 Push 알림 또한 받을 수 있도록 " +
                "설정할 수 있습니다. \n\n" +
                "Update 2017.12.18");

        List<String> question6 = new ArrayList<String>();
        question6.add("A) 회원가입할 때 설정했던 메뉴에 대한 Push 알림 설정을 해제 하기 위해서는 환경설정에 들어가 " +
                "Push 알림에 해당 해당 스위치 버튼을 Off로 변경할 수 있습니다. \n\n변경사항 또한 어플을 다시 시작했을 경우에 " +
                "적용이 될 수 있습니다. \n\n" +
                "Update 2017.12.18");

        expandableListDetail.put("Q) 로그인한 이름을 수정하고 싶어요.", question1);
        expandableListDetail.put("Q) 일주일이 지난 구매내역의 리뷰를 작성하고 싶어요.", question2);
        expandableListDetail.put("Q) 원하는 날짜의 메뉴를 볼 수가 없어요.", question3);
        expandableListDetail.put("Q) 여러 개의 메뉴를 한꺼번에 구매하고 싶어요.", question4);
        expandableListDetail.put("Q) 즐겨찾기에 메뉴를 등록하면 몇일동안 지속되나요?", question5);
        expandableListDetail.put("Q) Push 알림을 끄고 싶어요.", question6);

        return expandableListDetail;
    }
}