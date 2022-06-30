var urlTimeDepositsCargar="TimeDeposits_cargar.action";

$(document).ready(function(){




    $('#enviar').click(function(){
        var valido=true;

        if($("#id").get(0).value.isEmpty()){
            $("#mensaje").html("Teclee id");
            $("#mensaje").removeClass("blue");
            $("#mensaje").hasClass("red");
            valido=false;
        }
        if(valido)
            TimeDepositsCargarJSONData();

    });


});


function TimeDepositsCargarJSONData(){
    var url = urlTimeDepositsCargar;
    var param={};
    var jsonTimeDeposits=[];
    var TimeDeposits={};

    TimeDeposits.id=$("#id").get(0).value;

    jsonTimeDeposits[0]= TimeDeposits;


    param.jsonTimeDeposits=JSON.stringify({"timeDepositss":jsonTimeDeposits});

    sendServiceJSON(url,param,TimeDepositsCargarSuccess,null,null);
}


function TimeDepositsCargarSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;


    $("#mensaje").html(result.mensaje);
    $("#mensaje").removeClass("red");
    $("#mensaje").hasClass("red");


}






