<%@ page import="com.example.elearning.Model.*" %>
<%
    Account account = (Account) request.getSession().getAttribute("account");
    Student student = (Student) request.getSession().getAttribute("user");
%>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>Home</title>

    <!-- Meta Tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="author" content="Webestica.com">
    <meta name="description" content="Bootstrap based News, Magazine and Blog Theme">

    <!-- Dark mode -->
    <script>
        const storedTheme = localStorage.getItem('theme')

        const getPreferredTheme = () => {
            if (storedTheme) {
                return storedTheme
            }
            return window.matchMedia('(prefers-color-scheme: dark)').matches ? 'dark' : 'light'
        }

        const setTheme = function (theme) {
            if (theme === 'auto' && window.matchMedia('(prefers-color-scheme: dark)').matches) {
                document.documentElement.setAttribute('data-bs-theme', 'dark')
            } else {
                document.documentElement.setAttribute('data-bs-theme', theme)
            }
        }

        setTheme(getPreferredTheme())

        window.addEventListener('DOMContentLoaded', () => {
            var el = document.querySelector('.theme-icon-active');
            if (el != 'undefined' && el != null) {
                const showActiveTheme = theme => {
                    const activeThemeIcon = document.querySelector('.theme-icon-active use')
                    const btnToActive = document.querySelector(`[data-bs-theme-value="${theme}"]`)
                    const svgOfActiveBtn = btnToActive.querySelector('.mode-switch use').getAttribute('href')

                    document.querySelectorAll('[data-bs-theme-value]').forEach(element => {
                        element.classList.remove('active')
                    })

                    btnToActive.classList.add('active')
                    activeThemeIcon.setAttribute('href', svgOfActiveBtn)
                }

                window.matchMedia('(prefers-color-scheme: dark)').addEventListener('change', () => {
                    if (storedTheme !== 'light' || storedTheme !== 'dark') {
                        setTheme(getPreferredTheme())
                    }
                })

                showActiveTheme(getPreferredTheme())

                document.querySelectorAll('[data-bs-theme-value]')
                    .forEach(toggle => {
                        toggle.addEventListener('click', () => {
                            const theme = toggle.getAttribute('data-bs-theme-value')
                            localStorage.setItem('theme', theme)
                            setTheme(theme)
                            showActiveTheme(theme)
                        })
                    })

            }
        })

    </script>

    <!-- Favicon -->
    <link rel="shortcut icon" href="assets/images/favicon.ico">

    <!-- Google Font -->
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link
            href="https://fonts.googleapis.com/css2?family=Nunito+Sans:wght@400;700&family=Rubik:wght@400;500;700&display=swap"
            rel="stylesheet">

    <!-- Plugins CSS -->
    <link rel="stylesheet" type="text/css" href="assets/vendor/font-awesome/css/all.min.css">
    <link rel="stylesheet" type="text/css" href="assets/vendor/bootstrap-icons/bootstrap-icons.css">
    <link rel="stylesheet" type="text/css" href="assets/vendor/tiny-slider/tiny-slider.css">
    <link rel="stylesheet" type="text/css" href="assets/vendor/plyr/plyr.css">

    <!-- Theme CSS -->
    <link id="style-switch" rel="stylesheet" type="text/css" href="assets/css/style.css">

</head>

<body>
<header class="navbar-light navbar-sticky header-static border-bottom navbar-dashboard">
    <!-- Logo Nav START -->
    <nav class="navbar navbar-expand-xl">
        <div class="container">
            <!-- Logo START -->
            <a class="navbar-brand me-3" href="index.html">
                <img class="navbar-brand-item light-mode-item" src="assets/images/logo.svg" alt="logo">
                <img class="navbar-brand-item dark-mode-item" src="assets/images/logo-light.svg" alt="logo">
            </a>
            <!-- Logo END -->

            <!-- Responsive navbar toggler -->
            <button class="navbar-toggler ms-auto" type="button" data-bs-toggle="collapse"
                    data-bs-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false"
                    aria-label="Toggle navigation">
                <span class="text-body h6 d-none d-sm-inline-block">Menu</span>
                <span class="navbar-toggler-icon"></span>
            </button>

            <!-- Main navbar START -->
            <div class="collapse navbar-collapse" id="navbarCollapse">
                <ul class="navbar-nav navbar-nav-scroll mx-auto">

                    <li class="nav-item"><a class="nav-link" href="home.jsp"><i
                            class="bi bi-house-door me-1"></i>Home</a></li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="pagesMenu" data-bs-toggle="dropdown"
                           aria-haspopup="true" aria-expanded="false"><i class="bi bi-book me-1"></i>Courses</a>
                        <ul class="dropdown-menu" aria-labelledby="pagesMenu">
                            <li><a class="dropdown-item" href="/course-registration">Course registration</a>
                            </li>
                            <li><a class="dropdown-item" href="/course-statics">View Courses</a>
                            </li>
                        </ul>
                    </li>
                </ul>

            </div>
            <!-- Main navbar END -->

            <!-- Nav right START -->
            <div class="nav flex-nowrap align-items-center">
                <!-- Profile dropdown START -->
                <div class="nav-item ms-2 ms-md-3 dropdown">
                    <!-- Avatar -->
                    <a class="avatar avatar-sm p-0" href="#" id="profileDropdown" role="button"
                       data-bs-auto-close="outside" data-bs-display="static" data-bs-toggle="dropdown"
                       aria-expanded="false">
                        <img class="avatar-img rounded-circle" src="assets/images/avatar/03.jpg" alt="avatar">
                    </a>

                    <!-- Profile dropdown START -->
                    <ul class="dropdown-menu dropdown-animation dropdown-menu-end shadow pt-3"
                        aria-labelledby="profileDropdown">
                        <!-- Profile info -->
                        <li class="px-3">
                            <div class="d-flex align-items-center">
                                <!-- Avatar -->
                                <div class="avatar me-3">
                                    <img class="avatar-img rounded-circle shadow" src="assets/images/avatar/03.jpg"
                                         alt="avatar">
                                </div>
                                <div>
                                    <a class="h6 mt-2 mt-sm-0" href="#"> Louis Ferguson</a>
                                    <p class="small m-0">example@gmail.com</p>
                                </div>
                            </div>
                            <hr>
                        </li>
                        <!-- Links -->
                        <li><a class="dropdown-item" href="#"><i class="bi bi-power fa-fw me-2"></i>Sign Out</a>
                        </li>
                        <li class="dropdown-divider mb-2"></li>
                    </ul>
                    <!-- Profile dropdown END -->
                </div>
                <!-- Profile dropdown END -->

                <!-- Nav right END -->
            </div>
        </div>
    </nav>
    <!-- Logo Nav END -->
</header>


<!-- **************** MAIN CONTENT START **************** -->
<main>

    <!-- =======================
Main contain START -->
    <section class="py-4">
        <div class="container">


            <div class="row g-4 justify-content-center">
                <!-- Left sidebar START -->
                <div class="col-lg-7 col-xxl-8">
                    <!-- Profile START -->
                    <div class="card border mb-4">
                        <div class="card-header border-bottom p-3">
                            <h5 class="card-header-title mb-0">Profile details</h5>
                        </div>
                        <div class="card-body">
                            <!-- Full name -->
                            <div class="mb-3">
                                <label class="form-label">Full name</label>
                                <div class="input-group">
                                    <input type="text" class="form-control" value="<%=student.getFirstName()%>"
                                           placeholder="First name">
                                    <input type="text" class="form-control" value="<%=student.getLastName()%>"
                                           placeholder="Last name">
                                </div>
                            </div>
                            <!-- Username -->
                            <div class="mb-3">
                                <div class="input-group">
                                    <span class="input-group-text">Username</span>
                                    <input type="text" class="form-control" value="<%=account.getUsername()%>">
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- Profile END -->

                </div>
            </div>
        </div>
    </section>
    <!-- =======================
Main contain END -->

</main>
<!-- **************** MAIN CONTENT END **************** -->

<!-- =======================
JS libraries, plugins and custom scripts -->

<!-- Bootstrap JS -->
<script src="assets/vendor/bootstrap/dist/js/bootstrap.bundle.min.js"></script>

<!-- Vendors -->
<script src="assets/vendor/tiny-slider/tiny-slider.js"></script>
<script src="assets/vendor/sticky-js/sticky.min.js"></script>
<script src="assets/vendor/plyr/plyr.js"></script>

<!-- Template Functions -->
<script src="assets/js/functions.js"></script>
</body>

</html>