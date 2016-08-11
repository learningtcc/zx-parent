/**
 * Created by Lenovo on 2016/4/25.
 */
//执行ajax，给select赋值
function executeAjax(url,objId){
    executeAjax(url,objId,"");
}

function executeAjax(url,objId,value){
    executeAjax(url,objId,value,false);
}

function executeAjax(url,objId,value,isBindChange){
    $.ajax( {
        type : 'GET',
        contentType : 'application/json',
        url : url,
        dataType : 'json',
        success : function(data) {
            var options = "";
            options = "<option value=''>请选择...</option>";
            //if(required){
            //    options = "<option value=''>请选择...</option>";
            //}
            $.each(data, function(i, item) {
                options += "<option value=" + item.code + ">" + item.name + "</option>";
            });
            $("#"+objId).empty();
            $("#"+objId).html(options);
            $("#"+objId).val(value);
            if(isBindChange){
                $("#"+objId).trigger("change");
            }
        },
        error : function() {
            alert("系统出错，请联系管理员")
        }
    });
}

function findSource(url,objId,value,isBindChange){
    $.ajax( {
        type : 'GET',
        contentType : 'application/json',
        url : url,
        dataType : 'json',
        success : function(data) {
            var options = "";

            $.each(data, function(i, item) {
                options += "<option value=" + item.code + ">" + item.name + "</option>";
            });
            $("#"+objId).empty();
            $("#"+objId).html(options);
            $("#"+objId).val(value);
            if(isBindChange){
                $("#"+objId).trigger("change");
            }
        },
        error : function() {
            alert("系统出错，请联系管理员")
        }
    });
}

//匹配正则验证
function matchFrom(formid,matchDes,errerMsg){
    var value = $("#"+formid).val();
    if(!matchDes.test(value)){
        alert(errerMsg);
        return false;
    }

    return true;
}

function validateLengthFrom(formid,length,errerMsg){
    var value = $("#"+formid).val();
    if(value.length < length){
        alert(errerMsg);
        return false;
    }

    return true;
}