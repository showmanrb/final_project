<%--
  Created by IntelliJ IDEA.
  User: Professional
  Date: 03.08.2021
  Time: 10:35
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta http-equiv="x-ua-compatible" content="ie=edge" />
    <title>Material Design for Bootstrap</title>
    <%@include file="/fragment/header.jsp" %>
</head>
<body>
<!--Main Navigation-->
<header>
    <style>
        #intro {
            background-image: url("https://mdbootstrap.com/img/new/fluid/city/018.jpg");
            height: 100vh;
        }

        /* Height for devices larger than 576px */
        @media (min-width: 992px) {
            #intro {
                margin-top: -58.59px;
            }
        }
    </style>
</header>
<!--Main Navigation-->

<!--Main layout-->
<main class="mt-5">
    <div class="container">
        <!--Section: Content-->
        <section>
            <div class="row">
                <div class="col-md-6 gx-5 mb-4">
                    <div class="bg-image hover-overlay ripple shadow-2-strong" data-mdb-ripple-color="light">
                        <img src="https://events.dev.by/ckeditor_assets/pictures/38476/content_java-training_1074x480px_upd.png" class="img-fluid" />
                        <a href="#!">
                            <div class="mask" style="background-color: rgba(251, 251, 251, 0.15);"></div>
                        </a>
                    </div>
                </div>

                <div class="col-md-6 gx-5 mb-4">
                    <h4><strong>Facilis consequatur eligendi</strong></h4>
                    <p class="text-muted">
                        Lorem ipsum dolor sit amet consectetur adipisicing elit. Facilis consequatur
                        eligendi quisquam doloremque vero ex debitis veritatis placeat unde animi laborum
                        sapiente illo possimus, commodi dignissimos obcaecati illum maiores corporis.
                    </p>
                    <p><strong>Doloremque vero ex debitis veritatis?</strong></p>
                    <p class="text-muted">
                        Lorem ipsum dolor sit amet, consectetur adipisicing elit. Quod itaque voluptate
                        nesciunt laborum incidunt. Officia, quam consectetur. Earum eligendi aliquam illum
                        alias, unde optio accusantium soluta, iusto molestiae adipisci et?
                    </p>
                </div>
            </div>
        </section>
        <!--Section: Content-->

        <hr class="my-5" />

        <!--Section: Content-->
        <section class="text-center">
            <h4 class="mb-5"><strong>Facilis consequatur eligendi</strong></h4>

            <div class="row">
                <div class="col-lg-4 col-md-12 mb-4">
                    <div class="card">
                        <div class="bg-image hover-overlay ripple" data-mdb-ripple-color="light">
                            <img src="https://mdbootstrap.com/img/new/standard/nature/184.jpg" class="img-fluid" />
                            <a href="#!">
                                <div class="mask" style="background-color: rgba(251, 251, 251, 0.15);"></div>
                            </a>
                        </div>
                        <div class="card-body">
                            <h5 class="card-title">Card title</h5>
                            <p class="card-text">
                                Some quick example text to build on the card title and make up the bulk of the
                                card's content.
                            </p>
                            <a href="#!" class="btn btn-primary">Button</a>
                        </div>
                    </div>
                </div>

                <div class="col-lg-4 col-md-6 mb-4">
                    <div class="card">
                        <div class="bg-image hover-overlay ripple" data-mdb-ripple-color="light">
                            <img src="https://mdbootstrap.com/img/new/standard/nature/023.jpg" class="img-fluid" />
                            <a href="#!">
                                <div class="mask" style="background-color: rgba(251, 251, 251, 0.15);"></div>
                            </a>
                        </div>
                        <div class="card-body">
                            <h5 class="card-title">Card title</h5>
                            <p class="card-text">
                                Some quick example text to build on the card title and make up the bulk of the
                                card's content.
                            </p>
                            <a href="#!" class="btn btn-primary">Button</a>
                        </div>
                    </div>
                </div>

                <div class="col-lg-4 col-md-6 mb-4">
                    <div class="card">
                        <div class="bg-image hover-overlay ripple" data-mdb-ripple-color="light">
                            <img src="https://mdbootstrap.com/img/new/standard/nature/111.jpg" class="img-fluid" />
                            <a href="#!">
                                <div class="mask" style="background-color: rgba(251, 251, 251, 0.15);"></div>
                            </a>
                        </div>
                        <div class="card-body">
                            <h5 class="card-title">Card title</h5>
                            <p class="card-text">
                                Some quick example text to build on the card title and make up the bulk of the
                                card's content.
                            </p>
                            <a href="#!" class="btn btn-primary">Button</a>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!--Section: Content-->

    </div>
</main>
<!--Main layout-->

<!--Footer-->
<footer class="bg-light text-lg-start">
    <hr class="m-0" />
    <div class="text-center p-3" style="background-color: rgba(0, 0, 0, 0.2);">
        © 2021 Copyright: Ivan Velichko - clean code, bright weddings. +375293211711
    </div>

</footer>
<script type="text/javascript" src="js/mdb.min.js"></script>
<!-- Custom scripts -->
<script type="text/javascript" src="js/script.js"></script>
</body>
</html>