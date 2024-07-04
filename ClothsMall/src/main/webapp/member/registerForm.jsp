<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="/ClothsMall/member/css/register_style.css" />
<script src="/ClothsMall/member/register.js?ver=2"></script>
<script
	src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>


<div id="regForm" class="box">
		<table>
			<tr>
				<td><label for="member_id">아이디</label></td>
				<td><input id="member_id" name="member_id" type="text"
					size="20" maxlength="10" placeholder="example" autofocus></td>
				<td><button type="button" id="checkId">ID CHECK</button></td>
			</tr>
			<tr>
				<td><label for="member_passwd">비밀번호</label></td>
				<td colspan="2"><input id="member_passwd" name="member_passwd"
					type="password" size="20" placeholder="영문자와 숫자,특수기호 조합 6~16자"
					maxlength="16"></td>
			</tr>
			<tr>
				<td><label for="repass">비밀번호 재입력</label></td>
				<td colspan="2"><input id="repass" name="repass"
					type="password" size="20" placeholder="비밀번호 재입력" maxlength="16"></td>
			</tr>
			<tr>
				<td><label for="member_name">이름</label></td>
				<td colspan="2"><input id="member_name" name="member_name"
					type="text" size="20" placeholder="홍길동" maxlength="5"></td>
			</tr>
			<tr>
				<td><label for="member_postal_code">우편번호</label></td>
				<td><input id="member_postal_code" name="member_postal_code"
					type="text" size="10" placeholder="우편번호 입력" maxlength="6" readonly></td>
				<td><button type="button" id="searchAddress"
						onclick="openDaumPostcode()">주소검색</button></td>
			</tr>
			<tr>
				<td><label for="member_address">주소</label></td>
				<td colspan="2"><input id="member_address"
					name="member_address" type="text" size="30" placeholder="주소 입력"
					maxlength="50" readonly></td>
			</tr>
			<tr>
				<td><label for="member_detailed_address">상세주소</label></td>
				<td colspan="2"><input id="member_detailed_address"
					name="member_detailed_address" type="text" size="30"
					placeholder="상세주소 입력" maxlength="50"></td>
			</tr>
			<tr>
				<td><label>성별</label></td>
				<td colspan="2">
					<div class="radioDiv">
						<label for="male">남성</label><input type="radio" id="male" name="member_gender" value="M"
							checked class="gender"> 
					</div>
					<div class="radioDiv">
						<label for="female">여성</label><input type="radio" id="female" name="member_gender" value="F"
							class="gender"> 
					</div>
				</td>
			</tr>
			<tr>
				<td><label for="member_tel">전화번호</label></td>
				<td colspan="2"><input id="member_tel" name="member_tel"
					type="tel" size="20" placeholder="전화번호 입력" maxlength="13"></td>
			</tr>
			<tr class="label2">
				<td colspan="3">
					<button id="process" onclick="process()" type="button">가입하기</button>
					<button type="button" id="cancel">취소</button>
				</td>
			</tr>
		</table>
</div>