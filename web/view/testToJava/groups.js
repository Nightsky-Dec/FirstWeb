(function() {
    // getGroups
    $.ajax({
        url:"http://localhost:8088/test/groups",
        type: "get",
        contentType: "application/json",
        success: function(res){
            _.each(res, function(r) {
                $("#groups").append("<tr><td>"+r.group_id+"</td><td>"+r.group_name+"</td></tr>")
            })
        }
    });


})()