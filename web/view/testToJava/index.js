(function() {
    var id = 1;
    $.ajax({
        url:"http://localhost:8088/api/get?id=" + id,
        contentType: "application/json",
        success:function(result){
            // console.log(result.length)
            // var html = []
            // if (result.length) {
            //     for (var i=0;i<result.length;i++) {
            //
            //     }
            // } else {
            //     html = "<div>name: " + result.name + "</div><div>age: " + result.age + "</div>"
            // }
            //
            // $("#app").html(html);
        }
    });

    var loginPostData = JSON.stringify({
        name: "R3",
        age: "12"
    });
    $.ajax({
        type: 'POST',
        url: "http://localhost:8088/api/post",
        dataType: "json",
        contentType: "application/json",
        data: loginPostData,
        success: function(result){
            // console.log(result)
        }
    });

    var params = JSON.stringify({
        name: "R3",
        age: 32,
        designation: "销售",
        salary: 5000
    })
    $.ajax({
        url:"http://localhost:8088/emp/post3",
        type: "post",
        contentType: "application/json",
        data: params,
        success: function(result){
            // console.log(result)
        }
    });

})()