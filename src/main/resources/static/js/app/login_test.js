function login() {
    // alert('hello3');
    var $loginButton = $("#loginButton");
    var username = $("#username").val().trim();
    var password = $("#password").val().trim();
    // var code = $(".one input[name='code']").val().trim();
    // var rememberMe = $(".one input[name='rememberme']").is(':checked');
    if (username === "") {
        // $MB.n_warning("请输入用户名！");
        $("#message").html("请输入用户名！");
        $("#message_model").modal('toggle');
        return;
    }
    if (password === "") {
        // $MB.n_warning("请输入密码！");
        $("#message").html("请输入密码！");
        $("#message_model").modal('toggle');
        return;
    }
    // if (code === "") {
    //     $MB.n_warning("请输入验证码！");
    //     return;
    // }

    $.ajax({
        type: "post",
        url: ctx + "login_test",
        data: {
            "username": username,
            "password": password
        },
        dataType: "json",
        success: function (r) {
            if (r.code === 0) {
                location.href = ctx + 'index';
            } else {
                $("#message").html(r.msg);
                $("#message_model").modal('toggle');
                $loginButton.html("登录");
            }
        }
    });
}
