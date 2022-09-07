<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="./css/default.css" rel="stylesheet" type="text/css">
<link href="./css/subpage.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="script.js"></script>
<script type="text/javascript" src="script.js"></script>
<script type="text/javascript" src="./js/script.js" charset="utf-8"></script>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
function functionA() {
	alert("회원 가입을 축하합니다.");
/* 	location.href="./main.ma"; */
}



    function sample6_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if(data.userSelectedType === 'R'){
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if(extraAddr !== ''){
                        extraAddr = ' (' + extraAddr + ')';
                    }
                    // 조합된 참고항목을 해당 필드에 넣는다.
                    document.getElementById("sample6_extraAddress").value = extraAddr;
                
                } else {
                    document.getElementById("sample6_extraAddress").value = '';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('sample6_postcode').value = data.zonecode;
                document.getElementById("sample6_address").value = addr;
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById("sample6_detailAddress").focus();
            }
        }).open();
    }///짚코드
    //

    function idCheck(id){
	if(id == ""){
		alert("아이디를 입력해 주세요.");
		document.regForm.id.focus();
	}else{
		url="./member/IdCheck.jsp?id=" + id;
		window.open(url,"post","width=300,height=150");
	}
}
    function inputCheck(){
    	if(document.regForm.id.value==""){
    		alert("아이디를 입력해 주세요.");
    		document.regForm.id.focus();
    		return;
    	}
    	if(document.regForm.passwd.value==""){
    		alert("비밀번호를 입력해 주세요.");
    		document.regForm.passwd.focus();
    		return;
    	}
    	if(document.regForm.repasswd.value==""){
    		alert("비밀번호를 확인해 주세요");
    		document.regForm.repasswd.focus();
    		return;
    	}
    	if(document.regForm.passwd.value != document.regForm.repasswd.value){
    		alert("비밀번호가 일치하지 않습니다.");
    		document.regForm.repasswd.focus();
    		return;
    	}
    	if(document.regForm.name.value==""){
    		alert("이름을 입력해 주세요.");
    		document.regForm.name.focus();
    		return;
    	}}
    //
</script>
</head>
<body>
<div id="wrap">
<!-- 헤더들어가는 곳 -->
<jsp:include page="../inc/top.jsp"></jsp:include>
<!-- 헤더들어가는 곳 -->

<!-- 본문들어가는 곳 -->
<!-- 본문메인이미지 -->
<div id="sub_img_member"></div>
<!-- 본문메인이미지 -->
<!-- 왼쪽메뉴 -->
<nav id="sub_menu">
<ul>
<li><a href="./login.me">로그인</a></li>
<li><a href="./join.me">회원가입</a></li>
</ul>
</nav>
<!-- 왼쪽메뉴 -->
<!-- 본문내용 -->
<article>
<h1>회원가입 </h1>
<form action="./MemberJoinAction.me" method="post"   id="join">
<table border ="1">
 <tr> 
            <td colspan="3"><h2>회원 가입란</h2></td>
          </tr>
          <tr> 
            <td width="16%">아이디</td>
            <td width="57%"><input type="text" name="id" size="15">
		                     <input type="button" value="ID중복확인"  onClick="idCheck(this.form.id.value)"></td>
            <td width="27%">아이디를 적어 주세요.</td>
          </tr>
          <tr> 
            <td>패스워드</td>
            <td> <input type="password"  id="_pw" name="_pw" size="15"> </td>
            <td>패스워드를 적어주세요.</td>
          </tr>
          <tr> 
            <td>패스워드 확인</td>
            <td> <input type="password"  id="_pw2" name="_pw2" size="15" > </td>
            <td>패스워드를 확인합니다.</td>
          </tr>
          <tr> 
            <td>이름</td>
            <td> <input type="text" id="_name" name="name" size="15" > </td>
            <td>고객실명을 적어주세요.</td>
          </tr>
		  <tr> 
            <td>주민등록번호</td>
            <td> <input type="text"  name="pin1" size="6" maxlength="6"
			onKeyUp="if(this.value.length==6)regForm.num2.focus()">-
			     <input type="password" name="pin2" size="7" maxlength="7"
				  onKeyUp="if(this.value.length==7)regForm.email.focus();"></td>
            <td>주민등록번호를 적어주세요.</td>
          </tr>
          <tr> 
            <td>이메일</td>
            <td> <input type="text"  id="_mail" name="email" size="27" > </td>
            <td>이메일을 적어주세요.</td>
          </tr>
          <tr>  
            <td>전화번호</td>
            <td> <input type="text" name="tel" size="20"> </td>
            <td>연락처를 적어 주세요.</td>
          </tr>
		  <tr>  
            <td>우편번호</td>
            <td><input type="text" name="zipcode" id="sample6_postcode" placeholder="우편번호">
				<input type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기"></td>
				
            <td>우편번호를 검색 하세요.</td>
            
          </tr>
		  <tr>  
            <td>주소</td>
            <td>
            <input type="text" name="address" id="sample6_address" placeholder="주소">
			<input type="text" id="sample6_extraAddress" placeholder="(동이름_)">
            <input type="text" name="address2" id="sample6_detailAddress" placeholder="상세주소"></td>
            <td>주소를 적어 주세요.</td>
          </tr>
        
		  <tr>  
            <td>직업</td>
            <td><select name="job">
 					<option value="0" selected> 직업을 선택하세요.</option>
 					<option value="회사원">회사원</option>
 					<option value="연구전문직" >연구전문직</option>
 					<option value="교수학생">교수학생</option>
 					<option value="일반자영업">일반자영업</option>
 					<option value="공무원">공무원</option>
 					<option value="의료인">의료인</option>
 					<option value="법조인">법조인</option>
 					<option value="종교,언론,에술인">종교.언론/예술인</option>
 					<option value="농,축,수산,광업인">농/축/수산/광업인</option>
 					<option value="주부">주부</option>
 					<option value="무직">무직</option>
 					<option value="기타">기타</option>
				  </select></td>
            <td>직업을 선택 하세요.</td>
          </tr>


<tr><td colspan="10"><input type="reset" value="                     다시입력                          "></td></tr>

</table>
<div class="clear"></div>
<div id="buttons">
<input type="submit" value="가입하기" class="submit" onclick="functionA();" >
<input type="button" value="취소하기" class="cancel" onclick=" location.href='./join.me'; ">

</div>
</form>
</article>
<!-- 본문내용 -->
<!-- 본문들어가는 곳 -->

<div class="clear"></div>
<!-- 푸터들어가는 곳 -->
<jsp:include page="../inc/bottom.jsp"></jsp:include>
<!-- 푸터들어가는 곳 -->
</div>
</body>
</html>