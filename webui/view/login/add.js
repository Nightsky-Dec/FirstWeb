(function () {
    $("#addBtn").click(function () {
        var params = {
            name: $("input[name='name']").val(),
            pass: $("input[name='pass']").val(),
            confpass: $("input[name='confpass']").val(),
            age: $("input[name='age']").val(),
            email: $("input[name='email']").val(),
        }
        add(params)
    })
    function add(params) {
        $.ajax({
            url: "http://localhost:8088/user/add",
            type: "post",
            data: JSON.stringify(params),
            contentType: "application/json",
            success: function (res) {
                console.log(res)
                if (res.status) {
                    alert("注册成功")
                } else {
                    console.error(res.message)
                }
            },
            error: function (res) {
                console.error(res)
            }
        })
    }

})()