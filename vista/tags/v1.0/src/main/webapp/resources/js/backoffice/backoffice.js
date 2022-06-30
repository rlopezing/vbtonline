var urlBackOfficeCargar="BackOffice_cargar.action";

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
            BackOfficeCargarJSONData();

    });


});


function BackOfficeCargarJSONData(){
    var url = urlBackOfficeCargar;
    var param={};
    var jsonBackOffice=[];
    var BackOffice={};

    BackOffice.id=$("#id").get(0).value;

    jsonBackOffice[0]= BackOffice;


    param.jsonBackOffice=JSON.stringify({"backOffices":jsonBackOffice});

    sendServiceJSON(url,param,BackOfficeCargarSuccess,null,null);
}


function BackOfficeCargarSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;


    $("#mensaje").html(result.mensaje);
    $("#mensaje").removeClass("red");
    $("#mensaje").hasClass("red");


}






