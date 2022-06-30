var urlMutualFundsCargar="MutualFunds_cargar.action";

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
            MutualFundsCargarJSONData();
    });


});


function MutualFundsCargarJSONData(){
    var url = urlMutualFundsCargar;
    var param={};
    var jsonMutualFunds=[];
    var MutualFunds={};

    MutualFunds.id=$("#id").get(0).value;

    jsonMutualFunds[0]= MutualFunds;


    param.jsonMutualFunds=JSON.stringify({"mutualFundss":jsonMutualFunds});

    sendServiceJSON(url,param,MutualFundsCargarSuccess,null,null);
}


function MutualFundsCargarSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;


    $("#mensaje").html(result.mensaje);
    $("#mensaje").removeClass("red");
    $("#mensaje").hasClass("red");


}






