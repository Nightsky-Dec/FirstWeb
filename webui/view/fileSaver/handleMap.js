(function() {
    var map;
    function getMap(callback) {
        $.get("json/map.json",function(res) {
            // console.log(res)
            map = res
            handleMapDistrict(res, callback)
        })
    }

    function getCounties(num, fn) {
        var path = "json/geometryCouties/" + num + "00.json"
        $.get(path,function(res) {
            // console.log(res)
            fn && fn(res)
        })
    }

    function handleMap(city, key, fn) {
        _.each(city, function(city) {
            if (key === city.orgNO) {
                getCounties(city.id, fn)
            }
        })
    }
    
    function getJsonLength(jsonData) {
        var jsonLength = 0;
        for(var item in jsonData){
            jsonLength++;
        }
        return jsonLength;
    }

    function handleMapDistrict(data, callback) {
        var jsonLength = getJsonLength(data.district)
        var num = 0;
        _.map(data.district, function(list, key) {
            handleMap(data.city[33], key, function(coutieInfo) {
                num ++;
                changeCoutieId(list, coutieInfo)
                if (jsonLength === num) {
                    callback && callback(data)
                }
            })
        })
    }

    function changeCoutieId(list, coutieInfo) {
        var data = _.clone(list)
        _.each(data, function (l) {
            var lastLabel = l.orgName.replace(/\s+/g,"").substr(-2)
            if (lastLabel !== '直属') {
                var name = l.orgName.replace(/\s+/g,"").substr(0,2)
                var id = _.find(coutieInfo.features, function(feature) {
                    return feature.properties.name.replace(/\s+/g,"").substr(0,2) === name
                }).properties.id
                // console.log(id)
                l.id = id
                l.name = l.orgName.replace(/\s+/g,"").substr(0,3)
            }
        });
    }

    $("#btnToMap").click(function() {
        var loading = true;
        getMap(function(res) {
            loading = false;
            console.log(res)
            var blob = new Blob([JSON.stringify(map)], { type: "" });
            saveAs(blob, "map.json");
        });
    })
})()