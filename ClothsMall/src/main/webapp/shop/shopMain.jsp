<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Cruella</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/shop/css/style.css" />
<script src="<%=request.getContextPath()%>/shop/clothContent.js"></script>
<script src="https://kit.fontawesome.com/3842b33e0f.js" crossorigin="anonymous"></script>
</head>
<body>
	<div id="header-fix">&nbsp;</div>
	<header>
		<h1 class="logo-bar con-min-width">
			<div class="text-align-center con">
				<span>Cruella</span>
			</div>
		</h1>
		<section class="categories">
			<div class="category-grid">
				<ul>
					<li>
						<div class="dropdown">
							<button class="dropbtn">TOP</button>
							<div class="dropdown-content">
								<a href="<%=request.getContextPath()%>/list.do?cloth_category=1000">SHIRTS</a>
								<a href="<%=request.getContextPath()%>/list.do?cloth_category=1000">MTM</a>
								<a href="<%=request.getContextPath()%>/list.do?cloth_category=1000">BLOUSE</a>
							</div>
						</div>
					</li>
					<li>
						<div class="dropdown">
							<button class="dropbtn">OUTER</button>
							<div class="dropdown-content">
								<a href="<%=request.getContextPath()%>/list.do?cloth_category=3000">JUMPER</a>
								<a href="<%=request.getContextPath()%>/list.do?cloth_category=3000">JARCET</a>
								<a href="<%=request.getContextPath()%>/list.do?cloth_category=3000">COURT</a>
							</div>
						</div>
					</li>
					<li>
						<div class="dropdown">
							<button class="dropbtn">BOTTOM</button>
							<div class="dropdown-content">
								<a href="<%=request.getContextPath()%>/list.do?cloth_category=2000">SLACKS</a>
								<a href="<%=request.getContextPath()%>/list.do?cloth_category=2000">LEGGINGS</a>
								<a href="<%=request.getContextPath()%>/list.do?cloth_category=2000">DENNING</a>
							</div>
						</div>
					</li>
					<li>
						<div class="dropdown">
							<button class="dropbtn">SHOE</button>
							<div class="dropdown-content">
								<a href="<%=request.getContextPath()%>/list.do?cloth_category=4000">SANDLE</a>
								<a href="<%=request.getContextPath()%>/list.do?cloth_category=4000">SNEAKERS</a>
								<a href="<%=request.getContextPath()%>/list.do?cloth_category=4000">HIGH-HEEL</a>
							</div>
						</div>
					</li>
					<li>
						<div class="dropdown">
							<button class="dropbtn">ACCESSORY</button>
							<div class="dropdown-content">
								<a href="<%=request.getContextPath()%>/list.do?cloth_category=5000">HAT</a>
								<a href="<%=request.getContextPath()%>/list.do?cloth_category=5000">BELT</a>
								<a href="<%=request.getContextPath()%>/list.do?cloth_category=5000">JEWELRY</a>
							</div>
						</div>
					</li>
				</ul>


			</div>
		</section>

		<ul class="menu-bar con-min-width">

			</nav>
	</header>
	<!-- Slideshow container -->
	<div class="slideshow-container">

		<!-- Full-width images with number and caption text -->
		<div class="mySlides fade">
			<div class="numbertext">1 / 4</div>
			<img src="shop/images/shoe.jpg" width="100%" height="465">
			<div class="text"></div>
		</div>

		<div class="mySlides fade">
			<div class="numbertext">2 / 4</div>
			<img src="shop/images/cloth.jpg" width="100%" height="465">
			<div class="text"></div>
		</div>

		<div class="mySlides fade">
			<div class="numbertext">3 / 4</div>
			<img src="shop/images/woman.jpg" width="100%" height="465">
			<div class="text"></div>
		</div>

		<div class="mySlides fade">
			<div class="numbertext">4 / 4</div>
			<img src="shop/images/accessory.jpg" width="100%" height="465">
			<div class="text"></div>
		</div>

		<!-- Next and previous buttons -->
		<a class="prev" onclick="plusSlides(-1)">&#10094;</a>
		<a class="next" onclick="plusSlides(1)">&#10095;</a>
	</div>
	<br>

	<!-- The dots/circles -->
	<div style="text-align: center">
		<span class="dot" onclick="currentSlide(1)"></span>
		<span class="dot" onclick="currentSlide(2)"></span>
		<span class="dot" onclick="currentSlide(3)"></span>
		<span class="dot" onclick="currentSlide(4)"></span>
	</div>
	<aside class="side-bar">
		<section class="side-bar__icon-box">
			<section class="side-bar__icon-1">
				<div></div>
				<div></div>
				<div></div>
			</section>
		</section>
		<div id="mySidenav" class="sidenav">
			<a href="shop/showList.jsp" id="category" i class="fa-solid fa-list"></a>
			<a href="/mg/index.do" id="home" i class="fa-solid fa-house-chimney"></a>
			<a href="#" id="mypage" class="fa-solid fa-user"></a>
			<!-- <a href="shop/faqList.jsp" id="faq" i class="fa-solid fa-heart"></a> -->
			<!-- 136~138 승훈 작업  // 136 주석처리-->
			<a href="<%=request.getContextPath()%>/faqList.do" id="faq" i class="fa-solid fa-heart"></a>
		</div>

		<main>
			<section class="hero">
				<div class="hero-content">
					<p>
						<span>
							<b>Cruella</b>
						</span>
						에서
					<hr>
					다양한 스타일을 경험해보세요.
					</p>
				</div>
				<div class="hero-image">
					<a href="list.do?cloth_category=1000">
						<img src="shop/images/woman.jpg" width="200" height="200">
					</a>
					<hr>
				</div>
				<div class="hero-image">
					<a href="list.do?cloth_category=4000">
						<img src="shop/images/shoe.jpg" width="200" height="200">
					</a>
					<hr>
				</div>
				<div class="hero-image">
					<a href="list.do?cloth_category=5000">
						<img src="shop/images/accessory.jpg" width="200" height="200">
					</a>
					<hr>
				</div>
				<div class="hero-image">
					<a href="list.do?cloth_category=5000">
						<img src="shop/images/ring4.JPG" width="200" height="200">
					</a>
					<hr>
				</div>
				<div class="hero-image">
					<a href="list.do?cloth_category=1000">
						<img src="shop/images/cloth.jpg" width="200" height="200">
					</a>
					<hr>
				</div>
				<div class="hero-image">
					<a href="list.do?cloth_category=4000">
						<img src="shop/images/스니커즈4.JPG" width="200" height="200">
					</a>
					<hr>
				</div>
				<div class="hero-image">
					<a href="list.do?cloth_category=5000">
						<img src="shop/images/주얼리1.JPG" width="200" height="200">
					</a>
					<hr>
				</div>
			</section>


			<section class="featured-products">
				<h2></h2>
				<div class="product-grid">
					<!-- 인기 상품 목록 -->
				</div>
			</section>

			<section class="categories">
				<h2></h2>
				<div class="category-grid">
					<!-- 카테고리 목록 -->
				</div>
			</section>
		</main>

		<footer>
			<div class="footer content1">
				<a href="">Download</a>
				<a href="">Personal Information Processing Policy</a>
				<a href="">Copyright Guidelines and Reporting</a>
				<a href="">Refuse to collect email without permission</a>
			</div>
			<div class="footer content2">
				<p>Cruella : 4th Floor, Java 2-gil, Gangnam-gu, Seoul / Without a country code:118</p>
				<p>
					Copyright
					<span>ⓒ</span>
					2024 mrhi, Inc. All right reserved. Contact webmaster for more information. 118(Cyber terrorism)
				</p>
			</div>
		</footer>
</body>
</html>
