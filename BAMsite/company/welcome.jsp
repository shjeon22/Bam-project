<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="./css/default.css" rel="stylesheet" type="text/css">
<link href="./css/subpage.css" rel="stylesheet" type="text/css">

 
</head>
<body>
<div id="wrap">
<!-- 헤더가 들어가는 곳 -->
		<jsp:include page="../inc/top.jsp"/>
<!-- 헤더가 들어가는 곳 -->

<!-- 본문 들어가는 곳 -->
<!-- 서브페이지 메인이미지 -->
<div id="sub_img"></div>
<!-- 서브페이지 메인이미지 -->
<!-- 왼쪽메뉴 -->
<nav id="sub_menu">
<ul>
<li><a href="./map.mp">찾아오시는길</a></li>


</ul>
</nav>
<!-- 왼쪽메뉴 -->
<!-- 내용 -->
<article>
<h1>찾아오시는 길</h1>
<figcaption>지도</figcaption>
</figure>
<jsp:include page="map.html"></jsp:include>
<div><ul><li><h2><b>버스</b></h2></li>
<li><h4>벡스코 : 31, 39, 40, 63, 100, 100-1, 115-1, 141, 181, 200, 307</h4></li>
<li><h4>올림픽교차로 : 31, 39, 40, 63, 100, 100-1, 115-1, 141, 181, 200, 1001</h4></li>
</ul>
</div>
<hr>
<div><ul><li><h2><b>승용차</b></h2></li>
<li><h4>★서울방면
경부고속도로→부산톨게이트→원동IC→해운대방면→올림픽교차로에서 우회전 후 U턴하여 미술관 진입</h4></li>
<li><h4>★마산방면
남해고속도로→부산톨게이트→동서고가도로→해운대방면→올림픽동산삼거리에서 좌회전 후 직진하여 미술관 진입</h4></li>
</ul>
</div>


</article>
<!-- 내용 -->
<!-- 본문 들어가는 곳 -->
<div class="clear"></div>
<!-- 푸터 들어가는 곳 -->
	<jsp:include page="../inc/bottom.jsp"/>
<!-- 푸터 들어가는 곳 -->
</div>
</body>
</html>



