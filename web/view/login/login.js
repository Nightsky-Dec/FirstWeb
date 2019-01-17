(function () {
    /**
     * Login
     * */
    $("#login").click(function () {
        var params = {
            "name": $("input[name='name']").val(),
            "pass": $("input[name='pass']").val()
        }
        console.log(params.name)
        console.log(params.pass)
        login(params)
    })

    function login(params) {
        $.ajax({
            url: "http://localhost:8088/user/login",
            type: "post",
            data: JSON.stringify(params),
            contentType: "application/json",
            success: function (res) {
                console.log(res)
                // 成功
                if (res.status) {
                    // set token
                    sessionStorage.setItem("token", res.token)
                    sessionStorage.setItem("user", res.user)
                    /**
                     * ajax设置请求头
                     * headers:{"Authorization", token}
                     * */

                    // 登录成功，跳转到主页面
                    window.location.href = "./content.html"
                } else {
                    alert(res.message)
                    $('#prompt').text(res.message);
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                console.error(XMLHttpRequest)
                console.error(textStatus)
                console.error(errorThrown)
            }
        })
    }

})();

function getContextPath() {
    var pathName = document.location.pathname;
    var index = pathName.substr(1).indexOf("/");
    var result = pathName.substr(0,index+1);
    return result;
}

if(window.jQuery) {
    //ajax预处理 后置处理
    var config_contextPath = getContextPath();
    jQuery(document).bind("ajaxSend", function (event, request, settings) {
        var token = sessionStorage.getItem("token");
        //config_contextPath\contextPath 为需要设置token的 全局host,严格判断防止 token发送到其他站点被盗取
        if (token && config_contextPath && settings.url && settings.url.indexOf(config_contextPath) === 0) {
            var headers = settings.headers || {};
            headers["X-Auth-Token"] = token;
            request.setRequestHeader("X-Auth-Token", token);
            settings.headers = headers;
        }
    }).bind("ajaxComplete", function (event, xhr, settings) {
        if (config_contextPath && settings.url && settings.url.indexOf(config_contextPath) === 0 && (settings.dataType === 'JSON' || settings.dataType === 'json')) {
            if (xhr.status == 200 && xhr.responseText) {
                try {
                    var reObj = JSON.parse(xhr.responseText);
                    //特殊code 没有权限 和token失效
                    if (reObj && (reObj.code == 3001 || reObj.code == 3002)) {
                        window.setTimeout(function () {
                            if ($(".layui-layer-dialog.layui-layer-msg:visible").length < 1) {
                                layer.alert(reObj.message, {icon: 2}, function () {
                                    if (reObj.code == 3001) {
                                        var topWindow = parent ? (parent.parent ? (parent.parent.parent ? parent.parent.parent : parent.parent) : parent) : window;
                                        topWindow.location.href = '/login.html';
                                    }
                                });
                            }
                        }, 500);
                    }
                } catch (e) {
                    console.error(e)
                }
            }
        }
    });
}