var urlBackOfficeOpciones="BackOffice_opcionesGrupo.action";

$(document).ready(function(){




    $('#search').click(function(){
        var valido=true;

//        if($("#id").get(0).value.isEmpty()){
//            $("#mensaje").html("Teclee id");
//            $("#mensaje").removeClass("blue");
//            $("#mensaje").hasClass("red");
//            valido=false;
//        }
//        if(valido)
            BackOfficeOpcionesGrupoJSONData();

    });


});


function BackOfficeOpcionesGrupoJSONData(){
    var url = urlBackOfficeOpciones;
    var param={};
    var jsonGrupo=[];
    var Opciones={};

    Opciones.desopc=$("#desopc").get(0).value;

    jsonGrupo[0]= Opciones;


    param.jsonGrupo=JSON.stringify({"opcionesGruposs":jsonGrupo});

    sendServiceJSON(url,param,BackOfficeOpcionesGrupoSuccess,null,null);
}


function BackOfficeOpcionesGrupoSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;


    $("#mensaje").html(result.mensaje);
    $("#mensaje").removeClass("red");
    $("#mensaje").hasClass("red");


}

