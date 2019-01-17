(function() {
    /**
     * Users
     * */
    // getUsers
    function getUsers() {
        $.ajax({
            url:"http://localhost:8088/test/users",
            type: "get",
            contentType: "application/json",
            success: function(result){
                handleTable(result, "#users")
            }
        });
    }
    getUsers();

    var getUser = function() {
        $.ajax({
            url:"http://localhost:8088/test/user?id=" + 1,
            type: "get",
            contentType: "application/json",
            success: function(result){
                var data = [];
                data.push(result)
                handleTable(data, "#user")
            }
        });
    };
    getUser();

    function handleTable(res, tag) {
        $(tag).html("");
        var title = '<tr style="background: darkgray;"><td>id</td><td>name</td><td>age</td><td>修改</td><td>删除</td></tr>'
        $(tag).append(title)
        _.each(res, function(r) {
            var html = "<tr>"+
                "<td>"+r.id+"</td><td>"+r.name+"</td><td>"+r.age+"</td>"+
                "<td><input type='button' value='Update' dataId='"+r.id+"' dataName='"+r.name+"' dataAge='"+r.age+"'></td>"+
                "<td><input type='button' value='Delete' dataId='"+r.id+"'></td>"+
                "</tr>";
            $(tag).append(html)
        })
    }

    $("#addNew").click(function() {
        $(".edit_box").show();
        $("#editAdd").show();
    });
    // add
    $("#editAdd").click(function() {
        addUser(function() {
            cancel();
        })
    });
    var addUser = function(fn) {
        var name = $("#edit_name").val();
        var age  = $("#edit_age").val();
        if (!name || !age) { return }
        var paramsAdd = {
            "name": name,
            "age": age
        };
        $.ajax({
            url:"http://localhost:8088/test/add",
            type: "post",
            data: JSON.stringify(paramsAdd),
            contentType: "application/json",
            success: function(result){
                console.log('Service Add',result);
                getUsers();
                fn && fn();
            }
        });
    };

    // update
    $("#users").on("click", "input[value='Update']", function() {
        var userId = $(this).attr('dataId');
        console.log(userId)
        $("#edit_id").val(userId);
        $("#edit_name").val($(this).attr('dataName'));
        $("#edit_age").val($(this).attr('dataAge'));

        $(".edit_box").show();
        $("#editUpdate").show();
    });
    $("#editUpdate").click(function() {
        updateUser(function() {
            cancel();
        })
    });
    var updateUser = function(fn) {
        if (!$("#edit_id").val()) { return }
        var paramsAdd = {
            "id": $("#edit_id").val(),
            "name": $("#edit_name").val(),
            "age": $("#edit_age").val()
        };
        $.ajax({
            url:"http://localhost:8088/test/update",
            type: "post",
            data: JSON.stringify(paramsAdd),
            contentType: "application/json",
            success: function(result){
                console.log('Service Update',result);
                getUsers();
                fn && fn();
            }
        });
    };

    // delete
    $("#users").on("click", "input[value='Delete']", function() {
        var id = $(this).attr('dataId')
        delUser(id)
    });
    var delUser = function(id) {
        $.ajax({
            url:"http://localhost:8088/test/delete?id=" + id,
            type: "delete",
            contentType: "application/json",
            success: function(result){
                console.log(result)
                getUsers()
            }
        });
    };

    $("#editCancel").click(function() {
        cancel();
    })
    var cancel = function() {
        $(".edit_box").hide();
        $("#editAdd").hide();
        $("#editUpdate").hide();

        $("#edit_id").val('');
        $("#edit_name").val('');
        $("#edit_age").val('');
    }

})();