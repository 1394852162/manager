<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>SYBoard | Starter</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/font-awesome.min.css">
    <link rel="stylesheet" href="css/ionicons.min.css">
    <link rel="stylesheet" href="dist/css/AdminLTE.css">
    <!-- iCheck -->
    <link rel="stylesheet" href="plugins/iCheck/square/blue.css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <!--
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    -->
    <script src="js/html5shiv.min.js"></script>
    <script src="js/respond.min.js"></script>

    <!-- jQuery 2.2.3 -->
    <script src="plugins/jQuery/jquery-2.2.3.min.js"></script>
    <!-- Bootstrap 3.3.6 -->
    <script src="bootstrap/js/bootstrap.min.js"></script>
    <!-- iCheck -->
    <script src="plugins/iCheck/icheck.min.js"></script>

    <!--<script type="text/javascript" src="js/function-1.0.1.js"></script>-->
    <script type="text/javascript" src="js/function-1.0.1.min.js"></script>
    <![endif]-->
    <style>
        .vertical-center{
            position: absolute;
            top: 25%;
            left: 50%;
            transform: translate(-50%, -50%);
        }
        #captcha{
            height: 30px;
            width:180px;
            display:inline;
        }
        #kaptchaImage{
            height: 30px;
            width:135px;
            display: inline;
        }
        .panel {
            margin-bottom: 0px;
            background-color: white;
            border: 0px solid transparent;
            border-radius: 0px;
            -webkit-box-shadow: 0 0px 0px rgba(0,0,0,.05);
            box-shadow: 0 0px 0px rgba(0,0,0,.05);
        }
        .panel-default>.panel-heading {
            /*color: silver*/
            background-color: white;
            border-color: white;
        }

    </style>
    <script type="text/javascript">
        var statusLang = "cn";
        $(document).ready(function(){
            $('.panel-heading input').on('ifCreated ifClicked ifChanged ifChecked ifUnchecked ifDisabled ifEnabled ifDestroyed', function(event){
                if (event.type == 'ifChecked') {
//                    console.log($(this)[0].defaultValue);
                    statusLang = $(this)[0].defaultValue
                } else {
//                    checkboxes.iCheck('uncheck');
                    statusLang = "cn";
                }
            }).iCheck({
                /*checkboxClass: 'icheckbox_square-blue',
                 radioClass: 'iradio_square-blue',
                 increaseArea: '20%'*/
                checkboxClass: 'icheckbox_square-blue',
                radioClass: 'iradio_square-blue',
                increaseArea: '20%' // optional
            });
        });

        function submitForm(url, data) {
            var eleForm = document.body.appendChild(document.createElement('form'));
            eleForm.action = url;
            for (var property in data)
            {
                var hiddenInput = document.createElement('input');
                hiddenInput.type = 'hidden';
                hiddenInput.name = property;
                hiddenInput.value = data[property];
                eleForm.appendChild(hiddenInput);
            }
            this.eleForm = eleForm;
            if (!submitForm._initialized)
            {
                submitForm.prototype.post = function ()
                {
                    this.eleForm.method = 'post';
                    this.eleForm.submit();
                };
                submitForm._initialized = true;
            }
        }
        $(document).ready(function(){
            $("#btn-login").click(function(){
//                console.log(statusLang);
                var username = $("#username").val();
                var pwd = $("#pwd").val();

//                alert(username);
                $("#btn-login").button('loading');
                $.ajax({
                    type:"GET",
                    url : './employee/login.do',
                    dataType: "json",
                    async:false,
                    cache:false,
                    data:{
                        name: username,
                        password: pwd,
                        enabled: true
                    },
                    success:function(data,textStatus){
                        if(data){
//                            console.log(data);
                            var notecode = data.IfExit;
//                            alert(notecode);
                            $("#btn-login").button('reset');
                            if(notecode == "true"){
                                document.location.href= "starter.html?lang=" + "cn";
                            }else {
                                $("#login-form").prepend("<div class='alert alert-danger' role='alert'><button type='button' class='close' data-dismiss='alert' id='btnClose'><span aria-hidden='true'>&times;</span><span class='sr-only'>Close</span></button><strong>登陆失败！</strong> 2用户名或密码错误，请重试！</div>");
                            }
                        }
                    },
                    error:function(XMLHttpRequest, textStatus, errorThrown){
                        /*console.log(XMLHttpRequest);
                         console.log(textStatus);
                         console.log(errorThrown);*/

                        if($("#btnClose").length > 0){
                            $("#btnClose").trigger("click");
                        }
                        $("#btn-login").button('reset');
                        $("#login-form").prepend("<div class='alert alert-danger' role='alert'><button id='btnClose' type='button' class='close' data-dismiss='alert'><span aria-hidden='true'>&times;</span><span class='sr-only'>Close</span></button><strong>登陆失败！</strong> error用户名或密码错误，请重试！</div>");
                    }
                });
            });
        });

    </script>
</head>
<body class="hold-transition login-page">
<div class="login-box vertical-center">
    <div class="login-logo">
        <!--
        <span><b>SYB</b></span><span>oard</span>
        -->
        <span><b> </b></span><span> </span>
    </div>
    <!-- /.login-logo -->
    <div class="login-box-body">
        <div class="login-box-msg">
            <img src="img/favicon.ico">
        </div>
        <p class="login-box-msg"></p>
        <p class="login-box-msg"></p>
        <form role="form" id="login-form" onsubmit="return false;" autocomplete="off">
            <div class="form-group has-feedback">
                <input type="text" class="form-control" placeholder='用户名' id="username" required autofocus autocomplete="off" name="loginname">
                <span class="glyphicon glyphicon-user form-control-feedback"></span>
            </div>
            <div class="form-group has-feedback">
                <input type="password" class="form-control" placeholder='密码' id="pwd" required autofocus autocomplete="off" name="password">
                <span class="glyphicon glyphicon-lock form-control-feedback"></span>
            </div>

            <div class="row">
                <div class="col-xs-4">
                    <button type="submit" class="btn btn-primary btn-block btn-flat" data-loading-text='<spring:message code="loginStatus"/>' id="btn-login">登录</button>
                </div>
                <!-- /.col -->
            </div>
        </form>
        <p class="login-box-msg"></p>
    </div>
    <!-- /.login-box-body -->
</div>
<!-- /.login-box -->

</body>
</html>