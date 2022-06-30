"use strict";
$(document).ready(function () {
/*    const accordeon = document.querySelectorAll(".accordeon"),
        accordeonBlock = document.querySelectorAll(".accordeon__block"),
        accordeonTitles = document.querySelectorAll(".accordeon__titles"),
        accordeonContent = document.querySelectorAll(".accordeon__content"),
        btnNextAccordeon = document.querySelectorAll(
            ".form-transfer__button--next"
        );
    accordeonTitles.forEach((title, i) => {
        accordeonTitles[i].addEventListener("click", (e) => {
/!*            accordeonContent.forEach((content, i) => {
                accordeonContent[i].classList.remove("accordeon__content--active");
            });*!/
            accordeonContent[i].classList.toggle("accordeon__content--active");
        });
    });
    $.each(accordeon,function(i,item){
        accordeon[i] &&
        accordeon[i].addEventListener("click", (e) => {
            if (e.target.dataset.accordeonNext) {
                e.preventDefault();
                const t =
                        e.target.parentElement.parentElement.parentElement.parentElement
                            .nextElementSibling,
                    o =
                        e.target.parentElement.parentElement.parentElement.parentElement
                            .nextElementSibling.lastElementChild,
                    n =
                        e.target.parentElement.parentElement.parentElement.parentElement
                            .nextElementSibling.firstElementChild.firstElementChild;
                t.classList.add("accordeon__block--active"),
                    o.classList.add("accordeon__content--active"),
                    n.setAttribute(
                        "src",
                        "../vbtonline/resources/img/icons/ic_table_templates_step_complete.png"
                    );
            }
        })
    })*/
    const accordeonTitles = document.querySelectorAll(".accordeon__titles");
    const accordeonContent = document.querySelectorAll(".accordeon__content");

    accordeonTitles.forEach((title, i) => {
  accordeonTitles[i].addEventListener("click", (e) => {
    accordeonContent.forEach((content, i) => {
      accordeonContent[i].classList.remove("accordeon__content--active");
    });
    accordeonContent[i].classList.add("accordeon__content--active");
  });
});

    $("#calendar__prev").click(function () {
        jQuery.J.ChangeMonth(prevMonth);
        return false
    });

    $("#calendar__next").click(function () {
        jQuery.J.ChangeMonth(nextMonth);
        return false
    });

    $(".banner__description__show_more").click(function (e) {

        console.log($('.banner__description__show_more').parent());
        var parentDesc = $('.banner__description__show_more').parent();
        console.log($(parentDesc.find( ".banner__description" )));
        var description = $(parentDesc.find( ".banner__description" ));

        $( "#dialog-confirm p").html($(description).html());
        if(label_idioma=="es"){
            $( "#dialog-confirm" ).dialog({
                resizable: false,
                draggable: false,
                height: "auto",
                width: 600,
                // modal: true,
                closeOnEscape: false,
                position: {
                    // my: "center",
                    // at: "center",
                    // of: $('#marco_trabajo')
                    // of: $('#Motivos')
                },
                buttons: {
                    'Cerrar': function() {
                        /*validateBeneficiaryBankJSONData()*/
                        $( this ).dialog( "close" );
                    }
                }
            });
        }else{
            $( "#dialog-confirm" ).dialog({
                resizable: false,
                draggable: false,
                height: "auto",
                width: 600,
                // modal: true,
                closeOnEscape: false,
                position: {
                    // my: "center",
                    // at: "center",
                    // of: $('#marco_trabajo')
                    // of: $('#Motivos')
                },
                buttons: {
                    'Close': function() {
                        /*validateBeneficiaryBankJSONData()*/
                        $( this ).dialog( "close" );
                    }
                }
            });
        }

    });
    

})

$("img[data-modal]").click(function (e) {
    return (
        $("#security-modal").modal({
            showClose: !1,
            modalClass: "security-modal",
            fadeDuration: 100,
            blockerClass: "security-modal--blocker",
        }),
            !1
    );
});



