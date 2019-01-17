(function () {
    function getUsers() {
        var tag = "#users_box";
        $(tag).html('<tr style="background: darkgray;"><td>uid</td><td>name</td><td>pass</td><td>email</td><td>age</td><td>修改</td><td>删除</td></tr>');

        $.ajax({
            url: "http://localhost:8088/user/users",
            type: "get",
            contentType: "application/json",
            headers: {
                'X-Auth-Token' : sessionStorage.getItem('token')
            },
            success: function (res) {
                console.log(res)
                if (res.status) {
                    _.each(res.list, r => {
                        var ul = "<tr>"+
                            "<td>"+r.uid+"</td><td>"+r.name+"</td><td>"+r.pass+"</td><td>"+r.email+"</td><td>"+r.age+"</td>"+
                            "<td><input type='button' value='Update' dataId='"+r.id+"' dataName='"+r.name+"' dataAge='"+r.age+"'></td>"+
                            "<td><input type='button' value='Delete' dataId='"+r.id+"'></td>"+
                            "</tr>";
                        $(tag).append(ul);
                    })
                } else {
                    console.log(res.message)
                }
            },
            error: function (res) {
                console.error(res)
            }
        })
    }
    getUsers();

    /**
     * 登出
     * */
    $("#logout").click(function () {

        var params = {
            name: sessionStorage.getItem('user'),
            token: sessionStorage.getItem('token')
        };
        logout(params)
    });
    function logout(params) {
        $.ajax({
            url: "http://localhost:8088/user/logout",
            type: "post",
            data: JSON.stringify(params),
            contentType: "application/json",
            success: function (res) {
                console.log(res);
                if (res.status) {
                    sessionStorage.removeItem('user');
                    sessionStorage.removeItem('token');
                    window.location.href = './login.html'
                } else {
                    console.error(res.message)
                }
            },
            error: function (res) {
                console.error(res)
            }
        })
    }

})();